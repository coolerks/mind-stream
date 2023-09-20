package top.integer.blog.controller;

import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.integer.blog.model.dto.RolePermissionsDto;
import top.integer.blog.model.dto.update.RolePermissionsUpdateDto;
import top.integer.blog.model.entity.RolePermissions;
import top.integer.blog.model.vo.R;
import top.integer.blog.model.vo.RolePermissionsVo;
import top.integer.blog.service.RolePermissionsService;
import top.integer.blog.utils.BeanUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 角色-权限 控制层。
 *
 * @author moyok
 * @since 0.1
 */
@RestController
@Tag(name = "角色-权限接口")
@RequestMapping("/rolePermissions")
public class RolePermissionsController {

    @Autowired
    private RolePermissionsService rolePermissionsService;

    /**
     * 添加角色-权限。
     *
     * @param rolePermissionsDto
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(summary = "保存角色-权限")
    public R<Boolean> save(@RequestBody @Parameter(description = "角色-权限") RolePermissionsDto rolePermissionsDto) {
        RolePermissions rolePermissions = new RolePermissions();
        BeanUtils.copyProperties(rolePermissionsDto, rolePermissions);
        return R.ok(rolePermissionsService.save(rolePermissions));
    }

    /**
     * 根据主键删除角色-权限。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(summary = "根据主键角色-权限")
    public R<Boolean> remove(@PathVariable @Parameter(description = "角色-权限主键") Serializable id) {
        return R.ok(rolePermissionsService.removeById(id));
    }

    /**
     * 根据主键更新
     *
     * @param rolePermissionsUpdateDto 角色-权限
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(summary = "根据主键更新角色-权限")
    public R<Boolean> update(@RequestBody @Parameter(description = "角色-权限主键") RolePermissionsUpdateDto rolePermissionsUpdateDto) {
        RolePermissions rolePermissions = new RolePermissions();
        BeanUtils.copyProperties(rolePermissionsUpdateDto, rolePermissions);
        return R.ok(rolePermissionsService.updateById(rolePermissions));
    }

    /**
     * 查询所有角色-权限。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @Operation(summary = "查询所有角色-权限")
    public R<List<RolePermissionsVo>> list() {
        return R.ok(rolePermissionsService.list().stream().map(it -> BeanUtil.copy(it, new RolePermissionsVo())).toList());
    }

    /**
     * 根据角色-权限主键获取详细信息。
     *
     * @param id 角色-权限主键
     * @return 角色-权限详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(summary = "根据主键获取角色-权限")
    public R<RolePermissionsVo> getInfo(@PathVariable Serializable id) {
        return R.ok(BeanUtil.copy(rolePermissionsService.getById(id), new RolePermissionsVo()));
    }

    /**
     * 分页查询角色-权限。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    @Operation(summary = "分页查询角色-权限")
    public R<Page<RolePermissions>> page(@Parameter(description = "分页信息") Page<RolePermissions> page) {
        return R.ok(rolePermissionsService.page(page));
    }

}
