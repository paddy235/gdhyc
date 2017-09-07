package com.zhiren.fuelmis.dc.service.impl.gongyspg.kaohzb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.gongyspg.kaohzb.KaohzbDao;
import com.zhiren.fuelmis.dc.entity.gongyspg.kaohzb.Kaohzb;
import com.zhiren.fuelmis.dc.service.gongyspg.kaohzb.KaohzbService;

@Service("kaohzbService")
public class KaohzbServiceImpl implements KaohzbService{

	@Autowired
	private KaohzbDao kaohzbDao;

	@Override
	public JSONArray addKaohzb(Kaohzb kaohzb) {
		int result = 0;
		try{
			result = kaohzbDao.addKaohzb(kaohzb);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;		// TODO Auto-generated method stub
	}

	@Override
	public JSONArray updateKaohzb(Kaohzb kaohzb) {
		int result = 0;
		try{
			result = kaohzbDao.updateKaohzb(kaohzb);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getKaohzbList(Map<String, Object> map) {
		List<Map<String,Object>> list = kaohzbDao.getKaohzbList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{dataMap.get("ID"),dataMap.get("ZHIBMC"),dataMap.get("ZHIBDM"),
					dataMap.get("ZHIBDW"),dataMap.get("ZHUANGT"),dataMap.get("LEIB"),dataMap.get("DIANCXXB_ID"),dataMap.get("BEIZ")};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONArray delKaohzb(Map<String, Object> map) {
		int result = 0;
		try{
			result = kaohzbDao.delKaohzb(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getKaohzbById(Map<String, Object> map) {
		List<Kaohzb> list = kaohzbDao.getKaohzbById(map);
		Kaohzb kaohzb = null;
		if(list.size()>0){
			kaohzb = list.get(0);
		}
		JSONObject json = JSONObject.fromObject(kaohzb);
		return json;
	}
	
}
