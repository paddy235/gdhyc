package com.zhiren.fuelmis.dc.service.impl.gongyspg.gongmjhzl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.gongyspg.gongmjhzl.GongmjhzlDao;
import com.zhiren.fuelmis.dc.service.gongyspg.gongmjhzl.GongmjhzlService;

@Service("gongmjhzlService")
public class GongmjhzlServiceImpl implements GongmjhzlService{

	@Autowired
	private GongmjhzlDao gongmjhzlDao;
	@Override
	public JSONObject getRigmjhList(Map<String, Object> map) {
		List<Map<String,Object>> list = gongmjhzlDao.getRigmjhList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{dataMap.get("ID"),dataMap.get("MINGC"),dataMap.get("RIQ"),
					dataMap.get("JIHML")};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

}
