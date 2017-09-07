package com.zhiren.fuelmis.dc.controller.jiesgl;

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
import com.zhiren.fuelmis.dc.service.jiesgl.IJiesbbService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("jiesgl/jiesdcx")
public class JiesbbController {
	
	@Autowired
	private IJiesbbService jiesbbService;
	
	@RequestMapping(value = "/getAllJiesbh")
	public void getAllJiesbh(@RequestParam String search,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		Map<String,Object> map = JSONObject.fromObject(search);
		List<String> list = jiesbbService.getAllJiesbh(map);
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox("请选择","-1");
			jsonArray.add(combobox);
			for (int i = 0; i < list.size(); i++) {
				combobox.setValue(list.get(i).toString()); 
				combobox.setName(list.get(i).toString());
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
	@RequestMapping(value = "/gethycJiesd")
	public void gethycJiesd(@RequestParam String search,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		Map<String,Object> map=JSONObject.fromObject(search);
		JSONArray jsonArray = new JSONArray();
		String leix=map.get("chaxlx").toString();
		if(!map.get("jiesbh").equals("-1")){
			if("1".equals(leix)){ //结算单
				jsonArray = jiesbbService.getJiesd(map);
			}
			else if("2".equals(leix)){ //验收明细
				jsonArray = jiesbbService.getYansmx(map);
			}else if("3".equals(leix)){ //过衡单
				jsonArray = jiesbbService.getGuohd(map);
			}else if("4".equals(leix)){ //单批次明细
				jsonArray = jiesbbService.getDanpcmx(map);
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
	
	@RequestMapping(value = "/getJiesd")
	public void getJiesd(@RequestParam String search,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		Map<String,Object> map=JSONObject.fromObject(search);
		JSONArray jsonArray = new JSONArray();
		String leix=map.get("chaxlx").toString();
		if(!map.get("jiesbh").equals("-1")){
			if("1".equals(leix)){ //结算单
				jsonArray = jiesbbService.getJiesd(map);
			}else if("2".equals(leix)){ //验收明细
				jsonArray = jiesbbService.getYansmx(map);
			}else if("3".equals(leix)){ //过衡单
				jsonArray = jiesbbService.getGuohd(map);
			}else if("4".equals(leix)){ //单批次明细
				jsonArray = jiesbbService.getDanpcmx(map);
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
}
