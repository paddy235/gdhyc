package com.zhiren.fuelmis.dc.service.xitgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IGongysService {
	JSONObject getAll(Map<String,Object> map);
	
	JSONArray insertGongys(Map<String,Object> map);
	
	JSONArray getOne(Map<String,Object> map);
	
	JSONArray updateGongys(Map<String,Object> map);
	
	void updateWenjmc(Map<String,Object> map);
	
	JSONArray switchGongys(Map<String,Object> map);
}
