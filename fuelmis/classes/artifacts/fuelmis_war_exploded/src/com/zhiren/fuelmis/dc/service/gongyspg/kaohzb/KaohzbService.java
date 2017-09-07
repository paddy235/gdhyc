package com.zhiren.fuelmis.dc.service.gongyspg.kaohzb;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.gongyspg.kaohzb.Kaohzb;

public interface KaohzbService {

	public JSONArray addKaohzb(Kaohzb kaohzb);

	public JSONArray updateKaohzb(Kaohzb kaohzb);

	public JSONObject getKaohzbList(Map<String, Object> map);

	public JSONArray delKaohzb(Map<String, Object> map);

	public JSONObject getKaohzbById(Map<String, Object> map);


}
