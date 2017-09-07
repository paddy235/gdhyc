package com.zhiren.fuelmis.dc.controller.cnfy;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.service.cnfy.IFeiyxmwhService;
import com.zhiren.fuelmis.dc.utils.JsonUtil;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/feiyxmwh")
public class FeiyxmwhController {
	
	@Autowired
	private IFeiyxmwhService feiyxmwhService ;
	
	@RequestMapping(value = "/getAllData")
	public void getAllData(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONObject jsonArray = feiyxmwhService.getAllData();
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/feiyxmwhadd")
	public void addfeiyxmwh(HttpServletRequest request , HttpServletResponse response , HttpSession session) throws UnsupportedEncodingException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		String bianm = feiyxmwhService.getMaxBianm();
		long data = Long.parseLong(bianm) + 1;
		String newbianm = "";
		if(data<10){
			newbianm = "00"+data;
		}else if(data>=10 && data<100){
			newbianm = "0"+data;
		}else{
			newbianm = ""+data;
		}
		map.put("BIANM", newbianm);
		int ret = feiyxmwhService.addfeiyxmwhData(map);
		String reback;
		if(ret>0){
			reback="保存成功！！！";
		}else{
			reback="保存失败！！！";
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(reback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/getfeiyxmwhById")
	public void getfeiyxmwhById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		JSONObject jsonObj = feiyxmwhService.getfeiyxmwhById(id);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/feiyxmwhupdate")
	public void feiyxmwhupdate(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		int ret = feiyxmwhService.updatefeiyxmwh(map);
		String reback;
		if(ret>0){
			reback="更新成功！！！";
		}else{
			reback="更新失败！！！";
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(reback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/changestate")
	public void changeState(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		int status = feiyxmwhService.getState(id);
		if(status == 1){
			status = 0;
		}else{
			status = 1;
		}
		Map map = new HashMap();
		map.put("id", id);
		map.put("zhuangt", status);
		feiyxmwhService.changeState(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
