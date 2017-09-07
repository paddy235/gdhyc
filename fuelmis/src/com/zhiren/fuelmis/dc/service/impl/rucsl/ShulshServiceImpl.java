package com.zhiren.fuelmis.dc.service.impl.rucsl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zhiren.fuelmis.dc.dao.rucsl.ShulshDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.service.rucsl.IShulshService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.DateUtil.DateFormatType;
import com.zhiren.fuelmis.dc.utils.Sequence;

/**
 * @author 陈宝露
 */

@Service
public class ShulshServiceImpl implements IShulshService {

	@Autowired
	private ShulshDao shulshDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> getFahrq() {

		return null;
	}

	@Override
	public JSONObject getTableData(Map<String, Object> map) {
		List<Map<String, Object>> list = shulshDao.getTableData(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JSONArray jsonArray = new JSONArray();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size() ; i++) {
				Map<String, Object> rtnMap = list.get(i);
				// 查询预警信息
				int count = jdbcTemplate.queryForInt(
						"select count(1) from rl_ys_chepb where samcode = '" + rtnMap.get("SAMCODE") + "'");
				if (count > 10) {
					StringBuffer sb = new StringBuffer();
					sb.append("select avg(t.jingz) jingz from (SELECT * FROM (SELECT a.jingz, ROWNUM RN ");
					sb.append("FROM (SELECT maoz-piz-zongkd jingz FROM rl_ys_chepb where samcode = '");
					sb.append(rtnMap.get("SAMCODE"));
					sb.append("' order by id) A WHERE ROWNUM <= 10)WHERE RN >= 1)t union ");
					sb.append("SELECT jingz FROM (SELECT a.jingz, ROWNUM RN ");
					sb.append("FROM (SELECT maoz-piz-zongkd jingz FROM rl_ys_chepb where samcode = '");
					sb.append(rtnMap.get("SAMCODE"));
					sb.append("' order by id) A )WHERE RN =11");
					List<Map<String, Object>> lst = jdbcTemplate.queryForList(sb.toString());
					if (lst != null && list.size() > 0) {
						double avg = Double.parseDouble(lst.get(0).get("JINGZ").toString());
						double next = Double.parseDouble(lst.get(1).get("JINGZ").toString());
						rtnMap.put("yujts", "前十车的平均净重为" + avg + ",此次净重为" + next + ",请进行核对!");
					}
				}

				jsonArray.add(rtnMap);
			}
		}

		jsonMap.put("data", jsonArray);
		JSONObject result = JSONObject.fromObject(jsonMap);

