package com.zhiren.fuelmis.dc.dao.rib;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("guohrbDao")
public interface GuohrbDao {
	public List<Map<String, Object>> getTabelData(Map<String, Object> map);

}
