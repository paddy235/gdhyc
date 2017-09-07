package com.zhiren.fuelmis.dc.controller.kucgl.chukgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.kucgl.chukgl.IHaoyckService;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("/kucgl/chukgl")
public class HaoyckController {
	@Autowired
	private IHaoyckService haoyckService;


	@RequestMapping(value = "getHaoyckmx")
	public void getHaoyckmx(@RequestParam(defaultValue="") String rukdbh,
			HttpServletRequest request , HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rukdbh",rukdbh);
		Map<String,Object> haoyckmx = haoyckService.getHaoyckmx(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(JSONObject.fromObject(haoyckmx).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="saveChukd")
	public void save(@RequestParam(defaultValue="") String data, HttpServletRequest request , HttpServletResponse response, HttpSession session){
		JSONObject o=JSONObject.fromObject(data);
		Renyxx renyxx = (Renyxx) session.getAttribute("user");
		o.put("user",renyxx);
		String chukdbh=haoyckService.saveChukd(o);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(chukdbh);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="chuk")
	public void chuk(@RequestParam(defaultValue="") String data,HttpServletRequest request , HttpServletResponse response, HttpSession session){
		JSONObject o=JSONObject.fromObject(data);
		Renyxx renyxx = (Renyxx) session.getAttribute("user");
		o.put("user",renyxx);
		haoyckService.chuk(o);
	}

	@RequestMapping(value="chex")
	public void deleteChurkd(@RequestParam(defaultValue="") String chukdbh,HttpServletRequest request , HttpServletResponse response){
		haoyckService.deleteChurkd(chukdbh);
	}
	@RequestMapping(value="getChukdList")
	public void getChukdList(@RequestParam(defaultValue="") String search,HttpServletRequest request , HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map=JSONObject.fromObject(search);
		List<Map<String,Object>> list=haoyckService.getChukdList(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(list).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="getDangrjc")
	public @ResponseBody JSONArray getDangrjc(HttpServletRequest request , HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		List<Map<String,Object>> list=haoyckService.getDangrjc();
		return JSONArray.fromObject(list);
	}
	
}
