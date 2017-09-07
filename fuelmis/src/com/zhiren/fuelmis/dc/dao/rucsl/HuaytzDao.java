package com.zhiren.fuelmis.dc.dao.rucsl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("huaytzDao")
public interface HuaytzDao {
	public List<Map<String, Object>> getTabelData(Map<String, Object> map);

	List<Map<String,Object>> getTabelDataByRiq(Map<String, Object> map);
}
