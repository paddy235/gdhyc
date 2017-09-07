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

import com.zhiren.fuelmis.dc.entity.hetgl.PriceItem;
import com.zhiren.fuelmis.dc.service.hetgl.PriceItemService;
import com.zhiren.fuelmis.dc.utils.Constant;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/**
 * 计价指标Controller
 * @author ZY
 *
 */
@Controller
@RequestMapping("hetgl/priceitem")
public class PriceItemController {
	@Autowired
	private PriceItemService priceItemService;
	
	
	/**
	 * 新增计价组件
	 * @param info
	 * @param subinfo
	 * @param delinfo
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/addPriceItem")
	public void addPriceItem(@RequestParam String info, HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
		@SuppressWarnings("static-access")
		PriceItem priceItem = (PriceItem) json.toBean(json, PriceItem.class);
		
		//Renyxx renyxx = (Renyxx) session.getAttribute("user");
		//session过期
		//caigddb.setCaozry(renyxx.getMingc());
		//Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
		
		priceItem.setDiancxxb_id(515l);
		priceItem.setCreated_by_userid(1444708472160l);
		priceItem.setCreation_date(DateUtil.getCurrentTime());
		
		JSONArray jsonArray = priceItemService.addPriceItem(priceItem);
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
	@RequestMapping(value = "/updatePriceItem")
	public void updatePriceItem(@RequestParam String info, HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
		@SuppressWarnings("static-access")
		PriceItem priceItem = (PriceItem) json.toBean(json, PriceItem.class);
		JSONArray jsonArray = priceItemService.updatePriceItem(priceItem);
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
	@RequestMapping(value = "/getPriceItemList")
	public void getPriceItemList(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("YEWLY", Constant.HETMB);		
		JSONObject json = priceItemService.getPriceItemList(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@RequestMapping(value = "/delPriceItem")
	public void delPriceItem(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONObject json = priceItemService.delPriceItem(map);
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
	@RequestMapping(value = "/editPriceItem")
	public void editPriceItem(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
			response.setContentType("text/html;charset=UTF-8");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", id);
			JSONObject json = priceItemService.getPriceItemById(map);
			JSONObject result = new JSONObject();
			result.put("priceItem", json);
			PrintWriter writer  = null;
			try {
				writer = response.getWriter();
				writer.write(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
