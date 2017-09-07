package com.zhiren.fuelmis.dc.service.gongyspg.fahb;

import java.util.Map;

import net.sf.json.JSONObject;

public interface FahbService {

	public JSONObject updateFahb(Map map);

	public JSONObject getFahbList(Map<String, Object> map);

}
