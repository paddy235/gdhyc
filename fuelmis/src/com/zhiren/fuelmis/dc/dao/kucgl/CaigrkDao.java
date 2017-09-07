package com.zhiren.fuelmis.dc.dao.kucgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 陈宝露
 */
@Repository
public interface CaigrkDao {
	List<Map<String,Object>> getChurkdList(Map<String,Object> map);
	
	List<Map<String,Object>> getChurkdList2(Map<String,Object> map);
	
	List<Map<String,Object>> getYansmx(Map<String,Object> map);
	
	List<Map<String,Object>> getYansmxMX(Map<String,Object> map);
	
	List<Map<String,Object>> getCaigddList(Map<String,Object> map);

	void insertMeik(Map<String, Object> rukdbh);

	void insertMeikSub(String rukdbh);


	void deleteMeik(String rukdbh);

	void deleteMeikSub(String rukdbh);

	void insertChurkmxhzb(Map<String, Object> map);


}
