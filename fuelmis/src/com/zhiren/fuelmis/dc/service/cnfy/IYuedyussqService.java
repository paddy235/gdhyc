package com.zhiren.fuelmis.dc.service.cnfy;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface IYuedyussqService {
	
	JSONObject getYussqData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getFeiyMingc();
	
	int addYuedysData(Map<String, Object> map);
	
	int delYuedyusById(String id);
	
	JSONObject getYuedyusById(String id);
	
	int updateYudysyId(Map<String, Object> map);
	
}
