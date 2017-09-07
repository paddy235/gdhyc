package com.zhiren.fuelmis.dc.service.jiesgl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

public interface IYuejstjtzService {
	JSONArray getYuejstjtz(Map<String,Object> map);

	List<Map<String,Object>> getHetbh(Map<String, Object> map);
}
