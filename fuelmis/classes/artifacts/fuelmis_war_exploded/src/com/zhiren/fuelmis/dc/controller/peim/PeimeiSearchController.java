package com.zhiren.fuelmis.dc.controller.peim;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("/peimeisearch")
public class PeimeiSearchController {
	@Autowired
	private IPeimeiService peimeiService;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/selectyijpeimei")
	public void selectyijpeimei(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		if(null == riq){
			riq = new SimpleDateFormat("yyyy-MM").format(new Date())+"-01";
		}
		List list = null;
		list = peimeiService.loadData4MeiyChes(riq);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{hashMap.get("MEIYMC"),hashMap.get("CHES"),hashMap.get("DAOCLYG"),hashMap.get("SHIJDCL"),hashMap.get("RIQ")};  
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
	@RequestMapping(value="/getpeimeiList")
	public void getpeimeiList(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String Peimdw_id = request.getParameter("peimeidwid");
		String Meis_id = request.getParameter("meishanid");
		
		String riq = request.getParameter("riq");
		if(null == riq){
			riq = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		if(null == Peimdw_id||"undefined".equals(Peimdw_id)){
			Peimdw_id = "-1";
		}
		if(null == Meis_id||"undefined".equals(Meis_id)){
			Meis_id = "-1";
		}
		List list = null;
		list = peimeiService.loadData4Diaoymx(Peimdw_id,Meis_id,riq);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{hashMap.get("MINGC"),hashMap.get("MEISMC"),hashMap.get("MEIYMC"),hashMap.get("SHUL_DY"),hashMap.get("CHES"),hashMap.get("QNET_AR"),hashMap.get("S"),hashMap.get("V"),hashMap.get("BIAOMDJ"),hashMap.get("RIQ")};  
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

}
