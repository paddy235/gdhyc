package com.zhiren.fuelmis.dc.service.impl.yuebgl.yuebwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh.ZhibqkDao;
import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IZhibqkService;
import com.zhiren.fuelmis.dc.utils.Sequence;

/**
 * @author weiw
 */
@Service
public class ZhibqkServiceImpl implements IZhibqkService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ZhibqkDao zhibqkDao;

	@Override
	public JSONObject getAll(Map<String, Object> map) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		List<Map<String, Object>> allNamesList=zhibqkDao.getAllNames(map);
		String sqls = "select id,  ";
		//从JIHZBDYB表中查询出niandjh_zhib表的列名和计算公式
		for(int i = 0; i < allNamesList.size();i ++){
			Map<String,Object> zidmMap = allNamesList.get(i);//字段和名称map
			if(null != zidmMap.get("GONGS")&&!"".equals(zidmMap.get("GONGS"))&&!"0".equals(zidmMap.get("GONGS"))){
				sqls += zidmMap.get("GONGS") +" AS "+zidmMap.get("ZIDM") +","; 
			}else{
				sqls += zidmMap.get("ZIDM")+",";
			}
		}
		
		sqls = sqls.substring(0, sqls.length()-1);//去掉最后一个逗号
		map.put("sqls", sqls);
		List<Map<String, Object>> names_left = zhibqkDao.getAllName_Left(map);
		List<Map<String, Object>> names_right = zhibqkDao.getAllName_Right(map);
		List<Map<String, Object>> values = zhibqkDao.getAllValue(map);
		for (int i = 0; i < names_left.size(); i++) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("zidm1", names_left.get(i).get("ZIDM"));
			maps.put("xiangm1", names_left.get(i).get("MINGC"));
			maps.put("danw1", names_left.get(i).get("DANW"));
			maps.put("zhi1",values != null && values.size() > 0 ? values.get(0).get(names_left.get(i).get("ZIDM")) : 0);
			maps.put("gongs1", names_left.get(i).get("GONGS"));
			try {
				maps.put("zidm2", names_right.get(i).get("ZIDM"));
				maps.put("xiangm2", names_right.get(i).get("MINGC"));
				maps.put("danw2", names_right.get(i).get("DANW"));
				maps.put("zhi2",values != null && values.size() > 0 ? values.get(0).get(names_right.get(i).get("ZIDM")) : 0);
				maps.put("gongs2", names_right.get(i).get("GONGS"));
			} catch (IndexOutOfBoundsException e) {
				maps.put("xiangm2", "");
				maps.put("danw2", "");
				maps.put("zhi2", "");
				maps.put("gongs2", "none");
			}
			if(i==0){
			maps.put("yuezbId", values != null && values.size() > 0 ? values.get(0).get("ID") : 0); 
			}
			list.add(maps);
		}

		jsonMap.put("data", list);

		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	/**
	 * 修改/插入指标
	 */
	@Override
	public void saveData(List<Map<String, Object>> listzhibqk, Map<String, Object> map) {
		/**
		 * 将填报项插入表中
		 */
		for (int i = 0; i < listzhibqk.size(); i++) {
			Map<String, Object> mapf = listzhibqk.get(i);
			map.put((String) mapf.get("zidm1"), mapf.get("zhi1"));
			map.put((String) mapf.get("zidm2"), mapf.get("zhi2"));
		}
		if (!"0".equals(map.get("yuezbId"))) {
			zhibqkDao.updateTbDate(map);
		} else {
			String id = Sequence.nextId();
			map.put("id", id);
			zhibqkDao.saveTbData(map);
		}
	}

	@Override
	public JSONArray delData(Map<String, Object> map) {
		JSONArray jsonArr=JSONArray.fromObject(zhibqkDao.delData(map));
		return jsonArr;
	}

	@Override
	public JSONArray update(Map<String, Object> map) {
		JSONArray jsonArr=JSONArray.fromObject(zhibqkDao.updateData(map));
		return jsonArr;
	}
	
	/**
	 * 累计功能
	 */
	@SuppressWarnings("unused")
	public void saveLeij(Map<String, Object> map){
		List<Map<String, Object>> allNamesList=zhibqkDao.getAllNames(map);
		String sqls = "select fenx,  ";
		for(int i = 0; i < allNamesList.size();i ++){
			Map<String,Object> zidmMap = allNamesList.get(i);//字段和名称map
			if(null != zidmMap.get("LEIJGS")&&!"".equals(zidmMap.get("LEIJGS"))&&!"0".equals(zidmMap.get("LEIJGS"))){
				sqls += zidmMap.get("LEIJGS") +" AS "+zidmMap.get("ZIDM") +","; 
			}
		}
		sqls = sqls.substring(0, sqls.length()-1);//去掉最后一个逗号
		Map<String, Object> leijMaps = new HashMap<String, Object>();
		leijMaps.put("leijsql", sqls);
		leijMaps.put("riq",map.get("riq"));
		leijMaps.put("dianc",map.get("dianc"));
		Long idmap;
		List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT ID FROM YUEZBB WHERE  fenx='累计' AND substr(RIQ,0,7) ='"+leijMaps.get("riq")+"'");
		
		List<Map<String,Object>> leijData=zhibqkDao.getLeijData(leijMaps);
		if (leijData.size()!=0) {
			leijMaps=(Map<String,Object>)leijData.get(0);
			if(list.size()!=0){
				leijMaps.put("riq",map.get("riq"));
				zhibqkDao.upLeijData(leijMaps);
			}else{
				String lsId = Sequence.nextId();
				leijMaps.put("riq",map.get("riq"));
				leijMaps.put("dianc",map.get("dianc"));
				leijMaps.put("id",lsId);
				zhibqkDao.inLeijData(leijMaps);
			}
		}
	}
}