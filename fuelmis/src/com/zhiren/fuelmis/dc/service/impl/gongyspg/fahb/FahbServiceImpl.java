package com.zhiren.fuelmis.dc.service.impl.gongyspg.fahb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.service.impl.common.SaveService;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.gongyspg.fahb.FahbDao;
import com.zhiren.fuelmis.dc.entity.gongyspg.fahb.Fahb;
import com.zhiren.fuelmis.dc.service.gongyspg.fahb.FahbService;
import com.zhiren.fuelmis.dc.utils.Sequence;

@Service("fahbService")
public class FahbServiceImpl extends SaveService implements FahbService{

	@Autowired
	private FahbDao fahbDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
	@Override
	public JSONObject updateFahb(Map map) {
		int result = 1;
		try {
            String sql="SELECT * from RL_GYS_ZHIBDYB where leib=1";
//			Map<String,Object> map = new HashMap<String,Object>();
//			//List<Fahb> list = fahbDao.getFahbByDate(map);
//			map.put("date", date);
//			fahbDao.delFahbByDate(map);
//			List<Map<String,Object>> list = fahbDao.getFahbByDate(map);
//			for(int i = 0;i<list.size();i++){
//				HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
//				Fahb fahb = new Fahb();
//				fahb.setId(Long.parseLong(Sequence.nextId()));
//				fahb.setChes(Integer.parseInt(dataMap.get("CHES").toString()));
//				fahb.setGongysb_id(Long.parseLong(dataMap.get("GONGYSB_ID").toString()));
//				fahb.setDaohrq(dataMap.get("DAOHRQ").toString());
//				fahb.setLaiml(Double.parseDouble(dataMap.get("LAIML").toString()));
//				fahb.setSamcode(dataMap.get("SAMCODE").toString());
//				fahbDao.addFahb(fahb);
//			}
             sql="select count(c.id) ches,\n" +
                    "\t       max(c.id),\n" +
                    "\t       sum(c.maoz - c.piz-c.zongkd) as laiml,\n" +
                    "\t       substr(q.qingcsj, 0, 10) as daohrq,\n" +
                    "\t       max(c.gongysb_id) as gongysb_id,\n" +
                    "\t       c.samcode,\n" +
                    "\t       max(h.huayd_id),\n" +
                    "\t       max(h.star),\n" +
                    "\t       max(h.qnet_ar),\n" +
                    "\t       max(c.gongysmc)\n" +
                    "\t from rl_ys_chepb c\n" +
                    "\tleft join rl_ys_chepb_qc q\n" +
                    "\ton c.id = q.chepb_id\n" +
                    "\tleft join (select * from gx_chep_caizhbmb where zhuanhlb_id = 1) gx1\n" +
                    "\ton c.samcode = gx1.yuanbm\n" +
                    "\tleft join (select * from gx_chep_caizhbmb where zhuanhlb_id = 2) gx2\n" +
                    "\ton gx1.mubbm = gx2.yuanbm\n" +
                    "\tleft join rl_hy_huaydb h\n" +
                    "\ton gx2.mubbm = h.huaybm\n" +
                    "\t where 1=1\n" +
                    "\t\t" +
                    "\t\t\t--and substr(q.qingcsj, 0, 10) = #{date}\n" +
                    "\t   \n" +
                    "\t group by c.gongysb_id, c.samcode, substr(q.qingcsj, 0, 10)";
            jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		JSONObject json = new JSONObject();
		json.put("reslut", result);
		return json;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public JSONObject getFahbList(Map<String, Object> map) {
		System.out.println(map.get("date").toString());
		List<Map<String,Object>> list = fahbDao.getFahbList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			String samcode = dataMap.get("SAMCODE").toString();
			Map samMap = new HashMap<String, Object>();
			samMap.put("samcode", samcode);
			List<Map<String,Object>> datas = fahbDao.getHuaydbBySamcode(samMap);
			//收到基全硫
			double star=0;
			//低位发热量
			double qnetar=0;

			if(datas.size()>0){
				HashMap<String, Object>  da = (HashMap<String, Object>) datas.get(0);
				star = Double.parseDouble(da.get("STAR").toString());
				qnetar = Double.parseDouble(da.get("QNET_AR").toString());
			}
			objs[i] = new Object[]{dataMap.get("ID"),dataMap.get("DAOHRQ"),dataMap.get("GONGYSMC"),dataMap.get("LAIML"),
					dataMap.get("CHES"),qnetar,star};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}
	
}
