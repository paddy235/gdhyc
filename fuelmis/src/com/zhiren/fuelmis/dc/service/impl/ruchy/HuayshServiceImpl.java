package com.zhiren.fuelmis.dc.service.impl.ruchy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zhiren.fuelmis.dc.dao.ruchy.HuayshDao;
import com.zhiren.fuelmis.dc.service.ruchy.IHuayshService;

/** 
 * @author 陈宝露
 */
@Service
public class HuayshServiceImpl implements IHuayshService {
	
	@Autowired
	private HuayshDao huayshDao;

	@Override
	public JSONObject getTableData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = huayshDao.getTableData(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			Map<String,Object> datas = list.get(i);
			objs[i] = new Object[]{datas.get("ID"),datas.get("HUAYSJ"),datas.get("HUAYBH"),datas.get("QNET_AR"),
					datas.get("AAR"),datas.get("AD"),datas.get("VDAF"),datas.get("MT"),
					datas.get("STAD"),datas.get("AAD"),datas.get("MAD"),datas.get("MF"),
					datas.get("QBAD"),datas.get("HAD"),datas.get("VAD"),datas.get("FCAD"),datas.get("STD"),
					datas.get("QGRAD"),datas.get("HDAF"),datas.get("QGRAD_DAF"),datas.get("SDAF"),datas.get("QGRD"),
					datas.get("T1"),datas.get("T2"),datas.get("T3"),datas.get("T4"),datas.get("HUAYY"),
					datas.get("LURY"),datas.get("BEIZ"),datas.get("HUAYLB")};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		
		return result;
	}

	@Override
	public JSONArray updateZT(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = huayshDao.updateZT(map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(System.out);
		}
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public List<Map<String, Object>> getJincpcList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return huayshDao.getJincpcList(map);
	}

	@Override
	public JSONObject getTableData2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> list = huayshDao.getTableData2(map);
			Object[][] objs = new Object[list.size()][];
			for(int i = 0;i<list.size();i++){
				Map<String,Object> datas = list.get(i);
				objs[i] = new Object[]{datas.get("ID"),datas.get("ZHILID"),datas.get("MEIKDW"),datas.get("FAZ"),
						datas.get("PINZ"),datas.get("HUAYSJ"),datas.get("HUAYBH"),datas.get("CHES"),datas.get("SHUL"),
						datas.get("QNET_AR"),datas.get("AAR"),datas.get("AD"),datas.get("VDAF"),datas.get("MT"),
						datas.get("STAD"),datas.get("AAD"),datas.get("MAD"),datas.get("MF"),
						datas.get("QBAD"),datas.get("HAD"),datas.get("VAD"),datas.get("FCAD"),datas.get("STD"),
						datas.get("QGRAD"),datas.get("HDAF"),datas.get("QGRAD_DAF"),datas.get("SDAF"),datas.get("QGRD"),
						datas.get("T1"),datas.get("T2"),datas.get("T3"),datas.get("T4"),datas.get("HUAYY"),
						datas.get("BEIZ")};
			}
			jsonMap.put("data", objs);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(System.out);
		}
		
		JSONObject result = JSONObject.fromObject(jsonMap);
		
		return result;
	}

	@Override
	@Transactional
	public JSONArray updateZT2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			huayshDao.updateZT2(map);
			result = 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(System.out);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}
}
