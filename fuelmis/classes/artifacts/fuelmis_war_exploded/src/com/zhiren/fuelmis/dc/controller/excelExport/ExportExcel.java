package com.zhiren.fuelmis.dc.controller.excelExport;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

public class ExportExcel {
    @SuppressWarnings({ "unchecked", "deprecation" })
    public void exportExcel(String title,JSONArray headers,JSONArray rows, OutputStream out) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
//        workbook.setSheetName(0,"专利明细表", HSSFWorkbook.ENCODING_UTF_16);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        // 字符、日期格式
        HSSFCellStyle A_V_CENTER = workbook.createCellStyle();
        A_V_CENTER.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        A_V_CENTER.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        

        HSSFCellStyle numberFormat = workbook.createCellStyle();
        numberFormat.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        numberFormat.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));  

//        // 设置这些样式
////        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        // 生成一个字体
//        HSSFFont font = workbook.createFont();
//        font.setColor(HSSFColor.VIOLET.index);
//        font.setFontHeightInPoints((short) 12);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        // 把字体应用到当前的样式
//        style.setFont(font);
//        // 生成并设置另一个样式
//        HSSFCellStyle style2 = workbook.createCellStyle();
////        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
//        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        // 生成另一个字体
//        HSSFFont font2 = workbook.createFont();
//        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        // 把字体应用到当前的样式
//        style2.setFont(font2);

        // 声明一个画图的顶级管理器
//        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
//        // 定义注释的大小和位置,详见文档
//        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
//                0, 0, 0, (short) 4, 2, (short) 6, 5));
//        // 设置注释内容
//        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
//        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
//        comment.setAuthor("lvchunjun");

        // 产生表格标题行
        int rowIndex=0;
        List<String> rowspanLst=new ArrayList<String>();
        HSSFRow row = null;
        for (int i = 0; i < headers.size(); i++) {
            JSONArray rowJsonObject = (JSONArray) headers.get(i) ;
            row = sheet.createRow(rowIndex);
            int cellIndex=0;
            for (int j = 0; j < rowJsonObject.size(); j++,cellIndex++) {
            	while(rowspanLst.contains(rowIndex+"#"+cellIndex)){
            		cellIndex++;
            	}
                HSSFCell cell = row.createCell(cellIndex);
                cell.setCellStyle(A_V_CENTER);
                JSONObject cellJson = (JSONObject) rowJsonObject.get(j) ;
                String v = (String)cellJson.get("value") ;
                v=v.trim();
                String dataType = (String)cellJson.get("dataType") ;
                int rowspan = cellJson.getInt("rowspan") ;
                int colspan = cellJson.getInt("colspan") ;
                if(rowspan>1){
                	sheet.addMergedRegion(new Region(rowIndex,(short)cellIndex,rowIndex+rowspan-1,(short)cellIndex)); 
                	for(int a=rowIndex;a<=rowIndex+rowspan-1;a++){
                		rowspanLst.add(a+"#"+cellIndex);
                	}
                }
                if(colspan>1){
                	sheet.addMergedRegion(new Region(rowIndex,(short)cellIndex,rowIndex,(short)(cellIndex+colspan-1)));  
                	cellIndex=cellIndex+colspan-1 ;
                }

                if("number".equals(dataType)){
                	cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                	v=v.replaceAll(",", "") ;
                	cell.setCellStyle(numberFormat);
                    cell.setCellValue(Double.parseDouble(v));
                    continue;
                }else{
                    cell.setCellStyle(A_V_CENTER);
                }
                HSSFRichTextString text = new HSSFRichTextString(v.trim());
                cell.setCellValue(text);
            }
            rowIndex++;
        }

        // 遍历集合数据，产生数据行
        for (int i=0;i<rows.size();i++){
            JSONArray rowJsonObject = (JSONArray) rows.get(i) ;
            row = sheet.createRow(rowIndex);
            int cellIndex=0;
            for (int j = 0; j < rowJsonObject.size(); j++,cellIndex++) {
            	while(rowspanLst.contains(rowIndex+"#"+cellIndex)){
            		cellIndex++;
            	}
                HSSFCell cell = row.createCell(cellIndex);
                JSONObject cellJson = (JSONObject) rowJsonObject.get(j) ;
                String v = (String)cellJson.get("value") ;
                v=v.trim() ;
                String dataType = (String)cellJson.get("dataType") ;
                int rowspan = cellJson.getInt("rowspan") ;
                int colspan = cellJson.getInt("colspan") ;

                if(rowspan>1){
                	sheet.addMergedRegion(new Region(rowIndex,(short)cellIndex,rowIndex+rowspan-1,(short)cellIndex)); 
                	for(int a=rowIndex;a<=rowIndex+rowspan-1;a++){
                		rowspanLst.add(a+"#"+cellIndex);
                	}
                }
                if(colspan>1){
                	sheet.addMergedRegion(new Region(rowIndex,(short)cellIndex,rowIndex,(short)(cellIndex+colspan-1)));  
//                	System.out.println(rowIndex+","+cellIndex+"#"+rowIndex+","+(cellIndex+colspan-1));
                	cellIndex=cellIndex+colspan-1 ;
                }
                
                if("number".equals(dataType)){
                	cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                	v=v.replaceAll(",", "") ;
                	cell.setCellStyle(numberFormat);
                    cell.setCellValue(Double.parseDouble(v));
                    continue;
                }else{
                    cell.setCellStyle(A_V_CENTER);
                }
                HSSFRichTextString text = new HSSFRichTextString(v);
                cell.setCellValue(text);
            }
            rowIndex++;
        }
//        System.out.println(rowspanLst);
        try {
            workbook.write(out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
	public static void main(String args[]){
		System.out.println("3,000,000.00".replaceAll(",", ""));
	}
}
