package com.zhiren.fuelmis.dc.dao.gongyspg.pingffa;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.gongyspg.pingffa.Pingffa;

@Repository("pingffaDao")
public interface PingffaDao {

	public List<Pingffa> getPingffaById(Map<String, Object> map);

	public int delPingffa(Map<String, Object> map);

	public List<Map<String, Object>> getPingffaList(Map<String, Object> map);

	public int updatePingffa(Pingffa pingffa);

	public int addPingffa(Pingffa pingffa);

}
