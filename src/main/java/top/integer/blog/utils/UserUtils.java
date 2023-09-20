package top.integer.blog.utils;

/**
 * 用户工具类
 * @author moyok
 */
public class UserUtils {
    public static ThreadLocal<Long> userId = new ThreadLocal<>();
    public static ThreadLocal<String> token = new ThreadLocal<>();

    public static Long getUserId() {
        if (token.get() == null) {
        }

        return 1001L;
    }

}
