package top.integer.blog.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class PermissionsDto implements Serializable {
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

}
