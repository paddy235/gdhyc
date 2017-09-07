package com.zhiren.fuelmis.dc.dao.kucgl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 陈宝露
 */
@Repository
public interface PandcxDao {
	List<LinkedHashMap<String, Object>> getReport(Map<String,Object> map);

	List<Map<String,Object>> getPand(Map<String, Object> map);
}
