package com.zhiren.fuelmis.dc.controller.xitgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Ziyxx;
import com.zhiren.fuelmis.dc.service.xitgl.IZiyxxService;

/**
 * @author 陈宝露
 */
@Controller
@RequestMapping("/ziyxx")
public class ZiyxxController {
	@Autowired
	private IZiyxxService ziyxxService;

	@RequestMapping(value = "/getZiyxx")
	public void getZiyxx(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = ziyxxService.getZiyxx();
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getTopMenu")
	public void getTopMenu(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Renyxx renyxx = request.getSession().getAttribute("user") != null ? (Renyxx) request
				.getSession().getAttribute("user") : null;
		JSONArray jsonArray = ziyxxService.getTopMenu(renyxx!=null?renyxx.getId():null);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/addZiyxx")
	public void addZiyxx(@RequestParam Long id, @RequestParam String mingc,
			@RequestParam String wenjwz, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Ziyxx ziyxx = new Ziyxx();
		ziyxx.setName(mingc);
		ziyxx.setWenjwz(wenjwz);
		ziyxx.setFuid(id);
		JSONObject jsonArray = ziyxxService.insertZiyxx(ziyxx);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/modifyZiyxx")
	public void modifyZiyxx(@RequestParam Long id, @RequestParam String mingc,
			@RequestParam String wenjwz, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Ziyxx ziyxx = new Ziyxx();
		ziyxx.setName(mingc);
		ziyxx.setWenjwz(wenjwz);
		ziyxx.setId(id);
		JSONObject jsonArray = ziyxxService.updateZiyxx(ziyxx);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/delZiyxx")
	public void delZiyxx(@RequestParam Long id, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONObject jsonArray = ziyxxService.deleteZiyxx(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getOne")
	public void getOne(@RequestParam Long id, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONArray jsonArray = ziyxxService.getOne(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
