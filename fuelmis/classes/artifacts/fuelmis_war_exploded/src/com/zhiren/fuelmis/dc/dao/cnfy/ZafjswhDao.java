package com.zhiren.fuelmis.dc.dao.cnfy;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface ZafjswhDao {
	
	@SuppressWarnings("rawtypes")
	List getZafjsdata(String nianf);
	
	@SuppressWarnings("rawtypes")
	List getUserAndDeptById(String id);
	
	long getZaffybxdId();
	
	@SuppressWarnings("rawtypes")
	List getHetBianh();
	
	@SuppressWarnings("rawtypes")
	List getHetZaf(String hetid);
	
	int saveZafDjb(Map<String, Object> map);
	
	int delZaffydjbById(String id);
	
	int delZaffybxdById(String id);
	
	double getZongjeById(String id);
	
	int saveZaffybxd(Map<String, Object> map);
	
	int updateZaffybxd(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getBxddata(String id);
	
	@SuppressWarnings("rawtypes")
	List getZafyById(String id);
	
	String getHetBianhById(String id);
	
	BigDecimal getZongJine(String id);
	
	@SuppressWarnings("rawtypes")
	List getZafhtfydjbById(String id);
	
	int updateZafDjb(Map<String, Object> map);
	
	String getdanj(Map<String, Object> map);
	
}
