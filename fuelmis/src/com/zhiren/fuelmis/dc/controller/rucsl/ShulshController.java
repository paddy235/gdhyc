package com.zhiren.fuelmis.dc.controller.rucsl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.rucsl.IShulshService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.DateUtil.DateFormatType;
import com.zhiren.fuelmis.dc.utils.IPUtil;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("/rucsl/shulsh")
public class ShulshController {

	@Autowired
	private IShulshService shulshService;
	
	@RequestMapping(value = "/getTableData")
	public void getTableData(@RequestParam(defaultValue="1") String page,@RequestParam(defaultValue="0") String zhuangt,
			@RequestParam(defaultValue="") String sDate,@RequestParam(defaultValue="") String eDate,
			HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		response.setContentType("text/html;charset=UTF-8");
		map.put("zhuangt",zhuangt);
		map.put("sDate", "".equals(sDate)||sDate==null||sDate.equals("null")?DateUtil.format(new Date(),DateFormatType.SIMPLE_TYPE):sDate);
		map.put("eDate", "".equals(eDate)||eDate==null||eDate.equals("null")?DateUtil.format(new Date(),DateFormatType.SIMPLE_TYPE):eDate);
		JSONObject jsonObject = shulshService.getTableData(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/getTableData_MX")
	public void getTableData_MX(@RequestParam(defaultValue="") String id,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		response.setContentType("text/html;charset=UTF-8");
		map.put("samcode", id);
		JSONObject jsonObject = shulshService.getTableData_MX(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/shenh")
	public void shenh(@RequestParam(defaultValue="") String id,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		Renyxx renyxx=(Renyxx)session.getAttribute("user");
		Diancxx diancxx = (Diancxx)request.getSession().getAttribute("diancxx");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String YEWHJ = "验收管理-入场数量-数量审核";
//		String DANQCAOZLX= "审核";
//		String DANQCAOZRIP =IPUtil.ipConfig();
//		String DANQCAOZSJ  =df.format(new Date());
//		String DANQCAOZRY = renyxx.getMingc();
//		Map<String,Object> map1 = new HashMap<String,Object>();
//				map1.put("YEWHJ", YEWHJ);
//				map1.put("DANQCAOZLX", DANQCAOZLX);
//				map1.put("DANQCAOZRIP", DANQCAOZRIP);
//				map1.put("DANQCAOZSJ", DANQCAOZSJ);
//				map1.put("DANQCAOZRY", DANQCAOZRY);
//				map1.put("id", id);
//				shulshService.insertShulshLogs(map1);
		Map<String,Object> map = new HashMap<String,Object>();
		response.setContentType("text/html;charset=UTF-8");
		map.put("samcode", id);
		map.put("shenhr", ((Renyxx)request.getSession().getAttribute("user")).getQuanc());
		map.put("shenhsj", DateUtil.format(new Date()));
		map.put("diancxx",diancxx);
		map.put("user",renyxx);
		JSONArray jsonArray = shulshService.shenh(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/huit")
	public void huit(@RequestParam(defaultValue="") String id,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		response.setContentType("text/html;charset=UTF-8");
		map.put("samcode", id);
		JSONArray jsonArray = shulshService.huit(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/getJiesxxList")
	public void getJiesxxList(@RequestParam(defaultValue = "") String samcode,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list = shulshService.getJiesxxList(samcode);
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(list).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
