package com.zhiren.fuelmis.dc.service.kucgl.rukgl;

import java.util.List;
import java.util.Map;

public interface IChukdlrService {

	List<Map<String, Object>> getChurkd(Map<String, Object> map);

	void saveChurkd(List<Map<String, Object>> list);

	void delChurkd(List<Object> ids);

	String getChukdbh(Map<String, Object> map);

}
