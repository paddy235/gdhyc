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

import com.zhiren.fuelmis.dc.entity.hetgl.PriceComponent;
import com.zhiren.fuelmis.dc.service.hetgl.PriceComponetService;
import com.zhiren.fuelmis.dc.utils.Constant;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/**
 * 计价组件Controller
 * @author ZY
 *
 */
@Controller
@RequestMapping("hetgl/pricecomponet")
public class PriceComponetController {
	@Autowired
	private PriceComponetService priceComponentService;
	
	
	/**
	 * 新增计价组件
	 * @param info
	 * @param subinfo
	 * @param delinfo
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/addPriceComponet")
	public void addPriceComponet(@RequestParam String info, HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
		@SuppressWarnings("static-access")
		PriceComponent priceComponent = (PriceComponent) json.toBean(json, PriceComponent.class);
		
		//Renyxx renyxx = (Renyxx) session.getAttribute("user");
		//session过期
		//caigddb.setCaozry(renyxx.getMingc());
		//Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
		
		priceComponent.setDiancxxb_id(515l);
		priceComponent.setCreated_by_userid(1444708472160l);
		priceComponent.setCreation_date(DateUtil.getCurrentTime());
		
		JSONArray jsonArray = priceComponentService.addPriceComponet(priceComponent);
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
	@RequestMapping(value = "/updatePriceComponet")
	public void updatePriceComponet(@RequestParam String info, HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
		@SuppressWarnings("static-access")
		PriceComponent priceComponent = (PriceComponent) json.toBean(json, PriceComponent.class);
		JSONArray jsonArray = priceComponentService.updatePriceComponet(priceComponent);
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
	@RequestMapping(value = "/getPriceComponetList")
	public void getPriceComponetList(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("YEWLY", Constant.HETMB);		
		JSONObject json = priceComponentService.getPriceComponetList(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@RequestMapping(value = "/delPriceComponet")
	public void delPriceComponet(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONObject json = priceComponentService.delPriceComponet(map);
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
	@RequestMapping(value = "/editPriceComponet")
	public void editPriceComponet(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
			response.setContentType("text/html;charset=UTF-8");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", id);
			JSONObject json = priceComponentService.getPriceComponetById(map);
			JSONObject result = new JSONObject();
			result.put("priceComponent", json);
			PrintWriter writer  = null;
			try {
				writer = response.getWriter();
				writer.write(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
	
	
}
