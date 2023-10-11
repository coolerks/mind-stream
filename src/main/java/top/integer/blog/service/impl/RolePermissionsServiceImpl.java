package top.integer.blog.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.integer.blog.enums.Permission;
import top.integer.blog.event.RoleEvent;
import top.integer.blog.exception.DataException;
import top.integer.blog.mapper.RolePermissionsMapper;
import top.integer.blog.model.Pair;
import top.integer.blog.model.def.RolePermissionsDef;
import top.integer.blog.model.dto.RolePermissionBatchDto;
import top.integer.blog.model.entity.Role;
import top.integer.blog.model.entity.RolePermissions;
import top.integer.blog.operation.PermissionsOperation;
import top.integer.blog.operation.RoleOperation;
import top.integer.blog.operation.RolePermissionOperation;
import top.integer.blog.operation.UserRoleOperation;
import top.integer.blog.service.RolePermissionsService;
import top.integer.blog.utils.IpUtils;
import top.integer.blog.utils.UserUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 角色-权限 服务层实现。
 *
 * @author moyok
 * @since 0.1
 */
@Service
@RequiredArgsConstructor
public class RolePermissionsServiceImpl extends ServiceImpl<RolePermissionsMapper, RolePermissions> implements RolePermissionsService {
    private final PermissionsOperation permissionsOperation;
    private final RoleOperation roleOperation;
    private final String permissionPrefix = "permission:role:";
    private final RedisTemplate<String, Object> template;
    private final UserRoleOperation userRoleOperation;

    @Override
    public List<Long> getRolePermissionIds(Long id) {
        RolePermissionsDef rp = RolePermissionsDef.ROLE_PERMISSIONS;
        QueryWrapper wrapper = QueryWrapper.create()
                .select(rp.PERMISSIONS_ID)
                .from(rp)
                .where(rp.ROLE_ID.eq(id));
        return this.mapper.selectListByQuery(wrapper)
                .stream()
                .map(RolePermissions::getPermissionsId)
                .toList();
    }

    @Override
    public void addPermission(RolePermissionBatchDto dto) {
        Long userId = UserUtils.getUserId();
        long ip = IpUtils.getIpLong();
        LocalDateTime now = LocalDateTime.now();
        Long roleId = dto.getRoleId();
        if (roleId == 1) {
            throw new DataException("不允许更改超级管理员的权限");
        }

        if (!roleOperation.existRole(roleId)) {
            throw new DataException("角色不存在");
        }

        // 过滤重复的id
        List<Long> distinctIds = dto.getPermissionIds()
                .stream()
                .distinct()
                .toList();
        Set<Long> existSet = permissionsOperation.existPermissions(distinctIds);
        Set<Long> addedSet = new HashSet<>(this.getRolePermissionIds(roleId));

        // 过滤不存在的权限id
        // 过滤已添加的权限id
        List<Long> ids = distinctIds.stream()
                .filter(existSet::contains)
                .filter(it -> !addedSet.contains(it))
                .toList();

        List<RolePermissions> rolePermissions = ids.stream()
                .map(it -> RolePermissions.builder()
                        .roleId(roleId)
                        .permissionsId(it)
                        .createAt(ip)
                        .createBy(userId)
                        .createTime(now)
                        .build()
                )
                .toList();
        this.mapper.insertBatch(rolePermissions);
        template.delete(permissionPrefix + roleId);
    }

    @Override
    public void removePermission(RolePermissionBatchDto dto) {
        Long roleId = dto.getRoleId();
        if (roleId == 1) {
            throw new DataException("不允许更改超级管理员的权限");
        }

        List<Pair<Long, Long>> ids = dto.getPermissionIds()
                .stream()
                .map(it -> new Pair<>(roleId, it))
                .toList();
        mapper.removeBatch(ids);
        template.delete(permissionPrefix + roleId);
    }

    @Override
    @EventListener
    public void roleEventHandler(RoleEvent event) {
        RolePermissionsDef rp = RolePermissionsDef.ROLE_PERMISSIONS;
        Role role = event.getObject();
        Long id = role.getId();

        QueryWrapper wrapper = QueryWrapper.create()
                .from(rp)
                .where(rp.ROLE_ID.eq(id));

        this.mapper.deleteByQuery(wrapper);

        String key = permissionPrefix + id;
        template.delete(key);
    }

    @Override
    public boolean haveFullPermissions(Permission[] permissions) {
        Long userId = UserUtils.getUserId();
        List<Long> requiredPermissionIds = Arrays.stream(permissions)
                .map(it -> it.id)
                .distinct()
                .toList();
        Set<Long> userRoleIds = userRoleOperation.userRoleIds(userId);
        Set<Long> permissionIds = new HashSet<>();
        userRoleIds.forEach(it -> permissionIds.addAll(rolePermissionIds(it)));
        return permissionIds.containsAll(requiredPermissionIds);
    }

    @Override
    public Set<Long> rolePermissionIds(Long roleId) {
        String key = permissionPrefix + roleId;
        Set<Object> ids = template.opsForSet().members(key);
        if (ids == null || ids.isEmpty()) {
            Set<Long> rolePermissionIds = new HashSet<>(getRolePermissionIds(roleId));
            if (rolePermissionIds.isEmpty()) {
                template.opsForSet().add(key, -1);
            } else {
                template.opsForSet().add(key, rolePermissionIds.toArray());
            }
            template.expire(key, 5, TimeUnit.MINUTES);
            return rolePermissionIds;
        }
        template.expire(key, 5, TimeUnit.MINUTES);
        return ids.stream().map(it -> Long.valueOf(it.toString())).collect(Collectors.toSet());
    }
}
