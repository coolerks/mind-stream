package top.integer.blog.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import top.integer.blog.annotation.PermissionCheck;
import top.integer.blog.enums.Permission;
import top.integer.blog.exception.PermissionDeniedException;
import top.integer.blog.operation.RolePermissionOperation;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class PermissionCheckAspect {
    private final RolePermissionOperation rolePermissionOperation;

    @Before("@annotation(permissionCheck)")
    public void doCheck(PermissionCheck permissionCheck) {
        log.info("权限校验注解开始工作");
        Permission[] permissions = permissionCheck.value();
        if (!rolePermissionOperation.haveFullPermissions(permissions)) {
            throw new PermissionDeniedException();
        }
    }
}
