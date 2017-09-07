package com.zhiren.fuelmis.dc.service.cnfy;

import java.util.Map;

import net.sf.json.JSONObject;

public interface IFeiyxmwhService {
	
	JSONObject getAllData();
	
	int addfeiyxmwhData(Map<String, Object> map);
	
	String getMaxBianm();
	
	JSONObject getfeiyxmwhById(String id);
	
	int updatefeiyxmwh(Map<String, Object> map);
	
	int changeState(Map<String, Object> map);
	
	int getState(String id);
	
}
