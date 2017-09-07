package com.zhiren.fuelmis.dc.dao.xitgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.xitgl.Meikdq;

/** 
 * @author 陈宝露
 */
@Repository
public interface MeikdqDao {
	List<Meikdq> getAll(Map<String,Object> map);
	
	int insertMeikdq(Map<String,Object> map);
	
	List<Meikdq> getOne(Map<String,Object> map);
	
	int updateMeikdq(Map<String,Object> map);
}
