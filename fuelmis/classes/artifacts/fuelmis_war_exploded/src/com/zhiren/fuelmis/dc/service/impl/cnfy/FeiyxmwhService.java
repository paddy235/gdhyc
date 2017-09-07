package com.zhiren.fuelmis.dc.service.impl.cnfy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.cnfy.FeiyxmwhDao;
import com.zhiren.fuelmis.dc.service.cnfy.IFeiyxmwhService;
@Service
public class FeiyxmwhService implements IFeiyxmwhService {
	
	@Autowired
	private FeiyxmwhDao feiyxmwhDao;

	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Override
	public JSONObject getAllData() {
		List list = feiyxmwhDao.getAllData();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{hashMap.get("ID"),hashMap.get("ROWNUM"),hashMap.get("BIANM"),
					hashMap.get("MINGC"),hashMap.get("DANW"),hashMap.get("SHUOM"),hashMap.get("FEIYXMSX"),hashMap.get("FYXMFLMC"),
					hashMap.get("GUANLFL"),hashMap.get("CAIWUFL"),hashMap.get("ZHUANGT")}; 
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}
	@Override
	public int addfeiyxmwhData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return feiyxmwhDao.addfeiyxmwhData(map);
	}
	@Override
	public String getMaxBianm() {
		// TODO Auto-generated method stub
		return feiyxmwhDao.getMaxBianm();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public JSONObject getfeiyxmwhById(String id) {
		// TODO Auto-generated method stub
		List list = feiyxmwhDao.getFeiyxmwhById(id);
		JSONObject jsonobj = JSONObject.fromObject(list.get(0));
		return jsonobj;
	}
	@Override
	public int updatefeiyxmwh(Map<String, Object> map) {
		int  result;
		try{
			result = feiyxmwhDao.updateFeiyxmwh(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int changeState(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return feiyxmwhDao.changeState(map);
	}
	@Override
	public int getState(String id) {
		// TODO Auto-generated method stub
		return feiyxmwhDao.getState(id);
	}
}
