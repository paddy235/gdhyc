package com.zhiren.fuelmis.dc.service.impl.cnfy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.cnfy.FeiyxmsqDao;
import com.zhiren.fuelmis.dc.service.cnfy.IFeiyxmsqService;
@Service
public class FeiyxmsqService implements IFeiyxmsqService {
	
	@Autowired
	private FeiyxmsqDao feiyxmsqDao;

	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Override
	public JSONObject getFeiyxmData(Map<String, Object> map) {
		List list = feiyxmsqDao.getFeiyxmData(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{ hashMap.get("ID"),
									hashMap.get("ROWNUM"),
									hashMap.get("DANW"),
									hashMap.get("BIANM"),
									hashMap.get("FYXMFLMC"),
									hashMap.get("MINGC"),
									hashMap.get("GUANLFL"),
									hashMap.get("CAIWUFL"),
									hashMap.get("ZHUANGT"),
									hashMap.get("SHUOM"),
									hashMap.get("FEIYXMSX")};
			
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getFeiyxmfl() {
		return feiyxmsqDao.getFeiyxmfl();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getFeiyxmsx() {
		// TODO Auto-generated method stub
		return feiyxmsqDao.getFeiyxmsx();
	}

	@Override
	public int addFeiyxmData(Map<String, Object> map) {
		int  result;
		try{
			result = feiyxmsqDao.addFeiyxmData(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	@Override
	public int delFeiyxm(String id) {
		int  result;
		try{
			result = feiyxmsqDao.delFeiyxm(id);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public JSONObject getFeiyxmById(String id) {
		List list = feiyxmsqDao.getFeiyxmById(id);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = (HashMap<String, Object>)list.get(0);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public int updateFeiyxmById(Map<String, Object> map) {
		int  result;
		try{
			result = feiyxmsqDao.updateFeiyxmById(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
}
