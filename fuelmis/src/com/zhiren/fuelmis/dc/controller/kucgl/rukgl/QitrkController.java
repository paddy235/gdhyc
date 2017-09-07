package com.zhiren.fuelmis.dc.controller.kucgl.rukgl;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.kucgl.rukgl.IQitrkService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("/kucgl/rukgl/qitrk")
public class QitrkController {
	@Autowired
	private IQitrkService qitrkService;
	
	@RequestMapping(value = "/getQitrkList")
	public @ResponseBody JSONArray getQitrkList(String search ,HttpServletRequest request ,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = JSONObject.fromObject(search);
		List<Map<String,Object>> list = qitrkService.getQitrkList(map);
		return JSONArray.fromObject(list);
	}


	
	@RequestMapping(value = "getRanlcgrk_WRK_MX2")
	public void getRanlcgrk_WRK_MX2(@RequestParam(defaultValue="") String rukdbh,
			HttpServletRequest request , HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rukdbh",rukdbh);
		JSONObject jsonObject = qitrkService.getRanlcgrk_WRK_MX2(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value="saveQitrk")
	public void saveQitrk(@RequestParam(defaultValue="") String data,HttpServletRequest request , HttpServletResponse response){
		JSONObject d = JSONObject.fromObject(data);
		qitrkService.saveQitrk(d);
	}
	
	
	@RequestMapping(value="ruk")
	public void ruk(@RequestParam(defaultValue="") String rukdbh, HttpServletRequest request , HttpServletResponse response, HttpSession session){
		JSONArray jsonArray = qitrkService.ruk(rukdbh);
		Map<String,Object> map=new HashMap<String,Object>();
		Renyxx user = (Renyxx) session.getAttribute("user");
		map.put("user",user);
		map.put("rukdbh",rukdbh);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="chex")
	public void chex(@RequestParam(defaultValue="") String rukdbh,HttpServletRequest request , HttpServletResponse response){
		JSONArray jsonArray = qitrkService.chex(rukdbh);
		
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="getQitrk")
	public void getQitrk(HttpServletRequest request , HttpServletResponse response){
		JSONObject jsonArray = qitrkService.getQitrk();
		
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "check")
	public void check(@RequestParam String rukdbh,HttpServletRequest request , HttpServletResponse response){
		JSONArray jsonArray = qitrkService.check(rukdbh);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@RequestMapping(value = "deleteYunzf")
	public void deleteYunzf(@RequestParam String id,HttpServletRequest request , HttpServletResponse response){
		qitrkService.deleteYunzf(id);
	}
	
}