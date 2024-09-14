package io.github.carrothole.carrot.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Description:  <br>
 * Date: 2024/9/2 13:22 <br>
 *
 * @author moon
 * @since
 */
@ToString(callSuper = true)
@Data
@NoArgsConstructor
public class UnSupportOperationException extends CarrotException {

    public UnSupportOperationException(String message) {
        super(1002, message);
    }

}