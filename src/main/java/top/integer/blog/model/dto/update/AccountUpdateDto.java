package top.integer.blog.model.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "新建账户")
public class AccountUpdateDto {
    /**
     * 账户id
     */
    @Schema(description = "账户id")
    @NotNull(message = "账户id不能为空")
    private Long id;
    /**
     * 昵称
     */
    @Schema(description = "昵称")
    @Length(min = 1, max = 16, message = "昵称1-16个字符")
    @NotBlank(message = "昵称不为空")
    private String nickname;

    /**
     * 头像(路径)
     */
    @Schema(description = "头像(路径)")
    @Length(min = 1, max = 255, message = "头像1-30个字符")
    @NotBlank(message = "头像不为空")
    private String avatar;

    /**
     * 性别
     */
    @Schema(description = "性别")
    @NotNull(message = "性别不为空")
    private Boolean gender;

    /**
     * 个性签名
     */
    @Schema(description = "个性签名")
    @NotBlank(message = "个性签名不为空")
    @Length(min = 1, max = 100, message = "个性签名1-100个字符")
    private String sign;

    /**
     * 密码
     */
    @Schema(description = "密码")
    @Length(min = 6, max = 20, message = "密码6-20个字符")
    private String password;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @NotNull(message = "状态不为空")
    @Range(min = 0, max = 1, message = "状态错误")
    private Integer status;
}
