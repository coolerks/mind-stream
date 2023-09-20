package top.integer.blog.enums;

/**
 * @author moyok
 */

public enum ResultStatus {
    /**
     * 成功、失败
     */
    SUCCESS(200, "成功"),
    FAIL(400, "失败"),
    NOT_LOGGED_IN(401, "请登录"),
    AUTHENTICATION_FAILED(402, "认证失败"),
    PERMISSION_DENIED(403, "没有权限"),
    DATA_VALIDATION_FAILED(405, "数据校验失败"),
    REFRESH_TOKEN(300, "刷新token");


    public final int code;
    public final String msg;

    ResultStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
