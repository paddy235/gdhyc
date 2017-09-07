package com.zhiren.fuelmis.dc.controller.diaoygl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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

import com.zhiren.fuelmis.dc.service.diaoygl.IRibglService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("diaoygl/ribgl")
public class RibglController {
	
	@Autowired
	private IRibglService ribglService;

	@RequestMapping(value = "/getAll")
	public void getAll(@RequestParam(defaultValue="515") String diancxxb_id,@RequestParam String riq,
			HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("diancxxb_id", diancxxb_id);
		map.put("riq", "".equals(riq)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE):riq);
		JSONArray jsonArray = ribglService.getAll(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getAll2")
	public void getAll2(@RequestParam(defaultValue="515") String diancxxb_id,@RequestParam String riq,
			HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("diancxxb_id", diancxxb_id);
		map.put("riq", "".equals(riq)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE):riq);
		JSONArray jsonArray = ribglService.getAll2(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/createData")
	public void createData(@RequestParam(defaultValue="515") String diancxxb_id,@RequestParam String riq,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("diancxxb_id", diancxxb_id);
		map.put("riq", "".equals(riq)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE):riq);
		JSONArray jsonArray = ribglService.createData(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/createData2")
	public void createData2(@RequestParam(defaultValue="515") String diancxxb_id,@RequestParam String riq,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("diancxxb_id", diancxxb_id);
		map.put("riq", "".equals(riq)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE):riq);
		JSONArray jsonArray = ribglService.createData2(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/save")
	public void save(@RequestParam String strList,
			HttpServletRequest request,HttpServletResponse response) {
		JSONArray jsonArray = ribglService.save(strList);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/save2")
	public void save2(@RequestParam String strList,
			HttpServletRequest request,HttpServletResponse response) {
		JSONArray jsonArray = ribglService.save2(strList);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping(value = "/getRibtbShangc")
	public void getRibtbShangc(HttpServletRequest request,HttpServletResponse response,@RequestParam String riq) {
		response.setContentType("text/html;charset=UTF-8");
		
		JSONArray json = ribglService.getRibtbAll(riq);
		try{
			JSONArray jsonArray = JSONArray.fromObject(json);
			ribglService.RibtbShangc(jsonArray);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/getRibgsShangc")
	public void getRibgsShangc(HttpServletRequest request,HttpServletResponse response,@RequestParam String search) {
		response.setContentType("text/html;charset=UTF-8");
//		JSONArray json = ribglService.getRibgsAll(riq);
		Map<String,Object> m=JSONObject.fromObject(search);
		try{
			ribglService.RibgsShangc(m);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
