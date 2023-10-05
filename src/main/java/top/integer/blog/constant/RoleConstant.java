package top.integer.blog.constant;

import top.integer.blog.model.entity.Role;

import java.time.LocalDateTime;
import java.util.List;

public class RoleConstant {

    public static List<Role> initRole() {
        LocalDateTime now = LocalDateTime.now();
        Role superAdmin = Role.builder()
                .id(1L).name("super-admin").description("超级管理员")
                .createBy(1001L).updateBy(1001L).createTime(now).updateTime(now).status(1).build();
        return List.of(superAdmin);
    }

    private RoleConstant() {

    }
}
