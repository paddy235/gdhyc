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

import com.zhiren.fuelmis.dc.entity.hetgl.PriceQalityRange;
import com.zhiren.fuelmis.dc.service.hetgl.PriceQalityRangeService;
import com.zhiren.fuelmis.dc.utils.Constant;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/**
 * 质量范围计价组件Controller
 * @author ZY
 *
 */
@Controller
@RequestMapping("hetgl/priceqalityrange")
public class PriceQalityRangeController {

	
	@Autowired
	private PriceQalityRangeService priceQalityRangeService;
	
	
	/**
	 * 新增计价组件
	 * @param info
	 * @param subinfo
	 * @param delinfo
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/addPriceQalityRange")
	public void addPriceQalityRange(@RequestParam String info, HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
		@SuppressWarnings("static-access")
		PriceQalityRange priceQalityRange = (PriceQalityRange) json.toBean(json, PriceQalityRange.class);
		
		//Renyxx renyxx = (Renyxx) session.getAttribute("user");
		//session过期
		//caigddb.setCaozry(renyxx.getMingc());
		//Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
		
		priceQalityRange.setDiancxxb_id(515l);
		priceQalityRange.setCreated_by_userid(1444708472160l);
		priceQalityRange.setCreation_date(DateUtil.getCurrentTime());
		
		JSONArray jsonArray = priceQalityRangeService.addPriceQalityRange(priceQalityRange);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	
	/**
	 * 更新计价组件
	 * @param info
	 * @param subinfo
	 * @param delinfo
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/updatePriceQalityRange")
	public void updatePriceQalityRange(@RequestParam String info, HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
		@SuppressWarnings("static-access")
		PriceQalityRange priceQalityRange = (PriceQalityRange) json.toBean(json, PriceQalityRange.class);
		JSONArray jsonArray = priceQalityRangeService.updatePriceQalityRange(priceQalityRange);
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
	@RequestMapping(value = "/getPriceQalityRangeList")
	public void getPriceQalityRangeList(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("YEWLY", Constant.HETMB);		
		JSONObject json = priceQalityRangeService.getPriceQalityRangeList(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@RequestMapping(value = "/delPriceQalityRange")
	public void delPriceQalityRange(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONObject json = priceQalityRangeService.delPriceQalityRange(map);
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
	@RequestMapping(value = "/editPriceQalityRange")
	public void editPriceQalityRange(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
			response.setContentType("text/html;charset=UTF-8");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", id);
			JSONObject json = priceQalityRangeService.getPriceQalityRangeById(map);
			JSONObject result = new JSONObject();
			result.put("priceQalityRange", json);
			PrintWriter writer  = null;
			try {
				writer = response.getWriter();
				writer.write(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 新增计价组件
	 * @param info
	 * @param subinfo
	 * @param delinfo
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/addPriceQalityRanges")
	public void addPriceQalityRanges(@RequestParam String info, HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		JSONArray jsonArray = JSONArray.fromObject(info);
		//获取电厂信息		
		//Renyxx renyxx = (Renyxx) session.getAttribute("user");
		//session过期
		//caigddb.setCaozry(renyxx.getMingc());
		//Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
		Long diancxxb_id = 515l;
		Long userId= 1444708472160l;
		//priceQalityRange.setDiancxxb_id(515l);
		//priceQalityRange.setCreated_by_userid(1444708472160l);
		//priceQalityRange.setCreation_date();
		
		JSONArray result = priceQalityRangeService.addPriceQalityRanges(jsonArray,diancxxb_id,userId);
		try {
			writer = response.getWriter();
			writer.write(result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
}
