package com.zhiren.fuelmis.dc.dao.cnfy;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface YustzsqDao {
	@SuppressWarnings("rawtypes")
	List getYustzsqData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getsubmitdatas(String id);
	
	int addYustzData(Map<String, Object> map);
	
	int delYustzById(String id);
	
	@SuppressWarnings("rawtypes")
	List getYustzById(String id);
	
	int updateState(String id);
	
	int updateYustzById(Map<String, Object> map);
	
	String getstate(String id);
}
