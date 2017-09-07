package com.zhiren.fuelmis.dc.service.impl.xitgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.xitgl.LiclxDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Liclx;
import com.zhiren.fuelmis.dc.service.xitgl.ILiclxService;

/** 
 * @author 陈宝露
 */
@Service
public class LiclxServiceImpl implements ILiclxService {

	@Autowired
	private LiclxDao liclxDao;
	
	@Override
	public JSONObject getAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Liclx> list = liclxDao.getAll(map);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i=0;i<list.size();i++){
			Liclx liclx = list.get(i);
			objs[i] = new Object[]{liclx.getId(),liclx.getXuh(),liclx.getMingc(),
					liclx.getPiny(),liclx.getBeiz()};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONArray insertLiclx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = liclxDao.insertLiclx(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray getOne(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Liclx> list = liclxDao.getOne(map);
		JSONArray jsonArray = new JSONArray();
		if(list!=null){
			if(list.size()>0){
				Liclx liclx = list.get(0);
				jsonArray.add(liclx);
			}
		}
		return jsonArray;
	}

	@Override
	public JSONArray updateLiclx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = liclxDao.updateLiclx(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray delLiclx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = liclxDao.delLiclx(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

}
