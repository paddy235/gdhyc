package com.zhiren.fuelmis.dc.service.xitgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface ILicService {
	JSONObject getAll(Map<String,Object> map);
	
	JSONArray insertLic(Map<String,Object> map);
	
	JSONArray getOne(Map<String,Object> map);
	
	JSONArray updateLic(Map<String,Object> map);
	
	JSONArray delLic(Map<String,Object> map);
}
