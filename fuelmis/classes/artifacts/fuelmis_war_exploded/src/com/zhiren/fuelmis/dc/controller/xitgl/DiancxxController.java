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

import com.zhiren.fuelmis.dc.service.xitgl.IDiancxxService;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("/diancxx")
public class DiancxxController {

	@Autowired
	private IDiancxxService diancxxService;
	
	@RequestMapping(value = "/getAll")
	public void getAll(@RequestParam(defaultValue="515") String dianc,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", dianc);
		
		JSONObject jsonObject = diancxxService.selectAll(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/addDiancxx")
	public void addDiancxx(@RequestParam String info,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		@SuppressWarnings("unchecked")
		Map<String,Object> map = JSONObject.fromObject(info);
		map.put("id", System.currentTimeMillis());
		String caiyfs = map.get("caiyfs")!=null?map.get("caiyfs").toString():"";
		map.remove("caiyfs");
		map.put("caiyfs", caiyfs.equals("2")?"机械":"人工");
		String jilfs = map.get("jilfs")!=null?map.get("jilfs").toString():"";
		map.remove("jilfs");
		map.put("jilfs", jilfs.equals("2")?"检尺":"过衡");
		if(map.get("fuid")==null){
			map.remove("fuid");
			map.put("fuid", 0);
		}
		if(map.get("zhuangjrl")==null){
			map.remove("zhuangjrl");
			map.put("zhuangjrl", 0);
		}
		if(map.get("zuidkc")==null){
			map.remove("zuidkc");
			map.put("zuidkc", 0);
		}
		if(map.get("xianfhkc")==null){
			map.remove("xianfhkc");
			map.put("xianfhkc", 0);
		}
		if(map.get("rijhm")==null){
			map.remove("rijhm");
			map.put("rijhm", 0);
		}
		if(map.get("jiexx")==null){
			map.remove("jiexx");
			map.put("jiexx", 1);
		}
		if(map.get("jiexnl")==null){
			map.remove("jiexnl");
			map.put("jiexnl", 0);
		}
		JSONArray jsonArray = diancxxService.insertDiancxx(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getOneById")
	public void getOneById(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		JSONArray jsonArray = diancxxService.getOneById(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/updateDiancxx")
	public void updateDiancxx(@RequestParam String info,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		@SuppressWarnings("unchecked")
		Map<String,Object> map = JSONObject.fromObject(info);
		String caiyfs = map.get("caiyfs")!=null?map.get("caiyfs").toString():"";
		map.remove("caiyfs");
		map.put("caiyfs", caiyfs.equals("2")?"机械":"人工");
		String jilfs = map.get("jilfs")!=null?map.get("jilfs").toString():"";
		map.remove("jilfs");
		map.put("jilfs", jilfs.equals("2")?"检尺":"过衡");
		JSONArray jsonArray = diancxxService.updateDiancxx(map);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
