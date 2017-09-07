package com.zhiren.fuelmis.dc.controller.kucgl.baobcx;

import com.zhiren.fuelmis.dc.service.kucgl.baobcx.IKucbbcxService;
import com.zhiren.fuelmis.dc.service.kucgl.baobcx.IRanmzgService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author Rain
 */
@Controller
@RequestMapping("kucgl/kucbb")
public class KucbbcxController {
	@Autowired
	private IKucbbcxService kucbbcxService;
	@Autowired
	private IRanmzgService ranmzgService;

	@RequestMapping(value = "/getFadgrckhybb")
	public void getFadgrckhybb(@RequestParam(defaultValue="") String search,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		Map<String,Object> map =JSONObject.fromObject(search); 
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(kucbbcxService.getFadgrckhybb(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/getShiscbhsbb")
	public @ResponseBody JSONArray getShiscbhsbb(@RequestParam(defaultValue="") String search,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		Map<String,Object> map =JSONObject.fromObject(search);
			return kucbbcxService.getShiscbhsbb(map);
//		return kucbbcxService.get(map);
	}
	@RequestMapping(value = "/getChukdbb")
	public @ResponseBody JSONArray getChukdbb(@RequestParam(defaultValue="") String search,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		Map<String,Object> map =JSONObject.fromObject(search);
		return kucbbcxService.getChukdbb(map);
	}
	@RequestMapping(value = "/getRiclhcbb")
	public @ResponseBody JSONArray getRiclhcbb(@RequestParam(defaultValue="") String search,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		Map<String,Object> map =JSONObject.fromObject(search);
		return kucbbcxService.getRiclhcbb(map);
	}
	@RequestMapping(value = "/getRiclhcmxbb")
	public @ResponseBody JSONArray getRiclhcbbmx(@RequestParam(defaultValue="") String search,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		Map<String,Object> map =JSONObject.fromObject(search);
		return kucbbcxService.getRiclhcbbmx(map);
	}
	@RequestMapping(value = "/getChukcxbb")
	public @ResponseBody JSONArray getChukcxbb(@RequestParam(defaultValue="") String search,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		Map<String,Object> map =JSONObject.fromObject(search);
		return kucbbcxService.getChukcxbb(map);
	}
	//燃煤暂估
	@RequestMapping(value="/getRanmzg")
	public @ResponseBody JSONArray getRanmzg(@RequestParam(defaultValue="")String search,
			HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		Map<String,Object> map=JSONObject.fromObject(search);
		return ranmzgService.getRanmzg(map);
		
	}
	
}