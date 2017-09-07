package com.zhiren.fuelmis.dc.controller.rucsl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.ruchy.IRucslService;


/** 
 * @author 刘志宇
 */
@Controller
@RequestMapping("yansgl/rucsl")
public class SanfsllrController {
	@Autowired
	private IRucslService rucslService;
	@RequestMapping(value = "/getGongysList")
	public void getGongysList(@RequestParam(defaultValue = "") String riq ,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String,Object>> list = rucslService.getGongysList(riq);	
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ID", -1);
		map.put("MINGC", "全部");
		list.add(map);
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(list).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/getChep")
	public void getChep(@RequestParam(defaultValue = "") String condition,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		@SuppressWarnings("unchecked")
		Map<String, Object> conditionMap = JSONObject.fromObject(condition);
		List<Map<String,Object>> list = rucslService.getChep(conditionMap);
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(list).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/updateChep")
	public void updateChep(@RequestParam(defaultValue = "") String data,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		JSONArray a = JSONArray.fromObject(data);
		rucslService.updateChep(a);
	}
}
