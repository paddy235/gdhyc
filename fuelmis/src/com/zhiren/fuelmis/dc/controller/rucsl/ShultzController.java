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

import com.zhiren.fuelmis.dc.service.rucsl.ShultzService;

@Controller
@RequestMapping("/shultz")
public class ShultzController {

	@Autowired
	private ShultzService shultzService;
	
	@RequestMapping(value = "/getTableInfo")
	public void getTableInfo(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String strdate = request.getParameter("strdate");
		String diancid = request.getParameter("diancid");
		String enddate = request.getParameter("enddate");
		String gongysid = request.getParameter("gongysid");
		String pinzid = request.getParameter("pinzid");
		String jihkj = request.getParameter("jihkj");
		String shijtj = request.getParameter("shijtj");
		
		if(null == strdate || ("").equals(strdate)){
			strdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		if(null == diancid || "" == diancid ||("-1").equals(diancid)){
			diancid = "515";
		}		
		if(("-1").equals(pinzid)){
			pinzid = null; 
		}
		if(("-1").equals(jihkj)){
			jihkj = null; 
		}
		
		
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(shultzService.getTabelData(strdate,enddate,diancid,gongysid,pinzid,jihkj,shijtj).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
