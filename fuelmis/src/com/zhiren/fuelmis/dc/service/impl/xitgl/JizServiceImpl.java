package com.zhiren.fuelmis.dc.service.impl.xitgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.xitgl.JizDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Jiz;
import com.zhiren.fuelmis.dc.service.xitgl.IJizService;

/** 
 * @author 陈宝露
 */
@Service
public class JizServiceImpl implements IJizService {
	
	@Autowired
	private JizDao jizDao;

	@Override
	public JSONObject getAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Jiz> list = jizDao.getAll(map);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i=0;i<list.size();i++){
			Jiz jiz = list.get(i);
			objs[i] = new Object[]{jiz.getId(),jiz.getXuh(),jiz.getJizbh(),jiz.getJizurl(),jiz.getToucrq(),
					jiz.getRijhm(),jiz.getMeihl(),jiz.getJihdl(),jiz.getShejmz()};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONArray insertJiz(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = jizDao.insertJiz(map);
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
		List<Jiz> list = jizDao.getOne(map);
		JSONArray jsonArray = new JSONArray();
		if(list!=null){
			if(list.size()>0){
				Jiz jiz = list.get(0);
				jsonArray.add(jiz);
			}
		}
		return jsonArray;
	}

	@Override
	public JSONArray updateJiz(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = jizDao.updateJiz(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray deleteJiz(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = jizDao.deleteJiz(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

}
