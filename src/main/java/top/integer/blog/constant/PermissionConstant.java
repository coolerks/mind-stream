package top.integer.blog.constant;

import top.integer.blog.enums.Permission;
import top.integer.blog.model.entity.Permissions;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 权限常量类
 */
public class PermissionConstant {

    public static List<Permissions> userManagerPermissions() {
        return List.of(
                Permissions.of(Permission.VIEW_USER_LIST),
                Permissions.of(Permission.VIEW_USER_DETAILS),
                Permissions.of(Permission.ADD_USER),
                Permissions.of(Permission.EDIT_USER),
                Permissions.of(Permission.DELETE_USER)
        );
    }

    public static List<Permissions> roleManagerPermissions() {
        return List.of(
                Permissions.of(Permission.VIEW_ROLE_LIST),
                Permissions.of(Permission.VIEW_ROLE_DETAILS),
                Permissions.of(Permission.DELETE_ROLE),
                Permissions.of(Permission.ADD_ROLE),
                Permissions.of(Permission.ASSIGN_USER_ROLE),
                Permissions.of(Permission.CANCEL_USER_ROLE)
        );
    }

    public static List<Permissions> menuMangerPermissions() {
        return List.of(
                Permissions.of(Permission.VIEW_USER_ROLE),
                Permissions.of(Permission.ADD_ROLE_MENU),
                Permissions.of(Permission.CANCEL_ROLE_MENU),
                Permissions.of(Permission.VIEW_ALL_MENUS),
                Permissions.of(Permission.VIEW_ROLE_MENUS)
        );
    }

    public static List<Permissions> rightsManagementPermissions() {
        return List.of(
                Permissions.of(Permission.VIEW_ALL_PERMISSIONS),
                Permissions.of(Permission.ASSIGN_PERMISSION),
                Permissions.of(Permission.CANCEL_PERMISSION)
        );
    }

    public static List<Permissions> filesPermissions() {
        return List.of(
                Permissions.of(Permission.UPLOAD_FILE),
                Permissions.of(Permission.CREATE_FOLDER),
                Permissions.of(Permission.DELETE_FILE),
                Permissions.of(Permission.GET_FILE_LIST)
        );
    }

    private PermissionConstant() {
    }
}
