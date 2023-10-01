package top.integer.blog.model.vo.account.info;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 角色详情页中角色所属用户的信息
 */
@Data
@ToString
public class AccountRoleItemVo {
    private Integer id;
    private String nickname;
    private String email;
    private Integer status;
    private LocalDateTime createTime;
    private String avatar;
}
