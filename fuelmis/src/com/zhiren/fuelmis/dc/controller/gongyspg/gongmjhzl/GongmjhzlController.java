package com.zhiren.fuelmis.dc.controller.gongyspg.gongmjhzl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.gongyspg.gongmjhzl.GongmjhzlService;

/**
 * 
 * @author ZY
 *
 */
@Controller
@RequestMapping("gongyspg/gongmjhzl")
public class GongmjhzlController {

	@Autowired
	private GongmjhzlService gongmjhzlService;
	
	
	/**
	 * 获取全部
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getRigmjhList")
	public void getRigmjhList(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		String date = request.getParameter("date");	
		if(null == date || ("").equals(date)){
			date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		String gongysid = request.getParameter("gongysid");
		if(("-1").equals(gongysid)){
			gongysid = null;
		}
		map.put("date", date);
		map.put("gongysid", gongysid);
		JSONObject json = gongmjhzlService.getRigmjhList(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/searchRigmjhList")
	public void searchRigmjhList(@RequestParam String gongysid,@RequestParam String date, HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		if(null == date || ("").equals(date)){
			date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		if(("-1").equals(gongysid)){
			gongysid = null;
		}
		map.put("date", date);
		map.put("gongysid", gongysid);
		JSONObject json = gongmjhzlService.getRigmjhList(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	@RequestMapping(value = "/getRigmjhInfoById")
//	public void searchRigmjhList(@RequestParam String gongysid,@RequestParam String date, HttpServletRequest request , HttpServletResponse response) {
//		response.setContentType("text/html;charset=UTF-8");
//		Map<String,Object> map = new HashMap<String, Object>();
//		if(null == date || ("").equals(date)){
//			date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
//		}
//		if(("-1").equals(gongysid)){
//			gongysid = null;
//		}
//		map.put("date", date);
//		map.put("gongysid", gongysid);
//		JSONObject json = gongmjhzlService.getRigmjhList(map);
//		PrintWriter writer  = null;
//		try {
//			writer = response.getWriter();
//			writer.write(json.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}	
	
}
