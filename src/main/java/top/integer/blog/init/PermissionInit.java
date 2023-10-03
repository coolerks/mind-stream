package top.integer.blog.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.integer.blog.annotation.Version;
import top.integer.blog.constant.PermissionConstant;
import top.integer.blog.mapper.PermissionsMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Slf4j
public class PermissionInit {
    private final PermissionsMapper mapper;
    private final int version;

    @Version(0)
    public void version1() {
        mapper.insertBatch(PermissionConstant.userManagerPermissions());
        mapper.insertBatch(PermissionConstant.roleManagerPermissions());
        log.info("初始化用户、角色权限成功");
    }


    private void invoke(Method m) {
        try {
            m.setAccessible(true);
            m.invoke(PermissionInit.this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public PermissionInit(PermissionsMapper mapper) {
        this.version = 0;
        this.mapper = mapper;
        Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Version.class))
                .filter(m -> m.getAnnotation(Version.class).value() > version)
                .distinct()
                .forEach(this::invoke);
    }
}
