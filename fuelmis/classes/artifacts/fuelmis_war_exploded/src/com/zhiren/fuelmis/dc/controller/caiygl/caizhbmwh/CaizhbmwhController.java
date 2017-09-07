package com.zhiren.fuelmis.dc.controller.caiygl.caizhbmwh;

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

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.caiygl.caizhbmwh.ICaizhbmwhService;

/**
 * 
 * @author 刘志宇
 * 
 */
@Controller
@RequestMapping("caiygl/caizhbmwh")
public class CaizhbmwhController {

	@Autowired
	private ICaizhbmwhService caizhbmwhService;
	@RequestMapping(value = "/getBianm")
	public void getBianm(@RequestParam(defaultValue = "") String riq,
			@RequestParam(defaultValue = "") String diancid,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		List<Map<String, Object>> list = caizhbmwhService.getBianm(diancid,riq);
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				jsonArray.add(list.get(i));
			}
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/updateBianm")
	public void updateBianm(@RequestParam String data,HttpServletRequest request, HttpServletResponse response ,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter writer = null;
		Renyxx user=(Renyxx) session.getAttribute("user");
		JSONArray array = JSONArray.fromObject(data);
		caizhbmwhService.updateBianm(array,user);
		// try {
		// writer = response.getWriter();
		// writer.write(jsonArray.toString());
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	@RequestMapping(value = "/generateBianm")
	public void generateBianm(@RequestParam String data,@RequestParam String riq,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		JSONArray array = JSONArray.fromObject(data);
		JSONArray jsonArray = caizhbmwhService.generateBianm(array,riq);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
