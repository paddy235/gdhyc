package com.zhiren.fuelmis.dc.service.impl.gongyspg.heb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.utils.Sequence;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.gongyspg.hetbdao.HetbDao;
import com.zhiren.fuelmis.dc.entity.gongyspg.hetb.Hetb;
import com.zhiren.fuelmis.dc.service.gongyspg.hetb.HetbService;
import com.zhiren.fuelmis.dc.service.impl.common.SaveService;
@Service("hetbService")
public class HetbServiceImpl extends SaveService implements HetbService {

	@Autowired
	private HetbDao hetbDao;
	
	@Override
	public JSONArray addHetb(Hetb hetb,String star,String qnet_ar) {
		int result = 0;
		try{
			long hetb_id = hetbDao.getId();
			hetb.setId(hetb_id);
			result = hetbDao.addHetb(hetb);
			Map map = new HashMap();
			map.put("hetb_id",hetb_id );
			map.put("zhibdm", "STAR");
			map.put("zhibz",star );
			hetbDao.addhetzb(map);
			Map map1 = new HashMap();
			map1.put("hetb_id",hetb_id );
			map1.put("zhibdm", "QNET_AR");
			map1.put("zhibz",qnet_ar );
			hetbDao.addhetzb(map1);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray updateHetb(Hetb hetb,String star,String qnet_ar) {
		int result = 0;
		try{
			Map map = new HashMap();
			map.put("hetb_id",hetb.getId().toString());
			map.put("zhibdm", "STAR");
			map.put("zhibz",star );
			hetbDao.updateHetzhib(map);
			Map map1 = new HashMap();
			map1.put("hetb_id",hetb.getId().toString() );
			map1.put("zhibdm", "QNET_AR");
			map1.put("zhibz",qnet_ar );
			hetbDao.updateHetzhib(map1);
			result = hetbDao.updateHetb(hetb);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getHetbList(Map<String, Object> map,List zhiblist) {
		List<Map<String,Object>> list = hetbDao.getGyshtList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[] { dataMap.get("ID"), dataMap.get("GONGYSB_ID"),
					dataMap.get("KAISRQ"), dataMap.get("JIESRQ"),
					dataMap.get("STAR"), dataMap.get("QNET_AR"),
					dataMap.get("HETL"), dataMap.get("PINGFFAB_ID"),
					dataMap.get("BEIZ"), dataMap.get("LURRY"),
					dataMap.get("LURSJ") };
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONArray delHetb(Map<String, Object> map) {
		int result = 0;
		try{
			result = hetbDao.delHetb(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getHetbById(Map<String, Object> map) {
		List<Hetb> list = hetbDao.getHetbById(map);
		List zhib_list = hetbDao.getHetZhibByHetId(map);
		Hetb hetb = null;
		if(list.size()>0){
			hetb = list.get(0);
		}
		JSONObject json = JSONObject.fromObject(hetb);
		for(int i=0;i<zhib_list.size();i++){
			Map zhib_map = (Map) zhib_list.get(i);
			json.put(zhib_map.get("ZHIBDM").toString().toLowerCase(), zhib_map.get("ZHIBZ").toString());
		}
		return json;
	}

    @Override
    public void saveHetb(Map<String, Object> het) {
	    try{
            if(het.get("ID")==null){
                het.put("ID", Sequence.nextId());
            }
            this.saveData(het,"rl_gys_hetb","ID");
            List zhilList=(List) het.get("zhilList");
            for (Object zhil:zhilList) {
                Map map=(Map)zhil;
                map.put("HETB_ID",het.get("ID"));
                this.saveData(map,"rl_gys_hetzlb","HETB_ID,ZHIBDM");
            }
        }catch (Exception e){
	        e.printStackTrace();
        }

    }

}
