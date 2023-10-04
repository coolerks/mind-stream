package top.integer.blog.operation;

import java.util.List;
import java.util.Set;

public interface UserRoleOperation {
    /**
     * 是否存在某一个角色
     * @param roleId 角色id
     * @return 存在/不存在
     */
    default boolean existRole(Long roleId) {
        return existRole(List.of(roleId)).contains(roleId);
    }

    /**
     * 是否存在角色
     * @param ids id集合
     * @return 存在/不存在
     */
    Set<Long> existRole(List<Long> ids);

    /**
     * 用户的角色id
     * @param userId
     * @return
     */
    Set<Long> userRoleIds(long userId);


}
