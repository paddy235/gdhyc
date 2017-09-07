package com.zhiren.fuelmis.dc.service.impl.rucslys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zhiren.fuelmis.dc.dao.rucslys.ChepbtmpDao;
import com.zhiren.fuelmis.dc.entity.rucslys.Chepbtmp;
import com.zhiren.fuelmis.dc.service.rucslys.ChepbtmpService;
import com.zhiren.fuelmis.dc.utils.Sequence;

@Service("chepbtmpservice")
public class ChepbtmpServiceImpl implements ChepbtmpService {

	@Autowired
	private ChepbtmpDao chepbtmpDao;
	
	@Override
	public JSONObject getChepbtmpList(Map<String, Object> map) {		
		List<Map<String,Object>> list = chepbtmpDao.getChepbtmpList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{dataMap.get("ID"),dataMap.get("DIANCXXB_ID"),dataMap.get("PIAOJH"),
					dataMap.get("GONGYSMC"),dataMap.get("MEIKDWMC"),dataMap.get("PINZ"),dataMap.get("LURSJ")};
		}
		jsonMap.put("data", objs);						
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	
	@Override
	@Transactional
	public JSONObject updateData(JSONArray json,String caozry,String caozsj) {
		int result = 1;			
		for (int i=0;i<json.size();i++) {
			Chepbtmp chepbtmp = (Chepbtmp) JSONObject.toBean((JSONObject)json.get(i),Chepbtmp.class);
			Long chepbtmpId = chepbtmp.getId();
			chepbtmp.setId(Long.parseLong(Sequence.nextId()));
			chepbtmp.setLsbid(Long.parseLong(Sequence.nextId()));
			chepbtmp.setLury(caozry);
			chepbtmp.setLursj(caozsj);
			try {
				String yunsfs = chepbtmp.getYunsfs();
				if(("更新").equals(chepbtmp.getCaozlx())){
					//如果是更新
					chepbtmpDao.updateChepb(chepbtmp);
					chepbtmpDao.addChepbls(chepbtmp);
					/***
					 * 暂时缺少历史数据录入
					 */
					chepbtmp.setLsbid(Long.parseLong(Sequence.nextId()));	
					chepbtmpDao.updateKoubzxxks(chepbtmp);
					chepbtmpDao.addKoubzxxksls(chepbtmp);
					chepbtmp.setLsbid(Long.parseLong(Sequence.nextId()));	
					chepbtmpDao.updateKoubzxxkz(chepbtmp);
					chepbtmpDao.addKoubzxxkzls(chepbtmp);
					chepbtmp.setLsbid(Long.parseLong(Sequence.nextId()));	
					chepbtmpDao.updateKoubzxxkd(chepbtmp);					
					chepbtmpDao.addKoubzxxkdls(chepbtmp);
					if (yunsfs !=null && ("公路").equals(yunsfs)) {
						chepbtmpDao.updateChepbqc(chepbtmp);
						chepbtmp.setLsbid(Long.parseLong(Sequence.nextId()));
						chepbtmpDao.addChepbls(chepbtmp);
						//添加车批表历史
						chepbtmpDao.addChepbqcls(chepbtmp);
					}
				}else{
					//新增车批表数据
					chepbtmpDao.addChepb(chepbtmp);
					//新增车批历史表数据
					chepbtmpDao.addChepbls(chepbtmp);
					
					if (yunsfs !=null && ("公路").equals(yunsfs)) {
						//如果运输方式是公路则更新车皮汽车记录表
						chepbtmpDao.addChepbqc(chepbtmp);
						//新增汽车历史记录表
						chepbtmp.setLsbid(Long.parseLong(Sequence.nextId()));
						chepbtmpDao.addChepbls(chepbtmp);
						//添加车批表历史
						chepbtmpDao.addChepbqcls(chepbtmp);
					}
					//更新扣补重信息
					chepbtmp.setLsbid(Long.parseLong(Sequence.nextId()));
					chepbtmpDao.addKoubzxxks(chepbtmp);
					chepbtmpDao.addKoubzxxksls(chepbtmp);
					chepbtmp.setLsbid(Long.parseLong(Sequence.nextId()));
					chepbtmpDao.addKoubzxxkz(chepbtmp);
					chepbtmpDao.addKoubzxxkzls(chepbtmp);
					chepbtmp.setLsbid(Long.parseLong(Sequence.nextId()));
					chepbtmpDao.addKoubzxxkd(chepbtmp);
					chepbtmpDao.addKoubzxxkdls(chepbtmp);
				}
				//chepbtmpId
				chepbtmp.setId(chepbtmpId);
				chepbtmpDao.updateChepbtmp(chepbtmp);

			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				result = 0;
			}			
		}
		
		JSONObject resultjson = new JSONObject();
		resultjson.put("result", result);
		return resultjson;
	}


	@Override
	public JSONArray searchChepbtmpList(Map<String, Object> map) {
		List<Chepbtmp> list = chepbtmpDao.searchChepbtmpList(map);
		JSONArray result =  JSONArray.fromObject(list);
		return result;
	}

}
