package top.integer.blog.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 批量添加用户角色
 */
@ToString
@Data
public class UserRoleAddBatchDto {
    @NotNull(message = "用户id不能为空")
    private Long userId;
    @NotNull(message = "角色id不能为空")
    private List<Long> roleIds;
}
