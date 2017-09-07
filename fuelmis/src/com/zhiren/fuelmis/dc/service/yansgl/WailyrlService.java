package com.zhiren.fuelmis.dc.service.yansgl;

import java.util.Map;

import net.sf.json.JSONArray;

public interface WailyrlService {
	
	JSONArray getWailyrlAll(Map<String ,Object> map);
	
	void insertWailyrl(JSONArray json);
	
	void deleteWailyrl(int id);
	
	void updateWailyrl(JSONArray json);
}
