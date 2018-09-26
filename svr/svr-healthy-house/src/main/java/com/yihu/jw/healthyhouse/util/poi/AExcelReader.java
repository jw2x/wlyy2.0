package com.yihu.jw.healthyhouse.util.poi;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AExcelReader {
    protected List errorLs = new ArrayList<>();
    protected List correctLs = new ArrayList<>();
    protected Map<String, Set> repeat = new HashMap<>();

//    public abstract void read(Workbook rwb) throws Exception;
    public abstract void read(Workbook rwb) throws Exception;


    public void read(MultipartFile file) throws Exception {
        read(ExcelUtils.getWorkBook(file));
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            str = str.replaceAll("\\u00A0" ," ");
            //去除字符串中的空格、回车、换行符、制表符
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    protected String getCellCont(Sheet sheet, int row, int col){
        Cell cell = sheet.getRow(row).getCell(col);
        return ExcelUtils.getCellValue(cell);
    }

    public List getErrorLs() {
        return errorLs;
    }

    public void setErrorLs(List errorLs) {
        this.errorLs = errorLs;
    }

    public List getCorrectLs() {
        return correctLs;
    }

    public void setCorrectLs(List correctLs) {
        this.correctLs = correctLs;
    }

    public Map<String, Set> getRepeat() {
        return repeat;
    }

    public void setRepeat(Map<String, Set> repeat) {
        this.repeat = repeat;
    }

}
