package com.zhiren.fuelmis.dc.dao.gongyspg.pingffaxzb;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.gongyspg.pingffaxzb.Pingffaxzb;

@Repository("pingffaxzbDao")
public interface PingffaxzbDao {

	public int addPingffaxzb(Pingffaxzb pingffaxzb);

	public int updatePingffaxzb(Pingffaxzb pingffaxzb);

	public List<Map<String, Object>> getPingffaxzbList(Map<String, Object> map);

	public int delPingffaxzb(Map<String, Object> map);

	public List<Pingffaxzb> getPingffaxzbById(Map<String, Object> map);
	
}
