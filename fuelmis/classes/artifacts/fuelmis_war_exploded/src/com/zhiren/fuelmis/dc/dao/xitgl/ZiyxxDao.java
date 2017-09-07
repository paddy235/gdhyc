package com.zhiren.fuelmis.dc.dao.xitgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.xitgl.Ziyxx;

/** 
 * @author 陈宝露
 */
@Repository
public interface ZiyxxDao {
	List<Ziyxx> getZiyxx(Map<String,Object> map);
	
	List<Ziyxx> getParent(Map<String,Object> map);
	
	List<Ziyxx> getChildren(Map<String,Object> map);
	
	int insertZiyxx(Ziyxx ziyxx);
	
	int updateZiyxx(Ziyxx ziyxx);
	
	int deleteZiyxx(Map<String,Object> map);
	
	List<Ziyxx> getOne(Map<String,Object> map);
}
