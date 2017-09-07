package com.zhiren.fuelmis.dc.controller.excelExport;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.PropertiesUtil;
import com.zhiren.fuelmis.dc.utils.testExportExcel.MakeHtml;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope("prototype")
@RequestMapping("/export")
public class ExcelExportController {

	@RequestMapping(value = "/excel")
	public void excelExport(HttpServletRequest request, HttpServletResponse response) {
		try {
			String fileName=request.getParameter("fileName") ;
			String sheetName=request.getParameter("sheetName") ;
			String tabletitles=request.getParameter("tabletitles") ;
			String tableRows=request.getParameter("tableRows") ;
			OutputStream os = response.getOutputStream();// 取得输出流        
			response.reset();// 清空输出流        
//			os.write(new   byte []{( byte ) 0xEF ,( byte ) 0xBB ,( byte ) 0xBF });   
			response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("UTF-8"),"ISO8859-1"));  
			// 设定输出文件头        
//			response.setContentType("text/html;charaset=UTF-8");
			response.setContentType("application/msexcel");//;charaset=ISO8859-1");// 定义输出类型    
//			System.out.println(tabletitles);
//			System.out.println(tableRows);
			JSONArray titles = JSONArray.fromObject(tabletitles) ;
			JSONArray rows = JSONArray.fromObject(tableRows) ;
			ExportExcel excel = new ExportExcel();
			excel.exportExcel(sheetName,titles,rows,os) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/toExcel")
	public void toExcel(@RequestParam(defaultValue = "") String r, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> m = JSONObject.fromObject(r);
		String filePath= PropertiesUtil.getValue("upload_file_folder");
		//生成文件
//		String fileName= DateUtil.getCurrentTime();
//		MakeHtml.writeExcel(m.get("html").toString(),filePath+m.get("name").toString());
		MakeHtml.writeExcel(MakeHtml.html,filePath+m.get("name").toString());
	}
}
