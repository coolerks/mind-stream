package top.integer.blog.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import top.integer.blog.event.MyEvent;

@Component
public class MyEventListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        Object source = event.getSource();
        System.out.println("感受到了自定义事件了..." + Thread.currentThread().getName() + "...值为：" + source);
    }
}
