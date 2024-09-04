package io.github.carrothole.carrot.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 接口前置鉴权注解 <br>
 * Date: 2024/8/30 15:03 <br>
 *
 * @author moon
 * @since 0.0.1
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PreAuthorize {

    String[] menu();
    String[] user() default {};
    String[] role() default {};

}
