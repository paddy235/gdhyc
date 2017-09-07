package com.zhiren.fuelmis.dc.service.cnfy;

import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.entity.jih.Hetbean;

import net.sf.json.JSONObject;

public interface IZafhtService {
	
	JSONObject getZafhtByNianf(String nianf);
	
	int addhetData(Hetbean hetbean);
	
	int saveHetZaf(Map<String, Object> map);
	
	int delHetById(String id);
	
	int delHetzafById(String id);
	
	public JSONObject getZafhtById(String id);
	
	int updatehetData(Map<String, Object> map);
	
	int updatehetzafData(Map<String, Object> map);

	void submit(Map<String, Object> map);


}
