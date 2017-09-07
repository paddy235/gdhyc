package com.zhiren.fuelmis.dc.service.yuebgl.yuebwh;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IHaochjService {
	JSONObject getAll(Map<String,Object> map);
	
	JSONArray createData(Map<String,Object> map);
	
	void saveData(List<Map> list) throws Exception;
	
	JSONArray delData(Map<String,Object> map);
	
	JSONArray check(Map<String,Object> map);
}
