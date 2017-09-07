package com.zhiren.fuelmis.dc.service.hetgl;

import java.util.Map;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.hetgl.PriceScheme;

public interface PriceSchemeService {


    public void updatePriceScheme(JSONArray priceScheme, Renyxx user);


    public JSONObject delPriceScheme(Map<String, Object> map);


    public PriceScheme getPriceSchemeByCaigddId(Map<String, Object> map);

}

