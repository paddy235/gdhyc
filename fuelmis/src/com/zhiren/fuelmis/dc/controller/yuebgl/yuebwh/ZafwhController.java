package com.zhiren.fuelmis.dc.controller.yuebgl.yuebwh;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IZafwhService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;

import net.sf.json.JSONArray;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("yuebgl/yuebwh/zafwh")
public class ZafwhController {
	
	@Autowired
	private IZafwhService zafwhService;
	
	@RequestMapping(value = "/getAll")
	public void getAll(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="515") String dianc,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?DateUtil.getDayOfMonth(null):riq);
		map.put("dianc", "-1".equals(dianc)?"":dianc);
		String caoz = request.getParameter("caoz");
		response.setContentType("text/html;charset=UTF-8");
		
		JSONArray jsonArray= null;
				
		int intnianf = Integer.parseInt(riq.substring(0,4));
		int intYuef=Integer.parseInt(riq.substring(5,7));
		
		
		if (intnianf<=2016 && intYuef <=2) {
			jsonArray=zafwhService.getAll(map);
		}else{
			jsonArray=zafwhService.getAll_new(map);
		}
		
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			if(null != caoz){
				if(!"{\"data\":[]}".equals(jsonArray.toString())){
				writer.write("1");//提示是否继续复制并替换
			}else{
				writer.write("0"); //复制上月计划操作
			}
			
		}else{
			writer.write(jsonArray.toString());
		}} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getZfmingc")
	public void getZfmingc(HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(zafwhService.getZfmingc().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveZf")
	public void saveZf(@RequestParam String data,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
//		try {
//			data=new String(data.getBytes("iso-8859-1"),"UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		List<Map<String,Object>> l=JSONArray.fromObject(data);
		zafwhService.saveZf(l);
	}

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delZf")
	public void delZf(@RequestParam String data,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		List<Map<String,Object>> l=JSONArray.fromObject(data);
		zafwhService.deletezf(l);
	}
	
	@RequestMapping(value="/copyzafei")
	public void copyzafei(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String caoz = request.getParameter("caoz");//如果操作为“replace”,那就删除本月记录
		String diancid = request.getParameter("dianc");
		String riq = request.getParameter("riq");//本月日期
		String lastriq = "";//上月日期
		int month;
		String oldchar;
		String newChar;
		oldchar = riq.substring(riq.indexOf("-")+1);
		month = Integer.parseInt(oldchar)-1;
		if(month<10){
			newChar = "-0"+month;
		}else{
			newChar = "-"+month;
		}
		lastriq = riq.replace("-"+oldchar, newChar);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dianc",diancid);
		map.put("riq",riq );
		map.put("lastriq",lastriq );
		String reback;
		List<Map<String,Object>> copylist = zafwhService.getZafeiByDiancidAndRiq(map);
		
		if("[]" == copylist.toString()){
			reback = "上月计划无数据，无法复制！！！";
		}else{
			if(null != caoz){
				zafwhService.DelZafeiByDiancidAndRiq(map);//先删除已有的值
			}
			int ret=0;
			for (int i = 0; i < copylist.size(); i++) {
				Map<String,Object> maps=(Map<String,Object>)copylist.get(i);
				maps.put("dianc",diancid);
				maps.put("riq",riq);
				maps.put("ID",Sequence.nextId());
				ret = zafwhService.CopyZafeiData(maps);
			}
			if(ret>0){
					reback="复制上月计划成功！！！";
			}else{
					reback="复制上月计划失败！！！";
			}
		}
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(reback);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
