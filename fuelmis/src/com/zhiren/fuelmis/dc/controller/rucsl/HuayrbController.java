package com.zhiren.fuelmis.dc.controller.rucsl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.service.rucsl.HuayrbService;

@Controller
@RequestMapping("/huayrb")
public class HuayrbController {
	@Autowired
	private HuayrbService huayrbService;
	
	@RequestMapping(value = "/getTableInfo")
	public void getTableInfo(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String date = request.getParameter("date");
		String diancid = request.getParameter("diancid");	
		if(null == date || ("").equals(date)){
			date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		if(null == diancid || "" == diancid ){
			diancid = "515";
		}			
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(huayrbService.getTabelData(date,diancid).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
