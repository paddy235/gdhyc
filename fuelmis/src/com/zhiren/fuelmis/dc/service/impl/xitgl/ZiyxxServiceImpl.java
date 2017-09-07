package com.zhiren.fuelmis.dc.service.impl.xitgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.xitgl.ZiyxxDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Ziyxx;
import com.zhiren.fuelmis.dc.service.xitgl.IZiyxxService;

/**
 * @author 陈宝露
 */
@Service
public class ZiyxxServiceImpl implements IZiyxxService {

	@Autowired
	private ZiyxxDao ziyxxDao;

	@Override
	public JSONArray getZiyxx() {
		// TODO Auto-generated method stub
		List<Ziyxx> list = ziyxxDao.getZiyxx(null);
		if (list != null) {
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				jsonArray.add(list.get(i));
			}

			return jsonArray;
		}
		return null;
	}

	@Override
	public JSONArray getTopMenu(Long renyxxb_id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("renyxxb_id", renyxxb_id);
		
		JSONArray jsonArray = new JSONArray();
		
		List<Ziyxx> menu = ziyxxDao.getParent(map);
		for (int i = 0; i < menu.size(); i++) {
			Ziyxx ziyxx = menu.get(i);
			map.clear();
			map.put("id", ziyxx.getId());
			map.put("renyxxb_id", renyxxb_id);
			List<Ziyxx> children = ziyxxDao.getChildren(map);
			for (int j = 0; j < children.size(); j++) {
				Ziyxx child = children.get(j);
				map.clear();
				map.put("id", child.getId());
				map.put("renyxxb_id", renyxxb_id);
				List<Ziyxx> children_children = ziyxxDao.getChildren(map);
				child.setChildren(children_children);
			}
			ziyxx.setChildren(children);
			jsonArray.add(ziyxx);
		}
		return jsonArray;
	}

	@Override
	public JSONObject insertZiyxx(Ziyxx ziyxx) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = ziyxxDao.insertZiyxx(ziyxx);
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("data", result);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return jsonObject;
	}

	@Override
	public JSONObject updateZiyxx(Ziyxx ziyxx) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = ziyxxDao.updateZiyxx(ziyxx);
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("data", result);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return jsonObject;
	}

	@Override
	public JSONObject deleteZiyxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = ziyxxDao.deleteZiyxx(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("data", result);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return jsonObject;
	}

	@Override
	public JSONArray getOne(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Ziyxx> list = ziyxxDao.getOne(map);
		if (list != null) {
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				jsonArray.add(list.get(i));
			}

			return jsonArray;
		}
		return null;
	}
}
