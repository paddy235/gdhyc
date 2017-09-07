package com.zhiren.fuelmis.dc.service.cnfy;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface IFeiyxmsqService {
	
	JSONObject getFeiyxmData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getFeiyxmfl();
	
	@SuppressWarnings("rawtypes")
	List getFeiyxmsx();
	
	int addFeiyxmData(Map<String, Object> map);
	
	int delFeiyxm(String id);
	
	JSONObject getFeiyxmById(String id);
	
	int updateFeiyxmById(Map<String, Object> map);
	
}
