package com.zhiren.fuelmis.dc.service.hetgl;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceItem;

public interface PriceItemService {

	public JSONArray addPriceItem(PriceItem priceItem);

	public JSONArray updatePriceItem(PriceItem priceItem);

	public JSONObject getPriceItemList(Map<String, Object> map);

	public JSONObject delPriceItem(Map<String, Object> map);

	public JSONObject getPriceItemById(Map<String, Object> map);

}
