package io.github.carrothole.carrot.util;

import io.github.carrothole.carrot.exception.NullPointerException;

/**
 * Description:  <br>
 * Date: 2024/6/24 14:45 <br>
 *
 * @author moon
 * @since 0.0.1
 */
public class CheckUtil {


    /**
     * 判断传入的对象是否为null,如果不为null返回当前对象,如果null报错,错误信息展示提示信息内容
     */
    public static <T> T checkNotNull(T t, String message) {
        if (t == null) {
            throw new NullPointerException(message);
        }
        return t;
    }


}
