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
 * 路由菜单 实体类。
 *
 * @author moyok
 * @since 0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "路由菜单")
@Table(value = "ums_menu")
public class Menu implements Serializable {

    /**
     * 自增id
     */
    @Id(keyType = KeyType.None)
    @Schema(description = "自增id")
    private Long id;

    /**
     * 父菜单id
     */
    @Schema(description = "父菜单id")
    private Long parentId;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String menuName;

    /**
     * 菜单描述
     */
    @Schema(description = "菜单描述")
    private String description;

    /**
     * 菜单uri
     */
    @Schema(description = "菜单uri")
    private String menuUri;

    /**
     * 是否展示菜单
     */
    @Schema(description = "是否展示菜单 ")
    private Boolean isShow;


    /**
     * 图标
     */
    @Schema(description = "顺序 ")
    private String icon;

    /**
     * 顺序
     */
    @Schema(description = "顺序 ")
    private int order;

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


}
