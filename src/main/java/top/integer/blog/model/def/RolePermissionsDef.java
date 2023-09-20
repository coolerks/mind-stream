package top.integer.blog.model.def;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 角色-权限 表定义层。
 *
 * @author moyok
 * @since 0.1
 */
public class RolePermissionsDef extends TableDef {

    /**
     * 角色-权限
     */
    public static final RolePermissionsDef ROLE_PERMISSIONS = new RolePermissionsDef();

    /**
     * 自增id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 角色id
     */
    public final QueryColumn ROLE_ID = new QueryColumn(this, "role_id");

    /**
     * 状态 1:enable, 0:disable, -1:deleted
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_AT = new QueryColumn(this, "create_at");

    /**
     * 修改者
     */
    public final QueryColumn UPDATE_BY = new QueryColumn(this, "update_by");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 权限id
     */
    public final QueryColumn PERMISSIONS_ID = new QueryColumn(this, "permissions_id");

    /**
     * 权限类型
     */
    public final QueryColumn PERMISSION_TYPE = new QueryColumn(this, "permission_type");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, ROLE_ID, PERMISSIONS_ID, PERMISSION_TYPE, CREATE_AT, CREATE_TIME, UPDATE_TIME, UPDATE_BY, STATUS};

    public RolePermissionsDef() {
        super("", "ums_role_permissions");
    }

}
