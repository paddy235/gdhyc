package com.zhiren.fuelmis.dc.service.rulgl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IXianggwhService {

//	JSONObject getMeiHy();

	int updateMeihy(List<Map<String, Object>> map);



	int insertHuayd(String riq);


	List<Map<String, Object>> getMeiHySiS(String riq);


	int delMeiHy(List<Object> id);

	JSONObject getMeiHy(String haoyrq, String diancxxb_id);

	JSONObject getRulmpp(String riq, String dIANCXXB_ID);

	void updateHuaybm(JSONArray array);
	
	List<Map<String ,Object>> getMeihyAll(Map<String ,Object> map);//煤耗用sis查询
	
	void MeihyAdd(JSONArray jsonArray);  
	
	void DelMeihy(int id);
	
}
