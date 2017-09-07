package com.zhiren.fuelmis.dc.service.impl.js;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.dao.js.JsDao;
import com.zhiren.fuelmis.dc.service.js.IJiesService;
import com.zhiren.fuelmis.dc.utils.Sequence;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JiesServiceImpl implements IJiesService {

	@Autowired
	private JsDao jsDao;
	/**
	 * 查询所有发票信息
	 */

	public JSONObject getFapb(Map<String, Object> map) {
		@SuppressWarnings("rawtypes")
		List list = jsDao.getFapb(map);// 调用dao从数据库中获取发票信息表
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list
					.get(i);
			// 根据煤矿id查询煤矿简称
			// jsDao.getMeikjcById(hashMap.get("MEIKXXB_ID"))
			// 根据结算id查询结算单号
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// String time=format.format(date);
			objs[i] = new Object[] {
					hashMap.get("ID"), // 发票id
					hashMap.get("BIANM"), // 编码
					hashMap.get("XINGZ"), // 性质
					jsDao.getMeikjcById((BigDecimal) hashMap.get("MEIKXXB_ID")), // 煤矿简称
					jsDao.getJeisdhById((BigDecimal) hashMap.get("JIESB_ID")),// 结算单号
					hashMap.get("JIESMS"),// 结算描述
					hashMap.get("QISBH"), // 起始编号
					hashMap.get("ZHONGZBH"), // 终止编号
					hashMap.get("FAPS"), // 发票数
					hashMap.get("ZONGJE"), // 发票金额
					hashMap.get("JIESMK"),// 结算煤款
					format.format(hashMap.get("FAPRQ")), // 发票日期
					format.format(hashMap.get("RIQ")), // 录入日期
					hashMap.get("LURR"),// 录入人
					hashMap.get("ZHIFTK"),// 支付条款
					hashMap.get("ZHUANGT"),// 状态
					hashMap.get("FUJ") };// 附件

		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	public JSONObject getFapbById(String id) {
		@SuppressWarnings("rawtypes")
		List list = jsDao.getFapbById(id);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		@SuppressWarnings("unchecked")
		HashMap<String, Object> map = (HashMap<String, Object>) list.get(0);
		map.put("FAPRQ", format.format(map.get("FAPRQ")));
		JSONObject result = JSONObject.fromObject(map);
		return result;
	}

	
	 public Integer getMaxBianm(){ return jsDao.getMaxBianm(); }
	 

	@Override
	public int insertJies(Map<String, Object> map) {
		int ret = -1;
		try {
			map.put("ID", Sequence.nextId());
			ret = jsDao.addFapData(map);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}

	public int updateFap(Map<String, Object> map) {
		int ret = -1;
		try {
			ret = jsDao.updateFapById(map);
			// String maxBianh=jsDao.getMaxBianm();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}

	public int delFapById(String id) {

		return jsDao.delFapById(id);
	}

	public JSONObject getFapByCondition(Map<String, Object> map) {
		
		@SuppressWarnings("rawtypes")
		List list = jsDao.getFapByCondition(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < list.size(); i++) {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[] { 
					hashMap.get("ID"), 
					hashMap.get("BIANM"),
					hashMap.get("XINGZ"),
					jsDao.getMeikjcById((BigDecimal) hashMap.get("MEIKXXB_ID")), // 煤矿简称
					jsDao.getJeisdhById((BigDecimal) hashMap.get("JIESB_ID")),// 结算单号
					format.format(hashMap.get("FAPRQ")), // 发票日期
					hashMap.get("ZHIFTK"), 
					hashMap.get("ZONGJE"),
					hashMap.get("LURR"), 
					hashMap.get("ZHUANGT") 
					};
		}
		jsonMap.put("data", objs);

		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONObject getFapb() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> getAllJiesxx(String id) {
		// TODO Auto-generated method stub
		if(!id.equals("-1")){
			return jsDao.getAllJiesxx(id);
		}else {
			return jsDao.getJiesxx();
		}
	}

	public List<Map<String, Object>> getJiesxx() {
		// TODO Auto-generated method stub
		return jsDao.getJiesxx();
	}
	@Override
	public JSONObject getFap(Map<String, Object> conditionMap) {
		List<Map<String, Object>> list = jsDao.getFap(conditionMap);
		Map<String,Object> map=new HashMap<String,Object>();
		Object[][] o = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> m = (Map<String, Object>) list.get(i);
			o[i] = new Object[] { 
					m.get("ID"), 
					m.get("BIANM"),
					m.get("XINGZ"),
					m.get("MINGC"), // 煤矿简称
					m.get("JIESBH"),// 结算单号
					m.get("FAPRQ"), // 发票日期
					m.get("ZHIFTK"), 
					m.get("ZONGJE"),
					m.get("LURR"), 
					m.get("ZHUANGT") 
					};
		}
		map.put("data", o);
		JSONObject faps = JSONObject.fromObject(map);
		return faps;
	}

	@Override
	public List<Map<String, Object>> getJiesdhList(int id) {
		return jsDao.getJiesdhList(id);
	}

	@Override
	public JSONObject getFapxx(Map<String, Object> conditionMap) {
		List<Map<String, Object>> list = jsDao.getFap(conditionMap);
		Map<String,Object> map=new HashMap<String,Object>();
		Object[][] o = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> m = (Map<String, Object>) list.get(i);
			o[i] = new Object[] { 
					m.get("ID"), 
					m.get("BIANM"),
					m.get("XINGZ"),
					m.get("MINGC"), // 煤矿简称
					m.get("JIESBH"),// 结算单号
					m.get("JIESMS"),
					m.get("QISBH"),
					m.get("ZHONGZBH"),
					m.get("FAPS"),
					m.get("ZONGJE"),
					m.get("JIESMK"),
					m.get("FAPRQ"), // 发票日期
					m.get("LURSJ"),
					m.get("LURR"), 
					m.get("ZHIFTK"), 
					m.get("ZHUANGT") 
					};
		}
		map.put("data", o);
		JSONObject faps = JSONObject.fromObject(map);
		return faps;
	}

	@Override@Transactional
	public String jsHuit(String zhuangt, String huitlc_id, String jiesdbh,String rebacker, String advice) {
		if(zhuangt.equals("0")){
			zhuangt="0";//驳回
			jsDao.setJiesdLiucIDWithNull(jiesdbh);
		}else{
			zhuangt="2";//通过
//----------------------------------------------------------------------------------------------------------------------
			//根据结算编号更新使得出入库存表中各车的金额和等于结算金额
//			jiesdbh="GD-JS-dsrd-201512-03";
//			1；得到月结算单的单价、金额
			Map<String,Object> map=new  HashMap<String,Object>();
			map.put("jiesdbh",jiesdbh);
			List<Map<String,Object>> list=jsDao.getDanjAndJine(map);
			Double jiesdj=Double.parseDouble(list.get(0).get("JIESDJ").toString());
			Double jiesje=Double.parseDouble(list.get(0).get("JINE").toString());
			String balance_date=list.get(0).get("BALANCE_DATE").toString();
//			2：根据月结算单编号得到出入库存id的最大值
			String rukdidmax=jsDao.getMaxRukdid(jiesdbh);
//			3：得到rukdbh
			List<String> rukdbhs=jsDao.getRukdbh(jiesdbh);
			String r="'-1'";
			for (String rukdbh:rukdbhs) {
				r+=",'"+rukdbh+"'";
			}
//			4：得到其他车的rukdbh
//			5：根据结算单价，来计算其他车的金额排除最大id
			map.put("rukdbhs",r);
			map.put("maxid",rukdidmax);
			map.put("jiesdj",jiesdj);
			map.put("balance_date", balance_date);
			jsDao.updateOtherJine(map);

			String otherZongje=jsDao.getOtherZongje(map);
//			6：结算单金额-其他车总金额=得到的那个车的金额
			Double leftJine=jiesje-Double.parseDouble(otherZongje);
			jsDao.updateMaxRukdje(rukdidmax,leftJine,balance_date);
			//更新化验报告和会计期
			for (String rukdbh:rukdbhs) {
				String samcode=jsDao.getSamcode(rukdbh);
				Map<String,Object> huayd=jsDao.getHuayd(samcode);
				huayd.put("RUKDBH",rukdbh);
				jsDao.updateHuaybg(huayd);
				jsDao.updateChurkd(rukdbh,jiesdbh);
				//更新结算单id
			}		
		}
		jsDao.updateHuitZhuangt(jiesdbh,zhuangt);
		return null;
	}

	@Override
	public List<Map<String, Object>> getFapById(String id) {
		return jsDao.getFapById(id);
	}
}

