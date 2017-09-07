package com.zhiren.fuelmis.dc.service.xitgl;

import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.entity.xitgl.Gongysmkglb;
import com.zhiren.fuelmis.dc.entity.xitgl.Kuangzglb;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 陈宝露
 */
public interface IMeikxxService {
	JSONObject getAll(Map<String,Object> map);
	
	JSONArray insertMeikxx(Map<String,Object> map);
	
	JSONArray getOne(Map<String,Object> map);
	
	JSONArray updateMeikxx(Map<String,Object> map);
	
	JSONArray insertKuangzglb(List<Kuangzglb> list);
	
	JSONArray insertGongysmkglb(List<Gongysmkglb> list);
	
	JSONArray getKuangzglb(Map<String,Object> map);
	
	JSONArray getGongysmkglb(Map<String,Object> map);
}
