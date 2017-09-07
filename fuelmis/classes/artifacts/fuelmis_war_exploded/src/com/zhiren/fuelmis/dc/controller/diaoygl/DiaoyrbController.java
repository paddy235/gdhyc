package com.zhiren.fuelmis.dc.controller.diaoygl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.service.diaoygl.DiaoyrbService;

@Controller
@RequestMapping("diaoygl/ribtb")
public class DiaoyrbController {
	@Autowired
	private DiaoyrbService diaoyrbService;
	
	@RequestMapping(value = "/createData")
	public void createData(HttpServletRequest request , HttpServletResponse response) {
		
		String date = request.getParameter("date");
		String diancid = request.getParameter("diancid");	
		if(null == date || ("").equals(date)){
			date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		if(null == diancid || "" == diancid ){
			diancid = "515";
		}	
		
		diaoyrbService.CreateRBB(diancid, date);
	}

}