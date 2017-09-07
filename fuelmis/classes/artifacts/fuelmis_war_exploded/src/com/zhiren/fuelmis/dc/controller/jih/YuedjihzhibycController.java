package com.zhiren.fuelmis.dc.controller.jih;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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

import com.zhiren.fuelmis.dc.dao.jih.YuedjhzhibycDao;
import com.zhiren.fuelmis.dc.service.jih.IYuedjhzhibycService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/yuedjhzhibyc")
public class YuedjihzhibycController {
	
	@Autowired
	private IYuedjhzhibycService yuedjhzhibycService;
	
	@Autowired
	private YuedjhzhibycDao yuedjhzhibycDao;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getzhibycList")
	public void getYuedjhzhibyc(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String diancid = request.getParameter("diancid");
		String riq = request.getParameter("riq");
		List jihzbdybList = yuedjhzhibycService.getJihzbdyb();
		String sql = "select ";
		//从JIHZBDYB表中查询出yuedjh_zhib表的列名和计算公式
		for(int i = 0; i < jihzbdybList.size();i ++){
			Map zidmAndGongsMap = (HashMap) jihzbdybList.get(i);//字段和名称map
			if(null != zidmAndGongsMap.get("GONGS")&&!"".equals(zidmAndGongsMap.get("GONGS"))&&!"0".equals(zidmAndGongsMap.get("GONGS"))){
				sql += zidmAndGongsMap.get("GONGS") +" AS "+zidmAndGongsMap.get("ZIDM") +","; 
			}else{
				sql += zidmAndGongsMap.get("ZIDM")+",";
			}
		}
		sql = sql.substring(0, sql.length()-1);//去掉最后一个逗号
		Map map = new HashMap();
		map.put("diancid", diancid);
		map.put("riq", riq);
		map.put("sql", sql);
		List zhiList = yuedjhzhibycService.getZhiFromYuedjhzhib(map);
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
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/savezhibyc")
	public void saveYuedjhzhibyc(@RequestParam String id,@RequestParam String value,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String diancid = request.getParameter("diancid");
		String riq = request.getParameter("riq")+"-01";
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", riq);
		map.put("zhi", value);
		map.put("diancid", diancid);
		map.put("zidm", id);//字段名
		int count = yuedjhzhibycService.getIdByRiqAndDiancid(map);
		if(count>0){
			yuedjhzhibycService.updateYuedjhzhib(map);
		}else{
			yuedjhzhibycService.addYuedjhzhib(map);
		}
		List threedata = yuedjhzhibycDao.selectThreeData(map);
		if(threedata.size()>0){
			Map threedatamap = (Map) threedata.get(0);
			map.put("haoyyml", threedatamap.get("HAOYYML").toString());
			map.put("meizbmdj", threedatamap.get("MEIZBMDJ").toString());
			map.put("rulzhbmdj", threedatamap.get("RULZHBMDJ").toString());
			yuedjhzhibycService.updateThreeDate(map);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/save")
	public void save(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="") String diancid,
			HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		
		String riq_ = riq+"-01";
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", riq_);
		map.put("zhi", "515");
		map.put("diancid", diancid);
		map.put("zidm", "DIANCXXB_ID");//字段名
		int count = yuedjhzhibycService.getIdByRiqAndDiancid(map);
		if(count>0){
			yuedjhzhibycService.updateYuedjhzhib(map);
		}else{
			yuedjhzhibycService.addYuedjhzhib(map);
		}
		List threedata = yuedjhzhibycDao.selectThreeData(map);
		if(threedata.size()>0){
			Map threedatamap = (Map) threedata.get(0);
			map.put("haoyyml", threedatamap.get("HAOYYML").toString());
			map.put("meizbmdj", threedatamap.get("MEIZBMDJ").toString());
			map.put("rulzhbmdj", threedatamap.get("RULZHBMDJ").toString());
			yuedjhzhibycService.updateThreeDate(map);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/zhibycdel")
	public void delzhibycByRiqAndDiancid(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String diancid = request.getParameter("diancid");
		String riq = request.getParameter("riq");
		Map  map = new HashMap();
		map.put("diancid", diancid);
		map.put("riq", riq);
		int ret = yuedjhzhibycService.delByRiqAndDiancid(map);
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
		String riq = request.getParameter("riq");
		Map  map = new HashMap();
		map.put("diancid", diancid);
		map.put("riq", riq);
		int count = yuedjhzhibycService.getIdByRiqAndDiancid(map);
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
	@RequestMapping(value="/yuedjihzhibyccopy")
	public void yuedjihzhibyccopy(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String caoz = request.getParameter("caoz");//如果操作为“replace”,那就删除本月记录
		String diancid = request.getParameter("diancid");
		String riq = request.getParameter("riq");//本月日期
		
		Date date = DateUtil.StringToDate(riq, "yyyy-MM-dd");
		Date lastMonth = DateUtil.getLastMonth(date);
		String lastriq = DateUtil.dateToString(lastMonth,"yyyy-MM-dd");//上月日期
		
		Map map = new HashMap();
		map.put("diancid",diancid);
		map.put("riq",riq );
		map.put("lastriq",lastriq );
		String reback;
		List copylist = yuedjhzhibycService.getLastMonthData(map);
		if( "[]" == copylist.toString()){
			reback = "上月无数据，无法复制！！！";
		}else{
			if(null != caoz){
				yuedjhzhibycService.DelThisMonthData(map);//先删除已有的值
			}
			map.put("copylist", copylist);
			int  ret = yuedjhzhibycService.CopyLastMonthData(map);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({"unchecked", "rawtypes" })
	@RequestMapping(value="/getshenpstate")
	public void getshenpstate(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq")+"-01";
		String diancid = request.getParameter("diancid");
		Map map = new HashMap();
		map.put("riq",riq);
		map.put("diancid", diancid);
		PrintWriter writer = null;
		String state = yuedjhzhibycDao.getshenpstate(map);
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
}
