package com.zhiren.fuelmis.dc.controller.hetgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.service.hetgl.IChaxdyService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("hetgl/chaxdy")
public class ChaxdyController {

	@Autowired
	private IChaxdyService chaxdyService;
	
	@RequestMapping(value = "/getHetcx")
	public void getHetcx(@RequestParam(defaultValue="") String sDate,@RequestParam(defaultValue="") String eDate,
			@RequestParam(defaultValue="") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("sDate", "".equals(sDate)?DateUtil.getDayOfMonth(null):sDate);
		map.put("eDate", "".equals(eDate)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE):eDate);
		map.put("dianc", "-1".equals(dianc)?"":dianc);
		
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(chaxdyService.getHetcx(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getHetbh")
	public void getHetbh(@RequestParam String riq,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String sDate = "".equals(riq)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE_MONTH):riq+"-01";
		map.put("sDate",sDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String eDate = "";
		try {
			eDate = DateUtil.getLastDayOfMonth(sdf.parse(sDate));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		map.put("eDate", eDate);
		List<Map<String,Object>> list = chaxdyService.getHetbh(map);
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("HETBH"), list.get(i)
						.get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getPingsyjb")
	public void getPingsyjb(@RequestParam(defaultValue="") String hetb_id,
			@RequestParam(defaultValue="") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("hetb_id", hetb_id);
		
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(chaxdyService.getPingsyjb(map).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
