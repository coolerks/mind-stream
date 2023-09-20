package top.integer.blog.controller;

import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/roleMenus")
public class RoleMenusController {

    @Autowired
    private RoleMenusService roleMenusService;

    /**
     * 添加菜单-角色。
     *
     * @param roleMenusDto
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(summary = "保存菜单-角色")
    public R<Boolean> save(@RequestBody @Parameter(description = "菜单-角色") RoleMenusDto roleMenusDto) {
        RoleMenus roleMenus = new RoleMenus();
        BeanUtils.copyProperties(roleMenusDto, roleMenus);
        return R.ok(roleMenusService.save(roleMenus));
    }

    /**
     * 根据主键删除菜单-角色。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(summary = "根据主键菜单-角色")
    public R<Boolean> remove(@PathVariable @Parameter(description = "菜单-角色主键") Serializable id) {
        return R.ok(roleMenusService.removeById(id));
    }

    /**
     * 根据主键更新
     *
     * @param roleMenusUpdateDto 菜单-角色
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(summary = "根据主键更新菜单-角色")
    public R<Boolean> update(@RequestBody @Parameter(description = "菜单-角色主键") RoleMenusUpdateDto roleMenusUpdateDto) {
        RoleMenus roleMenus = new RoleMenus();
        BeanUtils.copyProperties(roleMenusUpdateDto, roleMenus);
        return R.ok(roleMenusService.updateById(roleMenus));
    }

    /**
     * 查询所有菜单-角色。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @Operation(summary = "查询所有菜单-角色")
    public R<List<RoleMenusVo>> list() {
        return R.ok(roleMenusService.list().stream().map(it -> BeanUtil.copy(it, new RoleMenusVo())).toList());
    }

    /**
     * 根据菜单-角色主键获取详细信息。
     *
     * @param id 菜单-角色主键
     * @return 菜单-角色详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(summary = "根据主键获取菜单-角色")
    public R<RoleMenusVo> getInfo(@PathVariable Serializable id) {
        return R.ok(BeanUtil.copy(roleMenusService.getById(id), new RoleMenusVo()));
    }

    /**
     * 分页查询菜单-角色。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    @Operation(summary = "分页查询菜单-角色")
    public R<Page<RoleMenus>> page(@Parameter(description = "分页信息") Page<RoleMenus> page) {
        return R.ok(roleMenusService.page(page));
    }

}
