package com.zhiren.fuelmis.dc.service.impl.gongyspg.yuegmjh;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiren.fuelmis.dc.dao.gongyspg.yuegmjh.YuegmjhDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.gongyspg.yuegmjh.YuegmjhService;
import com.zhiren.fuelmis.dc.utils.Sequence;


@Service("yuegmjhService")
public class YuegmjhServiceImpl implements YuegmjhService{

	@Autowired
	private YuegmjhDao yuegmjhDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
/*	private String getXitxx_item(String leib, String mingc, String diancxxb_id, String defaultValue) {
		String value = defaultValue;
		String sql = "select zhi from xitxxb where leib='" + leib + "' and mingc='" + mingc + "' 	\n"
				+ " 	and diancxxb_id in (" + diancxxb_id + ") and beiz='使用'";

		Map<String, Object> m = jdbcTemplate.queryForMap(sql);
		value = m.get("zhi").toString();
		return value;
	}*/
	@Override
	public List<Map<String, Object>> getYuegmjhList(Map<String, Object> map) {
		String date = map.get("date").toString();
		// 当月份是1的时候显示01,
		String StrMonth = map.get("date").toString().substring(5, 7);
		String StrYear = map.get("date").toString().substring(0, 4);
		/* 供应商条件 */
		StringBuffer gongysConditions = new StringBuffer("");
		if (!map.get("gongysb_id").toString().equals("-1")) {
			gongysConditions.append(" and gongysb.id=" + map.get("gongysb_id") + "");
		}
		/* 提交状态条件 */
		StringBuffer fabztconditions = new StringBuffer();
		if (map.get("zhuangt").toString().equals("0")) {
			fabztconditions.append(" and rl_gys_yuegmjhb.zhuangt=0");
		} else {
			fabztconditions.append(" and rl_gys_yuegmjhb.zhuangt=1");
		}
		List<Map<String, Object>> l = null;
		String sql;
		sql = "SELECT distinct rl_gys_YUEGMJHMXB.ID, rl_gys_YUEGMJHMXB.JIHGMRQ, rl_gys_YUEGMJHMXB.ZHIBZ, rl_gys_YUEGMJHMXB.BEIZ\n"
				+ "  FROM rl_gys_YUEGMJHB , rl_gys_YUEGMJHMXB \n"
				+ " WHERE rl_gys_YUEGMJHMXB.yuegmjhb_id = rl_gys_YUEGMJHB.id "  
				+ " AND substr(JIHRQ,0,7) = '" + map.get("date") + "'\n" + " AND rl_gys_YUEGMJHMXB.ZHIBDM = 'SL'\n ";
		if (map.get("zhuangt").toString().equals("0")) {
			/* 目的：由于“提交”后，状态没有改变，但是需要执行“刷新”操作 */
			// 先判断当前选择的月份是否存在数据？
			l = jdbcTemplate.queryForList(sql + " order by jihgmrq");
			if (l.size() == 0 && !map.get("gongysb_id").toString().equals("-1")) {
			/*	if ("是".equals(this.getXitxx_item("月供煤计划", "月供煤计划条件", 515 + "", "否"))) {

					l = jdbcTemplate.queryForList("SELECT 0 AS ID,\n" +
							// "--当月份为1月时需要对年份进行特殊判断，起始日期为当前年份-1\n" +
							"       to_char((SELECT to_date('" + StrYear + "-'" + "||" + " QISRQ,'yyyy-MM-dd')\n"
							+ "          FROM rl_gys_YUEGMJHRQTJB Y\n" + "         WHERE substr(RIQ,0,2) = '" + StrMonth
							+ "') + (ROWNUM - 1),'yyyy-MM-dd') AS JIHGMRQ,\n" + "       0 AS ZHIBZ,\n"
							+ "       NULL AS BEIZ\n" + "  FROM DUAL\n" + "CONNECT BY ROWNUM <= (SELECT TO_DATE('"
							+ StrYear + "-'" + "||" + "JIESRQ, 'yyyy-mm-dd') -\n"
							+ "                              TO_DATE('" + StrYear + "-'" + "||"
							+ "QISRQ, 'yyyy-mm-dd') + 1\n" + "                         FROM rl_gys_YUEGMJHRQTJB Y\n"
							+ "                        WHERE substr(RIQ,0,2) = '" + StrMonth + "')");
				} else {*/
					l = jdbcTemplate.queryForList("SELECT to_char(TO_DATE('" + date
							+ "-01', 'yyyy-MM-dd') + (ROWNUM - 1),'yyyy-mm-dd') as JIHGMRQ,0 AS zhibz,'' AS beiz\n"
							+ "FROM ALL_OBJECTS\n" + "WHERE ROWNUM <= to_char(last_day(TO_DATE('" + date
							+ "-01','yyyy-mm-dd')),'dd')\n");

				/*}*/
			} else {
				// 根据“未审核”状态查询
				l = jdbcTemplate.queryForList(sql + fabztconditions + " order by jihgmrq");
			}

		} else {
			// 查询已经审核的数据
			l = jdbcTemplate.queryForList(sql + fabztconditions + " order by jihgmrq");
		}
		return l;
	}
	/*@Override
	public JSONArray updateYuegmjh(List<Long> list) {
		int result = 0;
		try{
			result = yuegmjhDao.updateYuegmjh(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;		
	} */

