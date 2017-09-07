package com.zhiren.fuelmis.dc.dao.xitgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.xitgl.Meiz;

/** 
 * @author 陈宝露
 */
@Repository
public interface MeizDao {
	List<Meiz> getAll(Map<String,Object> map);
	
	int insertMeizxx(Map<String,Object> map);
	
	List<Meiz> getOne(Map<String,Object> map);
	
	int updateMeizxx(Map<String,Object> map);
	
	int delMeizxx(Map<String,Object> map);
}
