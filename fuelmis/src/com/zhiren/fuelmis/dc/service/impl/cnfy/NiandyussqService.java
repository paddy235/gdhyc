package com.zhiren.fuelmis.dc.service.impl.cnfy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.cnfy.NiandYussqDao;
import com.zhiren.fuelmis.dc.service.cnfy.INiandyussqService;
@Service
public class NiandyussqService implements INiandyussqService {
	
	@Autowired
	private NiandYussqDao niandYussqDao;

	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Override
	public JSONObject getYussqData(Map<String, Object> map) {
		List list = niandYussqDao.getNiandYussqData(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{hashMap.get("ID"),hashMap.get("ROWNUM"),hashMap.get("FEIYMC"),
					hashMap.get("YUSED"),hashMap.get("SHUOM")}; 
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public int addNiandysData(Map<String, Object> map) {
		int  result;
		try{
			result = niandYussqDao.addNiandysData(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	@Override
	public int delNiandyusById(String id) {
		int  result;
		try{
			result = niandYussqDao.delNiandyusById(id);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public JSONObject getNinadyusById(String id) {
		List list = niandYussqDao.getNinadyusById(id);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = (HashMap<String, Object>)list.get(0);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public int updateNiandysyId(Map<String, Object> map) {
		int  result;
		try{
			result = niandYussqDao.updateNiandysyId(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
		}
}
