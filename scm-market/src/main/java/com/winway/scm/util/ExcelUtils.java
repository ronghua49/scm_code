package com.winway.scm.util;

import org.apache.poi.hssf.usermodel.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel工具类
 *
 * @Author: FangGan
 * @Email: libayiv@163.com
 * @Date: 2019/8/22 14:48
 **/
public abstract class ExcelUtils {

    /**
     * 根据数据生成Excel表格（xls格式）
     *
     * @param lists       数据源
     * @param sheetName   工作表名
     * @param columnNames 列名
     * @return
     */
    public static HSSFWorkbook generateExcel(List<List<String>> lists, String sheetName, String[] columnNames) {
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet(sheetName);
        sheet.autoSizeColumn(1, true);// 自适应列宽度
        // 填充表头标题
        HSSFRow firstRow = sheet.createRow(0);// 第几行（从0开始）

        HSSFCellStyle sheetStyle = workbook.createCellStyle();
        sheetStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中显示
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 16);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        sheetStyle.setFont(font);
        firstRow.setRowStyle(sheetStyle);
        for (int i = 0; i < columnNames.length; i++) {
            firstRow.createCell(i).setCellValue(columnNames[i]);
        }
        // 填充表格内容
        if (lists != null && !lists.isEmpty()) {
            for (int i = 0; i < lists.size(); i++) {
                HSSFRow row2 = sheet.createRow(i + 1);// index：第几行
                List<String> data = lists.get(i);
                for (int j = 0; j < data.size(); j++) {
                    HSSFCell cell = row2.createCell(j);// 第几列：从0开始
                    //设置单元格内容为字符串型
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(data.get(j));
                }
            }
        }
        return workbook;
    }

    public static <T extends Serializable> List<String> castObj2List(T obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        List<String> result = new ArrayList<>(fields.length);

        for (Field field: fields) {
            field.setAccessible(true);
            result.add(StringValueOf(field.get(obj)));
        }
        return result;
    }

    private static String StringValueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }

}
