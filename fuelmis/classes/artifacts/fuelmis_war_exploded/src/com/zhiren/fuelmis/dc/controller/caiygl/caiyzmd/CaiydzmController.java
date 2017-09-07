package com.zhiren.fuelmis.dc.controller.caiygl.caiyzmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.service.caiygl.caiyzmd.CaiydzmService;

@Controller
@RequestMapping("caiygl/caiyzmd")
public class CaiydzmController {
	@Autowired
	private CaiydzmService caiydzmService;
	
	@RequestMapping(value = "/getTableInfo")
	public void getTableInfo(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String date = request.getParameter("date");
		//String diancid = request.getParameter("diancid");	
		if(null == date || ("").equals(date)){
			date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}			
		String tablehtml = caiydzmService.getTabelData(date);
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
