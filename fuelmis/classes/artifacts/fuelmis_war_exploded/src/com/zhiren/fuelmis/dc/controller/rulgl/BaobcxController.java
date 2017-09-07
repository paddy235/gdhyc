package com.zhiren.fuelmis.dc.controller.rulgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.rulgl.IBaobcxService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("rulgl/baobcx")
public class BaobcxController {
	
	@Autowired
	private IBaobcxService baobcxService;
	
	@RequestMapping(value = "/getRulhyd")
	public void getRulhyd(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="") String dianc,
			@RequestParam(defaultValue="") String rulbz,@RequestParam(defaultValue="") String jiz,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE):riq);
		map.put("dianc", "-1".equals(dianc)?"":dianc);
		map.put("rulbz", "-1".equals(rulbz)?"":rulbz);
		map.put("jiz", "-1".equals(jiz)?"":jiz);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(baobcxService.getRulhyd(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getRuljzbb")
	public void getRuljzbb(@RequestParam(defaultValue="") String sDate,@RequestParam(defaultValue="") String dianc,
			@RequestParam(defaultValue="") String eDate,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("sDate", "".equals(sDate)?DateUtil.getDayOfMonth(null):sDate);
		map.put("eDate", "".equals(eDate)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE):eDate);
		map.put("dianc", "-1".equals(dianc)?"":dianc);
		map.put("danwmc", request.getSession().getAttribute("diancxx"));

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(baobcxService.getRuljzbb(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getRuljztz")
	public void getRuljztz(@RequestParam(defaultValue="") String sDate,@RequestParam(defaultValue="") String dianc,
			@RequestParam(defaultValue="") String eDate,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("sDate", "".equals(sDate)?DateUtil.getDayOfMonth(null):sDate);
		map.put("eDate", "".equals(eDate)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE):eDate);
		map.put("dianc", "-1".equals(dianc)?"":dianc);
		map.put("danwmc", request.getSession().getAttribute("diancxx"));

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(baobcxService.getRuljztz(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getRucrlrzc")
	public void getRucrlrzc(@RequestParam(defaultValue="") String sDate,@RequestParam(defaultValue="") String eDate,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("sDate", "".equals(sDate)?DateUtil.getDayOfMonth(null):sDate);
		map.put("eDate", "".equals(eDate)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE):eDate);
		map.put("danwmc", request.getSession().getAttribute("diancxx"));

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(baobcxService.getRucrlrzc(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getSIS_shujcx")
	public void getSIS_shujcx(@RequestParam(defaultValue="") String riq,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE):riq);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(baobcxService.getSIS_shujcx(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
