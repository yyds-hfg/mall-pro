package com.atguigu.gulimall.coupon;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallCouponApplicationTests {

    /**
     * 测试本地缓存Caffeine是否能成功实现
     */
    @SneakyThrows
    @Test
    public void contextLoads() {
        Cache cache = getCache();
        cache.put("name","hanfugui");
        Thread.sleep(1000);
        System.out.println(cache.get("name").get());
    }

    @Autowired
    private CacheManager cacheManager;

    private Cache getCache() {
        return cacheManager.getCache("gulimall-coupon-cache");
    }

}
