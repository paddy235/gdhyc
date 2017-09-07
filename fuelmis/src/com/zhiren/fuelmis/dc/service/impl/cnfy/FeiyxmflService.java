package com.zhiren.fuelmis.dc.service.impl.cnfy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.cnfy.FeiyxmflDao;
import com.zhiren.fuelmis.dc.service.cnfy.IFeiyxmflService;
@Service
public class FeiyxmflService implements IFeiyxmflService {
	
	@Autowired
	private FeiyxmflDao feiyxmflDao;

	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Override
	public JSONObject getAllData() {
		List list = feiyxmflDao.getAllData();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{hashMap.get("ID"),hashMap.get("ROWNUM"),hashMap.get("BIANM"),
					hashMap.get("MINGC"),hashMap.get("SHUOM"),hashMap.get("KJMINGC"),hashMap.get("ZHUANGT")}; 
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getFenlkj() {
		// TODO Auto-generated method stub
		return feiyxmflDao.getFenlkj();
	}
	@Override
	public int addFeiyxmflData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return feiyxmflDao.addFeiyxmflData(map);
	}
	@Override
	public String getMaxBianm() {
		// TODO Auto-generated method stub
		return feiyxmflDao.getMaxBianm();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public JSONObject getFeiyxmflById(String id) {
		// TODO Auto-generated method stub
		List list = feiyxmflDao.getFeiyxmflById(id);
		JSONObject jsonobj = JSONObject.fromObject(list.get(0));
		return jsonobj;
	}
	@Override
	public int updateFeiyxmfl(Map<String, Object> map) {
		int  result;
		try{
			result = feiyxmflDao.updateFeiyxmfl(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int changeState(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return feiyxmflDao.changeState(map);
	}
	@Override
	public int getState(String id) {
		// TODO Auto-generated method stub
		return feiyxmflDao.getState(id);
	}
}
