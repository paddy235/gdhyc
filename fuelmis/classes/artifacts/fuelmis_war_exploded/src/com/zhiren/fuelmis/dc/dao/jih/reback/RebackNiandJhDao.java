package com.zhiren.fuelmis.dc.dao.jih.reback;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface RebackNiandJhDao {
	
	int addAdviceHistory(Map<String, Object> map);
	
	int updateNiandjhZhib(Map<String, Object> map);
	
	int updateNiandjhZaf(Map<String, Object> map);
	
	int updateNiandjhCaig(Map<String, Object> map);
}
