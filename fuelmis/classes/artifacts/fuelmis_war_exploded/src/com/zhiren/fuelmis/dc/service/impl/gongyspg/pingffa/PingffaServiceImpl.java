package com.zhiren.fuelmis.dc.service.impl.gongyspg.pingffa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.gongyspg.pingffa.PingffaDao;
import com.zhiren.fuelmis.dc.entity.gongyspg.pingffa.Pingffa;
import com.zhiren.fuelmis.dc.service.gongyspg.pingffa.PingffaService;

@Service("pingffaService")
public class PingffaServiceImpl implements PingffaService{
	@Autowired
	private PingffaDao pingffaDao;
	

	@Override
	public JSONArray addPingffa(Pingffa pingffa) {
		int result = 0;
		try{
			result = pingffaDao.addPingffa(pingffa);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray updatePingffa(Pingffa pingffa) {
		int result = 0;
		try{
			result = pingffaDao.updatePingffa(pingffa);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getPingffaList(Map<String, Object> map) {
		List<Map<String,Object>> list = pingffaDao.getPingffaList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{dataMap.get("ID"),dataMap.get("MINGC"),dataMap.get("JIHKJ_ID"),
					dataMap.get("QISRQ"),dataMap.get("JIEZRQ"),dataMap.get("ZHUANGT"),
					dataMap.get("SHANGCZT"),dataMap.get("FUJMC")};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONArray delPingffa(Map<String, Object> map) {
		int result = 0;
		try{
			result = pingffaDao.delPingffa(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getPingffaById(Map<String, Object> map) {
		List<Pingffa> list = pingffaDao.getPingffaById(map);
		Pingffa pingffa = null;
		if(list.size()>0){
			pingffa = list.get(0);
		}
		JSONObject json = JSONObject.fromObject(pingffa);
		return json;
	}

}
