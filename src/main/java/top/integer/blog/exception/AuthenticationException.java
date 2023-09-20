package top.integer.blog.exception;

import top.integer.blog.enums.ResultStatus;
import top.integer.blog.model.vo.R;

/**
 * 认证异常
 * @author singx
 */
public class AuthenticationException extends BizException{
    public AuthenticationException() {
        super("认证失败");
    }

    @Override
    public R<String> getResult() {
        return R.result(ResultStatus.AUTHENTICATION_FAILED);
    }
}
