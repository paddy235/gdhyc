package com.zhiren.fuelmis.dc.controller.rulgl;

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
import com.zhiren.fuelmis.dc.service.rulgl.IXianggwhService;

/**
 * 
 * @author 刘志宇
 * 
 */
@Controller
@RequestMapping("rulgl/xianggwh")
public class XianggwhController {

	@Autowired
	private IXianggwhService xianggwhService;

	@RequestMapping(value = "/getMeiHy")
	public void getMeiHy(@RequestParam(defaultValue = "") String riq,@RequestParam(defaultValue = "") String diancxxb_id,HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONObject meiHy = xianggwhService.getMeiHy(riq,diancxxb_id);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(meiHy.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/updateMeihy")
	public void updateMeihy(@RequestParam String data,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = JSONArray.fromObject(data);
		xianggwhService.updateMeihy(list);
	}



	@RequestMapping(value = "/getMeiHysis")
	public void getMeiHySiS(@RequestParam String riq,HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");

		List<Map<String,Object>> meiHy = xianggwhService.getMeiHySiS(riq);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(meiHy).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getRulmpp")
	public void getRulmpp(@RequestParam String riq,@RequestParam String diancxxbID,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");

		JSONObject meiHy = xianggwhService.getRulmpp(riq,diancxxbID);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(meiHy.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/delMeiHy")
	public void delMeiHy(@RequestParam String ids,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		List<Object> idList=JSONArray.fromObject(ids);
		int ret = xianggwhService.delMeiHy(idList);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/updateHuaybm")
	public void updateHuaybm(@RequestParam String data,
			HttpServletRequest request, HttpServletResponse response) {
	JSONArray array = JSONArray.fromObject(data);
	xianggwhService.updateHuaybm(array);
	}
	
	
	@RequestMapping(value = "/getMeihyAll")
	public void getMeihyAll(HttpServletRequest request,HttpServletResponse response,@RequestParam String Date) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("Date", Date);
		
		JSONArray json = (JSONArray) xianggwhService.getMeihyAll(map);
		PrintWriter writer=null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/MeihyAdd")
	public void MeihyAdd(HttpServletRequest request,HttpServletResponse response,@RequestParam String data) {
		response.setContentType("text/html;charset=UTF-8");
		try{
			JSONArray jsonArray = JSONArray.fromObject(data);
			xianggwhService.MeihyAdd(jsonArray);
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	
	@RequestMapping(value  = "/DelMeihy")
	public void DelMeihy(HttpServletRequest request,HttpServletResponse response,@RequestParam int id){
		response.setContentType("text/html;charset=UTF-8");
		try{
			xianggwhService.DelMeihy(id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
