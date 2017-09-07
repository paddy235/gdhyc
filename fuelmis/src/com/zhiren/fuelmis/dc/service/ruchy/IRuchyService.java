package com.zhiren.fuelmis.dc.service.ruchy;

import java.util.Map;

import net.sf.json.JSONArray;

public interface IRuchyService {
	public JSONArray getMeiyjcjg(Map<String, Object> map);
	
	public String get_huay_zhillsb(String dcId,String bianm,byte[] XMLDate) ;
	
	public byte[] getFahInfo_jt(String dcId, String bianm);
}
