package top.integer.blog.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Data
@ToString
public class FolderDto {
    @NotNull(message = "父id不能为空")
    private Long parentId;

    @NotNull(message = "名称不能为空")
    @Length(min = 1, max = 32)
    private String name;

    @NotNull(message = "描述信息不能为空")
    @Length(max = 255, message = "描述信息最长为255字符")
    private String description;
}
