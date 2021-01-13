package com.lpp.kiven.util.ExcelUtil;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExportExcelUtils {
	 //默认高度  
    private static short DEFAULT_ROW_HEIGHT = 400;  
    //默认宽度  
    private static int DEFAULT_CELL_WIDTH = 3000;  
    /**
     * 导出Excel文件并下载
     * @param response
     * @param fileName 文件名
     * @param data 文件内容
     * @throws Exception
     */
    public static void exportExcel(HttpServletResponse response, String fileName, ExcelData data) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8"));
        exportExcel(data, response.getOutputStream());
    }

    /**
     * 导出到本地
     * @param data
     * @param out
     * @throws Exception
     */
    public static void exportExcel(ExcelData data, OutputStream out) throws Exception {

        //得到Excel工作簿对象    
        XSSFWorkbook wb = new XSSFWorkbook();
        try {
            String sheetName = data.getName();
            if (null == sheetName) {
                sheetName = "Sheet1";
            }
            XSSFSheet sheet = wb.createSheet(sheetName);
            writeExcel(wb, sheet, data);

            wb.write(out);
        } finally {
//            wb.close();
        }
    }

    /**
     * 
     * @param wb
     * @param sheet
     * @param data
     */
    private static void writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelData data) {

        int rowIndex = 0;

        rowIndex = writeTitlesToExcel(wb, sheet, data.getTitles());
        writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);
       /* autoSizeColumns(sheet, data.getTitles().size() + 1);*/

    }

    /**
     * 填充表头
     * @param wb
     * @param sheet
     * @param titles 列名
     * @return 行
     */
    private static int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, List<String> titles) {
        int rowIndex = 0;
        int colIndex = 0;

        Font titleFont=ExcelStyleUtils.setFont(wb, "simsun", true,null, IndexedColors.BLACK.index);

        XSSFCellStyle titleStyle = wb.createCellStyle();
        //设置单元格的水平对齐类型
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //设置单元格的垂直对齐类型
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        //设置前景填充颜色
        titleStyle.setFillForegroundColor(new XSSFColor());
        //指定图案和纯色单元格填充的单元格填充信息
        titleStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        titleStyle.setFont(titleFont);
        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor());

        //在工作表中创建一个新行
        Row titleRow = sheet.createRow(rowIndex);
        // titleRow.setHeightInPoints(25);
        colIndex = 0;

        for (String field : titles) {
            //创建新单元格
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(field);
            cell.setCellStyle(titleStyle);
            colIndex++;
        }

        rowIndex++;
        return rowIndex;
    }

    /**
     * 填充数据
     * @param wb
     * @param sheet
     * @param rows 内容
     * @param rowIndex 行（数据一般从1开始）
     * @return
     */
    private static int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows, int rowIndex) {
        int colIndex = 0;

        Font dataFont =ExcelStyleUtils.setFont(wb, "simsun", false, (short)0, IndexedColors.BLACK.index);

        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        dataStyle.setFont(dataFont);
        setBorder(dataStyle, BorderStyle.THIN, new XSSFColor());

        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            colIndex = 0;

            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }

                cell.setCellStyle(dataStyle);
                colIndex++;
            }
            rowIndex++;
        }
        return rowIndex;
    }

    private static void autoSizeColumns(Sheet sheet, int columnNumber) {

        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            sheet.autoSizeColumn(i, true);
            int newWidth = (int) (sheet.getColumnWidth(i) + 100);
            if (newWidth > orgWidth) {
                sheet.setColumnWidth(i, newWidth);
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }

    /**
     * 设置单元格边框类型
     * @param style
     * @param border 边框类型
     * @param color 边框颜色
     */
    private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
        //设置单元格顶部边框的边框类型
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setBorderBottom(border);
        style.setBorderColor(BorderSide.TOP, color);
        style.setBorderColor(BorderSide.LEFT, color);
        style.setBorderColor(BorderSide.RIGHT, color);
        style.setBorderColor(BorderSide.BOTTOM, color);
    }
    
    /** 
     *  
     * @param book 工作簿对象，【可选】 
     * @param hanlder 自定义类型处理【可选】 
     * @param titles 标题 
     * @param columns 列名（Map类型处理，自定义可选） 
     * @param columnsWidth 宽度 
     * @param height 行高 
     * @param sheetTitle 表标题 
     * @param datas 数据 
     * @return 
     */  
    public static XSSFWorkbook exportExcel(XSSFWorkbook book,ExcelTypeHanlder hanlder,String[] titles,String[] columns  
            ,Integer[] columnsWidth,Short height,String sheetTitle,List datas){  
          
        if(book==null){  
            book = new XSSFWorkbook();  
        }  
          
        int size = DEFAULT_CELL_WIDTH;  
          
        //列大小  
        if(columnsWidth!=null&&columnsWidth.length==1){  
            size = columnsWidth[0];  
        }  
        if(height==null){  
            height = DEFAULT_ROW_HEIGHT;  
        }  
        XSSFSheet sheet = book.createSheet(sheetTitle);  
        int rowindex = 0;  
        XSSFRow firstrow = sheet.createRow(rowindex);  
        rowindex++;  
        sheet.setDefaultColumnWidth(size);  
        firstrow.setHeight(height);  
          
        XSSFFont font = book.createFont();  
        font.setBold(true);  
        XSSFCellStyle cellstyle = book.createCellStyle();  
        cellstyle.setFont(font);  
        cellstyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);  
          
        //标题  
        if(titles!=null){  
            int index = 0;  
            for (String title : titles) {  
                XSSFCell cell = firstrow.createCell(index);  
                cell.setCellStyle(cellstyle);  
                cell.setCellValue(title);  
                //列宽度设置  
                if(columnsWidth==null||columnsWidth.length==0||columnsWidth.length==1){  
                    sheet.setColumnWidth(cell.getColumnIndex(), size);  
                }else{  
                    if((columnsWidth.length-1)>=index){  
                        sheet.setColumnWidth(cell.getColumnIndex(), columnsWidth[index]==null?size:columnsWidth[index]);  
                    }else{  
                        sheet.setColumnWidth(cell.getColumnIndex(), size);  
                    }  
                }  
                index++;  
            }  
        }  
        if(datas==null){  
            return book;  
        }  
          
        //写入数据  
        for (Object data : datas) {  
              
            //map 类型处理  
            if(data instanceof Map){  
                Map<String,Object> map = (Map<String, Object>) data;  
                XSSFRow row = sheet.createRow(rowindex);  
                int i = 0;  
                for (String column : columns) {  
                    XSSFCell cell = row.createCell(i);  
                    Object val = map.get(column);  
                    if(hanlder!=null&&val==null){  
                        Object temp = hanlder.dataNullHander(column,map);  
                        cell.setCellValue(temp!=null?temp.toString():"");  
                    }else{  
                        cell.setCellValue(val!=null?val.toString():"");  
                    }  
                    i++;  
                }  
                row.setHeight(DEFAULT_ROW_HEIGHT);  
                rowindex++;  
            }else{  
                //其他处理  
                if(hanlder!=null){  
                    Object obj = data;  
                    XSSFRow row = sheet.createRow(rowindex);  
                    hanlder.typeHanlder(data, row);  
                    rowindex++;  
                }  
            }  
        }  
        return book;  
    }   
    
    //自定义处理对象回调  
    public static abstract class ExcelTypeHanlder<T>{  
        //类型处理  
        public void typeHanlder(T data,XSSFRow row){  
              
        }  
          
        //空数据处理  
        public Object dataNullHander(String column,T obj){  
            return null;  
        }  
    }
    
    public static void main(String[] args) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("hello");
        List<String> titles = new ArrayList();
        titles.add("a1");
        titles.add("a2");
        titles.add("a3");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        List<Object> row = new ArrayList();
        row.add("11111111111");
        row.add("22222222222");
        row.add("3333333333");
        rows.add(row);
        List<Object> row2 = new ArrayList();
        row2.add("1");
        row2.add("2");
        row2.add("3");
        rows.add(row2);
        data.setRows(rows);
      //生成本地
        File f = new File("E:/test.xlsx");
        FileOutputStream out = new FileOutputStream(f);
        ExportExcelUtils.exportExcel(data, out);
        out.close();
    }
}