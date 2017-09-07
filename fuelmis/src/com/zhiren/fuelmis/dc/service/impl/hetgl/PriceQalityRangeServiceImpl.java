package com.zhiren.fuelmis.dc.service.impl.hetgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.hetgl.PriceQalityRangeDao;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceQalityRange;
import com.zhiren.fuelmis.dc.service.hetgl.PriceQalityRangeService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;

@Service("priceQalityRangeService")
public class PriceQalityRangeServiceImpl implements PriceQalityRangeService {

	@Autowired
	private PriceQalityRangeDao priceQalityRangeDao;
	
	@Override
	public JSONArray addPriceQalityRange(PriceQalityRange priceQalityRange) {
		int result = 0;
		try{
			priceQalityRange.setId(Long.parseLong(Sequence.nextId().toString()));
			result = priceQalityRangeDao.addPriceQalityRange(priceQalityRange);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray updatePriceQalityRange(PriceQalityRange priceQalityRange) {
		int result = 0;
		try{
			result = priceQalityRangeDao.updatePriceQalityRange(priceQalityRange);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getPriceQalityRangeList(Map<String, Object> map) {
		List<Map<String,Object>> list = priceQalityRangeDao.getPriceQalityRangeList(map);
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
	public JSONObject delPriceQalityRange(Map<String, Object> map) {
		int result = 1;
		try{
			priceQalityRangeDao.delPriceQalityRange(map);
		}catch(Exception e){
			result = 0;
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}

	@Override
	public JSONObject getPriceQalityRangeById(Map<String, Object> map) {
		List<PriceQalityRange> list = priceQalityRangeDao.getPriceQalityRangeById(map);
		PriceQalityRange priceQalityRangeDao= null;
		if(list.size()>0){
			priceQalityRangeDao = list.get(0);
		}
		JSONObject json = JSONObject.fromObject(priceQalityRangeDao);
		return json;
	}

	@Override
	public JSONArray addPriceQalityRanges(JSONArray jsonArray,Long diancxxb_id,Long userid) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			for (int i = 0; i < jsonArray.size(); i++) {
				PriceQalityRange priceQalityRange = (PriceQalityRange) JSONObject.toBean((JSONObject)jsonArray.get(i),PriceQalityRange.class);
				priceQalityRange.setCreation_date(DateUtil.getCurrentTime());
				priceQalityRange.setCreated_by_userid(userid);
				priceQalityRange.setDiancxxb_id(diancxxb_id);
				priceQalityRange.setId(Long.parseLong(Sequence.nextId().toString()));
				result = priceQalityRangeDao.addPriceQalityRange(priceQalityRange);
			} 			
		} catch (Exception e) {
			e.printStackTrace();
		}

		jsonArray.add(result);
		return jsonArray;
	}
	
	@Override
	public JSONArray getPriceQalityRangeByComponentId(Map<String, Object> map) {
		List<PriceQalityRange> list = priceQalityRangeDao.getPriceQalityRangeByComponentId(map);
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray;
	}
}
