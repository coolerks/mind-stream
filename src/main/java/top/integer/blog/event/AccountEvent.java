package top.integer.blog.event;

import top.integer.blog.model.entity.AccountInfo;

public class AccountEvent extends Event<AccountInfo> {

    public AccountEvent(AccountInfo source) {
        super(source);
    }
}
