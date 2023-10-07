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
 * 文件 实体类。
 *
 * @author moyok
 * @since 0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "文件")
@Table(value = "fms_files")
public class Files implements Serializable {

    /**
     * 自增id
     */
    @Id(keyType = KeyType.Auto)
    @Schema(description = "自增id")
    private Long id;

    /**
     * 文件夹id
     */
    @Schema(description = "文件夹id")
    private Long folderId;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long userId;

    /**
     * 文件名
     */
    @Schema(description = "文件名")
    private String name;

    /**
     * 存储的完整路径
     */
    @Schema(description = "存储的完整路径")
    private String fullPath;

    /**
     * 压缩的文件路径
     */
    @Schema(description = "压缩的文件路径")
    private String compressPath;

    /**
     * 文件大小
     */
    @Schema(description = "大小")
    private Long size;

    /**
     * 文件状态
     */
    @Schema(description = "文件状态")
    private int status;

    /**
     * 存储策略
     */
    @Schema(description = "存储策略")
    private int policy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 创建者ip
     */
    @Schema(description = "创建者ip")
    private Long createIp;

}
