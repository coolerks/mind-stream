package top.integer.blog.exception;

import top.integer.blog.enums.ResultStatus;
import top.integer.blog.model.vo.R;

/**
 * 未登录异常
 * @author moyok
 */
public class PermissionDeniedException extends BizException{
    public PermissionDeniedException() {
        super("没有权限");
    }

    @Override
    public R<String> getResult() {
        return R.result(ResultStatus.PERMISSION_DENIED);
    }
}
