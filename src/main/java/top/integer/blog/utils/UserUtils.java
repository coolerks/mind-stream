package top.integer.blog.utils;

import top.integer.blog.exception.NotLoggedInException;

import java.util.Optional;

/**
 * 用户工具类
 *
 * @author moyok
 */
public class UserUtils {
    private static final ThreadLocal<Long> userId = new ThreadLocal<>();
    private static final ThreadLocal<String> token = new ThreadLocal<>();

    public static Long getUserId() {
        trySetUserId();
        return Optional.ofNullable(userId.get())
                .orElseThrow(NotLoggedInException::new);
    }

    public static void setToken(String token) {
        Optional.ofNullable(token)
                .ifPresent(UserUtils.token::set);
    }

    public static void remove() {
        userId.remove();
        token.remove();
    }

    private static void trySetUserId() {
        Optional.ofNullable(token.get())
                .ifPresent(token -> {
                    if (userId.get() == null) {
                        try {
                            long id = JwtUtils.getUserId(token);
                            userId.set(id);
                        } catch (Exception ignore) {
                        }
                    }
                });
    }

}
