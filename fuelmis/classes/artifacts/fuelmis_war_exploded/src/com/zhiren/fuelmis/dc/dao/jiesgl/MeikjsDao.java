package com.zhiren.fuelmis.dc.dao.jiesgl;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/** 
 * @author 陈宝露
 */
@Repository
public interface MeikjsDao {
	
	List<Map<String,Object>> getJiestz(Map<String,Object> map);

	List<Map<String, Object>> getHetong(Map<String, Object> map);

	List<Map<String, Object>> getYuejs(Map<String, Object> map);

	List<Map<String, Object>> getPinzList(Map<String, Object> map);
	
	List<Map<String, Object>> getHetbhList(Map<String, Object> map);

	List<Map<String, Object>> getYuejsdList(Map<String, Object> map);
	
	List<Map<String, Object>> getjsdList(Map<String, Object> map);

	List<Map<String,Object>> getZafjsList(Map<String, Object> map);

	List<Map<String,Object>> getYewlxList();
	
	List<Map<String,Object>> getJiesdbh();
	
	List<Map<String,Object>> getZafjswhList(Map<String, Object> m);

	void insertZafjs(Map<String, Object> m);
	
	void udpateChurkdb(Map<String, Object> m);
	
	void udpateChepbByJiesdid(String id);
	
	void udpateChurkdbByJiesdid(String id);
	
	void udpateChepb(Map<String, Object> m);
	
	String getJiesdidByJiesdbh(String jisdbh);
	
	void saveJiesd_hyc(@Param("jiesd") Map<String, Object> jiesd,@Param("caozlx") int caozlx);
	
	void deleteJiesdbByJiesbh(String Jiesbh);
	
	void deleteJieszbsjbByJiesbh(String jiesbh);
	
	void saveJieszbsjb_hyc(Map<String, Object> m);
	
	Map<String, Object> getjiesd(String Jiesbh);

	
	List<Map<String,Object>> getJieszbsjbByJiesbh(String jiesbh);
	
	void deleteJiesdByJiesdbh(String jiesbh);
	
	void deleteJieszbsjbByJiesdbh(String jiesbh);
	
	List<Map<String,Object>> gethetmx(String hetbh);

	void zafJies(Map<String, Object> map);

	void deleteZafjs(Map<String, Object> map);

	void insertGXChurkdbZafjsb(Map<String, Object> map);

	void deteteGXChurkdbZafjsb(Map<String, Object> map);

	void updateZafjs(Map<String, Object> m);

	void insertYuejsd(Map<String, Object> yuejsd);
}
