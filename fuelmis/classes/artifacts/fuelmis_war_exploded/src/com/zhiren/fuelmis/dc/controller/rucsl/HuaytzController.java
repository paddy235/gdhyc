package com.zhiren.fuelmis.dc.controller.rucsl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.service.rucsl.HuaytzService;

@Controller
@RequestMapping("/huaytz")
public class HuaytzController {

	@Autowired
	private HuaytzService huaytzService;
	
	
	@RequestMapping(value = "/getTableInfo")
	public void getTableInfo(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String strdate = request.getParameter("strdate");
		String diancid = request.getParameter("diancid");
		String enddate = request.getParameter("enddate");
		String yunsfs  = request.getParameter("yunsfs");
		String pinzid = request.getParameter("pinzid"); 
		
		if(null == strdate || ("").equals(strdate)){
			strdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		if(null == diancid || "" == diancid ||("-1").equals(diancid)){
			diancid = "515";
		}
		if(("-1").equals(pinzid)){
			pinzid = null;
		}
		if(("-1").equals(yunsfs)){
			yunsfs = null;
		}
		
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(huaytzService.getTabelData(strdate,enddate,diancid,yunsfs,pinzid).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@RequestMapping(value = "/getTableInfoByRiq")
	public void getTableInfoByRiq(String search,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map= JSONObject.fromObject(search);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(huaytzService.getTabelDataByRiq(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
