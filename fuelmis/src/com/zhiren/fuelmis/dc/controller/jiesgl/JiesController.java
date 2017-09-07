package com.zhiren.fuelmis.dc.controller.jiesgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.service.js.IJiesService;

/**
 * 
 * @author 刘志宇
 * @time 2016年1月21日 上午11:08:08
 * @enterprise zhiren
 */
@Controller
@RequestMapping("/jies")
public class JiesController {
	
	@Autowired
	private IJiesService jiesService;

	
	@SuppressWarnings({  "unchecked" })
	@RequestMapping(value = "/getFap")
	public void getFap(@RequestParam(defaultValue = "") String condition,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> conditionMap = JSONObject.fromObject(condition);
		JSONObject jsonObject = jiesService.getFap(conditionMap);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/getFapxx")
	public void getFapxx(@RequestParam(defaultValue = "") String condition,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> conditionMap = JSONObject.fromObject(condition);
		JSONObject jsonObject = jiesService.getFapxx(conditionMap);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询结算信息表
	 * @param request
	 * @param response
	 * @param session
	 */
	
	@RequestMapping(value = "/getJiesdhList")
	public void jiesdhList(@RequestParam(defaultValue = "") int id,HttpServletRequest request,HttpServletResponse response, HttpSession session) {		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String, Object>> list = jiesService.getJiesdhList(id);
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			jsonArray.add(new Combobox("全部", -1));
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("BIANM"), list.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 向数据库中添加一张发票
	 * @param info
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addFap")
	public void addFap(@RequestParam String info,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		Map<String,Object> map = JSONObject.fromObject(info);
		//给发票添加编号
		Integer maxBianm = jiesService.getMaxBianm();
		if(maxBianm!=null){
			map.put("BIANM", "FB"+(maxBianm+1));
		}else{
			map.put("BIANM", "FB"+0);
		}
		//添加状态
		map.put("ZHUANGT", "审批中");
		//在日期字段添加当前时间
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String time=format.format(date);
		map.put("RIQ", time);
		jiesService.insertJies(map);
	}
	@RequestMapping(value = "/getFapById")
	public void getFapById(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<Map<String,Object>> list=jiesService.getFapById(id);
		try {
			writer = response.getWriter();
			writer.write(JSONObject.fromObject(list.get(0)).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}