package com.zhiren.fuelmis.dc.controller.caiygl.heby;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.caiygl.heby.IHebyService;

/**
 * 
 * @author 刘志宇
 * 
 */
@Controller
@RequestMapping("caiygl/heby")
public class HebyController {

	@Autowired
	private IHebyService hebyService;
	@RequestMapping(value = "/getSamcodeList")
	public void getSamcodeList(@RequestParam(defaultValue = "") String riq,HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		List<Map<String, Object>> list = hebyService.getSamcodeList(riq);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(list).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getList")
	public void getList(@RequestParam String riq,HttpServletRequest request, HttpServletResponse response ,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		List<Map<String, Object>> list = hebyService.getList(riq);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(list).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/updateList")
	public void updateList(@RequestParam String data,HttpServletRequest request, HttpServletResponse response ,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		List<Map<String, Object>> list= JSONArray.fromObject(data);
		hebyService.updateList(list);
	}
	@RequestMapping(value = "/updateCancelList")
	public void updateCancelList(@RequestParam String data,HttpServletRequest request, HttpServletResponse response ,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		List<Map<String, Object>> list= JSONArray.fromObject(data);
		hebyService.updateCancelList(list);
	}
}
