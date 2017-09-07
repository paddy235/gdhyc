package com.zhiren.fuelmis.dc.controller.gongyspg.gongmjhzl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonObject;
import com.zhiren.fuelmis.dc.service.gongyspg.gongmjhzl.GongmjhtzService;


/**
 * 
 * @author SZT
 *
 */
@Controller
@RequestMapping("gongyspg/gongmjhzl")
public class GongmjhtzController {
 
	@Resource
	private GongmjhtzService gongmjhtzService;
 
	@RequestMapping(value = "/getGongmjhtzAll")
	public void getGongmjhtzAll(HttpServletRequest request,HttpServletResponse response,@RequestParam String condition) {
		response.setContentType("text/html;charset=UTF-8");

		Map<String,Object> map=JSONObject.fromObject(condition);
		JSONArray json = (JSONArray) gongmjhtzService.getGongmjhtzAll(map);
		PrintWriter writer=null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping(value  = "/updateGongmjh")
	public void updateGongmjh(HttpServletRequest request , HttpServletResponse response ,@RequestParam String data){
		response.setContentType("text/html;charset=UTF-8");
		try{
			JSONArray jsonArray = JSONArray.fromObject(data);
			gongmjhtzService.updateGongmjh(jsonArray);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/gongmjhFab")
	public void gongmjhFab(HttpServletRequest request,HttpServletResponse response,@RequestParam String data){
		response.setContentType("text/html;charset=UTF-8");
		try{
			JSONArray jsonArray = JSONArray.fromObject(data);
			gongmjhtzService.insertGongmjhFab(jsonArray);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
