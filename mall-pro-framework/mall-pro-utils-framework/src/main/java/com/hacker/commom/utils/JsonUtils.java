package com.hacker.commom.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONReader;

import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/6/21 19:12
 * @Description: json工具类
 */
public class JsonUtils {

    /**
     * json对象转化为List集合
     * @param josn josn
     * @param classs classs
     * @param <T>
     * @return
     */
    public static  <T> List<T> getListByJSON(String josn, Class<T> classs) {
        JSONArray jsonArray = JSON.parseArray(josn);
        return jsonArray.toJavaList(classs, JSONReader.Feature.SupportArrayToBean);
    }

}
