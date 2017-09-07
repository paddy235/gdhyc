package com.zhiren.fuelmis.dc.controller.rucslys;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.zhiren.fuelmis.dc.service.rucslys.ChepbtmpService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

@Controller
@RequestMapping("rucslys/chepbtmp")
public class ChepbtmpController {

	@Autowired
	private ChepbtmpService chepbtmpService;
	
	/**
	 * 获取数据
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getChepbtmpList")
	public void getChepbtmp(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = chepbtmpService.getChepbtmpList(null);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getChepbtmpListInfo")
	public void getChepbtmp(@RequestParam String diancid,@RequestParam String strdate,@RequestParam String enddate,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		if(null == strdate || ("").equals(strdate)){
			strdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		if(null == diancid || "" == diancid ){
			diancid = "515";
		}
		map.put("diancid", diancid);
		map.put("strdate", strdate);
		map.put("enddate", enddate);
		JSONArray json = chepbtmpService.searchChepbtmpList(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	@RequestMapping(value = "/searchChepbtmpList")
	public void searchChepbtmpList(@RequestParam String diancid,@RequestParam String strdate,@RequestParam String enddate,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		if(null == strdate || ("").equals(strdate)){
			strdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		if(null == diancid || "" == diancid ){
			diancid = "515";
		}
		map.put("diancid", diancid);
		map.put("strdate", strdate);
		map.put("enddate", enddate);
		JSONObject json = chepbtmpService.getChepbtmpList(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/updateData")
	public void updateData(@RequestParam String info,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		JSONArray json =  JSONArray.fromObject(info);
		Renyxx renyxx = (Renyxx) session.getAttribute("user");
		String caozry = renyxx.getQuanc();
		String caozsj = DateUtil.format(new Date());
		JSONObject result = chepbtmpService.updateData(json,caozry,caozsj);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
