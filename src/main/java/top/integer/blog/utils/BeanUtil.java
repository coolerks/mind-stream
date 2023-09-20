package top.integer.blog.utils;

import org.springframework.beans.BeanUtils;

public class BeanUtil {
    public static <T> T copy(Object source, T target) {
        BeanUtils.copyProperties(source, target);
        return (T) target;
    }
    private BeanUtil() {

    }
}
