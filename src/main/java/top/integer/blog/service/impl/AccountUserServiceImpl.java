package top.integer.blog.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.integer.blog.model.entity.AccountUser;
import top.integer.blog.mapper.AccountUserMapper;
import top.integer.blog.service.AccountUserService;
import org.springframework.stereotype.Service;

/**
 * 账户 服务层实现。
 *
 * @author moyok
 * @since 0.1
 */
@Service
public class AccountUserServiceImpl extends ServiceImpl<AccountUserMapper, AccountUser> implements AccountUserService {

}
