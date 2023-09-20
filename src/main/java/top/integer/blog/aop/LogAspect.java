package top.integer.blog.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.integer.blog.utils.EnvUtils;
import top.integer.blog.utils.IpUtils;

import java.util.Arrays;

/**
 * 日志记录
 *
 * @author moyok
 */
@Aspect
@Slf4j
@Component
public class LogAspect {
    @Around("within(top.integer.blog..controller..*)")
    public Object doLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if (EnvUtils.isTrack) {
            log.info("请求路径：{}，IP：{}，接收参数：{}", request.getServletPath(),
                    IpUtils.getIpAddress(request), Arrays.toString(proceedingJoinPoint.getArgs()));
        }
        Object proceed = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        if (EnvUtils.isTrack) {
            log.info("处理结果：{}", proceed);
        }
        return proceed;
    }
}
