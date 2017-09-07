package com.zhiren.fuelmis.dc.controller.yuebgl.yuebwh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IRulrzService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("yuebgl/yuebwh/rulrz")
public class RulrzController {
	
	@Autowired
	private IRulrzService rulrzService;
	
	@RequestMapping(value = "/getAll")
	public void getAll(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", riq);
		map.put("dianc", dianc);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(rulrzService.getAll(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			writer.write(rulrzService.check(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			writer.write(rulrzService.createData(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/saveData")
	public void saveData(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			@RequestParam(defaultValue="") String rucslList,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?DateUtil.getDayOfMonth(null):riq+"-01");
		map.put("dianc", "-1".equals(dianc)?"":dianc);
		map.put("rucslList",rucslList);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(rulrzService.saveData(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/delData")
	public void delData(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", riq);
		map.put("dianc",dianc);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(rulrzService.delData(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
