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

import com.zhiren.fuelmis.dc.service.rucsl.ZonghtService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

@Controller
@RequestMapping("/zonghtz")
public class ZonghtzController {

	@Autowired
	private ZonghtService zonghtzService;
	@RequestMapping(value = "/getTableInfo")
	public void getTableInfo(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String strdate = request.getParameter("strdate");
		String diancid = request.getParameter("diancid");
		String enddate = request.getParameter("enddate");
		String meik = request.getParameter("meik");
		String pinzid = request.getParameter("pinzid");
		String type = request.getParameter("type");
		String xiax = request.getParameter("xiax");
		String shangx = request.getParameter("shangx");
		if(null == strdate || ("").equals(strdate)){
			enddate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			strdate = DateUtil.getNextDay(new Date());
		}
		if(null == diancid || "" == diancid || "-1".equals(diancid)){
			diancid = "515";
		}
		if(null == xiax || "".equals(xiax.trim()) ){
			xiax = "-1";
		}
		if(null == shangx || "".equals(shangx.trim()) ){
			shangx = "-1";
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(zonghtzService.getTabelData(strdate,enddate,diancid,meik,pinzid,type,xiax,shangx).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
