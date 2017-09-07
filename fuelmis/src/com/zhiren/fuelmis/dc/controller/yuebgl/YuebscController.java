package com.zhiren.fuelmis.dc.controller.yuebgl;

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

import com.zhiren.fuelmis.dc.service.yuebgl.IYuebscService;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("yuebgl/yuebsc")
public class YuebscController {
	
	@Autowired
	private IYuebscService yuebscService;

	@RequestMapping(value = "/getYueb")@ResponseBody
	public JSONArray getYueb(String search, HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = JSONObject.fromObject(search);
		return  yuebscService.getYueb(map);
	}
	
	@RequestMapping(value = "/yuebsc")
	public void yuebsc(@RequestParam String search,@RequestParam(defaultValue="") String data,HttpServletRequest request,HttpServletResponse response)throws Exception{
       try {
           response.setContentType("text/html;charset=UTF-8");
           Map<String, Object> map = JSONObject.fromObject(search);
           List<Map<String, Object>> yueb = JSONArray.fromObject(data);
           yuebscService.yuebsc(map, yueb);
       }catch (Exception e){
           e.printStackTrace();
           throw e;
       }
	}
}
