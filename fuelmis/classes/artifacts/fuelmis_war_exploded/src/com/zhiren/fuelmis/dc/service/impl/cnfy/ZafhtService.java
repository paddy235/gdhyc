package com.zhiren.fuelmis.dc.service.impl.cnfy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.cnfy.ZafhtDao;
import com.zhiren.fuelmis.dc.entity.jih.Hetbean;
import com.zhiren.fuelmis.dc.service.cnfy.IZafhtService;


import org.springframework.transaction.annotation.Transactional;

@Service
public class ZafhtService implements IZafhtService {
	
	@Autowired
	private ZafhtDao zafhtDao;

	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Override
	public JSONObject getZafhtByNianf(String nianf ) {
		List list = zafhtDao.getZafhtByNianf(nianf);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{hashMap.get("ID"),hashMap.get("ROWNUM"),hashMap.get("BIANH"),
					hashMap.get("MINGC"),hashMap.get("YIF"),hashMap.get("YOUXQ"),hashMap.get("QIANDRQ"),hashMap.get("ZHUANGT")}; 
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}
	@Override
	public int addhetData(Hetbean hetbean) {
		int  result;
		try{
			result = zafhtDao.addhetData(hetbean);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	@Override
	public int saveHetZaf(Map<String, Object> map) {
		int  result;
		try{
			result = zafhtDao.saveHetZaf(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	
	@Override
	public int delHetById(String id) {
		int  result;
		try{
			result = zafhtDao.delHetById(id);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	@Override
	public int delHetzafById(String id) {
		int  result;
		try{
			result = zafhtDao.delHetzafById(id);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public JSONObject getZafhtById(String id) {
		// TODO Auto-generated method stub
		List list = zafhtDao.getZafhtById(id);
		JSONObject jsonobj = JSONObject.fromObject(list.get(0));
		return jsonobj;
	}
	@Override
	public int updatehetData(Map<String, Object> map) {
		int  result;
		try{
			result = zafhtDao.updatehetData(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int updatehetzafData(Map<String, Object> map) {
		int  result;
		try{
			result = zafhtDao.updatehetzafData(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	@Override@Transactional
	public void submit(Map<String, Object> map) {
		map.put("ZHUANGT","1");
		zafhtDao.updateZafhtzt(map);//更改状态

	}

}
