package com.zhiren.fuelmis.dc.dao.cnfy;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.jih.Hetbean;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface ZafhtDao {
	@SuppressWarnings("rawtypes")
	List getZafhtByNianf(String nianf);
	
	@SuppressWarnings("rawtypes")
	List getCnfyxm();
	
	int addhetData(Hetbean hetbean);
	
	int saveHetZaf(Map<String, Object> map);
	
	int delHetById(String id);
	
	int delHetzafById(String id);
	
	@SuppressWarnings("rawtypes")
	List getZafhtById(String id);
	
	@SuppressWarnings("rawtypes")
	List getUpdateCnfyxm(String id);
	
	int updatehetData(Map<String, Object> map);
	
	int updatehetzafData(Map<String, Object> map);

	List<Map<String,Object>> getsubmitdatas(Map<String, Object> map);

	void updateZafhtzt(Map<String, Object> map);

	String getZhuangt(Map<String, Object> map);

	void insertShenpjl(Map<String, Object> map);
}
