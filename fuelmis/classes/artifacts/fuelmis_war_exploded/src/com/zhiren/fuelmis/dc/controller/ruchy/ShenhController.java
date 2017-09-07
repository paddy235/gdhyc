package com.zhiren.fuelmis.dc.controller.ruchy;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.ruchy.IShenhService;
import com.zhiren.fuelmis.dc.utils.IPUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;

/** 
 * @author rain 刘志宇
 */
@Controller
@RequestMapping("/shenh")
public class ShenhController {
	@Autowired
	private IShenhService shenhService;
	@RequestMapping(value = "/getHuayd")
	public void getHuayd(@RequestParam(defaultValue = "") String condition,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		@SuppressWarnings("unchecked")
		Map<String, Object> conditionMap = JSONObject.fromObject(condition);
		int size=15;//页面大小
		int pages=0;
		conditionMap.put("size", size);
		List<Map<String,Object>> list = shenhService.getHuayd(conditionMap);
		Integer count=shenhService.getCount(conditionMap);
		if(count>size){
			if(count>size&&count%2==0){
				pages=count/size;
			}else{
				pages=count/size+1;
			}
		}else{
			pages=1;
		}		
		Map<String, Object> dataMap = new HashMap<String,Object>();
		dataMap.put("list", list);
		dataMap.put("pages", pages);
		dataMap.put("count", count);
		try {
			writer = response.getWriter();
			writer.write(JSONObject.fromObject(dataMap).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/getHuaydz")
	public void getHuaydz(@RequestParam(defaultValue = "") String condition,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		@SuppressWarnings("unchecked")
		Map<String, Object> conditionMap = JSONObject.fromObject(condition);
		List<Map<String,Object>> list = shenhService.getHuaydz(conditionMap);
		Object[] array = list.toArray();
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(array).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/getJiesxxList")
	public void getJiesxxList(@RequestParam(defaultValue = "") String huaybm,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		try{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = null;
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> list = shenhService.getJiesxxList(huaybm);
			try {
				writer = response.getWriter();
				writer.write(JSONArray.fromObject(list).toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}

	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/update")
	public void update(@RequestParam(defaultValue = "") String data,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		Renyxx renyxx=(Renyxx)session.getAttribute("user");
		String LURY=renyxx.getMingc();
		List<Map<String, Object>> a = JSONArray.fromObject(data);
		shenhService.update(a,LURY);
	}
	@RequestMapping(value = "/shenhHuaysj")
	public void shenhHuaysj(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		Renyxx renyxx=(Renyxx)session.getAttribute("user");
		String xiugry=renyxx.getQuanc();
		String id = request.getParameter("id");
//		String zhuangt_s = request.getParameter("zhuangt");
//		Integer zhuangt_i=Integer.parseInt(zhuangt_s)+1;//审核状态加1
//		String zhuangt=zhuangt_i.toString();
		String zhuangt = "2";
		shenhService.shenh(id,zhuangt,xiugry);
		String ip=IPUtil.ipConfig();
		
		//记录历史
		Map<String, Object> map =new HashMap<String,Object>();
		map.put("HUAYD_ID", id);
		map.put("ID",Sequence.nextId());
		map.put("CAOZRY", renyxx.getMingc());
		map.put("IP", ip);
		
//		if(zhuangt_s.equals("0")){
//			map.put("CAOZLX", "一级审核");
//			map.put("YEWHJ", "验收管理-入场质量-一级审核");
//		}else{
//			map.put("CAOZLX", "二级审核");
//			map.put("YEWHJ", "验收管理-入场质量-二级审核");
//		}
		map.put("CAOZLX", "一级审核");
		map.put("YEWHJ", "验收管理-入场质量-一级审核");
		shenhService.huaydLog(map);
	}
	@RequestMapping(value = "/huitHuaysj")
	public void huitHuaysj(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		try {
			response.setContentType("text/html;charset=UTF-8");
//			String zhuangt = request.getParameter("zhuangt");
			String zhuangt = "1";
			String id = request.getParameter("id");

			//记录历史
			String ip = IPUtil.ipConfig();
			Renyxx renyxx = (Renyxx) session.getAttribute("user");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("HUAYD_ID", id);
			map.put("ID", Sequence.nextId());
			map.put("CAOZRY", renyxx.getMingc());
			map.put("IP", ip);
			map.put("CAOZLX", "一级回退");
			map.put("YEWHJ", "验收管理-入场质量-一级审核");
//			if (zhuangt.equals("0")) {
//				map.put("CAOZLX", "一级回退");
//				map.put("YEWHJ", "验收管理-入场质量-一级审核");
//			} else {
//				map.put("CAOZLX", "二级回退");
//				map.put("YEWHJ", "验收管理-入场质量-二级审核回退");
//			}
			shenhService.huaydLog(map);
			shenhService.huit(id, zhuangt);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}


}
