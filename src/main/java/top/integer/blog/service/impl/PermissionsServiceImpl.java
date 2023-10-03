package top.integer.blog.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.ToString;
import top.integer.blog.model.def.PermissionsDef;
import top.integer.blog.model.entity.Permissions;
import top.integer.blog.mapper.PermissionsMapper;
import top.integer.blog.operation.PermissionsOperation;
import top.integer.blog.service.PermissionsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限 服务层实现。
 *
 * @author moyok
 * @since 0.1
 */
@Service
@ToString(callSuper = true)
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements
        PermissionsService, PermissionsOperation {

    @Override
    public Set<Long> existPermissions(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptySet();
        }
        PermissionsDef p = PermissionsDef.PERMISSIONS;
        QueryWrapper wrapper = QueryWrapper.create()
                .select(p.ID)
                .from(p)
                .where(p.ID.in(ids));
        return this.mapper.selectListByQuery(wrapper)
                .stream()
                .map(Permissions::getId)
                .collect(Collectors.toSet());
    }
}
