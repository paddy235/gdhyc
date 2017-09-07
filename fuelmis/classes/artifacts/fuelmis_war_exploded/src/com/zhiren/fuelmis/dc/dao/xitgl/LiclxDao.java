package com.zhiren.fuelmis.dc.dao.xitgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.xitgl.Liclx;

/** 
 * @author 陈宝露
 */
@Repository
public interface LiclxDao {
	List<Liclx> getAll(Map<String,Object> map);
	
	int insertLiclx(Map<String,Object> map);
	
	List<Liclx> getOne(Map<String,Object> map);
	
	int updateLiclx(Map<String,Object> map);
	
	int delLiclx(Map<String,Object> map);
}
