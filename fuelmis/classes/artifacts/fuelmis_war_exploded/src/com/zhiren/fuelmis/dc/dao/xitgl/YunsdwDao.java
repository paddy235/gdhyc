package com.zhiren.fuelmis.dc.dao.xitgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.xitgl.Yunsdw;

/** 
 * @author 陈宝露
 */
@Repository
public interface YunsdwDao {
	List<Yunsdw> getAll(Map<String,Object> map);
	
	int insertYunsdw(Map<String,Object> map);
	
	List<Yunsdw> getOne(Map<String,Object> map);
	
	int updateYunsdw(Map<String,Object> map);
}
