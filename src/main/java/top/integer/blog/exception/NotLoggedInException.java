package top.integer.blog.exception;

import top.integer.blog.enums.ResultStatus;
import top.integer.blog.model.vo.R;

/**
 * 未登录异常
 * @author moyok
 */
public class NotLoggedInException extends BizException{
    public NotLoggedInException() {
        super("请登录");
    }

    @Override
    public R<String> getResult() {
        return R.result(ResultStatus.NOT_LOGGED_IN);
    }
}
