package top.integer.blog.model.def;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 路由菜单 表定义层。
 *
 * @author moyok
 * @since 0.1
 */
public class MenuDef extends TableDef {

    /**
     * 路由菜单
     */
    public static final MenuDef MENU = new MenuDef();

    /**
     * 自增id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");


    public final QueryColumn ICON = new QueryColumn(this, "icon");


    public final QueryColumn ORDER = new QueryColumn(this, "order");

    /**
     * 是否展示菜单
     */
    public final QueryColumn IS_SHOW = new QueryColumn(this, "is_show");

    /**
     * 菜单uri
     */
    public final QueryColumn MENU_URI = new QueryColumn(this, "menu_uri");

    /**
     * 菜单名称
     */
    public final QueryColumn MENU_NAME = new QueryColumn(this, "menu_name");

    /**
     * 父菜单id
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 菜单描述
     */
    public final QueryColumn DESCRIPTION = new QueryColumn(this, "description");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, PARENT_ID, MENU_NAME, DESCRIPTION, MENU_URI, IS_SHOW, CREATE_TIME, UPDATE_TIME, ORDER, ICON};

    public MenuDef() {
        super("", "ums_menu");
    }

}
