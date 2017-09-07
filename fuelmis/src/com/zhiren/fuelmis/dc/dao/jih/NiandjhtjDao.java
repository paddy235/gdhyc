package com.zhiren.fuelmis.dc.dao.jih;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface NiandjhtjDao {
	
	@SuppressWarnings("rawtypes")
	List getZhibData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getCgjhData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getZfjhData(Map<String, Object> map);
	
	String getDiancMingc(String diancid);
	
	int UpdateState(Map<String, Object> map);
	
	String getSanjshenpState(Map<String, Object> map);
}
