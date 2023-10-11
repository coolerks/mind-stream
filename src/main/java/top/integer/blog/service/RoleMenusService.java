package top.integer.blog.service;

import com.mybatisflex.core.service.IService;
import top.integer.blog.event.RoleEvent;
import top.integer.blog.model.dto.RoleMenuBatchDto;
import top.integer.blog.model.entity.RoleMenus;

import java.util.List;

/**
 * 菜单-角色 服务层。
 *
 * @author moyok
 * @since 0.1
 */
public interface RoleMenusService extends IService<RoleMenus> {

    void addRoleMenu(RoleMenuBatchDto dto);

    void removeRoleMenu(RoleMenuBatchDto dto);

    List<Long> roleMenuIds(Long id);

    void roleEventHandler(RoleEvent event);
}
