package com.zhiren.fuelmis.dc.dao.jiesgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

 
@Repository
public interface YuejstjtzDao {
	List<Map<String,Object>> getYuejstjtz(Map<String,Object> map);

	List<Map<String,Object>> getHetbh(Map<String, Object> map);
}
