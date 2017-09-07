package com.zhiren.fuelmis.dc.dao.xitgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.xitgl.Gongys;

/** 
 * @author 陈宝露
 */
@Repository
public interface GongysDao {
	List<Gongys> getAll(Map<String,Object> map);
	
	int insertGongys(Map<String,Object> map) throws Exception;
	
	int insertGongysmkglb(Map<String,Object> map) throws Exception;
	
	List<Map<String,Object>> getOne(Map<String,Object> map);
	
	int updateGongys(Map<String,Object> map);
	
	int updateWenjmc(Map<String,Object> map);
	
	int updateGongysmkglb(Map<String,Object> map);
	
	int switchGongys(Map<String,Object> map);

	void insertGongysdcglb(Map<String, Object> map);
}
