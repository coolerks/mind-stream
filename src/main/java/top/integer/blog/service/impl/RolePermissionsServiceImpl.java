package top.integer.blog.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.integer.blog.exception.DataException;
import top.integer.blog.mapper.RolePermissionsMapper;
import top.integer.blog.model.Pair;
import top.integer.blog.model.def.RolePermissionsDef;
import top.integer.blog.model.dto.RolePermissionBatchDto;
import top.integer.blog.model.entity.Permissions;
import top.integer.blog.model.entity.RolePermissions;
import top.integer.blog.operation.PermissionsOperation;
import top.integer.blog.operation.RoleOperation;
import top.integer.blog.service.RolePermissionsService;
import top.integer.blog.utils.IpUtils;
import top.integer.blog.utils.UserUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        if (!roleOperation.existRole(dto.getRoleId())) {
            throw new DataException("角色不存在");
        }

        // 过滤重复的id
        List<Long> distinctIds = dto.getPermissionIds()
                .stream()
                .distinct()
                .toList();
        Set<Long> existSet = permissionsOperation.existPermissions(distinctIds);
        Set<Long> addedSet = new HashSet<>(this.getRolePermissionIds(dto.getRoleId()));

        // 过滤不存在的权限id
        // 过滤已添加的权限id
        List<Long> ids = distinctIds.stream()
                .filter(existSet::contains)
                .filter(it -> !addedSet.contains(it))
                .toList();

        List<RolePermissions> rolePermissions = ids.stream()
                .map(it -> RolePermissions.builder()
                        .roleId(dto.getRoleId())
                        .permissionsId(it)
                        .createAt(ip)
                        .createBy(userId)
                        .createTime(now)
                        .build()
                )
                .toList();
        this.mapper.insertBatch(rolePermissions);
    }

    @Override
    public void removePermission(RolePermissionBatchDto dto) {
        List<Pair<Long, Long>> ids = dto.getPermissionIds()
                .stream()
                .map(it -> new Pair<>(dto.getRoleId(), it))
                .toList();
        mapper.removeBatch(ids);
    }
}
