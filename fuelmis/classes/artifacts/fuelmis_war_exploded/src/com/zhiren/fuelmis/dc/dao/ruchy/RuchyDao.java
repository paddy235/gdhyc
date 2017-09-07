package com.zhiren.fuelmis.dc.dao.ruchy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author LU
 *
 */
@Repository
public interface RuchyDao {
	
	List<LinkedHashMap<String,Object>> getMeiyjcjg(Map<String,Object> map);
	
	int insertHuayd(Map<String,String> map);

	List<String> selectChepbId(String dcId, String bianm);

	void insertGX(String gxId, String chepbId, String huaydbid);

	List<Map<String, Object>> getFahInfo(String dcId, String bianm);

	String getHuaydID(String bianm);

	void updateHuayd(Map<String, String> map);

	String getHuaybm(String zhiybm);
}
