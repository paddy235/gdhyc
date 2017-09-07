package com.zhiren.fuelmis.gddl.dao.hetgl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 陈宝露 
 */
@Repository
public interface ChaxdyDao2 {	
	List<Map<String, Object>> getHetbh(Map<String, Object> map);
	
	List<Map<String, Object>> getHetxx(Map<String, Object> map);
	
	List<LinkedHashMap<String, Object>> getPingsxx(Map<String, Object> map);
}
