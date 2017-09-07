package com.zhiren.fuelmis.dc.dao.rucsl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("huayrbDao")
public interface HuayrbDao {
	public List<Map<String, Object>> getTabelData(Map<String, Object> map);
}
