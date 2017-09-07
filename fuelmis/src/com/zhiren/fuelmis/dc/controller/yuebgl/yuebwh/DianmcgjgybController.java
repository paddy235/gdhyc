package com.zhiren.fuelmis.dc.controller.yuebgl.yuebwh;

import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IDianmcgjgybService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 陈宝露
 */
@Controller
@RequestMapping("yueb/guodbb")
public class DianmcgjgybController {
	
	@Autowired
	private IDianmcgjgybService guodbbService;
	
	@RequestMapping(value = "/getMeishcbb")
	public void getMeishcbb(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,@RequestParam(defaultValue="515") String leix,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", riq);
		map.put("dianc", dianc);
		map.put("danwmc", request.getSession().getAttribute("diancxx"));
		map.put("leix", leix);
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(guodbbService.getMeishcbb(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @author 孟建云修改
	 * @param fangx 增加 if=“0”时汇总，else为明细
	 * @param riq
	 * @param dianc
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getShulysbb")
	public void getShulysbb(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			@RequestParam(defaultValue="0") String leix,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?getFirstDayOfLastMonth():riq);
		map.put("dianc", dianc);
		map.put("danwmc", request.getSession().getAttribute("diancxx"));
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(guodbbService.getShulysbb(leix,map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getZhilysbb")
	public void getZhilysbb(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,@RequestParam(defaultValue="1") String leix,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?getFirstDayOfLastMonth():riq);
		map.put("dianc", dianc);
		map.put("danwmc", request.getSession().getAttribute("diancxx"));
		map.put("leix", leix);
		
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(guodbbService.getZhilysbb(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getRucbmdjbb")
	public void getRucbmdjbb(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			@RequestParam(defaultValue="1") String leix,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?getFirstDayOfLastMonth():riq);
		map.put("dianc", dianc);
		map.put("leix", leix);
		map.put("danwmc", request.getSession().getAttribute("diancxx"));
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(guodbbService.getRucbmdjbb(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getRanyshc")
	public void getRanyshc(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?getFirstDayOfLastMonth():riq);
		map.put("dianc", dianc);
		map.put("danwmc", request.getSession().getAttribute("diancxx"));
		
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(guodbbService.getRanyshc(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getRulmrz")
	public void getRulmrz(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?getFirstDayOfLastMonth():riq);
		map.put("dianc", dianc);
		map.put("danwmc", request.getSession().getAttribute("diancxx"));
		
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(guodbbService.getRulmrz(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getRanlcbbb")
	public void getRanlcbbb(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?getFirstDayOfLastMonth():riq);
		map.put("dianc", dianc);
		map.put("danwmc", request.getSession().getAttribute("diancxx"));
		
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(guodbbService.getRanlcbbb(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getChangnfycx")
	public void getChangnfycx(@RequestParam String riq,@RequestParam(defaultValue="515") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
//		String ksrq = riq.substring(0,4);
//		map.put("kaisrq", ksrq+"-01"+"-01");
//		map.put("jiesrq", "".equals(riq)?getFirstDayOfLastMonth():riq);
		map.put("riq", riq);
		map.put("dianc", dianc);
		map.put("danwmc", request.getSession().getAttribute("diancxx"));
		
		request.getSession().setAttribute("changnfyParams", map);
		
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(guodbbService.getChangnfycxMX(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getChangnfycxMX")
	public void getChangnfycxMX(@RequestParam String riq,HttpServletRequest request,HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute("changnfyParams");
		
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(guodbbService.getChangnfycxMX(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getRanlzbqkb")
	public void getRanlzbqkb(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?getFirstDayOfLastMonth():riq);
		map.put("dianc", dianc);
		map.put("danwmc", request.getSession().getAttribute("diancxx"));
		map.put("lastriq", "".equals(riq)?DateUtil.getLastYearString(getFirstDayOfLastMonth()):DateUtil.getLastYearString(riq));
		
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(guodbbService.getRanlzbqkb(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getFirstDayOfLastMonth(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH);
	}
}
