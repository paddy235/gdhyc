package com.zhiren.fuelmis.dc.service.impl.yuebgl.yuebwh;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh.RulrzDao;
import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IRulrzService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;

/** 
 * @author 陈宝露
 */
@Service
public class RulrzServiceImpl implements IRulrzService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RulrzDao rulrzDao;

	@Override
	public JSONObject getAll(Map<String, Object> map) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = rulrzDao.getAll(map);
		jsonMap.put("data", list);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONArray createData(Map<String, Object> map) {
		String CurrZnDate =map.get("riq").toString();
		String CurrODate = map.get("riq").toString();
		String dcid=map.get("dianc").toString();	
		String sql="select nvl('本月', '') leix,\n" +
						"       m.diancxxb_id,\n" + 
						"       round(sum(m.fadhy+m.gongrhy+m.qity+m.feiscy), 0) meil,\n" + 
						"       decode(sum(m.fadhy+m.gongrhy+m.qity+m.feiscy),\n" + 
						"              0,\n" + 
						"              0,\n" + 
						"              round(sum(h.qnet_ar * (m.fadhy+m.gongrhy+m.qity+m.feiscy)) / sum(m.fadhy+m.gongrhy+m.qity+m.feiscy), 2)) qnet_ar,\n" + 
						"       decode(sum(m.fadhy+m.gongrhy+m.qity+m.feiscy), 0, 0, round(sum(h.mt * (m.fadhy+m.gongrhy+m.qity+m.feiscy)) / sum(m.fadhy+m.gongrhy+m.qity+m.feiscy), 2)) mt,\n" + 
						"       decode(sum(m.fadhy+m.gongrhy+m.qity+m.feiscy),\n" + 
						"              0,\n" + 
						"              0,\n" + 
						"              round(sum(h.aar * (m.fadhy+m.gongrhy+m.qity+m.feiscy)) / sum(m.fadhy+m.gongrhy+m.qity+m.feiscy), 2)) aar,\n" + 
						"       decode(sum(m.fadhy+m.gongrhy+m.qity+m.feiscy),\n" + 
						"              0,\n" + 
						"              0,\n" + 
						"              round(sum(h.vdaf * (m.fadhy+m.gongrhy+m.qity+m.feiscy)) / sum(m.fadhy+m.gongrhy+m.qity+m.feiscy), 2)) vdaf,\n" + 
						"       decode(sum(m.fadhy+m.gongrhy+m.qity+m.feiscy),\n" + 
						"              0,\n" + 
						"              0,\n" + 
						"              round(sum(h.std * (m.fadhy+m.gongrhy+m.qity+m.feiscy)) / sum(m.fadhy+m.gongrhy+m.qity+m.feiscy), 2)) std\n" + 
						"  from rl_rul_meihyb m, rl_hy_huaydb h\n" + 
						" where m.huayd_id = h.huayd_id\n" + 
						"   and substr(m.rulrq, 0, 7) = '"+CurrODate+"'\n" + 
						"   and m.diancxxb_id in ("+dcid+")\n" + 
						" group by m.diancxxb_id";
		List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);	
		StringBuffer bf=new StringBuffer();	
		bf.append(" delete from rulmrzybb r where r.riq='"+CurrODate+"' and r.diancxxb_id in ("+dcid+")");
		jdbcTemplate.update(bf.toString());
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> m = list.get(i);
			bf=new StringBuffer();
			bf.append(" insert into rulmrzybb (id, diancxxb_id, fenx,rulml,qnet_ar, mt, aar, vdaf, std, riq) "
					+ "values("+Sequence.nextId()+"," +
					m.get("DIANCXXB_ID") +",'"+m.get("LEIX")+"',"+m.get("MEIL")+","+
					m.get("QNET_AR") +","+m.get("MT")+","+m.get("AAR")+","+
					m.get("VDAF")+","+m.get("STD")+",'"+CurrODate+
					"')");
			jdbcTemplate.update(bf.toString());
		}	
        Date date=DateUtil.StringToDate(CurrZnDate, "yyyy-MM");
		String lastMonth=DateUtil.dateToString(DateUtil.getLastMonth(date), "yyyy-MM");//上一个月时间
		bf=new StringBuffer();
		bf.append(" select nvl('累计','') leix,r.diancxxb_id, round_new(sum(r.rulml),0) meil,\n");
		bf.append(" decode(sum(r.rulml),0,0,round(sum(r.qnet_ar*r.rulml)/sum(r.rulml),2) ) qnet_ar,\n");
		bf.append(" decode(sum(r.rulml),0,0,round(sum(r.mt*r.rulml)/sum(r.rulml),2) )  mt,\n");
		bf.append(" decode(sum(r.rulml),0,0,round(sum(r.aar*r.rulml)/sum(r.rulml),2) )  aar,\n");
		bf.append(" decode(sum(r.rulml),0,0,round(sum(r.vdaf*r.rulml)/sum(r.rulml),2) )  vdaf,\n");
		bf.append(" decode(sum(r.rulml),0,0,round(sum(r.std*r.rulml)/sum(r.rulml),2) )  std \n");
		bf.append("  from ( \n");
		
		bf.append(" select * from rulmrzybb where substr(riq,0,7)= '"+CurrODate+"' and fenx='本月' and diancxxb_id  in ("+dcid+") \n");//本月的数据
		bf.append(" union\n");
		bf.append(" select * from rulmrzybb where substr(riq,0,7)= '"+lastMonth+"' and fenx='累计' and diancxxb_id  in ("+dcid+") \n");
		
		bf.append(" ) r  \n");
		bf.append("  group by r.diancxxb_id \n");
		
		
		List<Map<String,Object>> l=jdbcTemplate.queryForList(bf.toString());
		
		for (int i = 0; i < l.size(); i++) {
			Map<String, Object> m = l.get(i);
			bf=new StringBuffer();
			bf.append(" insert into rulmrzybb (id, diancxxb_id, fenx,rulml,qnet_ar, mt, aar, vdaf, std, riq) "
					+ "values("+Sequence.nextId()+"," +
					m.get("DIANCXXB_ID") +",'"+m.get("LEIX")+"',"+m.get("MEIL")+","+
					m.get("QNET_AR") +","+m.get("MT")+","+m.get("AAR")+","+
					m.get("VDAF")+","+m.get("STD")+",'"+CurrODate+
					"')");
			jdbcTemplate.update(bf.toString());
		}
		JSONArray a = new JSONArray();
		a.add(1);
		return a;
	}

	@Override
	public JSONArray saveData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray delData(Map<String, Object> map) {
		String diancxxb_id = map.get("dianc").toString();
		String CurrODate =map.get("riq").toString();;// DateUtil.FormatOracleDate(strDate[0]+"-"+strDate[1]+"-01");
		JSONArray a=new JSONArray();
		String strSql=
			"delete from rulmrzybb where id in (select id from rulmrzybb where substr(riq,0,7)='"
			+CurrODate+"' and diancxxb_id="+diancxxb_id+")";
		int flag = jdbcTemplate.update(strSql);
		if(flag>0){
			flag=1;
		}else{
			flag=-1;
		}
		a.add(flag);
		return a;
	}

	@Override
	public JSONArray check(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
