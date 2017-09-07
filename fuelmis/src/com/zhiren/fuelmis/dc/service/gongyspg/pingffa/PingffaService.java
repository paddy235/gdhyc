package com.zhiren.fuelmis.dc.service.gongyspg.pingffa;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.gongyspg.pingffa.Pingffa;

public interface PingffaService {

	public JSONArray addPingffa(Pingffa pingffa);

	public JSONArray updatePingffa(Pingffa pingffa);

	public JSONObject getPingffaList(Map<String,Object> map);

	public JSONArray delPingffa(Map<String, Object> map);

	public JSONObject getPingffaById(Map<String, Object> map);

}
