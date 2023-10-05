package top.integer.blog.model.def;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 菜单-角色 表定义层。
 *
 * @author moyok
 * @since 0.1
 */
public class RoleMenusDef extends TableDef {

    /**
     * 菜单-角色
     */
    public static final RoleMenusDef ROLE_MENUS = new RoleMenusDef();

    /**
     * 自增id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 菜单id
     */
    public final QueryColumn MENU_ID = new QueryColumn(this, "menu_id");

    /**
     * 角色id
     */
    public final QueryColumn ROLE_ID = new QueryColumn(this, "role_id");

    /**
     * 创建者
     */
    public final QueryColumn CREATE_BY = new QueryColumn(this, "create_by");

    /**
     * 创建者ip
     */
    public final QueryColumn CREATE_IP = new QueryColumn(this, "create_ip");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, ROLE_ID, MENU_ID, CREATE_TIME, CREATE_BY, CREATE_IP};

    public RoleMenusDef() {
        super("", "ums_role_menus");
    }

}
