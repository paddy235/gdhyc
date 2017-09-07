package com.zhiren.fuelmis.dc.dao.jih;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface YuedjhzhibycDao {
	@SuppressWarnings("rawtypes")
	List getJihzbdyb();
	
	@SuppressWarnings("rawtypes")
	List getZhiFromYuedjhzhib(Map<String, Object> map);
	
	int getIdByRiqAndDiancid(Map<String, Object> map);
	
	int updateYuedjhzhib(Map<String, Object> map);
	
	int addYuedjhzhib(Map<String, Object> map);
	
	int delByRiqAndDiancid(Map<String, Object> map);
	
	int DelThisMonthData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getLastMonthData(Map<String, Object> map);
	
	int CopyLastMonthData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List selectThreeData(Map<String, Object> map);
	
	int updateThreeDate(Map<String, Object> map);
	
	String getshenpstate(Map<String, Object> map);
	
}
