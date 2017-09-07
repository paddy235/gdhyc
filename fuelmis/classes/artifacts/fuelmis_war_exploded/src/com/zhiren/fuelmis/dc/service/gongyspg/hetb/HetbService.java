package com.zhiren.fuelmis.dc.service.gongyspg.hetb;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.gongyspg.hetb.Hetb;

public interface HetbService {

	public JSONArray addHetb(Hetb hetb,String star,String qnet_ar);

	public JSONArray updateHetb(Hetb hetb,String star,String qnet_ar);

	public JSONObject getHetbList(Map<String,Object> map,List zhiblist);

	public JSONArray delHetb(Map<String, Object> map);

	public JSONObject getHetbById(Map<String, Object> map);

    void saveHetb(Map<String, Object> het);
}
