package com.zhiren.fuelmis.dc.service.rucslys;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ChepbtmpService {

	public JSONObject getChepbtmpList(Map<String,Object> map);
	
	public JSONObject updateData(JSONArray json,String caozry,String caozsj);

	public JSONArray searchChepbtmpList(Map<String, Object> map);

}
