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

import com.zhiren.fuelmis.dc.service.rib.IGuohrbcxService;

@Controller
@RequestMapping("/guohrb")
public class GuohrbController {

	@Autowired
	private IGuohrbcxService guohrbcxService;
	
	@RequestMapping(value = "/getTableInfo")
	public void getTableInfo(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String date = request.getParameter("date");
		String diancid = request.getParameter("diancid");
		if(null == date || "" ==date){
			date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		if(null == diancid || "" == diancid ){
			diancid = "515";
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(guohrbcxService.getTabelData(date, diancid).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
