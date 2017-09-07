package com.zhiren.fuelmis.dc.service.hetgl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.hetgl.Caigddb;

public interface CaigddbService {

	public JSONArray addCaigddb(Caigddb caigddb, JSONArray subs,Long diancxxb_id);

	public JSONArray updateCaigddb(Caigddb caigddb,JSONArray subs);

	public JSONObject getCaigddbList(Map<String,Object> map);

	public JSONObject getCaigddbinfoList(String strdate, String enddate,
										 String diancid);

	public JSONArray delCaigddb(Map<String, Object> map);

	public JSONArray delCaigddbsub(Map<String, Object> map);

	public JSONArray delCaigddbyfsub(Map<String, Object> map);

	public JSONObject getCaigddbById(Map<String, Object> map);

	public JSONArray getCaigddbsubById(Map<String, Object> map);

	public JSONArray getCaigddbyfsubById(Map<String, Object> map);

	public JSONObject getjihkjById(String string);

	public JSONObject getBianh(Map<String, Object> map);

	public JSONObject beforedelCaigddb(Map<String, Object> map);


	public JSONArray getJijfsByCaigddbId(Map<String, Object> map);

	public JSONArray getKaohzb_QnetByCaigddbId(Map<String, Object> map);

	public JSONArray getKaohzb_StarByCaigddbId(Map<String, Object> map);

    /**
     * 根据采购订单子表id查询计价方式
     * @param caigddb_sub_id
     * @return
     */
    List<Map<String,Object>> getJijfsByCaigddbSubId(String caigddb_sub_id);
}
