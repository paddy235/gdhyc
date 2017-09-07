package com.zhiren.fuelmis.dc.dao.cnfy;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface FeiyxmflDao {
	@SuppressWarnings("rawtypes")
	List getAllData();
	
	@SuppressWarnings("rawtypes")
	List getFenlkj();
	
	int addFeiyxmflData(Map<String, Object> map);
	
	String getMaxBianm();
	
	@SuppressWarnings("rawtypes")
	List getFeiyxmflById(String id);
	
	int updateFeiyxmfl(Map<String, Object> map);
	
	int changeState(Map<String, Object> map);
	
	int getState(String id);
	
}
