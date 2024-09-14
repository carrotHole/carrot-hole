package io.github.carrothole.carrot.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 登录返回对象
 *
 * @author moon
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "登录返回对象")
public class LoginResultVO implements Serializable {

    @Schema(description = "identifyKey key")
    private String identifyKey;

    @Schema(description = "用户信息")
    private List<LoginResultUserVO> users;

}
