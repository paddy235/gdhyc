package com.zhiren.fuelmis.dc.service.impl.hetgl;

import com.zhiren.fuelmis.dc.dao.hetgl.CaigddbDao;
import com.zhiren.fuelmis.dc.entity.hetgl.Hetkhzb;
import com.zhiren.fuelmis.dc.service.hetgl.HtKaohzbService;
import com.zhiren.fuelmis.dc.utils.Sequence;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("KaohzbService")
public class HtKaohzbServiceImpl implements HtKaohzbService {

	@Autowired
	private CaigddbDao caigddbDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public JSONArray addKaohzb(JSONArray kaohzbinfo) {
		int result = 0;
		try{
			for (int i = 0; i < kaohzbinfo.size(); i++) {
				Hetkhzb hetkhzb = (Hetkhzb) JSONObject.toBean((JSONObject)kaohzbinfo.get(i),Hetkhzb.class);
//				hetkhzb.setCaigddb_id(id);
				Long hetkhzbId = Long.parseLong(Sequence.nextId());
				hetkhzb.setId(hetkhzbId);
				caigddbDao.addKaohzb(hetkhzb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public void updateKaohzb(JSONArray kaohzbinfo,String caigddb_id) {
                List<Map<String,Object>> list=JSONArray.fromObject(kaohzbinfo);
        for (Map<String,Object> map:list) {
            if(map.get("ID")==null){
               map.put("ID",Sequence.nextId());
               map.put("caigddb_id", caigddb_id);
                caigddbDao.insertKaohzb(map);
            }else{
                caigddbDao.updateKaohzb(map);
            }
        }
	}

	@Override
	public JSONObject delKaohzb(Map<String, Object> map) {
		int result = 1;
		try{
//			jdbcTemplate.execute("delete from RL_HT_KAOHZBB where id = "+map.get("id"));
			result=caigddbDao.delKaohzbbyid(map);
		}catch(Exception e){
			result = 0;
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}
}
