package com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author weiw
 */
@Repository
public interface ZhibqkDao {
	
	List<Map<String,Object>> getAllName_Left(Map<String,Object> map);
	
	List<Map<String,Object>> getAllName_Right(Map<String,Object> map);
	
	List<Map<String,Object>> getAllValue(Map<String,Object> map);

	List<Map<String, Object>> getAllNames(Map<String,Object> map);
	//插入填报项
	void saveTbData(Map<String,Object> map);
	//插入填报项
	void updateTbDate(Map<String,Object> map);
	//修改
	int updateData(Map<String, Object> maps);
	//删除
	int delData(Map<String, Object> map);
	//获取累计数据
	List<Map<String, Object>> getLeijData(Map<String, Object> map);
	//更新累计数据
	void upLeijData(Map<String, Object> map);
	//插入累计数据
	void inLeijData(Map<String, Object> leijMaps);
}
