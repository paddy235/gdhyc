package com.zhiren.fuelmis.dc.controller.xitgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.xitgl.IChezxxService;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("/chezxx")
public class ChezxxController {
	
	@Autowired
	private IChezxxService chezxxService;
	
	@RequestMapping(value = "/getAll")
	public void getAll(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONObject jsonObject = chezxxService.getAll(null);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/addChezxx")
	public void addChezxx(@RequestParam String info,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		@SuppressWarnings("unchecked")
		Map<String,Object> map = JSONObject.fromObject(info);
		map.put("id", System.currentTimeMillis());
		String leib = map.get("leib")==null?"":map.get("leib").toString();
		map.remove("leib");
		map.put("leib", leib.equals("2")?"港口":"车站");
		JSONArray jsonArray = chezxxService.insertChezxx(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getOne")
	public void getOne(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		JSONArray jsonArray = chezxxService.getOne(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/updateChezxx")
	public void updateChezxx(@RequestParam String info,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		@SuppressWarnings("unchecked")
		Map<String,Object> map = JSONObject.fromObject(info);
		String leib = map.get("leib")==null?"":map.get("leib").toString();
		map.remove("leib");
		map.put("leib", leib.equals("2")?"港口":"车站");
		JSONArray jsonArray = chezxxService.updateChezxx(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/delChezxx")
	public void delChezxx(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		JSONArray jsonArray = chezxxService.deleteChezxx(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
