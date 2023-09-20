package top.integer.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.integer.blog.model.dto.RoleDto;
import top.integer.blog.model.dto.account.CommonPageQueryDto;
import top.integer.blog.model.dto.update.RoleUpdateDto;
import top.integer.blog.model.entity.Role;
import top.integer.blog.model.vo.PageVo;
import top.integer.blog.model.vo.R;
import top.integer.blog.model.vo.RoleVo;
import top.integer.blog.model.vo.role.RoleDetailVo;
import top.integer.blog.model.vo.role.RoleItemVo;
import top.integer.blog.service.RoleService;
import top.integer.blog.utils.BeanUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 角色 控制层。
 *
 * @author moyok
 * @since 0.1
 */
@RestController
@Tag(name = "角色接口")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 添加角色。
     *
     * @param roleDto 角色
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/")
    @Operation(summary = "保存角色")
    public R<String> save(@Validated @RequestBody @Parameter(description = "角色") RoleDto roleDto) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        roleService.saveRole(role);
        return R.ok();
    }

    /**
     * 根据主键删除角色。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(summary = "根据主键角色")
    public R<Boolean> remove(@PathVariable @Parameter(description = "角色主键") Serializable id) {
        return R.ok(roleService.removeById(id));
    }

    /**
     * 根据主键更新
     *
     * @param roleUpdateDto 角色
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/")
    @Operation(summary = "更新角色")
    public R<String> update(@RequestBody @Parameter(description = "角色主键") RoleUpdateDto roleUpdateDto) {
        Role role = new Role();
        BeanUtils.copyProperties(roleUpdateDto, role);
        roleService.update(role);
        return R.ok();
    }

    /**
     * 根据角色主键获取详细信息。
     *
     * @param id 角色主键
     * @return 角色详情
     */
    @GetMapping("/info/{id}")
    @Operation(summary = "根据id获取角色详情")
    public R<RoleDetailVo> getRoleDetail(@PathVariable Long id) {
        return R.ok(roleService.getRoleDetailById(id));
    }

    /**
     * 分页查询角色。
     *
     * @return 分页对象
     */
    @GetMapping("/list")
    @Operation(summary = "分页查询角色", parameters = {
            @Parameter(name = "pageNumber"),
            @Parameter(name = "pageSize"),
            @Parameter(name = "status"),
            @Parameter(name = "keyword"),
    })
    public R<PageVo<RoleItemVo>> page(@Parameter(description = "分页信息") CommonPageQueryDto queryDto) {
        return R.ok(roleService.pageRole(queryDto));
    }

}
