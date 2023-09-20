package top.integer.blog.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "路由菜单")
public class MenusVo {
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
    private String name;

    /**
     * 菜单uri
     */
    @Schema(description = "菜单uri")
    private String path;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "子路由")
    private List<MenusVo> routes;

    public MenusVo(Long id, Long parentId, String name, String path, String icon) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.path = path;
        this.icon = icon;
    }

    public static MenusVo fromMenuVo(MenuVo vo) {
        return new MenusVo(vo.getId(), vo.getParentId(), vo.getMenuName(), vo.getMenuUri(), vo.getIcon());
    }
}
