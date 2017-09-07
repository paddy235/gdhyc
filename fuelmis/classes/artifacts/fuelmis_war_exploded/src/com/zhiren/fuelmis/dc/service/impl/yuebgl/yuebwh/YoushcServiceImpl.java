package com.zhiren.fuelmis.dc.service.impl.yuebgl.yuebwh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh.YoushcDao;
import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IYoushcService;

/** 
 * @author 陈宝露
 */
@Service
public class YoushcServiceImpl implements IYoushcService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private YoushcDao youshcDao;

	@Override
	public JSONObject getAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		List<Map<String, Object>> list = youshcDao.getAll(map);
		jsonMap.put("data", list);

		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONArray createData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray saveData(Map<String, Object> map) {
		return null;
	}

	@Override
	public JSONArray delData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray check(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
