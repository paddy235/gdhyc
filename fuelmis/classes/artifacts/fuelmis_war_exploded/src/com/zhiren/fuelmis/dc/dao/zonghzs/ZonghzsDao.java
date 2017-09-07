package com.zhiren.fuelmis.dc.dao.zonghzs;

import com.zhiren.fuelmis.dc.entity.yueshchjb.YueshchjbVo;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/** 
 * @author 孟建云
 */
@Repository
public interface ZonghzsDao {
	/**
	 * 通知待办
	 * @param map
	 * @return
     */
	List<Map<String, Object>> getTongzdb(Map<String, Object> map);

	
}
