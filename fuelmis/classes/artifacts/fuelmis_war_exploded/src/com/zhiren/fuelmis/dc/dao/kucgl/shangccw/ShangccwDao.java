package com.zhiren.fuelmis.dc.dao.kucgl.shangccw;

import java.util.List;
import java.util.Map;

public interface ShangccwDao {


	List<Map<String, Object>> getJiesdList(Map<String, Object> map);

	void updateJiesdShangccwzt(Map<String, Object> map);

	List<Map<String, Object>> getList4Xml(Map<String, Object> map);

	void updateMonthTotalShangccwzt(Map<String, Object> map);

	List<Map<String, Object>> getHaoyList(Map<String, Object> map);

	List<Map<String, Object>> getZangList(Map<String, Object> map);

	Map<String, Object> getZangHejList(Map<String, Object> map);

	Map<String, Object> getHaoyHead(Map<String, Object> map);

}
