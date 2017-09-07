package com.zhiren.fuelmis.dc.service.impl.xitgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.xitgl.PinzDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Pinz;
import com.zhiren.fuelmis.dc.service.xitgl.IPinzService;

/** 
 * @author 陈宝露
 */
@Service
public class PinzServiceImpl implements IPinzService {
	
	@Autowired
	private PinzDao pinzDao;

	@Override
	public JSONObject getAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Pinz> list = pinzDao.getAll(map);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i=0;i<list.size();i++){
			Pinz pinz = list.get(i);
			objs[i] = new Object[]{pinz.getId(),pinz.getXuh(),pinz.getBianm(),pinz.getMingc(),pinz.getPiny(),
					pinz.getZhuangt()==1?"使用":"停用",pinz.getPinzms(),pinz.getLeib()};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONArray insertPinzxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = pinzDao.insertPinzxx(map);
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
		List<Pinz> list = pinzDao.getOne(map);
		JSONArray jsonArray = new JSONArray();
		if(list!=null){
			if(list.size()>0){
				Pinz pinz = list.get(0);
				pinz.setLeib("1");
				jsonArray.add(pinz);
			}
		}
		return jsonArray;
	}

	@Override
	public JSONArray updatePinzxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = pinzDao.updatePinzxx(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray delPinzxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = pinzDao.delPinzxx(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

}
