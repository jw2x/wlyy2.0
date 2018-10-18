package com.yihu.jw.base.endpoint.common.excel;




import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.List;

public abstract class AExcelWriter {


    public abstract void write(Workbook wwb, List ls, File file) throws Exception;

//    public void write(OutputStream os, List ls) throws Exception{
//        write(Workbook.createWorkbook(os), ls);
//    };

    public void write( File file, List ls) throws Exception{
        write(ExcelUtils.getWorkBook(file),ls,file);
    };

    //添加单元格内容
    public void addCell(Sheet ws, int row, int column, String data )  {
        Row row1 = ws.getRow(row) ;
        row1 = row1==null? ws.createRow(row) : row1;
        Cell cell = row1.createCell(column);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(data);
    }

    //添加单元格内容
    public  void addCell(Sheet ws, int row, int column, String data , String memo)  {
        Row row1 = ws.getRow(row) ;
        row1 = row1==null? ws.createRow(row) : row1;
        Cell cell = row1.createCell(column);
        cell.setCellValue(data);
    }

    //添加单元格内容
    public void addCell(Workbook wb,Sheet ws, int row, int column, String data, String memo)  {
        //1.得到一个POI的工具类
        CreationHelper factory = wb.getCreationHelper();
        //2 得到一个换图的对象
        Drawing drawing = ws.createDrawingPatriarch();
        //3. ClientAnchor是附属在WorkSheet上的一个对象，  其固定在一个单元格的左上角和右下角.
        ClientAnchor anchor = factory.createClientAnchor();
        //4  获取行
        Row row1 = ws.getRow(row) ;
        row1 = row1==null? ws.createRow(row) : row1;
        Cell cell = row1.createCell(column);
        cell.setCellValue(data);
        //5 设置注释
        anchor.setCol1(cell.getColumnIndex());
        anchor.setCol2(cell.getColumnIndex() + 2);
        anchor.setRow1(row1.getRowNum());
        anchor.setRow2(row1.getRowNum() + 3);

        if(!StringUtils.isEmpty(memo)){
            Comment comment0 = drawing.createCellComment(anchor);
            RichTextString richTextString = factory.createRichTextString(memo);
            comment0.setString(richTextString);
            cell.setCellComment(comment0);
        }


    }
}
