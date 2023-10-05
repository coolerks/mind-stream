package top.integer.blog.init;

import top.integer.blog.annotation.Version;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public interface Init {
    private void invoke(Method m) {
        try {
            m.setAccessible(true);
            m.invoke(Init.this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    default void doInit(int version) {
        Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Version.class))
                .filter(m -> m.getAnnotation(Version.class).value() > version)
                .sorted(Comparator.comparingInt(m -> m.getAnnotation(Version.class).value()))
                .distinct()
                .forEach(this::invoke);
    }
}
