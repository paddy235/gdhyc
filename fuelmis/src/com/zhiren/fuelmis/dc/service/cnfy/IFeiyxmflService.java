package com.zhiren.fuelmis.dc.service.cnfy;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface IFeiyxmflService {
	JSONObject getAllData();
	
	@SuppressWarnings("rawtypes")
	List getFenlkj();
	
	int addFeiyxmflData(Map<String, Object> map);
	
	String getMaxBianm();
	
	JSONObject getFeiyxmflById(String id);
	
	int updateFeiyxmfl(Map<String, Object> map);
	
	int changeState(Map<String, Object> map);
	
	int getState(String id);
	
}
