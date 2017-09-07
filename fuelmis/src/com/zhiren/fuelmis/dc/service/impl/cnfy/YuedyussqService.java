package com.zhiren.fuelmis.dc.service.impl.cnfy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.cnfy.YuedYussqDao;
import com.zhiren.fuelmis.dc.service.cnfy.IYuedyussqService;
@Service
public class YuedyussqService implements IYuedyussqService {
	
	@Autowired
	private YuedYussqDao yuedYussqDao;

	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Override
	public JSONObject getYussqData(Map<String, Object> map) {
		List list = yuedYussqDao.getYussqData(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{hashMap.get("ID"),hashMap.get("ROWNUM"),hashMap.get("FEIYMC"),hashMap.get("YUSED"),hashMap.get("SHUOM")}; 
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getFeiyMingc() {
		return yuedYussqDao.getFeiyMingc();
	}

	@Override
	public int addYuedysData(Map<String, Object> map) {
		int  result;
		try{
			result = yuedYussqDao.addYuedysData(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	@Override
	public int delYuedyusById(String id) {
		int  result;
		try{
			result = yuedYussqDao.delYuedyusById(id);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
		}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public JSONObject getYuedyusById(String id) {
		List list = yuedYussqDao.getYuedyusById(id);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = (HashMap<String, Object>)list.get(0);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public int updateYudysyId(Map<String, Object> map) {
		int  result;
		try{
			result = yuedYussqDao.updateYudysyId(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
		}
}
