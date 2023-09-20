package top.integer.blog.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.integer.blog.model.entity.AccountInfo;
import top.integer.blog.mapper.AccountInfoMapper;
import top.integer.blog.service.AccountInfoService;
import org.springframework.stereotype.Service;

/**
 * 账户信息 服务层实现。
 *
 * @author moyok
 * @since 0.1
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements AccountInfoService {

}
