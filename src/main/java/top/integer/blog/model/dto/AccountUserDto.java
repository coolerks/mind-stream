package top.integer.blog.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 账户 实体类。
 *
 * @author moyok
 * @since 0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "账户")
public class AccountUserDto implements Serializable {
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 最后一次登录时间
     */
    @Schema(description = "登录次数")
    private Integer loginTimes;

    /**
     * 状态 1:enable, 0:disable, -1:deleted
     */
    @Schema(description = "状态 1:enable, 0:disable, -1:deleted")
    private Integer status;

    /**
     * 创建ip
     */
    @Schema(description = "创建ip")
    private Long createIp;

    /**
     * 最后一次登录ip
     */
    @Schema(description = "最后一次登录ip")
    private Long lastLoginIp;


}
