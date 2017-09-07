package com.zhiren.fuelmis.dc.service.yuebgl.yuebwh;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface IDuibbmdjService {

//	JSONObject getMeiHy();

	int updateMeihy(List<Map<String, Object>> map);

	int insertHuayd(String riq);


	List<Map<String, Object>> getMeiHySiS(String riq);


	int delMeiHy(List<Object> id);

	List<Map<String, Object>> getMeiHy(Map<String, Object> map);


	void updateHuaybm(JSONArray array);
	
	List<Map<String ,Object>> getMeihyAll(Map<String, Object> map);//煤耗用sis查询
	
	void MeihyAdd(JSONArray jsonArray);  
	
	void DelMeihy(int id);
	
}
