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
public interface RulhyDao {
	
	List<LinkedHashMap<String,Object>> getMeiyjcjg(Map<String,Object> map);
	
	int insertHuayd(Map<String,String> map);

	List<String> selectChepbId(String dcId, String bianm);

	void insertGX(String gxId, String chepbId, String huaydbid);

	List<Map<String, Object>> getFahInfo(String dcId, String bianm);

	String getIspip(String huaybh);

	void deleteRulhy(String huaybh);

	void updateRulhy(Map<String, String> map);

	String getHuaydID(String rulrq, String banzxx);

	void updateZhuant(String string, String huaydID);

	void insertRulhy(Map<String, String> map);

	void addHuaydID(String huaydID, String rulrq, String banzxx);


}
