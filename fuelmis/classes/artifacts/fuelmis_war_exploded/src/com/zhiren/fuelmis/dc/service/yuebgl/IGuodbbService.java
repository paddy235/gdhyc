package com.zhiren.fuelmis.dc.service.yuebgl;

import java.util.Map;

import net.sf.json.JSONArray;

/** 
 * @author 陈宝露
 */
public interface IGuodbbService {
	JSONArray getMeishcbb(Map<String,Object> map);
	
	JSONArray getShulysbb(String fangx,Map<String,Object> map);
	
	JSONArray getZhilysbb(Map<String,Object> map);
	
	JSONArray getRucbmdjbb(Map<String,Object> map);
	
	JSONArray getRanyshc(Map<String,Object> map);
	
	JSONArray getRulmrz(Map<String,Object> map);
	
	JSONArray getRanlcbbb(Map<String,Object> map);
	
	JSONArray getChangnfycx(Map<String,Object> map);
	
	JSONArray getChangnfycxMX(Map<String,Object> map);
	
	JSONArray getRanlzbqkb(Map<String,Object> map);

	JSONArray getShulysbb(Map<String, Object> map);
}
