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
@RequestMapping("/meiccp")
public class MeiccpController {
	@Autowired
	private IPeimeiService peimeiService;
	@RequestMapping(value = "/loadmeiccp")
	public void loadmeiccp(HttpServletRequest request , HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		if(null == riq){
			riq = new SimpleDateFormat("yyyy-MM").format(new Date())+"-01";
		}
		String peimeidanwid = request.getParameter("peimeidanwid");
		if("undefined".equals(peimeidanwid)){
			peimeidanwid = "-1";
		}
		JSONObject jsonobject = peimeiService.loadData4Meicpmdy(peimeidanwid, riq);
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
	@RequestMapping(value="/getpeimeidw")
	public void getpeimeidw(HttpServletRequest request , HttpServletResponse response , HttpSession session){
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
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/getmeiyuan")
	public void getmeiyuan(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List yunsdwList = null;
		yunsdwList = peimeiService.loadMeiyCombo();
		JSONArray jsonArray = new JSONArray();
		if (yunsdwList != null) {
			Combobox combobox = new Combobox("请选择",-1);
			jsonArray.add(combobox);
			Iterator i4RowData = yunsdwList.iterator();
			while (i4RowData.hasNext()) {
				Map mRow = (Map)i4RowData.next();
				combobox = new Combobox(mRow.get("MEIYMC"), mRow.get("ID"));
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
	@RequestMapping(value="/getmeishan")
	public void getmeishan(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List yunsdwList = null;
		yunsdwList = peimeiService.loadMeisData();
		JSONArray jsonArray = new JSONArray();
		if (yunsdwList != null) {
			Combobox combobox = new Combobox("请选择",-1);
			jsonArray.add(combobox);
			Iterator i4RowData = yunsdwList.iterator();
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
	@RequestMapping(value = "/meiccpsave")
	public void meiccpsave(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		String info =  request.getParameter("info");
		JSONObject jsonObj =JSONObject.fromObject(info);
		boolean ret = false;
		ret = peimeiService.saveMeicpmdyData(riq, jsonObj);
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
	@RequestMapping(value = "/delmeiccp")
	public void delmeiccp(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		boolean ret = false;
		ret = peimeiService.delMeicpmdyData(id);
		String result="";
		if(ret){
			result = "删除成功";
		}else{
			result = "删除失败！！！";
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
