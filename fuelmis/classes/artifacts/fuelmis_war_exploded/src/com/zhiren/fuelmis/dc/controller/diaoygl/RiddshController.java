package com.zhiren.fuelmis.dc.controller.diaoygl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
import com.zhiren.fuelmis.dc.service.diaoygl.DiaodglService;
import com.zhiren.fuelmis.dc.utils.formular.Result;



@Controller
@RequestMapping("diaoygl/diaodgl")
public class RiddshController {
	@Autowired
	private DiaodglService diaodglService;
	
	@RequestMapping(value = "/getRiddsh")
	public void getRiddsh(@RequestParam(defaultValue="") String condition,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map=JSONObject.fromObject(condition);
		List<Map<String,Object>> list=diaodglService.getRiddsh(map);
		Object[][] data = Result.list2array(list, new String[]{ "ID","JIHDH","RIQ","PINZB_ID","SHUL","GONGYSB_ID","LURY","PIZR","SHENHID"});
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(data).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/shenh")
	public void shenh(HttpSession session,@RequestParam(defaultValue="") String id,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Renyxx user=(Renyxx) session.getAttribute("user");
		diaodglService.shenh(id,user.getId());	
	}
	@RequestMapping(value = "/huit")
	public void huit(HttpSession session,@RequestParam(defaultValue="") String id,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");	
		Renyxx user=(Renyxx) session.getAttribute("user");
		diaodglService.huit(id,user.getId());	
	}

}
