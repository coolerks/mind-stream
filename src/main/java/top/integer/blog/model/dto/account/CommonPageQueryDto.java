package top.integer.blog.model.dto.account;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.integer.blog.model.dto.PageQueryDto;

/**
 * 账户分页条件
 *
 * @author singx
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class CommonPageQueryDto extends PageQueryDto {
    private Integer status;
    private String keyword;
}
