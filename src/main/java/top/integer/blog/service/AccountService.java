package top.integer.blog.service;

import top.integer.blog.model.dto.account.AccountAddDto;
import top.integer.blog.model.dto.account.CommonPageQueryDto;
import top.integer.blog.model.vo.PageVo;
import top.integer.blog.model.vo.account.info.AccountItemVo;
import top.integer.blog.model.vo.account.info.AccountDetailVo;

public interface AccountService {
    PageVo<AccountItemVo> pageAccount(CommonPageQueryDto dto);

    AccountDetailVo getAccountById(Integer id);

    void addAccount(AccountAddDto dto);
}
