package top.integer.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.integer.blog.model.dto.PageQueryDto;
import top.integer.blog.model.dto.UserRoleBatchDto;
import top.integer.blog.model.vo.PageVo;
import top.integer.blog.model.vo.R;
import top.integer.blog.model.vo.account.info.AccountRoleItemVo;
import top.integer.blog.model.vo.role.UserRoleVo;
import top.integer.blog.service.UserRolesService;

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
     * 批量用户-角色信息
     */
    @PostMapping("/")
    @Operation(summary = "批量保存用户角色")
    public R<String> saveUserRoles(@RequestBody UserRoleBatchDto dto) {
        userRolesService.save(dto);
        return R.ok();
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "分页获取用户的角色", parameters = {
            @Parameter(name = "pageNumber"),
            @Parameter(name = "pageSize")
    })
    public R<PageVo<UserRoleVo>> pageUserRole(@PathVariable Long id, PageQueryDto dto) {
        PageVo<UserRoleVo> page = userRolesService.pageUserRole(id, dto);
        return R.ok(page);
    }

    @GetMapping("/role/{id}")
    @Operation(summary = "分页获取角色下的用户", parameters = {
            @Parameter(name = "pageNumber"),
            @Parameter(name = "pageSize")
    })
    public R<PageVo<AccountRoleItemVo>> pageRoleUser(@PathVariable Long id, PageQueryDto dto) {
        PageVo<AccountRoleItemVo> page = userRolesService.pageRoleUser(id, dto);
        return R.ok(page);
    }

    @DeleteMapping("/")
    @Operation(summary = "批量删除用户角色")
    public R<String> deleteUserRole(@Validated @RequestBody UserRoleBatchDto dto) {
        userRolesService.deleteBatch(dto);
        return R.ok();
    }

}
