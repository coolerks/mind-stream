package top.integer.blog.model.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 菜单-角色 实体类。
 *
 * @author moyok
 * @since 0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "菜单-角色")
public class RoleMenusUpdateDto implements Serializable {

    /**
     * 自增id
     */
    @Schema(description = "自增id")
    private Long id;

    /**
     * 角色id
     */
    @Schema(description = "角色id")
    private Long roleId;

    /**
     * 菜单id
     */
    @Schema(description = "菜单id")
    private Long menuId;

}
