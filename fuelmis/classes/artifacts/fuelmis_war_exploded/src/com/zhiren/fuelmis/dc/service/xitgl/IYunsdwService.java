package com.zhiren.fuelmis.dc.service.xitgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IYunsdwService {
	JSONObject getAll(Map<String,Object> map);
	
	JSONArray insertYunsdw(Map<String,Object> map);
	
	JSONArray getOne(Map<String,Object> map);
	
	JSONArray updateYunsdw(Map<String,Object> map);
}
