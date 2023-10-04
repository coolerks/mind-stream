package top.integer.blog.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.integer.blog.exception.DataException;
import top.integer.blog.mapper.UserRolesMapper;
import top.integer.blog.model.def.AccountUserDef;
import top.integer.blog.model.def.UserRolesDef;
import top.integer.blog.operation.AccountEnsureOperation;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountEnsureService implements AccountEnsureOperation {
    private final UserRolesMapper mapper;
    @Override
    public long getSuperAdminCount() {
        AccountUserDef a = AccountUserDef.ACCOUNT_USER;
        UserRolesDef ur = UserRolesDef.USER_ROLES;
        QueryWrapper wrapper = QueryWrapper.create()
                .select(a.ID)
                .from(ur)
                .join(a).on(ur.USER_ID.eq(a.ID))
                .where(ur.ROLE_ID.eq(1))
                .and(a.STATUS.eq(1));
        return this.mapper.selectCountByQuery(wrapper);
    }

    @Override
    public void ensureExistSuperAdmin(Long userId) {
        if (this.getSuperAdminCount() <= 1) {
            UserRolesDef ur = UserRolesDef.USER_ROLES;
            QueryWrapper wrapper = QueryWrapper.create()
                    .select(ur.ROLE_ID)
                    .where(ur.ROLE_ID.eq(1))
                    .and(ur.USER_ID.eq(userId));
            if (this.mapper.selectCountByQuery(wrapper) > 0) {
                throw new DataException("当前用户为唯一的超级管理员，请确保至少存在一个可用的超级管理员账户");
            }
        }
    }
}
