package com.carrothole.carrot.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: AuthorizationException
 * Description: 登录认证相关异常类
 * date: 2024-8-31 22:23
 *
 * @author moon
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
public class AuthorizationException extends CarrotException {

    public AuthorizationException(String message) {
        super(401, message);
    }

}
