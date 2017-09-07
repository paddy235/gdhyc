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

import com.zhiren.fuelmis.dc.entity.common.Fujxx;
import com.zhiren.fuelmis.dc.service.hetgl.HetmbglService;
import com.zhiren.fuelmis.dc.utils.Constant;

@Controller
@RequestMapping("hetgl/hetmb")
public class HetmbglController {

	@Autowired
	private HetmbglService hetmbglService;
	
	
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
		Fujxx fujxx = (Fujxx) json.toBean(json, Fujxx.class);
		JSONArray jsonArray = hetmbglService.updateHetmb(fujxx);
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
		JSONObject json = hetmbglService.getHetmbList(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取所有合同模板字段对象
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getHetmbsubList")
	public void getHetmbsubList(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");	
		JSONObject json = hetmbglService.getHetmbsubList(null);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取全部合同发货订单
	 * @param request
	 * @param response
	 */
//	@RequestMapping(value = "/searchHetmbList")
//	public void searchHetmbList(@RequestParam String diancid,@RequestParam String strdate,@RequestParam String enddate,HttpServletRequest request , HttpServletResponse response) {
//		response.setContentType("text/html;charset=UTF-8");
//		Map<String,Object> map = new HashMap<String, Object>();
//		if(null == strdate || ("").equals(strdate)){
//			strdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
//		}
//		if(null == diancid || "" == diancid ){
//			diancid = "515";
//		}
//		map.put("diancid", diancid);
//		map.put("strdate", strdate);
//		map.put("enddate", enddate);
//		JSONObject json = hetmbService.getHetmbList(map);
//		PrintWriter writer  = null;
//		try {
//			writer = response.getWriter();
//			writer.write(json.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}	
	
	
	/**
	 * 获取全部调度查询
	 * @param request
	 * @param response
	 */
//	@RequestMapping(value = "/getHetmbinfoList")
//	public void getHetmbinfoList(@RequestParam String diancid,@RequestParam String strdate,@RequestParam String enddate, HttpServletRequest request , HttpServletResponse response) {
//		response.setContentType("text/html;charset=UTF-8");
//		if(null == strdate || ("").equals(strdate)){
//			strdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
//		}
//		if(null == diancid || "" == diancid ){
//			diancid = "515";
//		}
//		
//		JSONObject json = hetmbService.getHetmbinfoList(strdate,enddate,diancid);
//		PrintWriter writer  = null;
//		try {
//			writer = response.getWriter();
//			writer.write(json.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 获取全部调度查询
	 * @param request
	 * @param response
	 */
//	@RequestMapping(value = "/getHetmbinfoList1")
//	public void getHetmbinfoList1(HttpServletRequest request , HttpServletResponse response) {
//		response.setContentType("text/html;charset=UTF-8");
//		String strdate = request.getParameter("strdate");
//		String enddate = request.getParameter("enddate");
//		String diancid = request.getParameter("diancid");
//		if(null == strdate || ("").equals(strdate)){
//			strdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
//		}
//		if(null == diancid || "" == diancid ){
//			diancid = "515";
//		}
//		
//		JSONObject json = hetmbService.getHetmbinfoList(strdate,enddate,diancid);
//		PrintWriter writer  = null;
//		try {
//			writer = response.getWriter();
//			writer.write(json.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
	@RequestMapping(value = "/delHetmb")
	public void delHetmb(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONObject json = hetmbglService.delHetmb(map);
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
			JSONObject json = hetmbglService.getHetmbById(map);
			JSONObject result = new JSONObject();
			result.put("fujxx", json);
			PrintWriter writer  = null;
			try {
				writer = response.getWriter();
				writer.write(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	
	
	
	
	
//	@RequestMapping(value = "/getCaigddbsub")
//	public void getCaigddbsub(@RequestParam String subinfo,HttpServletRequest request , HttpServletResponse response) {
//		JSONArray subs = JSONArray.fromObject(subinfo);
//		JSONArray caigddbsubs = hetmbService.getCaigddbsub(subs);
//		JSONObject result = new JSONObject();
//		result.put("caigddbsubs", caigddbsubs);
//		PrintWriter writer  = null;
//		try {
//			writer = response.getWriter();
//			writer.write(result.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	
//	/**
//	 * 获取编辑数据
//	 * @param id
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping(value = "/getGongysById")
//	public void getGongysById(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
//			response.setContentType("text/html;charset=UTF-8");
//			JSONObject gys = hetmbService.getGongysById(id);
//			JSONObject result = new JSONObject();
//			result.put("gongys", gys);
//			PrintWriter writer  = null;
//			try {
//				writer = response.getWriter();
//				writer.write(result.toString());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//	}
//	/**
//	 * 获取编辑数据
//	 * @param id
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping(value = "/getDiancxxById")
//	public void getDiancxxById(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
//			response.setContentType("text/html;charset=UTF-8");
//			JSONObject dianc = hetmbService.getDiancxxById(id);
//			JSONObject result = new JSONObject();
//			result.put("dianc", dianc);
//			PrintWriter writer  = null;
//			try {
//				writer = response.getWriter();
//				writer.write(result.toString());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//	}
	
}
