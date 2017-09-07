package com.zhiren.fuelmis.dc.dao.rulgl;

import java.util.List;
import java.util.Map;

public interface BaseSetDao {

	public List<Map<String, Object>> getBanz() ;


	
	public List<Map<String, Object>> getJiz() ;



	public int insertBanz(Map<String, Object> map);



	public int updateBanz(Map<String, Object> map);







	public int updateJiz(Map<String, Object> map);



	public int insertJiz(Map<String, Object> map);






}
