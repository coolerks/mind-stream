package top.integer.blog.controller;

import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.integer.blog.annotation.PermissionCheck;
import top.integer.blog.enums.Permission;
import top.integer.blog.model.dto.MenuDto;
import top.integer.blog.model.dto.update.MenuUpdateDto;
import top.integer.blog.model.entity.Menu;
import top.integer.blog.model.vo.MenuVo;
import top.integer.blog.model.vo.MenusVo;
import top.integer.blog.model.vo.R;
import top.integer.blog.service.MenuService;
import top.integer.blog.utils.BeanUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 路由菜单 控制层。
 *
 * @author moyok
 * @since 0.1
 */
@RestController
@Tag(name = "路由菜单接口")
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @GetMapping("/")
    @Operation(summary = "获取当前用户的路由菜单")
    public R<List<MenusVo>> getCurrentUserRouteMenus() {
        return R.ok(menuService.userMenu());
    }

    @GetMapping("/all")
    @Operation(summary = "获取所有的菜单")
    @PermissionCheck(Permission.VIEW_ALL_MENUS)
    public R<List<MenusVo>> getAllMenus() {
        return R.ok(menuService.allMenus());
    }


}
