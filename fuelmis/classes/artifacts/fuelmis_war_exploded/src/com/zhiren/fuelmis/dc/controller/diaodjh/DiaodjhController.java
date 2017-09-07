package com.zhiren.fuelmis.dc.controller.diaodjh;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.zhiren.fuelmis.dc.entity.diaodjh.Diaodjh;
import com.zhiren.fuelmis.dc.service.diaodjh.DiaodjhService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;

@Controller
@RequestMapping("/diaodjh")
public class DiaodjhController {

	@Autowired
	private DiaodjhService diaodjhService;
	
	/**
	 * 新增调度计划
	 * @param info
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addDiaodjh")
	public void addDiaodjh(@RequestParam String info,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
		//Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
		@SuppressWarnings("static-access")
		Diaodjh diaodjh = (Diaodjh) json.toBean(json, Diaodjh.class);
		//Renyxx renyxx = (Renyxx) session.getAttribute("user");
		//session过期
		//diaodjh.setLury(renyxx.getId());
		diaodjh.setLury(1444708472160l);
		String riq = diaodjh.getRiq();
		if(riq != null){
			String bianm = diaodjhService.getBianm(riq);
			riq = riq.replaceAll("-", "");
			if(bianm.equals("0")){
				bianm = riq+"1";
			}else{
				bianm = riq + (Long.parseLong(bianm)+1);
			}
			diaodjh.setBianh(bianm);
		}
		
		diaodjh.setLursj(DateUtil.format(new Date()));
		diaodjh.setId(Long.parseLong(Sequence.nextId().toString()));
		JSONArray jsonArray = diaodjhService.addDiaodjh(diaodjh);;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/updateDiaodjh")
	public void updateDiaodjh(@RequestParam String info,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
		@SuppressWarnings("static-access")
		Diaodjh diaodjh = (Diaodjh) json.toBean(json, Diaodjh.class);
		JSONArray jsonArray = diaodjhService.updateDiaodjh(diaodjh);;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * 获取全部调度计划
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getDiaodjhList")
	public void getDiaodjhList(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = diaodjhService.getDiaodjhList(null);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取全部调度计划
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/searchDiaodjhList")
	public void searchDiaodjhList(@RequestParam String diancid,@RequestParam String strdate,@RequestParam String enddate,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		if(null == strdate || ("").equals(strdate)){
			strdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		if(null == diancid || "" == diancid ){
			diancid = "515";
		}
		map.put("diancid", diancid);
		map.put("strdate", strdate);
		map.put("enddate", enddate);
		JSONObject json = diaodjhService.getDiaodjhList(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	
	/**
	 * 获取全部调度查询
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getDiaodjhinfoList")
	public void getDiaodjhinfoList(@RequestParam String diancid,@RequestParam String strdate,@RequestParam String enddate, HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		if(null == diancid || "" == diancid ){
			diancid = "515";
		}
		
		JSONObject json = diaodjhService.getDiaodjhinfoList(strdate,enddate,diancid);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取全部调度查询
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getDiaodjhinfoList1")
	public void getDiaodjhinfoList1(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String strdate = null;
		String enddate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		String diancid =  "515";
		
		JSONObject json = diaodjhService.getDiaodjhinfoList(strdate,enddate,diancid);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/delDiaodjh")
	public void delDiaodjh(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONArray json = diaodjhService.delDiaodjh(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取编辑数据
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/editDiaodjh")
	public void editDiaodjh(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
			response.setContentType("text/html;charset=UTF-8");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", id);
			JSONObject json = diaodjhService.getDiaodById(map);
			PrintWriter writer  = null;
			try {
				writer = response.getWriter();
				writer.write(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
