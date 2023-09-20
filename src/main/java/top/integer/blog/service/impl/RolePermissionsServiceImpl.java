package top.integer.blog.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.integer.blog.model.entity.RolePermissions;
import top.integer.blog.mapper.RolePermissionsMapper;
import top.integer.blog.service.RolePermissionsService;
import org.springframework.stereotype.Service;

/**
 * 角色-权限 服务层实现。
 *
 * @author moyok
 * @since 0.1
 */
@Service
public class RolePermissionsServiceImpl extends ServiceImpl<RolePermissionsMapper, RolePermissions> implements RolePermissionsService {

}
