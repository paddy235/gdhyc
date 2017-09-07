package com.zhiren.fuelmis.dc.dao.xitgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;

/** 
 * @author 陈宝露
 */
@Repository
public interface DiancxxDao {
	List<Diancxx> getAll();
	
	List<Diancxx> getOne();
	
	List<Diancxx> selectAll(Map<String,Object> map);
	
	List<Diancxx> getOneById(Map<String,Object> map);
	
	int insertDiancxx(Map<String,Object> map);
	
	int updateDiancxx(Map<String, Object> map);
}
