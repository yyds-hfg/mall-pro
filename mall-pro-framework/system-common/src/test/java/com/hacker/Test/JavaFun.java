package com.hacker.Test;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Zero
 * @Date: 2022/6/9 10:11
 * @Description:
 */
public class JavaFun {

    @Test
    public void test() {
        ArrayList<Cat> res = new ArrayList<>();
        ArrayList<Cat> list = new ArrayList<>();
        Map<String, List<Cat>> map = list.stream().collect(Collectors.groupingBy(Cat::getId));
        Set<String> keySet = map.keySet();
        for (String s : keySet) {
            List<Cat> cats = map.get(s);
            Cat cat = cats.stream().sorted((s1, s2) -> {
                return 1;
            }).collect(Collectors.toList()).get(0);
            res.add(cat);
        }

    }
}
@Data
class Cat {
    private String id;
    private String name;
    private String create_time;

}
