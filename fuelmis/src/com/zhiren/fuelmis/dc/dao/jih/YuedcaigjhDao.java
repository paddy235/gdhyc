package com.zhiren.fuelmis.dc.dao.jih;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface YuedcaigjhDao {
	List<Map<String, Object>> getCaigJh(Map<String, Object> map);
	@SuppressWarnings("rawtypes")
	List getTabelData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getCaigData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getYuedcaigById(String id);
	
	@SuppressWarnings("rawtypes")
	List getyueducaigByDiancidAndRiq(Map<String, Object> map);
	//品种
	@SuppressWarnings("rawtypes")
	List getPinz();
	//发站
	@SuppressWarnings("rawtypes")
	List getFaz(String riq);
	//供应商
	@SuppressWarnings("rawtypes")
	List getGongys();
	
	@SuppressWarnings("rawtypes")
	List getJihkj();
	
	int addCaigData(Map<String, Object> map);
	
	int delCaigById(String id);
	
	int DelYuedcaigByDiancidAndRiq(Map<String, Object> map);
	
	int updateCaigById(Map<String, Object> map);
	
	int CopyYuedcaigData(Map<String, Object> map);
	
	String getshenpstate(Map<String, Object> map);
}
