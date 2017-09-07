package com.zhiren.fuelmis.dc.dao.rulgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 刘志宇
 */
@Repository
public interface KucglDao {

	List<Map<String, Object>> getKuczz();

	int update(Map<String, Object> kuczzMap);

	int insert(Map<String, Object> map);
//-----------------------------------------------------------------------------------------------------------------------
	int insertKucwz(Map<String, Object> map);

	int updateKucwz(Map<String, Object> map);

	List<Map<String, Object>> getKucwz();
//------------------------------------------------会计期-----------------------------------------------------------------------
	List<Map<String, Object>> getKuaijqdy();

	int updateKuaijqdy(Map<String, Object> map);

	int insertKuaijqdy(Map<String, Object> map);
//-----------------------------------------------------------------------------------------------------------------------
	List<Map<String, Object>> getGuanl();

	List<Map<String, Object>> getKuaijqList();

	List<Map<String, Object>> getKuczzList();

	int updateGuanl(Map<String, Object> map);

	int insertGuanl(Map<String, Object> map);
//--------------------------------------------------------------------------------------------------------------------------
	List<Map<String, Object>> getWeiz();

	List<Map<String, Object>> getFuKuczzList(String fuid);

	int updateWeiz(Map<String, Object> map);

	int insertWeiz(Map<String, Object> map);
//------------------------------------------------------------------------------------------------------------------------
	List<Map<String,Object>> getKucftList();

	List<Map<String,Object>> getKucftListFromKucwl();

	void updateKucftList(Map<String, Object> m);

	void insertKucftList(Map<String, Object> m);

	List<Map<String,Object>> getChurkd(Map<String, Object> riq);

	void updateChurkd(Map<String, Object> m);

	void insertChurkd(Map<String, Object> m);

	void delChurkd(Object id);

	void insertKucyeb(HashMap<String, Object> m);

	void deleteKucyeb(HashMap<String, Object> map);



	String getChukdbh(Map<String, Object> map);

	void updateMonthTotal(Map<String, Object> map);

	void updateChukdSub(Map<String, Object> map);

	void updateChurkmxhzb(Map<String, Object> map);

	List<Map<String, Object>> getKuaijqListByKuczzId(Object object);

	void insertMonthTotal(Map<String, Object> map);

	String getkuaijrqById(Object object);
}
