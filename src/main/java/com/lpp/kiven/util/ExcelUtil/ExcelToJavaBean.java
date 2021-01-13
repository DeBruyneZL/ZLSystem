package com.lpp.kiven.util.ExcelUtil;

import com.lpp.kiven.config.log.Log4j2Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：zhangliang
 * @date ：Created in 2019/11/20 16:09
 * @description：读取解析Excel文件
 * @modified By：
 * @version: $
 */
public class ExcelToJavaBean {
    /**
     * 文件名后缀
     */
    public static final String XLSX = ".xlsx";
    public static final String XLS=".xls";
    /**
     * 文件类型
     */
    public static final int TYPE_XLSX = 1;
    public static final int TYPE_XLS=2;
    public static final int TYPE_OTHER=3;
    public static final int TYPE_NULL=0;

    /**
     * 获取Excel文件（.xls和.xlsx都支持）
     * @param file
     * @return  解析excle后的Json数据
     * @throws IOException
     * @throws FileNotFoundException
     * @throws InvalidFormatException
     */
    public static JSONArray readExcel(MultipartFile file) throws Exception{
        int res = checkFile(file);
        if (res == TYPE_NULL) {
            Log4j2Logger.error("未找到文件!");
        }else if (res == TYPE_XLSX){
            return readXLSX(file);
        }else if (res == TYPE_XLS){
            return readXLS(file);
        }else if (res == TYPE_OTHER){
            Log4j2Logger.error("文件类型错误!");
        }
        JSONArray array = new JSONArray();
        return array;
    }

    /**
     * 判断File文件的类型
     * @param file 传入的文件
     * @return TYPE_NULL-文件为空，TYPE_XLSX-XLSX文件，TYPE_XLS-XLS文件，TYPE_OTHER-其他文件
     */
    public static int checkFile(MultipartFile file){
        if (file==null) {
            return TYPE_NULL;
        }
        String fileName = file.getOriginalFilename();
        if (fileName.endsWith(XLSX)) {
            return TYPE_XLSX;
        }
        if (fileName.endsWith(XLS)) {
            return TYPE_XLS;
        }
        return TYPE_OTHER;
    }

    /**
     * 读取XLSX文件,对2007版本以及更高版本的支持 ---- XSSFWorkbook
     * @param file
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public  static JSONArray readXLSX(MultipartFile file) throws IOException{
        Workbook book = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = book.getSheetAt(0);
        return read(sheet, book);
    }

    /**
     * 读取XLS文件,对2003版本的Excel的支持 ---- HSSFWorkbook
     * @param file
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static JSONArray readXLS(MultipartFile file) throws FileNotFoundException, IOException{
        POIFSFileSystem poifsFileSystem = new POIFSFileSystem(file.getInputStream());
        Workbook book = new HSSFWorkbook(poifsFileSystem);
        Sheet sheet = book.getSheetAt(0);
        return read(sheet, book);
    }

    /**
     * 解析数据
     * @param sheet 表格sheet对象
     * @param book  工作簿对象
     * @return
     * @throws IOException
     */
    public static JSONArray read(Sheet sheet,Workbook book) throws IOException{
        int rowStart = sheet.getFirstRowNum();	// 首行下标
        int rowEnd = sheet.getLastRowNum();	// 尾行下标
        // 如果首行与尾行相同，表明只有一行，直接返回空数组
        if (rowStart == rowEnd) {
            return null;
        }
        // 获取第一行JSON对象键
        Row firstRow = sheet.getRow(rowStart);
        int cellStart = firstRow.getFirstCellNum();
        int cellEnd = firstRow.getLastCellNum();
        Map<Integer, String> keyMap = new HashMap<Integer, String>();
        for (int j = cellStart; j < cellEnd; j++) {
            keyMap.put(j,getValue(firstRow.getCell(j), rowStart, j, book, true));
        }
        // 获取每行JSON对象的值
        JSONArray array = new JSONArray();
        for(int i = rowStart+1; i <= rowEnd ; i++) {
            Row eachRow = sheet.getRow(i);
            JSONObject obj = new JSONObject();
            StringBuffer sb = new StringBuffer();
            for (int k = cellStart; k < cellEnd; k++) {
                if (eachRow != null) {
                    String val = getValue(eachRow.getCell(k), i, k, book, false);
                    sb.append(val);		// 所有数据添加到里面，用于判断该行是否为空
                    obj.put(keyMap.get(k),val);
                }
            }
            if (sb.toString().length() > 0) {
                array.add(obj);
            }
        }
        return array;
    }

    /**
     * 获取每个单元格的数据
     * @param cell 单元格对象
     * @param rowNum 第几行
     * @param index 该行第几个
     * @param book 主要用于关闭流
     * @param isKey 是否为键：true-是，false-不是。 如果解析Json键，值为空时报错；如果不是Json键，值为空不报错
     * @return
     * @throws IOException
     */
    public static String getValue(Cell cell,int rowNum,int index,Workbook book,boolean isKey) throws IOException{
        // 空白或空
        if (cell == null || cell.getCellType()==Cell.CELL_TYPE_BLANK ) {
                return "";
        }
        // 0. 数字 类型
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return df.format(date);
            }
            String val = NumberToTextConverter.toText(cell.getNumericCellValue());

            return val;
        }
        // 1. String类型
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            String val = cell.getStringCellValue();
            if (val == null || val.trim().length()==0) {
                return "";
            }
            return val.trim();
        }
        // 2. 公式 CELL_TYPE_FORMULA
        if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return cell.getStringCellValue();
        }
        // 4. 布尔值 CELL_TYPE_BOOLEAN
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return cell.getBooleanCellValue()+"";
        }
        // 5.	错误 CELL_TYPE_ERROR
        return "";
    }
}
