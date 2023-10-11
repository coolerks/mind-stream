package top.integer.blog.service;

import com.mybatisflex.core.service.IService;
import top.integer.blog.event.RoleEvent;
import top.integer.blog.model.dto.PageQueryDto;
import top.integer.blog.model.dto.UserRoleBatchDto;
import top.integer.blog.model.entity.UserRoles;
import top.integer.blog.model.vo.PageVo;
import top.integer.blog.model.vo.account.info.AccountRoleItemVo;
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

    void save(UserRoleBatchDto roleIds);

    PageVo<UserRoleVo> pageUserRole(Long id, PageQueryDto dto);

    PageVo<AccountRoleItemVo> pageRoleUser(Long id, PageQueryDto dto);

    void deleteBatch(UserRoleBatchDto dto);

    List<Long> listUserRoleIds(Long id);

    void roleEventHandler(RoleEvent event);
}
