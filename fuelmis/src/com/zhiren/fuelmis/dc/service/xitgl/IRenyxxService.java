package com.zhiren.fuelmis.dc.service.xitgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;

/**
 * @author 陈宝露
 */
public interface IRenyxxService {
	Renyxx getRenyxx(String userName,String password);
	
	JSONObject getAll(Map<String, Object> map);
	
	JSONArray insertRenyxx(Map<String, Object> map);
	
	JSONArray getOne(Map<String, Object> map);
	
	JSONArray updateRenyxx(Map<String, Object> map);
	
	JSONArray deleteRenyxx(Map<String, Object> map);
	
	JSONArray updateQuanx(Long id,String[] ziy);
	
	JSONArray getQuanx(Long renyxxb_id);
}
