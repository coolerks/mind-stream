package top.integer.blog.model.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 账户信息 实体类。
 *
 * @author moyok
 * @since 0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "账户信息")
public class AccountInfoUpdateDto implements Serializable {

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long id;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 头像(路径)
     */
    @Schema(description = "头像(路径)")
    private String avatar;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private Boolean gender;

    /**
     * 个性签名
     */
    @Schema(description = "个性签名")
    private String sign;

}