		return result;
	}

	@Override
	public JSONObject getTableData_MX(Map<String, Object> map) {
		LinkedList<Map<String, Object>> list = shulshDao.getTableData_MX(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> datas = list.get(i);

			objs[i] = new Object[] { (i + 1), datas.get("CHEPH"), datas.get("MAOZ"), datas.get("PIZ"),
					datas.get("PIAOZ"), datas.get("JINGZ"), 0, datas.get("ZONGKD"), datas.get("CAOZRY"),
					datas.get("CAOZSJ") };
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);

		return result;
	}

	@Transactional
	private String createRukdbh() {

		String jians = "select case\n" 
				+ "         when max(rukdbh) is null then\n"
				+ "          'RKD-'||replace(to_char(sysdate, 'yyyy-mm-dd'), '-', '') || '001'\n" 
				+ "         else\n"
				+ "          'RKD-'||to_char(max(rukdbh) + 1)\n" 
				+ "       end rukdbh\n"
				+ "  from (select distinct regexp_substr(rukdbh,'[0-9]+') rukdbh\n"
				+ "          from RL_KC_CHURKDB\n )  "
				+ "   where substr(rukdbh, 0, 8)  = replace(to_char(sysdate, 'yyyy-mm-dd'), '-', '') ";
		List xuh = jdbcTemplate.queryForList(jians);
		return (String) ((Map) xuh.get(0)).get("rukdbh");
	}

	@Transactional
	public String ranlcgrk(String ids, Diancxx diancxx, int yewlx) {
		String idList = jdbcTemplate.queryForObject("select wm_concat(ID) from RL_YS_CHEPB where SAMCODE = '" + ids+"'",
				String.class);
		if (idList == null) {
			return null;
		} 
		String kucwl = "0";
		String kuczz = "0";
		String kucwz = "0";
		String caigdd_id = "";
		String caigdd_sub_id = "";
		double meij = 0;
		double hansmj = 0;
		String caigddSql = "select distinct decode(pp.huoz_id, null, pp.huoz_id, pp.HUOZ_ID) as HUOZ_ID,\n" +
				"                pp.diancxxb_id,\n" + 
				"                pp.caigddb_sub_id,\n" + 
				"                CGDD.ID AS caigddb_id,\n" + 
				"                cp.diancxxb_id kuczz_id,\n" + 
				"                (select fuid\n" + 
				"                   from rl_kc_kucwlb\n" + 
				"                  where wuzmc = cp.pinz\n" + 
				"                    and dianc_id = cp.diancxxb_id) as kucwl_id,\n" + 
				"                DAOCJ\n" + 
				"  from rl_ys_chepb       cp,\n" + 
				"       rl_ys_chepb_qc    qc,\n" + 
				"       rl_ht_caigddppb   pp,\n" + 
				"       RL_HT_CAIGDDB_SUB sub,\n" + 
				"       RL_HT_CAIGDDB CGDD\n" + 
				" where cp.id = qc.chepb_id\n" + 
				"   and cp.meikxxb_id = pp.meikxxb_id\n" + 
				"   and cp.pinzb_id = pp.meiz_id\n" + 
				"   and pp.caigddb_sub_id = sub.id\n" + 
				"   AND SUB.CAIGDDB_ID = CGDD.ID\n" + 
				"   and substr(qc.qingcsj, 0, 10) between pp.qisrq and pp.jiesrq \n"  +
				"   and cp.samcode = '" + ids + "'  ";

		List<Map<String, Object>> list = jdbcTemplate.queryForList(caigddSql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> caigdd = list.get(i);
				kucwl = caigdd.get("KUCWL_ID").toString();
				kuczz = caigdd.get("HUOZ_ID").toString();
				caigdd_id = caigdd.get("CAIGDDB_ID").toString();
				caigdd_sub_id = caigdd.get("caigddb_sub_id").toString();
				meij = Double.parseDouble(caigdd.get("DAOCJ").toString()) / 1.17;
				hansmj = Double.parseDouble(caigdd.get("DAOCJ").toString());				
			}
		} else {

			return null;

		}

		String rukdbh = createRukdbh();

		if (idList.length() > 0) {
			String[] arrId = idList.split(",");
			String hangh_str = jdbcTemplate.queryForObject("select max(HANGH) from RL_KC_CHURKDB", String.class);
			int hangh = 0;
			if (hangh_str != null) {
				hangh = Integer.parseInt(hangh_str);
			}
			for (int i = 0; i < arrId.length; i++) {
				try {
					// 计算行号
					hangh = hangh + 1;
					// 获取验收明细
					Map<String, Object> chepmx = jdbcTemplate.queryForMap("select "
							+ " distinct c.id, c.xuh, c.cheph, c.piaojh, c.maoz, c.piz, c.piaoz, c.meikmc, c.meikxxb_id, c.gongysmc,\n"
							+ " c.gongysb_id, c.caigddb_id, c.faz, c.faz_id, c.daoz, c.daoz_id, c.jihkj, c.jihkjb_id, c.pinz,\n"
							+ " c.pinzb_id, c.yunsfs, c.yunsfsb_id, c.diancxxb_id, c.caozlx, c.caozry, c.caozsj, c.xiehsj, c.zhuangt,\n"
							+ " c.samcode, c.yingd, c.yingk, c.yuns, c.koud, c.kous, c.kouz, c.koum, c.zongkd, c.kuid, c.meic,\n"
							+ " c.sanfsl, c.chec, c.isconverged,P.MINGC PMINGC,substr(qc.Qingcsj,0,10) as daohrq,nvl(bm.huaybm,'') as huaybm  \n"
							+ " from RL_YS_CHEPB C,PINZB P,RL_YS_CHEPB_QC QC,vm_caizhbm bm \n"
							+ " where C.PINZB_ID = P.ID \n" 
							+ "   and c.samcode = bm.caiybm(+) \n"
							+ "   and C.id = QC.CHEPB_ID \n" 
							+ "   and C.ID = " + arrId[i]);
					String yewrq = chepmx.get("daohrq").toString();
					String gongysb_id = chepmx.get("gongysb_id").toString();
					String pinzb_id = chepmx.get("pinzb_id").toString();
					String meikxxb_id = chepmx.get("meikxxb_id").toString();
					String maoz = chepmx.get("maoz").toString();
					String piz = chepmx.get("piz").toString();
					String piaoz = chepmx.get("piaoz").toString();
					String samcode = chepmx.get("samcode").toString();
					String huaybm = chepmx.get("huaybm")==null?"":chepmx.get("huaybm").toString();

					// 获取库存位置
					try {
						kucwz = jdbcTemplate.queryForObject(
								"select distinct fuid as ID from RL_KC_KUCWZB where KUCZZ_ID in (select ID from RL_KC_KUCZZB where SUOSDW = "
										+ diancxx.getId() + ") and KUCWZMS = '" + chepmx.get("MEIC") + "'",
								String.class);
					} catch (Exception e) {

					}

					// 计算金额
					double yanssl = Double.parseDouble(chepmx.get("MAOZ").toString())
							- Double.parseDouble(chepmx.get("PIZ").toString());
					double jine = yanssl * meij;
					double hansje = hansmj * yanssl;
					String jizrq = DateUtil.format(new Date(), DateFormatType.SIMPLE_TYPE);
					// 删除rl_kc_churkdb// 删除gx_churkdb_yuansdj
					this.deleteChurkdbByChepbid(arrId[i]);
					// 插入RL_KC_CHURKDB
					StringBuffer sb = new StringBuffer("begin \n");
					
					//根据车号删除RL_KC_CHURKDB
					//sb.append("-------f \n");
					
					sb.append("insert into RL_KC_CHURKDB(ID,RUKDBH,ZUZ,KUCZZ,KUCWZ,KUCWL,HUOZ,JINE,YANSSL,HANGH,ZHUANGT,YEWLX,YEWRQ,\n");
					sb.append("CHURKLX,GONGYSB_ID,MEIKXXB_ID,PINZB_ID,MAOZ,PIZ,PIAOZ,HANSJE,jizrq,HUAYBH,SAMECODE,shifdr,caigdd_sub_id) values( \n");
					sb.append(Sequence.nextId() + ",'" + rukdbh + "', 515 ," + kuczz + "," + kucwz + "," + kucwl+ ",515,");
					sb.append("round(" + jine + ",2)," + yanssl + "," + hangh + ",1,1,'" + yewrq + "','入库',"+ gongysb_id + "," + meikxxb_id + ",\n");
					sb.append(pinzb_id + "," + maoz + "," + piz + "," + piaoz + "," + hansje + ",'" + jizrq + "','"+ huaybm + "','" + samcode + "',0,"+caigdd_sub_id+") ;\n");
					// 插入GX_CHURKDB_YUNSDJ
					sb.append("insert into GX_CHURKDB_YUNSDJ(RUKDBH,YUANDJ_ID,YUANDJLX,HANGH) values('" + rukdbh + "',"+ caigdd_id + ",2," + hangh + ") ;\n");
					// 插入chepbID
					sb.append("insert into GX_CHURKDB_YUNSDJ(RUKDBH,YUANDJ_ID,YUANDJLX,HANGH) values('" + rukdbh + "',"+ arrId[i] + ",1," + hangh + ") ;\n");
					sb.append("end ;\n");

					jdbcTemplate.update(sb.toString());

				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		}

		return rukdbh;
	}
	public void deleteChurkdbByChepbid(String chepbid) {
		String sql = "select distinct rukdbh from gx_churkdb_yunsdj where yuandj_id=" + chepbid + " and YUANDJLX=1";
		List<Map<String, Object>> rukdbhList = jdbcTemplate.queryForList(sql);
		if (rukdbhList != null && rukdbhList.size() != 0) {
			sql = "begin \n";
			for (Map<String, Object> map : rukdbhList) {
				sql += "delete from rl_kc_churkdb where rukdbh='" + map.get("RUKDBH") + "';";
				sql += "delete from gx_churkdb_yunsdj where rukdbh='" + map.get("RUKDBH") + "';";
			}
			sql += "end;";
			jdbcTemplate.update(sql);
		}
	}
	@Override
	@Transactional
	public JSONArray shenh(Map<String, Object> map) {
		int result = 0;
		try {
			List<Map<String, Object>> list = jdbcTemplate
					.queryForList("SELECT ID FROM RL_YS_CHEPB WHERE SAMCODE= '" + map.get("samcode").toString()+"'");

			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> chepbId = list.get(i);
					map.put("id", Sequence.nextId());
					map.put("chepb_id", chepbId.get("ID"));
					shulshDao.shenh(map);
				}
			}
			String rukdbh = ranlcgrk((String) map.get("samcode"), (Diancxx) map.get("diancxx"), 1);
			if (rukdbh == null) {
				JSONArray jsonArray = new JSONArray();
				jsonArray.add(0);
				return jsonArray;
			} else {
				map.put("rukdbh", rukdbh);
				// 查询是否已经化验
				String huaydid = shulshDao.isHuay(map);
				shulshDao.deleteHuaybgb(map);
				if (huaydid != null) {
					// 新增化验报告及关系表
					shulshDao.insertHuaybgb(map);
				} else {
					shulshDao.insertHuaybgbWithOtherWay(map);
				}
				shulshDao.insertGX_CHURUK_HUAYBH(map);
			}
			// 数量审核成功后，将数据上报至国电电力
			// 上传供应商
			result = 1;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace(System.out);
		}

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	@Transactional
	public JSONArray huit(Map<String, Object> map) {
		int result = 0;
		try {
			List<Map<String, Object>> list = jdbcTemplate
					.queryForList("SELECT ID FROM RL_YS_CHEPB WHERE SAMCODE= '" + map.get("samcode").toString()+"'");
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> chepbId = list.get(i);
					map.put("chepb_id", chepbId.get("ID"));
					shulshDao.huit(map);
				}
			}
			result = 1;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);
		return jsonArray;
	}

	@Override
	@Transactional
	public void insertShulshLogs(Map<String, Object> map) {
//		try {
//			String sql = "insert into rl_ys_chepb_logs select c.*,'" + map.get("YEWHJ") + "','" + map.get("DANQCAOZRIP")
//					+ "','" + map.get("DANQCAOZSJ") + "','" + map.get("DANQCAOZLX") + "','" + map.get("DANQCAOZRY")
//					+ "' from rl_ys_chepb c where samcode='" + map.get("id") + "'";
//			jdbcTemplate.update(sql);
//			String sql1 = "insert into RL_YS_CHEPB_QC_LOGS select q.* from RL_YS_CHEPB_QC q where q.chepb_id in(select id from rl_ys_chepb where samcode='"
//					+ map.get("id") + "')";
//			jdbcTemplate.update(sql1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public List<Map<String, Object>> getJiesxxList(String samcode) {
		return shulshDao.getJiesxxList(samcode);
	}

}
