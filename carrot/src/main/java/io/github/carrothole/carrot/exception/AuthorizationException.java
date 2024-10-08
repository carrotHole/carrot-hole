package io.github.carrothole.carrot.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ClassName: AuthorizationException
 * Description: 登录认证相关异常类
 * date: 2024-8-31 22:23
 *
 * @author moon
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class AuthorizationException extends CarrotException {

    public AuthorizationException(String message) {
        super(401, message);
    }

}
