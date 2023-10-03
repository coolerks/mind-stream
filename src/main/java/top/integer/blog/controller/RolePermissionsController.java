package top.integer.blog.controller;

import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.integer.blog.model.dto.RolePermissionBatchDto;
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
@RequestMapping("/role/permissions")
public class RolePermissionsController {

    @Autowired
    private RolePermissionsService rolePermissionsService;


    @GetMapping("/role/{id}")
    @Operation(summary = "获取角色已有的权限id")
    public R<List<Long>> getExistPermissionId(@PathVariable Long id) {
        List<Long> ids = rolePermissionsService.getRolePermissionIds(id);
        return R.ok(ids);
    }

    @PostMapping("/")
    @Operation(summary = "添加权限")
    public R<String> addPermission(@RequestBody RolePermissionBatchDto dto) {
        rolePermissionsService.addPermission(dto);
        return R.ok();
    }

    @DeleteMapping("/")
    @Operation(summary = "删除权限")
    public R<String> removePermission(@RequestBody RolePermissionBatchDto dto) {
        rolePermissionsService.removePermission(dto);
        return R.ok();
    }

}
