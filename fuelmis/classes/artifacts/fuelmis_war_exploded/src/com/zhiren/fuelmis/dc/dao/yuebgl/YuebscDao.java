package com.zhiren.fuelmis.dc.dao.yuebgl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 陈宝露
 */
@Repository
public interface YuebscDao {
	List<Map<String,Object>> getYueb(Map<String, Object> map);
}
