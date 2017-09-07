package com.zhiren.fuelmis.dc.service.impl.xitgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.xitgl.MeikdqDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Meikdq;
import com.zhiren.fuelmis.dc.service.xitgl.IMeikdqService;

/** 
 * @author 陈宝露
 */
@Service
public class MeikdqServiceImpl implements IMeikdqService {

	@Autowired
	private MeikdqDao meikdqDao;
	
	@Override
	public JSONObject getAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Meikdq> list = meikdqDao.getAll(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			Meikdq meikdq = list.get(i);
			objs[i] = new Object[]{meikdq.getId(),meikdq.getXuh(),meikdq.getMingc(),meikdq.getQuanc(),
					meikdq.getBianm(),meikdq.getShengfb_id()};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		
		return result;
	}

	@Override
	public JSONArray insertMeikdq(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = meikdqDao.insertMeikdq(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray getOne(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Meikdq> list = meikdqDao.getOne(map);
		JSONArray jsonArray = new JSONArray();
		if(list!=null){
			if(list.size()>0){
				Meikdq meikdq = list.get(0);
				jsonArray.add(meikdq);
			}
		}
		return jsonArray;
	}

	@Override
	public JSONArray updateMeikdq(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = meikdqDao.updateMeikdq(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

}
