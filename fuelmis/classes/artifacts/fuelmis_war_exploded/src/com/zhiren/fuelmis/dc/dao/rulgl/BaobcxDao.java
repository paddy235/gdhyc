package com.zhiren.fuelmis.dc.dao.rulgl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 陈宝露
 */
@Repository
public interface BaobcxDao {
	List<Map<String,Object>> getRulhyd(Map<String,Object> map);
	
	List<Map<String,Object>> getRuljzbb(Map<String,Object> map);
	
	List<Map<String,Object>> getRuljztz(Map<String,Object> map);
	
	List<LinkedHashMap<String,Object>> getRucrlrzc(Map<String,Object> map);
	
	List<LinkedHashMap<String,Object>> getSIS_shujcx(Map<String,Object> map);
}
