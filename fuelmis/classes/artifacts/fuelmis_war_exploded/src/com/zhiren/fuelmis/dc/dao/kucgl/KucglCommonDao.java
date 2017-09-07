package com.zhiren.fuelmis.dc.dao.kucgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * @author 陈宝露
 */
@Repository
public interface KucglCommonDao {
	List<Map<String, Object>> getChurkd(Map<String, Object> map);

	int rukjz(Map<String, Object> map);

	List<Map<String, Object>> getKucmx(Map<String, Object> map);
	
	List<Map<String, Object>> getKucmxByParams(Map<String, Object> map);

	List<Map<String, Object>> getKuchz(Map<String, Object> map);

	int insertKuchz(Map<String, Object> map);

	int updateKuchz(Map<String, Object> map);
}
