package com.zhiren.fuelmis.dc.service.impl.ruchy;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.ruchy.RuchyDao;
import com.zhiren.fuelmis.dc.dao.ruchy.ShenhDao;
import com.zhiren.fuelmis.dc.service.ruchy.IShenhService;
import com.zhiren.fuelmis.dc.utils.Sequence;
import com.zhiren.fuelmis.dc.utils.formular.Huayz;

/** 
 * @author 刘志宇
 */
@Service
public class ShenhServiceImpl implements IShenhService {
	
	@Autowired
	private ShenhDao shenhDao;
	@Autowired
	private RuchyDao ruchyDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void shenh(String huaydbid,String zhuangt,String xiugry) {
		shenhDao.shenh(huaydbid,zhuangt);
		String huaydlsbid=Sequence.nextId();//化验单历史表id
		List<Map<String, Object>> list=shenhDao.getHuaysjById(huaydbid);
		list.get(0).put("huaydlsbid", huaydlsbid);
		list.get(0).put("xiugry", xiugry);
		
		//shenhDao.shenhLs(list.get(0));
	}

	@Override
	public void huit(String id,String zhuangt) {
		Integer z=Integer.parseInt(zhuangt)-1;//回退状态减1
		if(z==-1){
			shenhDao.deleteHuayd(id);
			//删除gx_chep_huayd表中的id
//			shenhDao.deleteGX(id);
		}else{
			shenhDao.huit(id,z.toString());
		}
	}



	@Override
	public List<Map<String, Object>> getHuayd(Map<String, Object> map) {
		List<Map<String, Object>> list = shenhDao.getHuayd(map);
		//java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> m = list.get(i);
			//m.put("QNET_AR_K", Double.parseDouble(df.format(m.get("QNET_AR_K").toString())));
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> getHuaydz(Map<String, Object> map) {
		List<Map<String, Object>> list = shenhDao.getHuaydz(map);
		return list;
	}


	@Override
	public Integer getCount(Map<String, Object> conditionMap) {
		return shenhDao.getCount(conditionMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(List<Map<String, Object>> a,String LURY) {
		for (Map<String, Object> map : a) {
			Huayz.calculateHuayz(map);
				if (map.get("HUAYD_ID") == null||map.get("HUAYD_ID").equals("")) {
					String id = Sequence.nextId();
					map.put("HUAYD_ID", id);
					map.put("dcId", 515);
					map.put("LURY",LURY);
					shenhDao.insert(map);
				} else {
					map.put("LURY",LURY);
					shenhDao.update(map);
				}
			}
		}

	@Override
	public void huaydLog(Map<String, Object> map) {
		shenhDao.huaydLog(map);
	}

	@Override
	public List<Map<String, Object>> getJiesxxList(String huaybm) {
		return shenhDao.getJiesxxList(huaybm);
	}


}
