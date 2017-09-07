package com.zhiren.fuelmis.dc.dao.gongyspg.kaohzb;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.gongyspg.kaohzb.Kaohzb;

@Repository("kaohzbDao")
public interface KaohzbDao {

	public int addKaohzb(Kaohzb kaohzb);

	public List<Map<String, Object>> getKaohzbList(Map<String, Object> map);

	public int updateKaohzb(Kaohzb kaohzb);

	public int delKaohzb(Map<String, Object> map);

	public List<Kaohzb> getKaohzbById(Map<String, Object> map);


}
