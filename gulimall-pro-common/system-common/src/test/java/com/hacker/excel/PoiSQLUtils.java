package com.hacker.excel;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Author: Zero
 * @Date: 2022/6/8 09:25
 * @Description: poi操作工具类
 */
@Slf4j
public class PoiSQLUtils {

    private static final String XLS = ".xls";
    private static final String XLSX = ".xlsx";
    private static StringBuilder createSql = new StringBuilder("");
    private static StringBuilder commentSql = new StringBuilder("");
    private static StringBuilder retain = new StringBuilder("retain");
    private static Integer count = 1;


    private static String tableName = "";
    private static String tableCHName = "";

    public static void main(String[] args) {
        File file = new File("D:\\sqlfile.txt");
        List<String> list = FileUtil.listFileNames("D:\\excel");
        list.forEach(System.out::println);
        list.forEach(path-> {
            String[] split = path.split("\\.");
            tableName = split[0].split("_")[0];
            tableCHName=split[0].split("_")[1];
            PoiSQLUtils.readExcel("D:\\excel\\"+path);
            FileUtil.writeUtf8String(String.valueOf(createSql),file);
            FileUtil.writeUtf8String(String.valueOf(commentSql),file);
            createSql = new StringBuilder("");
            commentSql = new StringBuilder("");
            System.out.println("__________________");
        });
//
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
                        if (cell == null) {
                            cellString = "";
                        } else {
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
//            System.out.println(createSql);
//            System.out.println(commentSql);
            String sql = getSQL(createSql, commentSql);
            System.out.println(sql);
        }
    }

    private static String getSQL(StringBuilder createSql, StringBuilder commentSql) {

        return "CREATE TABLE " + tableName + "(" +
                createSql.deleteCharAt(createSql.length() - 1) + ");" +
                "COMMENT ON TABLE " + tableName + " IS " + "'" + tableCHName + "'" + ";" +
                commentSql;
    }

    public static void getString(List<String> list) {
        String colum = list.get(1);
        if (StringUtils.isBlank(list.get(1))) {
            colum = retain.append(String.valueOf(count++)).toString();
            retain = new StringBuilder("retain");
        }
        createSql.append(colum + " " + "VARCHAR2(" + replaceString(list.get(2)) + "),");
        commentSql.append("COMMENT ON COLUMN " + tableName + "." + colum + " IS " + "'" + list.get(0) + "'" + ";");
    }

    public static String replaceString(String str) {
        final String replace = str.substring(2).replace(")", "");
        String[] split = null;
        if (replace.contains(",")) {
            split = replace.split(",");
            return String.valueOf(Integer.parseInt(split[0]) + Integer.parseInt(split[1]));
        }
        return replace;
    }

}
