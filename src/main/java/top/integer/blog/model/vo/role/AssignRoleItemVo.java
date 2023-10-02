package top.integer.blog.model.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AssignRoleItemVo {
    /**
     * 自增id
     */
    @Schema(description = "自增id")
    private Long id;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;
}
