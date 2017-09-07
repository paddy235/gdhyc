package com.zhiren.fuelmis.dc.controller.gongyspg.pingggl;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.zhiren.fuelmis.dc.service.gongyspg.pingggl.IRizbpfService;




/**
 * 
 * @author 刘志宇
 * @time 2016年1月22日 上午9:36:44
 */
@Controller
@RequestMapping("gongyspg/pingggl/rizbpf")
public class RizbpfController {

	@Autowired
	private IRizbpfService rizbpfService;
	/**
	 * 查询日指标评分
	 * @param condition
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/getRizbpf")
	public void getRizbpf(@RequestParam String condition,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;		
		@SuppressWarnings("unchecked")
		Map<String, Object> map=JSONObject.fromObject(condition);
		List<Map<String,Object>> l=rizbpfService.getRizbpf(map);
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(l).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/computeScore")
	public void computeScore(@RequestParam String data,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list=JSONArray.fromObject(data);
		Renyxx renyxx = (Renyxx) session.getAttribute("user");
		String msg=rizbpfService.jis(list,renyxx);	
		try {
			writer = response.getWriter();
			writer.write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/fab")
	public void fab(@RequestParam String data,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list=JSONArray.fromObject(data);
		rizbpfService.fab(list);	
	}
	@RequestMapping(value = "/fabCancel")
	public void fabCancel(@RequestParam String data,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		List<String> list=JSONArray.fromObject(data);
		rizbpfService.fabCancel(list);	
	}
}
