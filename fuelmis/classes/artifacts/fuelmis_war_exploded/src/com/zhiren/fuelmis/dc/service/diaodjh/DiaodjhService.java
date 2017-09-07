package com.zhiren.fuelmis.dc.service.diaodjh;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.diaodjh.Diaodjh;

public interface DiaodjhService {

	public JSONArray addDiaodjh(Diaodjh diaodjh);

	public JSONObject getDiaodjhList(Map<String, Object> map);

	public String getBianm(String riq);

	public JSONArray delDiaodjh(Map<String, Object> map);

	public JSONObject getDiaodById(Map<String, Object> map);

	public JSONArray updateDiaodjh(Diaodjh diaodjh);

	public JSONObject getDiaodjhinfoList(String strdate, String enddate,
			String diancid);

}
