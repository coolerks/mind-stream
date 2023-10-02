package top.integer.blog.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.integer.blog.exception.DataException;
import top.integer.blog.mapper.UserRolesMapper;
import top.integer.blog.model.Pair;
import top.integer.blog.model.def.AccountInfoDef;
import top.integer.blog.model.def.AccountUserDef;
import top.integer.blog.model.def.RoleDef;
import top.integer.blog.model.def.UserRolesDef;
import top.integer.blog.model.dto.PageQueryDto;
import top.integer.blog.model.dto.UserRoleBatchDto;
import top.integer.blog.model.entity.UserRoles;
import top.integer.blog.model.vo.PageVo;
import top.integer.blog.model.vo.account.info.AccountRoleItemVo;
import top.integer.blog.model.vo.role.UserRoleVo;
import top.integer.blog.operation.AccountOperation;
import top.integer.blog.operation.RoleOperation;
import top.integer.blog.service.UserRolesService;
import top.integer.blog.utils.UserUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户角色 服务层实现。
 *
 * @author moyok
 * @since 0.1
 */
@Service
@RequiredArgsConstructor
public class UserRolesServiceImpl extends ServiceImpl<UserRolesMapper, UserRoles> implements UserRolesService {
    private final AccountOperation accountOperation;
    private final RoleOperation roleOperation;

    @Override
    public void save(UserRoleBatchDto dto) {
        Long userId = UserUtils.getUserId();

        List<Long> ids = dto.getRoleIds().stream().distinct().toList();
        if (ids.isEmpty()) {
            throw new DataException("角色信息不能为空");
        }
        if (!accountOperation.existAccount(dto.getUserId())) {
            throw new DataException("不存在此用户");
        }

        // 被设置过的角色id集合
        Set<Long> roleIdSet = existRole(ids);
        // 还没有被此用户添加的角色
        List<Long> roleIds = ids.stream().filter(it -> !roleIdSet.contains(it)).toList();
        // 所有存在的角色
        Set<Long> set = roleOperation.existRole(roleIds);

        LocalDateTime now = LocalDateTime.now();
        List<UserRoles> userRoles = ids.stream()
                .filter(set::contains)
                .map(it -> UserRoles.builder()
                        .createBy(userId).userId(dto.getUserId()).updateBy(userId).roleId(it)
                        .createTime(now).updateTime(now).build())
                .toList();
        if (userRoles.isEmpty()) {
            return;
        }
        mapper.insertBatch(userRoles);
    }

    @Override
    public PageVo<UserRoleVo> pageUserRole(Long id, PageQueryDto dto) {
        UserRolesDef ur = UserRolesDef.USER_ROLES;
        RoleDef r = RoleDef.ROLE;
        Page<UserRoleVo> page = new Page<>(dto.getPageNumber(), dto.getPageSize());
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(ur.ROLE_ID, r.NAME, ur.CREATE_TIME)
                .from(ur)
                .join(r).on(ur.ROLE_ID.eq(r.ID))
                .where(ur.USER_ID.eq(id));
        mapper.paginateWithRelationsAs(page, queryWrapper, UserRoleVo.class);
        return PageVo.of(page);
    }

    @Override
    public PageVo<AccountRoleItemVo> pageRoleUser(Long id, PageQueryDto dto) {
        AccountUserDef u = AccountUserDef.ACCOUNT_USER;
        AccountInfoDef i = AccountInfoDef.ACCOUNT_INFO;
        UserRolesDef r = UserRolesDef.USER_ROLES;

        QueryWrapper wrapper = QueryWrapper.create()
                .select(u.ID, u.USERNAME, u.EMAIL, i.NICKNAME, u.STATUS, r.CREATE_TIME.as("create_time"), i.AVATAR)
                .from(u)
                .join(i).on(u.ID.eq(i.ID))
                .join(r).on(r.USER_ID.eq(u.ID))
                .where(r.ROLE_ID.eq(id));

        Page<AccountRoleItemVo> page = mapper.paginateAs(new Page<>(dto.getPageNumber(), dto.getPageSize()),
                wrapper, AccountRoleItemVo.class);
        return PageVo.of(page);
    }

    @Override
    public void deleteBatch(UserRoleBatchDto dto) {
        List<Pair<Long, Long>> ids = dto.getRoleIds().stream()
                .map(it -> new Pair<>(dto.getUserId(), it))
                .toList();
        mapper.deleteBatch(ids);
    }

    @Override
    public List<Long> listUserRoleIds(Long id) {
        UserRolesDef r = UserRolesDef.USER_ROLES;
        QueryWrapper wrapper = QueryWrapper.create()
                .select(r.ROLE_ID)
                .from(r)
                .where(r.USER_ID.eq(id));
        return mapper.selectListByQuery(wrapper)
                .stream()
                .map(UserRoles::getRoleId)
                .toList();
    }

    @Override
    public Set<Long> existRole(List<Long> ids) {
        UserRolesDef ur = UserRolesDef.USER_ROLES;
        List<Long> list = ids.stream().distinct().toList();
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(ur.ROLE_ID)
                .from(ur)
                .where(ur.ROLE_ID.in(list));
        return mapper.selectListByQuery(queryWrapper).stream().map(UserRoles::getRoleId).collect(Collectors.toSet());
    }
}
