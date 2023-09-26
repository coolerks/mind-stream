package top.integer.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.integer.blog.event.MyEvent;
import top.integer.blog.model.dto.account.AccountAddDto;
import top.integer.blog.model.dto.account.CommonPageQueryDto;
import top.integer.blog.model.dto.update.AccountUpdateDto;
import top.integer.blog.model.vo.PageVo;
import top.integer.blog.model.vo.R;
import top.integer.blog.model.vo.account.info.AccountItemVo;
import top.integer.blog.model.vo.account.info.AccountDetailVo;
import top.integer.blog.service.AccountService;
import top.integer.blog.service.Publisher;

/**
 * 账户
 *
 * @author singx
 */
@Tag(name = "用户账户接口")
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;

    @Operation(summary = "分页查询所有账户", parameters = {
            @Parameter(name = "pageNumber"),
            @Parameter(name = "pageSize"),
            @Parameter(name = "status"),
            @Parameter(name = "keyword"),
    })
    @GetMapping("/list")
    public R<PageVo<AccountItemVo>> listAccount(CommonPageQueryDto dto) {
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        Publisher.publisher.publishEvent(new MyEvent("你好，世界"));
        return R.ok(service.pageAccount(dto));
    }

    @PostMapping("/")
    @Operation(summary = "添加用户")
    public R<String> addAccount(@Validated @RequestBody AccountAddDto dto) {
        service.addAccount(dto);
        return R.ok();
    }

    @PutMapping("/")
    @Operation(summary = "更新用户")
    public R<String> updateAccount(@Validated @RequestBody AccountUpdateDto dto) {
        service.updateAccount(dto);
        return R.ok();
    }

    @Operation(summary = "通过id获取账户信息")
    @GetMapping("/info/{id}")
    public R<AccountDetailVo> getAccountById(@PathVariable Integer id) {
        return R.ok(service.getAccountById(id));
    }
}
