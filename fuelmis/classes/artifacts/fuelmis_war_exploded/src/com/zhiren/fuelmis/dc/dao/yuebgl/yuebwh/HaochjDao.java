package com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/** 
 * @author 陈宝露
 */
@Repository
public interface HaochjDao {
	List<Map<String,Object>> getAll(Map<String,Object> map);

    Map<String,Object> getLastMonthLeij(@Param("riq") String riq);
}
