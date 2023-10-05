package top.integer.blog.controller;

import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.integer.blog.annotation.PermissionCheck;
import top.integer.blog.enums.Permission;
import top.integer.blog.model.dto.RoleMenuBatchDto;
import top.integer.blog.model.dto.RoleMenusDto;
import top.integer.blog.model.dto.update.RoleMenusUpdateDto;
import top.integer.blog.model.entity.RoleMenus;
import top.integer.blog.model.vo.R;
import top.integer.blog.model.vo.RoleMenusVo;
import top.integer.blog.service.RoleMenusService;
import top.integer.blog.utils.BeanUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单-角色 控制层。
 *
 * @author moyok
 * @since 0.1
 */
@RestController
@Tag(name = "菜单-角色接口")
@RequestMapping("/role/menus")
public class RoleMenusController {

    @Autowired
    private RoleMenusService roleMenusService;

    @PermissionCheck(Permission.ADD_ROLE_MENU)
    @PostMapping("/")
    @Operation(summary = "添加角色菜单")
    public R<String> addRoleMenu(@Validated @RequestBody RoleMenuBatchDto dto) {
        roleMenusService.addRoleMenu(dto);
        return R.ok();
    }

    @PermissionCheck(Permission.CANCEL_USER_ROLE)
    @DeleteMapping("/")
    @Operation(summary = "移除角色菜单")
    public R<String> removeRoleMenu(@Validated @RequestBody RoleMenuBatchDto dto) {
        roleMenusService.removeRoleMenu(dto);
        return R.ok();
    }

    @PermissionCheck(Permission.VIEW_ROLE_MENUS)
    @GetMapping("/role/{id}")
    @Operation(summary = "获取某角色所有的菜单id")
    public R<List<Long>> getRoleMenuIds(@PathVariable Long id) {
        List<Long> ids = roleMenusService.roleMenuIds(id);
        return R.ok(ids);
    }

}
