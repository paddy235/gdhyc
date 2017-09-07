package com.zhiren.fuelmis.dc.service.jih;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface INiandcaigjhService {
	
	String getTabelData(String diancid,String year);
	
	JSONObject getNiandcaigData(Map<String, Object> map);
	
	JSONObject getNiandCaigById(String id);
	
	@SuppressWarnings("rawtypes")
	List getNiandcaigByDiancidAndRiq(Map<String, Object> map);//
	
	@SuppressWarnings("rawtypes")
	List getGongys();
	
	int DelNiandcaigByDiancidAndRiq(Map<String, Object> map);//
	
	int delNiandcaigById(String id);
	
	int addCaigData(Map<String, Object> map);
	
	int updateNiandcaigById(Map<String, Object> map);
	
	int CopyNiandcaigData(Map<String, Object> map);//
	
}
