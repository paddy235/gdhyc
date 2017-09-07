package com.zhiren.fuelmis.dc.dao.jih;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface YuedjhtjDao {
	
	@SuppressWarnings("rawtypes")
	List getYuedjhcaig(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getZfjh(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getZhib(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getdiancxx(String id);
	
	int UpdateState(Map<String, Object> map);
	
	String getSanjshenpState(Map<String, Object> map);
}
