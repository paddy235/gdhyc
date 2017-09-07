package com.zhiren.fuelmis.dc.service.impl.cnfy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.cnfy.YustzsqDao;
import com.zhiren.fuelmis.dc.service.cnfy.IYustzsqService;
@Service
public class YuestzsqService implements IYustzsqService {
	
	@Autowired
	private YustzsqDao yustzsqDao;

	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Override
	public JSONObject getYustzsqData(Map<String, Object> map) {
		List list = yustzsqDao.getYustzsqData(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{hashMap.get("ID"),hashMap.get("ROWNUM"),hashMap.get("DANW"),hashMap.get("FEIYMC"),hashMap.get("YUSED"),hashMap.get("SHUOM")}; 
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public int addYustzData(Map<String, Object> map) {
		int  result;
		try{
			result = yustzsqDao.addYustzData(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	@Override
	public int delYustzById(String id) {
		int  result;
		try{
			result = yustzsqDao.delYustzById(id);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public JSONObject getYustzById(String id) {
		List list = yustzsqDao.getYustzById(id);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = (HashMap<String, Object>)list.get(0);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public int updateYustzById(Map<String, Object> map) {
		int  result;
		try{
			result = yustzsqDao.updateYustzById(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
		}

	
}
