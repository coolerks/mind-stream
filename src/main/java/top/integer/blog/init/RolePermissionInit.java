package top.integer.blog.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.integer.blog.annotation.Version;
import top.integer.blog.constant.RolePermissionConstant;
import top.integer.blog.mapper.RolePermissionsMapper;
import top.integer.blog.model.entity.RolePermissions;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RolePermissionInit implements Init {
    private final RolePermissionsMapper mapper;


    @Version(1)
    private void version1() {
        List<RolePermissions> rolePermissions = RolePermissionConstant.initSuperAdminPermission();
        mapper.insertBatch(rolePermissions);
        log.info("新增超级管理员权限成功");
    }

    @Version(2)
    private void version2() {
        List<RolePermissions> rolePermissions = RolePermissionConstant.initSuperAdminPermission2();
        mapper.insertBatch(rolePermissions);
        log.info("新增超级管理员授权的权限成功");
    }

}
