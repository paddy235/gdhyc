package com.zhiren.fuelmis.dc.service.hetgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceComponent;

public interface PriceComponetService {

	public JSONArray addPriceComponet(PriceComponent priceComponent);

	public JSONArray updatePriceComponet(PriceComponent priceComponent);

	public JSONObject getPriceComponetList(Map<String, Object> map);

	public JSONObject delPriceComponet(Map<String, Object> map);

	public JSONObject getPriceComponetById(Map<String, Object> map);

}
