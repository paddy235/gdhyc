package com.zhiren.fuelmis.dc.dao.diaodjh;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.diaodjh.Diaodjh;

@Repository("diaodjhDao")
public interface DiaodjhDao {

	public int addDiaodjh(Diaodjh diaodjh);

	public List<Map<String,Object>> getDiaodjhList(Map<String, Object> map);

	public String getBianm(Map<String, Object> map);

	public int delDiaodjh(Map<String, Object> map);

	public List<Diaodjh> getDiaodById(Map<String, Object> map);

	public int updateDiaodjh(Diaodjh diaodjh);

	public List<Map<String, Object>> getDiaodjhinfoList(Map<String, Object> map);

}
