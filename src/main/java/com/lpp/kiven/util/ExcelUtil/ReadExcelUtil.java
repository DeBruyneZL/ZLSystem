package com.lpp.kiven.util.ExcelUtil;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zhangliang
 * @date ：Created in 2020/6/2 16:46
 * @description：Excel文件读取工具
 * @modified By：
 * @version: $
 */
public class ReadExcelUtil {
    /**
     * 读取Excel文件指定列的数据
     * @param iStream 文件InputStream
     * @param columnIndex 读第几列 （0开始）
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static List<String> getColumn(InputStream iStream, int columnIndex) throws IOException, InvalidFormatException {
        List<String> columnList=new ArrayList<>();
        StringBuilder stringBuilder=new StringBuilder();
        Workbook book = WorkbookFactory.create(iStream);
        Sheet sheet = book.getSheetAt(0);

        for (int runNum =0; runNum <=sheet.getLastRowNum();runNum++) {
            Row row=sheet.getRow(runNum);
            int minColIx=row.getFirstCellNum();
            int maxColIx=row.getLastCellNum();
            //遍历该行，获取每个cell元素
            for (int colIx = minColIx; colIx <maxColIx; colIx++) {
                Cell cell=row.getCell(colIx);
                //获取指定的一列
                if (cell.getColumnIndex()==columnIndex) {
                    columnList.add(cell.getStringCellValue());
                    stringBuilder.append(cell.getStringCellValue());
                }else {
                    break;
                }
            }
        }

        return columnList;
    }
}
