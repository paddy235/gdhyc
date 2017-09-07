package com.zhiren.fuelmis.dc.service.jih;

import java.util.List;
import java.util.Map;

public interface IYuedjhzhibycService {
	
	@SuppressWarnings("rawtypes")
	List getJihzbdyb();
	
	@SuppressWarnings("rawtypes")
	List getZhiFromYuedjhzhib(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getLastMonthData(Map<String, Object> map);
	
	int getIdByRiqAndDiancid(Map<String, Object> map);
	
	int updateYuedjhzhib(Map<String, Object> map);
	
	int addYuedjhzhib(Map<String, Object> map);
	
	int delByRiqAndDiancid(Map<String, Object> map);
	
	int DelThisMonthData(Map<String, Object> map);
	
	int CopyLastMonthData(Map<String, Object> map);
	
	int updateThreeDate(Map<String, Object> map);
	
}
