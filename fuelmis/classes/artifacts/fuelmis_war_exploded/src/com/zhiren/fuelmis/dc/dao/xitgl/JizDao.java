package com.zhiren.fuelmis.dc.dao.xitgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.xitgl.Jiz;

/** 
 * @author 陈宝露
 */
@Repository
public interface JizDao {
	List<Jiz> getAll(Map<String,Object> map);
	
	int insertJiz(Map<String,Object> map);
	
	List<Jiz> getOne(Map<String,Object> map);
	
	int updateJiz(Map<String,Object> map);
	
	int deleteJiz(Map<String,Object> map);
}
