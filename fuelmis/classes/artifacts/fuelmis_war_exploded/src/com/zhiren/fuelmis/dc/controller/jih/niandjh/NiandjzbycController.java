package com.zhiren.fuelmis.dc.controller.jih.niandjh;

import com.zhiren.fuelmis.dc.dao.jih.YuedjhzhibycDao;
import com.zhiren.fuelmis.dc.service.jih.INiandjhzhibycService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("jihgl/nianjhlr")
public class NiandjzbycController {
	
	@Autowired
	private INiandjhzhibycService niandjhzhibycService;
	
	@Autowired
	private YuedjhzhibycDao niandjhzhibycDao;
	@RequestMapping(value="/getNiandjhzbycList")
	@ResponseBody
	public void getYuedjhzhibyc(String search,HttpServletRequest request , HttpServletResponse response , HttpSession session){
		PrintWriter writer  = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			List jihzbdybList = niandjhzhibycService.getJihzbdyb();
			String sql = "select ";
			//从JIHZBDYB表中查询出yuedjh_zhib表的列名和计算公式
			for (int i = 0; i < jihzbdybList.size(); i++) {
				Map zidmAndGongsMap = (HashMap) jihzbdybList.get(i);//字段和名称map
				if (null != zidmAndGongsMap.get("GONGS") && !"".equals(zidmAndGongsMap.get("GONGS")) && !"0".equals(zidmAndGongsMap.get("GONGS"))) {
					sql += zidmAndGongsMap.get("GONGS") + " AS " + zidmAndGongsMap.get("ZIDM") + ",";
				} else {
					sql += zidmAndGongsMap.get("ZIDM") + ",";
				}
			}
//		sql = sql.substring(0, sql.length()-1);//去掉最后一个逗号
			sql += "SANJ_ZT";
			Map map = JSONObject.fromObject(search);
			map.put("sql", sql);
			List zhiList = niandjhzhibycService.getZhiFromniandjhzhib(map);
			List<Map<String,Object>> list = new ArrayList();
			for (int j = 0; j < jihzbdybList.size(); j++) {
				Map<String, Object> jsonMap = new HashMap<String, Object>();
				Map zidmMap = (HashMap) jihzbdybList.get(j);
				if (zhiList.size() != 0) {
					Map zhiMap = (HashMap) zhiList.get(0);
					jsonMap.put("ZHI", zhiMap.get(zidmMap.get("ZIDM")));
					jsonMap.put("SANJ_ZT", zhiMap.get("SANJ_ZT"));
				} else {
					jsonMap.put("ZHI", "");
				}
				jsonMap.put("ID", zidmMap.get("ID"));
				jsonMap.put("XUH", zidmMap.get("XUH"));
				jsonMap.put("ZIDM", zidmMap.get("ZIDM"));
				jsonMap.put("MINGC", zidmMap.get("MINGC"));
				jsonMap.put("DANW", zidmMap.get("DANW"));
//				jsonMap.put("SANJ_ZT", zidmMap.get("SANJ_ZT"));
				jsonMap.put("GONGS", zidmMap.get("GONGS"));
				list.add(jsonMap);
			}
			String o=JSONArray.fromObject(list).toString();
				writer = response.getWriter();
				writer.write(o);


		}catch (Exception e){
			e.printStackTrace();

		}finally {
			writer.close();
		}
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/saveNiandjhzbycList")
	public void saveYuedjhzbycList(@RequestParam String search,@RequestParam String data,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = JSONObject.fromObject(search);
		List<Map<String,Object>> list=JSONArray.fromObject(data);
		for (Map<String,Object> m:list) {
			map.putAll(m);
			int count = niandjhzhibycService.getIdByRiqAndDiancid(map);
			if(count>0){
				niandjhzhibycService.updateniandjhzhib(map);
			}else{
				niandjhzhibycService.addniandjhzhib(map);
			}
			List threedata = niandjhzhibycDao.selectThreeData(map);
			if(threedata.size()>0){
				Map threedatamap = (Map) threedata.get(0);
				map.putAll(threedatamap);
				niandjhzhibycService.updateThreeDate(map);
			}
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
		int count = niandjhzhibycService.getIdByRiqAndDiancid(map);
		if(count>0){
			niandjhzhibycService.updateniandjhzhib(map);
		}else{
			niandjhzhibycService.addniandjhzhib(map);
		}
		List threedata = niandjhzhibycDao.selectThreeData(map);
		if(threedata.size()>0){
			Map threedatamap = (Map) threedata.get(0);
			map.put("haoyyml", threedatamap.get("HAOYYML").toString());
			map.put("meizbmdj", threedatamap.get("MEIZBMDJ").toString());
			map.put("rulzhbmdj", threedatamap.get("RULZHBMDJ").toString());
			niandjhzhibycService.updateThreeDate(map);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/zhibycdel")
	public void delzhibycByRiqAndDiancid(String search ,HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map=JSONObject.fromObject(search);
		int ret = niandjhzhibycService.delByRiqAndDiancid(map);
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
	
	


}
