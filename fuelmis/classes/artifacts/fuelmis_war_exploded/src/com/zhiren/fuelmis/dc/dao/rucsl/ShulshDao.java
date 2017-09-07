package com.zhiren.fuelmis.dc.dao.rucsl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.entity.rucslys.Chepbtmp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/** 
 * @author 陈宝露
 */
@Repository
public interface ShulshDao {


	List<Map<String, Object>> getTableData(Map<String, Object> map);

	LinkedList<Map<String, Object>> getTableData_MX(Map<String, Object> map);

	int shenh(Map<String, Object> map);

	int huit(Map<String, Object> map);


	List<Map<String, Object>> getJiesxxList(String samcode);

	void insertHuaybgb(Map<String, Object> map);

	void insertGX_CHURUK_HUAYBH(Map<String, Object> map);

	String isHuay(Map<String, Object> map);

	void insertHuaybgbWithOtherWay(Map<String, Object> map);

	void deleteHuaybgb(Map<String, Object> map);


}
