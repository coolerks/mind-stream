package top.integer.blog.model.vo.account.info;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import top.integer.blog.model.vo.account.AccountVo;

import java.time.LocalDateTime;

@Data
@ToString
public class AccountDetailVo {
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
     * 最后一次登录ip
     */
    @Schema(description = "最后一次登录ip")
    private String lastLoginIp;

    /**
     * 创建ip
     */
    @Schema(description = "最后一次登录ip")
    private String createIp;


    /**
     * 登录次数
     */
    @Schema(description = "上次登录时间")
    private LocalDateTime lastLoginTime;


}
