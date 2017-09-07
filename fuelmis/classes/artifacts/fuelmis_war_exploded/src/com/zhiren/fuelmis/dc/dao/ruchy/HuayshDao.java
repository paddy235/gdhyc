package com.zhiren.fuelmis.dc.dao.ruchy;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 陈宝露
 */
@Repository
public interface HuayshDao {
	List<Map<String,Object>> getTableData(Map<String,Object> map);
	
	int updateZT(Map<String,Object> map);
	
	List<Map<String,Object>> getJincpcList(Map<String,Object> map);
	
	List<Map<String,Object>> getTableData2(Map<String,Object> map);
	
	int updateZT2(Map<String,Object> map);
}
