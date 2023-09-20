package top.integer.blog.model.vo.role;

import com.mybatisflex.annotation.RelationOneToOne;
import lombok.Data;
import lombok.ToString;
import top.integer.blog.model.vo.account.AccountVo;

import java.time.LocalDateTime;

@Data
@ToString
public class RoleDetailVo {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer status;
    private AccountVo createBy;
}
