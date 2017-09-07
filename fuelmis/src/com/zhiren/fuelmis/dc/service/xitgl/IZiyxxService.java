package com.zhiren.fuelmis.dc.service.xitgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.xitgl.Ziyxx;

/** 
 * @author 陈宝露
 */
public interface IZiyxxService {
	JSONArray getZiyxx();
	
	JSONArray getTopMenu(Long renyxxb_id);
	
	JSONObject insertZiyxx(Ziyxx ziyxx);
	
	JSONObject updateZiyxx(Ziyxx ziyxx);
	
	JSONObject deleteZiyxx(Map<String,Object> map);
	
	JSONArray getOne(Map<String,Object> map);
}
