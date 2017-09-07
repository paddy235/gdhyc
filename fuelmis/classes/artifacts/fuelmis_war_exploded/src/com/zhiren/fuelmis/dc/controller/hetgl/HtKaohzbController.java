package com.zhiren.fuelmis.dc.controller.hetgl;

import com.zhiren.fuelmis.dc.service.hetgl.HtKaohzbService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 考核指标Controller
 * @author mjy
 *
 */
@Controller
@RequestMapping("hetgl/hetkhzb")
public class HtKaohzbController {

	@Autowired
	private HtKaohzbService kaohzbService;
	
	
	/**
	 * 增加考核指标
	 * @param info
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/addKaohzb")
	public void addKaohzb(@RequestParam String info, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
//		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
//		@SuppressWarnings("static-access")
//		Hetkhzb Kaohzb = (Hetkhzb) json.toBean(json, Hetkhzb.class);
		
		//Renyxx renyxx = (Renyxx) session.getAttribute("user");
		//session过期
		//caigddb.setCaozry(renyxx.getMingc());
		//Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
		
//		Kaohzb.setCaigddb_id(id);
		JSONArray kaohzbinfo = JSONArray.fromObject(info);
		JSONArray jsonArray = kaohzbService.addKaohzb(kaohzbinfo);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	
	/**
	 * 更新考核指标
	 * @param info
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/updateKaohzb")
	public void updateKaohzb(@RequestParam String info,@RequestParam String caigddb_id,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
        JSONArray kaohzbinfo = JSONArray.fromObject(info);
        try{
            kaohzbService.updateKaohzb(kaohzbinfo,caigddb_id);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }


	}
	
	
	/**
	 * 获取全部考核指标
	 * @param request
	 * @param response
	 */
//	@RequestMapping(value = "/getKaohzbList")
//	public void getKaohzbList(HttpServletRequest request , HttpServletResponse response) {
//		response.setContentType("text/html;charset=UTF-8");
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("YEWLY", Constant.HETMB);		
//		JSONObject json = kaohzbService.getKaohzbList(map);
//		PrintWriter writer  = null;
//		try {
//			writer = response.getWriter();
//			writer.write(json.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	

	@RequestMapping(value = "/delKaohzb")
	public void delKaohzb(@RequestParam String id, HttpServletRequest request , HttpServletResponse response) {
		
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONObject json = kaohzbService.delKaohzb(map);
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
//	@RequestMapping(value = "/editKaohzb")
//	public void editKaohzb(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
//			response.setContentType("text/html;charset=UTF-8");
//			Map<String,Object> map = new HashMap<String, Object>();
//			map.put("id", id);
//			JSONObject json = kaohzbService.getKaohzbById(map);
//			JSONObject result = new JSONObject();
//			result.put("Kaohzb", json);
//			PrintWriter writer  = null;
//			try {
//				writer = response.getWriter();
//				writer.write(result.toString());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//	}
}
