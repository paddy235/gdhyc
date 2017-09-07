package com.zhiren.fuelmis.dc.dao.diaoygl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("riddshDao")
public interface RiddshDao {

	List<Map<String, Object>> getRiddsh(Map<String, Object> map);

	void shenh(String id, Long lurrid);
	void huit(String id, Long lurrid);
}
