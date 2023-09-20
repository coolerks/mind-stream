package top.integer.blog.model.def;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 账户信息 表定义层。
 *
 * @author moyok
 * @since 0.1
 */
public class AccountInfoDef extends TableDef {

    /**
     * 账户信息
     */
    public static final AccountInfoDef ACCOUNT_INFO = new AccountInfoDef();

    /**
     * 用户id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 个性签名
     */
    public final QueryColumn SIGN = new QueryColumn(this, "sign");

    /**
     * 头像(路径)
     */
    public final QueryColumn AVATAR = new QueryColumn(this, "avatar");

    /**
     * 性别
     */
    public final QueryColumn GENDER = new QueryColumn(this, "gender");

    /**
     * 昵称
     */
    public final QueryColumn NICKNAME = new QueryColumn(this, "nickname");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NICKNAME, AVATAR, GENDER, SIGN, CREATE_TIME, UPDATE_TIME};

    public AccountInfoDef() {
        super("", "ums_account_info");
    }

}
