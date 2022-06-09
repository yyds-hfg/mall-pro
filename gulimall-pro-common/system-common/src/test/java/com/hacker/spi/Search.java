package com.hacker.spi;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @Author: Zero
 * @Date: 2022/6/9 10:30
 * @Description:
 */
public interface Search {
    public List<String> searchDoc(String keyword);
}

class FileSearch implements Search{
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("文件搜索 "+keyword);
        return null;
    }
}

class DatabaseSearch implements Search{
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("数据搜索 "+keyword);
        return null;
    }
}

class TestCase {
    public static void main(String[] args) {
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = s.iterator();
        while (iterator.hasNext()) {
            Search search =  iterator.next();
            search.searchDoc("hello world");
        }
    }
}

