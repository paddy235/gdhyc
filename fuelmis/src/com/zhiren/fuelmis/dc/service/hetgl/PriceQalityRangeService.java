package com.zhiren.fuelmis.dc.service.hetgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceQalityRange;

public interface PriceQalityRangeService {

	public JSONArray addPriceQalityRange(PriceQalityRange priceQalityRange);

	public JSONArray updatePriceQalityRange(PriceQalityRange priceQalityRange);

	public JSONObject getPriceQalityRangeList(Map<String, Object> map);

	public JSONObject delPriceQalityRange(Map<String, Object> map);

	public JSONObject getPriceQalityRangeById(Map<String, Object> map);

	public JSONArray addPriceQalityRanges(JSONArray jsonArray,Long diancxxb_id,Long userid);

	public JSONArray getPriceQalityRangeByComponentId(Map<String, Object> map);
	
	

}
