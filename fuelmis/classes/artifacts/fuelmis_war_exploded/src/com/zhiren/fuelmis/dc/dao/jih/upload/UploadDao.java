package com.zhiren.fuelmis.dc.dao.jih.upload;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author rain刘志宇
 */
@Repository
public interface UploadDao {
	List<Map<String,Object>> selectYuedcaigjh(Map<String, Object> map);
	List<Map<String,Object>> selectYuedRanlzf(Map<String,Object> map);
	List<Map<String,Object>> selectYuedjhzb(Map<String,Object> map);
	List<Map<String,Object>> selectNiandcaigjh(Map<String,Object> map);
	List<Map<String,Object>> selectNiandRanlzf(Map<String,Object> map);
	List<Map<String,Object>> selectNiandjhzb(Map<String,Object> map);
	List<String> gatColNames(String tableName);
}
