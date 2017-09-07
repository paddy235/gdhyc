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

import com.zhiren.fuelmis.dc.entity.gongyspg.pingffa.Pingffa;
import com.zhiren.fuelmis.dc.service.gongyspg.pingffa.PingffaService;

/**
 * 
 * @author ZY
 *
 */
@Controller
@RequestMapping("gongyspg/pingffa")
public class PingffaController {

	
	@Autowired
	private PingffaService pingffaService;
	
	/**
	 * 新增评分标准
	 * @param info
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addPingffa")
	public void addPingffa(@RequestParam String info,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
//		Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
		@SuppressWarnings("static-access")
		Pingffa pingffa = (Pingffa) json.toBean(json, Pingffa.class);
//		if(diancxx.getId()!=null){
//			Pingffa.setDiancxxb_id(diancxx.getId());
//		}else{
//			pingffa.setDiancxxb_id(515);
//		}
		JSONArray jsonArray = pingffaService.addPingffa(pingffa);;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/updatePingffa")
	public void updatePingffa(@RequestParam String info,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
		@SuppressWarnings("static-access")
		Pingffa pingffa = (Pingffa) json.toBean(json, Pingffa.class);
		JSONArray jsonArray = pingffaService.updatePingffa(pingffa);;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 获取全部评分标准
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getPingffaList")
	public void getPingffaList(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = pingffaService.getPingffaList(null);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/delPingffa")
	public void delPingffa(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONArray json = pingffaService.delPingffa(map);
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
	@RequestMapping(value = "/editPingffa")
	public void editPingffa(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
			response.setContentType("text/html;charset=UTF-8");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", id);
			JSONObject json = pingffaService.getPingffaById(map);
			PrintWriter writer  = null;
			try {
				writer = response.getWriter();
				writer.write(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
