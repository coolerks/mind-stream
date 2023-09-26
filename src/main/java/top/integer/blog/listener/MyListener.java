package top.integer.blog.listener;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.time.Duration;

/**
 * 监听器，应用场景：进行密钥授权
 */
public class MyListener implements SpringApplicationRunListener {
    /**
     * 首次调用 run 方法时调用 {@link SpringApplication}
     * <p>在run方法中</p>
     * 	<p>SpringApplicationRunListeners listeners = getRunListeners(args);</p>
     * 	<p>listeners.starting(bootstrapContext, this.mainApplicationClass);</p>
     * @param bootstrapContext the bootstrap context
     */
    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("===========starting===========");
    }

    /**
     * 环境准备好时调用，位于 prepareEnvironment() 方法中
     * @param bootstrapContext the bootstrap context
     * @param environment the environment
     */
    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        System.out.println("===========environmentPrepared===========");
    }

    /**
     * 在创建和准备好IOC容器后调用，此时容器内没有东西，prepareContext()
     * @param context the application context
     */
    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("===========contextPrepared===========");

    }

    /**
     * 加载完IOC容器后调用，此时还没有刷新容器，prepareContext()
     * @param context the application context
     */
    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("===========contextLoaded===========");

    }

    /**
     * 容器刷新了，并且容器中包含bean了，run()中执行
     * @param context the application context
     * @param timeTaken the time taken to start the application or {@code null} if unknown
     */
    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println("===========started===========");
    }

    /**
     * 在整个run结束前执行，run()中执行
     * @param context the application context.
     * @param timeTaken the time taken for the application to be ready or {@code null} if
     * unknown
     */
    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println("===========ready===========");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("===========failed===========");
    }
}
