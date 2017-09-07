package com.zhiren.fuelmis.dc.service.impl.gongyspg.rigmjh;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.gongyspg.rigmjh.RigmjhDao;
import com.zhiren.fuelmis.dc.service.gongyspg.rigmjh.RigmjhService;
import com.zhiren.fuelmis.dc.utils.Sequence;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("rigmjhService")
public class RigmjhServiceImpl implements RigmjhService{

	@Autowired
	private RigmjhDao rigmjhDao;

	@Override
	public JSONObject searchRigmjhList(Map<String, Object> map) {
		List<Map<String, Object>> list = rigmjhDao.searchRigmjhList(map);
		JSONArray listArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			listArray.add(list.get(i));
		}
		HashMap<String, JSONArray> resMap = new HashMap<String, JSONArray>();
		resMap.put("listArray", listArray);
		JSONObject result = JSONObject.fromObject(resMap);
		return result;
	}
	//保存
	@Override
	public int updateRigmjh(Map<String, Object> map) {
			int ret = rigmjhDao.updateRigmjh(map);
		return ret;
	}

	@Override
	public int insertRigmjh(Map<String, Object> map) {
			int ret = rigmjhDao.insertRigmjh(map);
			return ret;
	}
	//删除
	@Override
	public int delRigmjh(Long[] arr) {
		int ret = -1;
		try {
			ret = rigmjhDao.delRigmjh(arr);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}
	//发布
	@Override
	public int publishRigmjh(Long[] arr) {
		int ret = -1;
		try {
			ret = rigmjhDao.publishRigmjh(arr);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
		
	}

}
