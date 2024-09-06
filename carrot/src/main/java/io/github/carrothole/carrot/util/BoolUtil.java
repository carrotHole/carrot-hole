package io.github.carrothole.carrot.util;

/**
 * Description: 布尔工具类 <br>
 * Date: 2024/9/6 14:56 <br>
 *
 * @author moon
 * @since 0.0.1
 */
public class BoolUtil {
    public static final int trueInt = 1;
    public static final String trueIntStr = trueInt+"";
    public static final int falseInt = 0;
    public static final String falseIntStr = falseInt+"";
    public static boolean isTrue(Integer i){
        return i == trueInt;
    }

    public static boolean isTrue(String s){
        return trueIntStr.equals(s);
    }

}
