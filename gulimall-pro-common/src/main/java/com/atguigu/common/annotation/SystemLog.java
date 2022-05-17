package com.atguigu.common.annotation;

import java.lang.annotation.*;

/**
 * @Author: Zero
 * @Date: 2022/5/17 18:34
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD})
@Documented
public @interface SystemLog {

    String value() default "";
}
