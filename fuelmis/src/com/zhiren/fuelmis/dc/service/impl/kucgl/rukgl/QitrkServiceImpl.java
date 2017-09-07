package com.zhiren.fuelmis.dc.service.impl.kucgl.rukgl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.dao.kucgl.CaigrkDao;
import com.zhiren.fuelmis.dc.dao.kucgl.KucsscbDao;
import com.zhiren.fuelmis.dc.dao.kucgl.rukgl.QitrkDao;
import com.zhiren.fuelmis.dc.dao.kucgl.rukgl.YunzfrkDao;
import com.zhiren.fuelmis.dc.entity.kucgl.ChurkBean;
import com.zhiren.fuelmis.dc.entity.kucgl.Shiscbhs;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.kucgl.rukgl.IQitrkService;
import com.zhiren.fuelmis.dc.service.kucgl.rukgl.IRanlcgrkService;
import com.zhiren.fuelmis.dc.service.kucgl.rukgl.IYunzfrkService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;
import com.zhiren.fuelmis.dc.utils.DateUtil.DateFormatType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Rain
 */
@Service
public class QitrkServiceImpl implements IQitrkService {

	@Autowired
	private QitrkDao qitrkDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;


	private static final DecimalFormat df = new DecimalFormat("0.00");

	@Override
	public List<Map<String, Object>> getQitrkList(Map<String, Object> map) {
		List<Map<String, Object>> list = qitrkDao.getQitrkList(map);
		return list;
	}




	private String createRukdbh() {
		String date = DateUtil.format(new Date(), DateFormatType.SIMPLE_TYPE);
		String jians = "select case\n" + "         when max(rukdbh) is null then\n"
				+ "          'RKD-'||replace(to_char(sysdate, 'yyyy-mm-dd'), '-', '') || '001'\n" + "         else\n"
				+ "          'RKD-'||to_char(max(rukdbh) + 1)\n" + "       end rukdbh\n"
				+ "  from (select distinct regexp_substr(rukdbh,'[0-9]+') rukdbh\n"
				+ "          from RL_KC_CHURKDB\n )  "
				+ "   where substr(rukdbh, 0, 8)  = replace(to_char(sysdate, 'yyyy-mm-dd'), '-', '') ";
		List xuh = jdbcTemplate.queryForList(jians);
		return (String) ((Map) xuh.get(0)).get("rukdbh");
	}





