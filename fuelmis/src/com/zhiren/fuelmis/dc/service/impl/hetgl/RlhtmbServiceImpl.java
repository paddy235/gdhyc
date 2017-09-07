package com.zhiren.fuelmis.dc.service.impl.hetgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.hetgl.RlhtmbDao;
import com.zhiren.fuelmis.dc.entity.hetgl.Rlhtmb;
import com.zhiren.fuelmis.dc.service.hetgl.RlhtmbService;
import com.zhiren.fuelmis.dc.utils.Sequence;

@Service("rlhtmbService")
public class RlhtmbServiceImpl implements RlhtmbService {

	@Autowired
	private RlhtmbDao rlhtmbDao;
	
	@Override
	public JSONArray updateHetmb(Rlhtmb rlhtmb) {
		int result = 0;
		try{
			result = rlhtmbDao.updateHetmb(rlhtmb);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getHetmbList(Map<String, Object> map) {
		List<Map<String,Object>> list = rlhtmbDao.getHetmbList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{dataMap.get("ID"),dataMap.get("MUBBH"),dataMap.get("MINGC"),dataMap.get("LUJ"),dataMap.get("YOUXKSSJ"),
					dataMap.get("YOUXJSSJ"),dataMap.get("CAOZRY"),dataMap.get("CAOZSJ"),dataMap.get("ZHUANGT")};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONObject getHetmbById(Map<String, Object> map) {
		List<Rlhtmb> list = rlhtmbDao.getHetmbById(map);
		Rlhtmb hetmb= null;
		if(list.size()>0){
			hetmb = list.get(0);
		}
		JSONObject json = JSONObject.fromObject(hetmb);
		return json;
	}


	@Override
	public JSONObject delHetmb(Map<String, Object> map) {
		int result = 1;
		try{
			rlhtmbDao.delHetmb(map);
		}catch(Exception e){
			result = 0;
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}

	@Override
	public JSONArray addHetmb(Rlhtmb hetmb) {
		int result = 0;
		try{
			hetmb.setId(Long.parseLong(Sequence.nextId().toString()));
			result = rlhtmbDao.addHetmb(hetmb);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getHetmbByhtId(Map<String, Object> map) {
		List<Rlhtmb> list = rlhtmbDao.getHetmbByhtId(map);
		Rlhtmb hetmb= null;
		if(list.size()>0){
			hetmb = list.get(0);
		}
		JSONObject json = JSONObject.fromObject(hetmb);
		return json;
	}

}
