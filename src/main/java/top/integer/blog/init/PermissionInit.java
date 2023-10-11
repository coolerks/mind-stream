package top.integer.blog.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.integer.blog.annotation.Version;
import top.integer.blog.constant.PermissionConstant;
import top.integer.blog.enums.Permission;
import top.integer.blog.mapper.PermissionsMapper;
import top.integer.blog.model.entity.Permissions;

@Component
@RequiredArgsConstructor
@Slf4j
public class PermissionInit implements Init {
    private final PermissionsMapper mapper;

    @Version(0)
    public void version1() {
        mapper.insertBatch(PermissionConstant.userManagerPermissions());
        mapper.insertBatch(PermissionConstant.roleManagerPermissions());
        log.info("初始化用户、角色权限成功");
    }

    @Version(1)
    public void version2() {
        mapper.insertBatch(PermissionConstant.menuMangerPermissions());
        log.info("初始化菜单权限成功");
    }

    @Version(2)
    public void version3() {
        mapper.insertBatch(PermissionConstant.rightsManagementPermissions());
        log.info("初始化权限管理相关权限成功");
    }

    @Version(4)
    public void version4() {
        mapper.insertBatch(PermissionConstant.filesPermissions());
        log.info("初始化文件权限成功");
    }
}
