package top.integer.blog.model.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.integer.blog.enums.Permission;

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
     * 创建时间
     */
    @Schema(description = "创建IP")
    private Long createAt;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime = LocalDateTime.now();


    /**
     * 修改者
     */
    @Schema(description = "修改者")
    private Long createBy;

    public static RolePermissions of(Long roleId, Permission permission) {
        return RolePermissions.builder()
                .roleId(roleId)
                .permissionsId(permission.id)
                .createAt(2130706433L)
                .createTime(LocalDateTime.now())
                .createBy(1001L)
                .build();
    }

}
