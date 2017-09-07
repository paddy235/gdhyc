package com.zhiren.fuelmis.dc.dao.xitgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;

/**
 * @author 陈宝露
 */
@Repository
public interface RenyxxDao {
	List<Renyxx> getRenyxx(Map<String,Object> map);
	
	List<Renyxx> getAll(Map<String, Object> map);
	
	int getCount();
	
	int insertRenyxx(Map<String, Object> map);
	
	List<Renyxx> getOne(Map<String,Object> map);
	
	int updateRenyxx(Map<String, Object> map);
	
	int deleteRenyxx(Map<String, Object> map);
	
	int deleteQuanx(Map<String,Object> map);
	
	int insertQuanx(Map<String,Object> map);
	
	List<String> getQuanx(Long renyxxb_id);
}
