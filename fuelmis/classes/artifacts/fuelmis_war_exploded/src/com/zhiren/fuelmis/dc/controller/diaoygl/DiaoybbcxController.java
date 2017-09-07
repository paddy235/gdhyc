package com.zhiren.fuelmis.dc.controller.diaoygl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.service.diaoygl.IDiaoybbService;

@Controller
@RequestMapping("diaoygl/baobcx")
public class DiaoybbcxController {
	@Autowired
	private IDiaoybbService diaoybbService;
	
	@RequestMapping(value = "/getRanlrcgjhd")
	public void getRanlrcgjhd(String riq,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONArray array=diaoybbService.getRanlrcgjhd(riq);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	@RequestMapping(value = "/getDuizd")//getJiesList
	public void getDuizd(String riq,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONArray array=diaoybbService.getDuizd(riq);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}


}

