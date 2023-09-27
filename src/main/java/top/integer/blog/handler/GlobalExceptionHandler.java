package top.integer.blog.handler;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.integer.blog.enums.ResultStatus;
import top.integer.blog.exception.BizException;
import top.integer.blog.model.vo.R;
import top.integer.blog.utils.EnvUtils;

import java.util.List;

/**
 * 全局异常处理
 *
 * @author moyok
 */
@Component
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Environment environment;

    @ExceptionHandler(BizException.class)
    public R<String> handleBizExceptions(BizException e) {
        if (EnvUtils.isTrack) {
            e.printStackTrace();
        }
        return e.getResult();
    }


    @ExceptionHandler(Throwable.class)
    public R<String> handleAllExceptions(Throwable e) {
        if (EnvUtils.isTrack) {
            e.printStackTrace();
        }
        return R.fail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<List<String>> handleArgumentNotValidException(MethodArgumentNotValidException e) {
        if (EnvUtils.isTrack) {
            e.printStackTrace();
        }
        return R.result(ResultStatus.DATA_VALIDATION_FAILED, e.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList());
    }

    @PostConstruct
    public void init() {
        String[] activeProfiles = environment.getActiveProfiles();
        for (String activeProfile : activeProfiles) {
            if (!"prod".equals(activeProfile)) {
                EnvUtils.isTrack = true;
                return;
            }
        }
    }
}
