package com.lpp.kiven.util.ExcelUtil;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelStyleUtils {
    /**
     * 设置字体
     * @param wb
     * @param fontName 字体名称
     * @param bold 是否加粗
     * @param point 字体大小
     * @param color 字体颜色
     * @return
     */
    public static Font setFont(XSSFWorkbook wb,String fontName,boolean bold,Short point,Short color){
        Font font = wb.createFont();
        if (fontName!=null) {
            font.setFontName(fontName);
        }
        if (point!=null) {
            font.setFontHeightInPoints(point);
        }
        if (color!=null) {
            font.setColor(color);
        }
        return font;
    }
}
