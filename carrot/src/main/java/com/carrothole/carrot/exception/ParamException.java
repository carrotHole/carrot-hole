package com.carrothole.carrot.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: ParamException
 * Description: 参数异常
 * date: 2024-9-1 17:23
 *
 * @author moon
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
public class ParamException extends CarrotException {

    public ParamException(String message) {
        super(1001, message);
    }

}
