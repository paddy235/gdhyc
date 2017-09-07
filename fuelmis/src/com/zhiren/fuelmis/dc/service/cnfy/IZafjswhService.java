package com.zhiren.fuelmis.dc.service.cnfy;

import java.util.Map;

import net.sf.json.JSONObject;

public interface IZafjswhService {
	
	JSONObject getZafjsdata(String nianf);
	
	int saveZafDjb(Map<String, Object> map);
	
	int delZaffydjbById(String id);
	
	int delZaffybxdById(String id);
	
	int saveZaffybxd(Map<String, Object> map);
	
	int updateZaffybxd(Map<String, Object> map);
	
	int updateZafDjb(Map<String, Object> map);
}
