package io.renren.common.utils;

/**
 * Redis所有Keys
 *
 * @Author: Zero
 * @Date: 2022/6/2 15:22
 * @Description: Redis所有Keys
 */
public class RedisKeys {

    public static String getSysConfigKey(String key) {
        return "sys:config:" + key;
    }
}
