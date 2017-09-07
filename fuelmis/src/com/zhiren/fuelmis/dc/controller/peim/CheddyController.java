package com.zhiren.fuelmis.dc.controller.peim;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
import com.zhiren.fuelmis.dc.service.peim.IPeimeiService;
/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/Cheddy")
public class CheddyController {
	@Autowired
	private IPeimeiService peimeiService;
	@RequestMapping(value = "/loadcheddytz")
	public void loadcheddytz(HttpServletRequest request , HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		if(null == riq){
			riq = new SimpleDateFormat("yyyy-MM").format(new Date())+"-01";
		}
		String yunscdb_id = request.getParameter("yunscd_id");
		if("undefined".equals(yunscdb_id)){
			yunscdb_id = "-1";
		}
		JSONObject jsonobject = peimeiService.loadData4Cheddytz(yunscdb_id, riq);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonobject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/getyunsdanw")
	public void getyunsdanw(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List yunsdwList = null;
		yunsdwList = peimeiService.loadYunscdCombo();
		JSONArray jsonArray = new JSONArray();
		if (yunsdwList != null) {
			Combobox combobox = new Combobox("请选择",-1);
			jsonArray.add(combobox);
			Iterator i4RowData = yunsdwList.iterator();
			while (i4RowData.hasNext()) {
				Map mRow = (Map)i4RowData.next();
				combobox = new Combobox(mRow.get("CHEDMC"), mRow.get("ID"));
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
	@RequestMapping(value = "/Cheddysave")
	public void Cheddysave(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String info =  request.getParameter("info");
		JSONObject jsonObj =JSONObject.fromObject(info);
		boolean ret = false;
		ret = peimeiService.saveRicddytzData(jsonObj);
		String result="";
		if(ret){
			result = "保存成功";
		}else{
			result = "保存失败！！！";
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
}
