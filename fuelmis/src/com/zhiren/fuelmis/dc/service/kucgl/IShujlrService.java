package com.zhiren.fuelmis.dc.service.kucgl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IShujlrService {
	JSONObject getAll(Map<String,Object> map);

	JSONObject insertPandxx(Map<String, Object> map);

	JSONObject updatePandxx(Map<String,Object> map);
	
	JSONObject updateFuj(Map<String,Object> map);
	
	JSONArray getCount(Map<String,Object> map);
	
	String submitData(Map<String,Object> map);

	void save(List<Map<String, Object>> list);
}
