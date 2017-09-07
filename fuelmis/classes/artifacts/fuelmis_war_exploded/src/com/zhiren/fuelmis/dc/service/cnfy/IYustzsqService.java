package com.zhiren.fuelmis.dc.service.cnfy;

import java.util.Map;

import net.sf.json.JSONObject;

public interface IYustzsqService {
	
	JSONObject getYustzsqData(Map<String, Object> map);
	
	int addYustzData(Map<String, Object> map);
	
	int delYustzById(String id);
	
	JSONObject getYustzById(String id);
	
	int updateYustzById(Map<String, Object> map);
	
}
