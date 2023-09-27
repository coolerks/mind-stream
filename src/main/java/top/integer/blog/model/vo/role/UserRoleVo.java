package top.integer.blog.model.vo.role;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class UserRoleVo {
    private Long roleId;
    private String name;
    private LocalDateTime createTime;
}
