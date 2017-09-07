package com.zhiren.fuelmis.dc.service.xfire.impl;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhiren.fuelmis.dc.dao.cnfy.FeiyxmsqDao;
import com.zhiren.fuelmis.dc.dao.cnfy.NiandYussqDao;
import com.zhiren.fuelmis.dc.dao.cnfy.YuedYussqDao;
import com.zhiren.fuelmis.dc.service.xfire.dao.CnfyReback;

public class CnfyRebackImpl implements CnfyReback{
	@Autowired
	private YuedYussqDao yuedYussqDao ;
	@Autowired
	private NiandYussqDao niandYussqDao;
	@Autowired
	private FeiyxmsqDao feiyxmsqDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String yuedyshuit(String diancid, String riq){
		Map map = new HashMap();
		map.put("diancid", diancid);
		map.put("riq", riq);
		map.put("zhuangt", 0);
		yuedYussqDao.updateState(map);
		return "1";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String yuedys(String jsondata){
		try {
			JSONObject obj = JSONObject.fromObject(jsondata);
			JSONArray array = obj.getJSONArray("yuedys");
			for (int i = 0; i < array.size(); i++) {
				JSONObject object = array.getJSONObject(i);
				long id = object.getLong("yuedys_id");
				long yused = object.getLong("yused");
				long changnfyxm_id = object.getLong("changnfyxm_id");
				Map map = new HashMap();
				map.put("changnfyxm_id", changnfyxm_id);
				map.put("yused", yused);
				map.put("id",id );
				yuedYussqDao.updateYuedys(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "1";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String niandyshuit( String diancid, String nianf){
		Map map = new HashMap();
		map.put("diancid", diancid);
		map.put("nianf", nianf.replace("年", ""));
		map.put("zhuangt", 0);
		niandYussqDao.updateState(map); 
		return "1";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String niandys(String jsondata){
		try {
			JSONObject obj = JSONObject.fromObject(jsondata);
			JSONArray array = obj.getJSONArray("niandys");
			for (int i = 0; i < array.size(); i++) {
				JSONObject object = array.getJSONObject(i);
				long id = object.getLong("niandys_id");
				long yused = object.getLong("yused");
				long changnfyxm_id = object.getLong("changnfyxm_id");
				Map map = new HashMap();
				map.put("changnfyxm_id", changnfyxm_id);
				map.put("yused", yused);
				map.put("id",id );
				niandYussqDao.updateNiandys(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "1";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String feiyxmsp(String changnfyid, String feiymc){
		Map map = new HashMap();
		map.put("feiymc", feiymc);
		map.put("id", changnfyid);
		feiyxmsqDao.updateFeiyxmsq(map);
		return "1";
	}
}
