package top.integer.blog.model.vo.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import top.integer.blog.model.vo.account.AccountVo;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDetailVo {
    /**
     * 自增id
     */
    @Schema(description = "自增id")
    private Long id;

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
     * 下载链接
     */
    @Schema(description = "下载链接")
    private String downloadLink;

    /**
     * 压缩链接
     */
    @Schema(description = "压缩链接")
    private String compressLink;

    /**
     * 文件大小
     */
    @Schema(description = "大小")
    private Long size;

    /**
     * 存储策略
     */
    @Schema(description = "存储策略")
    private String policy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;



    /**
     * 用户
     */
    @Schema(description = "用户")
    private AccountVo user;
}
