package top.integer.blog.controller;

import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.integer.blog.model.dto.AccountUserDto;
import top.integer.blog.model.dto.update.AccountUserUpdateDto;
import top.integer.blog.model.entity.AccountUser;
import top.integer.blog.model.vo.account.AccountUserVo;
import top.integer.blog.model.vo.R;
import top.integer.blog.service.AccountUserService;
import top.integer.blog.utils.BeanUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 账户 控制层。
 *
 * @author moyok
 * @since 0.1
 */
@RestController
@Tag(name = "账户接口")
@RequestMapping("/accountUser")
public class AccountUserController {

    @Autowired
    private AccountUserService accountUserService;

    /**
     * 添加账户。
     *
     * @param accountUserDto
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(summary = "保存账户")
    public R<Boolean> save(@RequestBody @Parameter(description = "账户") AccountUserDto accountUserDto) {
        AccountUser accountUser = new AccountUser();
        BeanUtils.copyProperties(accountUserDto, accountUser);
        return R.ok(accountUserService.save(accountUser));
    }

    /**
     * 根据主键删除账户。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(summary = "根据主键账户")
    public R<Boolean> remove(@PathVariable @Parameter(description = "账户主键") Serializable id) {
        return R.ok(accountUserService.removeById(id));
    }

    /**
     * 根据主键更新
     *
     * @param accountUserUpdateDto 账户
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(summary = "根据主键更新账户")
    public R<Boolean> update(@RequestBody @Parameter(description = "账户主键") AccountUserUpdateDto accountUserUpdateDto) {
        AccountUser accountUser = new AccountUser();
        BeanUtils.copyProperties(accountUserUpdateDto, accountUser);
        return R.ok(accountUserService.updateById(accountUser));
    }

    /**
     * 查询所有账户。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @Operation(summary = "查询所有账户")
    public R<List<AccountUserVo>> list() {
        return R.ok(accountUserService.list().stream().map(it -> BeanUtil.copy(it, new AccountUserVo())).toList());
    }

    /**
     * 根据账户主键获取详细信息。
     *
     * @param id 账户主键
     * @return 账户详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(summary = "根据主键获取账户")
    public R<AccountUserVo> getInfo(@PathVariable Serializable id) {
        return R.ok(BeanUtil.copy(accountUserService.getById(id), new AccountUserVo()));
    }

    /**
     * 分页查询账户。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    @Operation(summary = "分页查询账户")
    public R<Page<AccountUser>> page(@Parameter(description = "分页信息") Page<AccountUser> page) {
        return R.ok(accountUserService.page(page));
    }

}
