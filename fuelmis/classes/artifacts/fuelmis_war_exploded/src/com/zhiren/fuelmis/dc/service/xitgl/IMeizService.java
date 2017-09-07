package com.zhiren.fuelmis.dc.service.xitgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IMeizService {
	JSONObject getAll(Map<String,Object> map);
	
	JSONArray insertMeizxx(Map<String,Object> map);
	
	JSONArray getOne(Map<String,Object> map);
	
	JSONArray updateMeizxx(Map<String,Object> map);
	
	JSONArray delMeizxx(Map<String,Object> map);
}
