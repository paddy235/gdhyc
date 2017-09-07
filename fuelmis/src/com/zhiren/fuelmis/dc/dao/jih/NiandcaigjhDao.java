package com.zhiren.fuelmis.dc.dao.jih;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface NiandcaigjhDao {
	@SuppressWarnings("rawtypes")
	List getTabelData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getNiandcaigData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getNiandCaigById(String id);
	
	@SuppressWarnings("rawtypes")
	List getNiandcaigByDiancidAndRiq(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getGongys();
	
	int delNiandcaigById(String id);
	
	int DelNiandcaigByDiancidAndRiq(Map<String, Object> map);
	
	int addCaigData(Map<String, Object> map);
	
	int updateNiandcaigById(Map<String, Object> map);
	
	int CopyNiandcaigData(Map<String, Object> map);
	
	String getshenpstate(Map<String, Object> map);

	@SuppressWarnings("rawtypes")
	List getCaigJh(Map map);
}
