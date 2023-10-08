package top.integer.blog.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class FilePageQueryDto extends PageQueryDto {
    private Long folderId;
}
