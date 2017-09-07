package com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 陈宝露
 */
@Repository
public interface YoushcDao {
	List<Map<String,Object>> getAll(Map<String,Object> map);
}
