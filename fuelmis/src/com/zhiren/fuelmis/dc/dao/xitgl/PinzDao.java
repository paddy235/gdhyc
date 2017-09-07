package com.zhiren.fuelmis.dc.dao.xitgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.xitgl.Pinz;

/** 
 * @author 陈宝露
 */
@Repository
public interface PinzDao {
	List<Pinz> getAll(Map<String,Object> map);
	
	int insertPinzxx(Map<String,Object> map);
	
	List<Pinz> getOne(Map<String,Object> map);
	
	int updatePinzxx(Map<String,Object> map);
	
	int delPinzxx(Map<String,Object> map);
}
