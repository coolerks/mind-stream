package top.integer.blog.model.vo.account.info;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 用户列表展示的用户信息
 */
@Data
@ToString
public class AccountItemVo {
    private Integer id;
    private String nickname;
    private String email;
    private Integer status;
    private LocalDateTime lastLoginTime;
    private String avatar;
}
