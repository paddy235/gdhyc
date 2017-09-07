package com.zhiren.fuelmis.dc.service.impl.yansgl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.dao.yansgl.ShulhjblDao;
import com.zhiren.fuelmis.dc.dao.yansgl.WailyrlDao;
import com.zhiren.fuelmis.dc.service.yansgl.ShulhjblService;
import com.zhiren.fuelmis.dc.service.yansgl.WailyrlService;

public class ShulhjblServiceImpl implements ShulhjblService{

	@Resource
	private ShulhjblDao shulhjblDao;
	
	
	@Override
	public JSONArray getHenjblAll(Map<String, Object> map) {
		List<Map<String, Object>> list = shulhjblDao.getHenjblAll(map);
		JSONArray result = JSONArray.fromObject(list);
		return result;
	}
}
