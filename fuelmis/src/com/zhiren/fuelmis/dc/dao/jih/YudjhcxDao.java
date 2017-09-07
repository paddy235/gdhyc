package com.zhiren.fuelmis.dc.dao.jih;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface YudjhcxDao {
	@SuppressWarnings("rawtypes")
	List getTabelData(Map<String, Object> map);
}
