package com.zhiren.fuelmis.dc.service.jiesgl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IMeikjsService {
	
	JSONArray getJiestz(Map<String,Object> map);
	
	JSONArray getRijstz(Map<String,Object> map);
	
	JSONObject getJiesList(Map<String,Object> map);
	
	JSONArray jies(Map<String, Object> map);
	
	Map<String, Object> getjiesd(String Jiesbh);

	
	void deleteJiesdByJiesdbh(String jiesbh);
	
	JSONObject getYuejsdList(Map<String,Object> map);
	
	JSONObject getjsdList(Map<String,Object> map);

	
	JSONArray create(Map<String, Object> map);

	JSONArray getHetong(Map<String, Object> map);

	List<Map<String, Object>> getGongysList(Map<String, Object> map);
	
	List<Map<String,Object>> getJiesdbh();
	
	List<Map<String, Object>> getPinzList(Map<String, Object> map);
	
	List<Map<String, Object>> getHetbh(Map<String, Object> map);

	List<Map<String,Object>> getZafjsList(Map<String, Object> map);

	List<Map<String,Object>> getYewlxList();
	
	List<Map<String,Object>> getJieszbsjbByJiesbh(String jiesbh);


	List<Map<String,Object>> getZafjswhList(Map<String, Object> m);

	void saveZafjswhList(Map<String, Object> m, List<Map<String, Object>> list);
	
	void saveJiesd_hyc(Map<String, Object> m);


	void zafJies(Map<String, Object> map);

	void deleteZafjs(Map<String, Object> map);

    void updateDatas(Map<String, Object> jiesdMap, List<Map<String, Object>> zengkmxList);
}
