package com.zhiren.fuelmis.dc.service.impl.xitgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zhiren.fuelmis.dc.dao.xitgl.GongysDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Gongys;
import com.zhiren.fuelmis.dc.service.xitgl.IGongysService;
import com.zhiren.fuelmis.dc.utils.Sequence;
import com.zhiren.fuelmis.dc.wsClient.FoundationDataClient;

/** 
 * @author 陈宝露
 */
@Service
public class GongysServiceImpl implements IGongysService {
	
	@Autowired
	private GongysDao gongysDao;
	
	@Autowired 
	private JdbcTemplate jdbcTemplate;

	@Override
	public JSONObject getAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Gongys> list = gongysDao.getAll(map);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i=0;i<list.size();i++){
			Gongys gongys = list.get(i);
			objs[i] = new Object[]{gongys.getId(),gongys.getXuh(),gongys.getBianm(),gongys.getMingc(),gongys.getQuanc(),
					gongys.getPiny(),gongys.getMeikxxb_id(),gongys.getShengfb_id(),gongys.getShangjgsbm(),
					gongys.getZhuangt(),gongys.getWenjmc()};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	@Transactional
	public JSONArray insertGongys(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			gongysDao.insertGongys(map);
			map.put("gongysb_id", map.get("id"));
			map.put("rid", Sequence.nextId());
//			gongysDao.insertGongysmkglb(map);
			gongysDao.insertGongysdcglb(map);
			result = 1;
			
//			List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from gongysb where id = "+map.get("id"));
//			FoundationDataClient.getInstance().uploadGongysb(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = -1;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray getOne(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = gongysDao.getOne(map);
		JSONArray jsonArray = new JSONArray();
		if(list!=null){
			jsonArray.add(list.get(0));
		}
		return jsonArray;
	}

	@Override
	@Transactional
	public JSONArray updateGongys(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			gongysDao.updateGongys(map);
			map.put("gongysb_id", map.get("id"));
//			gongysDao.updateGongysmkglb(map);
			result = 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = -1;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public void updateWenjmc(Map<String, Object> map) {
		// TODO Auto-generated method stub
		try{
			gongysDao.updateWenjmc(map);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public JSONArray switchGongys(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = gongysDao.switchGongys(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

}
