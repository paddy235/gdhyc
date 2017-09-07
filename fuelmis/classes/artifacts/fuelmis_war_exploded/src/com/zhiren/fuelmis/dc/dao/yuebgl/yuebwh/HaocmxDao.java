package com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/** 
 * @author 陈宝露/刘志宇
 */
@Repository
public interface HaocmxDao {
	List<Map<String,Object>> getAll(Map<String,Object> map); 


	void updateHaocmx(Map<String, Object> map);

	void updateHaocmxL(Map<String, Object> map);
    Map<String,Object> getLastMonthLeij(@Param("yuetjkjb_id") String yuetjkjb_id);
}
