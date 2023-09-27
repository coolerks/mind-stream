package top.integer.blog.controller;

import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.integer.blog.model.dto.PageQueryDto;
import top.integer.blog.model.dto.UserRoleAddBatchDto;
import top.integer.blog.model.dto.UserRolesDto;
import top.integer.blog.model.dto.update.UserRolesUpdateDto;
import top.integer.blog.model.entity.UserRoles;
import top.integer.blog.model.vo.PageVo;
import top.integer.blog.model.vo.R;
import top.integer.blog.model.vo.UserRolesVo;
import top.integer.blog.model.vo.role.UserRoleVo;
import top.integer.blog.service.UserRolesService;
import top.integer.blog.utils.BeanUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 用户角色 控制层。
 *
 * @author moyok
 * @since 0.1
 */
@RestController
@Tag(name = "用户角色接口")
@RequestMapping("/account/role")
public class UserRolesController {

    @Autowired
    private UserRolesService userRolesService;

    /**
     * 添加用户角色。
     *
     * @param userRolesDto
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(summary = "保存用户角色")
    public R<Boolean> save(@RequestBody @Parameter(description = "用户角色") UserRolesDto userRolesDto) {
        UserRoles userRoles = new UserRoles();
        BeanUtils.copyProperties(userRolesDto, userRoles);
        return R.ok(userRolesService.save(userRoles));
    }

    /**
     * 批量用户-角色信息
     */
    @PostMapping("/")
    @Operation(summary = "批量保存用户角色")
    public R<String> saveUserRoles(@RequestBody UserRoleAddBatchDto dto) {
        userRolesService.save(dto);
        return R.ok();
    }

    @PostMapping("/user/{id}")
    @Operation(summary = "分页获取用户的角色", parameters = {
            @Parameter(name = "pageNumber"),
            @Parameter(name = "pageSize")
    })
    public R<PageVo<UserRoleVo>> pageUserRole(@PathVariable Long id, PageQueryDto dto) {
        PageVo<UserRoleVo> page = userRolesService.pageUserRole(id, dto);
        return R.ok(page);
    }

    /**
     * 根据主键删除用户角色。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(summary = "根据主键用户角色")
    public R<Boolean> remove(@PathVariable @Parameter(description = "用户角色主键") Serializable id) {
        return R.ok(userRolesService.removeById(id));
    }

    /**
     * 根据主键更新
     *
     * @param userRolesUpdateDto 用户角色
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(summary = "根据主键更新用户角色")
    public R<Boolean> update(@RequestBody @Parameter(description = "用户角色主键") UserRolesUpdateDto userRolesUpdateDto) {
        UserRoles userRoles = new UserRoles();
        BeanUtils.copyProperties(userRolesUpdateDto, userRoles);
        return R.ok(userRolesService.updateById(userRoles));
    }

    /**
     * 查询所有用户角色。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @Operation(summary = "查询所有用户角色")
    public R<List<UserRolesVo>> list() {
        return R.ok(userRolesService.list().stream().map(it -> BeanUtil.copy(it, new UserRolesVo())).toList());
    }

    /**
     * 根据用户角色主键获取详细信息。
     *
     * @param id 用户角色主键
     * @return 用户角色详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(summary = "根据主键获取用户角色")
    public R<UserRolesVo> getInfo(@PathVariable Serializable id) {
        return R.ok(BeanUtil.copy(userRolesService.getById(id), new UserRolesVo()));
    }

    /**
     * 分页查询用户角色。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    @Operation(summary = "分页查询用户角色")
    public R<Page<UserRoles>> page(@Parameter(description = "分页信息") Page<UserRoles> page) {
        return R.ok(userRolesService.page(page));
    }

}
