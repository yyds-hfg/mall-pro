package com.hacker.commom.annotation;

import java.lang.annotation.*;

/**
 * @Author: Zero
 * @Date: 2022/5/17 18:34
 * @Description:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    String value() default "";
}
