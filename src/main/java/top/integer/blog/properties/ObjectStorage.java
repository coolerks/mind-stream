package top.integer.blog.properties;

import com.qcloud.cos.http.HttpProtocol;
import lombok.Data;
import lombok.ToString;
import top.integer.blog.enums.FileStorageStrategy;

@Data
@ToString
public class ObjectStorage {
    /**
     * 访问密钥
     */
    private String accessKey;
    /**
     * 密钥
     */
    private String secretKey;
    /**
     * 访问节点
     */
    private String endpoint;
    /**
     * 存储桶
     */
    private String bucket;
    /**
     * 自定义域名
     */
    private String domain;
    /**
     * 重定向的域名
     */
    private String redirectDomain;
    /**
     * 协议
     */
    private HttpProtocol httpProtocol = HttpProtocol.https;

    private FileStorageStrategy mode;
}
