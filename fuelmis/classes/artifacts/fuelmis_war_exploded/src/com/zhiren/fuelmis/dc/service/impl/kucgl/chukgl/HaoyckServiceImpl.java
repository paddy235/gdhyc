package com.zhiren.fuelmis.dc.service.impl.kucgl.chukgl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.dao.kucgl.chukgl.HaoyckDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.kucgl.ICaigrkService;
import com.zhiren.fuelmis.dc.service.kucgl.chukgl.IHaoyckService;
import com.zhiren.fuelmis.dc.utils.Sequence;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 陈宝露
 */
@Service
public class HaoyckServiceImpl implements IHaoyckService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final DecimalFormat df = new DecimalFormat("0.00");
	@Autowired
	private HaoyckDao haoyckDao;
	@Autowired
	private ICaigrkService caigrkService;

	@Override
	public Map<String, Object> getHaoyckmx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String rukdbh = map.get("rukdbh").toString();
		List<Map<String, Object>> chukdhList = haoyckDao.getChukdhList(map);
		Map<String, Object> chukd = haoyckDao.getChukd(map);
		Map<String, Object> haoyckmx = new HashMap<String, Object>();
		haoyckmx.put("chukd", chukd);
		haoyckmx.put("chukdhList", chukdhList);
		return haoyckmx;

	}

	@Override
	public String saveChukd(JSONObject m) {
		Map<String, Object> chukd = m.getJSONObject("chukd");
		Object chukdbh = chukd.get("CHUKDBH");
		if (chukdbh == null) {
			this.addChukdbh(chukd);// 给出出库单添加出库单编号
		} else {
			String chukrq = chukd.get("CHUKRQ").toString();
			String riq = chukdbh.toString().substring(4, 12);
			String chukrqs = chukrq.replace("-", "");
			if (!riq.equals(chukrqs)) {
				this.addChukdbh(chukd);
			}
		}
		Renyxx user = (Renyxx) JSONObject.toBean(m.getJSONObject("user"), Renyxx.class);
		List<Map<String, Object>> list = m.getJSONArray("chukdhList");
		if (m.get("delChukdhId") != null) {
			List<Object> delCIds = m.getJSONArray("delChukdhId");
			for (Object id : delCIds) {
				haoyckDao.deleteChurkd(id);
			}
		}
		String chukdid = Sequence.nextId();
		chukd.put("CHUKDID", chukdid);
		chukd.put("ZUZ", user.getDiancxxb_id());
		String kuaijrq = haoyckDao.getKuaijrq(chukd.get("KUCZZ"));
		chukd.put("KUAIJRQ", kuaijrq);
		chukd.put("CAOZRID", user.getId());
		chukd.put("CAOZR", user.getMingc());
		for (Map<String, Object> churkd : list) {
			churkd.putAll(chukd);
			if (churkd.get("ID") == null) {
				String id = Sequence.nextId();
				churkd.put("ID", id);
				churkd.put("ZHUANGT", 0);
				haoyckDao.insertChurkd(churkd);
			} else {
				haoyckDao.updateChurkd(churkd);
			}

		}
		return chukd.get("CHUKDBH").toString();
	}

	private void addChukdbh(Map<String, Object> chukd) {
		List xuh = jdbcTemplate.queryForList(
				"select " + "case when max(rukdbh) is null " + "then 'CKD-'||replace('" + chukd.get("CHUKRQ")
						+ "','-','')||'001' " + "else 'CKD-'||to_char(max(substr(rukdbh,5,11))+1) " + "end  rukdbh  \n"
						+ "  from (select distinct rukdbh  from RL_KC_CHURKDB where substr(rukdbh,5,8) = replace('"
						+ chukd.get("CHUKRQ") + "', '-', ''))  ");

		String chukdbh = (String) ((Map) xuh.get(0)).get("RUKDBH");
		chukd.put("CHUKDBH", chukdbh);
	}

	@Override
	public void deleteChurkd(String chukdbh) {
		haoyckDao.deleteChurkdByChukdbh(chukdbh);
	}

	@Override
	public List<Map<String, Object>> getChukdList(Map<String, Object> map) {
		return haoyckDao.getChukdList(map);
	}

	@Override
	public void chuk(JSONObject m) {
		Map<String, Object> map = m.getJSONObject("chukd");
		map.put("ZHUANGT", 1);
		haoyckDao.updateChurkdShifdrZhuant(map);
	}

	@Override
	public List<Map<String, Object>> getDangrjc() {
		return haoyckDao.getDangrjc();
	}
}
