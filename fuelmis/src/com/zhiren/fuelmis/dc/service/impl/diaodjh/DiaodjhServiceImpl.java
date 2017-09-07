package com.zhiren.fuelmis.dc.service.impl.diaodjh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.diaodjh.DiaodjhDao;
import com.zhiren.fuelmis.dc.entity.diaodjh.Diaodjh;
import com.zhiren.fuelmis.dc.service.diaodjh.DiaodjhService;

@Service("diaodjhService")
public class DiaodjhServiceImpl implements DiaodjhService {

	@Autowired
	private DiaodjhDao diaodjhDao;

	@Override
	public JSONArray addDiaodjh(Diaodjh diaodjh) {
		int result = 0;
		try {
			result = diaodjhDao.addDiaodjh(diaodjh);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getDiaodjhList(Map<String, Object> map) {
		List<Map<String, Object>> list = diaodjhDao.getDiaodjhList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list
					.get(i);
			objs[i] = new Object[] { dataMap.get("ID"), dataMap.get("BIANH"),
					dataMap.get("RIQ"), dataMap.get("PINZB_ID"),
					dataMap.get("JIHSL"), dataMap.get("MEIKXXB_ID"),
					dataMap.get("LURY"), dataMap.get("PIZR"),
					dataMap.get("LURSJ"), dataMap.get("ZHUANGT") };
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONObject getDiaodjhinfoList(String strdate, String enddate,
			String diancid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("strdate", strdate);
		map.put("enddate", enddate);
		map.put("diancid", diancid);
		List<Map<String, Object>> list = diaodjhDao.getDiaodjhinfoList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list
					.get(i);
			objs[i] = new Object[] { dataMap.get("ID"), dataMap.get("RIQ"),
					dataMap.get("WEEK"), dataMap.get("JIHSL"),
					dataMap.get("LURY"), dataMap.get("SHENH") };
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public String getBianm(String riq) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("riq", riq);
		return diaodjhDao.getBianm(map);
	}

	@Override
	public JSONArray delDiaodjh(Map<String, Object> map) {
		int result = 0;
		try {
			result = diaodjhDao.delDiaodjh(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getDiaodById(Map<String, Object> map) {
		List<Diaodjh> list = diaodjhDao.getDiaodById(map);
		Diaodjh diaodjh = null;
		if (list.size() > 0) {
			diaodjh = list.get(0);
		}
		JSONObject json = JSONObject.fromObject(diaodjh);
		return json;
	}

	@Override
	public JSONArray updateDiaodjh(Diaodjh diaodjh) {
		int result = 0;
		try {
			result = diaodjhDao.updateDiaodjh(diaodjh);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

}
