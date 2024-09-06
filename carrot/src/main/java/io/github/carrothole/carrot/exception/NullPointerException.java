package io.github.carrothole.carrot.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: NullPointerException
 * Description:
 * date: 2024-8-31 22:16
 *
 * @author moon
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
public class NullPointerException extends CarrotException {

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String msg;

    public NullPointerException(String msg) {
        super(1003,msg);
    }


}
