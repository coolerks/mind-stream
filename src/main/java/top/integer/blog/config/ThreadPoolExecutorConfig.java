package top.integer.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 */
@Configuration
public class ThreadPoolExecutorConfig {
    @Bean
    @Primary
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(200, 400, 1, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100), Thread::new, new ThreadPoolExecutor.AbortPolicy());
    }
}
