package top.integer.blog.operation;

import top.integer.blog.enums.Permission;

import java.util.List;
import java.util.Set;

public interface AccountOperation {
    /**
     * 是否存在某一个用户
     * @param userId 用户id
     * @return 存在/不存在
     */
    default boolean existAccount(Long userId) {
        return existAccount(List.of(userId)).contains(userId);
    }

    /**
     * 是否存在用户
     * @param ids id集合
     * @return 存在/不存在
     */
    Set<Long> existAccount(List<Long> ids);

}
