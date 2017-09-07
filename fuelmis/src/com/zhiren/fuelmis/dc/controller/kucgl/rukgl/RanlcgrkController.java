package com.zhiren.fuelmis.dc.controller.kucgl.rukgl;

import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.kucgl.rukgl.IRanlcgrkService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("/kucgl/rukgl/ranlcgrk")
public class RanlcgrkController {
	@Autowired
	private IRanlcgrkService caigrkService;
	
	@RequestMapping(value = "/getAll")
	public @ResponseBody JSONObject getAll(String search,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = JSONObject.fromObject(search);
		JSONObject jsonObject = caigrkService.getChurkdList(map);
		return jsonObject;
	}
	
	@RequestMapping(value = "/getAll2")
	public void getAll2(@RequestParam(defaultValue="") String sDate,@RequestParam(defaultValue="") String eDate,
			@RequestParam(defaultValue="") String yewlx,@RequestParam(defaultValue="") String rukdbh,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sDate", "".equals(sDate)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE_MONTH)+"-01":sDate);
		map.put("eDate", "".equals(eDate)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE):eDate);
		map.put("rukdbh", "%"+rukdbh+"%");
		map.put("yewlx", yewlx);
		JSONObject jsonObject = caigrkService.getChurkdList2(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getYansmx")
	public void getYansmx(@RequestParam(defaultValue="") String sDate,@RequestParam(defaultValue="") String eDate,
			@RequestParam String yewlx,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("yewlx", yewlx);
		if(yewlx.equals("2")){
			map.put("sDate", "".equals(sDate)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE_MONTH)+"-01":sDate);
			map.put("eDate", "".equals(eDate)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE):eDate);
		}
		JSONObject jsonObject = caigrkService.getYansmx(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getYansmxMX")
	public void getYansmxMX(@RequestParam(defaultValue="") String samcode,
			@RequestParam String yewlx,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("samcode", samcode);
		map.put("yewlx", yewlx);
		JSONObject jsonObject = caigrkService.getYansmxMX(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "ranlcgrk")
	public void ranlcgrk(@RequestParam String ids,HttpServletRequest request , HttpServletResponse response){
		Diancxx diancxx = (Diancxx)request.getSession().getAttribute("diancxx");
		JSONArray jsonArray = caigrkService.ranlcgrk(ids,diancxx,1);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "yunzfcgrk")
	public void yunzfcgrk(@RequestParam String ids,HttpServletRequest request , HttpServletResponse response){
		Diancxx diancxx = (Diancxx)request.getSession().getAttribute("diancxx");
		JSONArray jsonArray = caigrkService.ranlcgrk(ids,diancxx,2);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "getRanlcgrk_WRK_MX")
	public void getRanlcgrk_WRK_MX(@RequestParam(defaultValue="") String rukdbh,
			HttpServletRequest request , HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rukdbh",rukdbh);
		JSONObject jsonObject = caigrkService.getRanlcgrk_WRK_MX(map);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "getRanlcgrk_WRK_MX2")
	public void getRanlcgrk_WRK_MX2(@RequestParam(defaultValue="") String rukdbh,
			HttpServletRequest request , HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rukdbh",rukdbh);
		JSONObject jsonObject = caigrkService.getRanlcgrk_WRK_MX2(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="save")
	public void save(@RequestParam(defaultValue="") String rukdbh,@RequestParam(defaultValue="") String kuczz,
			@RequestParam(defaultValue="") String huoz,@RequestParam String rukdList,
			HttpServletRequest request , HttpServletResponse response){
		JSONArray jsonArray = caigrkService.saveRukd(rukdbh,kuczz,huoz,rukdList,1);
		
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="save_yunzf")
	public void save_yunzf(@RequestParam(defaultValue="") String rukdbh,@RequestParam(defaultValue="") String kuczz,
			@RequestParam(defaultValue="") String huoz,@RequestParam(defaultValue="") int yewlx,
			@RequestParam String rukdList,HttpServletRequest request , HttpServletResponse response){
		JSONArray jsonArray = caigrkService.saveRukd(rukdbh,kuczz,huoz,rukdList,yewlx);
		
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="ruk")
	public void ruk(@RequestParam(defaultValue="") String rukdbh, HttpServletRequest request , HttpServletResponse response, HttpSession session){
		JSONArray jsonArray = caigrkService.ruk(rukdbh);
		Map<String,Object> map=new HashMap<String,Object>();
		Renyxx user = (Renyxx) session.getAttribute("user");
		map.put("user",user);
		map.put("rukdbh",rukdbh);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="chex")
	public void chex(@RequestParam(defaultValue="") String rukdbh,HttpServletRequest request , HttpServletResponse response){
		JSONArray jsonArray = caigrkService.chex(rukdbh);
		
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="getQitrk")
	public void getQitrk(HttpServletRequest request , HttpServletResponse response){
		JSONObject jsonArray = caigrkService.getQitrk();
		
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "check")
	public void check(@RequestParam String rukdbh,HttpServletRequest request , HttpServletResponse response){
		JSONArray jsonArray = caigrkService.check(rukdbh);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getCaigdd")
	public void getCaigdd(@RequestParam(defaultValue="") String sDate,@RequestParam(defaultValue="") String eDate,
			@RequestParam(defaultValue="") String yewlx,@RequestParam(defaultValue="") String rukdbh,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONObject jsonObject = caigrkService.getCaigddList(null);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "editCaigdd")
	public void editCaigdd(@RequestParam String rukdbh,@RequestParam String caigdd_id,
			@RequestParam String yewlx,HttpServletRequest request , HttpServletResponse response){
		JSONArray jsonArray = caigrkService.editCaigdd(rukdbh,caigdd_id,yewlx);
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