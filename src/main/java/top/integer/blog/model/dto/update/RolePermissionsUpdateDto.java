package top.integer.blog.model.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色-权限 实体类。
 *
 * @author moyok
 * @since 0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色-权限")
public class RolePermissionsUpdateDto implements Serializable {

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
     * 权限id
     */
    @Schema(description = "权限id")
    private Long permissionsId;

    /**
     * 权限类型
     */
    @Schema(description = "权限类型")
    private Integer permissionType;


    /**
     * 状态 1:enable, 0:disable, -1:deleted
     */
    @Schema(description = "状态 1:enable, 0:disable, -1:deleted")
    private Integer status;

}
