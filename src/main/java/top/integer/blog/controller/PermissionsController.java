package top.integer.blog.controller;

import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
     * 添加权限。
     *
     * @param permissionsDto
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(summary = "保存权限")
    public R<Boolean> save(@RequestBody @Parameter(description = "权限") PermissionsDto permissionsDto) {
        Permissions permissions = new Permissions();
        BeanUtils.copyProperties(permissionsDto, permissions);
        return R.ok(permissionsService.save(permissions));
    }

    /**
     * 根据主键删除权限。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(summary = "根据主键权限")
    public R<Boolean> remove(@PathVariable @Parameter(description = "权限主键") Serializable id) {
        return R.ok(permissionsService.removeById(id));
    }

    /**
     * 根据主键更新
     *
     * @param permissionsUpdateDto 权限
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(summary = "根据主键更新权限")
    public R<Boolean> update(@RequestBody @Parameter(description = "权限主键") PermissionsUpdateDto permissionsUpdateDto) {
        Permissions permissions = new Permissions();
        BeanUtils.copyProperties(permissionsUpdateDto, permissions);
        return R.ok(permissionsService.updateById(permissions));
    }

    /**
     * 查询所有权限。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @Operation(summary = "查询所有权限")
    public R<List<PermissionsVo>> list() {
        return R.ok(permissionsService.list().stream().map(it -> BeanUtil.copy(it, new PermissionsVo())).toList());
    }

    /**
     * 根据权限主键获取详细信息。
     *
     * @param id 权限主键
     * @return 权限详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(summary = "根据主键获取权限")
    public R<PermissionsVo> getInfo(@PathVariable Serializable id) {
        return R.ok(BeanUtil.copy(permissionsService.getById(id), new PermissionsVo()));
    }

    /**
     * 分页查询权限。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    @Operation(summary = "分页查询权限")
    public R<Page<Permissions>> page(@Parameter(description = "分页信息") Page<Permissions> page) {
        return R.ok(permissionsService.page(page));
    }

}
