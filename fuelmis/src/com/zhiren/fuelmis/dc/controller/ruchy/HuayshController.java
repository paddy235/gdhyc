package com.zhiren.fuelmis.dc.controller.ruchy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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

import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.ruchy.IHuayshService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.DateUtil.DateFormatType;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("/ruchy/huaysh")
public class HuayshController {
	
	@Autowired
	private IHuayshService huayshService;
	
	@RequestMapping(value = "/getTableData")
	public void getTableData(@RequestParam(defaultValue="515") String diancid,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		response.setContentType("text/html;charset=UTF-8");
		map.put("diancid", diancid);
		JSONObject jsonObject = huayshService.getTableData(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/updateZT")
	public void updateZT(@RequestParam(defaultValue="") String id,@RequestParam(defaultValue="") String zhuangt,
			HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		response.setContentType("text/html;charset=UTF-8");
		map.put("id", id);
		map.put("zhuangt", zhuangt);
		map.put("shenhry", ((Renyxx)request.getSession().getAttribute("user")).getMingc());
		JSONArray jsonArray = huayshService.updateZT(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getJincpch")
	public void getJincpch(@RequestParam String sDate,@RequestParam String eDate,@RequestParam(defaultValue="515") String diancid,
			HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sDate", "".equals(sDate)?DateUtil.format(new Date(),DateFormatType.SIMPLE_TYPE):sDate);
		map.put("eDate", "".equals(eDate)?DateUtil.format(new Date(),DateFormatType.SIMPLE_TYPE):eDate);
		map.put("diancid", diancid);
		List<Map<String,Object>> list = huayshService.getJincpcList(map);
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("BIANM"), list.get(i)
						.get("ZHILB_ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getTableData2")
	public void getTableData2(@RequestParam(defaultValue="") String zhilb_id,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		response.setContentType("text/html;charset=UTF-8");
		map.put("zhilb_id", zhilb_id);
		JSONObject jsonObject = huayshService.getTableData2(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/updateZT2")
	public void updateZT2(@RequestParam(defaultValue="") String id,@RequestParam(defaultValue="") String zhuangt,
			HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		response.setContentType("text/html;charset=UTF-8");
		map.put("id", id);
		map.put("zhuangt", zhuangt);
		map.put("shenhryej", ((Renyxx)request.getSession().getAttribute("user")).getMingc());
		JSONArray jsonArray = huayshService.updateZT2(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
