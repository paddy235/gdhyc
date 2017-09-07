package com.zhiren.fuelmis.dc.controller.rulgl;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.zhiren.fuelmis.dc.service.rulgl.IbaseSetService;

/**
 * 
 * @author 刘志宇
 * 
 */
@Controller
@RequestMapping("baseSet")
public class BaseSetController {

	@Autowired
	private IbaseSetService baseSetService;

	@RequestMapping(value = "/getBanz")
	public void getBanz(@RequestParam(defaultValue = "") String riq,
			@RequestParam(defaultValue = "") String dianc,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		List<Map<String, Object>> list = baseSetService.getBanz();
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

	@RequestMapping(value = "/updateBanz")
	public void updateBanz(@RequestParam String data,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter writer = null;

		@SuppressWarnings("unchecked")
		Map<String, Object> map = JSONObject.fromObject(data);
		baseSetService.updateBanz(map);
		// try {
		// writer = response.getWriter();
		// writer.write(jsonArray.toString());
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	@RequestMapping(value = "/insertBanz")
	public void insertBanz(@RequestParam String data,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter writer = null;

		@SuppressWarnings("unchecked")
		Map<String, Object> map = JSONObject.fromObject(data);
		// JSONObject fromObject = JSONObject.fromObject(kuczzList);
		baseSetService.insertBanz(map);
		// try {
		// writer = response.getWriter();
		// writer.write(jsonArray.toString());
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	@RequestMapping(value = "/getJiz")
	public void getJiz(@RequestParam(defaultValue = "") String riq,
			@RequestParam(defaultValue = "") String dianc,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		List<Map<String, Object>> list = baseSetService.getJiz();
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

	@RequestMapping(value = "/updateJiz")
	public void updateJiz(@RequestParam String data,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter writer = null;

		@SuppressWarnings("unchecked")
		Map<String, Object> map = JSONObject.fromObject(data);
		 baseSetService.updateJiz(map);
		// try {
		// writer = response.getWriter();
		// writer.write(jsonArray.toString());
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	@RequestMapping(value = "/insertJiz")
	public void insertJiz(@RequestParam String data,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter writer = null;

		@SuppressWarnings("unchecked")
		Map<String, Object> map = JSONObject.fromObject(data);
		// JSONObject fromObject = JSONObject.fromObject(kuczzList);
		 baseSetService.insertJiz(map);
		// try {
		// writer = response.getWriter();
		// writer.write(jsonArray.toString());
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}
