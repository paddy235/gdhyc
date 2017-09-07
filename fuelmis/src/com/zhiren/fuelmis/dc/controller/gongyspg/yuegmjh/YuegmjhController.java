package com.zhiren.fuelmis.dc.controller.gongyspg.yuegmjh;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.gongyspg.yuegmjh.YuegmjhService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/**
 * 
 * @author ZY
 *
 */
@Controller
@RequestMapping("gongyspg/yuegmjh")
public class YuegmjhController {
	
	@Autowired
	private YuegmjhService yuegmjhService;
	
	/**
	 * 获取全部评分标准
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getYuegmjhList")
	public void getYuegmjhList(@RequestParam String condition,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;	
		@SuppressWarnings("unchecked")
		Map<String, Object> map=JSONObject.fromObject(condition);
		List<Map<String, Object>> l = yuegmjhService.getYuegmjhList(map);
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(l).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/saveYuegmjh")
	public void saveYuegmjh(HttpSession session,@RequestParam String data,@RequestParam String condition,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> yuegongmjhmx=JSONArray.fromObject(data);
		@SuppressWarnings("unchecked")
		Map<String, Object> yuegongmjh=JSONObject.fromObject(condition);
		Renyxx lurry = (Renyxx) session.getAttribute("user");
		yuegongmjh.put("LURRY", lurry.getId());
		yuegmjhService.saveYuegmjh(yuegongmjh,yuegongmjhmx);
	}
	/**
	 * 获取全部评分标准
	 * @param request
	 * @param response
	 */
	/*@RequestMapping(value = "/updateYuegmjh")
	public void updateYuegmjh(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		map.put("date", date);
		List<Long> list = yuegmjhService.getupdateYuegmjh(map);		
		JSONArray json = yuegmjhService.updateYuegmjh(list);		
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	
	/**
	 * 获取全部
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/searchYuegmjhList")
	public void searchYuegmjhList(@RequestParam String gongysbid,@RequestParam String date, HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		if(null == date || ("").equals(date)){
			date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		if(!("-1").equals(gongysbid)){
			map.put("gongysbid", gongysbid);
		}
		map.put("date", date);

		JSONObject json = yuegmjhService.searchYuegmjh(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取全部
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addYuegmjh")
	public void addYuegmjh(@RequestParam String gongysbid,@RequestParam String date, HttpServletRequest request ,
			HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		if(null == date || ("").equals(date)){
			date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		map.put("date", date);
		map.put("gongysbid", gongysbid);
		boolean isHet = yuegmjhService.cheakHetzt(map);
		
		Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
		Renyxx renyxx = (Renyxx) session.getAttribute("user");
		map.put("diancxxid", diancxx.getId());
		map.put("lurry", renyxx.getId());
		map.put("lursj", DateUtil.format(new Date()));
		//map.put("zhibdm", "SL");
		JSONArray json = new JSONArray();
		if(isHet == true){
			yuegmjhService.delYuegmjh(map);
			json = yuegmjhService.addYuegmjh(map);
		}else{
			json.add(5);
		}
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	
	@RequestMapping(value = "/delYuegmjh")
	public void delYuegmjh(@RequestParam String gongysbid,@RequestParam String date,HttpServletRequest request , HttpServletResponse response) {
		
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("gongysbid", gongysbid);
		JSONArray json = yuegmjhService.delYuegmjh(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/submitYuegmjh")
	public void submitYuegmjh(@RequestParam String condition,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> c=JSONObject.fromObject(condition);
		yuegmjhService.submitYuegmjh(c);
	}
	
	
}
