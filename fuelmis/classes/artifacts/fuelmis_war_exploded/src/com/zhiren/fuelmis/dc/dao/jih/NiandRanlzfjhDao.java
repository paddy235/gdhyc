package com.zhiren.fuelmis.dc.dao.jih;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface NiandRanlzfjhDao {
	
	@SuppressWarnings("rawtypes")
	List getTabelData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getRanlzfData(Map<String, Object> map);
	
	int addRanlzfData(Map<String, Object> map);
	
	int delRanlzfById(String id);
	
	int DelRanlzfByDiancidAndRiq(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getRanlzfById(String id);
	
	@SuppressWarnings("rawtypes")
	List getRanlzfByDiancidAndRiq(Map<String, Object> map);
	
	int updateRanlzfById(Map<String, Object> map);
	
	int CopyRanlzfData(Map<String, Object> map);
	
	String getshenpstate(Map<String, Object> map);
}
