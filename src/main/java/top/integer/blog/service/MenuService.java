package top.integer.blog.service;

import com.mybatisflex.core.service.IService;
import top.integer.blog.model.entity.Menu;
import top.integer.blog.model.vo.MenusVo;

import java.util.List;

/**
 * 路由菜单 服务层。
 *
 * @author moyok
 * @since 0.1
 */
public interface MenuService extends IService<Menu> {
    List<MenusVo> userMenu();
}
