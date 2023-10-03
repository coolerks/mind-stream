package top.integer.blog.service.impl;

import com.mybatisflex.core.query.DistinctQueryColumn;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.integer.blog.annotation.Version;
import top.integer.blog.constant.MenuConstant;
import top.integer.blog.mapper.MenuMapper;
import top.integer.blog.model.def.MenuDef;
import top.integer.blog.model.def.RoleMenusDef;
import top.integer.blog.model.def.UserRolesDef;
import top.integer.blog.model.entity.Menu;
import top.integer.blog.model.vo.MenusVo;
import top.integer.blog.model.vo.MenuVo;
import top.integer.blog.operation.MenuOperation;
import top.integer.blog.service.MenuService;
import top.integer.blog.utils.UserUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 路由菜单 服务层实现。
 *
 * @author moyok
 * @since 0.1
 */
@Service
@Transactional
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService, MenuOperation {

    @Override
    public List<MenusVo> userMenu() {
        Long userId = UserUtils.getUserId();
        MenuDef m = MenuDef.MENU;
        RoleMenusDef rm = RoleMenusDef.ROLE_MENUS;
        UserRolesDef ur = UserRolesDef.USER_ROLES;

        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(new DistinctQueryColumn(m.ID, m.PARENT_ID, m.MENU_NAME, m.MENU_URI, m.ICON, m.ORDER))
                .from(m)
                .join(rm).on(m.ID.eq(rm.MENU_ID))
                .join(ur).on(ur.ROLE_ID.eq(rm.ROLE_ID))
                .where(m.IS_SHOW.eq(true))
                .and(ur.USER_ID.eq(userId))
                .orderBy(m.ORDER, true);
        return buildMenus(this.mapper.selectListByQueryAs(queryWrapper, MenuVo.class));
    }

    @Override
    public List<MenusVo> allMenus() {
        MenuDef m = MenuDef.MENU;
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(m.ID, m.PARENT_ID, m.MENU_NAME, m.MENU_URI, m.ICON, m.ORDER)
                .from(m)
                .where(m.IS_SHOW.eq(true))
                .orderBy(m.ORDER, true);
        return buildMenus(this.mapper.selectListByQueryAs(queryWrapper, MenuVo.class));
    }


    public List<MenusVo> buildMenus(List<MenuVo> all) {
        List<MenusVo> menus = all.stream()
                .map(MenusVo::fromMenuVo)
                .toList();
        return buildMenus(menus, 0L);
    }


        public List<MenusVo> buildMenus(List<MenusVo> all, Long parentId) {
        return all.stream()
                .filter(it -> Objects.equals(it.getParentId(), parentId))
                .peek(it -> it.setRoutes(buildMenus(all, it.getId())))
                .toList();
    }

    @Override
    public Set<Long> existMenus(List<Long> ids) {
        MenuDef m = MenuDef.MENU;
        QueryWrapper wrapper = QueryWrapper.create()
                .select(m.ID)
                .from(m)
                .where(m.ID.in(ids));
        return this.mapper.selectListByQuery(wrapper).stream().map(Menu::getId).collect(Collectors.toSet());
    }
}
