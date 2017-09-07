package com.zhiren.fuelmis.dc.service.jiesgl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IJiesbbService {
	List<String> getAllJiesbh(Map<String,Object> map);
	
	JSONArray getJiesd(Map<String, Object> map);
	
	JSONObject gethycJiesd(Map<String, Object> map);
	
	JSONArray getYansmx(Map<String, Object> map);
	
	JSONArray getGuohd(Map<String, Object> map);
	
	JSONArray getDanpcmx(Map<String, Object> map);
}
