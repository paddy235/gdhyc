package com.zhiren.fuelmis.dc.dao.diaoygl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("diaodjhwhDao")
public interface DiaodjhwhDao {

	void insertDiaodjh(Map<String, Object> map);

	void insetDiaodjhSub(Map<String, Object> map);

	List<Map<String, Object>> getDiaodjhList(Map<String, Object> map);

	Map<String, Object> getDiaodjhMain(String id);

	List<Map<String, Object>> getDiaodjhSub(String id);

	void updateDiaodjh(Map<String, Object> map);

	void updateDiaodjhSub(Map<String, Object> m);

	void deleteDiaodjhSub(String id);

	List<String> selectSubId(String id);

	void deleteDiaodjhMain(String id);

	String[] getJihdh(String riq);

	
}
