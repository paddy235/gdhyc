package com.zhiren.fuelmis.dc.controller.yansgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.yansgl.ShulhjblService;
import com.zhiren.fuelmis.dc.service.yansgl.WailyrlService;


/**
 * @author 陈宝露
 */
@Controller
@RequestMapping("/yansgl")
public class ShulhjblController {
	
	@Resource
	private ShulhjblService shulhjblService;
	
	@RequestMapping(value = "/getHenjblAll")
	public void getHenjblAll(HttpServletRequest request,HttpServletResponse response,@RequestParam String search) {
		Map<String, Object> map = JSONObject.fromObject(search);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		JSONArray json =shulhjblService.getHenjblAll(map);
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
