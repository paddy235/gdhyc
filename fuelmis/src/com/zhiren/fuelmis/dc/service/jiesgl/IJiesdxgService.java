package com.zhiren.fuelmis.dc.service.jiesgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IJiesdxgService {
	JSONArray getJiesdbh(Map<String,Object> map);
	
	JSONObject getJiesd(Map<String,Object> map);
	
	JSONArray save(Map<String,Object> map);
	
	JSONArray delete(Map<String,Object> map);
	
	JSONArray submit(Map<String,Object> map);
	
	JSONArray check(Map<String,Object> map);
}
