package com.zhiren.fuelmis.dc.service.gongyspg.rijhzltb;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface RijhzltbService {
	public List<Map<String , Object>> getRijhzltbAll(Map<String, Object> map);
	 
	 void insertRijhzltb(JSONArray jsonArray);
	 
	 void updateRijhzltb(JSONArray jsonArray);
}
