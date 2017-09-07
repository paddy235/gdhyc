package com.zhiren.fuelmis.dc.service.impl.hetgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.hetgl.CaigddbDao;
import com.zhiren.fuelmis.dc.dao.hetgl.PriceQalityRangeDao;
import com.zhiren.fuelmis.dc.dao.hetgl.PriceSchemeDao;
import com.zhiren.fuelmis.dc.entity.hetgl.Caigddb;
import com.zhiren.fuelmis.dc.entity.hetgl.Caigddbsub;
import com.zhiren.fuelmis.dc.entity.hetgl.Caigddbyfsub;
import com.zhiren.fuelmis.dc.entity.hetgl.Hetkhzb;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceScheme;
import com.zhiren.fuelmis.dc.entity.xitgl.Gongys;
import com.zhiren.fuelmis.dc.service.hetgl.CaigddbService;
import com.zhiren.fuelmis.dc.utils.Sequence;

@Service("caigddbService")
public class CaigddbServiceImpl implements CaigddbService {

	@Autowired
	private CaigddbDao caigddbDao;

	@Autowired
	private PriceQalityRangeDao priceQalityRangeDao;

	@Autowired
	private PriceSchemeDao priceSchemeDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public JSONArray addCaigddb(Caigddb caigddb,JSONArray subs,Long diancxxb_id) {
		int result = 0;
		JSONArray array = new JSONArray();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		try{
			Long subId=new Long(0);
			Long id = Long.parseLong(Sequence.nextId());
			caigddb.setId(id);
			result = caigddbDao.addCaigddb(caigddb);
			JSONObject json = JSONObject.fromObject(caigddb);
			jsonObject.put("info", json);
			if("运费订单".equals(caigddb.getCaigddlx())){
				for(int i=0; i<subs.size(); i++){
					Caigddbyfsub caigddbyfsub = (Caigddbyfsub) JSONObject.toBean((JSONObject)subs.get(i),Caigddbyfsub.class);
					caigddbyfsub.setCaigddb_id(id);
					subId = Long.parseLong(Sequence.nextId());
					caigddbyfsub.setId(subId);
					caigddbDao.addCaigddbyfsub(caigddbyfsub);
					JSONObject json1 = JSONObject.fromObject(caigddbyfsub);
					jsonArray.add(json1);
				}
				jsonObject.put("caigyunfsinfo", jsonArray);
			}else{
				for(int i=0; i<subs.size(); i++){
					Caigddbsub caigddbsub = (Caigddbsub) JSONObject.toBean((JSONObject)subs.get(i),Caigddbsub.class);
					caigddbsub.setCaigddb_id(id);
					caigddbsub.setDiancxxb_id(diancxxb_id);
					subId = Long.parseLong(Sequence.nextId());
					caigddbsub.setId(subId);
					caigddbDao.addCaigddbsub(caigddbsub);
					JSONObject json1 = JSONObject.fromObject(caigddbsub);
					jsonArray.add(json1);
				}
				jsonObject.put("ranlcginfo", jsonArray);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		array.add(jsonObject);
		return array;
	}


	@Override
	public JSONObject getCaigddbList(Map<String, Object> map) {
		List<Map<String,Object>> list = caigddbDao.getCaigddbList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{dataMap.get("ID"),dataMap.get("BIANH"),dataMap.get("DINGDRQ"),
					dataMap.get("CAIGDDLX"),dataMap.get("DIANCXXB_ID"),dataMap.get("GONGYS_ID"),dataMap.get("CAOZRY")};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONObject getCaigddbinfoList(String strdate, String enddate,
										 String diancid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("strdate", strdate);
		map.put("enddate", enddate);
		map.put("diancid", diancid);
		List<Map<String,Object>> list = caigddbDao.getCaigddbinfoList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{dataMap.get("ID"),dataMap.get("RIQ"),dataMap.get("WEEK"),
					dataMap.get("JIHSL"),dataMap.get("LURY"),dataMap.get("SHENH")};
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONArray delCaigddb(Map<String, Object> map) {
		int result = 0;
		try{
			JSONObject json = getCaigddbById(map);
			JSONArray subs=new JSONArray();
			if("运费订单".equals(json.getString("caigddlx"))){
				caigddbDao.delCaigddbyfsub(map);
			}else{
				subs = getCaigddbsubById(map);
				for(int i=0; i<subs.size(); i++){
					Caigddbsub caigddbsub = (Caigddbsub) JSONObject.toBean((JSONObject)subs.get(i),Caigddbsub.class);
					Map<String,Object> map1 = new HashMap<String, Object>();
					map1.put("id",caigddbsub.getId());
					JSONArray jijfs = getJijfsByCaigddbId1(map1);
					for(int n=0;n<jijfs.size();n++){
						PriceScheme pricescheme = (PriceScheme) JSONObject.toBean((JSONObject)jijfs.get(n),PriceScheme.class);
						Map<String,Object> map2 = new HashMap<String, Object>();
						map2.put("id", pricescheme.getId());
						caigddbDao.delKaohzbbyPriceschemeId(map2);
						priceSchemeDao.delPriceScheme(map2);
					}
					caigddbDao.delCaigddbsubbyid(map1);
				}
			}
			result = caigddbDao.delCaigddb(map);

		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}


	@Override
	public JSONObject getCaigddbById(Map<String, Object> map) {
		List<Caigddb> list = caigddbDao.getCaigddbById(map);
		Caigddb caigddb = null;
		if(list.size()>0){
			caigddb = list.get(0);
		}
		JSONObject json = JSONObject.fromObject(caigddb);
		return json;
	}


	@Override
	public JSONArray updateCaigddb(Caigddb caigddb,JSONArray subs) {
		int result = 0;
		JSONArray array = new JSONArray();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		try{
			result = caigddbDao.updateCaigddb(caigddb);
			JSONObject json = JSONObject.fromObject(caigddb);
			jsonObject.put("info", json);
			if("运费订单".equals(caigddb.getCaigddlx())){
				for(int i=0; i<subs.size(); i++){
					Caigddbyfsub caigddbyfsub = (Caigddbyfsub) JSONObject.toBean((JSONObject)subs.get(i),Caigddbyfsub.class);
					if(caigddbyfsub.getId()==null||caigddbyfsub.getId()==0){
						caigddbyfsub.setCaigddb_id(caigddb.getId());
						Long subId = Long.parseLong(Sequence.nextId());
						caigddbyfsub.setId(subId);
						caigddbDao.addCaigddbyfsub(caigddbyfsub);
						JSONObject json1 = JSONObject.fromObject(caigddbyfsub);
						jsonArray.add(json1);
					}else{
						caigddbDao.updateCaigddbyfsub(caigddbyfsub);
						JSONObject json1 = JSONObject.fromObject(caigddbyfsub);
						jsonArray.add(json1);
					}
				}
				jsonObject.put("caigyunfsinfo", jsonArray);
			}else{
				for(int i=0; i<subs.size(); i++){
					Caigddbsub caigddbsub = (Caigddbsub) JSONObject.toBean((JSONObject)subs.get(i),Caigddbsub.class);
					if(caigddbsub.getId()==null||caigddbsub.getId()==0){
						caigddbsub.setCaigddb_id(caigddb.getId());
						caigddbsub.setDiancxxb_id(caigddb.getDiancxxb_id());
						Long subId = Long.parseLong(Sequence.nextId());
						caigddbsub.setId(subId);
						caigddbDao.addCaigddbsub(caigddbsub);
						JSONObject json1 = JSONObject.fromObject(caigddbsub);
						jsonArray.add(json1);
					}else{
						caigddbDao.updateCaigddbsub(caigddbsub);
						JSONObject json1 = JSONObject.fromObject(caigddbsub);
						jsonArray.add(json1);
					}
				}
				jsonObject.put("ranlcginfo", jsonArray);
			}

//			for(int i=0; i<kaohzbinfo.size(); i++){
//				Hetkhzb hetkhzb1 = (Hetkhzb) JSONObject.toBean((JSONObject)kaohzbinfo.get(i),Hetkhzb.class);
//				if(hetkhzb1.getId()==null||hetkhzb1.getId()==0){
//					hetkhzb1.setId(Long.parseLong(Sequence.nextId()));
//					hetkhzb1.setCaigddb_id(caigddb.getId());
//					caigddbDao.addKaohzb(hetkhzb1);
//				}else{
//					caigddbDao.updateKaohzb(hetkhzb1);
//				}
//			}
//			for(int i=0; i<kaohzbinfo1.size(); i++){
//				Hetkhzb hetkhzb2 = (Hetkhzb) JSONObject.toBean((JSONObject)kaohzbinfo1.get(i),Hetkhzb.class);
//				if(hetkhzb2.getId()==null||hetkhzb2.getId()==0){
//					hetkhzb2.setId(Long.parseLong(Sequence.nextId()));
//					hetkhzb2.setCaigddb_id(caigddb.getId());
//					caigddbDao.addKaohzb(hetkhzb2);
//				}else{
//					caigddbDao.updateKaohzb(hetkhzb2);
//				}
//			}
//			for(int i=0; i<sechemeInfo.size(); i++){
//				PriceScheme priceScheme = (PriceScheme) JSONObject.toBean((JSONObject)sechemeInfo.get(i),PriceScheme.class);
//				if(priceScheme.getId()==null||priceScheme.getId()==0){
//					priceScheme.setId(Long.parseLong(Sequence.nextId()));
//					priceScheme.setPo_sub_id(caigddb.getId());
//					priceSchemeDao.addPriceScheme(priceScheme);
//				}else{
//					priceSchemeDao.updatePriceScheme(priceScheme);
//				}
//			}
		}catch(Exception e){
			e.printStackTrace();
		}
		array.add(jsonObject);
		return array;
	}


	@Override
	public JSONArray getCaigddbsubById(Map<String, Object> map) {
		List<Caigddbsub> list = caigddbDao.getCaigddbsubById(map);
		JSONObject json = null;
		JSONArray jsonArray = new JSONArray();
		for (Caigddbsub caigddbsub : list) {
			json = JSONObject.fromObject(caigddbsub);
			Long gysid = caigddbsub.getGongys_id();
			Gongys gongys = getGongysById(gysid.toString());
			json.put("gongysmc", (gongys==null?"":gongys.getMingc()));
			Long huowid = caigddbsub.getHuow_id();
			String huowmc = "";
			if(null != huowid){
				huowmc = getHuowMingcById(huowid);
			}
			json.put("huowmc", huowmc);
			Long yunsfsid = caigddbsub.getYunsfs_id();
			String yunsfs = "";
			if(null != yunsfsid){
				yunsfs = getYunsfsMingcById(yunsfsid);
			}
			json.put("ysfsmc", yunsfs);

			jsonArray.add(json);
		}
		return jsonArray;
	}

	@SuppressWarnings("unchecked")
	public Gongys getGongysById(String id){
		@SuppressWarnings("rawtypes")
		Map map = new HashMap<String, Object>();
		map.put("id", id);
		Gongys gongys = null;
		List<Gongys> list = caigddbDao.getGongysById(map);
		if(list.size()>0){
			gongys =  list.get(0);
		}
		return gongys;
	}

	@SuppressWarnings("unchecked")
	public String getYunsfsMingcById(Long id){
		@SuppressWarnings("rawtypes")
		Map map = new HashMap<String, Object>();
		map.put("id", id);
		@SuppressWarnings("unused")
		Gongys gongys = null;
		String result = caigddbDao.getYunsfsMingcById(map);
		return result;
	}


	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public String getHuowMingcById(Long id){
		Map map = new HashMap<String, Object>();
		map.put("id", id);
		Gongys gongys = null;
		String result = caigddbDao.getHuowMingcById(map);
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@Override
	public JSONObject getjihkjById(String id) {
		Map map = new HashMap<String, Object>();
		map.put("id", id);
		List<Map<String,Object>> list = caigddbDao.getJihkjById(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JSONObject json  = new JSONObject();
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> dataMap = (HashMap<String, Object>) list.get(i);
			json.put("id",dataMap.get("ID"));
			json.put("mingc", dataMap.get("MINGC"));
		}
		return json;
	}


	@Override
	public JSONObject getBianh(Map<String, Object> map) {
		String dingdrq = map.get("dingdrq").toString();
		String bianh ="";
		List<Caigddb> list = caigddbDao.getCaigddByBh(map);
		if(list.size()>0){
			int count = list.size()+1;
			bianh = dingdrq+getBianhByCaigddb(count);
		}else{
			bianh +=dingdrq+"-001";
		}
		JSONObject json = new JSONObject();
		json.put("bianh", bianh);
		return json;
	}

	public String getBianhByCaigddb(int count){
		if(count<10){
			return "-00"+count;
		}if(count<100){
			return "-0"+count;
		}else{
			return "-"+count;
		}
	}


	@Override
	public JSONObject beforedelCaigddb(Map<String, Object> map) {
		String count = caigddbDao.beforedelCaigddb(map);
		JSONObject json = new JSONObject();
		int result = Integer.parseInt(count);
		if(result == 0){
			json.put("relust", "true");
		}else{
			json.put("relust", "false");
		}
		return json;
	}


	@Override
	public JSONArray getJijfsByCaigddbId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list =jdbcTemplate.queryForList("select * from RL_HT_PRICE_SCHEME where po_sub_id = "+map.get("id"));
		JSONArray jsonArray = new JSONArray();
		for (Map<String,Object> maps : list) {
			JSONObject json = new JSONObject();
			json.put("id", maps.get("ID"));
			json.put("price_item_id", maps.get("PRICE_ITEM_ID"));
			json.put("price_component_id", maps.get("PRICE_COMPONENT_ID"));
			json.put("shangxmax", maps.get("SHANGXMAX"));
			json.put("xiaxmin", maps.get("XIAXMIN"));
			PriceScheme pricescheme = (PriceScheme) JSONObject.toBean((JSONObject)json,PriceScheme.class);
			Map<String,Object> map1 = new HashMap<String, Object>();
			map1.put("id", pricescheme.getId());
			JSONArray kaohzb_qnet = getKaohzb_QnetByCaigddbId(map1);
			json.put("kaohzb", kaohzb_qnet);
			jsonArray.add(json);
		}
		return jsonArray;
	}

	public JSONArray getJijfsByCaigddbId1(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list =jdbcTemplate.queryForList("select * from RL_HT_PRICE_SCHEME where po_sub_id = "+map.get("id"));
		JSONArray jsonArray = new JSONArray();
		for (Map<String,Object> maps : list) {
			JSONObject json = new JSONObject();
			json.put("id", maps.get("ID"));
			json.put("price_item_id", maps.get("PRICE_ITEM_ID"));
			json.put("price_component_id", maps.get("PRICE_COMPONENT_ID"));
			jsonArray.add(json);
		}
		return jsonArray;
	}


	@Override
	public JSONArray getKaohzb_QnetByCaigddbId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list =jdbcTemplate.queryForList("select * from RL_HT_KAOHZBB where scheme_id =  "+map.get("id")+"  order by id asc");
		JSONArray jsonArray = new JSONArray();
		for (Map<String,Object> maps : list) {
			JSONObject json = new JSONObject();
			json.put("id", maps.get("ID"));
			json.put("shangx", maps.get("SHANGX"));
			json.put("jizjg", maps.get("JIZJG"));
			json.put("jizrz", maps.get("JIZRZ"));
			/**
			 * mjy add
			 */
			json.put("jizzb", maps.get("JIZZB"));
			json.put("shijzkj", maps.get("SHIJZKJ"));
			/**
			 * mjy end
			 */
			json.put("tiaoj", maps.get("TIAOJ"));
			/**
			 * mjy add
			 */
			json.put("xuh", maps.get("XUH"));
			json.put("xiax", maps.get("XIAX"));
			json.put("shijrz", maps.get("SHIJRZ"));
			json.put("rezjs", maps.get("REZJS"));
			json.put("jiangkjs", maps.get("JIANGKJS"));
			json.put("jijgs_id", maps.get("JIJGS_ID"));
			json.put("leix", maps.get("LEIX"));
			json.put("jizkj", maps.get("JIZKJ"));
			json.put("scheme_id", maps.get("SCHEME_ID"));
			json.put("shangxmax", maps.get("SHANGXMAX"));
			json.put("xiaxmin", maps.get("XIAXMIN"));
			/**
			 * mjy end
			 */
			jsonArray.add(json);

		}
		return jsonArray;
	}


	@Override
	public JSONArray getKaohzb_StarByCaigddbId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list =jdbcTemplate.queryForList("select * from RL_HT_KAOHZBB where scheme_id = "+map.get("id")+" order by id asc");
		JSONArray jsonArray = new JSONArray();
		for (Map<String,Object> maps : list) {
			JSONObject json = new JSONObject();
			json.put("id", maps.get("ID"));
			json.put("shangx", maps.get("SHANGX"));
			json.put("xiax", maps.get("XIAX"));
			json.put("jizzb", maps.get("JIZZB"));
			json.put("shijzkj", maps.get("SHIJZKJ"));
			//json.put("jiangkjs", maps.get("JIANGKJS"));
			json.put("tiaoj", maps.get("TIAOJ"));
			jsonArray.add(json);
		}
		return jsonArray;
	}

    @Override
    public List<Map<String, Object>> getJijfsByCaigddbSubId(String caigddb_sub_id) {
        List<Map<String,Object>> list=jdbcTemplate.queryForList("select a.id,a.po_sub_id,a.price_component_id,a.price_item_id,a.status,a.shangxmax,a.xiaxmin\n" +
							"from rl_ht_price_scheme a,rl_ht_price_item b where a.price_item_id = b.id and po_sub_id="+caigddb_sub_id+"\n" + 
							"order by b.xuh asc");
        for (Map<String,Object> map:list) {
            //计价组件
            Object component_id=map.get("PRICE_COMPONENT_ID");
            List<Map<String,Object>> componentList=jdbcTemplate.queryForList("select id,name,classname,url,status,danw,shuom \n" +
                    "from rl_ht_price_component where id="+component_id);
            map.put("COMPONENT",componentList.get(0));
            //计价指标
            Object item_id=map.get("PRICE_ITEM_ID");
            List<Map<String,Object>> itemList=jdbcTemplate.queryForList("select id,name,code,status\n" +
                    "from rl_ht_price_item where id="+item_id);
            map.put("ITEM",itemList.get(0));
            //考核指标
            Object scheme_id=map.get("ID");
            List<Map<String,Object>> kaohzbList=jdbcTemplate.queryForList("select * \n" +
                    "from RL_HT_KAOHZBB where scheme_id="+scheme_id+" order by xuh");
            map.put("KAOHZB",kaohzbList);
        }
        return list;
    }


    @Override
	public JSONArray getCaigddbyfsubById(Map<String, Object> map) {
		List<Caigddbyfsub> list = caigddbDao.getCaigddbyfsubById(map);
		JSONObject json = null;
		JSONArray jsonArray = new JSONArray();
		for (Caigddbyfsub caigddbyfsub : list) {
			json = JSONObject.fromObject(caigddbyfsub);
			jsonArray.add(json);
		}
		return jsonArray;
	}


	@Override
	public JSONArray delCaigddbsub(Map<String, Object> map1) {
		int result = 0;
		try{
			JSONArray jijfs = getJijfsByCaigddbId1(map1);
			for(int n=0;n<jijfs.size();n++){
				PriceScheme pricescheme = (PriceScheme) JSONObject.toBean((JSONObject)jijfs.get(n),PriceScheme.class);
				Map<String,Object> map2 = new HashMap<String, Object>();
				map2.put("id", pricescheme.getId());
				/*JSONArray kaohzb = getKaohzb_QnetByCaigddbId(map2);
				for(int m=0;m<kaohzb.size();m++){
					Hetkhzb hetkhzb = (Hetkhzb) JSONObject.toBean((JSONObject)kaohzb.get(m),Hetkhzb.class);
					Map<String,Object> map3 = new HashMap<String, Object>();
					map3.put("id", hetkhzb.getId());
					caigddbDao.delKaohzbbyid(map3);
				}*/
				caigddbDao.delKaohzbbyPriceschemeId(map2);
				priceSchemeDao.delPriceScheme(map2);
			}
			caigddbDao.delCaigddbsubbyid(map1);

		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}


	@Override
	public JSONArray delCaigddbyfsub(Map<String, Object> map) {
		int result = 0;
		try{
			result=	caigddbDao.delCaigddbyfsub(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

}
