package top.integer.blog.event;

import top.integer.blog.model.entity.Role;

public class RoleEvent extends Event<Role> {

    public RoleEvent(Role source) {
        super(source);
    }
}
