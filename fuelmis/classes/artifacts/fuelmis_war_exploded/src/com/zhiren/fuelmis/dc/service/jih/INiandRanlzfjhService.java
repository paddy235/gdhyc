package com.zhiren.fuelmis.dc.service.jih;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface INiandRanlzfjhService {
	
	String getTabelData(String diancid,String year);
	
	JSONObject getRanlzfData(Map<String, Object> map);
	
	JSONObject getRanlzfById(String id);
	
	@SuppressWarnings("rawtypes")
	List getRanlzfByDiancidAndRiq(Map<String, Object> map);
	
	int addRanlzfData(Map<String, Object> map);
	
	int delRanlzfById(String id);
	
	int DelRanlzfByDiancidAndRiq(Map<String, Object> map);
	
	int updateRanlzfById(Map<String, Object> map);
	
	int CopyRanlzfData(Map<String, Object> map);
}
