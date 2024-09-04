package io.github.carrothole.carrot.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

/**
 * Description:  <br>
 * Date: 2024/8/29 15:06 <br>
 *
 * @author moon
 * @since 0.0.1
 */
@Data
@Schema(description = "登录对象")
public class LoginVO implements Serializable {

    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "密码加密")
    private String passwordEnc;

    @Schema(description = "验证码")
    private String captcha;

    @Schema(description = "验证码key")
    private String captchaKey;

    @Schema(description = "是否记住我")
    private Boolean rememberMe;

    @Schema(description = "登录类型")
    private Integer loginType;

    @Schema(description = "租户标志")
    private Integer tenantMark;

}
