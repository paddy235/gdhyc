package com.zhiren.fuelmis.dc.service.hetgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.hetgl.Rlhtmb;

public interface RlhtmbService {
	
	public JSONArray updateHetmb(Rlhtmb rlhtmb);

	public JSONObject getHetmbList(Map<String, Object> map);

	public JSONObject getHetmbById(Map<String, Object> map);

	public JSONObject delHetmb(Map<String, Object> map);

	
	public JSONArray addHetmb(Rlhtmb hetmb);

	public JSONObject getHetmbByhtId(Map<String, Object> map);
	
}
