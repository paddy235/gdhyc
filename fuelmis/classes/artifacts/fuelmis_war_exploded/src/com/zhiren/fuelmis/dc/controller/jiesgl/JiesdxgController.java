package com.zhiren.fuelmis.dc.controller.jiesgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.jiesgl.IJiesdxgService;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("jiesgl/jiesdxg")
public class JiesdxgController {

	@Autowired
	private IJiesdxgService jiesdxgService;
	
	@RequestMapping(value = "/getJiesdbh")
	public void getJiesdbh(@RequestParam(defaultValue="515") String dianc,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dianc", dianc);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jiesdxgService.getJiesdbh(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getJiesd")
	public void getJiesd(@RequestParam(defaultValue="") String jiesdid,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("jiesdid", jiesdid);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jiesdxgService.getJiesd(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/save")
	public void save(@RequestParam(defaultValue="") String id,@RequestParam(defaultValue="") String data,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> m=JSONObject.fromObject(data);
		m.put("ID", id);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jiesdxgService.save(m).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/delete")
	public void delete(@RequestParam(defaultValue="") String jiesdid,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("jiesdid", jiesdid);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jiesdxgService.delete(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/submit")
	public void submit(@RequestParam(defaultValue="") String jiesdid,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("jiesdid", jiesdid);
		Diancxx diancxx = (Diancxx) request.getSession().getAttribute("diancxx");
		map.put("diancxx", diancxx);
		Renyxx renyxx = (Renyxx) request.getSession().getAttribute("user");
		map.put("renyxx", renyxx);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jiesdxgService.submit(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/check")
	public void check(@RequestParam(defaultValue="") String jiesdid,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("jiesdid", jiesdid);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jiesdxgService.check(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
