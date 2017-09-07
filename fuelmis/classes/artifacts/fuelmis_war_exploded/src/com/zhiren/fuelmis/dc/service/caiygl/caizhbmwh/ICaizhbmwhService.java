package com.zhiren.fuelmis.dc.service.caiygl.caizhbmwh;

import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;

import net.sf.json.JSONArray;

public interface ICaizhbmwhService {
	List<Map<String, Object>> getBianm(String diancid, String riq);

	void updateBianm(JSONArray array, Renyxx user);

	JSONArray generateBianm(JSONArray array, String riq);
}
