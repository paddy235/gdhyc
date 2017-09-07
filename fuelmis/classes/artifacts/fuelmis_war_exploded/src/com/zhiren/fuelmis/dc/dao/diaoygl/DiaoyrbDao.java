package com.zhiren.fuelmis.dc.dao.diaoygl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("diaoyrbDao")
public interface DiaoyrbDao {
	public List<Map<String, Object>> getFadlInfo(Map<String, Object> map); 
	
	public String getXitxxInfo(Map<String, Object> map);
	
	public List<Map<String,Object>> getZhonglInfo(Map<String, Object> map);

	public List<Map<String, Object>> getHaoyInfo(Map<String, Object> map);

	public String getZuorkcInfo(Map<String, Object> map);

	public String getShangykcInfo(Map<String, Object> map);

	public String getShangrkccInfo(Map<String, Object> map);

	public String getBukdmlInfo(Map<String, Object> map);

	public List<Map<String, Object>> getShifyscInfo(Map<String, Object> map);

	public int updateShouhcrbb(Map<String, Object> map);

	public int updateShouhcrbb1(Map<String, Object> map);

	public int updateShouhcrbb2(Map<String, Object> map);

	public int addShouhcrbb(Map<String, Object> map);

	public List<Map<String, Object>> getOLDShouhcfk(Map<String, Object> map);

	public List<Map<String, Object>> getShouhcfk(Map<String, Object> map);

	public int addShouhcfkb(Map<String, Object> map);

	public int addShouhcfkb1(Map<String, Object> map);

	public int delShouhcfkb(Map<String, Object> map);
	
}
