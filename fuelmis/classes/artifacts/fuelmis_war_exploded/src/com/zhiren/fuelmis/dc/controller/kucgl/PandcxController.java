package com.zhiren.fuelmis.dc.controller.kucgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.kucgl.IPandcxService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("kucgl/pandcx")
public class PandcxController {
	
	@Autowired
	private IPandcxService pandcxService;
	
	@RequestMapping(value = "/getReport")
	public void getReport(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", riq);
		map.put("dianc", "-1".equals(dianc)?"":dianc);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(pandcxService.getReport(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/shangb")
	public void shangb(@RequestParam(defaultValue="") String search,
						  HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = JSONObject.fromObject(search);
		response.setContentType("text/html;charset=UTF-8");
		pandcxService.shangb(map);
	}
}
