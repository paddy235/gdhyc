package com.zhiren.fuelmis.dc.controller.xitgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zhiren.fuelmis.dc.entity.xitgl.Gongysmkglb;
import com.zhiren.fuelmis.dc.entity.xitgl.Kuangzglb;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.common.ICommonService;
import com.zhiren.fuelmis.dc.service.xitgl.IMeikxxService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("/meikxx")
public class MeikxxController {
	@Autowired
	private IMeikxxService meikxxService;
	
	@Autowired
	private ICommonService commonService;
	
	@RequestMapping(value = "/getAll")
	public void getAll(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONObject jsonObject = meikxxService.getAll(null);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/addMeikxx")
	public void addMeikxx(@RequestParam String info,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		@SuppressWarnings("unchecked")
		Map<String,Object> map = JSONObject.fromObject(info);
		map.put("id", System.currentTimeMillis());
		String leib = map.get("leib")==null?"":map.get("leib").toString();
		map.remove("leib");
		map.put("leib", leib.equals("2")?"统配":"地方");
		String leix = map.get("leix")==null?"":map.get("leix").toString();
		map.remove("leix");
		map.put("leix", leix.equals("1")?"煤":"");
		map.put("creater", ((Renyxx)request.getSession().getAttribute("user")).getMingc());
		map.put("create_date", DateUtil.formatTime(new Date()));
		JSONArray jsonArray = meikxxService.insertMeikxx(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getOne")
	public void getOne(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		JSONArray jsonArray = meikxxService.getOne(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/updateMeikxx")
	public void updateMeikxx(@RequestParam String info,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		@SuppressWarnings("unchecked")
		Map<String,Object> map = JSONObject.fromObject(info);
		String leib = map.get("leib")==null?"":map.get("leib").toString();
		map.remove("leib");
		map.put("leib", leib.equals("2")?"统配":"地方");
		String leix = map.get("leix")==null?"":map.get("leix").toString();
		map.remove("leix");
		map.put("leix", leix.equals("1")?"煤":"");
		map.put("last_editer", ((Renyxx)request.getSession().getAttribute("user")).getMingc());
		map.put("last_edit_date", DateUtil.formatTime(new Date()));
		JSONArray jsonArray = meikxxService.updateMeikxx(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getKuangzglb")
	public void getKuangzglb(@RequestParam String meikxxb_id,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("meikxxb_id", meikxxb_id);
		JSONArray jsonArray = meikxxService.getKuangzglb(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getGongysmkglb")
	public void getGongysmkglb(@RequestParam String meikxxb_id,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("meikxxb_id", meikxxb_id);
		JSONArray jsonArray = meikxxService.getGongysmkglb(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/saveKuangzglb")
	public void saveKuangzglb(@RequestParam String meikxxb_id,@RequestParam String chezxxb_id,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Kuangzglb> list = new ArrayList<Kuangzglb>();
		String[] chezs = chezxxb_id.substring(1).split(",");
		for(int i=0;i<chezs.length;i++){
			Kuangzglb kuangzglb = new Kuangzglb();
			kuangzglb.setMeikxxb_id(meikxxb_id);
			kuangzglb.setChezxxb_id(chezs[i]);
			
			list.add(kuangzglb);
		}
		JSONArray jsonArray = meikxxService.insertKuangzglb(list);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/saveGongysmkglb")
	public void saveGongysmkglb(@RequestParam String meikxxb_id,@RequestParam String gongysb_id,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Gongysmkglb> list = new ArrayList<Gongysmkglb>();
		String[] gongyss = gongysb_id.substring(1).split(",");
		for(int i=0;i<gongyss.length;i++){
			Gongysmkglb gongysmkglb = new Gongysmkglb();
			gongysmkglb.setMeikxxb_id(meikxxb_id);
			gongysmkglb.setGongysb_id(gongyss[i]);
			list.add(gongysmkglb);
		}
		JSONArray jsonArray = meikxxService.insertGongysmkglb(list);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/uploadFile")
	public void uploadFile(@RequestParam(value = "upFile", required = false) MultipartFile file,
			@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String result = commonService.uploadFile(file);
		
		JSONArray jsonArray = new JSONArray();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("wenjmc", result);
		
		if(result!=null){
			meikxxService.updateMeikxx(map);
			map.put("msg", "文件上传成功！");
			jsonArray.add(map);
		}else{
			map.put("msg", "文件上传失败！");
			jsonArray.add(map);
		}
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
