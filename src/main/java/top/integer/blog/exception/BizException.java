package top.integer.blog.exception;

import top.integer.blog.model.vo.R;

/**
 * 业务逻辑异常，将异常的message返回给前端
 * @author moyok
 */
public abstract class BizException extends RuntimeException{
    public BizException(String message) {
        super(message);
    }

    /**
     * 处理返回结果
     * @return
     */
    public abstract R<String> getResult();
}
