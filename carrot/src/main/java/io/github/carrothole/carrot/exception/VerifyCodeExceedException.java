package io.github.carrothole.carrot.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 验证码过期异常
 *
 * @author moon
 * @since 0.0.1
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class VerifyCodeExceedException extends CarrotException {

    public VerifyCodeExceedException(String msg) {
        super(1004, msg);
    }


}
