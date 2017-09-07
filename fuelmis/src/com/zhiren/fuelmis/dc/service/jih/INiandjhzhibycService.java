package com.zhiren.fuelmis.dc.service.jih;

import java.util.List;
import java.util.Map;

public interface INiandjhzhibycService {
	
	@SuppressWarnings("rawtypes")
	List getJihzbdyb();
	
	@SuppressWarnings("rawtypes")
	List getZhiFromniandjhzhib(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getLastYearData(Map<String, Object> map);
	
	int getIdByRiqAndDiancid(Map<String, Object> map);
	
	int updateniandjhzhib(Map<String, Object> map);
	
	int addniandjhzhib(Map<String, Object> map);
	
	int delByRiqAndDiancid(Map<String, Object> map);
	
	int DelThisYearData(Map<String, Object> map);
	
	int CopyLastMonthData(Map<String, Object> map);
	
	int updateThreeDate(Map<String, Object> map);
}
