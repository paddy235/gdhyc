package com.zhiren.fuelmis.dc.service.ruchy;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IHuayshService {
	JSONObject getTableData(Map<String,Object> map);
	
	JSONArray updateZT(Map<String,Object> map);
	
	List<Map<String,Object>> getJincpcList(Map<String,Object> map);
	
	JSONObject getTableData2(Map<String,Object> map);
	
	JSONArray updateZT2(Map<String,Object> map);
	

}
