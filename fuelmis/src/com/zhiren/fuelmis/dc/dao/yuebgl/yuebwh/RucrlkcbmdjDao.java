package com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/** 
 * @author 陈宝露
 */
@Repository
public interface RucrlkcbmdjDao {
	List<Map<String,Object>> getAll(Map<String, Object> map);
}
