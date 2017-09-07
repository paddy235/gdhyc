package com.zhiren.fuelmis.dc.dao.rucsl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("zonghtzDao")
public interface ZonghtzDao {

	public List<Map<String, Object>> getTabelData(Map<String, Object> map);
}
