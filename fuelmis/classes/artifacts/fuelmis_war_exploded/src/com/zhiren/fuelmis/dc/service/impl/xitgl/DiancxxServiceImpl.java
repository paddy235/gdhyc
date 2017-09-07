package com.zhiren.fuelmis.dc.service.impl.xitgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.xitgl.DiancxxDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.service.xitgl.IDiancxxService;

/** 
 * @author 陈宝露
 */
@Service
public class DiancxxServiceImpl implements IDiancxxService {

	@Autowired
	private DiancxxDao diancxxDao;
	
	@Override
	public List<Diancxx> getAll() {
		// TODO Auto-generated method stub
		return diancxxDao.getAll();
	}

	@Override
	public Diancxx getOne() {
		// TODO Auto-generated method stub
		return diancxxDao.getOne().get(0);
	}

	@Override
	public JSONObject selectAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Diancxx> list = diancxxDao.selectAll(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			Diancxx diancxx = list.get(i);
			objs[i] = new Object[]{diancxx.getId(),diancxx.getXuh(),diancxx.getBianm(),diancxx.getMingc(),diancxx.getQuanc(),
					diancxx.getPiny(),diancxx.getShengfb_id(),diancxx.getFuid(),diancxx.getDaoz(),diancxx.getDaog()};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		
		return result;
	}

	@Override
	public JSONArray insertDiancxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = diancxxDao.insertDiancxx(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray updateDiancxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = diancxxDao.updateDiancxx(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray getOneById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Diancxx> list = diancxxDao.getOneById(map);
		JSONArray jsonArray = new JSONArray();
		if(list!=null){
			if(list.size()>0){
				Diancxx diancxx = list.get(0);
				diancxx.setCaiyfs(diancxx.getCaiyfs().equals("机械")?"2":"1");
				diancxx.setJilfs(diancxx.getJilfs().equals("检尺")?"2":"1");
				jsonArray.add(diancxx);
			}
		}
		return jsonArray;
	}
}
