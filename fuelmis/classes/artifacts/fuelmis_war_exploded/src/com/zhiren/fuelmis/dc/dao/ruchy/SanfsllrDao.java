package com.zhiren.fuelmis.dc.dao.ruchy;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 刘志宇
 */
@Repository
public interface SanfsllrDao {
	List<Map<String, Object>> getGongysList(String riq);

	List<Map<String, Object>> getChep(Map<String, Object> conditionMap);

	void updateChep(Map<String,Object> map);
}
