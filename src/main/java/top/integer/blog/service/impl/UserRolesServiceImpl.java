package top.integer.blog.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
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
import top.integer.blog.operation.AccountEnsureOperation;
import top.integer.blog.operation.AccountOperation;
import top.integer.blog.operation.RoleOperation;
import top.integer.blog.service.UserRolesService;
import top.integer.blog.utils.UserUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
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
    private final static String rolePrefix = "role:user:";
    private final RedisTemplate<String, Object> template;
    private final AccountEnsureOperation accountEnsureOperation;


    @Override
    public void save(UserRoleBatchDto dto) {
        Long userId = UserUtils.getUserId();
        LocalDateTime now = LocalDateTime.now();

        if (dto.getRoleIds().contains(1L)) {
            if (!this.userRoleIds(userId).contains(1L)) {
                throw new DataException("当前用户不是超级管理员，无法为此用户分配超级管理员角色");
            }
        }

        List<Long> distinctIds = dto.getRoleIds().stream().distinct().toList();
        if (distinctIds.isEmpty()) {
            throw new DataException("角色信息不能为空");
        }
        if (!accountOperation.existAccount(dto.getUserId())) {
            throw new DataException("不存在此用户");
        }

        // 去重
        // 过滤掉不存在的角色
        // 过滤掉被设置过的角色
        Set<Long> existRole = roleOperation.existRole(distinctIds);
        Set<Long> ownedRole = new HashSet<>(this.listUserRoleIds(dto.getUserId()));

        List<UserRoles> userRoles = distinctIds.stream()
                .filter(existRole::contains)
                .filter(it -> !ownedRole.contains(it))
                .map(it -> UserRoles.builder()
                        .createBy(userId).userId(dto.getUserId()).updateBy(userId).roleId(it)
                        .createTime(now).updateTime(now).build())
                .toList();
        if (userRoles.isEmpty()) {
            return;
        }
        mapper.insertBatch(userRoles);
        template.delete(rolePrefix + userId);
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
        List<Long> roleIds = dto.getRoleIds();
        if (roleIds.contains(1L)) {
            accountEnsureOperation.ensureExistSuperAdmin(dto.getUserId());
        }
        List<Pair<Long, Long>> ids = roleIds.stream()
                .map(it -> new Pair<>(dto.getUserId(), it))
                .toList();
        mapper.deleteBatch(ids);
        template.delete(rolePrefix + dto.getUserId());
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

    @Override
    public Set<Long> userRoleIds(long userId) {
        String key = rolePrefix + userId;
        Set<Object> ids = template.opsForSet().members(key);
        if (ids == null || ids.isEmpty()) {
            HashSet<Long> roleIdSet = new HashSet<>(listUserRoleIds(userId));
            if (roleIdSet.isEmpty()) {
                template.opsForSet().add(key, -1);
            } else {
                template.opsForSet().add(key, roleIdSet.toArray());
            }
            template.expire(key, 5, TimeUnit.MINUTES);
            return roleIdSet;
        }
        template.expire(key, 5, TimeUnit.MINUTES);
        return ids.stream().map(it -> Long.valueOf(it.toString())).collect(Collectors.toSet());
    }



}
