package com.zhiren.fuelmis.dc.dao.cnfy;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface CnfymxDao {
	@SuppressWarnings("rawtypes")
	List getTabelData(Map<String, Object> map);
	
}
