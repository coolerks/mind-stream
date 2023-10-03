package top.integer.blog.operation;

import java.util.List;
import java.util.Set;

public interface MenuOperation {
    /**
     * 是否存在某一个权限
     * @param roleId 角色id
     * @return 存在/不存在
     */
    default boolean existMenu(Long roleId) {
        return existMenus(List.of(roleId)).contains(roleId);
    }

    /**
     * 是否存在用户
     * @param ids id集合
     * @return 存在/不存在
     */
    Set<Long> existMenus(List<Long> ids);
}
