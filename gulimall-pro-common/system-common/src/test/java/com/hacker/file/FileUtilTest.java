package com.hacker.file;

import com.hacker.common.annotation.Element;
import com.hacker.entity.Person;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Zero
 * @Date: 2022/6/7 15:21
 * @Description: 批量插入对象
 */
@Slf4j
public class FileUtilTest {

    private static final String PATH = "D:\\test.dat";

    /**
     * 处理数据
     */
    private <T> void handleData(ArrayList<String> lines) {
        ArrayList<Person> objectList = new ArrayList<>();
        //得到每一个属性的定长
        List<Integer> elementSize = getElementSize(Person.class);
        lines.forEach(line -> {
            List<String> list = getLines(line, elementSize);

             Person person = (Person) getObject(Person.class, list);
            objectList.add(person);
        });
        //TODO 插入数据库
        objectList.forEach(System.out::println);
    }


    @Test
    public void test1() {
        //得到这个类的分割
        File file = new File(PATH);
        final ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = reader.readLine())!=null) {
                lines.add(line);
                if (lines.size()>4) {
                    //处理
                    handleData(lines);
                    lines.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //处理
        handleData(lines);
    }



    /**
     * 得到每一行切割好的数据
     */
    public static List<String> getLines(String line,List<Integer> lengths) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < lengths.size(); i++) {
            Integer length = lengths.get(i);
            Integer startIndex = getStartIndex(lengths,i);
            String s = line.substring(startIndex, startIndex + length);
            list.add(s);
        }
        return list;
    }

    /**
     * 得到当前索引
     */
    private static Integer getStartIndex(List<Integer>lengths, int i) {
        Integer length = 0;
        for (int j = 0; j < i; j++) {
            length+=lengths.get(j);
        }
        return length;
    }

    /**
     * 得到属性的长度
     */
    public static List<Integer> getElementSize(Class<?> type) {
        ArrayList<Integer> list = new ArrayList<>();
        List<Field> fieldList = Arrays.stream(type.getDeclaredFields())
                .sorted(Comparator.comparingInt(field -> Integer.parseInt(field.getAnnotation(Element.class).order())))
                .collect(Collectors.toList());
        fieldList.forEach(field->list.add(Integer.valueOf(field.getAnnotation(Element.class).length())));
        return list;
    }

    /**
     * @param type 字节码
     * @param list 需要设置的值
     * @return 对象
     */
    @SneakyThrows
    private Object getObject(Class<?> type,List<String> list) {
        List<Field> fieldList = Arrays.stream(type.getDeclaredFields())
                .sorted(Comparator.comparingInt(field -> Integer.parseInt(field.getAnnotation(Element.class).order())))
                .collect(Collectors.toList());
        Object o = type.getConstructor().newInstance();
        for (int i = 0; i < fieldList.size(); i++) {
            Method method = o.getClass().getMethod("set"+getUpper(fieldList.get(i).getName()), String.class);
            method.invoke(o,list.get(i));
        }
        return o;
    }

    public static String getUpper(String str) {
        String s = str.substring(0, 1).toUpperCase();
        return s+str.substring(1);
    }

}
