package com.zhiren.fuelmis.dc.dao.xitgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.xitgl.Chezxx;

/** 
 * @author 陈宝露
 */
@Repository
public interface ChezxxDao {
	List<Chezxx> getAll(Map<String,Object> map);
	
	int insertChezxx(Map<String,Object> map);
	
	List<Chezxx> getOne(Map<String,Object> map);
	
	int updateChezxx(Map<String,Object> map);
	
	int deleteChezxx(Map<String,Object> map);
}
