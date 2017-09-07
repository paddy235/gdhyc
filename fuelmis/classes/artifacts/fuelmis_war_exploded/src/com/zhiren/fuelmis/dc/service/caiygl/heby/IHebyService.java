package com.zhiren.fuelmis.dc.service.caiygl.heby;

import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;

import net.sf.json.JSONArray;

public interface IHebyService {


	List<Map<String, Object>> getSamcodeList(String riq);

	List<Map<String, Object>> getList(String riq);

	void updateList(List<Map<String, Object>> list);

	void updateCancelList(List<Map<String, Object>> list);
}
