package com.zhiren.fuelmis.dc.service.hetgl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

/** 
 * @author 陈宝露
 */
public interface IChaxdyService {
	JSONArray getHetcx(Map<String, Object> map);
	
	List<Map<String, Object>> getHetbh(Map<String, Object> map);
	
	JSONArray getPingsyjb(Map<String, Object> map);
}
