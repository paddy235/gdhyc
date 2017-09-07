package com.zhiren.fuelmis.dc.dao.diaoygl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("diaoybbDao")
public interface DiaoybbDao {
	public List<Map<String, Object>> getRanlrcgjhd(String riq);

	public List<Map<String, Object>> getDuizd(String riq);
	
}
