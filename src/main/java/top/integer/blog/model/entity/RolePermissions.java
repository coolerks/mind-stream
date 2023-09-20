package top.integer.blog.model.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(value = "ums_role_permissions")
public class RolePermissions implements Serializable {

    /**
     * 自增id
     */
    @Id(keyType = KeyType.Auto)
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
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Integer createAt;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime = LocalDateTime.now();

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime = LocalDateTime.now();

    /**
     * 修改者
     */
    @Schema(description = "修改者")
    private Integer updateBy;

    /**
     * 状态 1:enable, 0:disable, -1:deleted
     */
    @Schema(description = "状态 1:enable, 0:disable, -1:deleted")
    private Integer status;

}
