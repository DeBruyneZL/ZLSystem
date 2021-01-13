package com.lpp.kiven.util.ExcelUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author ：zhangliang
 * @date ：Created in 2020/9/8 14:36
 * @description：生成Excel 返回workbook对象
 * @modified By：
 * @version: $
 */
public class MakeEcxelWorkbook {
    public static final String      VAL_TYPE_DATE_DEFUALT = "date";
    public static final String      VAL_TYPE_STRING       = "string";
    //Execl表格样式
    private Map<String , CellStyle> contentCellStyleMap   = null;
    /**
     * 生成excel
     * @param sheetName 文件名
     * @param titleMap 表头<表名:长度>
     * @param objList 填充内容对象List
     * @param objNames 写入excel的对象名
     * @return 工作簿对象
     */
    public Workbook exportExcel(String sheetName, Map<String, Integer> titleMap, List<?> objList, String[] objNames){
        Workbook workbook= new SXSSFWorkbook(10000);
        //创建sheet
        Sheet sheet = null;
        if (StringUtils.isBlank(sheetName)) {
            sheet = workbook.createSheet();
        } else {
            sheet = workbook.createSheet(sheetName);
        }
        int index = 0;
        // 设置列宽
        for (Map.Entry<String, Integer> entry : titleMap.entrySet()) {
            sheet.setColumnWidth(index++, 256*entry.getValue()+184);
        }
        // 固定首行
        sheet.createFreezePane(0, 1, 0, 1);
        // 创建表头样式
        CellStyle titleCellStyle = this.createTitleCellStyle(workbook);
        // 设置表头
        Row row = sheet.createRow(0);
        index = 0;
        // 设置列名
        for (Map.Entry<String, Integer> entry : titleMap.entrySet()) {
            this.setRowVal(row, index++, titleCellStyle, entry.getKey());
        }
        int startRowNum = 1;
        //将表格填充内容写入workBook之中
        for(Object obj : objList ){
            Row contentRow = sheet.createRow(startRowNum);
            startRowNum++;
            int i = 0;
            for (String contentName : objNames) {
                try {
                    Field field= obj.getClass().getDeclaredField(contentName);
                    field.setAccessible(true);
                    Object val = field.get(obj);
                    this.setRowVal(workbook, contentRow, i, val);
                } catch (Exception e) { }
                i++;
            }
        }
        return workbook;
    }
    /**
     * 创建表头样式
     * @param workbook
     * @return
     */
    private CellStyle createTitleCellStyle(Workbook workbook) {
        // 表头样式
        CellStyle titleCellStyle = workbook.createCellStyle();
        // 下边框
        titleCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        // 左边框
        titleCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        // 上边框
        titleCellStyle.setBorderTop(CellStyle.BORDER_THIN);
        // 右边框
        titleCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        // 居中
        titleCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        // 垂直
        titleCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        // 设置背景色
        titleCellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
        titleCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //字体
        Font titleFont = workbook.createFont();
        titleFont.setFontName("宋体");
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
        titleFont.setFontHeightInPoints((short) 10);
        titleCellStyle.setFont(titleFont);// 选择需要用到的字体格式
        return titleCellStyle;
    }
    /**
     * 表格赋值
     * @param row
     * @param col
     * @param cellStyle
     * @param value
     */
    private void setRowVal(Row row, int col, CellStyle cellStyle, Object value) {
        Cell cell = row.createCell(col);
        if (null != cellStyle) {
            cell.setCellStyle(cellStyle);
        }
        if (value instanceof Date) {
            Date date= (Date) value;
            cell.setCellValue(date);
        } else {
            String val = null;
            if (value==null) {
                val = "";
            }else{
                val = value.toString();
            }
            cell.setCellValue(val);
        }
    }
    /**
     * 设置表格值
     * @param workbook
     * @param row
     * @param col
     * @param value
     */
    private void setRowVal(Workbook workbook, Row row, int col, Object value) {
        if (value instanceof Date) {
            this.setRowVal(workbook, row, col, value, VAL_TYPE_DATE_DEFUALT);
        } else {
            this.setRowVal(workbook, row, col, value, VAL_TYPE_STRING);
        }
    }
    /**
     * 根据类型创建内容格式
     * @param workbook
     * @param row
     * @param col
     * @param value
     * @param valType
     */
    private void setRowVal(Workbook workbook, Row row, int col, Object value, String valType) {
        if (null == contentCellStyleMap) {
            contentCellStyleMap = new HashMap<String, CellStyle>();
            contentCellStyleMap.put("string", this.createContentCellStyle(workbook, "@"));
            contentCellStyleMap.put("date", this.createContentCellStyle(workbook, "yyyy-MM-dd HH:mm:ss"));
        }
        CellStyle cellStyle = contentCellStyleMap.get(valType);
        this.setRowVal(row, col, cellStyle, value);
    }
    /**
     * 内容格式
     * @param workbook
     * @param format
     * @return
     */
    private CellStyle createContentCellStyle(Workbook workbook, String format) {
        // 内容样式
        CellStyle contentCellStyle = workbook.createCellStyle();
        //边框
        contentCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        contentCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        contentCellStyle.setBorderTop(CellStyle.BORDER_THIN);
        contentCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        // 居中
        contentCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        // 垂直
        contentCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        //字体
        Font contentFont = workbook.createFont();
        contentFont.setFontName("宋体");
        contentFont.setFontHeightInPoints((short) 10);
        // 选择需要用到的字体格式
        contentCellStyle.setFont(contentFont);
        if (StringUtils.isNotEmpty(format)) {
            DataFormat dateformat = workbook.createDataFormat();
            contentCellStyle.setDataFormat(dateformat.getFormat(format));
        }
        return contentCellStyle;
    }
}
