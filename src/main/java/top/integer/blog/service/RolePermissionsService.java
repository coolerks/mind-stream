package top.integer.blog.service;

import com.mybatisflex.core.service.IService;
import top.integer.blog.model.dto.RolePermissionBatchDto;
import top.integer.blog.model.entity.RolePermissions;
import top.integer.blog.operation.RolePermissionOperation;

import java.util.List;

/**
 * 角色-权限 服务层。
 *
 * @author moyok
 * @since 0.1
 */
public interface RolePermissionsService extends IService<RolePermissions>, RolePermissionOperation {

    List<Long> getRolePermissionIds(Long id);

    void addPermission(RolePermissionBatchDto dto);

    void removePermission(RolePermissionBatchDto dto);
}
