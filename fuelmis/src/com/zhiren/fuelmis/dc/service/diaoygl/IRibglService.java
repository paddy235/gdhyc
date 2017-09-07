
package com.zhiren.fuelmis.dc.service.diaoygl;

import java.util.Map;

import net.sf.json.JSONArray;

/** 
 * @author 陈宝露
 */
public interface IRibglService {
	public JSONArray getAll(Map<String,Object> map);
	
	public JSONArray getAll2(Map<String,Object> map);
	
	public JSONArray createData(Map<String,Object> map);
	
	public JSONArray save(String strList);
	
	public JSONArray save2(String strList);
	
	public JSONArray createData2(Map<String, Object> map);
	
	public JSONArray getRibtbAll(String riq);
	
	public JSONArray getRibgsAll(String riq);
	
	void  RibtbShangc(JSONArray json);
	
	void  RibgsShangc(Map<String, Object> json);

	
}
