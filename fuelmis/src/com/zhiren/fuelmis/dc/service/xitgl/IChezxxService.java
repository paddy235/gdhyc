package com.zhiren.fuelmis.dc.service.xitgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IChezxxService {
	JSONObject getAll(Map<String,Object> map);
	
	JSONArray insertChezxx(Map<String,Object> map);
	
	JSONArray getOne(Map<String,Object> map);
	
	JSONArray updateChezxx(Map<String,Object> map);
	
	JSONArray deleteChezxx(Map<String,Object> map);
}
