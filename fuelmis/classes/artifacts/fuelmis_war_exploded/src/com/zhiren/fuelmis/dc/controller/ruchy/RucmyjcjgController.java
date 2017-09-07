package com.zhiren.fuelmis.dc.controller.ruchy;

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

import com.zhiren.fuelmis.dc.service.ruchy.IRuchyService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/**
 * 
 * @author 刘志宇
 *
 */
@Controller
@RequestMapping("ruchy")
public class RucmyjcjgController {

	@Autowired
	private IRuchyService ruchyService;
	
	@RequestMapping(value = "/getMeiyjcjg")
	public void getMeiyjcjg(@RequestParam(defaultValue="") String sDate,@RequestParam(defaultValue="") String eDate,
			@RequestParam(defaultValue="") String gongys,@RequestParam(defaultValue="0") String isRuzrq,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("qisrq", "".equals(sDate)?DateUtil.getDayOfMonth(null):sDate);
		map.put("zhongzrq", "".equals(eDate)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE):eDate);
/*		map.put("gongys", "-1".equals(gongys)?"":gongys);
		map.put("isRuzrq", "0".equals(isRuzrq)?"否":"是");*/
		
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(ruchyService.getMeiyjcjg(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getMeiyjcjgByCondition")
	public void getMeiyjcjgByCondition(@RequestParam(defaultValue="") String sDate,@RequestParam(defaultValue="") String eDate,
			@RequestParam(defaultValue="") String diancid,@RequestParam(defaultValue="0") String isRuzrq,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("qisrq", "".equals(sDate)?DateUtil.getDayOfMonth(null):sDate);
		map.put("zhongzrq", "".equals(eDate)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE):eDate);
		map.put("diancid", diancid);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(ruchyService.getMeiyjcjg(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
