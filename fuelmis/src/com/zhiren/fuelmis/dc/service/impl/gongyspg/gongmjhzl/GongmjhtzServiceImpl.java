package com.zhiren.fuelmis.dc.service.impl.gongyspg.gongmjhzl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiren.fuelmis.dc.dao.gongyspg.gongmjhzl.GongmjhtzDao;
import com.zhiren.fuelmis.dc.dao.gongyspg.gongmjhzl.GongmjhzlDao;
import com.zhiren.fuelmis.dc.service.gongyspg.gongmjhzl.GongmjhtzService;
import com.zhiren.fuelmis.dc.service.gongyspg.gongmjhzl.GongmjhzlService;
import com.zhiren.fuelmis.dc.utils.Sequence;

@Service("gongmjhtzService")
public class GongmjhtzServiceImpl implements GongmjhtzService{
	
	@Resource
	private GongmjhtzDao gongmjhtzDao;
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public List<Map<String , Object>> getGongmjhtzAll(Map<String, Object> map) {
		List<Map<String , Object>> list = gongmjhtzDao.getGongmjhtzAll(map);
		JSONArray result=JSONArray.fromObject(list);
		return result;
	}

	@Override
	public void updateGongmjh(JSONArray jsonArray) {
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Map<String ,Object> map = jsonObject.fromObject(jsonObject);
			gongmjhtzDao.updateGongmjh(map);
		}
	}

	@Override
	@Transactional
	public void insertGongmjhFab(JSONArray jsonArray) {
		List<Map<String,Object>> list=JSONArray.fromObject(jsonArray);
		  for (Map<String, Object> map : list) {
			  List<Map<String,Object>> lists=jdbcTemplate.queryForList("select * from rl_gys_rigmjhb where GONGYSB_ID = " +map.get("GONGYSB_ID")+" and riq = '"+map.get("JIHGMRQ")+"'");
			  if(lists.size()!=0){
				  String sql1= "update rl_gys_rigmjhb set jihml ="+map.get("ZHIBZ")+" where GONGYSB_ID="+map.get("GONGYSB_ID")+" and riq = '"+map.get("JIHGMRQ")+"'";
				  jdbcTemplate.update(sql1);
			  }else{
				  String sql= "insert INTO rl_gys_rigmjhb(ID,RIQ,GONGYSB_ID,JIHML,TIAOZL,ZHUANGT,FABRQ,FABRY,PINGFFAB_ID,LEIB) values("
						+Sequence.nextId()+ ",'"
				  		+ map.get("JIHGMRQ")+ "',"
				  		+ map.get("GONGYSB_ID") +" ,"
				  		+ map.get("ZHIBZ")  +" ,"
				  		+ "0.00"  +","
				  		+ " 0 " +" ,to_char(sysdate,'yyyy-MM-dd'),'"
				  		+ "管理员" + "',"
				  		+ map.get("PINGFFAB_ID") +","
				  		+ 1
				  		+")";
				  jdbcTemplate.update(sql);
			  }
		}
	}
}
