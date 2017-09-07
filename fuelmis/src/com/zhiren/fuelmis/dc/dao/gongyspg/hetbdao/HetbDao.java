package com.zhiren.fuelmis.dc.dao.gongyspg.hetbdao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.gongyspg.hetb.Hetb;

@Repository("hetbDao")
public interface HetbDao {

	public int addHetb(Hetb hetb);
	
	public long getId();

	public int updateHetb(Hetb hetb);
	
	public int updateHetzhib(Map<String, Object> map);
	
	public int addhetzb(Map<String, Object> map);
	
	public List  getZhibdm();
	
	public List getHetZhibByHetId(Map<String, Object> map);
	
	public List<Map<String,Object>> getGyshtList(Map<String, Object> map);

	public List<Map<String, Object>> getHetbList(Map<String, Object> map);

	public int delHetb(Map<String, Object> map);

	public List<Hetb> getHetbById(Map<String, Object> map);

}
