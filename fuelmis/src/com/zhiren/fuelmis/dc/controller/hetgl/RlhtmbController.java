package com.zhiren.fuelmis.dc.controller.hetgl;
import java.io.IOException;
import java.io.PrintWriter;
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

import com.zhiren.fuelmis.dc.entity.hetgl.Rlhtmb;
import com.zhiren.fuelmis.dc.service.hetgl.RlhtmbService;
import com.zhiren.fuelmis.dc.utils.Constant;
import com.zhiren.fuelmis.dc.utils.DateUtil;


@Controller
@RequestMapping("hetgl/rlhtmb")
public class RlhtmbController {
	@Autowired
	private RlhtmbService rlhtmbService;
	
	
	/**
	 * 新增合同模板
	 * @param info
	 * @param subinfo
	 * @param delinfo
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/addHetmb")
	public void addHetmb(@RequestParam String info, HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
		@SuppressWarnings("static-access")
		Rlhtmb hetmb = (Rlhtmb) json.toBean(json, Rlhtmb.class);
		
		//Renyxx renyxx = (Renyxx) session.getAttribute("user");
		//session过期
		//caigddb.setCaozry(renyxx.getMingc());
		//Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
		
		hetmb.setDiancxxb_id(515l);
		hetmb.setCaozry(1444708472160l);
		hetmb.setCaozsj(DateUtil.getCurrentTime());
		
		JSONArray jsonArray = rlhtmbService.addHetmb(hetmb);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	
	/**
	 * 更新合同模板
	 * @param info
	 * @param subinfo
	 * @param delinfo
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/updateHetmb")
	public void updateHetmb(@RequestParam String info, HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
		@SuppressWarnings("static-access")
		Rlhtmb hetmb = (Rlhtmb) json.toBean(json, Rlhtmb.class);
		JSONArray jsonArray = rlhtmbService.updateHetmb(hetmb);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取全部合同发货订单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getHetmbList")
	public void getHetmbList(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("YEWLY", Constant.HETMB);		
		JSONObject json = rlhtmbService.getHetmbList(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@RequestMapping(value = "/delHetmb")
	public void delHetmb(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONObject json = rlhtmbService.delHetmb(map);
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
	@RequestMapping(value = "/editHetmb")
	public void editHetmb(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
			response.setContentType("text/html;charset=UTF-8");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", id);
			JSONObject json = rlhtmbService.getHetmbById(map);
			JSONObject result = new JSONObject();
			result.put("hetmb", json);
			PrintWriter writer  = null;
			try {
				writer = response.getWriter();
				writer.write(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
	/**
	 * 通过合同id获取合同模板
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getHetmbByhtId")
	public void getHetmbByhtId(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
			response.setContentType("text/html;charset=UTF-8");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", id);
			JSONObject json = rlhtmbService.getHetmbByhtId(map);
			PrintWriter writer  = null;
			try {
				writer = response.getWriter();
				writer.write(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}	

}
