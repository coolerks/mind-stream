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
import top.integer.blog.model.dto.PermissionsDto;
import top.integer.blog.model.dto.update.PermissionsUpdateDto;
import top.integer.blog.model.entity.Permissions;
import top.integer.blog.model.vo.PermissionsVo;
import top.integer.blog.model.vo.R;
import top.integer.blog.service.PermissionsService;
import top.integer.blog.utils.BeanUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 权限 控制层。
 *
 * @author moyok
 * @since 0.1
 */
@RestController
@Tag(name = "权限接口")
@RequestMapping("/permissions")
public class PermissionsController {

    @Autowired
    private PermissionsService permissionsService;


    /**
     * 查询所有权限。
     *
     * @return 所有数据
     */
    @PermissionCheck(Permission.VIEW_ALL_PERMISSIONS)
    @GetMapping("/list")
    @Operation(summary = "查询所有权限")
    public R<List<PermissionsVo>> list() {
        return R.ok(permissionsService.list().stream().map(it -> BeanUtil.copy(it, new PermissionsVo())).toList());
    }
}
