package com.atguigu.common.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Zero
 * @Date: 2022/5/16 15:03
 * @Description: 添加防止Xss攻击
 *
 * 防止Xss攻击解决方案
 *      1.通过注解的方式
 *      2.通过Spring框架初始化数据绑定:
 *          将传进来的字符串进行Html编码,防止Xss攻击
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Constraint(validatedBy = { XssValidator.class })
public @interface Xss {

    String message() default "不允许任何脚本运行";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
