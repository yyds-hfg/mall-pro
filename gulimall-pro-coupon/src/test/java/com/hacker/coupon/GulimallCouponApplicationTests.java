package com.hacker.coupon;

import com.hacker.common.annotation.FieldAlias;
import com.hacker.common.utils.SpringContextUtil;
import com.hacker.coupon.controller.CouponController;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.lang.reflect.Field;

@SpringBootTest
public class GulimallCouponApplicationTests {

    /**
     * 测试本地缓存Caffeine是否能成功实现
     */
    @SneakyThrows
    @Test
    public void contextLoads() {
        Cache cache = getCache();
        cache.put("name", "hanfugui");
        Thread.sleep(1000);
        System.out.println(cache.get("name").get());
    }

    @Autowired
    private CacheManager cacheManager;

    private Cache getCache() {
        return cacheManager.getCache("gulimall-coupon-cache");
    }

    /**
     * 测试SpringContext
     */
    @Test
    public void getContext() {
        CouponController bean = SpringContextUtil.getBean(CouponController.class);
        System.out.println(bean.membercoupons());
    }

    @Test
    public void testFieldAlias() {
        class Cat {
            @FieldAlias(fieldName = "名字")
            private String name;
        }
        Field[] fields = Cat.class.getDeclaredFields();
        for (Field field : fields) {
            FieldAlias annotation = field.getAnnotation(FieldAlias.class);
            if (field.getName().equals("name")) {
                String fieldName = annotation.fieldName();
                System.out.println("属性别名为:" + fieldName);
            }
        }
    }

}
