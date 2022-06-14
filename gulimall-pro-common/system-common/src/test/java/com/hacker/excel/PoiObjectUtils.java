package com.hacker.excel;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zero
 * @Date: 2022/6/8 10:46
 * @Description:
 */
@Slf4j
public class PoiObjectUtils {

    private static final String XLS = ".xls";
    private static final String XLSX = ".xlsx";
    private static StringBuilder retain = new StringBuilder("RETAIN");
    private static Integer count = 1;
    private static Integer order = 1;

    //需要更改
    private static final String PATH = "D:\\CUSTR.xlsx";
    private static final List<Map<String, List<String>>> eleAllList = new ArrayList<>();
    private static final String JAVA_PATH = "D:\\JavaCode\\gulimall-pro\\gulimall-pro-common\\system-common\\src\\test\\java\\com\\hacker\\entity\\";

    //TODO 待处理
    private static List<String> tableNameList = new ArrayList<>();

    public static void main(String[] args) {
        PoiObjectUtils.readExcel(PATH);
        for (int i = 0; i < eleAllList.size(); i++) {
            Map<String, List<String>> listMap = eleAllList.get(i);
            for (Map.Entry<String, List<String>> entry : listMap.entrySet()) {
                extracted(entry);
            }
            System.out.println();
        }

    }

    public static void extracted(Map.Entry<String, List<String>> entry) {
        int count = 0;
        List<String> javaList = new ArrayList<>();
        List<String> java_po_name_list = FileUtil.listFileNames(JAVA_PATH);
        String key = entry.getKey(); //表名
        List<String> eleList = entry.getValue();
        //根据文件表名 找到文件的路径
        File file = new File(JAVA_PATH + key + ".Java");

        //  然后读文件  遍历  添加到javaList  再把写入文件  清空javalist
        List<String> list = FileUtil.readLines(file, "utf-8"); //然后读文件
        for (String s1 : list) {
            javaList.add(s1);
            if (s1.contains("@TableField")) {
                javaList.add(eleList.get(count++));
            }
        }
        FileUtil.writeLines(javaList, file, "utf-8");
    }


    /**
     * 读取excel文件
     * 流程：Workbook(工作簿)——>Sheet(工作表)——>Row(行)——>Cell(单元格)
     *
     * @param filePath excel文件地址
     */
    public static void readExcel(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("excel文件地址不可为空");
        }
        boolean validEnds = filePath.endsWith(XLS) || filePath.endsWith(XLSX);
        if (!validEnds) {
            throw new IllegalArgumentException("excel文件地址无效，filePath=" + filePath);
        }

        InputStream inputStream = null;
        Workbook workbook = null;
        try {
            inputStream = new FileInputStream(filePath);
            //创建工作簿对象
            if (filePath.endsWith(XLS)) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (filePath.endsWith(XLSX)) {
                workbook = new XSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("excel文件地址无效，filePath=" + filePath);
            }

            //获取工作簿下sheet的个数
            int sheetNum = workbook.getNumberOfSheets();

            //遍历工作簿中的所有数据
            for (int i = 0; i < sheetNum; i++) {
                // sheet
                Sheet sheet = workbook.getSheetAt(i);
                String sheetName = sheet.getSheetName();
                tableNameList.add(sheetName);
                // 循环行Row
                List<String> eleList = new ArrayList<>();
                for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    // 迭代单元格cell
                    ArrayList<String> list = new ArrayList<>();
                    for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
                        Cell cell = row.getCell(cellIndex);
                        //todo 业务处理 可在这里将每行数据封装成自己的业务对象 作为函数返参
                        String cellString = null;
                        if (cell == null) {
                            cellString = "";
                        } else {
                            cellString = cell.toString();
                        }
                        list.add(cellString);
                    }
                    eleList.add(getString(list));
                }
                HashMap<String, List<String>> hashMap = new HashMap<>();
                hashMap.put(sheetName, eleList);
                eleAllList.add(hashMap);
                order =1;
            }
        } catch (FileNotFoundException e) {
            log.error("文件不存在，filePath={}", filePath);
            throw new RuntimeException("文件不存在", e);
        } catch (IOException e) {
            log.error("文件读取异常，filePath={}", filePath);
            throw new RuntimeException("文件读取异常", e);
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("数据流关闭异常！", e);
            }
        }
    }

    public static String getString(List<String> list) {
        //添加注解
        String element = "";
        String colum = list.get(1);
        if (StringUtils.isBlank(list.get(1))) {
            colum = retain.append(String.valueOf(count++)).toString();
            element = addElementAnnotation(list, retain.toString());
            retain = new StringBuilder("RETAIN");
        } else {
            element = addElementAnnotation(list, null);
        }
        return element;
    }

    private static String addElementAnnotation(List<String> list, String retainX) {
        return "@Element(length = " + "\"" + replaceString(list.get(2)) + "\"" + ",order = " + "\"" + (order++) + "\"" + ")";
    }

    public static String changeFormat(String str) {
        String lowerCase = str.toLowerCase();
        char[] chars = lowerCase.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '_') {
                char c = chars[i + 1];
                if (c >= 'a' && c <= 'z') {
                    //			将小写转大写输出
                    chars[i + 1] = c -= 32;
                }
            }
        }
        String s = new String(chars);
        return s.replace("_", "");
    }

    public static String replaceString(String str) {
        final String replace = str.substring(2).replace(")", "");
        String[] split = null;
        if (replace.contains(",")) {
            split = replace.split(",");
            return String.valueOf(Integer.parseInt(split[0]));
        }
        return replace;
    }

}
