package top.integer.blog.model.vo.file.upload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.integer.blog.enums.FileStorageStrategy;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class TencentCosUploadRequestResponseVo extends UploadRequestResponseVo {
    /**
     * 上传的路径+名称
     */
    private String key;
    /**
     * 上传的策略，base64编码
     */
    private String policy;

    /**
     * 签名哈希算法
     */
    @JsonProperty("q-sign-algorithm")
    private String qSignAlgorithm;

    /**
     * SecretId
     */
    @JsonProperty("q-ak")
    private String qAk;

    /**
     * 有效时间
     */
    @JsonProperty("q-key-time")
    private String qKeyTime;

    @JsonProperty("q-signature")
    private String qSignature;

    @JsonProperty("success_action_redirect")
    private String successActionRedirect;

    /**
     * 文件id
     */
    private Long id;

    @Builder
    public TencentCosUploadRequestResponseVo(String key, String policy, String qSignAlgorithm, String qAk, String qKeyTime,
                                             String qSignature, String uploadUrl, long expiration, String successActionRedirect) {
        super(uploadUrl, FileStorageStrategy.TENCENT_CLOUD, "post", expiration);
        this.key = key;
        this.policy = policy;
        this.qSignAlgorithm = qSignAlgorithm;
        this.qAk = qAk;
        this.qKeyTime = qKeyTime;
        this.qSignature = qSignature;
        this.successActionRedirect = successActionRedirect;
        try {
            this.id = Long.valueOf(this.successActionRedirect.split("=")[1]);
        } catch (Exception ignored) {

        }
        setFileStorageStrategy(FileStorageStrategy.TENCENT_CLOUD);
    }
}
