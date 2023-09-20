package top.integer.blog.model.vo.account;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AccountVo {
    private Long id;
    private String email;
    private String username;
    private String nickname;
    private String avatar;
}
