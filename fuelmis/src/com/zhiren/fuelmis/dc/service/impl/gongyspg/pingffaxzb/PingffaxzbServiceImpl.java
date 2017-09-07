package com.zhiren.fuelmis.dc.service.impl.gongyspg.pingffaxzb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.gongyspg.pingffaxzb.PingffaxzbDao;
import com.zhiren.fuelmis.dc.entity.gongyspg.pingffaxzb.Pingffaxzb;
import com.zhiren.fuelmis.dc.service.gongyspg.pingffaxzb.PingffaxzbService;


@Service("pingffaxzbService")
public class PingffaxzbServiceImpl implements PingffaxzbService {

	@Autowired
	private PingffaxzbDao pingffaxzbDao;

	@Override
	public JSONArray addPingffaxzb(Pingffaxzb pingffaxzb) {
		int result = 0;
		try{
			result = pingffaxzbDao.addPingffaxzb(pingffaxzb);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray updatePingffaxzb(Pingffaxzb pingffaxzb) {
		int result = 0;
		try{
			result = pingffaxzbDao.updatePingffaxzb(pingffaxzb);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getPingffaxzbList(Map<String, Object> map) {
		List<Map<String,Object>> list = pingffaxzbDao.getPingffaxzbList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{dataMap.get("ID"),dataMap.get("PINGFFAB_ID"),dataMap.get("ZHIBDM"),dataMap.get("ZHIBGS"),
					dataMap.get("ZHIBFZ"),dataMap.get("LEIX"),dataMap.get("WENZSM")};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONArray delPingffaxzb(Map<String, Object> map) {
		int result = 0;
		try{
			result = pingffaxzbDao.delPingffaxzb(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getPingffaxzbById(Map<String, Object> map) {
		List<Pingffaxzb> list = pingffaxzbDao.getPingffaxzbById(map);
		Pingffaxzb pingffaxzb = null;
		if(list.size()>0){
			pingffaxzb = list.get(0);
		}
		JSONObject json = JSONObject.fromObject(pingffaxzb);
		return json;
	}

	
}
