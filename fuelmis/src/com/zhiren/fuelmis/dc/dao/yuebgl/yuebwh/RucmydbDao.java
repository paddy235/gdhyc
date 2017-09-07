package com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** 
 * @author 陈宝露
 */
@Repository
public interface RucmydbDao {
	List<LinkedHashMap<String,Object>> getMeishcbb(Map<String, Object> map);
	
	List<LinkedHashMap<String,Object>> getShulysbb(Map<String, Object> map);
	
	List<LinkedHashMap<String,Object>> getShulysbbFkmx(Map<String, Object> map);
	
	List<LinkedHashMap<String,Object>> getZhilysbb(Map<String, Object> map);
	
	List<LinkedHashMap<String,Object>> getRucbmdjbb(Map<String, Object> map);
	
	List<LinkedHashMap<String,Object>> getRanyshc(Map<String, Object> map);
	
	List<LinkedHashMap<String,Object>> getRulmrz(Map<String, Object> map);
	
	List<LinkedHashMap<String,Object>> getRanlcbbb(Map<String, Object> map);
	
	List<LinkedHashMap<String,Object>> getChangnfycx(Map<String, Object> map);
	
	List<LinkedHashMap<String,Object>> getChangnfycxMX(Map<String, Object> map);
	
	List<Map<String,Object>> getRanlzbqkb(Map<String, Object> map);
}
