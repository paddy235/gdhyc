package com.zhiren.fuelmis.dc.dao.jih;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface NiandjhzhibycDao {
	@SuppressWarnings("rawtypes")
	List getJihzbdyb();
	
	@SuppressWarnings("rawtypes")
	List getZhiFromniandjhzhib(Map<String, Object> map);
	
	int getIdByRiqAndDiancid(Map<String, Object> map);
	
	int updateniandjhzhib(Map<String, Object> map);
	
	int addniandjhzhib(Map<String, Object> map);
	
	int delByRiqAndDiancid(Map<String, Object> map);
	
	int DelThisYearData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getLastYearData(Map<String, Object> map);
	
	int CopyLastMonthData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List selectThreeData(Map<String, Object> map);
	
	int updateThreeDate(Map<String, Object> map);
	
	String getshenpstate(Map<String, Object> map);
}
