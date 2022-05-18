package com.hacker.common.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @Author: Zero
 * @Date: 2022/5/16 15:05
 * @Description: 获取属性别名自定义注解
 *      在某些特殊情况下,可能需要获取类的属性的注释
 * @Test: com.atguigu.gulimall.coupon.GulimallCouponApplicationTests#testFieldAlias()
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FieldAlias {

    @AliasFor("fieldName")
    String value() default "";

    @AliasFor("value")
    String fieldName() default "";

}
