package com.hacker.common.utils;

import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: Zero
 * @Date: 2022/5/16 10:30
 * @Description: SpringContextUtil工具类
 */
public class SpringContextUtil implements ApplicationContextAware {

    @Setter
    private static ApplicationContext context;

    /**
     * 给context赋值
     *
     * @param applicationContext applicationContext
     * @throws BeansException bean异常
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setContext(applicationContext);
    }

    /**
     * 获取到ApplicationContext
     *
     * @return ApplicationContext
     */
    @SneakyThrows
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return context;
    }

    /**
     * 获取Bean
     *
     * @param requiredType bean的类型
     * @param <T>          requiredType
     * @return bean对象
     * @throws NoSuchBeanDefinitionException   —如果没有找到给定类型的bean
     * @throws NoUniqueBeanDefinitionException —如果发现给定类型的多个bean
     */
    @SneakyThrows
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return context.getBean(requiredType);
    }

    /**
     * 获取Bean
     *
     * @param name         要检索的bean的名称
     * @param requiredType requiredType bean的类型
     * @param <T>          bean对象
     * @return bean对象
     * @throws NoSuchBeanDefinitionException—如果没有这样的bean定义
     * @throws BeanDefinitionStoreException                —如果给出了参数，但受影响的bean不是原型
     * @throws BeansException—如果不能创建bean
     */
    @SneakyThrows
    public static <T> T getBean(String name, Class<T> requiredType) {
        assertContextInjected();
        return context.getBean(name, requiredType);
    }


    /**
     * 判断ApplicationContext 是否注入
     *
     * @throws IllegalAccessException 当前执行的方法没有访问指定类
     */
    private static void assertContextInjected() throws IllegalAccessException {
        if (context == null) {
            throw new IllegalAccessException("Application have not bean injected");
        }
    }

}
