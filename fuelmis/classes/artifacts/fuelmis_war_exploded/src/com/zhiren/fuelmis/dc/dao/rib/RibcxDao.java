package com.zhiren.fuelmis.dc.dao.rib;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface RibcxDao {
	
	String getXitxxItem(Map<String, Object> map);
	
	String findzhi(@Param("diancid") String diancid);
	
	@SuppressWarnings("rawtypes")
	List getShouhc(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getRipjkc(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getShouhc_zhoub(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getShouhc_zhoubdetail(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getShouhcDetail(Map<String, Object> map);
}
