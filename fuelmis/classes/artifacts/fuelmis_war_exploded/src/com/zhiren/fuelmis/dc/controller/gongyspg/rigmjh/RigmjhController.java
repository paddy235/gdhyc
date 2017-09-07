package com.zhiren.fuelmis.dc.controller.gongyspg.rigmjh;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.gongyspg.rigmjh.RigmjhService;

import net.sf.json.JSONObject;


/**
 * 
 * @author LK
 *
 */
@Controller
@RequestMapping("gongyspg/rigmjh")
public class RigmjhController {
	
	@Autowired
	private RigmjhService rigmjhService;

	
	/**
	 * 获取全部
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/searchRigmjhList")
		public void searchRigmjhList(@RequestParam String gongysb_id,@RequestParam String strdate,@RequestParam String enddate,@RequestParam String zhuangt,@RequestParam String fabrq, HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		if(null == strdate || ("").equals(strdate)){
			strdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		if(null == enddate || ("").equals(enddate)){
			enddate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		}
		if(null == gongysb_id || ("").equals(gongysb_id) || ("undefined").equals(gongysb_id)){
		}else if(gongysb_id.equals("-1")){
		}else{
			map.put("gongysb_id", gongysb_id);
		}
		if(("发布").equals(zhuangt)){
			map.put("zhuangt", 1);
		}else if(("未发布").equals(zhuangt)){
			map.put("zhuangt", 0);
		}else{
		}
		map.put("strdate", strdate);
		map.put("enddate", enddate);
		map.put("fabrq", fabrq);

		JSONObject json = rigmjhService.searchRigmjhList(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//添加
	@RequestMapping(value = "/updateRigmjh")
	public void updateRigmjh(@RequestParam String data,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> map = JSONObject.fromObject(data);
		rigmjhService.updateRigmjh(map);
	}

	@RequestMapping(value = "/insertRigmjh")
	public void insertRigmjh(@RequestParam String data,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> map = JSONObject.fromObject(data);
		map.put("LURY", ((Renyxx)session.getAttribute("user")).getMingc());
		rigmjhService.insertRigmjh(map);
	}
	
	//删除
	@RequestMapping(value = "/delRigmjh")
	public void delRigmjh(@RequestBody Long[] arr,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");

		int ret = rigmjhService.delRigmjh(arr);
		PrintWriter writer = null;
		
		
		try {
			writer = response.getWriter();
			writer.write(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//发布
	@RequestMapping(value = "/publishRigmjh")
	public void publishRigmjh(@RequestBody Long[] arr,
			HttpServletRequest request, HttpServletResponse response) {
		   int ret =rigmjhService.publishRigmjh(arr);
		   
		   PrintWriter writer = null;
			try {
				writer = response.getWriter();
				writer.write(ret);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
}
