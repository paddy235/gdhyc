package com.zhiren.fuelmis.dc.service.xitgl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;

/** 
 * @author 陈宝露
 */
public interface IDiancxxService {
	List<Diancxx> getAll();
	
	Diancxx getOne();
	
	JSONObject selectAll(Map<String,Object> map);
	
	JSONArray insertDiancxx(Map<String,Object> map);
	
	JSONArray updateDiancxx(Map<String, Object> map);
	
	JSONArray getOneById(Map<String, Object> map);
}
