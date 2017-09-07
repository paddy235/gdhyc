package com.zhiren.fuelmis.dc.service.gongyspg.pingffaxzb;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.gongyspg.pingffaxzb.Pingffaxzb;

public interface PingffaxzbService {

	public JSONArray addPingffaxzb(Pingffaxzb pingffaxzb);

	public JSONArray updatePingffaxzb(Pingffaxzb pingffaxzb);

	public JSONObject getPingffaxzbList(Map<String,Object> map);

	public JSONArray delPingffaxzb(Map<String, Object> map);

	public JSONObject getPingffaxzbById(Map<String, Object> map);

}
