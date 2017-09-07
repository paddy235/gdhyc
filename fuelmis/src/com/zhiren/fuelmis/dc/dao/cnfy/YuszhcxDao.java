package com.zhiren.fuelmis.dc.dao.cnfy;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface YuszhcxDao {
	@SuppressWarnings("rawtypes")
	List getshijwcdata(Map<String, Object> map);
	@SuppressWarnings("rawtypes")
	List getyusdata(Map<String, Object> map);
	
}
