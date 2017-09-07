package com.zhiren.fuelmis.dc.controller.jiesgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.impl.jiesgl.YuejstjtzServiceImpl;

@Controller
@RequestMapping("jiesgl/jiesdcx")
public class YuejstjtzController {
	@Resource
	private YuejstjtzServiceImpl yuejstjtzService;

	@RequestMapping(value = "/getHetbh")
	public void getHetbh(HttpServletRequest request,HttpServletResponse response,@RequestParam String search,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		Map<String , Object> map = JSONObject.fromObject(search);
		List<Map<String,Object>> list = yuejstjtzService.getHetbh(map);
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(list).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/getYuejstjtz")
	public void getYuejstjtz(HttpServletRequest request,HttpServletResponse response,@RequestParam String search,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		Map<String , Object> map = JSONObject.fromObject(search);
		JSONArray json = yuejstjtzService.getYuejstjtz(map);
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
