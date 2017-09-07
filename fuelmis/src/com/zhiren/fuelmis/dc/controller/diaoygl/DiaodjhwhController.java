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
import com.zhiren.fuelmis.dc.service.diaoygl.IDiaodjhwhService;
import com.zhiren.fuelmis.dc.utils.formular.Result;



@Controller
@RequestMapping("diaoygl/diaodgl")
public class DiaodjhwhController {
	@Autowired
	private IDiaodjhwhService diaodjhwhService;
	

	
	@RequestMapping(value = "/getDiaodjhList")
	public void getDiaodjhList(@RequestParam(defaultValue="") String condition,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map=JSONObject.fromObject(condition);
		List<Map<String,Object>> list=diaodjhwhService.getDiaodjhList(map);
		Object[][] data = Result.list2array(list, new String[]{ "ID","JIHDH","RIQ","GONGYS","PINZ","SHUL","ZHUANGT","LURR"});
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(data).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	@RequestMapping(value = "/insertDiaodjh")
	public void insertDiaodjh(@RequestParam(defaultValue="") String data,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		Renyxx user=(Renyxx) session.getAttribute("user");
		Map<String,Object> map=JSONObject.fromObject(data);
		map.put("LURR_ID", user.getId());
		diaodjhwhService.insertDiaodjh(map);
	}
	@RequestMapping(value = "/getDiaodjh")
	public void getDiaodjh(@RequestParam(defaultValue="") String id,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map=diaodjhwhService.getDiaodjh(id);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(JSONObject.fromObject(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/deleteDiaodjhSub")
	public void deleteDiaodjhSub(@RequestParam(defaultValue="") String id,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		diaodjhwhService.deleteDiaodjhSub(id);
	}
	@RequestMapping(value = "/deleteDiaodjh")
	public void deleteDiaodjh(@RequestParam(defaultValue="") String id,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		diaodjhwhService.deleteDiaodjh(id);
	}
	@RequestMapping(value = "/generateJihdh")
	public void generateJihdh(@RequestParam(defaultValue="") String riq,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		String jihdh=diaodjhwhService.generateJihdh(riq);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jihdh);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
