package com.zhiren.fuelmis.dc.service.rucsl;

import java.util.Map;

import net.sf.json.JSONArray;

public interface IRuchybgService {
	public JSONArray getTabelData(Map<String,Object> map);

	public JSONArray getHuaybh(Map<String, Object> map);
}
