package com.zhiren.fuelmis.dc.controller.yansgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.yansgl.WailyrlService;


/**
 * @author 陈宝露
 */
@Controller
@RequestMapping("/yansgl")
public class WailyrlController {
	
	@Resource
	private WailyrlService wailyrlService;
	
	@RequestMapping(value = "/getWailyrlAll")
	public void getWailyrlAll(HttpServletRequest request,HttpServletResponse response,@RequestParam String riq) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", riq);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		JSONArray json =wailyrlService.getWailyrlAll(map);
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
