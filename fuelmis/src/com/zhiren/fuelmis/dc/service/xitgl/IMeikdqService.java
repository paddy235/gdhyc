package com.zhiren.fuelmis.dc.service.xitgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IMeikdqService {
	JSONObject getAll(Map<String,Object> map);
	
	JSONArray insertMeikdq(Map<String,Object> map);
	
	JSONArray getOne(Map<String,Object> map);
	
	JSONArray updateMeikdq(Map<String,Object> map);
}
