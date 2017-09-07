package com.zhiren.fuelmis.dc.dao.jiesgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 陈宝露
 */
@Repository
public interface JiesbbDao {
	List<String> getAllJiesbh(Map<String,Object> map);
}
