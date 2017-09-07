package com.zhiren.fuelmis.dc.service.jih;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface IYuedcaigjhService {
	
	String getTabelData(String diancid,String riq);
	
	JSONObject getCaigData(Map<String, Object> map);
	
	JSONObject getYuedcaigById(String id);
	
	@SuppressWarnings("rawtypes")
	List getyueducaigByDiancidAndRiq(Map<String, Object> map);
	
	//品种
	@SuppressWarnings("rawtypes")
	List getPinz();
	
	//发站
	@SuppressWarnings("rawtypes")
	List getFaz(String riq);
	
	//供应商
	@SuppressWarnings("rawtypes")
	List getGongys();
	
	//计划口径
	@SuppressWarnings("rawtypes")
	List getJihkj();
	
	int addCaigData(Map<String, Object> map);
	
	int delCaigById(String id);
	
	int DelYuedcaigByDiancidAndRiq(Map<String, Object> map);
	
	int updateCaigById(Map<String, Object> map);
	
	int CopyYuedcaigData(Map<String, Object> map);
}
