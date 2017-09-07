package com.zhiren.fuelmis.dc.dao.jiesgl;

import java.util.List;
import java.util.Map;

public interface ZafjsDao {

	List<Map<String, Object>> getZafjs();


	void delZafjs(String id);

	void insertZafjs(Map<String, Object> map);

	String getgongysmcById(String id);

	void updateZafjs(Map<String, Object> map);

}
