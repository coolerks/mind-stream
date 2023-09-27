package top.integer.blog.service;

import com.mybatisflex.core.service.IService;
import top.integer.blog.model.dto.account.CommonPageQueryDto;
import top.integer.blog.model.entity.Role;
import top.integer.blog.model.vo.PageVo;
import top.integer.blog.model.vo.role.RoleDetailVo;
import top.integer.blog.model.vo.role.RoleItemVo;
import top.integer.blog.operation.RoleOperation;

/**
 * 角色 服务层。
 *
 * @author moyok
 * @since 0.1
 */
public interface RoleService extends IService<Role>, RoleOperation {

    void saveRole(Role role);

    PageVo<RoleItemVo> pageRole(CommonPageQueryDto queryDto);

    RoleDetailVo getRoleDetailById(Long id);

    void update(Role role);
}
