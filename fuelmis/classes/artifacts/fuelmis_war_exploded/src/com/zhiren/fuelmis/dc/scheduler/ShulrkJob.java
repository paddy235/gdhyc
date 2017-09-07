package com.zhiren.fuelmis.dc.scheduler;

import java.util.*;

import com.zhiren.fuelmis.dc.dao.rucsl.ShulshDao;
import com.zhiren.fuelmis.dc.service.common.ILogService;
import com.zhiren.fuelmis.dc.service.kucgl.ICaigrkService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;
import com.zhiren.fuelmis.dc.utils.DateUtil.DateFormatType;

import net.sf.json.JSONArray;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 陈宝露
 */
@Component("shulrkJob")
public class ShulrkJob {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static Logger logger = LogManager.getLogger(ShulrkJob.class);
	@Autowired
	private ShulshDao shulshDao;

	public void execute() {
		logger.info("***************************自动入库开始************************");
		String sql = "SELECT DISTINCT C.SAMCODE\n" 
				+ "  FROM RL_YS_CHEPB C, RL_YS_CHEPB_QC QC, rl_ys_chepb_sp sp\n"
				+ " WHERE C.ID = QC.CHEPB_ID\n" 
				+ "   AND SUBSTR(QC.QINGCSJ, 0, 10) >= '2016-05-01'\n"
				+ "   and c.id not in (select g.yuandj_id from gx_churkdb_yunsdj g where g.yuandjlx = 1)\n"
				+ "   and samcode is not null \n"
				+ "   and c.id = sp.chepb_id  ";
		logger.info(sql);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		List<Object> samcodeList=new ArrayList<Object>();
		for (Map<String,Object> sam:list ) {
			samcodeList.add(sam.get("SAMCODE"));
		}
		this.ruk(samcodeList);
		logger.info("***************************自动入库结束************************");
	}

	private String createRukdbh() {
		String date = DateUtil.format(new Date(), DateFormatType.SIMPLE_TYPE);
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

	public String ranlcgrk(String ids, String dcid, int yewlx) {
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
								"select distinct fuid as ID from RL_KC_KUCWZB where KUCZZ_ID in (select ID from RL_KC_KUCZZB where SUOSDW = '515') and KUCWZMS = '" + chepmx.get("MEIC") + "'",
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
	public void ruk(List<Object> samcodes){
		if (samcodes != null && samcodes.size() > 0) {
			for (int i = 0; i < samcodes.size(); i++) {
				String samecode = samcodes.get(i).toString();
				String rukdbh = ranlcgrk(samecode, "515", 1);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samcode", samecode);
				if (rukdbh == null) {
					JSONArray jsonArray = new JSONArray();
					jsonArray.add(0);
				} else {
					map.put("rukdbh", rukdbh);
					// --------------------------------------------------------------------------------------------------
					// 查询是否已经化验
					String huaydid = shulshDao.isHuay(map);
					map.put("rukdbh", rukdbh);
					shulshDao.deleteHuaybgb(map);
					if (huaydid != null) {
						// 新增化验报告及关系表
						shulshDao.insertHuaybgb(map);
					} else {
						shulshDao.insertHuaybgbWithOtherWay(map);
					}
					shulshDao.insertGX_CHURUK_HUAYBH(map);
					// --------------------------------------------------------------------------------------------------
				}

			}
		}
	}

}
