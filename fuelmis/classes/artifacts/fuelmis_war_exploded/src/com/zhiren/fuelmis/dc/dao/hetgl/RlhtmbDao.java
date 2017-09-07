package com.zhiren.fuelmis.dc.dao.hetgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.hetgl.Rlhtmb;

@Repository("rlhtmbDao")
public interface RlhtmbDao {

	public List<Map<String, Object>> getHetmbList(Map<String, Object> map);

	public int updateHetmb(Rlhtmb rlhtmb);

	public List<Rlhtmb> getHetmbById(Map<String, Object> map);

	public void delHetmb(Map<String, Object> map);

	public int addHetmb(Rlhtmb hetmb);

	public List<Rlhtmb> getHetmbByhtId(Map<String, Object> map);

}
