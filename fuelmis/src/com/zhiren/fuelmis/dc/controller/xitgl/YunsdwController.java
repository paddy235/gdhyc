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

import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.service.xitgl.IYunsdwService;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("/yunsdw")
public class YunsdwController {
	@Autowired
	private IYunsdwService yunsdwService;
	
	@RequestMapping(value = "/getAll")
	public void getAll(@RequestParam(defaultValue="") String dianc,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", "-1".equals(dianc)?"":dianc);
		JSONObject jsonObject = yunsdwService.getAll(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/addYunsdw")
	public void addYunsdw(@RequestParam String info,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		@SuppressWarnings("unchecked")
		Map<String,Object> map = JSONObject.fromObject(info);
		map.put("id", System.currentTimeMillis());
		map.put("diancxxb_id", ((Diancxx)request.getSession().getAttribute("diancxx")).getId());
		JSONArray jsonArray = yunsdwService.insertYunsdw(map);
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
		JSONArray jsonArray = yunsdwService.getOne(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/updateYunsdw")
	public void updateYunsdw(@RequestParam String info,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		@SuppressWarnings("unchecked")
		Map<String,Object> map = JSONObject.fromObject(info);
		JSONArray jsonArray = yunsdwService.updateYunsdw(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
