package com.zhiren.fuelmis.dc.controller.yuebgl.yuebwh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IZhibqkService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

import net.sf.json.JSONArray;

/** 
 * @author weiw
 * 
 */
@Controller
@RequestMapping("yuebgl/yuebwh/zhibqk")
public class ZhibqkController {
	
	@Autowired
	private IZhibqkService zhibqkService;
	
	@RequestMapping(value = "/getAll")
	public void getAll(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?DateUtil.getDayOfMonth(null).substring(0,7):riq);
		map.put("dianc", "-1".equals(dianc)?"":dianc);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(zhibqkService.getAll(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateAll")
	public void update(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			@RequestParam(defaultValue="") String zhibqkList,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		List<Map<String,Object>> zhiList =JSONArray.fromObject(zhibqkList);
		Map<String, Object> map = new HashMap<String,Object>();
		for (int i = 0; i < zhiList.size(); i++) {
			Map<String, Object> mapf = zhiList.get(i);
			map.put((String) mapf.get("zidm1"), mapf.get("zhi1"));
			map.put((String) mapf.get("zidm2"), mapf.get("zhi2"));
		}
		map.put("riq", "".equals(riq)?DateUtil.getDayOfMonth(null).substring(0,7):riq);
		map.put("dianc", "-1".equals(dianc)?"":dianc);
		

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(zhibqkService.update(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveData")
	public void saveData(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			@RequestParam(defaultValue="") String zhibqkList,@RequestParam(defaultValue="0")String yuezbId,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("yuezbId", yuezbId);
		map.put("riq", "".equals(riq)?DateUtil.getDayOfMonth(null).substring(0,7):riq);
		map.put("dianc", "-1".equals(dianc)?"":dianc);
		List<Map<String,Object>> listzhibqk=JSONArray.fromObject(zhibqkList);
		response.setContentType("text/html;charset=UTF-8");
		zhibqkService.saveData(listzhibqk,map);
	}
	
	@RequestMapping(value = "/delData")
	public void delData(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?DateUtil.getDayOfMonth(null).substring(0,7):riq);
		map.put("dianc", "-1".equals(dianc)?"":dianc);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(zhibqkService.delData(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 累计功能
	 * @param riq
	 * @param dianc
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveLeij")
	public void saveLeij(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?DateUtil.getDayOfMonth(null).substring(0,7):riq);
		map.put("dianc", "-1".equals(dianc)?"":dianc);
		response.setContentType("text/html;charset=UTF-8");
		zhibqkService.saveLeij(map);
	}
}
