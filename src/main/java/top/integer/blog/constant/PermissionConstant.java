package top.integer.blog.constant;

import top.integer.blog.model.entity.Permissions;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 权限常量类
 */
public class PermissionConstant {

    public static List<Permissions> userManagerPermissions() {
        LocalDateTime now = LocalDateTime.now();
        Permissions.builder().id(1L).name("查看用户列表").description("");
        return List.of();
    }
    private PermissionConstant() {
    }
}
