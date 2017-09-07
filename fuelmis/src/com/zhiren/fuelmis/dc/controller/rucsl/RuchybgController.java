package com.zhiren.fuelmis.dc.controller.rucsl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.service.rucsl.IRuchybgService;

@Controller
@RequestMapping("/ruchybg")
public class RuchybgController {
	@Autowired
	private IRuchybgService ruchybgService;
	
	@RequestMapping(value = "/getAllHuaybg")
	public void getTableInfo(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String huaybh = request.getParameter("huaybh");
		String dianc = request.getParameter("dianc");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("huaybh", huaybh);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(ruchybgService.getTabelData(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@RequestMapping(value = "/getHuaybh")
	public void getHuaybh(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String sDate = request.getParameter("sDate");
		String eDate = request.getParameter("eDate");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(ruchybgService.getHuaybh(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
	}
}
