package com.zhiren.fuelmis.dc.service.gongyspg.gongmjhzl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface GongmjhtzService {
	public List<Map<String , Object>> getGongmjhtzAll(Map<String, Object> map);
	
	 void updateGongmjh(JSONArray jsonArray);
	 
	 void insertGongmjhFab(JSONArray jsonArray);
}
