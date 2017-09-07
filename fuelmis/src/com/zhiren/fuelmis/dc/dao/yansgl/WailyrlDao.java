package com.zhiren.fuelmis.dc.dao.yansgl;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;

public interface WailyrlDao {
	
	List<Map<String , Object>> getWailyrlAll(Map<String ,Object> map);
	
	void insertWailyrl(Map<String , Object>map);
	
	void deleteWailyrl(int id);
	
	void updateWailyrl(Map<String , Object>map);
	
}
