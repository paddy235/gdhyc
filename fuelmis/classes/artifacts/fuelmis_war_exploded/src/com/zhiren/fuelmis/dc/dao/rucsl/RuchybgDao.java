package com.zhiren.fuelmis.dc.dao.rucsl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("ruchybgDao")
public interface RuchybgDao {
	public List<Map<String, Object>> getTabelData(Map<String, Object> map);

	public List<Map<String, Object>> getHuaybh(Map<String, Object> map);

}
