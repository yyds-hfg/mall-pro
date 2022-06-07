package com.hacker.common.annotation;

import java.lang.annotation.*;

/**
 * @Author: Zero
 * @Date: 2022/6/7 15:43
 * @Description:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Element {

    /**
     * 属性长度
     */
    String length() default "0";

    /**
     * 属性顺序
     */
    String order() default "1";

}
