package com.zhiren.fuelmis.dc.service.impl.hetgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.hetgl.HetmbglDao;
import com.zhiren.fuelmis.dc.entity.common.Fujxx;
import com.zhiren.fuelmis.dc.service.hetgl.HetmbglService;

@Service("hetmbglService")
public class HetmbglServiceImpl implements HetmbglService {
	
	@Autowired
	private HetmbglDao hetmbglDao;

	@Override
	public JSONArray updateHetmb(Fujxx fujxx) {
		int result = 0;
		try{
			result = hetmbglDao.updateHetmb(fujxx);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getHetmbList(Map<String, Object> map) {
		List<Map<String,Object>> list = hetmbglDao.getHetmbList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{dataMap.get("ID"),dataMap.get("DAIM"),dataMap.get("MINGC"),dataMap.get("YOUXKSRQ"),
					dataMap.get("YOUXJSRQ")};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONObject getHetmbsubList(Map<String, Object> map) {
		List<Map<String,Object>> list = hetmbglDao.getHetmbsubList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{dataMap.get("ID"),dataMap.get("MINGC"),dataMap.get("SHUOM")};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}	
	
	
	@Override
	public JSONObject getHetmbById(Map<String, Object> map) {
		List<Fujxx> list = hetmbglDao.getHetmbById(map);
		Fujxx hetmb = null;
		if(list.size()>0){
			hetmb = list.get(0);
		}
		JSONObject json = JSONObject.fromObject(hetmb);
		return json;
	}

	@Override
	public JSONObject delHetmb(Map<String, Object> map) {
		int result = 0;
		try{
			int count = hetmbglDao.getHetmbglCount(map);
			if(count ==0){
				result = 1;
				hetmbglDao.delHetmb(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
		
	}


	

}
