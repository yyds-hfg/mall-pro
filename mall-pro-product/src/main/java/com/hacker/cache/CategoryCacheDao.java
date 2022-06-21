package com.hacker.cache;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONReader;
import com.hacker.entity.CategoryEntity;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/6/21 17:10
 * @Description:
 */
@Repository
public class CategoryCacheDao {

    private static final String KEY_PATTERN = "category:%s"; // user:用户编号 <1>

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> operations;

    private static String buildKey(String id) {
        return String.format(KEY_PATTERN, id);
    }

    public List<CategoryEntity> get(String id) {
        String key = buildKey(id);
        String value = operations.get(key);
        return null;
    }

    public void set(String id, List<CategoryEntity> list) {
        operations.set(buildKey(id),JSON.toJSONString(list));
    }

    public static void main(String[] args) {
        ArrayList<Cat> cats = new ArrayList<>();
        cats.add(new Cat("1","11"));cats.add(new Cat("2","22"));
        String s = JSON.toJSONString(cats);
        System.out.println(s);
        JSONArray jsonArray = JSON.parseArray(s);
        List<Cat> catList = jsonArray.toJavaList(Cat.class, JSONReader.Feature.SupportArrayToBean);
        for (Cat cat : catList) {
            System.out.println(cat);
        }
    }



}
@Data
@AllArgsConstructor
class Cat {
    private String id;
    private String name;
}
