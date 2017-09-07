package com.zhiren.fuelmis.dc.dao.cnfy;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface YuedYussqDao {
	@SuppressWarnings("rawtypes")
	List getYussqData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getFeiyMingc();
	
	@SuppressWarnings("rawtypes")
	List getsubmitdatas(Map<String, Object> map);
	
	int addYuedysData(Map<String, Object> map);
	
	int delYuedyusById(String id);
	
	@SuppressWarnings("rawtypes")
	List getYuedyusById(String id);
	
	int updateState(Map<String, Object> map);
	
	int updateYuedys(Map<String, Object> map);
	
	int updateYudysyId(Map<String, Object> map);
	
	String getZhuangt(Map<String, Object> map);
	
}
