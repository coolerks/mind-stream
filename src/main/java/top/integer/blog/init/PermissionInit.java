package top.integer.blog.init;

import top.integer.blog.annotation.Version;
import top.integer.blog.mapper.MenuMapper;
import top.integer.blog.mapper.PermissionsMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class PermissionInit {
    private final PermissionsMapper mapper;
    private final int version;


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
