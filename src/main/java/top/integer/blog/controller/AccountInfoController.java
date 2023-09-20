package top.integer.blog.controller;

import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.integer.blog.model.dto.AccountInfoDto;
import top.integer.blog.model.dto.update.AccountInfoUpdateDto;
import top.integer.blog.model.entity.AccountInfo;
import top.integer.blog.model.vo.account.AccountInfoVo;
import top.integer.blog.model.vo.R;
import top.integer.blog.service.AccountInfoService;
import top.integer.blog.utils.BeanUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 账户信息 控制层。
 *
 * @author moyok
 * @since 0.1
 */
@RestController
@Tag(name = "账户信息接口")
@RequestMapping("/accountInfo")
public class AccountInfoController {

    @Autowired
    private AccountInfoService accountInfoService;

    /**
     * 添加账户信息。
     *
     * @param accountInfoDto
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "保存账户信息")
    public R<Boolean> save(@RequestBody @Parameter(description = "账户信息") AccountInfoDto accountInfoDto) {
        AccountInfo accountInfo = new AccountInfo();
        BeanUtils.copyProperties(accountInfoDto, accountInfo);
        return R.ok(accountInfoService.save(accountInfo));
    }

    /**
     * 根据主键删除账户信息。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(summary = "根据主键账户信息")
    public R<Boolean> remove(@PathVariable @Parameter(description = "账户信息主键") Serializable id) {
        return R.ok(accountInfoService.removeById(id));
    }

    /**
     * 根据主键更新
     *
     * @param accountInfoUpdateDto 账户信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(summary = "根据主键更新账户信息")
    public R<Boolean> update(@RequestBody @Parameter(description = "账户信息主键") AccountInfoUpdateDto accountInfoUpdateDto) {
        AccountInfo accountInfo = new AccountInfo();
        BeanUtils.copyProperties(accountInfoUpdateDto, accountInfo);
        return R.ok(accountInfoService.updateById(accountInfo));
    }

    /**
     * 查询所有账户信息。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @Operation(summary = "查询所有账户信息")
    public R<List<AccountInfoVo>> list() {
        return R.ok(accountInfoService.list().stream().map(it -> BeanUtil.copy(it, new AccountInfoVo())).toList());
    }

    /**
     * 根据账户信息主键获取详细信息。
     *
     * @param id 账户信息主键
     * @return 账户信息详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(summary = "根据主键获取账户信息")
    public R<AccountInfoVo> getInfo(@PathVariable Serializable id) {
        return R.ok(BeanUtil.copy(accountInfoService.getById(id), new AccountInfoVo()));
    }

    /**
     * 分页查询账户信息。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    @Operation(summary = "分页查询账户信息")
    public R<Page<AccountInfo>> page(@Parameter(description = "分页信息") Page<AccountInfo> page) {
        return R.ok(accountInfoService.page(page));
    }

}
