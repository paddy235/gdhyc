package com.zhiren.fuelmis.dc.service.rulgl;

import java.util.List;
import java.util.Map;

public interface IbaseSetService {

	List<Map<String, Object>> getBanz();

	int updateBanz(Map<String, Object> map);

	int insertBanz(Map<String, Object> map);

	List<Map<String, Object>> getJiz();

	int insertJiz(Map<String, Object> map);

	int updateJiz(Map<String, Object> map);

}
