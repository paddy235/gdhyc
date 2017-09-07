package com.zhiren.fuelmis.dc.service.impl.cnfy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.cnfy.ZafjswhDao;
import com.zhiren.fuelmis.dc.service.cnfy.IZafjswhService;
@Service
public class ZafjswhService implements IZafjswhService {
	
	@Autowired
	private ZafjswhDao zafjswhDao;

	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Override
	public JSONObject getZafjsdata(String nianf ) {
		List list = zafjswhDao.getZafjsdata(nianf);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{hashMap.get("ID"),hashMap.get("ROWNUM"),hashMap.get("BIANH"),
					hashMap.get("ZHIDRQ"),hashMap.get("JINGBR"),hashMap.get("ZONGJE"),hashMap.get("ZHUANGT")}; 
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}
	@Override
	public int saveZafDjb(Map<String, Object> map) {
		int  result;
		try{
			result = zafjswhDao.saveZafDjb(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int delZaffydjbById(String id) {
		int  result;
		try{
			result = zafjswhDao.delZaffydjbById(id);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int delZaffybxdById(String id) {
		int  result;
		try{
			result = zafjswhDao.delZaffybxdById(id);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int saveZaffybxd(Map<String, Object> map) {
		int  result;
		try{
			result = zafjswhDao.saveZaffybxd(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int updateZafDjb(Map<String, Object> map) {
		int  result;
		try{
			result = zafjswhDao.updateZafDjb(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int updateZaffybxd(Map<String, Object> map) {
		int  result;
		try{
			result = zafjswhDao.updateZaffybxd(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
}
