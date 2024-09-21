package io.github.carrothole.carrot.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Description: 修改密码对象 <br>
 * Date: 2024/9/6 16:43 <br>
 *
 * @author moon
 * @since 0.0.1
 */
@Data
@Schema(description = "修改密码对象")
public class ChangePasswordVO {

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String newPassword;


    private String oldPassword;

    /**
     * 密码加密 todo 实现
     */
    @Schema(description = "密码加密")
    private String oldPasswordEnc;

}
