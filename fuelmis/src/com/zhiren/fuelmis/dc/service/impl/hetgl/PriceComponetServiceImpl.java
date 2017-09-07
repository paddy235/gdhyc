package com.zhiren.fuelmis.dc.service.impl.hetgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.hetgl.PriceComponetDao;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceComponent;
import com.zhiren.fuelmis.dc.service.hetgl.PriceComponetService;
import com.zhiren.fuelmis.dc.utils.Sequence;

@Service("priceComponetService")
public class PriceComponetServiceImpl implements PriceComponetService {

	@Autowired
	private PriceComponetDao priceComponetDao;
	
	@Override
	public JSONArray addPriceComponet(PriceComponent priceComponet) {
		int result = 0;
		try{
			priceComponet.setId(Long.parseLong(Sequence.nextId().toString()));
			result = priceComponetDao.addPriceComponet(priceComponet);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray updatePriceComponet(PriceComponent priceComponet) {
		int result = 0;
		try{
			result = priceComponetDao.updatePriceComponet(priceComponet);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getPriceComponetList(Map<String, Object> map) {
		List<Map<String,Object>> list = priceComponetDao.getPriceComponetList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i); 
			objs[i] = new Object[]{dataMap.get("ID"),dataMap.get("NAME"),dataMap.get("CLASSNAME"),dataMap.get("URL"),dataMap.get("REMARKS"),
					dataMap.get("STATUS")};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONObject delPriceComponet(Map<String, Object> map) {
		int result = 1;
		try{
			priceComponetDao.delPriceComponet(map);
		}catch(Exception e){
			result = 0;
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}

	@Override
	public JSONObject getPriceComponetById(Map<String, Object> map) {
		List<PriceComponent> list = priceComponetDao.getPriceComponetById(map);
		PriceComponent priceComponent= null;
		if(list.size()>0){
			priceComponent = list.get(0);
		}
		JSONObject json = JSONObject.fromObject(priceComponent);
		return json;
	}

}
