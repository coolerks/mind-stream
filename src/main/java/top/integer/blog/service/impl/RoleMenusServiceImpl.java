package top.integer.blog.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.integer.blog.model.entity.RoleMenus;
import top.integer.blog.mapper.RoleMenusMapper;
import top.integer.blog.service.RoleMenusService;
import org.springframework.stereotype.Service;

/**
 * 菜单-角色 服务层实现。
 *
 * @author moyok
 * @since 0.1
 */
@Service
public class RoleMenusServiceImpl extends ServiceImpl<RoleMenusMapper, RoleMenus> implements RoleMenusService {

}
