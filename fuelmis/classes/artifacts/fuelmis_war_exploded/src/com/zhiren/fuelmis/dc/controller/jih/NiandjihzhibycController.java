package com.zhiren.fuelmis.dc.controller.jih;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.dao.jih.NiandjhzhibycDao;
import com.zhiren.fuelmis.dc.service.jih.INiandjhzhibycService;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/niandjhzhibyc")
public class NiandjihzhibycController {
	
	@Autowired
	private INiandjhzhibycService niandjhzhibycService;
	
	@Autowired 
	private NiandjhzhibycDao niandjhzhibycDao;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getzhibycList")
	public void getniandjhzhibyc(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String diancid = request.getParameter("diancid");
		String riq = request.getParameter("nianf")+"-01-01";
		List jihzbdybList = niandjhzhibycService.getJihzbdyb();
		String sql = "select ";
		//从JIHZBDYB表中查询出niandjh_zhib表的列名和计算公式
		for(int i = 0; i < jihzbdybList.size();i ++){
			Map zidmAndGongsMap = (HashMap) jihzbdybList.get(i);//字段和名称map
			if(null != zidmAndGongsMap.get("GONGS")&&!"".equals(zidmAndGongsMap.get("GONGS"))&&!"0".equals(zidmAndGongsMap.get("GONGS"))){
				sql += zidmAndGongsMap.get("GONGS") +" AS "+zidmAndGongsMap.get("ZIDM") +","; 
			}else{
				sql += zidmAndGongsMap.get("ZIDM")+",";
			}
		}
//		sql = sql.substring(0, sql.length()-1);//去掉最后一个逗号
		sql += "SANJ_ZT";
		Map map = new HashMap();
		map.put("diancid", diancid);
		map.put("riq", riq);
		map.put("sql", sql);
		List zhiList = niandjhzhibycService.getZhiFromniandjhzhib(map);
		List list = new ArrayList();
		for(int j=0;j<jihzbdybList.size();j++){
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			Map zidmMap = (HashMap)jihzbdybList.get(j);
			if(zhiList.size() != 0){
				Map zhiMap = (HashMap) zhiList.get(0);
				jsonMap.put("ZHI", zhiMap.get(zidmMap.get("ZIDM")));
			}else{
				jsonMap.put("ZHI", "");
			}
			jsonMap.put("ID", zidmMap.get("ID"));
			jsonMap.put("XUH", zidmMap.get("XUH"));
			jsonMap.put("ZIDM",zidmMap.get("ZIDM"));
			jsonMap.put("MINGC",zidmMap.get("MINGC"));
			jsonMap.put("DANW", zidmMap.get("DANW"));
			
			list.add(jsonMap);
		}
		Map retMap = new HashMap();
		retMap.put("data", list);
		JSONObject result = JSONObject.fromObject(retMap);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(result.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping(value = "/savezhibyc")
	public void saveniandjhzhibyc(@RequestParam String id,@RequestParam String value,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String diancid = request.getParameter("diancid");
		String riq = request.getParameter("nianf")+"-01-01";
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", riq);
		map.put("zhi", value);
		map.put("diancid", diancid);
		map.put("zidm", id);//字段名
		int count = niandjhzhibycService.getIdByRiqAndDiancid(map);
		int result = -1;
		if(count>0){
			result = niandjhzhibycService.updateniandjhzhib(map);
		}else{
			result = niandjhzhibycService.addniandjhzhib(map);
		}
		List threedata = niandjhzhibycDao.selectThreeData(map);
		if(threedata.size()>0){
			Map threedatamap = (Map) threedata.get(0);
			map.put("meizbmdj", threedatamap.get("MEIZBMDJ").toString());
			map.put("zafje", threedatamap.get("ZAFJE").toString());
			map.put("rulzhbmdj", threedatamap.get("RULZHBMDJ").toString());
			niandjhzhibycService.updateThreeDate(map);
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/zhibycdel")
	public void delzhibycByRiqAndDiancid(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String diancid = request.getParameter("diancid");
		String riq = request.getParameter("nianf")+"-01-01";
		Map  map = new HashMap();
		map.put("diancid", diancid);
		map.put("riq", riq);
		int ret = niandjhzhibycService.delByRiqAndDiancid(map);
		String reback;
		if(ret>0){
			reback="删除成功！！！";
		}else{
			reback="删除失败！！！";
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(reback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getzhibycCount")
	public void getZhibycCount(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String diancid = request.getParameter("diancid");
		String riq = request.getParameter("nianf")+"-01-01";
		Map  map = new HashMap();
		map.put("diancid", diancid);
		map.put("riq", riq);
		int count = niandjhzhibycService.getIdByRiqAndDiancid(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(count+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/niandjihzhibyccopy")
	public void niandjihzhibyccopy(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String caoz = request.getParameter("caoz");//如果操作为“replace”,那就删除本年记录
		String diancid = request.getParameter("diancid");
		String nianf = request.getParameter("nianf");//今年
		long year =  Integer.parseInt(nianf)-1;
		String lastyear = year + "";//上一年
		Map map = new HashMap();
		map.put("diancid",diancid);
		map.put("riq",nianf +"-01-01");
		map.put("lastyear",lastyear+"-01-01" );
		String reback;
		List copylist = niandjhzhibycService.getLastYearData(map);
		if("[]" == copylist.toString()){
			reback = "上年计划无数据，无法复制！！！";
		}else{
			if(null != caoz){
				niandjhzhibycService.DelThisYearData(map);//先删除已有的值
			}
			map.put("copylist", copylist);
			int  ret = niandjhzhibycService.CopyLastMonthData(map);
			if(ret>0){
					reback="复制上年计划成功！！！";
			}else{
					reback="复制上年计划失败！！！";
			}
		}
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(reback);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({"unchecked", "rawtypes" })
	@RequestMapping(value="/getshenpstate")
	public void getshenpstate(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String nianf = request.getParameter("nianf")+"-01-01";
		String diancid = request.getParameter("diancid");
		Map map = new HashMap();
		map.put("nianf",nianf);
		map.put("diancid", diancid);
		PrintWriter writer = null;
		String state = niandjhzhibycDao.getshenpstate(map);
		if(state == null){
			state = "0";
		}
		try {
			writer = response.getWriter();
			writer.write(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/save")
	public void save(@RequestParam(defaultValue="") String nianf,@RequestParam(defaultValue="") String diancid,
			HttpServletRequest request,HttpServletResponse response){
		
		
		String riq = nianf+"-01-01";
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", riq);
		map.put("diancid", diancid);
		map.put("zhi", "515");
		map.put("zidm", "QITFY");//字段名
		int count = niandjhzhibycService.getIdByRiqAndDiancid(map);
		@SuppressWarnings("unused")
		int result = -1;
		if(count>0){
			result = niandjhzhibycService.updateniandjhzhib(map);
		}else{
			result = niandjhzhibycService.addniandjhzhib(map);
		}
		List threedata = niandjhzhibycDao.selectThreeData(map);
		if(threedata.size()>0){
			Map threedatamap = (Map) threedata.get(0);
			map.put("meizbmdj", threedatamap.get("MEIZBMDJ").toString());
			map.put("zafje", threedatamap.get("ZAFJE").toString());
			map.put("rulzhbmdj", threedatamap.get("RULZHBMDJ").toString());
			niandjhzhibycService.updateThreeDate(map);
		}
	}
	
	
	
}
