package top.integer.blog.model.vo.file.upload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Tolerate;
import top.integer.blog.enums.FileStorageStrategy;

@Data
@AllArgsConstructor
public abstract class UploadRequestResponseVo {
    private String uploadUrl;
    private FileStorageStrategy serviceProvider;
    private String method;
    /**
     * 过期时间
     */
    private Long expiration;

    protected void setFileStorageStrategy(FileStorageStrategy serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Tolerate
    public UploadRequestResponseVo() {
    }
}
