package com.zhiren.fuelmis.dc.controller.kucgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.kucgl.KucsscbService;
@Controller
@RequestMapping("kucgl/")
public class KucsscbController {
	@Resource
	private KucsscbService kucsscbService;
	
	@RequestMapping(value = "/getKucsscbAll")
	public void getKucsscbAll(HttpServletRequest request,HttpServletResponse response,@RequestParam String Date) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("Date", Date);
		
		JSONArray json = (JSONArray) kucsscbService.getKucsscbAll(map);
		PrintWriter writer=null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
