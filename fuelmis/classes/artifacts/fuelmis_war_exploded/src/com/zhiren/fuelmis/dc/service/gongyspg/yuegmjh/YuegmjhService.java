package com.zhiren.fuelmis.dc.service.gongyspg.yuegmjh;

import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface YuegmjhService {
	public List<Map<String, Object>> getYuegmjhList(Map<String,Object> map);

	public JSONArray addYuegmjh(Map<String, Object> map);

	public JSONArray delYuegmjh(Map<String, Object> map);

	public JSONObject searchYuegmjh(Map<String, Object> map);

	public boolean cheakHetzt(Map<String, Object> map);

	public List<Long> getupdateYuegmjh(Map<String, Object> map);


	public void saveYuegmjh(Map<String, Object> yuegongmjh, List<Map<String, Object>> yuegongmjhmx);

	public void submitYuegmjh(Map<String, Object> c);

}
