package top.integer.blog.model.def;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 文件 表定义层。
 *
 * @author moyok
 * @since 0.1
 */
public class FilesDef extends TableDef {

    /**
     * 文件
     */
    public static final FilesDef FILES = new FilesDef();

    /**
     * 自增id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 文件名
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");


    /**
     * 存储策略
     */
    public final QueryColumn POLICY = new QueryColumn(this, "policy");

    /**
     * 文件状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 用户id
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 创建者ip
     */
    public final QueryColumn CREATE_IP = new QueryColumn(this, "create_ip");

    /**
     * 文件夹id
     */
    public final QueryColumn FOLDER_ID = new QueryColumn(this, "folder_id");

    /**
     * 存储的完整路径
     */
    public final QueryColumn FULL_PATH = new QueryColumn(this, "full_path");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 压缩的文件路径
     */
    public final QueryColumn COMPRESS_PATH = new QueryColumn(this, "compress_path");

    /**
     * 文件大小
     */
    public final QueryColumn SIZE = new QueryColumn(this, "size");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, FOLDER_ID, USER_ID, NAME, FULL_PATH, COMPRESS_PATH, STATUS, POLICY, CREATE_TIME, UPDATE_TIME, CREATE_IP, SIZE};

    public FilesDef() {
        super("", "fms_files");
    }

}
