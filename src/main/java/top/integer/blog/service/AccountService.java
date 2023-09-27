package top.integer.blog.service;

import top.integer.blog.model.dto.account.AccountAddDto;
import top.integer.blog.model.dto.account.CommonPageQueryDto;
import top.integer.blog.model.dto.account.LoginDto;
import top.integer.blog.model.dto.update.AccountUpdateDto;
import top.integer.blog.model.vo.PageVo;
import top.integer.blog.model.vo.account.info.AccountItemVo;
import top.integer.blog.model.vo.account.info.AccountDetailVo;
import top.integer.blog.operation.AccountOperation;

public interface AccountService extends AccountOperation {
    PageVo<AccountItemVo> pageAccount(CommonPageQueryDto dto);

    AccountDetailVo getAccountById(Long id);

    void addAccount(AccountAddDto dto);

    void updateAccount(AccountUpdateDto dto);

    String login(LoginDto dto);
}
