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
@Table(value = "ums_role")
public class Role implements Serializable {

    /**
     * 自增id
     */
    @Id(keyType = KeyType.Auto)
    @Schema(description = "自增id")
    private Long id;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;

    /**
     * 角色描述
     */
    @Schema(description = "角色描述")
    private String description;

    /**
     * 创建者
     */
    @Schema(description = "创建者")
    private Long createBy;

    /**
     * 修改者
     */
    @Schema(description = "修改者")
    private Long updateBy;

    /**
     * 状态 1:enable, 0:disable, -1:deleted
     */
    @Schema(description = "状态 1:enable, 0:disable, -1:deleted")
    private Integer status;

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
