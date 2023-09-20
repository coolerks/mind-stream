package top.integer.blog.model.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class MenuUpdateDto implements Serializable {

    /**
     * 自增id
     */
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

}
