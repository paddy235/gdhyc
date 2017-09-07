package com.zhiren.fuelmis.dc.dao.gongyspg.gongmjhzl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("gongmjhtzDao")
public interface GongmjhtzDao {
	public List<Map<String, Object>> getGongmjhtzAll(Map<String, Object> map);
	
	void updateGongmjh(Map<String , Object> map);
}
