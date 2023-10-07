package top.integer.blog.model.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件夹 实体类。
 *
 * @author moyok
 * @since 0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "文件夹")
@Table(value = "fms_folder")
public class Folder implements Serializable {

    /**
     * 自增id
     */
    @Id(keyType = KeyType.Auto)
    @Schema(description = "自增id")
    private Long id;

    /**
     * 父文件夹id
     */
    @Schema(description = "父文件夹id")
    private Long parentId;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long userId;

    /**
     * 文件夹名称
     */
    @Schema(description = "文件夹名称")
    private String name;

    /**
     * 文件夹描述
     */
    @Schema(description = "文件夹描述")
    private String description;

    /**
     * 完整路径
     */
    @Schema(description = "完整路径")
    private String fullPath;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime updateTime;

    /**
     * 创建者ip
     */
    @Schema(description = "创建者ip")
    private Long createIp;

}
