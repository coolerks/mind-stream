package top.integer.blog.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import top.integer.blog.enums.FileStorageStrategy;

import java.util.List;

@Data
@ToString
@ConfigurationProperties("file")
public class FileStorageProperties {
    private String bathPath = "/";
    private List<ObjectStorage> polices;
    private FileStorageStrategy mode = FileStorageStrategy.TENCENT_CLOUD;
}
