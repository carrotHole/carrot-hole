package io.github.carrothole.carrot.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 验证码不存在异常
 *
 * @author moon
 * @since 0.0.1
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class VerifyCodeNotExistException extends CarrotException {

    public VerifyCodeNotExistException(String msg) {
        super(1005, msg);
    }


}
