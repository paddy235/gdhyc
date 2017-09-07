package com.zhiren.fuelmis.dc.controller.cnfy;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.service.cnfy.IFeiyxmflService;
import com.zhiren.fuelmis.dc.utils.JsonUtil;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/feiyxmfl")
public class FeiyxmflController {
	
	@Autowired
	private IFeiyxmflService feiyxmflService ;
	
	@RequestMapping(value = "/getAllData")
	public void getAllData(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONObject jsonArray = feiyxmflService.getAllData();
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({"unchecked" })
	@RequestMapping(value="/getfeilkj")
	public void getfeilkj(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<HashMap<String, Object>> list = feiyxmflService.getFenlkj();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox();
			combobox.setName("请选择");
			combobox.setValue(-1);
			jsonArray.add(combobox);
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("MINGC"), list.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/feiyxmfladd")
	public void addFeiyxmfl(HttpServletRequest request , HttpServletResponse response , HttpSession session) throws UnsupportedEncodingException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		String bianm = feiyxmflService.getMaxBianm();
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
		int ret = feiyxmflService.addFeiyxmflData(map);
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
	
	@RequestMapping(value="/getFeiyxmflById")
	public void getFeiyxmflById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		JSONObject jsonObj = feiyxmflService.getFeiyxmflById(id);
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
	@RequestMapping(value="/feiyxmflupdate")
	public void feiyxmflupdate(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		int ret = feiyxmflService.updateFeiyxmfl(map);
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
		int status = feiyxmflService.getState(id);
		if(status == 1){
			status = 0;
		}else{
			status = 1;
		}
		Map map = new HashMap();
		map.put("id", id);
		map.put("zhuangt", status);
		feiyxmflService.changeState(map);
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
