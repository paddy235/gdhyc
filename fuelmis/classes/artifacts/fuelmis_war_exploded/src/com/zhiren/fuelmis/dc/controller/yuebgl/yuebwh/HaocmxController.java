package com.zhiren.fuelmis.dc.controller.yuebgl.yuebwh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IHaocmxService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("yuebgl/yuebwh/haocmx")
public class HaocmxController { 
	
	@Autowired
	private IHaocmxService haocmxService;
	
	@RequestMapping(value = "/getAll")
	public void getAll(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", riq);
		map.put("dianc",dianc);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(haocmxService.getAll(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/check")
	public void check(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?DateUtil.getDayOfMonth(null):riq+"-01");
		map.put("dianc", "-1".equals(dianc)?"":dianc);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(haocmxService.check(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/createData")
	public void createData(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", riq);
		map.put("dianc", dianc);
		response.setContentType("text/html;charset=UTF-8");
			
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(haocmxService.createData(map)+"");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/saveData")
	public void saveData(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			@RequestParam(defaultValue="") String haocmxList,
			HttpServletRequest request,HttpServletResponse response)  {
		response.setContentType("text/html;charset=UTF-8");
		try {

			request.setCharacterEncoding("utf-8");
			List<Map<String,Object>> list = JSONArray.fromObject(haocmxList);
			PrintWriter writer = null;
			writer = response.getWriter();
			writer.write(haocmxService.saveData(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/delData")
	public void delData(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq",riq);
		map.put("dianc", dianc);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(haocmxService.delData(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
