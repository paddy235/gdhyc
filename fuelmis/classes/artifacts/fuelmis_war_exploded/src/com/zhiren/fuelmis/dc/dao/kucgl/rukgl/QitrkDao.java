package com.zhiren.fuelmis.dc.dao.kucgl.rukgl;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/** 
 * @author Rain
 */
@Repository
public interface QitrkDao {
	List<Map<String,Object>> getChurkdList(Map<String, Object> map);
	
	List<Map<String,Object>> getChurkdList2(Map<String, Object> map);
	
	List<Map<String,Object>> getYansmx(Map<String, Object> map);
	
	List<Map<String,Object>> getYansmxMX(Map<String, Object> map);
	
	List<Map<String,Object>> getCaigddList(Map<String, Object> map);

	void insertMeik(Map<String, Object> rukdbh);

	void insertMeikSub(String rukdbh);


	void deleteMeik(String rukdbh);

	void deleteMeikSub(String rukdbh);

	void insertChurkmxhzb(Map<String, Object> map);

	List<Map<String, Object>> getQitrkList(Map<String, Object> map);

	void isertYunzf(Map<String, Object> rukdh);

	void updateYunzf(Map<String, Object> rukdh);

	void deleteYunzf(String id);

	void insertGXChurkdbYunsdjb(Map<String, Object> rukdh);


}
