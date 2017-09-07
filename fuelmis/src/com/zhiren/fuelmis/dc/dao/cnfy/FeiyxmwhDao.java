package com.zhiren.fuelmis.dc.dao.cnfy;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface FeiyxmwhDao {
	@SuppressWarnings("rawtypes")
	List getAllData();
	
	int addfeiyxmwhData(Map<String, Object> map);
	
	String getMaxBianm();
	
	@SuppressWarnings("rawtypes")
	List getFeiyxmwhById(String id);
	
	int updateFeiyxmwh(Map<String, Object> map);
	
	int changeState(Map<String, Object> map);
	
	int getState(String id);
}
