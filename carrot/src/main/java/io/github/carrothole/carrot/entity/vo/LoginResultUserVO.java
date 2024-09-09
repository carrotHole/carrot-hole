package io.github.carrothole.carrot.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author moon
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "登录返回用户对象")
public class LoginResultUserVO implements Serializable {


    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "是否为真实用户")
    private Integer realUser;

    @Schema(description = "部门主键")
    private String deptId;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "登录时间")
    private Integer lastLogin;

}
