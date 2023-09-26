package top.integer.blog.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import top.integer.blog.event.MyEvent;

@Service
public class MyEventListener2 {
    @EventListener
    public void onMyEvent(MyEvent myEvent) {
        System.out.println("方法上添加注解接收事件..." + Thread.currentThread().getName());
    }
}
