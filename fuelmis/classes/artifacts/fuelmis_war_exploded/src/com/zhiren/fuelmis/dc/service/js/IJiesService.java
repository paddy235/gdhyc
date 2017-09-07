package com.zhiren.fuelmis.dc.service.js;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface IJiesService {
	JSONObject getFapb();

	JSONObject getFap(Map<String, Object> conditionMap);


	List<Map<String, Object>> getJiesdhList(int id);

	JSONObject getFapxx(Map<String, Object> conditionMap);

	Integer getMaxBianm();

	int insertJies(Map<String, Object> map);

	String jsHuit(String zhuangt, String huitlc_id, String jiesdbh,
			String rebacker, String advice);

	List<Map<String, Object>> getFapById(String id);
}
