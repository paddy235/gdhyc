package com.zhiren.fuelmis.dc.dao.jiesgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

 
@Repository
public interface RijsscDao {
	List<Map<String,Object>> getRijscx(Map<String,Object> map);
}