	@Override
	public JSONArray ruk(String rukdbh) {
		int result = 0;
		try {
			// 判断是否有会计期打开
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("rukdbh", rukdbh);
			// // 更新RL_KC_CHURKDB
			jdbcTemplate.update(
					"update RL_KC_CHURKDB set jizrq = '" + DateUtil.format(new Date(), DateFormatType.SIMPLE_TYPE) + "'," 
							+ "yewrq = '" + DateUtil.format(new Date(), DateFormatType.SIMPLE_TYPE) + "', \n"
							+ " zhuangt = 1 ,shifdr=0,kuaijrq=(decode((select yewlx from rl_kc_churkdb where rukdbh='"+rukdbh+"'),3,"
							+ "(select kuaijrq from rl_kc_kuaijrqb kq inner join gx_kuaijq_zuz gx on kq.id=gx.kuaijq_id where gx.zhuangt='启用' )"
							+ ",null)),kuaijrq_id=(decode((select yewlx from rl_kc_churkdb where rukdbh='"+rukdbh+"'),3,"
							+ "(select kq.id from rl_kc_kuaijrqb kq inner join gx_kuaijq_zuz gx on kq.id=gx.kuaijq_id where gx.zhuangt='启用' ) ,null)) , \n"
							+ " balance_flag= 1 ,balance_date = '" + DateUtil.format(new Date(), DateFormatType.SIMPLE_TYPE) + "' \n"
							+ "where rukdbh = '" + rukdbh + "' ");

			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONArray chex(String rukdbh) {
		int result = 0;
		try {
			jdbcTemplate.update("delete from RL_KC_CHURKDB where rukdbh = '" + rukdbh + "'");
			jdbcTemplate.update("delete from GX_CHURKDB_YUNSDJ where rukdbh = '" + rukdbh + "'");
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getQitrk() {
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("hangh", "");
		rtnMap.put("kucwz", "");
		rtnMap.put("kucwl", "");
		rtnMap.put("shul", "");
		rtnMap.put("jine", "");
		rtnMap.put("tiaozje", "");
		rtnMap.put("zongje", "");
		rtnMap.put("jiz", "");
		rtnMap.put("banz", "");
		rtnMap.put("dangqkcjg", "");
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(rtnMap);
		jsonObject.put("list", jsonArray);
		jsonObject.put("rukdbh", createRukdbh());
		return jsonObject;
	}

	@Override
	public JSONArray check(String rukdbh) {
		int result = 0;
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select KUAIJRQ from RL_KC_CHURKDB where RUKDBH = '" + rukdbh + "'");
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).get("KUAIJRQ") != null && !"".equals(list.get(i).get("KUAIJRQ")))
					result++;
			}
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	public JSONObject getRanlcgrk_WRK_MX2(Map<String, Object> map) {
		String rukdbh = map.get("rukdbh").toString();

		JSONObject jsonObject = new JSONObject();

		try {
			if (!rukdbh.equals("")) {
				String sql = "select HANGH,KUCWZ,KUCWL,YUNDSL,YANSSL,RUKSL,JIESSL,JINE,RUKDBH,TIAOZJE,ZHUANGT from RL_KC_CHURKDB where RUKDBH ='"+ rukdbh + "'";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
				JSONArray jsonArray = new JSONArray();
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> maps = list.get(i);
					Map<String, Object> rtnMap = new HashMap<String, Object>();
					rtnMap.put("hangh", maps.get("HANGH"));
					rtnMap.put("kucwz", maps.get("KUCWZ"));
					rtnMap.put("kucwl", maps.get("KUCWL"));
					double shul = 0;
					if (maps.get("YUNDSL") != null)
						shul += Double.parseDouble(maps.get("YUNDSL").toString());

					if (maps.get("YANSSL") != null)
						shul += Double.parseDouble(maps.get("YANSSL").toString());

					if (maps.get("RUKSL") != null)
						shul += Double.parseDouble(maps.get("RUKSL").toString());

					if (maps.get("JIESSL") != null)
						shul += Double.parseDouble(maps.get("JIESSL").toString());

					rtnMap.put("shul", df.format(shul));

					double jine = Double.parseDouble(maps.get("JINE").toString());
					double tiaozje = Double.parseDouble(maps.get("TIAOZJE").toString());
					rtnMap.put("jine", df.format(jine));
					rtnMap.put("tiaozje", df.format(tiaozje));
					rtnMap.put("zongje", df.format(tiaozje + jine));
					rtnMap.put("zhuangt", maps.get("ZHUANGT"));

					jsonArray.add(rtnMap);
				}

				// 查询入库单合计数
				String sumSql = "select sum(JINE)+sum(TIAOZJE) JINE,sum(YUNDSL) YUNDSL,sum(YANSSL) YANSSL,sum(RUKSL) RUKSL,sum(JIESSL) JIESSL,HUOZ,KUCZZ,ZHUANGT,YEWLX from RL_KC_CHURKDB where RUKDBH = '"
						+ rukdbh + "' group by HUOZ,KUCZZ,ZHUANGT,YEWLX";
				Map<String, Object> sumMap = jdbcTemplate.queryForMap(sumSql);

				jsonObject.put("rukdbh", rukdbh);
				jsonObject.put("zongje", df.format(Double.parseDouble(sumMap.get("JINE").toString())));
				double zongsl = 0;
				if (sumMap.get("YUNDSL") != null)
					zongsl += Double.parseDouble(sumMap.get("YUNDSL").toString());
				if (sumMap.get("YANSSL") != null)
					zongsl += Double.parseDouble(sumMap.get("YANSSL").toString());
				if (sumMap.get("RUKSL") != null)
					zongsl += Double.parseDouble(sumMap.get("RUKSL").toString());
				if (sumMap.get("JIESSL") != null)
					zongsl += Double.parseDouble(sumMap.get("JIESSL").toString());
				jsonObject.put("zongsl", df.format(zongsl));
				jsonObject.put("kuczz", sumMap.get("KUCZZ"));
				jsonObject.put("huoz", sumMap.get("HUOZ"));
				jsonObject.put("zhuangt", sumMap.get("ZHUANGT"));
				jsonObject.put("yewlx", sumMap.get("YEWLX"));
				jsonObject.put("list", jsonArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;
	}








	@Override
	public void saveQitrk(JSONObject d) {
		Map<String,Object> rukd=d.getJSONObject("rukd");
		List<Map<String,Object>> rukdList=d.getJSONArray("rukdList");
		String yewlx=rukd.get("yewlx").toString();
		int i=1;
		for (Map<String, Object> rukdh : rukdList) {
			rukdh.put("hangh", i);
			rukdh.putAll(rukd);
			if(rukdh.get("id")==null){
				rukdh.put("id",	 Sequence.nextId());
				qitrkDao.isertYunzf(rukdh);
				if(yewlx.equals("13")){
					qitrkDao.insertGXChurkdbYunsdjb(rukdh);
				}
			}else{
				qitrkDao.updateYunzf(rukdh);
			}
			i++;
		}
		
	}

	@Override
	public void deleteYunzf(String id) {
		qitrkDao.deleteYunzf(id);
	}

}
