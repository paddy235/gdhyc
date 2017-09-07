package com.zhiren.fuelmis.dc.dao.caiygl.heby;

import java.util.List;
import java.util.Map;

public interface HebyDao {
	
	List<Map<String, Object>> getSamcodeList(String riq);

	List<Map<String, Object>> getList(String riq);

	void updateList(Map<String, Object> map);

	void updateCancelList(Map<String, Object> map);

}
