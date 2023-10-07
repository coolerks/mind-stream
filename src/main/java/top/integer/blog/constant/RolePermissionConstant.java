package top.integer.blog.constant;

import top.integer.blog.enums.Permission;
import top.integer.blog.model.entity.RolePermissions;

import java.util.List;

public class RolePermissionConstant {

    public static List<RolePermissions> initSuperAdminPermission() {
        return List.of(
                RolePermissions.of(1L, Permission.VIEW_USER_LIST),
                RolePermissions.of(1L, Permission.VIEW_USER_DETAILS),
                RolePermissions.of(1L, Permission.ADD_USER),
                RolePermissions.of(1L, Permission.EDIT_USER),
                RolePermissions.of(1L, Permission.DELETE_USER),
                RolePermissions.of(1L, Permission.VIEW_ROLE_LIST),
                RolePermissions.of(1L, Permission.VIEW_ROLE_DETAILS),
                RolePermissions.of(1L, Permission.DELETE_ROLE),
                RolePermissions.of(1L, Permission.ADD_ROLE),
                RolePermissions.of(1L, Permission.ASSIGN_USER_ROLE),
                RolePermissions.of(1L, Permission.CANCEL_USER_ROLE),
                RolePermissions.of(1L, Permission.VIEW_USER_ROLE),
                RolePermissions.of(1L, Permission.ADD_ROLE_MENU),
                RolePermissions.of(1L, Permission.CANCEL_ROLE_MENU),
                RolePermissions.of(1L, Permission.VIEW_ALL_MENUS),
                RolePermissions.of(1L, Permission.VIEW_ROLE_MENUS)
        );
    }

    public static List<RolePermissions> initSuperAdminPermission2() {
        return List.of(
                RolePermissions.of(1L, Permission.VIEW_ALL_PERMISSIONS),
                RolePermissions.of(1L, Permission.ASSIGN_PERMISSION),
                RolePermissions.of(1L, Permission.CANCEL_PERMISSION)
        );
    }


    private RolePermissionConstant() {

    }
}
