package top.integer.blog.constant;

import top.integer.blog.model.entity.RoleMenus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoleMenuConstant {
    public static List<RoleMenus> initSuperAdminMenus() {
        LocalDateTime now = LocalDateTime.now();

        RoleMenus.RoleMenusBuilder roleMenusBuilder = RoleMenus.builder()
                .roleId(1L)
                .createTime(now)
                .createBy(1001L)
                .createIp(2130706433L);

        List<RoleMenus> roleRootMenus = MenuConstant.rootMenu().stream()
                .map(it -> roleMenusBuilder.menuId(it.getId()).build())
                .toList();
        List<RoleMenus> userMenus = MenuConstant.userMenus().stream()
                .map(it -> roleMenusBuilder.menuId(it.getId()).build())
                .toList();
        List<RoleMenus> roleMenus = new ArrayList<>(roleRootMenus);
        roleMenus.addAll(userMenus);
        return roleMenus;
    }

    private RoleMenuConstant() {

    }
}
