package com.zhiren.fuelmis.dc.service.rucsl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IShulshService {
	List<Map<String,Object>> getFahrq();
	
	JSONObject getTableData(Map<String, Object> map);
	
	JSONObject getTableData_MX(Map<String, Object> map);
	/**
	 * map.put("samcode",samcode)
	 * @param map
	 * @return
	 */
	JSONArray shenh(Map<String, Object> map);
	
	JSONArray huit(Map<String, Object> map);
	
	void insertShulshLogs(Map<String, Object> map);

	List<Map<String,Object>> getJiesxxList(String samcode);

}
