package com.zhiren.fuelmis.dc.service.yuebgl.yuebwh;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IYoushcService {
	JSONObject getAll(Map<String,Object> map);
	
	JSONArray createData(Map<String,Object> map);
	
	JSONArray saveData(Map<String,Object> map);
	
	JSONArray delData(Map<String,Object> map);
	
	JSONArray check(Map<String,Object> map);
}
