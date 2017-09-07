package com.zhiren.fuelmis.dc.controller.yuebgl.yuebwh;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IRuczlService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("yuebgl/yuebwh/ruczl")
public class RuczlController {
	@Autowired
	private IRuczlService ruczlService;
	
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
			writer.write(ruczlService.getAll(map).toString());
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
			writer.write(ruczlService.check(map).toString());
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
			writer.write(ruczlService.createData(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/saveData")
	public void saveData(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			@RequestParam(defaultValue="") String ruczlList,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?DateUtil.getDayOfMonth(null):riq+"-01");
		map.put("dianc", "-1".equals(dianc)?"":dianc);
		try {
			ruczlList=new String(ruczlList.getBytes("iso-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		map.put("ruczlList",ruczlList);
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(ruczlService.saveData(map).toString());
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
		map.put("dianc", dianc);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(ruczlService.delData(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
