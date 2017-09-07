package com.zhiren.fuelmis.dc.service.impl.kucgl.rukgl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.dao.kucgl.CaigrkDao;
import com.zhiren.fuelmis.dc.dao.kucgl.KucsscbDao;
import com.zhiren.fuelmis.dc.dao.kucgl.rukgl.YunzfrkDao;
import com.zhiren.fuelmis.dc.entity.kucgl.ChurkBean;
import com.zhiren.fuelmis.dc.entity.kucgl.Shiscbhs;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
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
public class ZafrkServiceImpl implements IYunzfrkService {

	@Autowired
	private YunzfrkDao yunzfrkDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;


	private static final DecimalFormat df = new DecimalFormat("0.00");

	@Override
	public JSONObject getChurkdList(Map<String, Object> map) {
		List<Map<String, Object>> list = yunzfrkDao.getChurkdList(map);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> rtnMap = list.get(i);
			double shul = 0;
			if (rtnMap.get("YUNDSL") != null)
				shul += Double.parseDouble(rtnMap.get("YUNDSL").toString());

			if (rtnMap.get("YANSSL") != null)
				shul += Double.parseDouble(rtnMap.get("YANSSL").toString());

			if (rtnMap.get("RUKSL") != null)
				shul += Double.parseDouble(rtnMap.get("RUKSL").toString());

			if (rtnMap.get("JIESSL") != null)
				shul += Double.parseDouble(rtnMap.get("JIESSL").toString());
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("rukdbh", rtnMap.get("RUKDBH"));
			maps.put("kuczz", rtnMap.get("KUCZZ"));
			maps.put("yewlx", rtnMap.get("YEWLX"));
			maps.put("zhuangt", rtnMap.get("ZHUANGT"));
			maps.put("huoz", rtnMap.get("HUOZ"));
			maps.put("shul", df.format(shul));
			maps.put("jine", df.format(Double.parseDouble(rtnMap.get("JINE").toString())));
			rtnList.add(maps);
		}
		jsonMap.put("data", rtnList);
		JSONObject result = JSONObject.fromObject(jsonMap);

