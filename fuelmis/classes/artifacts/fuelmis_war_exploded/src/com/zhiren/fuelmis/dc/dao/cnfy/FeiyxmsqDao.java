package com.zhiren.fuelmis.dc.dao.cnfy;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface FeiyxmsqDao {
	@SuppressWarnings("rawtypes")
	List getFeiyxmData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getsubmitdatas(String id);
	
	@SuppressWarnings("rawtypes")
	List getFeiyxmfl();
	
	@SuppressWarnings("rawtypes")
	List getFeiyxmsx();
	
	@SuppressWarnings("rawtypes")
	List getFeiyxmById(String id);
	
	int addFeiyxmData(Map<String, Object> map);
	
	int delFeiyxm(String id);
	
	int updateState(String id);
	
	String getState(String id);
	
	int updateFeiyxmsq(Map<String, Object> map);
	
	int updateFeiyxmById(Map<String, Object> map);
	
}
