package com.zhiren.fuelmis.dc.service.impl.xitgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.xitgl.YunsdwDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Yunsdw;
import com.zhiren.fuelmis.dc.service.xitgl.IYunsdwService;

/** 
 * @author 陈宝露
 */
@Service
public class YunsdwServiceImpl implements IYunsdwService {
	
	@Autowired
	private YunsdwDao yunsdwDao;

	@Override
	public JSONObject getAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Yunsdw> list = yunsdwDao.getAll(map);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i=0;i<list.size();i++){
			Yunsdw yunsdw = list.get(i);
			objs[i] = new Object[]{yunsdw.getId(),yunsdw.getMingc(),yunsdw.getQuanc(),yunsdw.getDanwdz(),
					yunsdw.getYouzbm(),yunsdw.getShuih(),yunsdw.getFaddbr(),yunsdw.getWeitdlr(),
					yunsdw.getKaihyh(),yunsdw.getZhangh(),yunsdw.getDianh(),yunsdw.getChuanz(),yunsdw.getBeiz()};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONArray insertYunsdw(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = yunsdwDao.insertYunsdw(map);
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
		List<Yunsdw> list = yunsdwDao.getOne(map);
		JSONArray jsonArray = new JSONArray();
		if(list!=null){
			if(list.size()>0){
				Yunsdw yunsdw = list.get(0);
				jsonArray.add(yunsdw);
			}
		}
		return jsonArray;
	}

	@Override
	public JSONArray updateYunsdw(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = yunsdwDao.updateYunsdw(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

}
