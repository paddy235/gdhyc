package com.zhiren.fuelmis.dc.service.kucgl.rukgl;

import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Map;

/** 
 * @author Rain
 */
public interface IRanlcgrkService {
	
	JSONObject getChurkdList(Map<String, Object> map);
	
	JSONObject getChurkdList2(Map<String, Object> map);
	
	JSONObject getYansmx(Map<String, Object> map);
	
	JSONObject getYansmxMX(Map<String, Object> map);
	
	JSONArray ranlcgrk(String ids, Diancxx diancxx, int yewlx);
	
	JSONObject getRanlcgrk_WRK_MX(Map<String, Object> map);
	
	JSONObject getRanlcgrk_WRK_MX2(Map<String, Object> map);
	
	JSONArray saveRukd(String rukdbh, String kuczz, String huoz, String rukdList, int yewlx);
	
	JSONArray ruk(String rukdbh);
	
	JSONArray chex(String rukdbh);
	
	JSONObject getQitrk();
	
	JSONArray check(String rukdbh);
	
	JSONArray ranlhyrk(String huayd_id);
	
	JSONObject getCaigddList(Map<String, Object> map);
	
	JSONArray editCaigdd(String rukdbh, String caigdd_id, String yewlx);

}
