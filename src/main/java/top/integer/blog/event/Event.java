package top.integer.blog.event;

import org.springframework.context.ApplicationEvent;

public class Event<T> extends ApplicationEvent {
    public Event(T source) {
        super(source);
    }

    @SuppressWarnings("unchecked")
    public T getObject() {
        Object obj = getSource();
        return (T) obj;
    }
}
