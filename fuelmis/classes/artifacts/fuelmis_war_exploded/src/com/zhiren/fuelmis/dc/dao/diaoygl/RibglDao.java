package com.zhiren.fuelmis.dc.dao.diaoygl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

public interface RibglDao {
	public  List<Map<String ,Object>> getRibtbShangc(String riq);
	
	public  List<Map<String ,Object>> getRibgsShangc(String riq);

	void updateZhuangt(String riq);

	void updateRibgsZhuangt(String riq);

	List<Map<String,Object>> getRibgsList(Map<String, Object> map);

	public void updateRibgsZhuangt(Map<String, Object> map);
}
 