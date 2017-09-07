package com.zhiren.fuelmis.dc.service.cnfy;

import java.util.Map;
import net.sf.json.JSONObject;

public interface INiandyussqService {
	
	JSONObject getYussqData(Map<String, Object> map);
	
	int addNiandysData(Map<String, Object> map);
	
	int delNiandyusById(String id);
	
	JSONObject getNinadyusById(String id);
	
	int updateNiandysyId(Map<String, Object> map);
	
}
