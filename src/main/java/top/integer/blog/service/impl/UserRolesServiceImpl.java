package top.integer.blog.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.integer.blog.model.entity.UserRoles;
import top.integer.blog.mapper.UserRolesMapper;
import top.integer.blog.service.UserRolesService;
import org.springframework.stereotype.Service;

/**
 * 用户角色 服务层实现。
 *
 * @author moyok
 * @since 0.1
 */
@Service
public class UserRolesServiceImpl extends ServiceImpl<UserRolesMapper, UserRoles> implements UserRolesService {

}
