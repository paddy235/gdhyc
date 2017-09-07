package com.zhiren.fuelmis.dc.service.hetgl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Map;


public interface HtKaohzbService {

	public JSONArray addKaohzb(JSONArray priceScheme);

	public void updateKaohzb(JSONArray priceScheme,String caigddb_id);

	public JSONObject delKaohzb(Map<String, Object> map);

}
