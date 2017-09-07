package com.zhiren.fuelmis.dc.service.impl.yuebgl.yuebwh;

import com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh.DuibbmdjDao;
import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IDuibbmdjService;
import com.zhiren.fuelmis.dc.utils.Sequence;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class  DuibbmdjServiceImpl implements IDuibbmdjService {
	@Autowired
	private DuibbmdjDao  xianggwhDao;
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> getMeiHy(Map<String,Object> map) {
		List<Map<String, Object>> haoy = xianggwhDao.getMeihy(map);

		return haoy;
	}

	@Override
	public int updateMeihy(List<Map<String, Object>> list) {
		int ret = -1;
		try {
			for (Map<String,Object> map:list) {
				if(map.get("ID")!=null){
					ret = xianggwhDao.updateMeihy(map);
				}else{
					String id = Sequence.nextId();
					map.put("ID", id);
					ret = xianggwhDao.insertMeihy(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}
	@Override
	public int insertHuayd(String riq) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int ret = -1;
		try {
			String id = Sequence.nextId();
			map.put("id", id);
			map.put("DANJLX", "入炉化验");
			// 产生化验编码
			Integer c = xianggwhDao.getHaoyCount(riq);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
			String nowTime = df.format(new Date());// new Date()为获取当前系统时间
			String huaybm = nowTime + "10" + (c + 1);
			map.put("HUAYBM", huaybm);
			ret = xianggwhDao.insertHuayd(map);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}

	@Override
	public List<Map<String, Object>> getMeiHySiS(String riq) {
		return xianggwhDao.getMeihysis(riq);
	}



	@Override@Transactional
	public int delMeiHy(List<Object> ids) {
		int ret = -1;
		try {
			for (Object id:ids) {
				ret = xianggwhDao.delMeiHy(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateHuaybm(JSONArray array) {

		try {
			int n = array.size();
			for (int i = 0; i < n; i++) {
				JSONObject meihaoy = array.getJSONObject(i);
				Map<String, Object> map = JSONObject.fromObject(meihaoy);
				System.out.println(map.get("HUAYD_ID"));
				if (map.get("HUAYD_ID").toString().equals("0")){ 
					map.put("SHENHZT", "0");
				} else {
					map.put("SHENHZT", "1");
				}
				xianggwhDao.updateMeihy(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Map<String, Object>> getMeihyAll(Map<String, Object> map) {
		 List<Map<String , Object>> list = xianggwhDao.getMeihyAll(map);
			JSONArray result=JSONArray.fromObject(list);
			return result;
	}

	@Override
	@Transactional
	public void MeihyAdd(JSONArray jsonArray) {
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			Map<String, Object> map = jsonObject.fromObject(jsonObject);
			String sql="delete from RL_RUL_MEIHYB where id = "+map.get("ID")+" and RULRQ ='"+map.get("RIQ")+"'";
			jdbcTemplate.update(sql);
			xianggwhDao.MeihyAdd(map);
		}
		
	}

	@Override
	public void DelMeihy(int id) {
		xianggwhDao.DelMeihy(id);
	}
}
