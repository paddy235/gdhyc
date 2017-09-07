package com.zhiren.fuelmis.dc.service.impl.xitgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.xitgl.LicDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Lic;
import com.zhiren.fuelmis.dc.service.xitgl.ILicService;

/** 
 * @author 陈宝露
 */
@Service
public class LicServiceImpl implements ILicService {
	
	@Autowired
	private LicDao licDao;

	@Override
	public JSONObject getAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Lic> list = licDao.getAll(map);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i=0;i<list.size();i++){
			Lic lic = list.get(i);
			objs[i] = new Object[]{lic.getId(),lic.getFaz_id(),lic.getDaoz_id(),lic.getLiclxb_id(),lic.getZhi(),
					lic.getMeikxxb_id(),lic.getBeiz()};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONArray insertLic(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = licDao.insertLic(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray getOne(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = licDao.getOne(map);
		JSONArray jsonArray = new JSONArray();
		if(list!=null){
			if(list.size()>0){
				Map<String,Object> rtnMap = list.get(0);
				jsonArray.add(rtnMap);
			}
		}
		return jsonArray;
	}

	@Override
	public JSONArray updateLic(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = licDao.updateLic(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray delLic(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = licDao.delLic(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}
}
