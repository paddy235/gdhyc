package com.zhiren.fuelmis.dc.service.xitgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IPinzService {
	JSONObject getAll(Map<String,Object> map);
	
	JSONArray insertPinzxx(Map<String,Object> map);
	
	JSONArray getOne(Map<String,Object> map);
	
	JSONArray updatePinzxx(Map<String,Object> map);
	
	JSONArray delPinzxx(Map<String,Object> map);
}
