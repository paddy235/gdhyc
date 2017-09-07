package com.zhiren.fuelmis.dc.controller.gongyspg.fahb;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.gongyspg.fahb.FahbService;


/**
 * 
 * @author ZY
 *
 */
@Controller
@RequestMapping("gongys/fahb")
public class FahbController {

	@Autowired
	private FahbService fahbService;
	
	/**
	 * 根据日期生成发货表数据
	 * @param date
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/updateFahb")
	public void updateFahb(@RequestParam String search,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		Map map=JSONObject.fromObject(search);
		JSONObject json = fahbService.updateFahb(map);
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="getFahbList")
	public void getFahbList(@RequestParam String date,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;	
		Map<String, Object> map = new HashMap<String, Object>();
		if(null == date || ("").equals(date)){
			date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		map.put("date", date);
		JSONObject json = fahbService.getFahbList(map);
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
	@RequestMapping(value="getFahbInfo")
	public void getFahbInfo(HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;	
		Map<String, Object> map = new HashMap<String, Object>();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		map.put("date", date);
		JSONObject json = fahbService.getFahbList(map);
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}	
}
