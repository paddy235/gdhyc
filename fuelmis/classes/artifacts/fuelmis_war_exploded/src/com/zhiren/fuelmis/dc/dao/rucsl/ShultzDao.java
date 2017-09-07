package com.zhiren.fuelmis.dc.dao.rucsl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("shultzDao")
public interface ShultzDao {

	public List<Map<String, Object>> getTabelData(Map<String, Object> map);

	public List<Map<String, Object>> getBaseData(Map<String, Object> map);

}