		return result;
	}

	@Override
	public JSONObject getYansmx(Map<String, Object> map) {
		List<Map<String, Object>> list = yunzfrkDao.getYansmx(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> rtnMap = list.get(i);
			objs[i] = new Object[] { rtnMap.get("SAMCODE"), rtnMap.get("GONGYSMC"),
					rtnMap.get("B_CHEPH").toString() + "-" + rtnMap.get("E_CHEPH").toString(),
					rtnMap.get("CAOZSJ").toString(), rtnMap.get("CHES").toString(), rtnMap.get("JINGZ"),
					rtnMap.get("PIAOZ"), rtnMap.get("SAMCODE") };
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);

		return result;
	}

	@Override
	public JSONArray ranlcgrk(String ids, Diancxx diancxx, int yewlx) {
		String idList = jdbcTemplate.queryForObject("select wm_concat(ID) from RL_YS_CHEPB where SAMCODE = " + ids,
				String.class);
		String rukdbh = createRukdbh();
		if (yewlx == 1) {
			if (idList.length() > 0) {
				String[] arrId = idList.split(",");
				String hangh_str = jdbcTemplate.queryForObject("select max(HANGH) from RL_KC_CHURKDB", String.class);
				int hangh = 0;
				if (hangh_str != null) {
					hangh = Integer.parseInt(hangh_str);
				}
				for (int i = 0; i < arrId.length; i++) {
					// 计算行号
					hangh = hangh + 1;
					// 获取验收明细
					Map<String, Object> chepmx = jdbcTemplate
							.queryForMap("select  c.id, c.xuh, c.cheph, c.piaojh, c.maoz, c.piz, c.piaoz,\n"
									+ "c.meikmc, c.meikxxb_id, c.gongysmc, c.gongysb_id, c.caigddb_id, c.faz, c.faz_id, c.daoz, c.daoz_id,\n"
									+ "c.jihkj, c.jihkjb_id, c.pinz, c.pinzb_id, c.yunsfs, c.yunsfsb_id, c.diancxxb_id, c.caozlx, c.caozry,\n"
									+ "c.caozsj, c.xiehsj, c.samcode, c.yingd, c.yingk, c.yuns, c.koud, c.kous, c.kouz, c.koum, c.zongkd,\n"
									+ "c.kuid, c.meic, c.sanfsl, c.chec, P.MINGC PMINGC \n"
									+ " from RL_YS_CHEPB C,PINZB P \n" + " where C.PINZB_ID = P.ID and C.ID = "
									+ arrId[i]);
					String gongys_id = chepmx.get("GONGYSB_ID").toString();
					String yunsfs = chepmx.get("YUNSFSB_ID").toString();
					String pinzbmc = chepmx.get("PMINGC").toString();
					String pinzb_id = chepmx.get("pinzb_id").toString();
					String meikxxb_id = chepmx.get("meikxxb_id").toString();
					String maoz = chepmx.get("maoz").toString();
					String piz = chepmx.get("piz").toString();
					String piaoz = chepmx.get("piaoz").toString();

					String yewrq = "";
					if (yunsfs.equals("2")) {
						yewrq = jdbcTemplate.queryForObject(
								"select QINGCSJ from RL_YS_CHEPB_QC where CHEPB_ID = " + arrId[i], String.class);
					}
					// 获取库存位置
					String kucwz = "0";
					kucwz = jdbcTemplate
							.queryForObject(
									"select ID from RL_KC_KUCWZB where KUCZZ_ID in (select ID from RL_KC_KUCZZB where SUOSDW = "
											+ diancxx.getId() + ") and KUCWZMS = '" + chepmx.get("MEIC") + "'",
									String.class);

					// 获取采购订单行
					List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
					String kucwl = "0";
					double meij = 0;
					double hansmj = 0;
					String caigddSql = "select c.id, c.bianh, c.ou, c.gongys_id, c.gonysmc, c.kaissj, c.jiessj, c.caozry, c.caozsj,\n"
							+ "	c.hetb_id, c.kouj_id, c.diancxxb_id, c.caigddlx, c.dingdrq,s.id, s.caigddb_id, s.xuh, s.shul, s.zhil,\n"
							+ "	s.pingcj, s.yunzf, s.daocj, s.yunsfs_id, s.shuljsfs, s.jijfs_id, s.gongys_id, s.gongysmc,\n"
							+ "	s.diancxxb_id, s.huow_id, s.huowmc, s.huowgg\n"
							+ " from RL_HT_CAIGDDB C,RL_HT_CAIGDDB_SUB S \n" + " where C.ID = S.CAIGDDB_ID"
							+ " and C.GONGYS_ID = " + gongys_id + " and S.YUNSFS_ID = " + yunsfs
							+ " and S.HUOW_ID = (SELECT ID FROM RL_KC_KUCWLB WHERE WUZMC = '" + pinzbmc + "')"
							+ " and '" + yewrq.substring(0, 10) + "' between C.KAISSJ and C.JIESSJ order by C.ID desc";
					lst = jdbcTemplate.queryForList(caigddSql);
					if (lst != null && lst.size() > 0) {
						Map<String, Object> caigdd = lst.get(0);
						// 库存物料
						kucwl = caigdd.get("HUOW_ID").toString();
						meij = Double.parseDouble(caigdd.get("DAOCJ").toString()) / 1.17;
						hansmj = Double.parseDouble(caigdd.get("DAOCJ").toString());
					}

					// 计算金额
					double yanssl = Double.parseDouble(chepmx.get("MAOZ").toString())
							- Double.parseDouble(chepmx.get("PIZ").toString());
					double jine = yanssl * meij;
					double hansje = hansmj * yanssl;

					String jizrq = DateUtil.format(new Date(), DateFormatType.SIMPLE_TYPE);
					// 插入RL_KC_CHURKDB
					StringBuffer sb = new StringBuffer("begin \n");
					sb.append(
							"insert into RL_KC_CHURKDB(ID,RUKDBH,ZUZ,KUCZZ,KUCWZ,KUCWL,HUOZ,JINE,YANSSL,HANGH,ZHUANGT,YEWLX,YEWRQ,\n");
					sb.append("CHURKLX,GONGYSB_ID,MEIKXXB_ID,PINZB_ID,MAOZ,PIZ,PIAOZ,HANSJE,jizrq) values( \n");
					sb.append(Sequence.nextId() + ",'" + rukdbh + "', 515,515 ," + kucwz + "," + kucwl + ",515 ,");
					sb.append("round(" + jine + ",2)," + yanssl + "," + hangh + ",-1," + yewlx + ",'" + jizrq + "',\n");
					sb.append("'入库'," + gongys_id + "," + meikxxb_id + "," + pinzb_id + "," + maoz + "," + piz + ","
							+ piaoz + "," + hansje + ",'" + jizrq + "') ;\n");

					// 插入GX_CHURKDB_YUNSDJ
					if (lst != null && lst.size() > 0) {
						Map<String, Object> caigdd = lst.get(0);
						sb.append("insert into GX_CHURKDB_YUNSDJ(RUKDBH,YUANDJ_ID,YUANDJLX,HANGH) values('" + rukdbh
								+ "'," + caigdd.get("CAIGDDB_ID") + ",2," + hangh + ") ;\n");
					}

					sb.append("insert into GX_CHURKDB_YUNSDJ(RUKDBH,YUANDJ_ID,YUANDJLX,HANGH) values('" + rukdbh + "',"
							+ arrId[i] + ",1," + hangh + ") ;\n");

					sb.append("end ;\n");

					jdbcTemplate.update(sb.toString());

				}
			}
		} else if (yewlx == 2) {
			if (idList.length() > 0) {
				String[] arrId = idList.split(",");
				// 计算行号
				String hangh_str = jdbcTemplate.queryForObject("select max(HANGH) from RL_KC_CHURKDB", String.class);
				int hangh = 0;
				if (hangh_str != null) {
					hangh = Integer.parseInt(hangh_str);
				}

				for (int i = 0; i < arrId.length; i++) {
					hangh = hangh + 1;
					// 获取库存位置
					String kucwz = "0";

					kucwz = jdbcTemplate.queryForObject(
							"select min(ID) from RL_KC_KUCWZB where KUCZZ_ID in (select ID from RL_KC_KUCZZB where SUOSDW = "
									+ diancxx.getId() + ")",
							String.class);

					// 获取验收明细
					Map<String, Object> chepmx = jdbcTemplate
							.queryForMap("select * from RL_YS_CHEPB where ID = " + arrId[i]);
					String gongys_id = chepmx.get("GONGYSB_ID").toString();
					String yunsfs = chepmx.get("YUNSFSB_ID").toString();
					String yewrq = "";
					if (yunsfs.equals("2")) {
						yewrq = jdbcTemplate.queryForObject(
								"select QINGCSJ from RL_YS_CHEPB_QC where CHEPB_ID = " + arrId[i], String.class);
					}
					// 获取采购订单行
					List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
					String kucwl = "0";
					double yunj = 0;

					String caigddSql = "select C.*,S.* from RL_HT_CAIGDDB C,RL_HT_CAIGDDB_SUB S where C.ID = S.CAIGDDB_ID"
							+ " and C.GONGYS_ID = " + gongys_id + " and S.YUNSFS_ID = " + yunsfs + " and '" + yewrq
							+ "' between C.KAISSJ and C.JIESSJ order by C.ID desc";
					lst = jdbcTemplate.queryForList(caigddSql);
					if (lst != null && lst.size() > 0) {
						Map<String, Object> caigdd = lst.get(0);
						// 库存物料
						kucwl = caigdd.get("HUOW_ID").toString();
						yunj = Double.parseDouble(caigdd.get("YUNZF").toString());
					}

					// 计算金额
					double yanssl = Double.parseDouble(chepmx.get("MAOZ").toString())
							- Double.parseDouble(chepmx.get("PIZ").toString());
					double jine = yanssl * yunj;
					// 插入RL_KC_CHURKDB
					String sql = "insert into RL_KC_CHURKDB(ID,RUKDBH,ZUZ,KUCZZ,KUCWZ,KUCWL,HUOZ,JINE,YANSSL,HANGH,ZHUANGT,YEWLX,YEWRQ) values("
							+ Sequence.nextId() + ",'" + rukdbh + "'," + diancxx.getId() + ",0," + kucwz + "," + kucwl
							+ "," + diancxx.getId() + "," + jine + "," + yanssl + "," + hangh + ",-1," + yewlx + ",'"
							+ DateUtil.format(new Date(), DateFormatType.SIMPLE_TYPE) + "')";
					jdbcTemplate.update(sql);
					// 插入GX_CHURKDB_YUNSDJ
					if (lst != null && lst.size() > 0) {
						Map<String, Object> caigdd = lst.get(0);
						String sqlss = "insert into GX_CHURKDB_YUNSDJ(RUKDBH,YUANDJ_ID,YUANDJLX,HANGH) values('"
								+ rukdbh + "'," + caigdd.get("ID") + ",2," + hangh + ")";
						jdbcTemplate.update(sqlss);
					}
					String sqls = "insert into GX_CHURKDB_YUNSDJ(RUKDBH,YUANDJ_ID,YUANDJLX,HANGH) values('" + rukdbh
							+ "'," + arrId[i] + ",1," + hangh + ")";
					jdbcTemplate.update(sqls);

				}
			}
		}

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(rukdbh);
		return jsonArray;
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
	public JSONObject getRanlcgrk_WRK_MX(Map<String, Object> map) {
		String rukdbh = map.get("rukdbh").toString();

		JSONObject jsonObject = new JSONObject();

		try {
			if (!rukdbh.equals("")) {
				String sql = "select id,HANGH,KUCWZ,KUCWL,YUNDSL,YANSSL,RUKSL,JIESSL,JINE,RUKDBH,YEWRQ,ZHUANGT from RL_KC_CHURKDB where RUKDBH ='"
						+ rukdbh + "'";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
				JSONArray jsonArray = new JSONArray();
				for (int i = 0; i < list.size(); i++) {
					try {
						Map<String, Object> maps = list.get(i);
						Map<String, Object> rtnMap = new HashMap<String, Object>();
						rtnMap.put("hangh", maps.get("HANGH"));
						rtnMap.put("kucwz", maps.get("KUCWZ"));
						rtnMap.put("kucwl", maps.get("KUCWL"));
						rtnMap.put("yewrq", maps.get("YEWRQ"));
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
						rtnMap.put("jine", df.format(jine));

						rtnMap.put("danj", df.format((jine / shul)));

						rtnMap.put("zhuangt", maps.get("ZHUANGT"));

						// 查询采购订单
						try {
							String caigdd_sql = "select YUANDJ_ID from GX_CHURKDB_YUNSDJ where YUANDJLX = 2 and RUKDBH = '"
									+ maps.get("RUKDBH") + "' and HANGH = " + maps.get("HANGH");
							String caigdd_id = jdbcTemplate.queryForObject(caigdd_sql, String.class);
							String caigddSql = "select G.MINGC GONGYS_ID,C.KAISSJ,C.JIESSJ,Y.MINGC YUNSFS_ID,L.WUZMC HUOW_ID"
									+ " from RL_HT_CAIGDDB C,GONGYSB G,RL_HT_CAIGDDB_SUB S,YUNSFSB Y,RL_KC_KUCWLB L"
									+ " where C.ID = S.CAIGDDB_ID and "
									+ " C.GONGYS_ID = G.ID and S.YUNSFS_ID = Y.ID and S.HUOW_ID = L.ID "
									+ " and C.ID = " + caigdd_id;
							Map<String, Object> queryMap = jdbcTemplate.queryForMap(caigddSql);
							if (queryMap != null && queryMap.size() != 0) {
								rtnMap.put("caigdd",
										"供应商：" + queryMap.get("GONGYS_ID") + "<br>运输方式：" + queryMap.get("YUNSFS_ID")
												+ "&nbsp;&nbsp;&nbsp;&nbsp;货名：" + queryMap.get("HUOW_ID") + "<br>时间："
												+ queryMap.get("KAISSJ") + "至" + queryMap.get("JIESSJ"));
							}
						} catch (Exception e) {
						}
						jsonArray.add(rtnMap);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// 查询入库单合计数
				try {
					String sumSql = "select sum(JINE) JINE,sum(YUNDSL) YUNDSL,sum(YANSSL) YANSSL,sum(RUKSL) RUKSL,sum(JIESSL) JIESSL,HUOZ,KUCZZ,ZHUANGT from RL_KC_CHURKDB where RUKDBH = '"
							+ rukdbh + "' group by HUOZ,KUCZZ,ZHUANGT";
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
				} catch (Exception e) {
				}
				jsonObject.put("list", jsonArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

	@Override
	public JSONArray saveRukd(String rukdbh, String kuczz, String huoz, String rukdList, int yewlx) {
		int result = 0;
		JSONArray jsonArray = JSONArray.fromObject(rukdList);
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<Map<String, Object>> list = JSONArray.toList(jsonArray, Map.class);
		if (yewlx == 1) {
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					result += jdbcTemplate.update("update RL_KC_CHURKDB set kucwz = " + map.get("kucwz") + ",kucwl = "
							+ map.get("kucwl") + ",kuczz = " + kuczz + ",huoz = " + huoz + ",yewlx = 1 where rukdbh = '"
							+ rukdbh + "' and hangh =" + map.get("hangh"));
				}
			}
		} else if (yewlx == 2) {
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					result += jdbcTemplate.update("update RL_KC_CHURKDB set kucwz = " + map.get("kucwz") + ",kucwl = "
							+ map.get("kucwl") + ",kuczz = " + kuczz + ",huoz = " + huoz + ",yewlx = 2 where rukdbh = '"
							+ rukdbh + "' and hangh =" + map.get("hangh"));
				}
			}
		} else {
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					if (map.get("hangh") == null || map.get("hangh").equals("")) {
						String sql = "insert into RL_KC_CHURKDB(ID,RUKDBH,ZUZ,KUCZZ,KUCWZ,KUCWL,HUOZ,JINE,TIAOZJE,YEWLX,"
								+ "RUKSL,HANGH,ZHUANGT,YEWRQ) values (" + Sequence.nextId() + ",'" + rukdbh + "',"
								+ huoz + "," + kuczz + "," + map.get("kucwz") + "," + map.get("kucwl") + "," + huoz
								+ "," + (map.get("jine") == null ? "0" : map.get("jine")) + ","
								+ (map.get("tiaozje") == null ? "0" : map.get("tiaozje")) + "," + yewlx + ","
								+ (map.get("shul") == null ? "0" : map.get("shul")) + "," + (i + 1) + ",-1,'"
								+ DateUtil.format(new Date(), DateFormatType.SIMPLE_TYPE) + "')";
						result += jdbcTemplate.update(sql);
					} else {
						String sql = "update RL_KC_CHURKDB set kucwz = " + map.get("kucwz") + ",kucwl = "
								+ map.get("kucwl") + ",kuczz = " + kuczz + ",huoz = " + huoz + ",yewlx = " + yewlx
								+ ",jine = "
								+ (map.get("jine") == null || "".equals(map.get("jine").toString()) ? "0"
										: map.get("jine"))
								+ ",tiaozje = "
								+ (map.get("tiaozje") == null || "".equals(map.get("tiaozje").toString()) ? "0"
										: map.get("tiaozje"))
								+ ",ruksl = "
								+ (map.get("shul") == null || "".equals(map.get("shul").toString()) ? "0"
										: map.get("shul"))
								+ " where rukdbh = '" + rukdbh + "' and hangh =" + map.get("hangh");
						result += jdbcTemplate.update(sql);
					}
				}
			}
		}

		JSONArray array = new JSONArray();
		array.add(result);
		return array;
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
					"update RL_KC_CHURKDB set jizrq = '" + DateUtil.format(new Date(), DateFormatType.SIMPLE_TYPE)
							+ "'," + "yewrq = '" + DateUtil.format(new Date(), DateFormatType.SIMPLE_TYPE)
							+ "',zhuangt = 1 where rukdbh = '" + rukdbh + "'");

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
				String sql = "select HANGH,KUCWZ,KUCWL,YUNDSL,YANSSL,RUKSL,JIESSL,JINE,RUKDBH,TIAOZJE,ZHUANGT from RL_KC_CHURKDB where RUKDBH ='"
						+ rukdbh + "'";
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
	public JSONArray ranlhyrk(String huayd_id) {
		JSONArray jsonArray = new JSONArray();
		// 根据化验单ID找到对应的车皮信息
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("SELECT * FROM GX_CHEP_HUAYD WHERE HUAYD_ID = " + huayd_id);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				// 获取车皮数量、硫分、热值
				Map<String, Object> mapChep = jdbcTemplate.queryForMap(
						"SELECT (C.MAOZ-C.PIZ-c.zongkd) SHUL,(Z.STAD*(100-Z.MT)/(100-Z.MAD)) STAR,(Z.QNET_AR*1000/4.1816) QNET_AR "
								+ " FROM RL_YS_CHEPB C,RL_HY_HUAYDB Z,GX_CHEP_HUAYD G"
								+ " WHERE C.ID = G.CHEPB_ID AND Z.HUAYD_ID = G.HUAYD_ID" + " AND C.ID = "
								+ list.get(i).get("CHEPB_ID") + " AND Z.ID = " + list.get(i).get("HUAYD_ID"));
				// 调用其他方法，计算价格
				List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("huaydid", list.get(i).get("HUAYD_ID"));
				param.put("shul", mapChep.get("SHUL"));
				param.put("star", mapChep.get("STAR"));
				param.put("qnetar", mapChep.get("QNET_AR"));
				params.add(param);
				double jiag = 0;
				// 根据车皮找到对应的入库单及行号
				Map<String, Object> mapRukd = jdbcTemplate
						.queryForMap("SELECT * FROM GX_CHURKDB_YUNSDJ WHERE YUANDJ_ID = " + list.get(i).get("CHEPB_ID")
								+ " AND YUANDJLX = 1"
								+ " AND RUKDBH IN (SELECT RUKDBH FROM RL_KC_CHURKDB WHERE YEWLX = 1)");
				// 更新RL_KC_CHURKDB
				Map<String, Object> rukd = jdbcTemplate.queryForMap("sELECT * FROM RL_KC_CHURKDB WHERE RUKDBH = '"
						+ mapRukd.get("RUKDBH") + "' AND HANGH = " + mapRukd.get("HANGH"));
				// 取当前打开的会计期
				// String kuaijrq = jdbcTemplate
				// .queryForObject(
				// "select kuaijrq from rl_kc_kuaijrqb where zhuangt = '启用'",
				// String.class);
				String sql = "insert into RL_KC_CHURKDB(ID,RUKDBH,ZUZ,KUCZZ,KUCWZ,KUCWL,HUOZ,JINE,YANSSL,HANGH,ZHUANGT,YEWLX,YEWRQ,JIZRQ) values("
						+ Sequence.nextId() + ",'" + rukd.get("RUKDBH") + "'," + rukd.get("ZUZ") + ","
						+ rukd.get("KUCZZ") + "," + rukd.get("KUCWZ") + "," + rukd.get("KUCWL") + "," + rukd.get("HUOZ")
						+ "," + (jiag - Double.parseDouble(rukd.get("JINE").toString())) + "," + rukd.get("YANSSL")
						+ "," + rukd.get("HANGH") + "," + rukd.get("ZHUANGT") + "," + rukd.get("YEWLX") + ",'"
						+ rukd.get("YEWRQ") + "','"
						/* + kuaijrq + "','" */ + rukd.get("JIZRQ") + "')";
				int result = jdbcTemplate.update(sql);
				jsonArray.add(result);
			}
		}
		return jsonArray;
	}

	@Override
	public JSONObject getCaigddList(Map<String, Object> map) {
		List<Map<String, Object>> list = yunzfrkDao.getCaigddList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> rtnMap = list.get(i);
			objs[i] = new Object[] { rtnMap.get("ID"), rtnMap.get("BIANH"), rtnMap.get("GONGYS"), rtnMap.get("KAISSJ"),
					rtnMap.get("JIESSJ"), rtnMap.get("JIHKJ"), rtnMap.get("CAIGDDLX"), rtnMap.get("YUNSFS") };
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);

		return result;
	}

	@Override
	public JSONArray editCaigdd(String rukdbh, String caigdd_id, String yewlx) {
		try {
			// 重新计算价格
			String danj = "";
			if (yewlx.equals("1")) {
				danj = jdbcTemplate.queryForObject(
						"select DAOCJ from RL_HT_CAIGDDB_SUB where CAIGDDB_ID = " + caigdd_id, String.class);
			} else {
				danj = jdbcTemplate.queryForObject(
						"select YUNZF from RL_HT_CAIGDDB_SUB where CAIGDDB_ID = " + caigdd_id, String.class);
			}
			// 查询库存物料
			String kucwl = jdbcTemplate.queryForObject(
					"select huow_id from rl_ht_caigddb_sub where caigddb_id = " + caigdd_id, String.class);
			// 查询所有入库单
			List<Map<String, Object>> lst = jdbcTemplate
					.queryForList("select * from RL_KC_CHURKDB where RUKDBH = '" + rukdbh + "'");
			// 遍历所有入库单，修改每一个入库单行
			for (int i = 0; i < lst.size(); i++) {
				Map<String, Object> map = jdbcTemplate
						.queryForMap("select YUNDSL,YANSSL,RUKSL,JIESSL from RL_KC_CHURKDB where RUKDBH = '" + rukdbh
								+ "' and HANGH = " + lst.get(i).get("HANGH"));
				double shul = 0;
				if (map.get("YUNDSL") != null)
					shul += Double.parseDouble(map.get("YUNDSL").toString());
				if (map.get("YANSSL") != null)
					shul += Double.parseDouble(map.get("YANSSL").toString());
				if (map.get("RUKSL") != null)
					shul += Double.parseDouble(map.get("RUKSL").toString());
				if (map.get("JIESSL") != null)
					shul += Double.parseDouble(map.get("JIESSL").toString());
				jdbcTemplate.update("update RL_KC_CHURKDB set JINE = " + shul + "*" + danj + ",kucwl = " + kucwl
						+ " where RUKDBH = '" + rukdbh + "' and HANGH = " + lst.get(i).get("HANGH"));
				// 更新GX_CHURKDB_YUNSDJ中的采购订单行
				int count = jdbcTemplate
						.queryForInt("select count(1) from GX_CHURKDB_YUNSDJ where YUANDJLX = 2 and RUKDBH = '" + rukdbh
								+ "' and HANGH = " + lst.get(i).get("HANGH"));
				if (count > 0) {
					jdbcTemplate.update("update GX_CHURKDB_YUNSDJ set YUANDJ_ID = " + caigdd_id
							+ " where YUANDJLX = 2 and RUKDBH = '" + rukdbh + "' and HANGH = "
							+ lst.get(i).get("HANGH"));
				} else {
					jdbcTemplate.update("insert into GX_CHURKDB_YUNSDJ(RUKDBH,YUANDJ_ID,YUANDJLX,HANGH) values ('"
							+ rukdbh + "'," + caigdd_id + ",2," + lst.get(i).get("HANGH") + ")");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(rukdbh);
		return jsonArray;
	}


	@Override
	public JSONObject getChurkdList2(Map<String, Object> map) {
		List<Map<String, Object>> list = yunzfrkDao.getChurkdList2(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> rtnMap = list.get(i);
			double shul = 0;
			if (rtnMap.get("YUNDSL") != null)
				shul += Double.parseDouble(rtnMap.get("YUNDSL").toString());

			if (rtnMap.get("YANSSL") != null)
				shul += Double.parseDouble(rtnMap.get("YANSSL").toString());

			if (rtnMap.get("RUKSL") != null)
				shul += Double.parseDouble(rtnMap.get("RUKSL").toString());

			if (rtnMap.get("JIESSL") != null)
				shul += Double.parseDouble(rtnMap.get("JIESSL").toString());
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("rukdbh", rtnMap.get("RUKDBH"));
			maps.put("kuczz", rtnMap.get("KUCZZ"));
			maps.put("yewlx", rtnMap.get("YEWLX"));
			maps.put("zhuangt", rtnMap.get("ZHUANGT"));
			maps.put("huoz", rtnMap.get("HUOZ"));
			maps.put("shul", df.format(shul));
			maps.put("jine", df.format(Double.parseDouble(rtnMap.get("JINE").toString())));
			rtnList.add(maps);
		}
		jsonMap.put("data", rtnList);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	public JSONObject getYansmxMX(Map<String, Object> map) {
		List<Map<String, Object>> list = yunzfrkDao.getYansmxMX(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> rtnMap = list.get(i);
			objs[i] = new Object[] { rtnMap.get("GONGYSMC"), rtnMap.get("CHEPH"), rtnMap.get("CAOZSJ"),
					rtnMap.get("JINGZ"), rtnMap.get("PIAOZ"), "" };
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);

		return result;
	}

}
