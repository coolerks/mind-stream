package top.integer.blog.file.support;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.Data;
import lombok.ToString;
import org.springframework.scheduling.annotation.Scheduled;
import top.integer.blog.enums.FileStorageStrategy;
import top.integer.blog.file.AbstractFileManager;
import top.integer.blog.mapper.FilesMapper;
import top.integer.blog.mapper.FolderMapper;
import top.integer.blog.model.vo.file.upload.TencentCosUploadRequestResponseVo;
import top.integer.blog.model.vo.file.upload.UploadRequestResponseVo;
import top.integer.blog.properties.ObjectStorage;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class TencentCosFileManager extends AbstractFileManager {
    private final ObjectMapper objectMapper;
    private final COSClient cosClient;


    @Override
    public UploadRequestResponseVo requestUpload(String path, String redirectUrl, Integer timeout) {
        var start = LocalDateTime.now(ZoneOffset.UTC);
        var end = start.plusMinutes(timeout);
        var keyTime = start.toEpochSecond(ZoneOffset.UTC) + ";" + end.toEpochSecond(ZoneOffset.UTC);

        var policy = new Policy(path, objectStorage.getAccessKey(), keyTime, redirectUrl);
        policy.setExpiration(end.toString() + 'Z');

        String policyJson = null;
        try {
            policyJson = objectMapper.writeValueAsString(policy);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        var policyJsonBase64 = Base64.getEncoder().encodeToString(policyJson.getBytes());

        var hMac = new HMac(HmacAlgorithm.HmacSHA1, objectStorage.getSecretKey().getBytes());
        var signKey = hMac.digestHex(keyTime);
        var stringToSign = SecureUtil.sha1(policyJson);
        HMac hMac2 = new HMac(HmacAlgorithm.HmacSHA1, signKey.getBytes());
        String signature = hMac2.digestHex(stringToSign);

        return TencentCosUploadRequestResponseVo.builder()
                .key(path)
                .qSignAlgorithm("sha1")
                .policy(policyJsonBase64)
                .qAk(objectStorage.getAccessKey())
                .qKeyTime(keyTime)
                .qSignature(signature)
                .uploadUrl(objectStorage.getDomain())
                .expiration(end.toEpochSecond(ZoneOffset.UTC))
                .successActionRedirect(redirectUrl)
                .build();
    }

    @Override
    public boolean upload(String path, InputStream inputStream) {
        cosClient.putObject(objectStorage.getBucket(), path, inputStream, new ObjectMetadata());
        return this.fileExist(path);
    }

    @Override
    public boolean deleteFile(String path) {
        cosClient.deleteObject(objectStorage.getBucket(), path);
        return true;
    }

    @Override
    public boolean fileExist(String path) {
        return cosClient.doesObjectExist(objectStorage.getBucket(), path);
    }

    @Override
    public boolean isSupport(FileStorageStrategy storageStrategy) {
        return storageStrategy == FileStorageStrategy.TENCENT_CLOUD;
    }

    @Override
    public boolean isSupport(int code) {
        return FileStorageStrategy.TENCENT_CLOUD.code == code;
    }

    private COSClient createCosClient(ObjectStorage objectStorage) {
        COSCredentials cred = new BasicCOSCredentials(objectStorage.getAccessKey(), objectStorage.getSecretKey());
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion(new Region(objectStorage.getEndpoint()));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        clientConfig.setSocketTimeout(30 * 1000);
        clientConfig.setConnectionTimeout(30 * 1000);
        clientConfig.setHttpProxyPort(80);
        return new COSClient(cred, clientConfig);
    }


    public TencentCosFileManager(ObjectStorage objectStorage, FilesMapper filesMapper, FolderMapper folderMapper) {
        super(objectStorage, filesMapper, folderMapper);
        this.cosClient = createCosClient(objectStorage);
        this.objectMapper = new ObjectMapper();
    }
}

@Data
@ToString
class Policy {
    /**
     * 过期时间，ISO8601 格式字符串
     */
    private String expiration;
    private final List<Object> conditions;

    /**
     *
     * @param key 文件路径
     * @param secretId id
     * @param keyTime 开始时间和结束时间
     */
    public Policy(String key, String secretId, String keyTime, String successActionRedirect) {
        conditions = List.of(
                List.of("starts-with", "$key", key),
                List.of("starts-with", "$$success_action_redirect", successActionRedirect),
                Map.of("q-sign-algorithm", "sha1"),
                Map.of("q-ak", secretId),
                Map.of("q-sign-time", keyTime)
        );
    }
}
