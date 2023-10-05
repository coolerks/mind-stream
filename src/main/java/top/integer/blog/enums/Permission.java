package top.integer.blog.enums;

/**
 * 权限列表
 */
public enum Permission {
    /**
     * 用户管理相关权限
     */
    VIEW_USER_LIST(1, "view_user_list", "查看用户列表"),
    VIEW_USER_DETAILS(2, "view_user_details", "查看用户详情"),
    ADD_USER(3, "add_user", "添加用户"),
    EDIT_USER(4, "edit_user", "编辑用户"),
    DELETE_USER(5, "delete_user", "删除用户"),

    /**
     * 角色管理相关权限
     */
    VIEW_ROLE_LIST(6, "view_role_list", "查看角色列表"),
    VIEW_ROLE_DETAILS(7, "view_role_details", "查看角色详情"),
    DELETE_ROLE(8, "delete_role", "删除角色"),
    ADD_ROLE(9, "add_role", "新增角色"),
    ASSIGN_USER_ROLE(10, "assign_user_role", "分配用户角色"),
    CANCEL_USER_ROLE(11, "cancel_user_role", "取消用户角色"),
    VIEW_USER_ROLE(12, "view_user_role", "查看用户角色"),
    /**
     * 菜单管理相关权限
     */
    ADD_ROLE_MENU(13, "add_role_menu", "添加角色菜单"),
    CANCEL_ROLE_MENU(14, "cancel_role_menu", "取消角色菜单"),
    VIEW_ALL_MENUS(15, "view_all_menus", "查看所有菜单"),
    VIEW_ROLE_MENUS(16, "view_role_menus", "查看角色菜单"),

    /**
     * 权限管理相关权限
     */
    VIEW_ALL_PERMISSIONS(17, "view_all_permissions", "查看所有权限"),
    ASSIGN_PERMISSION(18, "assign_permission", "分配权限"),
    CANCEL_PERMISSION(19, "cancel_permission", "取消权限");

    /**
     * id
     */
    public final long id;
    /**
     * 权限名称
     */
    public final String name;
    /**
     * 描述信息
     */
    public final String description;

    Permission(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
