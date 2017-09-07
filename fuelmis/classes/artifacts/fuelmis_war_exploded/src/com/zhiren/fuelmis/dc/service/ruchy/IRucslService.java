package com.zhiren.fuelmis.dc.service.ruchy;


import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

/** 
 * @author 刘志宇
 */
public interface IRucslService {



	List<Map<String, Object>> getGongysList(String riq);

	List<Map<String, Object>> getChep(Map<String, Object> conditionMap);


	void updateChep(List<Map<String, Object>> a);
}
