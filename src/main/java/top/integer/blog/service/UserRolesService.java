package top.integer.blog.service;

import com.mybatisflex.core.service.IService;
import top.integer.blog.model.dto.PageQueryDto;
import top.integer.blog.model.dto.UserRoleAddBatchDto;
import top.integer.blog.model.entity.UserRoles;
import top.integer.blog.model.vo.PageVo;
import top.integer.blog.model.vo.role.UserRoleVo;
import top.integer.blog.operation.UserRoleOperation;

import java.util.List;

/**
 * 用户角色 服务层。
 *
 * @author moyok
 * @since 0.1
 */
public interface UserRolesService extends IService<UserRoles>, UserRoleOperation {

    void save(UserRoleAddBatchDto roleIds);

    PageVo<UserRoleVo> pageUserRole(Long id, PageQueryDto dto);
}
