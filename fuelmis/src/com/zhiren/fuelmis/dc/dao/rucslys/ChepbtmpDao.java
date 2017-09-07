package com.zhiren.fuelmis.dc.dao.rucslys;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.rucslys.Chepbtmp;

@Repository("chepbtmpDao")
public interface ChepbtmpDao {

	public List<Map<String, Object>> getChepbtmpList(Map<String, Object> map);
	
	public List<Map<String, Object>> getChepbtmpById(Map<String, Object> map);
	public List<Map<String, Object>> getChepbtmpByTruckenter_id(Map<String, Object> map);

	public int addChepb(Chepbtmp chepbtmp);

	public int addKoubzxx(Chepbtmp chepbtmp);

	public int addChepbqc(Chepbtmp chepbtmp);

	public List<Chepbtmp> searchChepbtmpList(Map<String, Object> map);

	public int updateChepbtmp(Chepbtmp chepbtmp);

	public int addKoubzxxks(Chepbtmp chepbtmp);

	public int addKoubzxxkz(Chepbtmp chepbtmp);

	public int addKoubzxxkd(Chepbtmp chepbtmp);
	
	public int updateChepb(Chepbtmp chepbtmp);
	
	public int updateKoubzxxks(Chepbtmp chepbtmp);

	public int updateKoubzxxkz(Chepbtmp chepbtmp);

	public int updateKoubzxxkd(Chepbtmp chepbtmp);
	
	public int updateChepbqc(Chepbtmp chepbtmp);

	public int addChepbls(Chepbtmp chepbtmp);

	public int addChepbqcls(Chepbtmp chepbtmp);

	public int addKoubzxxksls(Chepbtmp chepbtmp);

	public int addKoubzxxkzls(Chepbtmp chepbtmp);

	public int addKoubzxxkdls(Chepbtmp chepbtmp);

	public List<Chepbtmp> getChepbtmpInfo(Map<String, Object> map);

}
