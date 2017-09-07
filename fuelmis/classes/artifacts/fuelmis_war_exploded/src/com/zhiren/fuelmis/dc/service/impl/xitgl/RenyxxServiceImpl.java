package com.zhiren.fuelmis.dc.service.impl.xitgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zhiren.fuelmis.dc.dao.xitgl.RenyxxDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.xitgl.IRenyxxService;
import com.zhiren.fuelmis.dc.utils.MD5Util;

/**
 * @author 陈宝露
 */
@Service
public class RenyxxServiceImpl implements IRenyxxService {

	@Autowired
	private RenyxxDao renyxxDao;

	@Override
	public Renyxx getRenyxx(String userName, String password) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mingc", userName);
		List<Renyxx> list = renyxxDao.getRenyxx(map);
		if (list.size() != 0) {
			Renyxx renyxx = list.get(0);
			boolean verify = MD5Util.verify(password, renyxx.getMim());
			if (verify) {
				return renyxx;
			}
		}
		return null;
	}

	@Override
	public JSONObject getAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Renyxx> list = renyxxDao.getAll(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			Renyxx renyxx = list.get(i);
			objs[i] = new Object[]{renyxx.getId(),renyxx.getMingc(),renyxx.getQuanc(),renyxx.getXingb(),
					renyxx.getBum(),renyxx.getZhiw(),renyxx.getZhuangt()==1?"是":"否",renyxx.getYiddh(),renyxx.getGuddh(),
					renyxx.getChuanz(),renyxx.getYouzbm(),renyxx.getEmail(),renyxx.getLianxdz()};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		
		return result;
	}

	@Override
	public JSONArray insertRenyxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = renyxxDao.insertRenyxx(map);
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
		List<Renyxx> list = renyxxDao.getOne(map);
		JSONArray jsonArray = new JSONArray();
		if(list!=null){
			if(list.size()>0){
				Renyxx renyxx = list.get(0);
				renyxx.setXingb(renyxx.getXingb().equals("男")?"1":"2");
				jsonArray.add(renyxx);
			}
		}
		return jsonArray;
	}

	@Override
	public JSONArray updateRenyxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Renyxx> list = renyxxDao.getOne(map);
		if(list!=null&&list.size()>0){
			Renyxx renyxx = list.get(0);
			if(map.get("mim").equals(renyxx.getMim())){
				map.remove("mim");
			}else{
				String mim = map.get("mim").toString();
				map.remove("mim");
				map.put("mim", MD5Util.generate(mim));
			}
		}
		int result = 0;
		try{
			result = renyxxDao.updateRenyxx(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray deleteRenyxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = renyxxDao.deleteRenyxx(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	@Transactional
	public JSONArray updateQuanx(Long id,String[] ziy) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("renyxxb_id", id);
		map.put("ziy",ziy);
		
		int result = 0;
		try{
			renyxxDao.deleteQuanx(map);
			renyxxDao.insertQuanx(map);
			result = 1;
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray getQuanx(Long renyxxb_id) {
		// TODO Auto-generated method stub
		List<String> list = renyxxDao.getQuanx(renyxxb_id);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(list);
		return jsonArray;
	}
}
