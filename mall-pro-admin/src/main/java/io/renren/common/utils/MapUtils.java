package io.renren.common.utils;

import java.util.HashMap;


/**
 *
 *
 * @Author: Zero
 * @Date: 2022/6/2 15:21
 * @Description: 链式HashMap
 */
public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
