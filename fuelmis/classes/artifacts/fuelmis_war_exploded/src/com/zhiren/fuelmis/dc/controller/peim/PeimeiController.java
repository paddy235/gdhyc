package com.zhiren.fuelmis.dc.controller.peim;

import java.io.IOException;
import java.io.PrintWriter;
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
@RequestMapping("/peimei")
public class PeimeiController {
	@Autowired
	private IPeimeiService peimeiService;
	@RequestMapping(value = "/peimeisearch")
	public void peimeisearch(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		JSONObject	jsonobject = peimeiService.loadPageData(riq);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonobject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	@RequestMapping(value = "/peimeisjis")
	public void peimeisjis(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		String info =  request.getParameter("info");
		JSONObject jsonObj = JSONObject.fromObject(info);
		String ret="";
		ret = peimeiService.calculate(riq,jsonObj);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(ret);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	@RequestMapping(value = "/peimeisave")
	public void peimeisave(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		String info =  request.getParameter("info");
		JSONObject jsonObj =JSONObject.fromObject(info);
		boolean ret = false;
		ret = peimeiService.saveData(riq, jsonObj);
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
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/getPeimdw")
	public void getPeimdw(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List peimdwList = null;
		peimdwList = peimeiService.loadPeimdwData();
		JSONArray jsonArray = new JSONArray();
		if (peimdwList != null) {
			Combobox combobox = new Combobox("请选择",-1);
			jsonArray.add(combobox);
			Iterator i4RowData = peimdwList.iterator();
			while (i4RowData.hasNext()) {
				Map mRow = (Map)i4RowData.next();
				combobox = new Combobox(mRow.get("MINGC"), mRow.get("ID"));
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
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/getMeishan")
	public void getMeishan(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String peimdw_id = request.getParameter("peimdw_id");
		PrintWriter writer = null;
		List peimdwList = null;
		peimdwList = peimeiService.loadMeisData(peimdw_id);
		JSONArray jsonArray = new JSONArray();
		if (peimdwList != null) {
			Combobox combobox = new Combobox("请选择",-1);
			jsonArray.add(combobox);
			Iterator i4RowData = peimdwList.iterator();
			while (i4RowData.hasNext()) {
				Map mRow = (Map)i4RowData.next();
				combobox = new Combobox(mRow.get("MEISMC"), mRow.get("ID"));
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
	
	@RequestMapping(value = "/diaoysearch")
	public void diaoysearch(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		JSONObject	jsonobject = peimeiService.loadRicddyjhData(riq);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonobject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/diaoyjhsave")
	public void diaoyjhsave(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		String info =  request.getParameter("info");
		JSONObject jsonObj =JSONObject.fromObject(info);
		boolean ret = false;
		ret = peimeiService.saveRicddyjhData(riq, jsonObj);
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
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/getyunsdw")
	public void getyunsdw(HttpServletRequest request , HttpServletResponse response , HttpSession session){
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
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/getmeic")
	public void getmeic(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List yunsdwList = null;
		yunsdwList = peimeiService.loadPeimdwData();
		JSONArray jsonArray = new JSONArray();
		if (yunsdwList != null) {
			Combobox combobox = new Combobox("请选择",-1);
			jsonArray.add(combobox);
			Iterator i4RowData = yunsdwList.iterator();
			while (i4RowData.hasNext()) {
				Map mRow = (Map)i4RowData.next();
				combobox = new Combobox(mRow.get("MINGC"), mRow.get("ID"));
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
}
