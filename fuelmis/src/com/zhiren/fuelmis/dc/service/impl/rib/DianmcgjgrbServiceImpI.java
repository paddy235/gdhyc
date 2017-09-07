package com.zhiren.fuelmis.dc.service.impl.rib;

import com.zhiren.fuelmis.dc.dao.rib.DianmcgjgrbDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.rib.IDianmcgjgrbService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.formular.Result;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 陈宝露
 */
@Service
public class DianmcgjgrbServiceImpI implements IDianmcgjgrbService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	private DianmcgjgrbDao guodbbDao;

	
	
	private String getBaseSql(String strDate, String diancxxb_id) {
		String SQL="";
		
			SQL="SELECT distinct KJ.GONGYSB_ID,\n" +
			"       KJ.DQID,\n" + 
			"       KJ.DQMC,\n" + 
			"       SHENGFB.QUANC QUANC,\n" + 
			"       DECODE(KJ.JIHKJB_ID, 1, '重点订货', 3, '重点订货', J.MINGC) TJKJ,\n" + 
			"       KJ.JIHKJB_ID,\n" + 
			"       KJ.PINZB_ID,\n" + 
			"       YUNSFSB_ID,\n" + 
			"       DIANCXXB_ID,\n" + 
			"       KJ.FENX,\n" + 
			"       SL.JINGZ AS JINGZ,\n" + 
			"       SL.YUNS,\n" + 
			"       decode(KJ.FENX,'本月',HC.QICKC,GETQICKCLJ(KJ.ID))QICKC,\n" + 
			"       HC.FADY + HC.GONGRY + HC.QITH + HC.SUNH AS HEJ,\n" + 
			"       HC.FADY,\n" + 
			"       HC.GONGRY,\n" + 
			"       HC.QITH,\n" + 
			"       HC.SUNH,\n" + 
			"       -HC.DIAOCL DIAOCL,\n" + 
			"       HC.SHUIFCTZ SHUIFTZ,\n" + 
			"       HC.PANYK,\n" + 
			"       HC.KUC\n" + 
			"  FROM "+

			"YUESLB SL,\n" + 
			"       YUEHCB HC,\n" + 
			"       (SELECT K.ID,\n" + 
			"               DQ.DQID,\n" + 
			"               DQ.DQMC,\n" + 
			"               K.GONGYSB_ID,\n" + 
			"               K.JIHKJB_ID,\n" + 
			"               K.PINZB_ID,\n" + 
			"               K.YUNSFSB_ID,\n" + 
			"               K.DIANCXXB_ID,\n" + 
			"               NVL('本月', '') AS FENX\n" + 
			"          FROM YUETJKJB K, VWMEIKDQ DQ\n" + 
			"         WHERE substr(K.RIQ,0,7) = '" + strDate + "'\n" + 
			"           AND DQ.ID = K.GONGYSB_ID\n" + 
			"           AND K.DIANCXXB_ID IN (" + diancxxb_id + ")\n" + 
			"        UNION\n" + 
			"        SELECT K.ID,\n" + 
			"               DQ.DQID,\n" + 
			"               DQ.DQMC,\n" + 
			"               K.GONGYSB_ID,\n" + 
			"               K.JIHKJB_ID,\n" + 
			"               K.PINZB_ID,\n" + 
			"               K.YUNSFSB_ID,\n" + 
			"               K.DIANCXXB_ID,\n" + 
			"               NVL('累计', '') AS FENX\n" + 
			"          FROM YUETJKJB K, VWMEIKDQ DQ\n" + 
			"         WHERE substr(K.RIQ,0,7) = '" + strDate + "'\n" + 
			"           AND DQ.ID = K.GONGYSB_ID\n" + 
			"           AND K.DIANCXXB_ID IN (" + diancxxb_id + ")) KJ,\n" + 
			"       JIHKJB J,\n" + 
			"       MEIKDQB,\n" + 
			"       SHENGFB\n"+ 
			" WHERE KJ.ID = SL.YUETJKJB_ID(+) \n";

SQL+="   AND KJ.FENX = SL.FENX(+)\n" + 
"   AND KJ.ID = HC.YUETJKJB_ID(+)\n" + 
"   AND KJ.FENX = HC.FENX(+)\n" + 
"   AND KJ.JIHKJB_ID = J.ID\n" + 
"   AND KJ.DQID = MEIKDQB.ID\n" + 
"   AND MEIKDQB.SHENGFB_ID = SHENGFB.ID";
		return SQL;
	}
	/**
	 * 煤收耗存报表
	 */
	@Override
	public JSONArray getMeishcbb(Map<String, Object> map) {
		/*String Select=null;
		String Having=null;
		String strDate=map.get("riq").toString();
		String diancxxb_id=map.get("dianc").toString();
		String leix=map.get("leix").toString();*/
		String sql=" SELECT * FROM \n" +
				"( SELECT '合计' meikmc, sum(jingz) jingz,\n" +
				"round_new(decode(sum(mt), 0, 0,sum(mt * jingz) / sum(jingz)),2) mt,\n" +
				"round_new(decode(sum(mad), 0, 0,sum(mad * jingz) / sum(jingz)),2) mad,\n" +
				"round_new(decode(sum(ad), 0, 0,sum(ad * jingz) / sum(jingz)),2) ad,\n" +
				"round_new(decode(sum(aar), 0, 0,sum(aar * jingz) / sum(jingz)),2) aar,\n" +
				"round_new(decode(sum(std), 0, 0,sum(std * jingz) / sum(jingz)),2) std,\n" +
				"round_new(decode(sum(vdaf), 0, 0,sum(vdaf * jingz) / sum(jingz)),2) vdaf,\n" +
				"round_new(decode(sum(fcd), 0, 0,sum(fcd * jingz) / sum(jingz)),2) fcd,\n" +
				"round_new(decode(sum(qnet_ar), 0, 0,sum(qnet_ar * jingz) / sum(jingz)),2) rez,\n" +
				"round(decode(sum(jiesjg), 0, 0, sum(jiesjg * jingz) / sum(jingz)), 2) jiesjg,0 yunf,0 zaf,0 hetj,0 kuc,\n" +
				"\n" +
				"round(decode(sum(daocmj), 0, 0, sum(daocmj * jingz) / sum(jingz)), 2) daocmj,\n" +
				"round(decode(sum(daocbmj), 0, 0, sum(daocbmj * jingz) / sum(jingz)), 2) daocbmj,\n" +
				"round(decode(sum(buhsmj), 0, 0, sum(daocmj * jingz) / sum(jingz)), 2) buhsmj,\n" +
				"round(decode(sum(buhsbmdj), 0, 0, sum(buhsbmdj * jingz) / sum(jingz)), 2) buhsbmdj,0 rezc,0 rulbmdj\n" +
				"  FROM(\n" +
				"  SELECT c.meikmc,maoz-piz jingz, h.mt,h.mad,h.ad,h.aar,h.std,h.vdaf,h.fcd,h.qnet_ar,j.jiesjg,(jiesjg) daocmj,\n" +
				"  (j.jiesjg*29.271/h.qnet_ar) daocbmj,(j.jiesjg/1.17) buhsmj,((j.jiesjg/1.17)*29.271/h.qnet_ar) buhsbmdj \n" +
				" FROM rl_ys_chepb c\n" +
				"  INNER JOIN rl_ys_chepb_qc q\n" +
				"  ON c.id = q.chepb_id\n" +
				"  INNER JOIN (SELECT yuanbm samcode,mubbm zhiybm FROM gx_chep_caizhbmb WHERE zhuanhlb_id = 1) gx_samcode_zhiybm\n" +
				"  ON c.samcode = gx_samcode_zhiybm.samcode\n" +
				"  INNER JOIN (SELECT yuanbm zhiybm,mubbm huaybm FROM gx_chep_caizhbmb WHERE zhuanhlb_id = 2) gx_zhiybm_huaybm\n" +
				"    ON gx_samcode_zhiybm.zhiybm = gx_zhiybm_huaybm.zhiybm\n" +
				"  INNER JOIN rl_hy_huaydb h\n" +
				"  ON gx_zhiybm_huaybm.huaybm = h.huaybm \n" +
				"  INNER JOIN gx_jiesdb_chepb g ON g.chepb_id=c.id\n" +
				"  INNER JOIN rl_js_rijsdb j \n" +
				"  ON j.id=g.jiesdb_id\n" +
				"\n" +
				"  WHERE substr(q.qingcsj,0,10)='"+map.get("sDate")+"'  ) t)\n" +
				"   ,\n" +
				"   (SELECT\n" +
				"sum(fadhy+gongrhy) rulml,\n" +
				"round_new(decode((sum(fadhy+gongrhy)), 0, 0,sum(qnet_ar * (fadhy+gongrhy)) / sum(fadhy+gongrhy)),2) qnet_ar\n" +
				"FROM rl_rul_meihyb m INNER JOIN rl_hy_huaydb h ON m.huayd_id=h.huayd_id \n" +
				"  WHERE substr(rulrq,0,10)='"+map.get("sDate")+"'\n" +
				") \n" +
				"UNION ALL\n" +
				"\n" +
				"SELECT t.meikmc,sum(jingz) jingz,\n" +
				"round_new(decode(sum(mt), 0, 0,sum(mt * jingz) / sum(jingz)),2) mt,\n" +
				"round_new(decode(sum(mad), 0, 0,sum(mad * jingz) / sum(jingz)),2) mad,\n" +
				"round_new(decode(sum(ad), 0, 0,sum(ad * jingz) / sum(jingz)),2) ad,\n" +
				"round_new(decode(sum(aar), 0, 0,sum(aar * jingz) / sum(jingz)),2) aar,\n" +
				"round_new(decode(sum(std), 0, 0,sum(std * jingz) / sum(jingz)),2) std,\n" +
				"round_new(decode(sum(vdaf), 0, 0,sum(vdaf * jingz) / sum(jingz)),2) vdaf,\n" +
				"round_new(decode(sum(fcd), 0, 0,sum(fcd * jingz) / sum(jingz)),2) fcd,\n" +
				"round_new(decode(sum(qnet_ar), 0, 0,sum(qnet_ar * jingz) / sum(jingz)),2) rez,\n" +
				"round(decode(sum(jiesjg), 0, 0, sum(jiesjg * jingz) / sum(jingz)), 2) jiesjg,0 yunf,0 zaf,0 hetj,0 kuc,\n" +
				"round(decode(sum(daocmj), 0, 0, sum(daocmj * jingz) / sum(jingz)), 2) daocmj,\n" +
				"round(decode(sum(daocbmj), 0, 0, sum(daocbmj * jingz) / sum(jingz)), 2) daocbmj,\n" +
				"round(decode(sum(buhsmj), 0, 0, sum(daocmj * jingz) / sum(jingz)), 2) buhsmj,\n" +
				"round(decode(sum(buhsbmdj), 0, 0, sum(buhsbmdj * jingz) / sum(jingz)), 2) buhsbmdj,0 rulml,0 qnet_ar,0 rezc,0 rulbmdj\n" +
				"  FROM(\n" +
				"  SELECT c.meikmc,maoz-piz jingz, h.mt,h.mad,h.ad,h.aar,h.std,h.vdaf,h.fcd,h.qnet_ar,j.jiesjg,(jiesjg) daocmj,\n" +
				"  (j.jiesjg*29.271/h.qnet_ar) daocbmj,(j.jiesjg/1.17) buhsmj,((j.jiesjg/1.17)*29.271/h.qnet_ar) buhsbmdj \n" +
				" FROM rl_ys_chepb c\n" +
				"  INNER JOIN rl_ys_chepb_qc q\n" +
				"  ON c.id = q.chepb_id\n" +
				"  INNER JOIN (SELECT yuanbm samcode,mubbm zhiybm FROM gx_chep_caizhbmb WHERE zhuanhlb_id = 1) gx_samcode_zhiybm\n" +
				"  ON c.samcode = gx_samcode_zhiybm.samcode\n" +
				"  INNER JOIN (SELECT yuanbm zhiybm,mubbm huaybm FROM gx_chep_caizhbmb WHERE zhuanhlb_id = 2) gx_zhiybm_huaybm\n" +
				"    ON gx_samcode_zhiybm.zhiybm = gx_zhiybm_huaybm.zhiybm\n" +
				"  INNER JOIN rl_hy_huaydb h\n" +
				"  ON gx_zhiybm_huaybm.huaybm = h.huaybm \n" +
				"  INNER JOIN gx_jiesdb_chepb g ON g.chepb_id=c.id\n" +
				"  INNER JOIN rl_js_rijsdb j \n" +
				"  ON j.id=g.jiesdb_id\n" +
				" \n" +
				"  WHERE substr(q.qingcsj,0,10)='"+map.get("sDate")+"'  ) t\n" +
				"  \n" +
				" GROUP BY meikmc";

		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

		String[][] arrData = new String[list.size()][23];
		for (int i = 0; i < list.size(); i++) {
			int j = 0;
			Iterator<String> it = list.get(i).keySet().iterator();
			while (it.hasNext()) {
				Object o = list.get(i).get(it.next());
				if(o!=null){
					arrData[i][j++] = o.toString();
				}else{
					arrData[i][j++]="";
				}
			}
		}
		Report rt = new Report();
		String[][] ArrHeader = new String[2][23];
		ArrHeader[0] = new String[] { "矿点", "数量", "全水", "干基水", "干基灰", "应基灰", "全硫", "挥发分", "固定碳", "热值", "煤价", "运费", "杂费", "合同价", "库存","到厂煤价","到厂标煤价","不含税煤价", "不含税标煤单价","入炉煤量","入炉热值","热值差","入炉标煤单价"};
		ArrHeader[1] = new String[] { "矿点", "吨", "Mt", "Mad", "Ad", "Aar", "st.d", "Vdaf", "FCd", "Qver", "元/吨", "元/吨", "元/吨", "元/吨", "吨","元/吨","元/吨","元/吨", "元/吨","吨","兆焦/千克","兆焦/千克","元/吨"};


		int[] ArrWidth = new int[] { 65, 48, 48, 48, 48, 48, 48, 50, 50, 50,50, 50, 50, 50, 50, 50, 50, 50,50, 50, 50, 50, 50 };
		Table titleTable = new Table(4,23);
		titleTable.setBorderNone();
		for (int i = 1; i <= titleTable.getRows(); i++) {
			for (int j = 1; j <= titleTable.getCols(); j++) {
				titleTable.setCellBorderNone(i, j);
			}
		}
		//titleTable.setCellValue(4, 1, "填报单位:" + ((Diancxx)map.get("danwmc")).getQuanc(), titleTable.getCols() - 2);
		//titleTable.setCellAlign(4, 1, Table.ALIGN_LEFT);
		
		//titleTable.setCellValue(3,titleTable.getCols() - 1, "国电燃01表", 2);
		titleTable.setCellAlign(3, titleTable.getCols() - 1, Table.ALIGN_RIGHT);
		
		/*titleTable.setCellValue(3, 1, getTBRQ(map.get("riq").toString()), titleTable.getCols() - 2);
		titleTable.setCellAlign(3, 1 , Table.ALIGN_CENTER);*/
		
		//titleTable.setCellValue(4, titleTable.getCols() - 1, "单位:吨、元、% ", 2);
		titleTable.setCellAlign(4, titleTable.getCols() - 1, Table.ALIGN_RIGHT);
		rt.setTitle(titleTable);
		rt.setTitle("国电新疆红雁池发电有限公司、电煤采购价格日报", 2);
		rt.setBody(new Table(arrData, 3, 0, 0, 23));
		rt.body.setHeaderData(ArrHeader);
		convertItem(rt.body);
		rt.body.setPageRows(23);
		int pageCount = rt.getPages();
		
		rt.body.mergeFixedCols();
		rt.body.mergeFixedRow();
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		rt.body.setColAlign(4, Table.ALIGN_RIGHT);
		rt.body.setColAlign(5, Table.ALIGN_RIGHT);
		rt.body.setColAlign(6, Table.ALIGN_RIGHT);
		rt.body.setColAlign(7, Table.ALIGN_RIGHT);
		rt.body.setColAlign(8, Table.ALIGN_RIGHT);
		rt.body.setColAlign(9, Table.ALIGN_RIGHT);
		rt.body.setColAlign(10, Table.ALIGN_RIGHT);
		rt.body.setColAlign(11, Table.ALIGN_RIGHT);
		rt.body.setColAlign(12, Table.ALIGN_RIGHT);
		rt.body.setColAlign(13, Table.ALIGN_RIGHT);
		rt.body.setColAlign(14, Table.ALIGN_RIGHT);
		rt.body.setColAlign(15, Table.ALIGN_RIGHT);
		rt.body.setColAlign(16, Table.ALIGN_RIGHT);
		rt.body.setColAlign(17, Table.ALIGN_RIGHT);
		rt.body.setColAlign(18, Table.ALIGN_RIGHT);
		rt.body.setColAlign(19, Table.ALIGN_RIGHT);
		rt.body.setColAlign(20, Table.ALIGN_RIGHT);
		rt.body.setColAlign(21, Table.ALIGN_RIGHT);
		rt.body.setColAlign(22, Table.ALIGN_RIGHT);
		rt.body.setColAlign(23, Table.ALIGN_RIGHT);
		rt.body.ShowZero=false;
		rt.createDefautlFooter(ArrWidth);
		/*rt.setDefautlFooter(1, 2, "分管领导:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(6, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter((rt.footer.getCols() - 1), 1, "制表:",
				Table.ALIGN_RIGHT);*/

		Map<String, Object> resultMap = new HashMap<String, Object>();
		//
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}
	/**
	 * 数量验收报表
	 */
	@Override
	public JSONArray getShulysbb(String leix,Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<LinkedHashMap<String, Object>> list=new ArrayList<LinkedHashMap<String, Object>>() ;
		if("0".equals(leix)){
		list = guodbbDao.getShulysbb(map);
		}else{
			list = guodbbDao.getShulysbbFkmx(map);
		}
		String[][] arrData = new String[list.size()][14];
		for (int i = 0; i < list.size(); i++) {
			int j = 0;
			Iterator<String> it = list.get(i).keySet().iterator();
			while (it.hasNext()) {
				arrData[i][j++] = list.get(i).get(it.next()).toString();
			}
		}

		Report rt = new Report();
		String[][] ArrHeader = new String[3][14];
		ArrHeader[0] = new String[] { "矿别", "分项", "进厂数量", "验收数量", "验收数量",
				"验收数量", "检斤率", "检斤率", "盈(+)亏(-)数量", "盈(+)亏(-)数量", "盈(+)亏(-)数量",
				"亏吨数量折合金额", "索赔金额", "索赔率" };

		ArrHeader[1] = new String[] { "矿别", "分项", "进厂数量", "小计", "过衡数量", "检尺数量",
				"过衡率", "检尺率", "小计", "过衡", "检尺", "亏吨数量折合金额", "索赔金额", "索赔率" };

		ArrHeader[2] = new String[] { "甲", "乙", "1", "2", "3", "4", "5", "6",
				"7", "8", "9", "10", "11", "12" };

		int[] ArrWidth = new int[] { 150, 80, 80, 80, 80, 80, 80, 80, 80, 80,
				80, 80, 80, 80 };
		Table titleTable = new Table(4, 14);
		titleTable.setBorderNone();
		for (int i = 1; i <= titleTable.getRows(); i++) {
			for (int j = 1; j <= titleTable.getCols(); j++) {
				titleTable.setCellBorderNone(i, j);
			}
		}
		titleTable.setWidth(ArrWidth);
		titleTable.setCellValue(4, 1,"填报单位:" + ((Diancxx)map.get("danwmc")).getQuanc(),titleTable.getCols() - 2);
		titleTable.setCellAlign(4, 1, Table.ALIGN_LEFT);
		
		titleTable.setCellValue(3, titleTable.getCols() - 1, "国电燃02表", 2);
		titleTable.setCellAlign(3, titleTable.getCols() - 1, Table.ALIGN_RIGHT);
		
		titleTable.setCellValue(3,1,"填报日期:"+ getTBRQ(map.get("riq").toString()),titleTable.getCols() - 2);
		titleTable.setCellAlign(3, 1, Table.ALIGN_CENTER);
		
		titleTable.setCellValue(4, titleTable.getCols() - 1, "单位:吨、元、% ", 2);
		titleTable.setCellAlign(4, titleTable.getCols() - 1, Table.ALIGN_RIGHT);
		
		
		rt.setTitle(titleTable);
		rt.setTitle("国电电力发展股份有限公司进厂燃煤数量验收及索赔月报", 2);
		rt.setBody(new Table(arrData, 3, 0, 0, 14));
		rt.body.setHeaderData(ArrHeader);
		rt.body.setPageRows(15);
		int pageCount = rt.getPages();
		
		rt.body.mergeFixedRow();
		rt.body.mergeFixedCols();
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		for(int i=3;i<=14;i++){
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}
		rt.body.setColFormat(12, "0.00");
		rt.body.setColFormat(13, "0.00");
		rt.body.ShowZero=false;
		convertItem(rt.body);
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "分管领导:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(6, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter((rt.footer.getCols() - 1), 1, "制表:",
				Table.ALIGN_RIGHT);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	private String getBaseSql_03(String strDate, String diancxxb_id) {
		String SQL="SELECT KJ.GONGYSB_ID,\n" +
				"       KJ.DQID,\n" + 
				"       KJ.DQMC,\n" + 
				"       SHENGFB.QUANC QUANC,\n" + 
				"       DECODE(KJ.JIHKJB_ID, 1, '重点订货', 3, '重点订货', J.MINGC) TJKJ,\n" + 
				"       KJ.JIHKJB_ID,\n" + 
				"       KJ.PINZB_ID,\n" + 
				"       KJ.YUNSFSB_ID,\n" + 
				"       DIANCXXB_ID,\n" + 
				"       KJ.FENX AS FX,\n" + 
				"       SL.JINGZ+sl.yuns AS JINCML,\n" + 
				"       SL.JINGZ AS YANSML,\n" + 
				"       ZL.*\n" + 
				"  FROM YUESLB SL,\n" + 
				"       YUEZLB ZL,\n" + 
				"       (SELECT K.ID,\n" + 
				"               DQ.DQID,\n" + 
				"               DQ.DQMC,\n" + 
				"               K.GONGYSB_ID,\n" + 
				"               K.JIHKJB_ID,\n" + 
				"               K.PINZB_ID,\n" + 
				"               K.YUNSFSB_ID,\n" + 
				"               K.DIANCXXB_ID,\n" + 
				"               NVL('本月', '') AS FENX\n" + 
				"          FROM YUETJKJB K, VWMEIKDQ DQ\n" + 
				"         WHERE K.RIQ = substr(to_char(to_date('" + strDate + "'||'-01','yyyy-MM-dd'),'yyyy-MM-dd'),0,7)\n" + 
				"           AND DQ.ID = K.GONGYSB_ID\n" + 
				"           AND K.DIANCXXB_ID IN (" + diancxxb_id + ")\n" + 
				"        UNION\n" + 
				"        SELECT K.ID,\n" + 
				"               DQ.DQID,\n" + 
				"               DQ.DQMC,\n" + 
				"               K.GONGYSB_ID,\n" + 
				"               K.JIHKJB_ID,\n" + 
				"               K.PINZB_ID,\n" + 
				"               K.YUNSFSB_ID,\n" + 
				"               K.DIANCXXB_ID,\n" + 
				"               NVL('累计', '') AS FENX\n" + 
				"          FROM YUETJKJB K, VWMEIKDQ DQ\n" + 
				"         WHERE K.RIQ = substr(to_char(to_date('" + strDate + "'||'-01','yyyy-MM-dd'),'yyyy-MM-dd'),0,7)\n" + 
				"           AND DQ.ID = K.GONGYSB_ID\n" + 
				"           AND K.DIANCXXB_ID IN (" + diancxxb_id + ")) KJ,\n" + 
				"       JIHKJB J,\n" + 
				"       MEIKDQB,\n" + 
				"       SHENGFB\n" + 
				" WHERE KJ.ID = SL.YUETJKJB_ID(+) \n";
				SQL+="   AND KJ.FENX = SL.FENX(+)\n" + 
				"   AND KJ.ID = ZL.YUETJKJB_ID(+)\n" + 
				"   AND KJ.FENX = ZL.FENX(+)\n" + 
				"   AND KJ.JIHKJB_ID = J.ID\n" + 
				"   AND KJ.DQID = MEIKDQB.ID\n" + 
				"   AND MEIKDQB.SHENGFB_ID = SHENGFB.ID";
				return SQL;
			}
	
	/**
	 * 质量验收报表
	 */
	@Override
	public JSONArray getZhilysbb(Map<String, Object> map) {
		// TODO Auto-generated method stub

		//List<LinkedHashMap<String, Object>> list = guodbbDao.getZhilysbb(map);

		String Select =null;
		String Having =null;
		if(map.get("leix").equals("0")){
			Select="DECODE(GROUPING(Z.TJKJ),\n" +
			"              1,\n" + 
			"              '总计',\n" + 
			"              DECODE(GROUPING(JIHKJB.MINGC),\n" + 
			"                     1,\n" + 
			"                     '*' || Z.TJKJ,\n" + 
			"                     DECODE(GROUPING(z.QUANC),\n" + 
			"                            1,\n" + 
			"                            '#' || decode(JIHKJB.MINGC,'市场采购','地方矿',JIHKJB.MINGC) || '小计',\n" + 
			"                            DECODE(GROUPING(Z.DQMC),\n" + 
			"                                   1,\n" + 
			"                                   '<I>'||z.QUANC||'</I>',\n" + 
			"                                   DECODE(GROUPING(yunsfsb.MINGC),\n" + 
			"                                          1,\n" + 
			"                                          Z.DQMC || '小计',\n" + 
			"                                          yunsfsb.MINGC))))) AS KUANGB,\n";
			Having="GROUP BY ROLLUP(Z.FX,Z.TJKJ,JIHKJB.MINGC,z.QUANC,Z.DQMC,yunsfsb.mingc,meikxxb.MINGC,PINZB.MINGC)\n" + 
				   "HAVING GROUPING(meikxxb.MINGC) + GROUPING(Z.FX) = 1\n";
		}else{
			Select="DECODE(GROUPING(Z.TJKJ),\n" +
			"              1,\n" + 
			"              '总计',\n" + 
			"              DECODE(GROUPING(JIHKJB.MINGC),\n" + 
			"                     1,\n" + 
			"                     '*' || Z.TJKJ,\n" + 
			"                     DECODE(GROUPING(z.QUANC),\n" + 
			"                            1,\n" + 
			"                            '#' || decode(JIHKJB.MINGC,'市场采购','地方矿',JIHKJB.MINGC) || '小计',\n" + 
			"                            DECODE(GROUPING(Z.DQMC),\n" + 
			"                                   1,\n" + 
			"                                   '<I>'||z.QUANC||'</I>',\n" + 
			"                                   DECODE(GROUPING(meikxxb.MINGC),\n" + 
			"                                          1,\n" + 
			"                                          Z.DQMC || '小计',\n" + 
			"                                          DECODE(GROUPING(yunsfsb.mingc), 1,meikxxb.MINGC,yunsfsb.MINGC\n" + 
			"                                                 )))))) AS KUANGB,\n";
			Having="GROUP BY ROLLUP(Z.FX,Z.TJKJ,JIHKJB.MINGC,z.QUANC,Z.DQMC,meikxxb.MINGC,PINZB.MINGC,yunsfsb.mingc)\n" + 
					"HAVING NOT(GROUPING(meikxxb.MINGC) + GROUPING(PINZB.MINGC) = 1 OR GROUPING(Z.FX) = 1)\n";
		}
		String sql=
			"SELECT " +
			Select+
			"			Z.FX,	\n" + 
			"	       round(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)), 0) AS JINML,	\n" + 
			"	       round(SUM(DECODE(Z.DIANCXXB_ID, 215, YANSML * 0.4, YANSML)), 0) AS YANSSL,	\n" + 
			"	       DECODE(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	              0,	\n" + 
			"	              0,	\n" + 
			"	              ROUND(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)) /	\n" + 
			"	                    SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)) * 100,	\n" + 
			"	                    2)) AS JIANZL,	\n" + 
			"  SUM(0) MT_KF,  SUM(0) AAR_KF, SUM(0) VDAF_KF, SUM(0) QNET_AR_KF, SUM(0)STD_KF,\n"+
		/*	"	       DECODE(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	              0,	\n" + 
			"	              0,	\n" + 
			"	              ROUND_NEW(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML) *	\n" + 
			"	                            MT_KF) /	\n" + 
			"	                        SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	                        1)) AS MT_KF,	\n" + 
			"	       DECODE(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	              0,	\n" + 
			"	              0,	\n" + 
			"	              ROUND_NEW(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML) *	\n" + 
			"	                            AAR_KF) /	\n" + 
			"	                        SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	                        2)) AS AAR_KF,	\n" + 
			"	       DECODE(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	              0,	\n" + 
			"	              0,	\n" + 
			"	              ROUND_NEW(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML) *	\n" + 
			"	                            Vdaf_KF) /	\n" + 
			"	                        SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	                        2)) AS Vdaf_KF,	\n" + 
			"	       DECODE(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	              0,	\n" + 
			"	              0,	\n" + 
			"	              ROUND_NEW(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML) *	\n" + 
			"	                            QNET_AR_KF) /	\n" + 
			"	                        SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	                        2)) AS QNET_AR_KF,	\n" + 
			"	       DECODE(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	              0,	\n" + 
			"	              0,	\n" + 
			"	              ROUND_NEW(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML) *	\n" + 
			"	                            STD_KF) /	\n" + 
			"	                        SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	                        2)) AS ST_D_KF,	\n" +*/ 
			"	       DECODE(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	              0,	\n" + 
			"	              0,	\n" + 
			"	              ROUND_NEW(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML) * MT) /	\n" + 
			"	                        SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	                        1)) AS Mar,	\n" + 
			"	       DECODE(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	              0,	\n" + 
			"	              0,	\n" + 
			"	              ROUND_NEW(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML) * AAD) /	\n" + 
			"	                        SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	                        2)) AS AAD,	\n" + 
			"	       DECODE(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	              0,	\n" + 
			"	              0,	\n" + 
			"	              ROUND_NEW(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML) * AD) /	\n" + 
			"	                        SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	                        2)) AS AD,	\n" + 
			"	       DECODE(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	              0,	\n" + 
			"	              0,	\n" + 
			"	              ROUND_NEW(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML) * Vdaf) /	\n" + 
			"	                        SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	                        2)) AS Vdaf,	\n" + 
			"	       ROUND_NEW(DECODE(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	                        0,	\n" + 
			"	                        0,	\n" + 
			"	                        ROUND_NEW(SUM(DECODE(Z.DIANCXXB_ID,	\n" + 
			"	                                             215,	\n" + 
			"	                                             JINCML * 0.4,	\n" + 
			"	                                             JINCML) * QNET_AR) /	\n" + 
			"	                                  SUM(DECODE(Z.DIANCXXB_ID,	\n" + 
			"	                                             215,	\n" + 
			"	                                             JINCML * 0.4,	\n" + 
			"	                                             JINCML)),	\n" + 
			"	                                  3)),	\n" + 
			"	                 2) AS QNET_AR,	\n" + 
			"	       DECODE(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	              0,	\n" + 
			"	              0,	\n" + 
			"	              ROUND_NEW(SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML) * STD) /	\n" + 
			"	                        SUM(DECODE(Z.DIANCXXB_ID, 215, JINCML * 0.4, JINCML)),	\n" + 
			"	                        2)) AS ST_D,	\n" + 
			"	       SUM(DECODE(Z.DIANCXXB_ID,	\n" + 
			"	                  215,	\n" + 
			"	                  NVL(ZHIJBFJE, 0) * 0.4,	\n" + 
			"	                  NVL(ZHIJBFJE, 0))) AS JZ_HEJ,	\n" + 
			"	       SUM(DECODE(Z.DIANCXXB_ID,	\n" + 
			"	                  215,	\n" + 
			"	                  NVL(ZHIJBFJE_M, 0) * 0.4,	\n" + 
			"	                  NVL(ZHIJBFJE_M, 0))) AS JZ_SHUIF,	\n" + 
			"	       SUM(DECODE(Z.DIANCXXB_ID,	\n" + 
			"	                  215,	\n" + 
			"	                  NVL(ZHIJBFJE_A, 0) * 0.4,	\n" + 
			"	                  NVL(ZHIJBFJE_A, 0))) AS JZ_HUIF,	\n" + 
			"	       SUM(DECODE(Z.DIANCXXB_ID,	\n" + 
			"	                  215,	\n" + 
			"	                  NVL(ZHIJBFJE_V, 0) * 0.4,	\n" + 
			"	                  NVL(ZHIJBFJE_V, 0))) AS JZ_HUIFF,	\n" + 
			"	       SUM(DECODE(Z.DIANCXXB_ID,	\n" + 
			"	                  215,	\n" + 
			"	                  NVL(ZHIJBFJE_Q, 0) * 0.4,	\n" + 
			"	                  NVL(ZHIJBFJE_Q, 0))) AS JZ_FARL,	\n" + 
			"	       SUM(DECODE(Z.DIANCXXB_ID,	\n" + 
			"	                  215,	\n" + 
			"	                  NVL(ZHIJBFJE_S, 0) * 0.4,	\n" + 
			"	                  NVL(ZHIJBFJE_S, 0))) AS JZ_LIUF,	\n" + 
			"	       SUM(DECODE(Z.DIANCXXB_ID,	\n" + 
			"	                  215,	\n" + 
			"	                  NVL(ZHIJBFJE_T, 0) * 0.4,	\n" + 
			"	                  NVL(ZHIJBFJE_T, 0))) AS JZ_HUIRD,	\n" + 
			"	       SUM(DECODE(Z.DIANCXXB_ID, 215, NVL(SUOPJE, 0) * 0.4, NVL(SUOPJE, 0))) AS SUOPJE,	\n" + 
			"	       decode(SUM(DECODE(Z.DIANCXXB_ID,	\n" + 
			"	                         215,	\n" + 
			"	                         NVL(ZHIJBFJE, 0) * 0.4,	\n" + 
			"	                         NVL(ZHIJBFJE, 0))),	\n" + 
			"	              0,	\n" + 
			"	              0,	\n" + 
			"	              round(SUM(DECODE(Z.DIANCXXB_ID,	\n" + 
			"	                               215,	\n" + 
			"	                               NVL(SUOPJE, 0) * 0.4,	\n" + 
			"	                               NVL(SUOPJE, 0))) /	\n" + 
			"	                    SUM(DECODE(Z.DIANCXXB_ID,	\n" + 
			"	                               215,	\n" + 
			"	                               NVL(ZHIJBFJE, 0) * 0.4,	\n" + 
			"	                               NVL(ZHIJBFJE, 0))) * 100,	\n" + 
			"	                    2)) AS SUOPL	\n" + 
			"FROM (" + getBaseSql_03(map.get("riq").toString(), map.get("dianc").toString()) + ") Z,meikxxb,JIHKJB,PINZB,yunsfsb\n" +
			"WHERE Z.GONGYSB_ID = meikxxb.ID\n" +
			"   AND Z.JIHKJB_ID = JIHKJB.ID\n" + 
			"   AND Z.PINZB_ID = PINZB.ID\n" + 
			"	and z.yunsfsb_id=yunsfsb.id\n"+
			Having+			
			"ORDER BY Z.TJKJ DESC,JIHKJB.MINGC DESC,z.QUANC DESC, GROUPING(Z.DQMC) DESC,Z.DQMC,grouping(meikxxb.MINGC)DESC,\n" +
			"meikxxb.MINGC ,grouping(PINZB.MINGC)desc,PINZB.MINGC,grouping(yunsfsb.mingc)desc,yunsfsb.mingc,grouping(Z.FX)desc,Z.FX";
		
//		List<LinkedHashMap<String, Object>> list = guodbbDao.getZhilysbb(map);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

		String[][] arrData = new String[list.size()][25];
		for (int i = 0; i < list.size(); i++) {
			int j = 0;
			Iterator<String> it = list.get(i).keySet().iterator();
			while (it.hasNext()) {
				arrData[i][j++] = list.get(i).get(it.next()).toString();
			}
		}

		Report rt = new Report();
		String[][] ArrHeader = new String[3][25];
		ArrHeader[0] = new String[] { "矿别", "分项", "进厂数量", "验收数量", "检质率",
				"矿方化验", "矿方化验", "矿方化验", "矿方化验", "矿方化验", "进厂质量验收情况", "进厂质量验收情况",
				"进厂质量验收情况", "进厂质量验收情况", "进厂质量验收情况", "进厂质量验收情况", "质价不符折合金额",
				"质价不符折合金额", "质价不符折合金额", "质价不符折合金额", "质价不符折合金额", "质价不符折合金额",
				"质价不符折合金额", "索赔金额", "索赔率" };
		ArrHeader[1] = new String[] { "矿别", "分项", "进厂数量", "验收数量", "检质率",
				"Mt</br>(%)", "Aar</br>(%)", "Vdaf</br>(%)",
				"Qnet,ar</br>(MJ/Kg)", "St,d</br>(%)", "Mt</br>(%)",
				"Aad</br>(%)", "Ad</br>(%)", "Vdaf</br>(%)", "Qnet,ar</br>(%)",
				"St,d</br>(%)", "小计", "水分", "灰分", "挥发分", "热值", "硫分", "灰熔点",
				"索赔金额", "索赔率" };
		ArrHeader[2] = new String[] { "甲", "乙", "1", "2", "3", "4", "5", "6",
				"7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23" };

		int[] ArrWidth = new int[] { 150, 80, 80, 80, 80, 80, 80, 80, 80, 80,
				80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80 };
		Table titleTable = new Table(4, 25);
		titleTable.setBorderNone();
		for (int i = 1; i <= titleTable.getRows(); i++) {
			for (int j = 1; j <= titleTable.getCols(); j++) {
				titleTable.setCellBorderNone(i, j);
			}
		}
		titleTable.setWidth(ArrWidth);
		titleTable.setCellValue(4, 1,"填报单位:" + ((Diancxx)map.get("danwmc")).getQuanc(),titleTable.getCols() - 2);
		titleTable.setCellAlign(4, 1, Table.ALIGN_LEFT);
		
		titleTable.setCellValue(3, titleTable.getCols() - 1, "国电燃03表", 2);
		titleTable.setCellAlign(3, titleTable.getCols() - 1, Table.ALIGN_RIGHT);
		
		titleTable.setCellValue(3,1,"填报日期:"+ getTBRQ(map.get("riq").toString()),titleTable.getCols() - 2);
		titleTable.setCellAlign(3, 1, Table.ALIGN_CENTER);
		
		titleTable.setCellValue(4, titleTable.getCols() - 1, "单位:吨、元、% ", 2);
		titleTable.setCellAlign(4, titleTable.getCols() - 1, Table.ALIGN_RIGHT);
		
		rt.setTitle(titleTable);
		rt.setTitle("国电电力发展股份有限公司进厂燃煤质量验收及索赔月报", 2);
		rt.setBody(new Table(arrData, 3, 0, 0, 25));
		rt.body.setHeaderData(ArrHeader);
		rt.body.setPageRows(15);
		int pageCount = rt.getPages();
		
		rt.body.mergeFixedRowCol();
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		for(int i=3;i<=25;i++){
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}
		for (int i = 6; i <= 24; i++) {
			rt.body.setColFormat(i, "0.00");
		}
		rt.body.ShowZero=false;
		convertItem(rt.body);
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "分管领导:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(11, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter((rt.footer.getCols() - 1), 1, "制表:",
				Table.ALIGN_RIGHT);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	/**
	 * 入厂标煤单价报表
	 */
	@Override
	public JSONArray getRucbmdjbb(Map<String, Object> map) {
		// TODO Auto-generated method stub
		//入厂标煤单价汇总
		String sql ="";
		if(map.get("leix").toString().equals("1")){
		//入厂标煤单价汇总
		 sql = 
		"SELECT\n" +
		" SR.DQMC,\n" + 
		" SR.FENX,\n" + 
		" SR.JIESL,\n" + 
		" SR.MEIJ + SR.YUNJ + SR.ZAF ZONGHJ,\n" + 
		" SR.MEIJ,\n" + 
		" SR.YUNJ,\n" + 
		" SR.ZAF,\n" + 
		" ROUND((SR.MEIJ / 1.17 + SR.YUNJ - SR.YUNJS + SR.ZAF - SR.ZAFS), 2) ZONGHJBHS,\n" + 
		" round_new(SR.QNET_AR, 2) QNET_AR,\n" + 
		" ROUND(decode(SR.QNET_AR,\n" + 
		"              0,\n" + 
		"              0,\n" + 
		"              ROUND((SR.MEIJ / 1.17 + SR.YUNJ - SR.YUNJS + SR.ZAF - SR.ZAFS),\n" + 
		"                    2) * 29.271 / SR.QNET_AR),\n" + 
		"       2) BUHSBMDJ,\n" + 
		" ROUND(decode(SR.QNET_AR,\n" + 
		"              0,\n" + 
		"              0,\n" + 
		"              (SR.MEIJ + SR.YUNJ + SR.ZAF) * 29.271 / SR.QNET_AR),\n" + 
		"       2) BIAOMDJM\n" + 
		"  FROM (SELECT DECODE(GROUPING(T.TJKJ),\n" + 
		"                      1,\n" + 
		"                      '总计',\n" + 
		"                      DECODE(GROUPING(JIHKJB.MINGC),\n" + 
		"                             1,\n" + 
		"                             '*' || T.TJKJ,\n" + 
		"                             DECODE(GROUPING(T.SMC),\n" + 
		"                                    1,\n" + 
		"                                    '#' || DECODE(JIHKJB.MINGC,\n" + 
		"                                                  '市场采购',\n" + 
		"                                                  '地方矿',\n" + 
		"                                                  JIHKJB.MINGC) || '小计',\n" + 
		"                                    DECODE(GROUPING(T.DQMC),\n" + 
		"                                           1,\n" + 
		"                                           '<I>' || T.SMC || '</I>',\n" + 
		"                                           DECODE(GROUPING(T.YUNSFSB_ID),\n" + 
		"                                                  1,\n" + 
		"                                                  T.DQMC || '小计',\n" + 
		"                                                  T.YUNSFSB_ID))))) DQMC,\n" + 
		"               Y.FENX,\n" + 
		"               round(SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)), 0) JIESL,\n" + 
		"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
		"                                       215,\n" + 
		"                                       JIESL * 0.4,\n" + 
		"                                       JIESL)),\n" + 
		"                            0,\n" + 
		"                            0,\n" + 
		"                            SUM((Y.MEIJ + Y.YUNJ + Y.DAOZZF + Y.KUANGQYF +\n" + 
		"                                Y.QIT + Y.ZAF) *\n" + 
		"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
		"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
		"                     2) ZONGHJ,\n" + 
		"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
		"                                       215,\n" + 
		"                                       JIESL * 0.4,\n" + 
		"                                       JIESL)),\n" + 
		"                            0,\n" + 
		"                            0,\n" + 
		"                            SUM(Y.MEIJ *\n" + 
		"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
		"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
		"                     2) MEIJ,\n" + 
		"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
		"                                       215,\n" + 
		"                                       JIESL * 0.4,\n" + 
		"                                       JIESL)),\n" + 
		"                            0,\n" + 
		"                            0,\n" + 
		"                            SUM(Y.YUNJ *\n" + 
		"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
		"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
		"                     2) YUNJ,\n" + 
		"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
		"                                       215,\n" + 
		"                                       JIESL * 0.4,\n" + 
		"                                       JIESL)),\n" + 
		"                            0,\n" + 
		"                            0,\n" + 
		"                            SUM((Y.ZAF + Y.DAOZZF + Y.QIT + Y.KUANGQYF) *\n" + 
		"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
		"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
		"                     2) ZAF,\n" + 
		"               round_new(decode(sum(DECODE(T.DIANCXXB_ID,\n" + 
		"                                           215,\n" + 
		"                                           JIESL * 0.4,\n" + 
		"                                           JIESL)),\n" + 
		"                                0,\n" + 
		"                                0,\n" + 
		"                                sum((y.meij - y.meijs + y.yunj - y.yunjs +\n" + 
		"                                    y.daozzf + y.kuangqyf + y.qit + y.zaf -\n" + 
		"                                    y.zafs) * DECODE(T.DIANCXXB_ID,\n" + 
		"                                                     215,\n" + 
		"                                                     JIESL * 0.4,\n" + 
		"                                                     JIESL)) /\n" + 
		"                                sum(DECODE(T.DIANCXXB_ID,\n" + 
		"                                           215,\n" + 
		"                                           JIESL * 0.4,\n" + 
		"                                           JIESL))),\n" + 
		"                         2) zonghjbhs,\n" + 
		"               round_new(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
		"                                           215,\n" + 
		"                                           JIESL * 0.4,\n" + 
		"                                           JIESL)),\n" + 
		"                                0,\n" + 
		"                                0,\n" + 
		"                                SUM(Y.QNET_AR * DECODE(T.DIANCXXB_ID,\n" + 
		"                                                       215,\n" + 
		"                                                       JIESL * 0.4,\n" + 
		"                                                       JIESL)) /\n" + 
		"                                SUM(DECODE(T.DIANCXXB_ID,\n" + 
		"                                           215,\n" + 
		"                                           JIESL * 0.4,\n" + 
		"                                           JIESL))),\n" + 
		"                         3) QNET_AR,\n" + 
		"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
		"                                       215,\n" + 
		"                                       JIESL * 0.4,\n" + 
		"                                       JIESL)),\n" + 
		"                            0,\n" + 
		"                            0,\n" + 
		"                            SUM(Y.BUHSBMDJ *\n" + 
		"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
		"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
		"                     2) BUHSBMDJ,\n" + 
		"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
		"                                       215,\n" + 
		"                                       JIESL * 0.4,\n" + 
		"                                       JIESL)),\n" + 
		"                            0,\n" + 
		"                            0,\n" + 
		"                            SUM(Y.BIAOMDJ *\n" + 
		"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
		"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
		"                     2) BIAOMDJ,\n" + 
		"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
		"                                       215,\n" + 
		"                                       JIESL * 0.4,\n" + 
		"                                       JIESL)),\n" + 
		"                            0,\n" + 
		"                            0,\n" + 
		"                            SUM(Y.ZAFS *\n" + 
		"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
		"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
		"                     2) ZAFS,\n" + 
		"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
		"                                       215,\n" + 
		"                                       JIESL * 0.4,\n" + 
		"                                       JIESL)),\n" + 
		"                            0,\n" + 
		"                            0,\n" + 
		"                            SUM(Y.YUNJS *\n" + 
		"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
		"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
		"                     2) YUNJS,\n" + 
		"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
		"                                       215,\n" + 
		"                                       JIESL * 0.4,\n" + 
		"                                       JIESL)),\n" + 
		"                            0,\n" + 
		"                            0,\n" + 
		"                            SUM(Y.MEIJS *\n" + 
		"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
		"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
		"                     2) MEIJS,\n" + 
		"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
		"                                       215,\n" + 
		"                                       JIESL * 0.4,\n" + 
		"                                       JIESL)),\n" + 
		"                            0,\n" + 
		"                            0,\n" + 
		"                            SUM(Y.QIT *\n" + 
		"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
		"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
		"                     2) QIT,\n" + 
		"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
		"                                       215,\n" + 
		"                                       JIESL * 0.4,\n" + 
		"                                       JIESL)),\n" + 
		"                            0,\n" + 
		"                            0,\n" + 
		"                            SUM(Y.DAOZZF *\n" + 
		"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
		"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
		"                     2) DAOZZF,\n" + 
		"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
		"                                       215,\n" + 
		"                                       JIESL * 0.4,\n" + 
		"                                       JIESL)),\n" + 
		"                            0,\n" + 
		"                            0,\n" + 
		"                            SUM(Y.KUANGQYF *\n" + 
		"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
		"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
		"                     2) KUANGQYF\n" + 
		"          FROM YUEJSBMDJ Y,\n" + 
		"               (SELECT T.ID,\n" + 
		"                       Y.MINGC YUNSFSB_ID,\n" + 
		"                       T.GONGYSB_ID,\n" + 
		"                       V.SMC,\n" + 
		"                       V.DQMC,\n" + 
		"                       G.MINGC,\n" + 
		"                       DECODE(T.JIHKJB_ID,\n" + 
		"                              1,\n" + 
		"                              '重点订货',\n" + 
		"                              3,\n" + 
		"                              '重点订货',\n" + 
		"                              J.MINGC) TJKJ,\n" + 
		"                       T.JIHKJB_ID,\n" + 
		"                       T.RIQ,\n" + 
		"                       T.DIANCXXB_ID\n" + 
		"                  FROM YUETJKJB T,\n" + 
		"                       GONGYSB G,\n" + 
		"                       JIHKJB J,\n" + 
		"                       YUNSFSB Y,\n" + 
		"                       (SELECT DISTINCT ID, DQMC, SMC FROM VWGONGYSDQ) V\n" + 
		"                 WHERE T.GONGYSB_ID = G.ID\n" + 
		"                   AND V.ID = G.ID\n" + 
		"                   AND T.JIHKJB_ID = J.ID\n" + 
		"                   AND T.YUNSFSB_ID = Y.ID\n" + 
		"                   AND T.DIANCXXB_ID IN ("+map.get("dianc").toString()+")\n" + 
		"                   AND T.RIQ = '"+map.get("riq").toString()+"') T,\n" + 
		"               JIHKJB\n" + 
		"         WHERE Y.YUETJKJB_ID = T.ID\n" + 
		"           AND T.JIHKJB_ID = JIHKJB.ID\n" + 
		"         GROUP BY ROLLUP(Y.FENX,\n" + 
		"                         T.TJKJ,\n" + 
		"                         JIHKJB.MINGC,\n" + 
		"                         T.SMC,\n" + 
		"                         T.DQMC,\n" + 
		"                         T.YUNSFSB_ID,\n" + 
		"                         T.MINGC)\n" + 
		"        HAVING GROUPING(T.MINGC) + GROUPING(fenx) = 1\n" + 
		"         ORDER BY\n" + 
		"                  T.TJKJ DESC,\n" + 
		"                  JIHKJB.MINGC DESC,\n" + 
		"                  T.SMC DESC,\n" + 
		"                  GROUPING(T.DQMC) DESC,\n" + 
		"                  T.DQMC,\n" + 
		"                  GROUPING(T.MINGC) DESC,\n" + 
		"                  T.MINGC,\n" + 
		"                  GROUPING(T.YUNSFSB_ID) DESC,\n" + 
		"                  T.YUNSFSB_ID,\n" + 
		"                  GROUPING(Y.FENX) DESC,\n" + 
		"                  Y.FENX) SR";
		}else if(map.get("leix").toString().equals("2")){
		 //入厂标煤单价分矿明细
		 sql =
			"SELECT\n" +
			" SR.DQMC,\n" + 
			" SR.FENX,\n" + 
			" SR.JIESL,\n" + 
			" SR.MEIJ + SR.YUNJ + SR.ZAF ZONGHJ,\n" + 
			" SR.MEIJ,\n" + 
			" SR.YUNJ,\n" + 
			" SR.ZAF,\n" + 
			" ROUND((SR.MEIJ / 1.17 + SR.YUNJ - SR.YUNJS + SR.ZAF - SR.ZAFS), 2) ZONGHJBHS,\n" + 
			" round_new(SR.QNET_AR, 2) QNET_AR,\n" + 
			" ROUND(decode(SR.QNET_AR,\n" + 
			"              0,\n" + 
			"              0,\n" + 
			"              ROUND((SR.MEIJ / 1.17 + SR.YUNJ - SR.YUNJS + SR.ZAF - SR.ZAFS),\n" + 
			"                    2) * 29.271 / SR.QNET_AR),\n" + 
			"       2) BUHSBMDJ,\n" + 
			" ROUND(decode(SR.QNET_AR,\n" + 
			"              0,\n" + 
			"              0,\n" + 
			"              (SR.MEIJ + SR.YUNJ + SR.ZAF) * 29.271 / SR.QNET_AR),\n" + 
			"       2) BIAOMDJM\n" + 
			"  FROM (SELECT DECODE(GROUPING(T.TJKJ),\n" + 
			"                      1,\n" + 
			"                      '总计',\n" + 
			"                      DECODE(GROUPING(JIHKJB.MINGC),\n" + 
			"                             1,\n" + 
			"                             '*' || T.TJKJ,\n" + 
			"                             DECODE(GROUPING(T.SMC),\n" + 
			"                                    1,\n" + 
			"                                    '#' || DECODE(JIHKJB.MINGC,\n" + 
			"                                                  '市场采购',\n" + 
			"                                                  '地方矿',\n" + 
			"                                                  JIHKJB.MINGC) || '小计',\n" + 
			"                                    DECODE(GROUPING(T.DQMC),\n" + 
			"                                           1,\n" + 
			"                                           '<I>' || T.SMC || '</I>',\n" + 
			"                                           DECODE(GROUPING(T.MINGC),\n" + 
			"                                                  1,\n" + 
			"                                                  T.DQMC || '小计',\n" + 
			"                                                  decode(grouping(T.YUNSFSB_ID),\n" + 
			"                                                         1,\n" + 
			"                                                         T.MINGC,\n" + 
			"                                                         T.YUNSFSB_ID)))))) DQMC,\n" + 
			"               Y.FENX,\n" + 
			"               round(SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)), 0) JIESL,\n" + 
			"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
			"                                       215,\n" + 
			"                                       JIESL * 0.4,\n" + 
			"                                       JIESL)),\n" + 
			"                            0,\n" + 
			"                            0,\n" + 
			"                            SUM((Y.MEIJ + Y.YUNJ + Y.DAOZZF + Y.KUANGQYF +\n" + 
			"                                Y.QIT + Y.ZAF) *\n" + 
			"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
			"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
			"                     2) ZONGHJ,\n" + 
			"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
			"                                       215,\n" + 
			"                                       JIESL * 0.4,\n" + 
			"                                       JIESL)),\n" + 
			"                            0,\n" + 
			"                            0,\n" + 
			"                            SUM(Y.MEIJ *\n" + 
			"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
			"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
			"                     2) MEIJ,\n" + 
			"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
			"                                       215,\n" + 
			"                                       JIESL * 0.4,\n" + 
			"                                       JIESL)),\n" + 
			"                            0,\n" + 
			"                            0,\n" + 
			"                            SUM(Y.YUNJ *\n" + 
			"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
			"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
			"                     2) YUNJ,\n" + 
			"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
			"                                       215,\n" + 
			"                                       JIESL * 0.4,\n" + 
			"                                       JIESL)),\n" + 
			"                            0,\n" + 
			"                            0,\n" + 
			"                            SUM((Y.ZAF + Y.DAOZZF + Y.QIT + Y.KUANGQYF) *\n" + 
			"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
			"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
			"                     2) ZAF,\n" + 
			"               round_new(decode(sum(DECODE(T.DIANCXXB_ID,\n" + 
			"                                           215,\n" + 
			"                                           JIESL * 0.4,\n" + 
			"                                           JIESL)),\n" + 
			"                                0,\n" + 
			"                                0,\n" + 
			"                                sum((y.meij - y.meijs + y.yunj - y.yunjs +\n" + 
			"                                    y.daozzf + y.kuangqyf + y.qit + y.zaf -\n" + 
			"                                    y.zafs) * DECODE(T.DIANCXXB_ID,\n" + 
			"                                                     215,\n" + 
			"                                                     JIESL * 0.4,\n" + 
			"                                                     JIESL)) /\n" + 
			"                                sum(DECODE(T.DIANCXXB_ID,\n" + 
			"                                           215,\n" + 
			"                                           JIESL * 0.4,\n" + 
			"                                           JIESL))),\n" + 
			"                         2) zonghjbhs,\n" + 
			"               round_new(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
			"                                           215,\n" + 
			"                                           JIESL * 0.4,\n" + 
			"                                           JIESL)),\n" + 
			"                                0,\n" + 
			"                                0,\n" + 
			"                                SUM(Y.QNET_AR * DECODE(T.DIANCXXB_ID,\n" + 
			"                                                       215,\n" + 
			"                                                       JIESL * 0.4,\n" + 
			"                                                       JIESL)) /\n" + 
			"                                SUM(DECODE(T.DIANCXXB_ID,\n" + 
			"                                           215,\n" + 
			"                                           JIESL * 0.4,\n" + 
			"                                           JIESL))),\n" + 
			"                         3) QNET_AR,\n" + 
			"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
			"                                       215,\n" + 
			"                                       JIESL * 0.4,\n" + 
			"                                       JIESL)),\n" + 
			"                            0,\n" + 
			"                            0,\n" + 
			"                            SUM(Y.BUHSBMDJ *\n" + 
			"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
			"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
			"                     2) BUHSBMDJ,\n" + 
			"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
			"                                       215,\n" + 
			"                                       JIESL * 0.4,\n" + 
			"                                       JIESL)),\n" + 
			"                            0,\n" + 
			"                            0,\n" + 
			"                            SUM(Y.BIAOMDJ *\n" + 
			"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
			"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
			"                     2) BIAOMDJ,\n" + 
			"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
			"                                       215,\n" + 
			"                                       JIESL * 0.4,\n" + 
			"                                       JIESL)),\n" + 
			"                            0,\n" + 
			"                            0,\n" + 
			"                            SUM(Y.ZAFS *\n" + 
			"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
			"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
			"                     2) ZAFS,\n" + 
			"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
			"                                       215,\n" + 
			"                                       JIESL * 0.4,\n" + 
			"                                       JIESL)),\n" + 
			"                            0,\n" + 
			"                            0,\n" + 
			"                            SUM(Y.YUNJS *\n" + 
			"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
			"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
			"                     2) YUNJS,\n" + 
			"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
			"                                       215,\n" + 
			"                                       JIESL * 0.4,\n" + 
			"                                       JIESL)),\n" + 
			"                            0,\n" + 
			"                            0,\n" + 
			"                            SUM(Y.MEIJS *\n" + 
			"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
			"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
			"                     2) MEIJS,\n" + 
			"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
			"                                       215,\n" + 
			"                                       JIESL * 0.4,\n" + 
			"                                       JIESL)),\n" + 
			"                            0,\n" + 
			"                            0,\n" + 
			"                            SUM(Y.QIT *\n" + 
			"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
			"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
			"                     2) QIT,\n" + 
			"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
			"                                       215,\n" + 
			"                                       JIESL * 0.4,\n" + 
			"                                       JIESL)),\n" + 
			"                            0,\n" + 
			"                            0,\n" + 
			"                            SUM(Y.DAOZZF *\n" + 
			"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
			"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
			"                     2) DAOZZF,\n" + 
			"               round(DECODE(SUM(DECODE(T.DIANCXXB_ID,\n" + 
			"                                       215,\n" + 
			"                                       JIESL * 0.4,\n" + 
			"                                       JIESL)),\n" + 
			"                            0,\n" + 
			"                            0,\n" + 
			"                            SUM(Y.KUANGQYF *\n" + 
			"                                DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL)) /\n" + 
			"                            SUM(DECODE(T.DIANCXXB_ID, 215, JIESL * 0.4, JIESL))),\n" + 
			"                     2) KUANGQYF\n" + 
			"          FROM YUEJSBMDJ Y,\n" + 
			"               (SELECT T.ID,\n" + 
			"                       Y.MINGC YUNSFSB_ID,\n" + 
			"                       T.GONGYSB_ID,\n" + 
			"                       V.SMC,\n" + 
			"                       V.DQMC,\n" + 
			"                       G.MINGC,\n" + 
			"                       DECODE(T.JIHKJB_ID,\n" + 
			"                              1,\n" + 
			"                              '重点订货',\n" + 
			"                              3,\n" + 
			"                              '重点订货',\n" + 
			"                              J.MINGC) TJKJ,\n" + 
			"                       T.JIHKJB_ID,\n" + 
			"                       T.RIQ,\n" + 
			"                       T.DIANCXXB_ID\n" + 
			"                  FROM YUETJKJB T,\n" + 
			"                       GONGYSB G,\n" + 
			"                       JIHKJB J,\n" + 
			"                       YUNSFSB Y,\n" + 
			"                       (SELECT DISTINCT ID, DQMC, SMC FROM VWGONGYSDQ) V\n" + 
			"                 WHERE T.GONGYSB_ID = G.ID\n" + 
			"                   AND V.ID = G.ID\n" + 
			"                   AND T.JIHKJB_ID = J.ID\n" + 
			"                   AND T.YUNSFSB_ID = Y.ID\n" + 
			"                   AND T.DIANCXXB_ID IN ("+map.get("dianc").toString()+")\n" + 
			"                   AND T.RIQ = '"+map.get("riq").toString()+"') T,\n" + 
			"               JIHKJB\n" + 
			"         WHERE Y.YUETJKJB_ID = T.ID\n" + 
			"           AND T.JIHKJB_ID = JIHKJB.ID\n" + 
			"         GROUP BY ROLLUP(Y.FENX,\n" + 
			"                         T.TJKJ,\n" + 
			"                         JIHKJB.MINGC,\n" + 
			"                         T.SMC,\n" + 
			"                         T.DQMC,\n" + 
			"                         T.MINGC,\n" + 
			"                         T.YUNSFSB_ID)\n" + 
			"        HAVING NOT(GROUPING(Y.FENX) = 1)\n" + 
			"         ORDER BY\n" + 
			"                  T.TJKJ DESC,\n" + 
			"                  JIHKJB.MINGC DESC,\n" + 
			"                  T.SMC DESC,\n" + 
			"                  GROUPING(T.DQMC) DESC,\n" + 
			"                  T.DQMC,\n" + 
			"                  GROUPING(T.MINGC) DESC,\n" + 
			"                  T.MINGC,\n" + 
			"                  GROUPING(T.YUNSFSB_ID) DESC,\n" + 
			"                  T.YUNSFSB_ID,\n" + 
			"                  GROUPING(Y.FENX) DESC,\n" + 
			"                  Y.FENX) SR";
		 }
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		String[][] arrData = new String[list.size()][11];
		for (int i = 0; i < list.size(); i++) {
			int j = 0;
			Iterator<String> it = list.get(i).keySet().iterator();
			while (it.hasNext()) {
				arrData[i][j++] = list.get(i).get(it.next()).toString();
			}
		}

		Report rt = new Report();
		String[][] ArrHeader = new String[2][11];
		ArrHeader[0] = new String[] { "矿别", "分项", "入厂量(吨)", "到厂综合价", "煤价(含税)",
				"运费(含税)", "杂费", "进厂不含</br>税总价", "Qnet,ar</br>(MJ/Kg)",
				"进厂不含</br>税标煤单价", "进厂含税</br>标煤单价" };
		ArrHeader[1] = new String[] { "甲", "乙", "1", "2", "3", "4", "5", "6",
				"7", "8", "9" };

		int[] ArrWidth = new int[] { 150, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80 };
		Table titleTable = new Table(4, 11);
		titleTable.setBorderNone();
		for (int i = 1; i <= titleTable.getRows(); i++) {
			for (int j = 1; j <= titleTable.getCols(); j++) {
				titleTable.setCellBorderNone(i, j);
			}
		}
		titleTable.setWidth(ArrWidth);
		titleTable.setCellValue(4, 1,"填报单位:" + ((Diancxx)map.get("danwmc")).getQuanc(),titleTable.getCols() - 3);
		titleTable.setCellAlign(4, 1, Table.ALIGN_LEFT);
		
		titleTable.setCellValue(3, titleTable.getCols() - 1, "国电燃04表", 2);
		titleTable.setCellAlign(3, titleTable.getCols() - 1, Table.ALIGN_RIGHT);
		
		titleTable.setCellValue(3,1,"填报日期:"+ getTBRQ(map.get("riq").toString()),titleTable.getCols() - 2);
		titleTable.setCellAlign(3, 1, Table.ALIGN_CENTER);
		
		titleTable.setCellValue(4, titleTable.getCols() - 1, "单位:吨、元、% ", 2);
		titleTable.setCellAlign(4, titleTable.getCols() - 1, Table.ALIGN_RIGHT);
		
		rt.setTitle(titleTable);
		rt.setTitle("国电电力发展股份有限公司分矿燃料费用构成及进厂标煤单价分析月报", 2);
		rt.setBody(new Table(arrData, 2, 0, 0, 11));
		rt.body.setHeaderData(ArrHeader);
		rt.body.setPageRows(15);
		int pageCount = rt.getPages();
		
		rt.body.mergeFixedRow();
		rt.body.mergeFixedCols();
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		for(int i=3;i<=11;i++){
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}
		for (int i = 4; i <= rt.body.getCols(); i++) {
			rt.body.setColFormat(i, "0.00");
		}
		convertItem(rt.body);
		rt.body.ShowZero=false;
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "分管领导:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(5, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter((rt.footer.getCols() - 1), 1, "制表:",
				Table.ALIGN_RIGHT);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	/**
	 * 燃油收耗存
	 */
	@Override
	public JSONArray getRanyshc(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<LinkedHashMap<String, Object>> list = guodbbDao.getRanyshc(map);

		String[][] arrData = new String[list.size()][15];
		for (int i = 0; i < list.size(); i++) {
			int j = 0;
			Iterator<String> it = list.get(i).keySet().iterator();
			while (it.hasNext()) {
				arrData[i][j++] = list.get(i).get(it.next()).toString();
			}
		}

		Report rt = new Report();
		String[][] ArrHeader = new String[4][15];
		ArrHeader[0] = new String[] { "单位", "品种", "分项", "期初库存", "实际供应情况",
				"实际供应情况", "实际供应情况", "实际耗用量", "实际耗用量", "实际耗用量", "实际耗用量",
				"实际耗用量", "实际耗用量", "实际耗用量", "月末库存" };
		ArrHeader[1] = new String[] { "单位", "品种", "分项", "期初库存", "数量", "价格构成",
				"价格构成", "小计", "其中", "其中", "其中", "其中", "其中", "其中", "月末库存" };
		ArrHeader[2] = new String[] { "单位", "品种", "分项", "期初库存", "数量", "含税油价",
				"含税运价", "小计", "发电", "供热", "其他", "盘盈亏", "损耗", "调出量", "月末库存" };
		ArrHeader[3] = new String[] { "甲", "乙", "丙", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "10", "11", "12" };
		int[] ArrWidth = new int[] { 80, 80, 80, 80, 80, 80, 80, 80, 80, 80,
				80, 80, 80, 80, 80 };
		Table titleTable = new Table(4, 15);
		titleTable.setBorderNone();
		for (int i = 1; i <= titleTable.getRows(); i++) {
			for (int j = 1; j <= titleTable.getCols(); j++) {
				titleTable.setCellBorderNone(i, j);
			}
		}
		titleTable.setWidth(ArrWidth);
		titleTable.setCellValue(4, 1,
				"填报单位:" + ((Diancxx)map.get("danwmc")).getQuanc(),4);
		titleTable.setCellValue(4,5,"填报日期:"+getTBRQ(map.get("riq").toString()),7);
		titleTable.setCellValue(4, titleTable.getCols() - 1, "单位:吨、元、% ", 2);

		titleTable.setCellAlign(4, 5, Table.ALIGN_CENTER);
		titleTable.setCellAlign(4, titleTable.getCols() - 1, Table.ALIGN_RIGHT);
		rt.setTitle(titleTable);
		rt.setTitle("国电电力发展股份有限公司生产用燃油供应、耗用与结存月报", 2);
		rt.setBody(new Table(arrData, 4, 0, 1, 15));
		rt.body.setHeaderData(ArrHeader);
		rt.body.setPageRows(15);
		int pageCount = rt.getPages();
		rt.body.ShowZero = false;
		rt.body.mergeFixedRowCol();
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		for(int i=4;i<=15;i++){
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "分管领导:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(6, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter((rt.footer.getCols() - 1), 1, "制表:",
				Table.ALIGN_RIGHT);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	/**
	 * 入炉煤热值
	 */
	@Override
	public JSONArray getRulmrz(Map<String, Object> map) {
		// TODO Auto-generated method stub
		// List<LinkedHashMap<String, Object>> list = guodbbDao.getRulmrz(map);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", "无此报表");
		resultMap.put("pageCount", 1);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	/**
	 * 燃料成本报表
	 */
	private StringBuffer getZbbStr(String riq ,String dianc,String fenl,int count,String danw1,String danw2,String danw3,String danw4,String xiangm1,String xiangm2,String xiangm3,String xiangm4,String zid1,String zid2,String zid3,String zid4){
		String diancxxb_id = dianc;
		String strDate=riq;
//		Visit visit = (Visit) getPage().getVisit();
		String tiaoj="";
		StringBuffer sql=new StringBuffer();
		sql.append(" select * from (");
	    sql.append(" select nvl('"+fenl+"','') fenl,\n");
	    sql.append(" nvl("+(++count)+",1) lanh,");
	    sql.append(" nvl('"+xiangm1+"','') mingc,\n");
	    sql.append(" nvl('"+danw1+"','') danw,\n");
	    sql.append(" ( select "+zid1+"   from yuezbb y,diancxxb d where y.diancxxb_id=d.id and  y.diancxxb_id in ("+diancxxb_id+") \n");
	    sql.append("  and substr(y.riq,0,7)='"+strDate+"' and fenx='本月' "+tiaoj+" ) benyz,");
	    sql.append(" ( select "+zid1+"   from yuezbb y,diancxxb d where y.diancxxb_id=d.id and  y.diancxxb_id in ("+diancxxb_id+") \n");
	    sql.append("  and substr(y.riq,0,7)='"+strDate+"' and fenx='累计' "+tiaoj+" ) leijz,");
	    sql.append(" '' beiz ");
	    sql.append(" from dual ");	    
	    sql.append(" union \n");	    
	    sql.append(" select nvl('"+fenl+"','') fenl,\n");
	    sql.append(" nvl("+(++count)+",1) lanh,");
	    sql.append(" nvl('"+xiangm2+"','') mingc,\n");
	    sql.append(" nvl('"+danw2+"','') danw,\n");
	    sql.append(" ( select "+zid2+"   from yuezbb y,diancxxb d where y.diancxxb_id=d.id and  y.diancxxb_id in ("+diancxxb_id+") \n");
	    sql.append("  and substr(y.riq,0,7)='"+strDate+"' and fenx='本月' "+tiaoj+" ) benyz,");
	    sql.append(" ( select "+zid2+"   from yuezbb y,diancxxb d where y.diancxxb_id=d.id and  y.diancxxb_id in ("+diancxxb_id+") \n");
	    sql.append("  and substr(substr(y.riq,0,7),0,7)='"+strDate+"' and fenx='累计' "+tiaoj+" ) leijz,");
	    sql.append(" '' beiz ");
	    sql.append(" from dual ");	    
	    sql.append(" union \n");	    
	    sql.append(" select nvl('"+fenl+"','') fenl,\n");
	    sql.append(" nvl("+(++count)+",1) lanh,");
	    sql.append(" nvl('"+xiangm3+"','') mingc,\n");
	    sql.append(" nvl('"+danw3+"','') danw,\n");
	    sql.append(" ( select "+zid3+"   from yuezbb y,diancxxb d where y.diancxxb_id=d.id and  y.diancxxb_id in ("+diancxxb_id+") \n");
	    sql.append("  and substr(y.riq,0,7)='"+strDate+"' and fenx='本月' "+tiaoj+" ) benyz,");
	    sql.append(" ( select "+zid3+"   from yuezbb y,diancxxb d where y.diancxxb_id=d.id and  y.diancxxb_id in ("+diancxxb_id+") \n");
	    sql.append("  and substr(y.riq,0,7)='"+strDate+"' and fenx='累计' "+tiaoj+" ) leijz,");
	    sql.append(" '' beiz ");
	    sql.append(" from dual ");	    
	    sql.append(" union \n");	    
	    sql.append(" select nvl('"+fenl+"','') fenl,\n");
	    sql.append(" nvl("+(++count)+",1) lanh,");
	    sql.append(" nvl('"+xiangm4+"','') mingc,\n");
	    sql.append(" nvl('"+danw4+"','') danw,\n");
	    sql.append(" ( select "+zid4+"   from yuezbb y,diancxxb d where y.diancxxb_id=d.id and  y.diancxxb_id in ("+diancxxb_id+") \n");
	    sql.append("  and substr(y.riq,0,7)='"+strDate+"' and fenx='本月' "+tiaoj+" ) benyz,");
	    sql.append(" ( select "+zid4+"   from yuezbb y,diancxxb d where y.diancxxb_id=d.id and  y.diancxxb_id in ("+diancxxb_id+") \n");
	    sql.append("  and substr(y.riq,0,7)='"+strDate+"' and fenx='累计' "+tiaoj+" ) leijz,");
	    sql.append(" '' beiz ");
	    sql.append(" from dual ");	    
	    sql.append(" ) order by lanh asc");
		return sql;
	}
	private StringBuffer getZbKucStr(String riq,String dianc,String fenl,int count,String danw1,String danw2,String danw3,String danw4,String xiangm1,String xiangm2,String xiangm3,String xiangm4,String zid1,String zid2,String zid3,String zid4){
		String diancxxb_id = dianc;
		String strDate=riq;
		String tiaoj="";
		StringBuffer sql=new StringBuffer();

		sql.append("SELECT FENL, LANH, MINGC, DANW, BENYZ, LEIJZ, BEIZ\n" );
		sql.append("  FROM (SELECT NVL('"+fenl+"', '') FENL,\n" );
		sql.append("               NVL("+(++count)+", 1) LANH,\n" );
		sql.append("               NVL('"+xiangm1+"', '') MINGC,\n" );
		sql.append("               NVL('"+danw1+"', '') DANW,\n" );
		sql.append("               (SELECT "+zid1+"\n" );
		sql.append("                  FROM YUEZBB Y, DIANCXXB D, YUESHCHJB H\n" );
		sql.append("                 WHERE Y.DIANCXXB_ID = D.ID\n" );
		sql.append("                   AND H.DIANCXXB_ID = Y.DIANCXXB_ID\n" );
		sql.append("                   AND H.RIQ = Y.RIQ\n" );
		sql.append("                   AND H.FENX = Y.FENX\n" );
		sql.append("                   AND Y.DIANCXXB_ID IN ("+diancxxb_id+")\n" );
		sql.append("                   AND substr(Y.RIQ,0,7) = '"+strDate+"'\n" );
		sql.append("                   AND Y.FENX = '本月' "+tiaoj+") BENYZ,\n" );
		sql.append("               (SELECT "+zid1+"\n" );
		sql.append("                  FROM YUEZBB Y, DIANCXXB D, YUESHCHJB H\n" );
		sql.append("                 WHERE Y.DIANCXXB_ID = D.ID\n" );
		sql.append("                   AND H.DIANCXXB_ID = Y.DIANCXXB_ID\n" );
		sql.append("                   AND H.RIQ = Y.RIQ\n" );
		sql.append("                   AND H.FENX = Y.FENX\n" );
		sql.append("                   AND Y.DIANCXXB_ID IN ("+diancxxb_id+")\n" );
		sql.append("                   AND Y.RIQ = '"+strDate+"'\n" );
		sql.append("                   AND Y.FENX = '累计' "+tiaoj+") LEIJZ,\n" );
		sql.append("               '' BEIZ\n" );
		sql.append("          FROM DUAL\n" );
		sql.append("        UNION\n" );
		sql.append("        SELECT NVL('"+fenl+"', '') FENL,\n" );
		sql.append("               NVL("+(++count)+", 1) LANH,\n" );
		sql.append("               NVL('"+xiangm2+"', '') MINGC,\n" );
		sql.append("               NVL('"+danw2+"', '') DANW,\n" );
		sql.append("               (SELECT "+zid2+"\n" );
		sql.append("                  FROM YUEZBB Y, DIANCXXB D, YUESHCHJB H\n" );
		sql.append("                 WHERE Y.DIANCXXB_ID = D.ID\n" );
		sql.append("                   AND H.DIANCXXB_ID = Y.DIANCXXB_ID\n" );
		sql.append("                   AND H.RIQ = Y.RIQ\n" );
		sql.append("                   AND H.FENX = Y.FENX\n" );
		sql.append("                   AND Y.DIANCXXB_ID IN ("+diancxxb_id+")\n" );
		sql.append("                   AND Y.RIQ = '"+strDate+"'\n" );
		sql.append("                   AND Y.FENX = '本月' "+tiaoj+") BENYZ,\n" );
		sql.append("               (SELECT "+zid2+"\n" );
		sql.append("                  FROM YUEZBB Y, DIANCXXB D, YUESHCHJB H\n" );
		sql.append("                 WHERE Y.DIANCXXB_ID = D.ID\n" );
		sql.append("                   AND H.DIANCXXB_ID = Y.DIANCXXB_ID\n" );
		sql.append("                   AND H.RIQ = Y.RIQ\n" );
		sql.append("                   AND H.FENX = Y.FENX\n" );
		sql.append("                   AND Y.DIANCXXB_ID IN ("+diancxxb_id+")\n" );
		sql.append("                   AND Y.RIQ = '"+strDate+"'\n" );
		sql.append("                   AND Y.FENX = '累计' "+tiaoj+") LEIJZ,\n" );
		sql.append("               '' BEIZ\n" );
		sql.append("          FROM DUAL\n" );
		sql.append("        UNION\n" );
		sql.append("        SELECT NVL('"+fenl+"', '') FENL,\n" );
		sql.append("               NVL("+(++count)+", 1) LANH,\n" );
		sql.append("               NVL('"+xiangm3+"', '') MINGC,\n" );
		sql.append("               NVL('"+danw3+"', '') DANW,\n" );
		sql.append("               (SELECT "+zid3+"\n" );
		sql.append("                  FROM YUEZBB Y, DIANCXXB D, YUESHCHJB H\n" );
		sql.append("                 WHERE Y.DIANCXXB_ID = D.ID\n" );
		sql.append("                   AND H.DIANCXXB_ID = Y.DIANCXXB_ID\n" );
		sql.append("                   AND H.RIQ = Y.RIQ\n" );
		sql.append("                   AND H.FENX = Y.FENX\n" );
		sql.append("                   AND Y.DIANCXXB_ID IN ("+diancxxb_id+")\n" );
		sql.append("                   AND Y.RIQ = '"+strDate+"'\n" );
		sql.append("                   AND Y.FENX = '本月' "+tiaoj+") BENYZ,\n" );
		sql.append("               (SELECT "+zid3+"\n" );
		sql.append("                  FROM YUEZBB Y, DIANCXXB D, YUESHCHJB H\n" );
		sql.append("                 WHERE Y.DIANCXXB_ID = D.ID\n" );
		sql.append("                   AND H.DIANCXXB_ID = Y.DIANCXXB_ID\n" );
		sql.append("                   AND H.RIQ = Y.RIQ\n" );
		sql.append("                   AND H.FENX = Y.FENX\n" );
		sql.append("                   AND Y.DIANCXXB_ID IN ("+diancxxb_id+")\n" );
		sql.append("                   AND Y.RIQ = '"+strDate+"'\n" );
		sql.append("                   AND Y.FENX = '累计' "+tiaoj+") LEIJZ,\n" );
		sql.append("               '' BEIZ\n" );
		sql.append("          FROM DUAL\n" );
		sql.append("        UNION\n" );
		sql.append("        SELECT NVL('"+fenl+"', '') FENL,\n" );
		sql.append("               NVL("+(++count)+", 1) LANH,\n" );
		sql.append("               NVL('"+xiangm4+"', '') MINGC,\n" );
		sql.append("               NVL('"+danw4+"', '') DANW,\n" );
		sql.append("               (SELECT "+zid4+"\n" );
		sql.append("                  FROM YUEZBB Y, DIANCXXB D, YUESHCHJB H\n" );
		sql.append("                 WHERE Y.DIANCXXB_ID = D.ID\n" );
		sql.append("                   AND H.DIANCXXB_ID = Y.DIANCXXB_ID\n" );
		sql.append("                   AND H.RIQ = Y.RIQ\n" );
		sql.append("                   AND H.FENX = Y.FENX\n" );
		sql.append("                   AND Y.DIANCXXB_ID IN ("+diancxxb_id+")\n" );
		sql.append("                   AND Y.RIQ = '"+strDate+"'\n" );
		sql.append("                   AND Y.FENX = '本月' "+tiaoj+") BENYZ,\n" );
		sql.append("               (SELECT "+zid4+"\n" );
		sql.append("                  FROM YUEZBB Y, DIANCXXB D, YUESHCHJB H\n" );
		sql.append("                 WHERE Y.DIANCXXB_ID = D.ID\n" );
		sql.append("                   AND H.DIANCXXB_ID = Y.DIANCXXB_ID\n" );
		sql.append("                   AND H.RIQ = Y.RIQ\n" );
		sql.append("                   AND H.FENX = Y.FENX\n" );
		sql.append("                   AND Y.DIANCXXB_ID IN ("+diancxxb_id+")\n" );
		sql.append("                   AND Y.RIQ = '"+strDate+"'\n" );
		sql.append("                   AND Y.FENX = '累计' "+tiaoj+") LEIJZ,\n" );
		sql.append("               '' BEIZ\n" );
		sql.append("          FROM DUAL)\n" );
		sql.append(" ORDER BY LANH ASC");
		return sql;
	}

	@Override
	public JSONArray getRanlcbbb(Map<String, Object> map) {
		// TODO Auto-generated method stub
		//List<LinkedHashMap<String, Object>> list = guodbbDao.getRanlcbbb(map);
//=============================================================================================================================
/*		String _Danwqc = getTianzdwQuanc();
		JDBCcon cn = new JDBCcon();
		String strMonth=getNianfValue().getValue()+" 年 "+getYuefValue().getValue()+" 月";*/
		 
		//定义表头数据

/*		 String ArrHeader[][]=new String[1][8];
		 ArrHeader[0]=new String[] {"项目分类","栏号","项目名称","计量单位","本月数值","年累计值","备注"};
		 int ArrWidth[]=new int[] {160,80,160,80,100,100,100};
		
		Report rt=new Report();
		rt.setTitle("国电电力发展股份有限公司发电、供热耗用燃料成本月报(不含税)", ArrWidth);	
			
	    rt.setDefaultTitle(1, 2, "单位:"+_Danwqc, Table.ALIGN_LEFT);
		rt.setDefaultTitle(3, 3, strMonth, Table.ALIGN_CENTER);
		rt.setDefaultTitle(6, 2, "国电燃07表", Table.ALIGN_RIGHT);
		
		rt.setMarginBottom(rt.getMarginBottom()+25);*/
		
//		数据
		
//		发电供热燃料费
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		StringBuffer sql=new StringBuffer();
		
		String fenl="发电供热燃料费";
		String danw1="元";
		String danw2="元";
		String danw3="元";
		String danw4="元";
		int count=0;
		String xiangm1="发电供热总燃料费";
		String xiangm2="其中：燃煤";
		String xiangm3="燃油";
		String xiangm4="燃气";		
		String zid1="sum(DECODE(Y.DIANCXXB_ID,215,(RANLCB_BHS)*0.4,RANLCB_BHS))";
//		String zid1="y.FADMCB+ FADYCB+ FADRQCB+ GONGRMCB+ GONGRYCB+ GONGRRQCB";
		String zid2="sum(DECODE(Y.DIANCXXB_ID,215,(y.FADMCB+y.GONGRMCB+ Y.QIZ_RANM)*0.4,(y.FADMCB+y.GONGRMCB+ Y.QIZ_RANM)))";
		String zid3="sum(DECODE(Y.DIANCXXB_ID,215,(y.FADYCB+y.GONGRYCB+Y.QIZ_RANY)*0.4,(y.FADYCB+y.GONGRYCB+Y.QIZ_RANY)))";
		String zid4="sum(DECODE(Y.DIANCXXB_ID,215,(y.FADRQCB+y.GONGRRQCB+ Y.QIZ_RANQ)*0.4,(y.FADRQCB+y.GONGRRQCB+ Y.QIZ_RANQ)))";
		String riq=map.get("riq").toString();
		String dianc=map.get("dianc").toString();
		sql=this.getZbbStr(riq,dianc, fenl, count, danw1,danw2,danw3,danw4, xiangm1, xiangm2, xiangm3, xiangm4, zid1, zid2, zid3, zid4);
		List<Map<String,Object>> fadgrlist=jdbcTemplate.queryForList(sql.toString());
		list.addAll(fadgrlist);
/*		ResultSetList rs=cn.getResultSetList(sql.toString());
		
		rt.setBody(new Table(rs,1,0,3));*/
		
		
//		发电燃料费
		
		fenl="发电燃料费";
		danw1="元";
		danw2="元";
		danw3="元";
		danw4="元";
//		rs.beforefirst();
//		count+=rs.getRows();
		count+=fadgrlist.size();
		xiangm1="发电燃料费";
		xiangm2="其中：燃煤";
		xiangm3="燃油";
		xiangm4="燃气";
		zid1="sum(DECODE(Y.DIANCXXB_ID,215,(y.FADMCB+y.FADYCB+y.FADRQCB)*0.4,y.FADMCB+y.FADYCB+y.FADRQCB))";
		zid2="sum(DECODE(Y.DIANCXXB_ID,215,y.FADMCB*0.4,y.FADMCB))";
		zid3="sum(DECODE(Y.DIANCXXB_ID,215,y.FADYCB*0.4,y.FADYCB))";
		zid4="sum(DECODE(Y.DIANCXXB_ID,215,y.FADRQCB*0.4,y.FADRQCB))";

		sql=this.getZbbStr(riq,dianc, fenl, count,danw1,danw2,danw3,danw4, xiangm1, xiangm2, xiangm3, xiangm4, zid1, zid2, zid3, zid4);
		List<Map<String,Object>> fadrlf=jdbcTemplate.queryForList(sql.toString());
		list.addAll(fadrlf);
		/*rs=cn.getResultSetList(sql.toString());
		rt.body.AddTableData(rs);*/
		
		
		
//		供热用燃料费
		
		fenl="供热用燃料费";
		danw1="元";
		danw2="元";
		danw3="元";
		danw4="元";
//		rs.beforefirst();
//		count+=rs.getRows();
		count+=fadrlf.size();
		xiangm1="供热用燃料费";
		xiangm2="其中：燃煤";
		xiangm3="燃油";
		xiangm4="燃气";
		zid1="sum(DECODE(Y.DIANCXXB_ID,215,(y.GONGRMCB+y.GONGRYCB+y.GONGRRQCB)*0.4,(y.GONGRMCB+y.GONGRYCB+y.GONGRRQCB)))";
		zid2="sum(DECODE(Y.DIANCXXB_ID,215,y.GONGRMCB*0.4,y.GONGRMCB))";
		zid3="sum(DECODE(Y.DIANCXXB_ID,215,y.GONGRYCB*0.4,y.GONGRYCB))";
		zid4="sum(DECODE(Y.DIANCXXB_ID,215,y.GONGRRQCB*0.4,y.GONGRRQCB))";

		sql=this.getZbbStr(riq,dianc, fenl, count, danw1,danw2,danw3,danw4, xiangm1, xiangm2, xiangm3, xiangm4, zid1, zid2, zid3, zid4);
	/*	rs=cn.getResultSetList(sql.toString());
		rt.body.AddTableData(rs);*/
		List<Map<String,Object>> gongrylist=jdbcTemplate.queryForList(sql.toString());
		list.addAll(gongrylist);
//		供热用电分摊燃料费
		
		fenl="供热用电分摊燃料费";
		danw1="元";
		danw2="元";
		danw3="元";
		danw4="元";
//		rs.beforefirst();
//		count+=rs.getRows();
		count+=gongrylist.size();
		xiangm1="供热用电分摊燃料费";
		xiangm2="其中：燃煤";
		xiangm3="燃油";
		xiangm4="燃气";
		zid1="sum(DECODE(Y.DIANCXXB_ID,215,(y.GONGRCYDFTRLF)*0.4,(y.GONGRCYDFTRLF)))";
		zid2="sum(DECODE(Y.DIANCXXB_ID,215,(y.QIZ_RANM)*0.4,y.QIZ_RANM))";
		zid3="sum(DECODE(Y.DIANCXXB_ID,215,(y.QIZ_RANY)*0.4,y.QIZ_RANY))";
		zid4="sum(DECODE(Y.DIANCXXB_ID,215,(y.QIZ_RANQ)*0.4,y.QIZ_RANQ))";

		sql=this.getZbbStr(riq,dianc, fenl, count, danw1,danw2,danw3,danw4, xiangm1, xiangm2, xiangm3, xiangm4, zid1, zid2, zid3, zid4);
//		rs=cn.getResultSetList(sql.toString());
//		rt.body.AddTableData(rs);
//		发电供热总标煤量
		List<Map<String,Object>> gongrydftlist=jdbcTemplate.queryForList(sql.toString());
		list.addAll(gongrydftlist);
		fenl="发电供热总标煤量";
		danw1="吨";
		danw2="吨";
		danw3="吨";
		danw4="吨";
//		rs.beforefirst();
//		count+=rs.getRows();
		count+=gongrydftlist.size();
		xiangm1="发电供热总标煤量";
		xiangm2="其中：煤折标煤量";
		xiangm3="油折标煤量";
		xiangm4="气折标煤量";
		zid1="sum(DECODE(Y.DIANCXXB_ID,215,(y.FADMZBML+y.FADYZBZML+y.FADQZBZML+y.GONGRMZBML+y.GONGRYZBZML+y.GONGRQZBZML)*0.4,(y.FADMZBML+y.FADYZBZML+y.FADQZBZML+y.GONGRMZBML+y.GONGRYZBZML+y.GONGRQZBZML)))";
		zid2="sum(DECODE(Y.DIANCXXB_ID,215,(y.FADMZBML+y.GONGRMZBML)*0.4,(y.FADMZBML+y.GONGRMZBML)))";
		zid3="sum(DECODE(Y.DIANCXXB_ID,215,(y.FADYZBZML+y.GONGRYZBZML)*0.4,(y.FADYZBZML+y.GONGRYZBZML)))";
		zid4="sum(DECODE(Y.DIANCXXB_ID,215,(y.FADQZBZML+y.GONGRQZBZML)*0.4,(y.FADQZBZML+y.GONGRQZBZML)))";

		sql=this.getZbbStr(riq,dianc, fenl, count,danw1,danw2,danw3,danw4,xiangm1, xiangm2, xiangm3, xiangm4, zid1, zid2, zid3, zid4);
//		rs=cn.getResultSetList(sql.toString());
//		rt.body.AddTableData(rs);	
		List<Map<String,Object>> fadgrzbml=jdbcTemplate.queryForList(sql.toString());
		list.addAll(fadgrzbml);
//		发电用标煤量
		
		fenl="发电用标煤量";
		danw1="吨";
		danw2="吨";
		danw3="吨";
		danw4="吨";
//		rs.beforefirst();
//		count+=rs.getRows();
		count+=fadgrzbml.size();
		xiangm1="发电用标煤量";
		xiangm2="其中：煤折标煤量";
		xiangm3="油折标煤量";
		xiangm4="气折标煤量";
		zid1="sum(DECODE(Y.DIANCXXB_ID,215,(y.FADMZBML+y.FADYZBZML+y.FADQZBZML)*0.4,(y.FADMZBML+y.FADYZBZML+y.FADQZBZML)))";
		zid2="sum(DECODE(Y.DIANCXXB_ID,215,y.FADMZBML*0.4,y.FADMZBML))";
		zid3="sum(DECODE(Y.DIANCXXB_ID,215,y.FADYZBZML*0.4,y.FADYZBZML))";
		zid4="sum(DECODE(Y.DIANCXXB_ID,215,y.FADQZBZML*0.4,y.FADQZBZML))";

		sql=this.getZbbStr(riq,dianc, fenl, count, danw1,danw2,danw3,danw4, xiangm1, xiangm2, xiangm3, xiangm4, zid1, zid2, zid3, zid4);
//		rs=cn.getResultSetList(sql.toString());
//		rt.body.AddTableData(rs);
		List<Map<String,Object>> fadybml=jdbcTemplate.queryForList(sql.toString());
		list.addAll(fadybml);
//		供热用标煤量
		
		fenl="供热用标煤量";
		danw1="吨";
		danw2="吨";
		danw3="吨";
		danw4="吨";
//		rs.beforefirst();
//		count+=rs.getRows();
		count+=fadybml.size();
		xiangm1="供热用标煤量";
		xiangm2="其中：煤折标煤量";
		xiangm3="油折标煤量";
		xiangm4="气折标煤量";
		zid1="sum(DECODE(Y.DIANCXXB_ID,215,(y.GONGRMZBML+y.GONGRYZBZML+y.GONGRQZBZML)*0.4,(y.GONGRMZBML+y.GONGRYZBZML+y.GONGRQZBZML)))";
		zid2="sum(DECODE(Y.DIANCXXB_ID,215,y.GONGRMZBML*0.4,y.GONGRMZBML))";
		zid3="sum(DECODE(Y.DIANCXXB_ID,215,y.GONGRYZBZML*0.4,y.GONGRYZBZML))";
		zid4="sum(DECODE(Y.DIANCXXB_ID,215,y.GONGRQZBZML*0.4,y.GONGRQZBZML))";

		sql=this.getZbbStr(riq,dianc, fenl, count, danw1,danw2,danw3,danw4, xiangm1, xiangm2, xiangm3, xiangm4, zid1, zid2, zid3, zid4);
//		rs=cn.getResultSetList(sql.toString());
//		rt.body.AddTableData(rs);	
		List<Map<String,Object>> gongrybml=jdbcTemplate.queryForList(sql.toString());
		list.addAll(gongrybml);
//		入炉综合标煤单价
		
		fenl="入炉综合标煤单价";
		danw1="元/吨";
		danw2="元/吨";
		danw3="元/吨";
		danw4="元/吨";
//		rs.beforefirst();
//		count+=rs.getRows();
		count+=gongrybml.size();
		xiangm1="入炉综合标煤单价";
		xiangm2="其中：煤折标煤单价";
		xiangm3="油折标煤单价";
		xiangm4="气折标煤单价"; 
		zid1="decode(sum(DECODE(Y.DIANCXXB_ID,215,nvl(Rulmzbzml+RULYZBZML+RULQZBZML,0)*0.4,nvl(Rulmzbzml+RULYZBZML+RULQZBZML,0))),0,0," +
				"round_new(sum(Y.RULZHBMDJ*DECODE(Y.DIANCXXB_ID,215,nvl(Rulmzbzml+RULYZBZML+RULQZBZML,0)*0.4,nvl(Rulmzbzml+RULYZBZML+RULQZBZML,0)))" +
				"/sum(DECODE(Y.DIANCXXB_ID,215,nvl(Rulmzbzml+RULYZBZML+RULQZBZML,0)*0.4,nvl(Rulmzbzml+RULYZBZML+RULQZBZML,0))),2) )";

		zid2="decode( sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.FADMZBML+y.GONGRMZBML,0)*0.4,nvl(y.FADMZBML+y.GONGRMZBML,0))),0,0, " +
				"round_new(sum(DECODE(Y.DIANCXXB_ID,215,(Y.FADMCB + Y.GONGRMCB + Y.QIZ_RANM)*0.4,(Y.FADMCB + Y.GONGRMCB + Y.QIZ_RANM)))" +
				"/sum(DECODE(Y.DIANCXXB_ID,215,(y.FADMZBML+y.GONGRMZBML)*0.4,y.FADMZBML+y.GONGRMZBML)),2) )";
		
		zid3="decode(sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.FADYZBZML+y.GONGRYZBZML,0)*0.4,nvl(y.FADYZBZML+y.GONGRYZBZML,0))),0,0, " +
				"round_new(sum(DECODE(Y.DIANCXXB_ID,215,(Y.FADYCB + Y.GONGRYCB +Y.QIZ_RANY)*0.4,Y.FADYCB + Y.GONGRYCB +Y.QIZ_RANY))" +
				"/sum(DECODE(Y.DIANCXXB_ID,215,(y.FADYZBZML+y.GONGRYZBZML)*0.4,y.FADYZBZML+y.GONGRYZBZML)),2) )";
		zid4="decode( sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.FADQZBZML+y.GONGRQZBZML,0)*0.4,nvl(y.FADQZBZML+y.GONGRQZBZML,0))),0,0, " +
				"round_new(sum(DECODE(Y.DIANCXXB_ID,215,(Y.FADRQCB + Y.GONGRRQCB + Y.QIZ_RANQ)*0.4,(Y.FADRQCB + Y.GONGRRQCB + Y.QIZ_RANQ)))/" +
				"sum(DECODE(Y.DIANCXXB_ID,215,(y.FADQZBZML+y.GONGRQZBZML)*0.4,(y.FADQZBZML+y.GONGRQZBZML))),2) )";

		sql=this.getZbbStr(riq,dianc, fenl, count, danw1,danw2,danw3,danw4, xiangm1, xiangm2, xiangm3, xiangm4, zid1, zid2, zid3, zid4);
//		rs=cn.getResultSetList(sql.toString());
//		rt.body.AddTableData(rs);	
		List<Map<String,Object>> rulzhbmdj=jdbcTemplate.queryForList(sql.toString());
		list.addAll(rulzhbmdj);
//		发电标煤单价
		
		fenl="发电标煤单价";
		danw1="元/吨";
		danw2="元/吨";
		danw3="元/吨";
		danw4="元/吨";
//		rs.beforefirst();
//		count+=rs.getRows();
		count+=rulzhbmdj.size();
		xiangm1="发电标煤单价";
		xiangm2="其中：煤折标煤单价";
		xiangm3="油折标煤单价";
		xiangm4="气折标煤单价";
		zid1="decode(sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.FADMZBML+y.FADYZBZML+y.FADQZBZML,0)*0.4,nvl(y.FADMZBML+y.FADYZBZML+y.FADQZBZML,0))),0,0," +
				"round_new(sum(FADBZMDJ*DECODE(Y.DIANCXXB_ID,215,nvl(y.FADMZBML+y.FADYZBZML+y.FADQZBZML,0)*0.4,nvl(y.FADMZBML+y.FADYZBZML+y.FADQZBZML,0)))/" +
				"sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.FADMZBML+y.FADYZBZML+y.FADQZBZML,0)*0.4,nvl(y.FADMZBML+y.FADYZBZML+y.FADQZBZML,0))),2))";
		zid2="decode(sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.FADMZBML,0)*0.4,nvl(y.FADMZBML,0))),0,0," +
				"round_new(sum(QIZ_MEIZBMDJ*DECODE(Y.DIANCXXB_ID,215,nvl(y.FADMZBML,0)*0.4,nvl(y.FADMZBML,0)))/sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.FADMZBML,0)*0.4,nvl(y.FADMZBML,0))),2))";
		zid3="decode(sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.FADYZBZML,0)*0.4,nvl(y.FADYZBZML,0))),0,0," +
				"round_new(sum(QIZ_YOUZBMDJ*DECODE(Y.DIANCXXB_ID,215,nvl(y.FADYZBZML,0)*0.4,nvl(y.FADYZBZML,0)))/sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.FADYZBZML,0)*0.4,nvl(y.FADYZBZML,0))),2))";
		zid4="decode(sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.FADQZBZML,0)*0.4,nvl(y.FADQZBZML,0))),0,0," +
				"round_new(sum(QIZ_QIZBMDJ*DECODE(Y.DIANCXXB_ID,215,nvl(y.FADQZBZML,0)*0.4,nvl(y.FADQZBZML,0)))/sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.FADQZBZML,0)*0.4,nvl(y.FADQZBZML,0))),2))";

		sql=this.getZbbStr(riq,dianc, fenl, count, danw1,danw2,danw3,danw4, xiangm1, xiangm2, xiangm3, xiangm4, zid1, zid2, zid3, zid4);
//		rs=cn.getResultSetList(sql.toString());
//		rt.body.AddTableData(rs);	
		List<Map<String,Object>> fadbmdj=jdbcTemplate.queryForList(sql.toString());
		list.addAll(fadbmdj);
//		供热标煤单价
		
		fenl="供热标煤单价";
		danw1="元/吨";
		danw2="元/吨";
		danw3="元/吨";
		danw4="元/吨";
//		rs.beforefirst();
//		count+=rs.getRows();
		count+=fadbmdj.size();
		xiangm1="供热标煤单价";
		xiangm2="其中：煤折标煤单价";
		xiangm3="油折标煤单价";
		xiangm4="气折标煤单价";
		zid1="decode(sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.GONGRMZBML+y.GONGRYZBZML+y.GONGRQZBZML,0)*0.4,nvl(y.GONGRMZBML+y.GONGRYZBZML+y.GONGRQZBZML,0))),0,0," +
				"round_new(sum(GONGRBZMDJ*DECODE(Y.DIANCXXB_ID,215,nvl(y.GONGRMZBML+y.GONGRYZBZML+y.GONGRQZBZML,0)*0.4,nvl(y.GONGRMZBML+y.GONGRYZBZML+y.GONGRQZBZML,0)))" +
				"/sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.GONGRMZBML+y.GONGRYZBZML+y.GONGRQZBZML,0)*0.4,nvl(y.GONGRMZBML+y.GONGRYZBZML+y.GONGRQZBZML,0))),2))";
		zid2="decode(sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.GONGRMZBML,0)*0.4,nvl(y.GONGRMZBML,0))),0,0," +
				"round_new(sum(QIZ_MEIZBMDJ_GR*DECODE(Y.DIANCXXB_ID,215,nvl(y.GONGRMZBML,0)*0.4,nvl(y.GONGRMZBML,0)))/sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.GONGRMZBML,0)*0.4,nvl(y.GONGRMZBML,0))),2))";
		zid3="decode(sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.GONGRYZBZML,0)*0.4,nvl(y.GONGRYZBZML,0))),0,0," +
				"round_new(sum(QIZ_YOUZBMDJ_GR*DECODE(Y.DIANCXXB_ID,215,nvl(y.GONGRYZBZML,0)*0.4,nvl(y.GONGRYZBZML,0)) )/sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.GONGRYZBZML,0)*0.4,nvl(y.GONGRYZBZML,0))),2))";
		zid4="decode(sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.GONGRQZBZML,0)*0.4,nvl(y.GONGRQZBZML,0))),0,0," +
				"round_new(sum(QIZ_QIZBMDJ_GR*DECODE(Y.DIANCXXB_ID,215,nvl(y.GONGRQZBZML,0)*0.4,nvl(y.GONGRQZBZML,0)))/sum(DECODE(Y.DIANCXXB_ID,215,nvl(y.GONGRQZBZML,0)*0.4,nvl(y.GONGRQZBZML,0))),2))";
		
		sql=this.getZbbStr(riq,dianc, fenl, count, danw1,danw2,danw3,danw4, xiangm1, xiangm2, xiangm3, xiangm4, zid1, zid2, zid3, zid4);
//		rs=cn.getResultSetList(sql.toString());
//		rt.body.AddTableData(rs);
		List<Map<String,Object>> gongrbmdj=jdbcTemplate.queryForList(sql.toString());
		list.addAll(gongrbmdj);
//		生产指标
		fenl="生产指标";
		danw1="万千瓦时";
		danw2="克/千瓦时";
		danw3="吉焦";
		danw4="千克/吉焦";
//		rs.beforefirst();
//		count+=rs.getRows();
		count+=gongrbmdj.size();
		xiangm1="发电量";
		xiangm2="发电煤耗";
		xiangm3="供热量";
		xiangm4="供热煤耗";
		zid1="sum(DECODE(Y.DIANCXXB_ID,215,y.FADL*0.4,y.FADL))";
		zid2="Round(DECODE (sum(DECODE(Y.DIANCXXB_ID,215,y.FADL*0.4,y.FADL)) ,0,0,sum(DECODE(Y.DIANCXXB_ID,215,(y.FADYZBZML+y.FADMZBML)*0.4,y.FADYZBZML+y.FADMZBML))*100/ sum(DECODE(Y.DIANCXXB_ID,215,y.FADL*0.4,y.FADL))),2) ";
		zid3="sum(DECODE(Y.DIANCXXB_ID,215,GONGRL*0.4,GONGRL))";
		zid4="Round(DECODE(sum(DECODE(Y.DIANCXXB_ID,215,y.GONGRL*0.4,y.GONGRL)),0,0, sum(DECODE(Y.DIANCXXB_ID,215,(y.GONGRMZBML+ y.GONGRYZBZML)*0.4,(y.GONGRMZBML+ y.GONGRYZBZML)))*1000/ sum(DECODE(Y.DIANCXXB_ID,215,y.GONGRL*0.4,y.GONGRL))),2) ";

		sql=this.getZbbStr(riq,dianc, fenl, count, danw1,danw2,danw3,danw4, xiangm1, xiangm2, xiangm3, xiangm4, zid1, zid2, zid3, zid4);
//		rs=cn.getResultSetList(sql.toString());
//		rt.body.AddTableData(rs);
		List<Map<String,Object>> shengczb=jdbcTemplate.queryForList(sql.toString());
		list.addAll(shengczb);
//		库存指标
		fenl="库存指标";
		danw1="吨";
		danw2="兆焦/千克";
		danw3="元/吨";
		danw4="元/吨";
//		rs.beforefirst();
//		count+=rs.getRows();
		count+=shengczb.size();
		xiangm1="库存量";
		xiangm2="库存天然煤热值";
		xiangm3="库存天然煤单价(不含税)";
		xiangm4="库存标煤单价(不含税)";
		zid1="SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC))";
		zid2="ROUND(DECODE(SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC)),0,0,SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC)*Y.KUCTRMRZ)/SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC))),2)KUCTRMRZ";
		zid3="ROUND(DECODE(SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC)),0,0,SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC)*Y.KUCTRMJ)/SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC))),2)KUCTRMJ";
		zid4="ROUND(DECODE(ROUND(DECODE(SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC)),0,0,SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC)*Y.KUCTRMRZ)/SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC))),2),0,0,\n" +
			"           ROUND(DECODE(SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC)),0,0,SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC)*Y.KUCTRMJ)/SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC))),2)*29.271/\n" + 
			"           ROUND(DECODE(SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC)),0,0,SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC)*Y.KUCTRMRZ)/SUM(DECODE(Y.DIANCXXB_ID, 215, H.KUC * 0.4, H.KUC))),2)),2)BMDJ";

//		得到库存天然煤信息
		sql=this.getZbKucStr(riq,dianc, fenl, count, danw1,danw2,danw3,danw4, xiangm1, xiangm2, xiangm3, xiangm4, zid1, zid2, zid3, zid4);
//		rs=cn.getResultSetList(sql.toString());
		List<Map<String,Object>> kuczb=jdbcTemplate.queryForList(sql.toString());
		list.addAll(kuczb);
/*		rt.body.AddTableData(rs);
		
		rt.body.setWidth(ArrWidth);
		rt.body.setPageRows(-1);
		rt.body.setHeaderData(ArrHeader);//表头数据
		rt.body.mergeFixedRow();
		rt.getPages();
		
		rt.body.mergeCol(1);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		rt.body.setColAlign(4, Table.ALIGN_CENTER);
		rt.body.setColAlign(5, Table.ALIGN_CENTER);
		rt.body.setColAlign(6, Table.ALIGN_CENTER);
		rt.body.setColAlign(7, Table.ALIGN_CENTER);
		rt.body.ShowZero=reportShowZero();
	
		//页脚
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2,"分管领导:",Table.ALIGN_RIGHT);
		rt.setDefautlFooter(3, 1, "审核:", Table.ALIGN_RIGHT);
		rt.setDefautlFooter(5, 1, "制表:", Table.ALIGN_CENTER);
		rt.setDefautlFooter(rt.footer.getCols()-1,2,Table.PAGENUMBER_CHINA,Table.ALIGN_RIGHT );
	
		_CurrentPage=1;
		_AllPages=rt.getPages();
		if (_AllPages==0){
			_CurrentPage=0;
		}
		cn.Close();		
		return rt.getAllPagesHtml();			
		*/
		
//------------------------------------------------------------------------------------------------------------------		
/*		String[][] arrData = new String[list.size()][7];
		for (int i = 0; i < list.size(); i++) {
			int j = 0;
			Iterator<String> it = list.get(i).keySet().iterator();
			while (it.hasNext()) {
				arrData[i][j++] = list.get(i).get(it.next()).toString();
			}
		}*/
		String[][] arrData=Result.list2array(list, 7);
		Report rt = new Report();
		String[][] ArrHeader = new String[1][7];
		ArrHeader[0] = new String[] { "项目分类", "栏号", "项目名称", "计量单位", "本月数值",
				"年累计值", "备注" };

		int[] ArrWidth = new int[] { 150, 80, 150, 80, 80, 80, 80 };
		
		Table titleTable = new Table(4, 7);
		titleTable.setBorderNone();
		for (int i = 1; i <= titleTable.getRows(); i++) {
			for (int j = 1; j <= titleTable.getCols(); j++) {
				titleTable.setCellBorderNone(i, j);
			}
		}
		titleTable.setWidth(ArrWidth);
		titleTable.setCellValue(4, 1,"单位:" + ((Diancxx)map.get("danwmc")).getQuanc(),3);
		titleTable.setCellValue(4, 4,getTBRQ(map.get("riq").toString()),1);
		titleTable.setCellValue(4, titleTable.getCols() - 1, "国电燃07表", 2);

		titleTable.setCellAlign(4, 1, Table.ALIGN_LEFT);
		titleTable.setCellAlign(4, 4, Table.ALIGN_CENTER);
		titleTable.setCellAlign(4, titleTable.getCols() - 1, Table.ALIGN_RIGHT);
		
		rt.setTitle(titleTable);
		
		rt.setTitle("国电电力发展股份有限公司发电、供热耗用燃料成本月报(不含税)", 1);
		rt.setBody(new Table(arrData, 1, 0, 0, 7));
		rt.body.setHeaderData(ArrHeader);
		rt.body.setPageRows(20);
		int pageCount = rt.getPages();
		rt.body.ShowZero = false;
		rt.body.mergeCol(1);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		rt.body.setColAlign(4, Table.ALIGN_CENTER);
		rt.body.setColAlign(5, Table.ALIGN_RIGHT);
		rt.body.setColAlign(6, Table.ALIGN_RIGHT);
		rt.body.setColAlign(7, Table.ALIGN_CENTER);
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "分管领导:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(4, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter((rt.footer.getCols() - 1), 1, "制表:",
				Table.ALIGN_RIGHT);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	/**
	 * 厂内费用查询
	 */
	@Override
	public JSONArray getChangnfycx(Map<String, Object> map) {
		String eriq=map.get("riq").toString();
		String sriq=eriq.substring(0,4)+"-01";
		map.put("eriq",eriq);
		map.put("sriq",sriq);
		String lastYearERiq=DateUtil.getLastYearString(eriq);
		map.put("leriq", lastYearERiq);
		String lastYearSRiq=DateUtil.getLastYearString(sriq);
		map.put("lsriq",lastYearSRiq);
		List<LinkedHashMap<String, Object>> list = guodbbDao.getChangnfycxMX(map);

		String[][] arrData = new String[list.size()][14];
		for (int i = 0; i < list.size(); i++) {
			int j = 0;
			Iterator<String> it = list.get(i).keySet().iterator();
			while (it.hasNext()) {
				arrData[i][j++] = list.get(i).get(it.next()).toString();
			}
		}

		Report rt = new Report();
		String[][] ArrHeader = new String[2][14];
		ArrHeader[0] = new String[] { "单位名称", "杂费名称", "当月金额", "当月金额", "当月金额",
				"当月金额", "当月金额", "当月金额", "累计金额", "累计金额", "累计金额", "累计金额", "累计金额",
				"累计金额" };
		ArrHeader[1] = new String[] { "单位名称", "杂费名称", "本期费用", "本期吨</br>煤费用",
				"同期费用", "同期吨</br>煤费用", "费用同比", "吨煤费</br>用同比", "本期费用",
				"本期吨</br>煤费用", "同期费用", "同期吨</br>煤费用", "费用同比", "吨煤费</br>用同比" };

		int[] ArrWidth = new int[] { 80, 80, 80, 80, 80, 80, 80, 80, 80, 80,
				80, 80, 80, 80 };
		Table titleTable = new Table(3, 14);
		titleTable.setBorderNone();
		for (int i = 1; i <= titleTable.getRows(); i++) {
			for (int j = 1; j <= titleTable.getCols(); j++) {
				titleTable.setCellBorderNone(i, j);
			}
		}
		titleTable.setWidth(ArrWidth);
		titleTable.setCellValue(3, 1,
				"填报单位(盖章):" + ((Diancxx)map.get("danwmc")).getQuanc(),
				4);
		titleTable.setCellValue(
				3,
				5,
				"填报日期:"
						+ getTBRQ(eriq),
				5);
		titleTable.setCellValue(3, titleTable.getCols() - 1, "单位:元、元/吨", 2);

		titleTable.setCellAlign(3, titleTable.getCols() - 1, Table.ALIGN_RIGHT);
		titleTable.setCellAlign(3, 1, Table.ALIGN_CENTER);
		titleTable.setCellAlign(3, titleTable.getCols() - 1, Table.ALIGN_RIGHT);
		rt.setTitle(titleTable);
		rt.setTitle(getTBRQ(eriq)+"杂费查询表", 2);
		rt.setBody(new Table(arrData, 2, 0, 1, 14));
		rt.body.setHeaderData(ArrHeader);
		rt.body.setPageRows(15);
		int pageCount = rt.getPages();
		rt.body.mergeFixedRowCol();
//		rt.body.mergeCell(4, 1, list.size()-1, 1);
//		rt.body.mergeFixedRow();
		rt.body.mergeFixedCols();
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		for(int i=3;i<=14;i++){
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "打印日期:"+DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE), Table.ALIGN_LEFT);
		rt.setDefautlFooter(11, 2, "审核:", Table.ALIGN_LEFT);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	/**
	 * 燃料指标情况表
	 */
	@Override
	public JSONArray getRanlzbqkb(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = guodbbDao.getRanlzbqkb(map);
		
		String[][] arrData = new String[list.size()][23];
		for (int i = 0; i < list.size(); i++) {
			int j = 0;
			Iterator<String> it = list.get(i).keySet().iterator();
			while (it.hasNext()) {
				arrData[i][j++] =list.get(i).get(it.next()).toString();
			}
		}

		Report rt = new Report();
		String[][] ArrHeader = new String[2][23];
		ArrHeader[0] = new String[] { "单位名称", "分项", "来煤量", "耗煤量", "月末库存",
				"耗油量", "进厂煤热值", "入炉煤热值", "热值差", "结算量", "结算热值", "煤价</br>(含税)",
				"运费</br>(含税)", "杂费", "入厂原煤综合单价</br>(含税)", "入厂标煤单价</br>(含税)",
				"入厂标煤单价</br>(不含税)", "入厂标煤量(含油、汽)", "入厂综合标煤单价",
				"入厂入炉标煤单价差</br>(不含税)", "库存天然煤价</br>(不含税)", "库存天然煤热值",
				"库存标煤单价</br>(不含税)"};
		ArrHeader[1] = new String[]{"甲","乙","1","2","3","4","5","6","7","8","9","10",
				"11","12","13","14","15","16","17","18","19","20","21"};
		
		Table tb = new Table(arrData, 2, 0, 0, 23);
		rt.setBody(tb);
		int[] ArrWidth =( new int[] { 100,80, 80, 80,80 ,60, 70, 70, 60, 70, 60,
				60, 60, 60, 70, 70, 70, 70, 70, 70, 70, 70, 70});
		rt.setTitle("", ArrWidth);
		
		rt.title.setCellValue(1, 1, getTBRQ(map.get("riq").toString())+"份月度燃料管理指标情况表");
		rt.title.setCellFont(1, 1, "", 12, true);
		rt.title.setCellAlign(1, 1, Table.ALIGN_CENTER);
		rt.title.mergeRowCells(1);
		
		rt.title.setCellValue(2, 1,"单位:" + ((Diancxx)map.get("danwmc")).getQuanc());
		rt.title.setCellFont(2, 1, "", 10, false);
		rt.title.setCellAlign(2, 1, Table.ALIGN_LEFT);
		rt.title.mergeRowCells(2);
		
		rt.title.setCellValue(3, 1, "单位:吨、MJ/Kg、元/吨");
		rt.title.setCellFont(3, 1, "", 10, false);
		rt.title.setCellAlign(3, 1, Table.ALIGN_RIGHT);
		rt.title.mergeRowCells(3);
		
		
		rt.body.setWidth(ArrWidth);
		rt.body.ShowZero = true;
		rt.body.setWidth(ArrWidth);
		rt.body.setHeaderData(ArrHeader);
		
		rt.body.mergeCol(1);
//		设置对齐方式
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		for(int i = 4;i<=23;i++){
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}
		int pageCount = rt.getPages();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml());
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	@Override
	public JSONArray getChangnfycxMX(Map<String, Object> map) {
		String eriq=map.get("riq").toString();
		String sriq=eriq.substring(0,4)+"-01";
		map.put("eriq",eriq);
		map.put("sriq",sriq);
		String lastYearERiq=DateUtil.getLastYearString(eriq);
		map.put("leriq", lastYearERiq);
		String lastYearSRiq=DateUtil.getLastYearString(sriq);
		map.put("lsriq",lastYearSRiq);
		List<LinkedHashMap<String, Object>> list = guodbbDao
				.getChangnfycxMX(map);

		String[][] arrData = new String[list.size()][8];
		for (int i = 0; i < list.size(); i++) {
			int j = 0;
			Iterator<String> it = list.get(i).keySet().iterator();
			while (it.hasNext()) {
				arrData[i][j++] = list.get(i).get(it.next()).toString();
			}
		}
		Report rt = new Report();
		String[][] ArrHeader = new String[2][8];
		ArrHeader[0] = new String[] { "单位名称", "杂费名称", "当月金额", "当月金额", "当月金额",
				"累计金额", "累计金额", "累计金额" };
		ArrHeader[1] = new String[] { "单位名称", "杂费名称", "本期", "同期", "同比", "本期",
				"同期", "同比" };
		int[] ArrWidth = new int[] { 150, 100, 80, 80, 80, 80, 80, 80 };
		String[] arrFormat = new String[] { "", "", "0.000", "0.000", "0.000",
				"0.000", "0.000", "0.000" };
		rt.setBody(new Table(arrData, 2, 0, 0, 8));
		rt.setTitle(getTBRQ(map.get("eriq").toString())+"杂费查询表", ArrWidth);
		rt.setDefaultTitle(1, 3, "填报单位(盖章):"+((Diancxx)map.get("danwmc")).getQuanc(), Table.ALIGN_LEFT);
		rt.setDefaultTitle(6, 3, "填报日期:"+getTBRQ(map.get("eriq").toString()), Table.ALIGN_RIGHT);
		rt.body.setWidth(ArrWidth);
		rt.body.setHeaderData(ArrHeader);// 表头数据
		rt.body.merge(2, 1, rt.body.getRows(), 1); // 对第一列进行合并
		rt.body.merge(1, 1, 2, rt.body.getCols()); // 对第一、二行进行合并
		
		rt.body.setColFormat(arrFormat);

		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		for(int i=3;i<=8;i++){
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}

		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 3, "打印日期:"+DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE), Table.ALIGN_LEFT);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}
	
	private String getTBRQ(String strDate){
		String[] strs = strDate.split("-");
		String rtnStr = "";
		if(strs.length>0)
			rtnStr += strs[0]+"年";
		if(strs.length>1)
			rtnStr += strs[1]+"月";
		
		return rtnStr;
	}
	
	private void convertItem(Table tb) {
		String tbCell = "";
		String compareCell = "default"; 
		int t = -1;
		int k = 0;
		int j = 0;
		
		for (int i = 1; i< tb.getRows()-1; i++) {
			tbCell = tb.getCellValue(i, 1);
			t = tbCell.indexOf("*");
			if (t > -1) {
				//防止连续合并的相同数据累加序号k
				if (!compareCell.equals(tbCell)) k++;
				compareCell = tbCell;
				tb.setCellValue(i, 1, getDxValue(k) + "、" + tbCell.substring(t + 1));
				if (k > 1) j = 0;  //当碰见下一个计划口径时，j从零开始
			}
			t = tbCell.indexOf("#");
			if (t > -1) {
				if (!compareCell.equals(tbCell)) j++;
				compareCell = tbCell;
				tb.setCellValue(i, 1, j + "、" + tbCell.substring(t + 1));
			}
		}
	}
	
	private String getDxValue(int xuh) {
		String reXuh = "";
		String[] dx = { "", "一", "二", "三", "四", 
				"五", "六", "七", "八", "九", "十" };
		String strXuh = String.valueOf(xuh);
		for (int i = 0; i < strXuh.length(); i++)
			reXuh = reXuh + dx[Integer.parseInt(strXuh.substring(i, i + 1))];

		return reXuh;
	}
	@Override
	public JSONArray getShulysbb(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
