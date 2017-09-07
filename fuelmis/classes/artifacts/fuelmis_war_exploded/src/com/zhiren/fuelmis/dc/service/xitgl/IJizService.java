package com.zhiren.fuelmis.dc.service.xitgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IJizService {
	JSONObject getAll(Map<String,Object> map);
	
	JSONArray insertJiz(Map<String,Object> map);
	
	JSONArray getOne(Map<String,Object> map);
	
	JSONArray updateJiz(Map<String,Object> map);
	
	JSONArray deleteJiz(Map<String,Object> map);
}
