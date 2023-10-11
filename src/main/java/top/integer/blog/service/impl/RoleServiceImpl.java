package top.integer.blog.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import top.integer.blog.event.RoleEvent;
import top.integer.blog.exception.BizException;
import top.integer.blog.exception.DataException;
import top.integer.blog.model.def.RoleDef;
import top.integer.blog.model.dto.PageQueryDto;
import top.integer.blog.model.dto.account.CommonPageQueryDto;
import top.integer.blog.model.entity.Role;
import top.integer.blog.mapper.RoleMapper;
import top.integer.blog.model.vo.PageVo;
import top.integer.blog.model.vo.role.AssignRoleItemVo;
import top.integer.blog.model.vo.role.RoleDetailVo;
import top.integer.blog.model.vo.role.RoleItemVo;
import top.integer.blog.service.Publisher;
import top.integer.blog.service.RoleService;
import org.springframework.stereotype.Service;
import top.integer.blog.utils.UserUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色 服务层实现。
 *
 * @author moyok
 * @since 0.1
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public void saveRole(Role role) {
        Long userId = UserUtils.getUserId();
        role.setCreateBy(userId);

        verifyRoleName(role);

        this.save(role);
    }

    private void verifyRoleName(Role role) {
        RoleDef r = RoleDef.ROLE;
        QueryWrapper queryWrapper = QueryWrapper.create().and(r.NAME.eq(role.getName()));

        if (this.exists(queryWrapper)) {
            throw new DataException("角色名称重复");
        }
    }

    @Override
    public PageVo<RoleItemVo> pageRole(CommonPageQueryDto queryDto) {
        Page<RoleItemVo> page = PageQueryDto.toPage(queryDto, RoleItemVo.class);
        String keyword = queryDto.getKeyword();
        Integer status = queryDto.getStatus();
        RoleDef r = RoleDef.ROLE;
        QueryWrapper wrapper = QueryWrapper.create()
                .select(r.ID, r.NAME, r.STATUS, r.STATUS, r.CREATE_TIME)
                .where(r.NAME.eq(keyword, StringUtils.isNotBlank(keyword))
                        .and(r.STATUS.eq(status, status != null))
                );
        this.pageAs(page, wrapper, RoleItemVo.class);
        return PageVo.of(page);
    }

    @Override
    public RoleDetailVo getRoleDetailById(Long id) {
        return mapper.getRoleDetailById(id);
    }

    @Override
    public void update(Role role) {
        Long userId = UserUtils.getUserId();
        role.setUpdateBy(userId);

        Role r = this.getById(role.getId());

        if (!r.getName().equals(role.getName())) {
            verifyRoleName(role);
        }
        this.updateById(role);
    }

    @Override
    public List<AssignRoleItemVo> listAssignRoles() {
        RoleDef r = RoleDef.ROLE;
        QueryWrapper wrapper = QueryWrapper.create()
                .select(r.ID, r.NAME)
                .from(r);
        return this.listAs(wrapper, AssignRoleItemVo.class);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteRole(Long id) {
        if (id == 1) {
            throw new DataException("超级管理员不允许被删除");
        }
        Role role = this.mapper.selectOneById(id);
        if (role != null) {
            Publisher.publisher.publishEvent(new RoleEvent(role));
        }
        this.mapper.deleteById(id);
    }

    @Override
    public Set<Long> existRole(List<Long> ids) {
        if (ids.isEmpty()) {
            return Collections.emptySet();
        }
        RoleDef r = RoleDef.ROLE;
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(r.ID)
                .from(r)
                .where(r.ID.in(ids));

        return mapper.selectListByQuery(queryWrapper)
                .stream()
                .map(Role::getId)
                .collect(Collectors.toSet());
    }
}
