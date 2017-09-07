package com.zhiren.fuelmis.dc.service.hetgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.common.Fujxx;

public interface HetmbglService {

	public JSONArray updateHetmb(Fujxx fujxx);

	public JSONObject getHetmbList(Map<String, Object> map);

	public JSONObject getHetmbById(Map<String, Object> map);

	public JSONObject getHetmbsubList(Map<String,Object> map);

	public JSONObject delHetmb(Map<String, Object> map);


}
