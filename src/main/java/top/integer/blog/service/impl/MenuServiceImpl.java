package top.integer.blog.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.integer.blog.annotation.Version;
import top.integer.blog.constant.MenuConstant;
import top.integer.blog.mapper.MenuMapper;
import top.integer.blog.model.def.MenuDef;
import top.integer.blog.model.entity.Menu;
import top.integer.blog.model.vo.MenusVo;
import top.integer.blog.model.vo.MenuVo;
import top.integer.blog.service.MenuService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 路由菜单 服务层实现。
 *
 * @author moyok
 * @since 0.1
 */
@Service
@Transactional
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    private final int version;

    @Version(0)
    private void version1() {
        List<Menu> rootMenus = MenuConstant.rootMenu();
        List<Menu> userMenus = MenuConstant.userMenus();
        this.mapper.insertBatch(rootMenus);
        this.mapper.insertBatch(userMenus);
        log.info("新增根菜单和用户菜单成功");
    }

    public MenuServiceImpl(MenuMapper menuMapper) {
        this.version = 0;
        this.mapper = menuMapper;
        Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Version.class))
                .filter(m -> m.getAnnotation(Version.class).value() > version)
                .forEach(this::invoke);
    }

    private void invoke(Method m) {
        try {
            m.setAccessible(true);
            m.invoke(MenuServiceImpl.this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MenusVo> userMenu() {
        MenuDef m = MenuDef.MENU;
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(m.ID, m.PARENT_ID, m.MENU_NAME, m.MENU_URI, m.ICON, m.ORDER)
                .from(m)
                .where(m.IS_SHOW.eq(true));
        List<MenusVo> menus = this.mapper.selectListByQueryAs(queryWrapper, MenuVo.class)
                .stream()
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
}
