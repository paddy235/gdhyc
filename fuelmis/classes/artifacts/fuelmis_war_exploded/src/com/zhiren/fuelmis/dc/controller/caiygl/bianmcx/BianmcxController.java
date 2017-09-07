package com.zhiren.fuelmis.dc.controller.caiygl.bianmcx;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.service.caiygl.bianmcx.BianmcxService;

@Controller
@RequestMapping("caiygl/bianmcx")
public class BianmcxController {

	@Autowired
	private BianmcxService bianmcxService;
	
	@RequestMapping(value = "/getTableInfo")
	public void getTableInfo(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String date = request.getParameter("date");
		//String diancid = request.getParameter("diancid");	
		if(null == date || ("").equals(date)){
			date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}			
		String tablehtml = bianmcxService.getTabelData(date);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(tablehtml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
