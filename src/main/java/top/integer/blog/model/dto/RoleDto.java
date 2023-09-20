package top.integer.blog.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * 角色 实体类。
 *
 * @author moyok
 * @since 0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色")
public class RoleDto implements Serializable {
    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    @Length(min = 1, max = 20, message = "角色名称需大于1个字符小于20个字符")
    @NotNull(message = "状态信息不能为空")
    private String name;

    /**
     * 角色描述
     */
    @Schema(description = "角色描述")
    @Length(max = 255, message = "描述信息需小于255个字符")
    private String description;

    /**
     * 状态 1:enable, 0:disable, -1:deleted
     */
    @Range(min = 0, max = 1, message = "状态信息错误")
    @Schema(description = "状态 1:enable, 0:disable")
    @NotNull(message = "状态信息不能为空")
    private Integer status;


}
