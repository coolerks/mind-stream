package top.integer.blog.model.def;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 文件夹 表定义层。
 *
 * @author moyok
 * @since 0.1
 */
public class FolderDef extends TableDef {

    /**
     * 文件夹
     */
    public static final FolderDef FOLDER = new FolderDef();

    /**
     * 自增id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 文件夹名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 用户id
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 创建者ip
     */
    public final QueryColumn CREATE_IP = new QueryColumn(this, "create_ip");

    /**
     * 完整路径
     */
    public final QueryColumn FULL_PATH = new QueryColumn(this, "full_path");

    /**
     * 父文件夹id
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 创建时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 文件夹描述
     */
    public final QueryColumn DESCRIPTION = new QueryColumn(this, "description");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, PARENT_ID, USER_ID, NAME, DESCRIPTION, FULL_PATH, CREATE_TIME, UPDATE_TIME, CREATE_IP};

    public FolderDef() {
        super("", "fms_folder");
    }

}
