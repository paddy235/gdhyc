package com.zhiren.fuelmis.dc.service.impl.yansgl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.dao.yansgl.WailyrlDao;
import com.zhiren.fuelmis.dc.service.yansgl.WailyrlService;

public class WailyrlServiceImpl implements WailyrlService{

	@Resource
	private WailyrlDao wailyrlDao;
	
	
	@Override
	public JSONArray getWailyrlAll(Map<String, Object> map) {
		List<Map<String, Object>> list = wailyrlDao.getWailyrlAll(map);
		JSONArray result = JSONArray.fromObject(list);
		return result;
	}

	@Override
	public void insertWailyrl(JSONArray json) {
		for(int i=0;i<json.size();i++){
			JSONObject jsonObject=json.getJSONObject(i);
			Map<String, Object> map = jsonObject.fromObject(jsonObject);
			wailyrlDao.insertWailyrl(map);
		}
	}

	@Override
	public void deleteWailyrl(int id) {
		wailyrlDao.deleteWailyrl(id);
	}

	@Override
	public void updateWailyrl(JSONArray json) {
		for(int i=0;i<json.size();i++){
			JSONObject jsonObject = json.getJSONObject(i);
			Map<String ,Object> map = jsonObject.fromObject(jsonObject);
			wailyrlDao.updateWailyrl(map);
		}
	}

}
