package com.zhiren.fuelmis.dc.dao.hetgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.common.Fujxx;

@Repository("hetmbglDao")
public interface HetmbglDao {

	public int updateHetmb(Fujxx fujxx);

	public List<Map<String, Object>> getHetmbList(Map<String, Object> map);

	public List<Fujxx> getHetmbById(Map<String, Object> map);

	public List<Map<String, Object>> getHetmbsubList(Map<String, Object> map);

	public Integer getHetmbglCount(Map<String, Object> map);

	public int delHetmb(Map<String, Object> map);

}
