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
public class RolePermissionBatchDto {
    @NotNull(message = "角色id不能为空")
    private Long roleId;
    @NotNull(message = "权限id不能为空")
    private List<Long> permissionIds;
}
