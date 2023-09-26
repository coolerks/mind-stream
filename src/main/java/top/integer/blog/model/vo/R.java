package top.integer.blog.model.vo;

import lombok.Getter;
import lombok.ToString;
import top.integer.blog.enums.ResultStatus;

/**
 * 通用的结果返回类
 *
 * @param <T> 类型
 */
@Getter
@ToString
public class R<T> {
    private final Integer code;
    private final String msg;
    private T data;

    public static <T> R<T> ok(T data) {
        return new R<>(ResultStatus.SUCCESS, data);
    }

    public static R<String> ok() {
        return new R<>(ResultStatus.SUCCESS, ResultStatus.SUCCESS.msg);
    }

    public static <T> R<T> ok(T data, String msg) {
        return new R<>(ResultStatus.SUCCESS.code, data, msg);
    }

    public static <T> R<T> fail(T data, String msg) {
        return new R<>(ResultStatus.FAIL.code, data, msg);
    }

    public static <T> R<T> fail(T data) {
        return new R<>(ResultStatus.FAIL, data);
    }

    public static R<String> fail() {
        return new R<>(ResultStatus.FAIL, ResultStatus.FAIL.msg);
    }


    public static <T> R<T> result(ResultStatus status, T data) {
        return new R<>(status, data);
    }

    public static R<String> result(ResultStatus status) {
        return new R<>(status, status.msg);
    }


    private R(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    private R(ResultStatus status) {
        this.code = status.code;
        this.msg = status.msg;
    }

    private R(ResultStatus status, T data) {
        this.code = status.code;
        this.msg = data instanceof String d ? d : status.msg;
        this.data = data;
    }
}
