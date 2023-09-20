package top.integer.blog.model.def;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 账户 表定义层。
 *
 * @author moyok
 * @since 0.1
 */
public class AccountUserDef extends TableDef {

    /**
     * 账户
     */
    public static final AccountUserDef ACCOUNT_USER = new AccountUserDef();

    /**
     * 账号id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 邮箱
     */
    public final QueryColumn EMAIL = new QueryColumn(this, "email");

    /**
     * 状态 1:enable, 0:disable, -1:deleted
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 创建者
     */
    public final QueryColumn CREATE_BY = new QueryColumn(this, "create_by");

    /**
     * 创建ip
     */
    public final QueryColumn CREATE_IP = new QueryColumn(this, "create_ip");

    /**
     * 密码
     */
    public final QueryColumn PASSWORD = new QueryColumn(this, "password");

    /**
     * 用户名
     */
    public final QueryColumn USERNAME = new QueryColumn(this, "username");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 最后一次登录时间
     */
    public final QueryColumn LOGIN_TIMES = new QueryColumn(this, "login_times");

    /**
     * 最后一次登录ip
     */
    public final QueryColumn LAST_LOGIN_IP = new QueryColumn(this, "last_login_ip");

    /**
     * 登录次数
     */
    public final QueryColumn LAST_LOGIN_TIME = new QueryColumn(this, "last_login_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, EMAIL, USERNAME, PASSWORD, LOGIN_TIMES, STATUS, CREATE_IP, LAST_LOGIN_IP, CREATE_BY, CREATE_TIME, LAST_LOGIN_TIME};

    public AccountUserDef() {
        super("", "ums_account_user");
    }

}
