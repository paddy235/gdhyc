package com.zhiren.fuelmis.dc.service.impl.xitgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.xitgl.ChezxxDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Chezxx;
import com.zhiren.fuelmis.dc.service.xitgl.IChezxxService;

/** 
 * @author 陈宝露
 */
@Service
public class ChezxxServiceImpl implements IChezxxService {

	@Autowired
	private ChezxxDao chezxxDao;
	
	@Override
	public JSONObject getAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Chezxx> list = chezxxDao.getAll(map);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i=0;i<list.size();i++){
			Chezxx chezxx = list.get(i);
			objs[i] = new Object[]{chezxx.getId(),chezxx.getXuh(),chezxx.getBianm(),chezxx.getMingc(),
					chezxx.getQuanc(),chezxx.getPiny(),chezxx.getLujxxb_id(),chezxx.getBeiz()};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONArray insertChezxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = chezxxDao.insertChezxx(map);
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
		List<Chezxx> list = chezxxDao.getOne(map);
		JSONArray jsonArray = new JSONArray();
		if(list!=null){
			if(list.size()>0){
				Chezxx chezxx = list.get(0);
				chezxx.setLeib(chezxx.getLeib().equals("港口")?"2":"1");
				jsonArray.add(chezxx);
			}
		}
		return jsonArray;
	}

	@Override
	public JSONArray updateChezxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = chezxxDao.updateChezxx(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray deleteChezxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = chezxxDao.deleteChezxx(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

}
