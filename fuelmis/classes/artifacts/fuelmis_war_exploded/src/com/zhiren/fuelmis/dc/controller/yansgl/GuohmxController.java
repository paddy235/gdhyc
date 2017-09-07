package com.zhiren.fuelmis.dc.controller.yansgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.zhiren.fuelmis.dc.service.yansgl.GuohmxService;
import com.zhiren.fuelmis.dc.service.yansgl.ShulhjblService;
import com.zhiren.fuelmis.dc.service.yansgl.WailyrlService;


/**
 * @author 陈宝露
 */
@Controller
@RequestMapping("/yansgl")
public class GuohmxController {
	
	@Resource
	private GuohmxService guohmxService;
	
	@RequestMapping(value = "/getGuohmxAll")
	public void getGuohmxAll(HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		 String sDate= request.getParameter("sDate");
		 String eDate = request.getParameter("eDate");
		 String gongys_id = request.getParameter("gongys_id");
		 String meikxxb_id = request.getParameter("meikxxb_id");
		if(null == sDate || ("").equals(sDate)){
			sDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		if(null == eDate || ("").equals(eDate)){
			eDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(guohmxService.getGuohmxAll(sDate,meikxxb_id,eDate,gongys_id).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}		
		
}
