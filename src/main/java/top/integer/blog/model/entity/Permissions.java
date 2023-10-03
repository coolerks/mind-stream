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
 * 权限 实体类。
 *
 * @author moyok
 * @since 0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "权限")
@Table(value = "ums_permissions")
public class Permissions implements Serializable {

    /**
     * 自增id
     */
    @Id(keyType = KeyType.None)
    @Schema(description = "自增id")
    private Long id;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;

    /**
     * 权限描述
     */
    @Schema(description = "权限描述")
    private String description;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime = LocalDateTime.now();

    public static Permissions of(Permission permission) {
        LocalDateTime now = LocalDateTime.now();
        return Permissions.builder()
                .id(permission.id)
                .name(permission.name)
                .description(permission.description)
                .createTime(now)
                .build();
    }

}
