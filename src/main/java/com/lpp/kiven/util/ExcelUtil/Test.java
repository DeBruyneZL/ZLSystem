package com.lpp.kiven.util.ExcelUtil;

import com.lpp.kiven.model.test.model.Student;
import com.lpp.kiven.util.PDFUtil.PdfUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Test test=new Test();
        test.test();
    }
    private void test(){
        List<Student> list=new ArrayList();
        for (int i=0;i<=5;i++){
            Student student=new Student();
            student.setGender("男");
            student.setName("张三");
            student.setPhone("1320818027"+i);
            student.setStudentId("20153106059"+i);
            student.setMath("9"+i);
            student.setEnglish("8"+i);
            list.add(student);
        }
        String title="成绩单";
        String[] columnHeaders={"姓名","性别","电话","学号","数学","英语"};
        try {
            PdfUtil.generatePdfFile(list,"E:\\File\\student.pdf",title,columnHeaders);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date=sdf.format(new Date());
            PdfUtil.waterMark("E:\\File\\student.pdf","E:\\File\\studentwater.pdf",date,"debruyne");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
	
}
