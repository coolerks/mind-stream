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
public class RoleMenus implements Serializable {

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
     * 菜单id
     */
    @Schema(description = "菜单id")
    private Long menuId;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime = LocalDateTime.now();


    /**
     * 创建者
     */
    @Schema(description = "创建者")
    private Long createBy;

    /**
     * 创建者
     */
    @Schema(description = "创建者IP")
    private Long createIp;
}
