package com.zhiren.fuelmis.dc.service.yuebgl.yuebwh;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IHaocmxService {
	JSONObject getAll(Map<String,Object> map);
	
	int createData(Map<String,Object> map);
	
	JSONArray saveData(List<Map<String,Object>> list) throws Exception;
	
	JSONArray delData(Map<String,Object> map);
	
	JSONArray check(Map<String,Object> map);
}
