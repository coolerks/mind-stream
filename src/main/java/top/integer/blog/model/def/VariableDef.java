package top.integer.blog.model.def;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 环境变量 表定义层。
 *
 * @author moyok
 * @since 0.1
 */
public class VariableDef extends TableDef {

    /**
     * 用户角色
     */
    public static final VariableDef VARIABLE = new VariableDef();

    /**
     * 自增id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 值
     */
    public final QueryColumn VALUE = new QueryColumn(this, "value");

    /**
     * 类型
     */
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NAME, VALUE, TYPE};

    public VariableDef() {
        super("", "ums_variable");
    }

}
