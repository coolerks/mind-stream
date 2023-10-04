package top.integer.blog.operation;

import top.integer.blog.enums.Permission;

import java.util.Set;

public interface RolePermissionOperation {
    /**
     * 是否有所有权限
     * @param permissions
     * @return
     */
    boolean haveFullPermissions(Permission[] permissions);

    /**
     * 角色的权限
     * @param roleId
     * @return
     */
    Set<Long> rolePermissionIds(Long roleId);
}
