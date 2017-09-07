package com.zhiren.fuelmis.dc.dao.caiygl.caiyzmd;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("caiydzmDao")
public interface CaiydzmDao {
	public List<Map<String, Object>> getTabelData(Map<String, Object> map);
}
