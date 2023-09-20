package top.integer.blog.model.dto;

import com.mybatisflex.annotation.Table;
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
@Table(value = "ums_role_menus")
public class RoleMenusDto implements Serializable {

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

    /**
     * 状态 1:enable, 0:disable, -1:deleted
     */
    @Schema(description = "状态 1:enable, 0:disable, -1:deleted")
    private Integer status;

}
