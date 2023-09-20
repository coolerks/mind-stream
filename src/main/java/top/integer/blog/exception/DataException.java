package top.integer.blog.exception;

import top.integer.blog.model.vo.R;

public class DataException extends BizException{
    public DataException(String message) {
        super(message);
    }

    @Override
    public R<String> getResult() {
        return R.fail(getMessage());
    }
}
