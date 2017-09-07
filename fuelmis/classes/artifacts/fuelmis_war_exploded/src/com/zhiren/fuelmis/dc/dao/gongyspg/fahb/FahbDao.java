package com.zhiren.fuelmis.dc.dao.gongyspg.fahb;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.gongyspg.fahb.Fahb;

@Repository("fahbDao")
public interface FahbDao {

	public List<Map<String,Object>> getFahbByDate(Map<String, Object> map);

	public void addFahb(Fahb fahb);

	public List<Map<String, Object>> getFahbList(Map<String, Object> map);

	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> getHuaydbBySamcode(Map samMap);

	public int delFahbByDate(Map<String, Object> map);

}
