package top.integer.blog.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Data
@ToString
public class FileUploadDto {
    @NotNull(message = "文件夹不能为空")
    private Long folderId;

    @NotNull(message = "文件名不能为空")
    @Length(min = 1, max = 128)
    private String name;

    @NotNull(message = "文件大小不能为空")
    private Long size;
}
