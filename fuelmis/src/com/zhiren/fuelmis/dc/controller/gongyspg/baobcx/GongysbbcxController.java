package com.zhiren.fuelmis.dc.controller.gongyspg.baobcx;


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
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiren.fuelmis.dc.service.gongyspg.baobcx.IGongysbbcxService;



/**
 * 
 * @author ZY
 *
 */
@Controller
@RequestMapping("gongys/baobcx")
public class GongysbbcxController {

	@Autowired
	private IGongysbbcxService gongysbbcxService;
	
	/**
	 * 根据日期生成发货表数据
	 * @param date
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/getGongysypfbb")
	public @ResponseBody JSONArray getGongysypfbb(@RequestParam String search,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map=JSONObject.fromObject(search);
		JSONArray json = gongysbbcxService.getGongysypfbb(map);
		return json;
	}
	
}
