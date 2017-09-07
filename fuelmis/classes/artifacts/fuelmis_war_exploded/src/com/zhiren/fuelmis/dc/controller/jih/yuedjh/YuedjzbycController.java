package com.zhiren.fuelmis.dc.controller.jih.yuedjh;

import com.zhiren.fuelmis.dc.dao.jih.YuedjhzhibycDao;
import com.zhiren.fuelmis.dc.service.jih.IYuedjhzhibycService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
@RequestMapping("jihgl/yuejhlr")
public class YuedjzbycController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IYuedjhzhibycService yuedjhzhibycService;
	
	@Autowired
	private YuedjhzhibycDao yuedjhzhibycDao;
	@RequestMapping(value="/getYuedjhzbycList")
	@ResponseBody
	public void getYuedjhzhibyc(String search,HttpServletRequest request , HttpServletResponse response , HttpSession session){
		PrintWriter writer  = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			List jihzbdybList = yuedjhzhibycService.getJihzbdyb();
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
			List zhiList = yuedjhzhibycService.getZhiFromYuedjhzhib(map);
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
	@RequestMapping(value = "/saveYuedjhzbycList")
	public void saveYuedjhzbycList(@RequestParam String search,@RequestParam String data,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = JSONObject.fromObject(search);
		List<Map<String,Object>> list=JSONArray.fromObject(data);
		for (Map<String,Object> m:list) {
			map.putAll(m);
			int count = yuedjhzhibycService.getIdByRiqAndDiancid(map);
			if(count>0){
				yuedjhzhibycService.updateYuedjhzhib(map);
			}else{
				yuedjhzhibycService.addYuedjhzhib(map);
			}
			List threedata = yuedjhzhibycDao.selectThreeData(map);
			if(threedata.size()>0){
				Map threedatamap = (Map) threedata.get(0);
				map.putAll(threedatamap);
				yuedjhzhibycService.updateThreeDate(map);
			}
		}
		try{
			this.countBMDJ("515",map.get("riq").toString());
		}catch (Exception e){
			e.printStackTrace();
		}


	}
	private int countBMDJ(String diancxxb_id, String CurrODate) {
		String upsql ="SELECT ROUND(DECODE(EL1, 0, 0, BIAOMLHJ * 29.271 / EL1),2) HAOYYML,\n" +
				"          MEIZBMDJ,RULZHBMDJ\n" +
				"  FROM (SELECT DECODE((JIH_SL + SHANGYMKC),0,0,ROUND((JIH_REZ * JIH_SL + SHANGYMKCRZ * SHANGYMKC) /(JIH_SL + SHANGYMKC),2)) EL1,\n" +
				"          		ROUND(DECODE(BIAOMLHJ,0,0,(MEIZBML * MEIZBMDJ + RANYL * RANYDJ + ZAFJE) / BIAOMLHJ),2) RULZHBMDJ,\n" +
				"               MEIZBMDJ,BIAOMLHJ\n" +
				"          FROM (SELECT NVL(ZAF.ZAFJE, 0) ZAFJE,\n" +
				"                       NVL(JHCG.JIH_SL, 0) JIH_SL,\n" +
				"                       NVL(JHCG.JIH_DAOCJBHS, 0) JIH_DAOCJBHS,\n" +
				"                       NVL(JHCG.JIH_REZ, 0) JIH_REZ,\n" +
				"                       NVL(YUEZB.RUCRLRZC, 0) RUCRLRZC,\n" +
				"                       NVL(YUEZB.SHANGYMKC, 0) SHANGYMKC,\n" +
				"                       NVL(YUEZB.BIAOMLHJ, 0) BIAOMLHJ,\n" +
				"                       NVL(YUEZB.SHANGYMKCDJ, 0) SHANGYMKCDJ,\n" +
				"                       NVL(YUEZB.SHANGYMKCRZ, 0) SHANGYMKCRZ,\n" +
				"                       NVL(YUEZB.MEIZBML, 0) MEIZBML,\n" +
				"               		ROUND(DECODE((NVL(JHCG.JIH_REZ, 0) - NVL(YUEZB.RUCRLRZC, 0)),0,0,NVL(JHCG.JIH_DAOCJBHS, 0) * 29.271 /(NVL(JHCG.JIH_REZ, 0) - NVL(YUEZB.RUCRLRZC, 0))),2) MEIZBMDJ,\n" +
				"                       NVL(YUEZB.RANYL, 0) RANYL,\n" +
				"                       NVL(YUEZB.RANYDJ, 0) RANYDJ\n" +
				"                  FROM (SELECT SUM(CG.JIH_SL) JIH_SL,\n" +
				"                               ROUND(DECODE(SUM(JIH_SL),0,0,SUM(CG.JIH_SL * CG.JIH_DAOCJBHS) /SUM(JIH_SL)),2) JIH_DAOCJBHS,\n" +
				"                               ROUND(DECODE(SUM(JIH_SL),0,0,SUM(CG.JIH_SL * CG.JIH_REZ) /SUM(JIH_SL)),2) JIH_REZ\n" +
				"                          FROM YUEDJH_CAIG CG\n" +
				"                         WHERE DIANCXXB_ID = "+diancxxb_id+"\n" +
				"                           AND to_char(RIQ,'yyyy-MM') = '"+CurrODate+"') JHCG,\n" +
				"                       (SELECT SUM(YUCJE) ZAFJE\n" +
				"                        FROM YUEDJH_ZAF\n" +
				"                        WHERE DIANCXXB_ID = "+diancxxb_id+"\n" +
				"                        AND to_char(RIQ,'yyyy-MM') = '"+CurrODate+"') ZAF,\n" +
				"                       (SELECT ZB.RUCRLRZC,\n" +
				"								ZB.SHANGYMKC,\n" +
				"                               ZB.SHANGYMKCDJ,\n" +
				"                               ZB.MEIZBML,\n" +
				"                               ZB.SHANGYMKCRZ,\n" +
				"                               ZB.BIAOMLHJ,\n" +
				"                               ZB.RANYL,\n" +
				"                               ZB.RANYDJ\n" +
				"                          FROM YUEDJH_ZHIB ZB\n" +
				"                         WHERE DIANCXXB_ID = "+diancxxb_id+"\n" +
				"                           AND to_char(RIQ,'yyyy-MM') = '"+CurrODate+"') YUEZB) LY)";

		SqlRowSet rsl = jdbcTemplate.queryForRowSet(upsql);

		String updateSql="";
		if(rsl.next()) {
			double MEIZBMDJ=rsl.getDouble("MEIZBMDJ");
			double HAOYYML=rsl.getDouble("HAOYYML");
			double RLZHBMDJ=rsl.getDouble("RULZHBMDJ");
			updateSql = "update YUEDJH_ZHIB set MEIZBMDJ="+MEIZBMDJ+", HAOYYML="+HAOYYML+", RLZHBMDJ="+RLZHBMDJ+" where to_char(riq,'yyyy-MM') = '"+CurrODate+"' and diancxxb_id=" + diancxxb_id;
		}
		if(updateSql.length()>1){
			jdbcTemplate.update(updateSql);
			return 1;
		}else{
			return -1;
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
	public void delzhibycByRiqAndDiancid(String search ,HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map=JSONObject.fromObject(search);
		int ret = yuedjhzhibycService.delByRiqAndDiancid(map);
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
