package top.integer.blog.operation;

public interface AccountEnsureOperation {
    /**
     * 确保存在一个超级管理员
     * @param userId
     */
    void ensureExistSuperAdmin(Long userId);

    /**
     * 获取已启用的超级管理员账户数目
     * @return
     */
    long getSuperAdminCount();
}
