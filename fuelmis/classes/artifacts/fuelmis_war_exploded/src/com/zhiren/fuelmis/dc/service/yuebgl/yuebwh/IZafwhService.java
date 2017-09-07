package com.zhiren.fuelmis.dc.service.yuebgl.yuebwh;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

/**
 * @author 陈宝露
 */
public interface IZafwhService {
	
	JSONArray getAll(Map<String, Object> map);

	JSONArray getZfmingc();

	void deletezf(List<Map<String, Object>> l);

	void saveZf(List<Map<String, Object>> l);

	List<Map<String,Object>> getZafeiByDiancidAndRiq(Map<String,Object> map);

	int DelZafeiByDiancidAndRiq(Map<String,Object> map);

	int CopyZafeiData(Map<String,Object> map);

	JSONArray getAll_new(Map<String, Object> map);

}
