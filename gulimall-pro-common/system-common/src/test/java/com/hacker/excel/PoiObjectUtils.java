package com.hacker.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/6/8 10:46
 * @Description:
 */
@Slf4j
public class PoiObjectUtils {

    private static final String XLS = ".xls";
    private static final String XLSX = ".xlsx";
    private static StringBuilder retain = new StringBuilder("retain");
    private static Integer count = 1;
    private static Integer order =1;

    //需要更改
    private static final String PATH = "D:\\CUSTR.xlsx";

     public static void main(String[] args) {
        PoiObjectUtils.readExcel(PATH);
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
                // 循环行Row
                for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    // System.out.println("第" + (rowIndex + 1) + "行的数据如下：");
                    Row row = sheet.getRow(rowIndex);
                    // 迭代单元格cell
                    final ArrayList<String> list = new ArrayList<>();
                    for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
                        Cell cell = row.getCell(cellIndex);
                        //todo 业务处理 可在这里将每行数据封装成自己的业务对象 作为函数返参
                        String cellString = null;
                        if (cell==null) {
                            cellString = "";
                        }else {
                            cellString = cell.toString();
                        }
                        list.add(cellString);
                    }
                    getString(list);
                    list.clear();
                }
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



    public static void getString(List<String> list) {
        //添加注解
        String element = addElementAnnotation(list);

        String colum = list.get(1);
        if (StringUtils.isBlank(list.get(1))) {
            colum = retain.append(String.valueOf(count++)).toString();
            retain = new StringBuilder("retain");
        }
        String field = "private String " + changeFormat(colum) + ";";
        System.out.println(element);
        System.out.println(field);
    }

    private static String addElementAnnotation(List<String> list) {
        return "@Element(length = "+replaceString(list.get(2))+",order = "+(order++)+")";
    }

    public static String changeFormat(String str) {
        String lowerCase = str.toLowerCase();
        char[] chars = lowerCase.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i]=='_') {
                char c = chars[i+1];
                if(c >= 'a' && c <= 'z') {
                        //			将小写转大写输出
                   chars[i+1] =c-=32;;
                }
            }
        }
        final String s = new String(chars);
        return s.replace("_","");
    }

    public static String replaceString(String  str) {
        final String replace = str.substring(2).replace(")", "");
        String[] split = null;
        if (replace.contains(",")) {
            split = replace.split(",");
            return String.valueOf(Integer.parseInt(split[0])+Integer.parseInt(split[1]));
        }
        return replace;
    }

    @Test
    public void test() {
        changeFormat("CUSTR_NBR");
    }

}
