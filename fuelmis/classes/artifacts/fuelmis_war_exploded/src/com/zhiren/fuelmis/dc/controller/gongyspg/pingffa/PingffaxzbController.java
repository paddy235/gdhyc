package com.zhiren.fuelmis.dc.controller.gongyspg.pingffa;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.zhiren.fuelmis.dc.entity.gongyspg.pingffaxzb.Pingffaxzb;
import com.zhiren.fuelmis.dc.service.gongyspg.pingffaxzb.PingffaxzbService;

/**
 * 
 * @author ZY
 *
 */
@Controller
@RequestMapping("gongyspg/pingffaxzb")
public class PingffaxzbController {
	
	@Autowired
	private PingffaxzbService pingffaxzbService;

	
	/**
	 * 新增
	 * @param info
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addPingffaxzb")
	public void addPingffaxzb(@RequestParam String info,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
//		Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
		@SuppressWarnings("static-access")
		Pingffaxzb pingffaxzb = (Pingffaxzb) json.toBean(json, Pingffaxzb.class);
//		if(diancxx.getId()!=null){
//			Pingffaxzb.setDiancxxb_id(diancxx.getId());
//		}else{
			pingffaxzb.setDiancxxb_id(515l);
//		}
		JSONArray jsonArray = pingffaxzbService.addPingffaxzb(pingffaxzb);;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/updatePingffaxzb")
	public void updatePingffaxzb(@RequestParam String info,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
		@SuppressWarnings("static-access")
		Pingffaxzb pingffaxzb = (Pingffaxzb) json.toBean(json, Pingffaxzb.class);
		JSONArray jsonArray = pingffaxzbService.updatePingffaxzb(pingffaxzb);;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 获取全部
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getPingffaxzbList")
	public void getPingffaxzbList(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = pingffaxzbService.getPingffaxzbList(null);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/delPingffaxzb")
	public void delPingffaxzb(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONArray json = pingffaxzbService.delPingffaxzb(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取编辑数据
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/editPingffaxzb")
	public void editPingffaxzb(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
			response.setContentType("text/html;charset=UTF-8");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", id);
			JSONObject json = pingffaxzbService.getPingffaxzbById(map);
			PrintWriter writer  = null;
			try {
				writer = response.getWriter();
				writer.write(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
