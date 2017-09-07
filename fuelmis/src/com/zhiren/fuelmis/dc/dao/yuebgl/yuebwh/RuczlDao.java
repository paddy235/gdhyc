package com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 陈宝露
 */
@Repository
public interface RuczlDao {
	List<Map<String,Object>> getAll(Map<String,Object> map);

	List<Map<String, Object>> getHuayz(Map<String, Object> map);

	String getYuetjkjb_id(Map<String, Object> m);

	void insertYuezlb(Map<String, Object> m);

	Map<String, Object> getLeij_qit(Map<String, Object> m);
	
	Map<String, Object> getLeij_yuany(Map<String, Object> m);

	void insertYuezlbLeij(Map<String, Object> m);

	void insertYuezlbEmpty(Map<String, Object> leij);
}
