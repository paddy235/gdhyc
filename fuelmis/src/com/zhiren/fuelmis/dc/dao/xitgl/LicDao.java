package com.zhiren.fuelmis.dc.dao.xitgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.xitgl.Lic;

/** 
 * @author 陈宝露
 */
@Repository
public interface LicDao {
	List<Lic> getAll(Map<String,Object> map);
	
	int insertLic(Map<String,Object> map);
	
	List<Map<String,Object>> getOne(Map<String,Object> map);
	
	int updateLic(Map<String,Object> map);
	
	int delLic(Map<String,Object> map);
}
