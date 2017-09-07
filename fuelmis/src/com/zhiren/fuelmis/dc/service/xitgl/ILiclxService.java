package com.zhiren.fuelmis.dc.service.xitgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface ILiclxService {
	JSONObject getAll(Map<String,Object> map);
	
	JSONArray insertLiclx(Map<String,Object> map);
	
	JSONArray getOne(Map<String,Object> map);
	
	JSONArray updateLiclx(Map<String,Object> map);
	
	JSONArray delLiclx(Map<String,Object> map);
}
