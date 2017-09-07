package com.zhiren.fuelmis.dc.service.impl.jiesgl;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.jiesgl.IJiesdxgService;
import com.zhiren.fuelmis.dc.utils.CnUpperCaser;
import com.zhiren.fuelmis.dc.utils.PropertiesUtil;
import com.zhiren.fuelmis.dc.utils.TreeUtil;

/**
 * @author 陈宝露
 */
@Service
public class JiesdxgServiceImpl implements IJiesdxgService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public JSONArray getJiesdbh(Map<String, Object> map) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select id,JIESBH from RL_JS_YUEJSDB where DIANCXXB_ID = " + map.get("dianc")
						+ " and zhuangt = 0 or zhuangt is null  order by jiesbh");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Combobox combobox = new Combobox(list.get(i).get("JIESBH"), list.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		return jsonArray;
	}

	@Override
	public JSONObject getJiesd(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> maps = new HashMap<String, Object>();
		// DecimalFormat df_2 = new DecimalFormat("0.00");

		try {
			// 查询状态
			// Map<String, Object> m = jdbcTemplate.queryForMap("select ZHUANGT
			// from RL_JS_YUEJSDB where id='" + map.get("jiesdid") + "'");
			maps = jdbcTemplate.queryForMap("select D.QUANC DIANCMC,\n" + "       JS.JIESBH,\n"
					+ "       G.QUANC GONGYSMC,\n" + "       C.MINGC CHEZMC,\n" + "       JS.ZHONGCSJ,\n"
					+ "       JS.QINGCSJ,\n" + "       D.KAIHYH,\n" + "       D.ZHANGH,\n" + "       JS.DAIBCH,\n"
					+ "       P.MINGC PINZMC,\n" + "       JS.PIAOZ,\n" + "       JS.CHES,\n"
					+ "       JS.CHANGRLJBR,\n" + "       JS.CHANGRLJSRQ,\n" + "       JS.JINGZ,\n"
					+ "       (JS.JIESSL - JS.JINGZ) JIESSLCY,\n" + "       JS.BEIZ,\n" + "       JS.JIESJG,\n"
					+ "       round(JS.JIESSL, 0) jiessl,\n" + "       round(JS.JIESJG / 1.17, 2) BUHSDJ,\n"
					+ "       JS.MEIKJE MEIKJE,\n" + "       JS.shuik SHUIK,\n"
					+ "       round(JS.JIESJG * round(JS.JIESSL, 0), 2) MEIKHJ,\n"
					+ "       round(JS.JIESRZ * 1000 / 4.1816, 0) JIESRZ,\n" + "       JS.JIESLF,\n"
					+ "       round((JS.REZZK * JS.JINGZ), 2) REZZJJE,\n" + "       (JS.LIUFZK * JS.JINGZ) LIUFZJJE,\n"
					+ "       round(JS.REZZK, 2) REZZK,\n" + "       JS.LIUFZK\n"
					+ "  from RL_JS_YUEJSDB JS, DIANCXXB D, GONGYSB G, CHEZXXB C, PINZB P\n"
					+ " where JS.DIANCXXB_ID = D.ID\n" + "   and JS.GONGYSB_ID = G.ID\n"
					+ "   and JS.FAZ_ID = C.ID(+)\n" + "   and JS.PINZB_ID = P.ID(+)\n" + "   and JS.id =  '"
					+ map.get("jiesdid") + "'");
			// }

			CnUpperCaser caser = new CnUpperCaser(maps.get("MEIKHJ").toString());
			maps.put("MEIKDJDX", caser.getCnString());
			Map<String, Object> fahsj = jdbcTemplate
					.queryForMap("select substr(min(zhongcsj),0,10)||'至'||substr(max(zhongcsj),0,10) zhongcsj, "
							+ "substr(min(qingcsj),0,10)||'至'||substr(max(qingcsj),0,10) qingcsj"
							+ " from rl_ys_chepb_qc where chepb_id in   (select chepb_id from GX_JIESDB_CHEPB where jiesdb_id in "
							+ "(select rijsdb_id from GX_RIJSD_YUEJSD where yuejsdb_id = "
							+ "(select id from rl_js_yuejsdb where id = '" + map.get("jiesdid") + "')" + "))");
			maps.put("ZHONGCSJ", fahsj.get("ZHONGCSJ"));
			maps.put("QINGCSJ", fahsj.get("QINGCSJ"));
			Map<String, Object> zhil = jdbcTemplate
					.queryForMap("select round((sum(qnet_ar*t2.jingz)/sum(t2.jingz))*1000/4.1816,0) qnet_ar,"
							+ "round( sum((STAD*(100-MT)/(100-MAD))*t2.jingz)/sum(t2.jingz),2) star from rl_hy_huaydb h,"
							+ "(select g2.mubbm,t.jingz from ( select samcode,sum(maoz-piz-zongkd) jingz from rl_ys_chepb where id in("
							+ "( select chepb_id from GX_JIESDB_CHEPB where jiesdb_id in ("
							+ "select rijsdb_id from GX_RIJSD_YUEJSD where yuejsdb_id = (select id from rl_js_yuejsdb where id = '"
							+ map.get("jiesdid") + "'))"
							+ " )) group by samcode )t,GX_CHEP_CAIZHBMB g1,GX_CHEP_CAIZHBMB g2  where t.samcode = g1.yuanbm and g1.mubbm = g2.yuanbm) t2 "
							+ "where h.huaybm = t2.mubbm");
			int qnetar_cf = Integer.parseInt(zhil.get("QNET_AR").toString());
			double star_cf = Double.parseDouble(zhil.get("STAR").toString());
			maps.put("REZ_CF", qnetar_cf);
			maps.put("LIUF_CF", star_cf);
		} catch (Exception e) {

		}
		// }

		JSONObject jsonObject = JSONObject.fromObject(maps);
		return jsonObject;
	}

	@Override
	public JSONArray save(Map<String, Object> map) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		try {
			jdbcTemplate.update("update rl_js_yuejsdb set jiesbh = '" + map.get("JIESBH") + "',MEIKJE="
					+ map.get("MEIKJE") + ",SHUIK=" + map.get("SHUIK") + ",JIESJE=" + map.get("MEIKHJ") +
					",beiz='"+map.get("BEIZ")+"'"+
					/*
					 * ",JIESSL="+map.get("JIESSL")+
					 * ",PIAOZ="+map.get("JIESSL")+ ",JINGZ="+map.get("JIESSL")+
					 */
					// ",ZHUANGT=2 " +
			" where id = '" + map.get("ID") + "'");
			jsonArray.add(1);
		} catch (Exception e) {
			jsonArray.add(0);
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	@Transactional
	public JSONArray delete(Map<String, Object> map) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		try {
			jdbcTemplate
					.update("delete from GX_RIJSD_YUEJSD where yuejsdb_id = (select id from rl_js_yuejsdb where id='"
							+ map.get("jiesdid") + "')");
			jdbcTemplate.update("delete from rl_js_yuejsdb where id = '" + map.get("jiesdid") + "'");
			jsonArray.add(1);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			jsonArray.add(0);
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public JSONArray submit(Map<String, Object> map) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		try {
			Diancxx diancxx = (Diancxx) map.get("diancxx");
			Renyxx renyxx = (Renyxx) map.get("renyxx");

			Report rt = new Report();
			String ArrHeader[][] = new String[6][5];
			ArrHeader[0] = new String[] { "", "", "", "", "" };
			ArrHeader[1] = new String[] { "国 电 电 力 发 展 股 份 有 限 公 司", "国 电 电 力 发 展 股 份 有 限 公 司",
					"国 电 电 力 发 展 股 份 有 限 公 司", "国 电 电 力 发 展 股 份 有 限 公 司", "国 电 电 力 发 展 股 份 有 限 公 司" };
			ArrHeader[2] = new String[] { "SP POWER DEVELOPMENT CO.,LTD.", "SP POWER DEVELOPMENT CO.,LTD.",
					"SP POWER DEVELOPMENT CO.,LTD.", "SP POWER DEVELOPMENT CO.,LTD.", "SP POWER DEVELOPMENT CO.,LTD." };
			ArrHeader[3] = new String[] { "燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单",
					"燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单", };
			ArrHeader[4] = new String[] { "", "", "", "", "" };

			// 结算单信息
			// Map<String, Object> m = jdbcTemplate.queryForMap("select ZHUANGT
			// from RL_JS_YUEJSDB where id='" + map.get("jiesdid") + "'");
			Map<String, Object> maps = null;
			maps = jdbcTemplate.queryForMap("select js.jieslx, D.QUANC DIANCMC,\n"
					+ "		(JS.HETJG-JS.JIESJG) JIAGC,\n" + "		JS.HETJG,\n" + "       JS.JIESBH,\n"
					+ "       G.QUANC GONGYSMC,\n" + "       C.MINGC CHEZMC,\n" + "       JS.ZHONGCSJ,\n"
					+ "       JS.QINGCSJ,\n" + "       D.KAIHYH,\n" + "       D.ZHANGH,\n" + "       JS.DAIBCH,\n"
					+ "       P.MINGC PINZMC,\n" + "       JS.PIAOZ,\n" + "       JS.CHES,\n"
					+ "       JS.CHANGRLJBR,\n" + "       JS.CHANGRLJSRQ,\n" + "       JS.JINGZ,\n"
					+ "		JS.SHUIK SHUIJ,\n" + "       (JS.JIESSL - JS.JINGZ) JIESSLCY,\n" + "       JS.BEIZ,\n"
					+ "       JS.JIESJG,\n" + "       round(JS.JIESSL, 0) jiessl,\n"
					+ "       round(JS.JIESJG / 1.17, 2) BUHSDJ,\n" + "       JS.MEIKJE MEIKJE,\n"
					+ "       JS.shuik SHUIK,\n" + "       JS.MEIKJE ,\n"
					+ "       round(JS.JIESRZ * 1000 / 4.1816, 0) JIESRZ,\n" + "       JS.JIESLF,\n"
					+ "       round((JS.REZZK * JS.JINGZ), 2) REZZJJE,\n" + "       (JS.LIUFZK * JS.JINGZ) LIUFZJJE,\n"
					+ "       round(JS.REZZK, 2) REZZK,\n" + "       JS.LIUFZK,\n" + "       JS.JIESJE,\n" + "JS.HETBH"+",nvl(js.beiz,' ') beiz ,"
					+ "nvl(js.yansksrq,substr(js.shijd,0,10))||'至'||nvl(js.yansjzrq,substr(js.shijd,12,10)) yansrq,nvl(js.hetbh,' ') hetbh \n "
					+ "  from RL_JS_YUEJSDB JS, DIANCXXB D, GONGYSB G, CHEZXXB C, PINZB P\n"
					+ " where JS.DIANCXXB_ID = D.ID\n" + "   and JS.GONGYSB_ID = G.ID\n"
					+ "   and JS.FAZ_ID = C.ID(+)\n" + "   and JS.PINZB_ID = P.ID(+)\n" + "   and JS.id =  '"
					+ map.get("jiesdid") + "'");
			String rucrq = "";
			Map<String, Object> map3 = null;
			if (maps.get("JIESLX") != null && !maps.get("JIESLX").equals("杂费结算")) {
				rucrq = maps.get("YANSRQ").toString();
				map3 = jdbcTemplate.queryForMap("SELECT round(sum(h.QNET_AR*t.jingz)/sum(t.jingz),3) QNET_AR,"
						+ "round(sum(h.AAR*t.jingz)/sum(t.jingz),2) AAR,"
						+ "round(sum((h.STAD*(100-h.MT)/(100-h.MAD))*t.jingz)/sum(t.jingz),2) STAR,"
						+ "round(sum((h.VAD*(100-h.MT)/(100-h.MAD))*t.jingz)/sum(t.jingz),2) VAR,"
						+ "round(sum(h.MT*t.jingz)/sum(t.jingz),1) MT FROM RL_HY_HUAYDB h,"
						+ "(SELECT SAMCODE,sum(maoz-piz-zongkd) jingz FROM RL_YS_CHEPB WHERE ID IN("
						+ "SELECT CHEPB_ID FROM GX_JIESDB_CHEPB WHERE JIESDB_ID in ("
						+ "select rijsdb_id from GX_RIJSD_YUEJSD where yuejsdb_id =("
						+ "SELECT ID FROM RL_JS_YUEJSDB WHERE id = '" + map.get("jiesdid")
						+ "'))) group by SAMCODE) t, " + "GX_CHEP_CAIZHBMB g1,GX_CHEP_CAIZHBMB g2 "
						+ "where t.samcode = g1.yuanbm and g1.mubbm = g2.yuanbm and h.huaybm = g2.mubbm");

			}
			ArrHeader[5] = new String[] { "单位：" + maps.get("DIANCMC"), "日期：" + rucrq, "合同编号：" + maps.get("HETBH"),
					"单位：吨,元/吨,MJ/kg,%,元", "编号：" + maps.get("JIESBH") };
			String ArrBody[][] = new String[19][19];
			ArrBody[0] = new String[] { "结算部门：" + PropertiesUtil.getValue("jiesbm"), "", "",
					"供货单位：" + maps.get("GONGYSMC"), "", "", "", "", "", "供货地区：", "", "", "",
					"计划渠道：" + maps.get("JIHKJMC"), "", "", "", "品种：" + maps.get("PINZMC"), "" };
			ArrBody[1] = new String[] { "数量", "", "", "", "", "单价", "", "", "", "", "", "", "", "", "", "", "煤款", "",
					"税金" };
			ArrBody[2] = new String[] { "车数", "票重", "盈吨", "亏吨", "实收", "煤价", "扣款", "", "", "", "", "", "", "单价合计",
					"不含税价", "", "", "", "" };
			ArrBody[3] = new String[] { "", "", "", "", "", "", "热值", "灰分", "挥发分", "水分", "硫量", "其他", "小计", "", "", "",
					"", "", "" };

			DecimalFormat df_2 = new DecimalFormat("0.00");
			double hetjg = Double.parseDouble(maps.get("HETJG").toString());
			double rezzk = maps.get("REZZK") == null ? 0 : Double.parseDouble(maps.get("REZZK").toString());
			double liufzk = maps.get("LIUFZK") == null ? 0 : Double.parseDouble(maps.get("LIUFZK").toString());
			double koukxj = Double.parseDouble(maps.get("JIAGC").toString());
			double danjhj = Double.parseDouble(maps.get("JIESJG").toString());
			double buhsmj = danjhj / 1.17;
			long shul=0;
			if(maps.get("JINGZ")!=null){
				shul = Math.round(Double.parseDouble(maps.get("JINGZ").toString()));
			}

			double meik = Double.parseDouble(maps.get("MEIKJE").toString());
			double shuij = Double.parseDouble(maps.get("SHUIJ").toString());
			double shifje = Double.parseDouble(maps.get("JIESJE").toString());// meik
																				// +
																				// shuij;

			ArrBody[4] = new String[] { maps.get("CHES").toString(), String.valueOf(shul),
					maps.get("YINGD") == null ? "" : maps.get("YINGD").toString(),
					maps.get("KUID") == null ? "" : maps.get("KUID").toString(), String.valueOf(shul),
					String.valueOf(hetjg), String.valueOf(rezzk), "", "", "", String.valueOf(liufzk), "",
					String.valueOf(koukxj), String.valueOf(danjhj), df_2.format(buhsmj), "", df_2.format(meik), "",
					df_2.format(shuij) };
			ArrBody[5] = new String[] { "热值", "热值", "灰分", "挥发分", "水分", "硫量", "应付价款", "", "应付税金", "", "其他扣款", "", "实付金额",
					"", "", "", "", "", "" };
			long rez_j = 0;
			if (map3 != null) {
				rez_j = Math.round(Double.parseDouble(map3.get("QNET_AR").toString()) / 4.1816 * 1000);

				ArrBody[6] = new String[] { String.valueOf(rez_j), map3.get("QNET_AR").toString(),
						map3.get("AAR").toString(), map3.get("VAR").toString(), map3.get("MT").toString(),
						map3.get("STAR").toString(), df_2.format(meik), "", df_2.format(shuij), "", "", "",
						df_2.format(shifje), "", "", "", "", "", "" };
			} else {

				ArrBody[6] = new String[] { String.valueOf(rez_j), "", "", "", "", "", df_2.format(meik), "",
						df_2.format(shuij), "", "", "", df_2.format(shifje), "", "", "", "", "", "" };
			}
			ArrBody[7] = new String[] { "运距", "", "运费单价明细", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
					"印花税" };
			ArrBody[8] = new String[] { "国铁", "地铁", "国铁", "地铁", "矿运", "专线", "短运", "汽运", "其他运费", "", "", "", "", "",
					"运杂费(元/车)", "", "", "", "" };
			ArrBody[9] = new String[] { "", "", "", "", "", "", "", "", "电附加", "风沙", "储装", "道口", "其它", "小计", "取送车",
					"变更费", "单价合计", "不含税价", "" };
			ArrBody[10] = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
			ArrBody[11] = new String[] { "国铁运费", "", "地铁运费", "", "矿区运费", "", "专线运费", "", "短途运费", "", "汽车运费", "", "其他运费",
					"", "运杂费", "", "扣款", "", "实付运费金额" };
			ArrBody[12] = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "亏吨费", "其他",
					"" };
			ArrBody[13] = new String[] { "" + "" + "", "", "", "", "" + "" + "", "", "", "", "", "", "" + "" + "", "",
					"", "", "", "", "" + "" + "", "", "" + "" + "" };
			ArrBody[14] = new String[] { "注：根据本企业资金管理制度权限履行会审及审批程序，会审部门及审批人写明意见、签名及日期。", "", "", "", "", "", "", "", "",
					"", "", "", "", "", "", "", "", "", "" };

			String fengld = "";
			String jihyxb = "";
			String zhijsh = "";
			String shulsh = "";
			// String zhib = "";
			String zongkjs = "";
			String huaijjg = "";
			String caiw = "";
			String fuk = "";
			String jiesr = "";
			String zongjl = "";
			// String liuc_id = jdbcTemplate.queryForObject("select liuc_id from
			// rl_js_yuejsdb where id = '" + map.get("jiesdid") + "'",
			// String.class);
			ArrBody[15] = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
			ArrBody[16] = new String[] { "公司总经理：", "", zongjl, "", "市场营销部负责人：", "", jihyxb, "", "质价审核：", "", zhijsh, "",
					"数量审核：", "", shulsh, "", "结算人：", jiesr, "" };
			ArrBody[17] = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
			ArrBody[18] = new String[] { "公司（厂）分管领导：", "", fengld, "", "总会计师：", "", zongkjs, "", "会计机构负责人：", "",
					huaijjg, "", "财务审核：", "", caiw, "", "付款：", "", fuk, "", };

			int ArrWidth[] = new int[] { 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54 };
			int ArrWidthTitle[] = new int[] { 220, 170, 320, 160, 160 };
			// 定义页Title
			rt.setTitle(new Table(ArrHeader, 0, 0, 0));
			rt.setBody(new Table(ArrBody, 0, 0, 0));
			rt.body.setWidth(ArrWidth);
			rt.title.setWidth(ArrWidthTitle);
			// 合并单元格
			// 表头_Begin
			rt.title.merge(2, 1, 2, 5);
			rt.title.merge(3, 1, 3, 5);
			rt.title.merge(4, 1, 4, 5);
			rt.title.merge(5, 1, 5, 5);

			rt.title.setBorder(0, 0, 0, 0);
			rt.title.setRowCells(1, Table.PER_BORDER_BOTTOM, 0);
			rt.title.setRowCells(2, Table.PER_BORDER_BOTTOM, 0);
			rt.title.setRowCells(3, Table.PER_BORDER_BOTTOM, 0);
			rt.title.setRowCells(4, Table.PER_BORDER_BOTTOM, 0);
			rt.title.setRowCells(5, Table.PER_BORDER_BOTTOM, 0);
			rt.title.setRowCells(6, Table.PER_BORDER_BOTTOM, 0);

			rt.title.setRowCells(1, Table.PER_BORDER_RIGHT, 0);
			rt.title.setRowCells(2, Table.PER_BORDER_RIGHT, 0);
			rt.title.setRowCells(3, Table.PER_BORDER_RIGHT, 0);
			rt.title.setRowCells(4, Table.PER_BORDER_RIGHT, 0);
			rt.title.setRowCells(5, Table.PER_BORDER_RIGHT, 0);
			rt.title.setRowCells(6, Table.PER_BORDER_RIGHT, 0);

			rt.title.setRowCells(1, Table.PER_ALIGN, Table.ALIGN_CENTER);
			rt.title.setRowCells(2, Table.PER_ALIGN, Table.ALIGN_CENTER);
			rt.title.setRowCells(3, Table.PER_ALIGN, Table.ALIGN_CENTER);
			rt.title.setRowCells(4, Table.PER_ALIGN, Table.ALIGN_CENTER);
			rt.title.setRowCells(5, Table.PER_ALIGN, Table.ALIGN_CENTER);

			rt.title.setCellAlign(6, 1, Table.ALIGN_LEFT);
			rt.title.setCellAlign(6, 2, Table.ALIGN_LEFT);
			rt.title.setCellAlign(6, 3, Table.ALIGN_CENTER);
			rt.title.setCellAlign(6, 4, Table.ALIGN_LEFT);
			rt.title.setCellAlign(6, 5, Table.ALIGN_RIGHT);

			// 字体
			rt.title.setCells(2, 1, 2, 5, Table.PER_FONTNAME, "黑体");
			rt.title.setCells(2, 1, 2, 5, Table.PER_FONTSIZE, 11);
			rt.title.setCells(3, 1, 3, 5, Table.PER_FONTNAME, "Arial Unicode MS");
			rt.title.setCells(3, 1, 3, 5, Table.PER_FONTSIZE, 12);
			rt.title.setCells(4, 1, 4, 5, Table.PER_FONTNAME, "隶书");
			rt.title.setCells(4, 1, 4, 5, Table.PER_FONTSIZE, 20);
			// 字体

			// 图片
			rt.title.setCellImage(1, 1, 110, 50, "images/report/GDBZ.gif"); // 国电的标志（到现场要一个换上就行了）
			rt.title.setRowCells(1, Table.PER_ALIGN, Table.ALIGN_LEFT);
			rt.title.setCellImage(5, 1, rt.title.getWidth() / 3 + 30, 10, "images/report/GDHX.gif");
			// 图片_End

			// 表头_End
			// 表体_Begin
			rt.body.mergeCell(1, 1, 1, 3);
			rt.body.mergeCell(1, 4, 1, 9);
			rt.body.mergeCell(1, 10, 1, 13);
			rt.body.mergeCell(1, 14, 1, 17);
			rt.body.mergeCell(1, 18, 1, 19);
			rt.body.setRowCells(1, Table.PER_ALIGN, Table.ALIGN_LEFT);

			rt.body.mergeCell(2, 1, 2, 5);
			rt.body.mergeCell(2, 6, 2, 16);
			rt.body.mergeCell(2, 17, 4, 18);
			rt.body.mergeCell(2, 19, 4, 19);
			rt.body.setRowCells(2, Table.PER_ALIGN, Table.ALIGN_CENTER);

			rt.body.mergeCell(3, 1, 4, 1);
			rt.body.mergeCell(3, 2, 4, 2);
			rt.body.mergeCell(3, 3, 4, 3);
			rt.body.mergeCell(3, 4, 4, 4);
			rt.body.mergeCell(3, 5, 4, 5);
			rt.body.mergeCell(3, 6, 4, 6);
			rt.body.mergeCell(3, 7, 3, 13);
			rt.body.mergeCell(3, 14, 4, 14);
			rt.body.mergeCell(3, 15, 4, 16);
			rt.body.setRowCells(3, Table.PER_ALIGN, Table.ALIGN_CENTER);

			rt.body.setRowCells(4, Table.PER_ALIGN, Table.ALIGN_CENTER);

			rt.body.mergeCell(5, 15, 5, 16);
			rt.body.mergeCell(5, 17, 5, 18);
			rt.body.setRowCells(5, Table.PER_ALIGN, Table.ALIGN_CENTER);

			rt.body.mergeCell(6, 1, 6, 2);
			rt.body.mergeCell(6, 7, 6, 8);
			rt.body.mergeCell(6, 9, 6, 10);
			rt.body.mergeCell(6, 11, 6, 12);
			rt.body.mergeCell(6, 13, 6, 14);
			rt.body.mergeCell(6, 15, 6, 16);
			rt.body.mergeCell(6, 17, 6, 18);
			rt.body.setRowCells(6, Table.PER_ALIGN, Table.ALIGN_CENTER);

			rt.body.mergeCell(7, 7, 7, 8);
			rt.body.mergeCell(7, 9, 7, 10);
			rt.body.mergeCell(7, 11, 7, 12);
			rt.body.mergeCell(7, 13, 7, 14);
			rt.body.mergeCell(7, 15, 7, 16);
			rt.body.mergeCell(7, 17, 7, 18);
			rt.body.setRowCells(7, Table.PER_ALIGN, Table.ALIGN_CENTER);

			rt.body.mergeCell(8, 1, 8, 2);
			rt.body.mergeCell(8, 3, 8, 18);
			rt.body.mergeCell(8, 19, 10, 19);
			rt.body.setRowCells(8, Table.PER_ALIGN, Table.ALIGN_CENTER);

			rt.body.mergeCell(9, 1, 10, 1);
			rt.body.mergeCell(9, 2, 10, 2);
			rt.body.mergeCell(9, 3, 10, 3);
			rt.body.mergeCell(9, 4, 10, 4);
			rt.body.mergeCell(9, 5, 10, 5);
			rt.body.mergeCell(9, 6, 10, 6);
			rt.body.mergeCell(9, 7, 10, 7);
			rt.body.mergeCell(9, 8, 10, 8);
			rt.body.mergeCell(9, 9, 9, 14);
			rt.body.mergeCell(9, 15, 9, 18);
			rt.body.setRowCells(9, Table.PER_ALIGN, Table.ALIGN_CENTER);

			rt.body.setRowCells(10, Table.PER_ALIGN, Table.ALIGN_CENTER);

			rt.body.setRowCells(11, Table.PER_ALIGN, Table.ALIGN_CENTER);

			rt.body.mergeCell(12, 1, 13, 2);
			rt.body.mergeCell(12, 3, 13, 4);
			rt.body.mergeCell(12, 5, 13, 6);
			rt.body.mergeCell(12, 7, 13, 8);
			rt.body.mergeCell(12, 9, 13, 10);
			rt.body.mergeCell(12, 11, 13, 12);
			rt.body.mergeCell(12, 13, 13, 14);
			rt.body.mergeCell(12, 15, 13, 16);
			rt.body.mergeCell(12, 17, 12, 18);
			rt.body.mergeCell(12, 19, 13, 19);
			rt.body.setRowCells(12, Table.PER_ALIGN, Table.ALIGN_CENTER);

			rt.body.setRowCells(13, Table.PER_ALIGN, Table.ALIGN_CENTER);

			rt.body.mergeCell(14, 1, 14, 2);
			rt.body.mergeCell(14, 3, 14, 4);
			rt.body.mergeCell(14, 5, 14, 6);
			rt.body.mergeCell(14, 7, 14, 8);
			rt.body.mergeCell(14, 9, 14, 10);
			rt.body.mergeCell(14, 11, 14, 12);
			rt.body.mergeCell(14, 13, 14, 14);
			rt.body.mergeCell(14, 15, 14, 16);
			rt.body.setRowCells(14, Table.PER_ALIGN, Table.ALIGN_CENTER);

			rt.body.mergeCell(15, 1, 15, 19);
			rt.body.setRowCells(15, Table.PER_BORDER_BOTTOM, 2);

			// “注：”(的下一行)
			rt.body.mergeCell(16, 1, 16, 19);
			rt.body.setRowHeight(16, 8);
			rt.body.setRowCells(16, Table.PER_BORDER_BOTTOM, 0);
			rt.body.setRowCells(16, Table.PER_BORDER_RIGHT, 0);

			rt.body.setBorder(0, 0, 2, 0);
			rt.body.setCells(1, 1, 15, 1, Table.PER_BORDER_LEFT, 2);
			rt.body.setCells(1, 19, 15, 19, Table.PER_BORDER_RIGHT, 2);
			rt.body.setCells(1, 18, 1, 18, Table.PER_BORDER_RIGHT, 2);
			rt.body.setCells(15, 1, 15, 1, Table.PER_BORDER_RIGHT, 2);

			rt.body.mergeCell(17, 1, 17, 2);
			rt.body.mergeCell(17, 3, 17, 4);
			rt.body.mergeCell(17, 5, 17, 6);
			rt.body.mergeCell(17, 7, 17, 8);
			rt.body.mergeCell(17, 9, 17, 10);
			rt.body.mergeCell(17, 11, 17, 12);
			rt.body.mergeCell(17, 13, 17, 14);
			rt.body.mergeCell(17, 15, 17, 16);
			rt.body.mergeCell(17, 17, 17, 17);
			rt.body.mergeCell(17, 18, 17, 19);
			rt.body.setRowHeight(17, 5);
			rt.body.setRowCells(17, Table.PER_BORDER_BOTTOM, 0);
			rt.body.setRowCells(17, Table.PER_BORDER_RIGHT, 0);

			rt.body.mergeCell(18, 1, 18, 19);
			rt.body.setRowCells(18, Table.PER_BORDER_BOTTOM, 0);
			rt.body.setRowCells(18, Table.PER_BORDER_RIGHT, 0);

			rt.body.mergeCell(19, 1, 19, 2);
			rt.body.mergeCell(19, 3, 19, 4);
			rt.body.mergeCell(19, 5, 19, 6);
			rt.body.mergeCell(19, 7, 19, 8);
			rt.body.mergeCell(19, 9, 19, 10);
			rt.body.mergeCell(19, 11, 19, 12);
			rt.body.mergeCell(19, 13, 19, 14);
			rt.body.mergeCell(19, 15, 19, 16);
			rt.body.mergeCell(19, 17, 19, 17);
			rt.body.mergeCell(19, 18, 19, 19);
			rt.body.setRowHeight(19, 5);
			rt.body.setRowCells(19, Table.PER_BORDER_BOTTOM, 0);
			rt.body.setRowCells(19, Table.PER_BORDER_RIGHT, 0);

			String html = rt.getAllPagesHtml();
//----------------------------------------开始提及流程---------------------------------------------------------------------------------
			String sanjurl = PropertiesUtil.getValue("sanjsp_url");
			sanjurl += "/service/StartupService?wsdl";
			// sanjurl = "http://172.24.19.4:8299/gdsjsp_spr_oracle/service/StartupService?wsdl;"
			Client client = new Client(new URL(sanjurl));
			String renwms = "单位：" + diancxx.getMingc() + "、结算编号：" + maps.get("JIESBH").toString() + "、供应商："
					+ maps.get("GONGYSMC").toString();
			JSONObject obj = new JSONObject();
			JSONArray a = new JSONArray();
			TreeUtil.checkMapNull(maps,
					new String[] { "KUID", "YINGD", "JIHKJMC", "DIANCMC", "IAGC", "HETJG", "IESBH", "GONGYSMC",
							"CHEZMC", "ZHONGCSJ", "QINGCSJ", "KAIHYH", "ZHANGH", "DAIBCH", "MINGC PINZMC", "PIAOZ",
							"CHES", "CHANGRLJBR", "CHANGRLJSRQ", "JINGZ", "SHUIJ", "JIESSLCY", "BEIZ", "JIESJG",
							"jiessl", "BUHSDJ", "MEIKJE", "SHUIK", "MEIKJE ", "JIESRZ", "JIESLF", "REZZJJE", "LIUFZJJE",
							"REZZK", "LIUFZK" });
			if(map3!=null){
				TreeUtil.checkMapNull(map3, new String[] { "QNET_AR", "AAR", "VAR", "MT", "STAR" });
			}
			
			obj.put("danw", maps.get("DIANCMC").toString());
			obj.put("riq", rucrq);
			obj.put("jjdanw", "吨,元/吨,MJ/kg,%,元");
			obj.put("bianh", maps.get("JIESBH").toString());
			obj.put("jiesbm", PropertiesUtil.getValue("jiesbm"));
			obj.put("gonghdw", maps.get("GONGYSMC").toString());
			obj.put("jihqd", maps.get("JIHKJMC").toString());
			obj.put("ches", maps.get("CHES").toString());
			obj.put("shul", shul + "");
			obj.put("yingd", maps.get("YINGD").toString());
			obj.put("kuidun", maps.get("KUID").toString());
			obj.put("shis", shul + "");
			obj.put("meij", hetjg + "");
			if (maps.get("REZZK") != null) {
				obj.put("rez", maps.get("REZZK").toString());
			}
			obj.put("huif", "");
			obj.put("huiff", "");
			obj.put("shuif", "");
			if (maps.get("LIUFZK") != null) {
				obj.put("liul", maps.get("LIUFZK").toString());
			}
			obj.put("qit", "");// ?
			obj.put("xiaoj", df_2.format(hetjg - danjhj));
			obj.put("danjhj", danjhj);
			obj.put("buhsj", df_2.format(buhsmj));
			obj.put("meik", maps.get("MEIKJE").toString());
			obj.put("shuij", maps.get("SHUIJ").toString());
			if(map3!=null){
				obj.put("rez1", map3.get("QNET_AR").toString() + "/" + df_2.format(rez_j));
				obj.put("huif1", map3.get("AAR").toString());
				obj.put("huiff1", map3.get("VAR").toString());
				obj.put("shuif1", map3.get("MT").toString());
				obj.put("liul1", map3.get("STAR").toString());
			}else{
				obj.put("rez1","");
				obj.put("huif1", "");
				obj.put("huiff1", "");
				obj.put("shuif1","");
				obj.put("liul1","");
			}

			obj.put("yingfjk", maps.get("MEIKJE").toString());
			obj.put("yingfsj", maps.get("SHUIJ").toString());
			obj.put("qitkk", "");
			obj.put("shifje", maps.get("JIESJE").toString());
			obj.put("shifmyk", "");
			obj.put("guot", "");
			obj.put("dit", "");
			obj.put("guot1", "");
			obj.put("dit1", "");
			obj.put("kuangy", "");
			obj.put("zhuanx", "");
			obj.put("duany", "");
			obj.put("qiy", "");
			obj.put("dianfj", "");
			obj.put("fens", "");
			obj.put("chuz", "");
			obj.put("daok", "");
			obj.put("qit1", "");
			obj.put("xiaoj1", "");
			obj.put("qusc", "");
			obj.put("biangf", "");
			obj.put("qit2", "");
			obj.put("xiaoj2", "");
			obj.put("yinhs", "");
			obj.put("guotyf", "");
			obj.put("dityf", "");
			obj.put("kuangqyf", "");
			obj.put("zhuangxyf", "");
			obj.put("duantyf", "");
			obj.put("qicyf", "");
			obj.put("qityf", "");
			obj.put("yunzf", "");
			obj.put("fujf", "");
			obj.put("qit3", "");
			obj.put("shifyfje", "");
			obj.put("beiz", "注："+maps.get("BEIZ"));
			a.add(obj);
			String hetbh="";
			if(maps.get("HETBH")!=null){
				hetbh=maps.get("HETBH").toString();
			}
			String[] parms = { diancxx.getMingc(), diancxx.getQuanc(), diancxx.getId().toString(),
					maps.get("JIESBH").toString(), hetbh, maps.get("GONGYSMC").toString(),
					String.valueOf(shul), String.valueOf(shifje), renyxx.getId().toString(), "", "", renwms, html,
					a.toString() };
			Object[] r = client.invoke("saveJiessd", parms);
			String ret = (String) r[0];
			jdbcTemplate.update("update rl_js_yuejsdb "
					+ "set zhuangt = 1,"
					+ "liuc_id = '" + ret 
					+ "' where id = '"+ map.get("jiesdid") + "'");
			jsonArray.add(1);
		} catch (Exception e) {
			jsonArray.add(0);
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public JSONArray check(Map<String, Object> map) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		String zhuangt = jdbcTemplate.queryForObject(
				"select zhuangt from rl_js_yuejsdb where id = '" + map.get("jiesdid") + "'", String.class);
		jsonArray.add(zhuangt);
		return jsonArray;
	}
}
