package com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 陈宝露
 */
@Repository
public interface ZafwhDao {
	List<Map<String,Object>> getAll(Map<String,Object> map);
	
	List<Map<String,Object>> getAll_new(Map<String,Object> map);

	List<Map<String, Object>> getZfmingc();

	void deleteZf(Object object);

	void updateZf(Map<String, Object> m);

	void insertZf(Map<String, Object> m);

	List<Map<String, Object>> getZafeiByDiancidAndRiq(Map<String, Object> m);

	int DelZafeiByDiancidAndRiq(Map<String, Object> m);

	int CopyZafeiData(Map<String, Object> m);

}
