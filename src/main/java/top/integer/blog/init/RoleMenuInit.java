package top.integer.blog.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.integer.blog.annotation.Version;
import top.integer.blog.constant.RoleMenuConstant;
import top.integer.blog.mapper.RoleMenusMapper;
import top.integer.blog.model.entity.RoleMenus;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoleMenuInit implements Init {
    private final RoleMenusMapper mapper;


    @Version(1)
    private void version1() {
        List<RoleMenus> roleMenus = RoleMenuConstant.initSuperAdminMenus();
        mapper.insertBatch(roleMenus);
        log.info("新增超级管理员菜单成功");
    }

    @Version(3)
    private void version3() {
        List<RoleMenus> roleMenus = RoleMenuConstant.initSuperAdminFileMenus();
        mapper.insertBatch(roleMenus);
        log.info("初始化超级管理员文件菜单成功");
    }

}
