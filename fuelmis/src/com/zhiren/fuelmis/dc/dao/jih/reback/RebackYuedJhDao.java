package com.zhiren.fuelmis.dc.dao.jih.reback;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface RebackYuedJhDao {
	
	int addAdviceHistory(Map<String, Object> map);
	
	int updateYuedjhZhib(Map<String, Object> map);
	
	int updateYuedjhZaf(Map<String, Object> map);
	
	int updateYuedjhCaig(Map<String, Object> map);

	void addHetAdviceHistory(Map<String,String> map);
}
