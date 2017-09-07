package com.zhiren.fuelmis.dc.dao.kucgl.rukgl;

import java.util.List;
import java.util.Map;

public interface ChurkdlrDao {

	List<Map<String, Object>> getChurkd(Map<String, Object> map);

	void updateChurkd(Map<String, Object> m);

	void insertChurkd(Map<String, Object> m);

	void delChurkd(Object id);

	String getChukdbh(Map<String, Object> map);

}
