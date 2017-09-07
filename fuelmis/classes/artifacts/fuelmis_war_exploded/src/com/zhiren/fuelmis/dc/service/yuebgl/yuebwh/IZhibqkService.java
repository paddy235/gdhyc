package com.zhiren.fuelmis.dc.service.yuebgl.yuebwh;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IZhibqkService {
	JSONObject getAll(Map<String,Object> map);
	
	void saveData(List<Map<String,Object>> listzhibqk,Map<String,Object> map);
	
	JSONArray delData(Map<String,Object> map);
	
	JSONArray update(Map<String,Object> map);
	
	void saveLeij(Map<String,Object> map);
}
