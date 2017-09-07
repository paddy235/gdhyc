package com.zhiren.fuelmis.dc.controller.gongyspg.rijhzltb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
 


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

 

import com.zhiren.fuelmis.dc.service.gongyspg.rijhzltb.RijhzltbService;


/**
 * 
 * @author SZT
 *
 */
@Controller
@RequestMapping("gongyspg/rijhzltb")
public class RijhzltbController {
 
	@Resource
	private  RijhzltbService rijhzltbService;
 
	@RequestMapping(value = "/getRijhzltbAll")
	public void getRijhzltbAll(HttpServletRequest request,HttpServletResponse response,@RequestParam String danw,@RequestParam String jihrq,@RequestParam String zhuangt) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map= new HashMap<String, Object>(); 
		map.put("danw", danw);
		map.put("jihrq",jihrq);
		map.put("zhuangt", zhuangt);
		JSONArray json = (JSONArray) rijhzltbService.getRijhzltbAll(map);
		PrintWriter writer=null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
		@RequestMapping(value  = "/insertRijhzltb")
		public void insertRijhzltb(HttpServletRequest request, HttpServletResponse response , @RequestParam String data){
			response.setContentType("text/html;charset=UTF-8");
			try{
				JSONArray jsonArray = JSONArray.fromObject(data);
				rijhzltbService.insertRijhzltb(jsonArray);
			}catch(Exception e){
				e.printStackTrace();
			}	
		}

		@RequestMapping(value  = "/updateRijhzltb")
		public void updateRijhzltb(HttpServletRequest request, HttpServletResponse response , @RequestParam String data){
			response.setContentType("text/html;charset=UTF-8");
			try{
				JSONArray jsonArray = JSONArray.fromObject(data);
				rijhzltbService.updateRijhzltb(jsonArray);
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
	 
}
