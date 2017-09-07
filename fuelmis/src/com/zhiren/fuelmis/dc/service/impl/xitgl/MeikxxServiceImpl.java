package com.zhiren.fuelmis.dc.service.impl.xitgl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zhiren.fuelmis.dc.dao.xitgl.MeikxxDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Gongysmkglb;
import com.zhiren.fuelmis.dc.entity.xitgl.Kuangzglb;
import com.zhiren.fuelmis.dc.entity.xitgl.Meikxx;
import com.zhiren.fuelmis.dc.service.xitgl.IMeikxxService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;
import com.zhiren.fuelmis.dc.wsClient.FoundationDataClient;

/** 
 * @author 陈宝露
 */
@Service
public class MeikxxServiceImpl implements IMeikxxService {

	@Autowired
	private MeikxxDao meikxxDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public JSONObject getAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Meikxx> list = meikxxDao.getAll(map);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i=0;i<list.size();i++){
			Meikxx meikxx = list.get(i);
			objs[i] = new Object[]{meikxx.getId(),meikxx.getXuh(),meikxx.getMeikdq_id(),meikxx.getBianm(),meikxx.getMingc(),
					meikxx.getQuanc(),meikxx.getPiny(),meikxx.getShengfb_id(),meikxx.getLeib(),meikxx.getLeix(),
					meikxx.getJihkjb_id(),meikxx.getLianxr(),meikxx.getGuddh(),meikxx.getYiddh(),meikxx.getChuanz(),
					meikxx.getEmail(),meikxx.getShiyzt().equals("1")?"使用":"停用",meikxx.getWenjmc(),meikxx.getBeiz()};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONArray insertMeikxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			result = meikxxDao.insertMeikxx(map);
			
			jdbcTemplate.update("insert into gongysb(id,fuid,xuh,mingc,quanc,bianm,shengfb_id,leix) values ("
					+ map.get("id")+"," 
					+ map.get("meikdq_id")+",(select (nvl(max(xuh),0)+1) from gongysb where leix = 0),'"
					+ map.get("mingc")+"','" 
					+ map.get("quanc")+"','" 
					+ map.get("bianm")+"'," 
					+ map.get("shengfb_id")+",0" 
					+")");
			
			/*jdbcTemplate.update("insert into coallog(id,riq,leix,dongz,oldcode,newcode,oldname,newname) values ("
					+Sequence.nextId()+",to_date('"
					+DateUtil.format(new Date())+"','yyyy-MM-dd HH24:mi:ss'),'煤矿单位','增加',0,"
					+map.get("bianm")+",null,'"+map.get("mingc")
					+ "')");*/
			
//			List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from meikxxb where id = "+map.get("id"));
//			FoundationDataClient.getInstance().uploadMeikxx(list);
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
		List<Meikxx> list = meikxxDao.getOne(map);
		JSONArray jsonArray = new JSONArray();
		if(list!=null){
			if(list.size()>0){
				Meikxx meikxx = list.get(0);
				meikxx.setLeib(meikxx.getLeib().equals("统配")?"2":"1");
				meikxx.setLeix("1");
				jsonArray.add(meikxx);
			}
		}
		return jsonArray;
	}

	@Override
	public JSONArray updateMeikxx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			/*jdbcTemplate.update("insert into coallog(id,riq,leix,dongz,oldcode,newcode,oldname,newname) values ("
					+Sequence.nextId()+",to_date('"
					+DateUtil.format(new Date())+"','yyyy-MM-dd HH24:mi:ss'),'煤矿单位','更新',(select bianm from meikxxb where id = "+map.get("id")+"),"
					+map.get("bianm")+",(select mingc from meikxxb where id = "+map.get("id")+"),'"+map.get("mingc")
					+ "')");*/
			
			result = meikxxDao.updateMeikxx(map);
			
			jdbcTemplate.update("update gongysb set mingc = '"+map.get("mingc")+"',quanc = '"+map.get("quanc")+"',bianm = "+map.get("bianm")+",shengfb_id = "+map.get("shengfb_id")+" where id = "+map.get("id"));
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	@Transactional
	public JSONArray insertKuangzglb(List<Kuangzglb> list) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			meikxxDao.deleteKuangzglb(list.get(0).getMeikxxb_id());
			result = meikxxDao.insertKuangzglb(list);
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	@Transactional
	public JSONArray insertGongysmkglb(List<Gongysmkglb> list) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			meikxxDao.deleteGongysmkglb(list.get(0).getMeikxxb_id());
			result = meikxxDao.insertGongysmkglb(list);
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray getKuangzglb(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<String> list = meikxxDao.getKuangzglb(map);
		JSONArray jsonArray = new JSONArray();
		if(list!=null&&list.size()>0)
			for(int i=0;i<list.size();i++)
				jsonArray.add(list.get(i));
		return jsonArray;
	}

	@Override
	public JSONArray getGongysmkglb(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<String> list = meikxxDao.getGongysmkglb(map);
		JSONArray jsonArray = new JSONArray();
		if(list!=null&&list.size()>0)
			for(int i=0;i<list.size();i++)
				jsonArray.add(list.get(i));
		return jsonArray;
	}

}
