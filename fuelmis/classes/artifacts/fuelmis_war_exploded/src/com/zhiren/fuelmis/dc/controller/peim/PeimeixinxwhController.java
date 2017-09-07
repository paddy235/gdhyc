package com.zhiren.fuelmis.dc.controller.peim;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.service.peim.IPeimeiService;
/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/peimeixinxiwh")
public class PeimeixinxwhController {
	@Autowired
	private IPeimeiService peimeiService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/yunscdlist")
	public void yunscdlist(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		List list = peimeiService.loadYunsdwData();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{hashMap.get("ID"),i+1,hashMap.get("CHEDMC"),hashMap.get("ZHUANGT_CN"),hashMap.get("ZHUANGT")};  
		}
		jsonMap.put("data", objs);
		JSONObject jsonobject = JSONObject.fromObject(jsonMap);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonobject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	@RequestMapping(value="/yunscdadd")
	public void yunscdadd(HttpServletRequest request , HttpServletResponse response , HttpSession session) throws UnsupportedEncodingException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String info = request.getParameter("info");
		JSONObject json = JSONObject.fromObject(info);
		boolean ret = peimeiService.saveYunscdData(json);
		String reback;
		if(ret){
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
	@RequestMapping(value="/changeyunscdstate")
	public void changeState(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		peimeiService.changeYunscdStatus(id);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/changemeiyuanstate")
	public void changemeiyuanstate(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		peimeiService.changeMeiyStatus(id);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/meiyuanlist")
	public void meiyuanlist(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		List list = peimeiService.loadMeiyData();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{hashMap.get("ID"),i+1,hashMap.get("MEIYMC"),hashMap.get("QNET_AR"),hashMap.get("S"),hashMap.get("V"),
					hashMap.get("MEIJ"),hashMap.get("YUNJ"),hashMap.get("BIAOMDJ"),hashMap.get("SHUL_MAX"),hashMap.get("SHUL_MIN"),hashMap.get("ZHUANGT_CN")};  
		}
		jsonMap.put("data", objs);
		JSONObject jsonobject = JSONObject.fromObject(jsonMap);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonobject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/meishanlist")
	public void meishanlist(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		List list = peimeiService.loadMeisData();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{hashMap.get("ID"),i+1,hashMap.get("MEISMC"),hashMap.get("DIANCXXB_ID_CN"),
					hashMap.get("MEICMC"),hashMap.get("QNET_AR"),hashMap.get("S"),hashMap.get("V")};  
		}
		jsonMap.put("data", objs);
		JSONObject jsonobject = JSONObject.fromObject(jsonMap);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonobject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	@RequestMapping(value="/meiyuanadd")
	public void meiyuanadd(HttpServletRequest request , HttpServletResponse response , HttpSession session) throws UnsupportedEncodingException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String info = request.getParameter("info");
		JSONObject json = JSONObject.fromObject(info);
		boolean ret = peimeiService.saveMeiyData(json);
		String reback;
		if(ret){
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
	@RequestMapping(value="/meishanadd")
	public void meishanadd(HttpServletRequest request , HttpServletResponse response , HttpSession session) throws UnsupportedEncodingException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String info = request.getParameter("info");
		JSONObject json = JSONObject.fromObject(info);
		boolean ret = peimeiService.saveMeisData(json);
		String reback;
		if(ret){
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
	@RequestMapping(value="/getYunscdById")
	public void getYunscdById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		JSONObject jsonObj = peimeiService.loadYunscdData4ID(id);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/getMeiyuanById")
	public void getMeiyuanById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		JSONObject jsonObj = peimeiService.loadMeiyData4ID(id);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/getMeishanById")
	public void getMeishanById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		JSONObject jsonObj = peimeiService.loadMeisData4ID(id);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
