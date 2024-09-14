package io.github.carrothole.carrot.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ClassName: BaseException
 * Description:
 * date: 2024-8-31 22:16
 *
 * @author moon
 * @since 0.0.1
 */
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class CarrotException extends RuntimeException {

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String msg;

    public CarrotException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CarrotException(Throwable e) {
        super(e);
        this.code = 500;
        this.msg = "服务器异常，请联系管理员";
    }


}
