package top.integer.blog.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.ToString;
import top.integer.blog.model.entity.Permissions;
import top.integer.blog.mapper.PermissionsMapper;
import top.integer.blog.service.PermissionsService;
import org.springframework.stereotype.Service;

/**
 * 权限 服务层实现。
 *
 * @author moyok
 * @since 0.1
 */
@Service
@ToString(callSuper = true)
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements PermissionsService {

}
