package com.zhiren.fuelmis.dc.service.yuebgl.yuebwh;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Map;

/** 
 * @author 陈宝露
 */
public interface IRucrlkcbmdjService {
	JSONObject getAll(Map<String, Object> map);
	
	JSONArray createData(Map<String, Object> map);
	
	JSONArray saveList(Map<String, Object> map);
	
	JSONArray delData(Map<String, Object> map);
	
	JSONArray check(Map<String, Object> map);
}