package com.zhiren.fuelmis.dc.controller.rib;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.service.rib.IRibcxService;
import com.zhiren.fuelmis.dc.utils.testExportExcel.MakeHtml;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/rib")
public class RibController {
	
	@Autowired
	private IRibcxService ribcxService;
	
	@RequestMapping(value = "/ribcx")
	public void getYuedcaigjh(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String diancid = request.getParameter("diancid");
		String kaisriq = request.getParameter("kaisriq");
		String jiezriq = request.getParameter("jiezriq");
		String baobleix = request.getParameter("baobleix");
		if(null == kaisriq || "" ==kaisriq){
			kaisriq = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		if(null == jiezriq || "" ==jiezriq){
			jiezriq = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		if(null == diancid || "" == diancid ){
			diancid = "-1";
		}
		String tablehtml = "";
		if(baobleix.equals("3")){
			tablehtml = ribcxService.getShouhc_zhoub(diancid,kaisriq,jiezriq)+"<div>&nbsp;</div>"+ribcxService.getShouhc_zhoubDetail(diancid,kaisriq,jiezriq,baobleix);
			MakeHtml.html=tablehtml;
		}else if(baobleix.equals("4")){
			tablehtml = ribcxService.getRipjkc(diancid,kaisriq,jiezriq);
		}else{
			tablehtml = ribcxService.getShouhc(diancid,kaisriq,jiezriq)+"<div>&nbsp;</div>"+ribcxService.getShouhcDetail(diancid,kaisriq,jiezriq,baobleix);
			MakeHtml.html=tablehtml;
		}
		 
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(tablehtml);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
