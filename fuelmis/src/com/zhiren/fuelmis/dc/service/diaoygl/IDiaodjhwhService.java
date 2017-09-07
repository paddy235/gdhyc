package com.zhiren.fuelmis.dc.service.diaoygl;

import java.util.List;
import java.util.Map;

public interface IDiaodjhwhService {

	void insertDiaodjh(Map<String, Object> map);

	List<Map<String, Object>> getDiaodjhList(Map<String, Object> map);

	Map<String, Object> getDiaodjh(String id);

	void deleteDiaodjhSub(String id);

	void deleteDiaodjh(String id);

	String generateJihdh(String riq);

}
