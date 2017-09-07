package com.zhiren.fuelmis.dc.service.impl.hetgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.dao.hetgl.PriceItemDao;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceItem;
import com.zhiren.fuelmis.dc.service.hetgl.PriceItemService;
import com.zhiren.fuelmis.dc.utils.Sequence;

@Service("priceItemService")
public class PriceItemServiceImpl implements PriceItemService {

	@Autowired
	private PriceItemDao priceItemDao;
	
	@Override
	public JSONArray addPriceItem(PriceItem priceItem) {
		int result = 0;
		try{
			priceItem.setId(Long.parseLong(Sequence.nextId().toString()));
			result = priceItemDao.addPriceItem(priceItem);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray updatePriceItem(PriceItem priceItem) {
		int result = 0;
		try{
			result = priceItemDao.updatePriceItem(priceItem);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getPriceItemList(Map<String, Object> map) {
		List<Map<String,Object>> list = priceItemDao.getPriceItemList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i); 
			objs[i] = new Object[]{dataMap.get("ID"),dataMap.get("NAME"),dataMap.get("CODE"),dataMap.get("STATUS")};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONObject delPriceItem(Map<String, Object> map) {
		int result = 1;
		try{
			priceItemDao.delPriceItem(map);
		}catch(Exception e){
			result = 0;
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}

	@Override
	public JSONObject getPriceItemById(Map<String, Object> map) {
		List<PriceItem> list = priceItemDao.getPriceItemById(map);
		PriceItem priceItemDao= null;
		if(list.size()>0){
			priceItemDao = list.get(0);
		}
		JSONObject json = JSONObject.fromObject(priceItemDao);
		return json;
	}

}