	@Override
	public JSONArray addYuegmjh(Map<String, Object> map) {
		int result = 1;
		try{
			result = yuegmjhDao.addYuegmjh(map);
		}catch(Exception e){
			result = 0;
			e.printStackTrace();
		}
		if(result>0){
			result=1;
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray delYuegmjh(Map<String, Object> map) {
		int result = 0;
		try{
			result = yuegmjhDao.delYuegmjh(map);
			result = yuegmjhDao.delYuegmjhmx(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject searchYuegmjh(Map<String, Object> map) {
		List<Map<String,Object>> list = yuegmjhDao.searchYuegmjh(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{dataMap.get("ID"),dataMap.get("JIHRQ"),dataMap.get("ZHIBZ"),dataMap.get("GONGYSB_ID")};
			jsonMap.put("zhuangt", dataMap.get("ZHUANGT"));
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	
	@Override
	public boolean cheakHetzt(Map<String, Object> map) {
		boolean result = false;
		Long hetsl = Long.parseLong(yuegmjhDao.getHetzt(map).toString());
		if(hetsl>0){
			result = true;
		}
		return result;
	}

	@Override
	public List<Long> getupdateYuegmjh(Map<String, Object> map) {
		List<Map<String,Object>> list = yuegmjhDao.searchYuegmjh(map);
		List<Long> result = new ArrayList<Long>();
		if(list.size()>0){
			for(int i = 0;i<list.size();i++){
				HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
				Long id = Long.parseLong(dataMap.get("ID").toString());
				result.add(id);
			}			
		}
		return result;
	}
	@Override@Transactional
	public void saveYuegmjh(Map<String, Object> yuegongmjh, List<Map<String, Object>> yuegongmjhmx) {
		String id=yuegmjhDao.getYuegmjhId(yuegongmjh);
		if(id!=null){
			yuegongmjh.put("ID", id);
			yuegmjhDao.updateYuegmjh(yuegongmjh);
			for (Map<String, Object> map : yuegongmjhmx) {
				if(map.get("ID")==null){
					map.put("ID", Sequence.nextId());
					map.put("YUEGMJHB_ID",id);
					yuegmjhDao.insertYuegmjhmx(map);
				}else{
					yuegmjhDao.updateYuegmjhmx(map);
				}
			}
		}else{
			String yuegongmjhId=Sequence.nextId();
			yuegongmjh.put("ID",yuegongmjhId);
			yuegmjhDao.insertYuegmjh(yuegongmjh);
			for (Map<String, Object> map : yuegongmjhmx) {
				if(map.get("ID")==null){
					map.put("YUEGMJHB_ID",yuegongmjhId);
					map.put("ID", Sequence.nextId());
					yuegmjhDao.insertYuegmjhmx(map);
				}else{
					yuegmjhDao.updateYuegmjhmx(map);
				}
			}
		}

	}

	@Override
	public void submitYuegmjh(Map<String, Object> c) {
		yuegmjhDao.submit(c);
	}


	


}
