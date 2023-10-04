package top.integer.blog.operation;

import top.integer.blog.enums.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionsOperation {
    /**
     * 是否存在某一个权限
     * @param permissionId 用户id
     * @return 存在/不存在
     */
    default boolean existPermissions(Long permissionId) {
        return existPermissions(List.of(permissionId)).contains(permissionId);
    }

    /**
     * 是否存在用户
     * @param ids id集合
     * @return 存在/不存在
     */
    Set<Long> existPermissions(List<Long> ids);


}
