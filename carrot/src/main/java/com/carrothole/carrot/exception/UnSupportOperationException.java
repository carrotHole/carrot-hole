package com.carrothole.carrot.exception;

/**
 * Description:  <br>
 * Date: 2024/9/2 13:22 <br>
 *
 * @author moon
 * @since
 */
public class UnSupportOperationException extends CarrotException {

    public UnSupportOperationException(String message) {
        super(1002, message);
    }

}