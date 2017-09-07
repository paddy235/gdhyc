package com.zhiren.fuelmis.dc.service.yuebgl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

/** 
 * @author 陈宝露
 */
public interface IYuebscService {
	JSONArray getYueb(Map<String, Object> map);
	void  yuebsc(Map<String, Object> map, List<Map<String, Object>> yueb) throws Exception;
}
