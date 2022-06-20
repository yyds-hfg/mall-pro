package com.hacker.common;

import com.hacker.commom.utils.ListSplitter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/6/20 09:26
 * @Description:
 */
public class TestClass {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 53; i++) {
            list.add("顺序"+i);
        }
        ListSplitter<String> splitter = new ListSplitter<String>(list,5);
        while (splitter.hasNext()) {
            List<String> next = splitter.next();
            System.out.println(next.toString());
        }
    }
}
