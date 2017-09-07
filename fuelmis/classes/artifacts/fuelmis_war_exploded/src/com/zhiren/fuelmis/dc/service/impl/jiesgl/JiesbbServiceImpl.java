package com.zhiren.fuelmis.dc.service.impl.jiesgl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.jiesgl.JiesbbDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.jiesgl.IJiesbbService;
import com.zhiren.fuelmis.dc.utils.PropertiesUtil;

 

/**
 * @author 陈宝露
 */
@Service
public class JiesbbServiceImpl implements IJiesbbService {

	@Autowired
	private JiesbbDao jiesbbDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<String> getAllJiesbh(Map<String, Object> map) {
		return jiesbbDao.getAllJiesbh(map);
	}

	@Override
	public JSONArray getJiesd(Map<String, Object> map) {
		Report rt = new Report();
		String ArrHeader[][] = new String[6][19];
		ArrHeader[0] = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
		ArrHeader[1] = new String[] { "国 电 电 力 发 展 股 份 有 限 公 司", "国 电 电 力 发 展 股 份 有 限 公 司", "国 电 电 力 发 展 股 份 有 限 公 司",
				"国 电 电 力 发 展 股 份 有 限 公 司", "国 电 电 力 发 展 股 份 有 限 公 司", "国 电 电 力 发 展 股 份 有 限 公 司",
				"国 电 电 力 发 展 股 份 有 限 公 司", "国 电 电 力 发 展 股 份 有 限 公 司", "国 电 电 力 发 展 股 份 有 限 公 司",
				"国 电 电 力 发 展 股 份 有 限 公 司", "国 电 电 力 发 展 股 份 有 限 公 司", "国 电 电 力 发 展 股 份 有 限 公 司",
				"国 电 电 力 发 展 股 份 有 限 公 司", "国 电 电 力 发 展 股 份 有 限 公 司", "国 电 电 力 发 展 股 份 有 限 公 司",
				"国 电 电 力 发 展 股 份 有 限 公 司", "国 电 电 力 发 展 股 份 有 限 公 司", "国 电 电 力 发 展 股 份 有 限 公 司",
				"国 电 电 力 发 展 股 份 有 限 公 司" };
		ArrHeader[2] = new String[] { "SP POWER DEVELOPMENT CO.,LTD.", "SP POWER DEVELOPMENT CO.,LTD.",
				"SP POWER DEVELOPMENT CO.,LTD.", "SP POWER DEVELOPMENT CO.,LTD.", "SP POWER DEVELOPMENT CO.,LTD.",
				"SP POWER DEVELOPMENT CO.,LTD.", "SP POWER DEVELOPMENT CO.,LTD.", "SP POWER DEVELOPMENT CO.,LTD.",
				"SP POWER DEVELOPMENT CO.,LTD.", "SP POWER DEVELOPMENT CO.,LTD.", "SP POWER DEVELOPMENT CO.,LTD.",
				"SP POWER DEVELOPMENT CO.,LTD.", "SP POWER DEVELOPMENT CO.,LTD.", "SP POWER DEVELOPMENT CO.,LTD.",
				"SP POWER DEVELOPMENT CO.,LTD.", "SP POWER DEVELOPMENT CO.,LTD.", "SP POWER DEVELOPMENT CO.,LTD.",
				"SP POWER DEVELOPMENT CO.,LTD.", "SP POWER DEVELOPMENT CO.,LTD." };
		ArrHeader[3] = new String[] { "燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单",
				"燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单",
				"燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单",
				"燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单",
				"燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单", "燃  料  （ 煤 炭 ）  结  算  单",
				"燃  料  （ 煤 炭 ）  结  算  单" };
		ArrHeader[4] = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
		// 结算单信息
		Map<String, Object> m = jdbcTemplate
				.queryForMap("select ZHUANGT from RL_JS_YUEJSDB where JIESBH='" + map.get("jiesbh") + "'");
		Map<String, Object> maps = null;
		
		Map<String, Object> map_yssj = jdbcTemplate.queryForMap(
					"select round_new(sum(jingz*QNET_AR)/sum(jingz),2) as QNET_AR,\n" +
					"       round_new(round_new(sum(jingz*QNET_AR)/sum(jingz),2)/0.0041816,0) as QNET_AR_DK,\n" + 
					"       round_new(sum(jingz*AAR)/sum(jingz),2) as AAR,\n" + 
					"       round_new(sum(jingz*STAR)/sum(jingz),2) as STAR,\n" + 
					"       round_new(sum(jingz*VDAF)/sum(jingz),2) as VDAF,\n" + 
					"       round_new(sum(jingz*MT)/sum(jingz),2) as MT\n" + 
					"from\n" + 
					"(SELECT round(SUM(Cp.MAOZ - Cp.PIZ - cp.zongkd), 2) JINGZ,\n" + 
					"       Cp.SAMCODE,\n" + 
					"       MAX(Hy.QNET_AR) QNET_AR,\n" + 
					"       MAX(Hy.AAR) AAR,\n" + 
					"       MAX(Hy.VAD) VAD,\n" + 
					"       MAX(Hy.MT) MT,\n" + 
					"       MAX(ROUND(Hy.STAD * (100 - Hy.MT) / (100 - Hy.MAD), 2)) STAR,\n" + 
					"       MAX(ROUND(Hy.MAD, 2)) MAD,\n" + 
					"       MAX(ROUND(Hy.Vdaf, 2)) VDAF,\n" + 
					"       MAX(ROUND(Hy.AAD, 2)) AAD,\n" + 
					"       round(max(js.jiesjg), 2) jiesjg\n" + 
					"  from rl_ys_chepb    cp,\n" + 
					"       rl_ys_chepb_qc qc,\n" + 
					"       vm_caizhbm     bm,\n" + 
					"       rl_hy_huaydb   hy,\n" + 
					"       rl_js_yuejsdb  js\n" + 
					" where cp.id = qc.chepb_id\n" + 
					"   and js.id = cp.rl_js_yuejsdb_id\n" + 
					"   and cp.samcode = bm.caiybm\n" + 
					"   and bm.huaybm = hy.huaybm\n" + 
					"   and js.jiesbh = '" + map.get("jiesbh") + "'\n" + 
					" group by cp.rl_js_yuejsdb_id, Cp.SAMCODE)");
		
        //查询结算单信息
		maps = jdbcTemplate.queryForMap( "SELECT id,nvl(jiesbh, 0) jiesbh,  nvl(caozrq, 0) caozrq, nvl(gongysb_id, 0) gongysb_id,\n" +
						"  nvl(faz_id, 0) faz_id,nvl(pinzb_id, 0) pinzb_id,nvl(zhongcsj, 0) zhongcsj,nvl(qingcsj, 0) qingcsj,\n" + 
						"  nvl(diancxxb_id, 0) diancxxb_id,nvl(piaoz, 0) piaoz,nvl(ches, 0) ches,nvl(daibch, 0) daibch,\n" + 
						"  nvl(jiesrz, 0) jiesrz,nvl(jieslf, 0) jieslf,nvl(jiessl, 0) jiessl,nvl(rezzk, 0) rezzk,\n" + 
						"  nvl(liufzk, 0) liufzk,nvl(jiesjg, 0) jiesjg,nvl(jiesje, 0) jiesje,nvl(jihkjb_id, 0) jihkjb_id,\n" + 
						"  nvl(yingd, 0) yingd,nvl(kuid, 0) kuid,nvl(jingz, 0) jingz,nvl(hetjg, 0) hetjg,nvl(changrljbr, 0) changrljbr,\n" + 
						"  nvl(changrljsrq, 0) changrljsrq,nvl(beiz, ' ') beiz,nvl(zhuangt, 0) zhuangt,nvl(liuc_id, 0) liuc_id,\n" + 
						"  nvl(meikje, 0) meikje,nvl(shuik, 0) shuik,nvl(shijd, 0) shijd,nvl(hetbh, 0) hetbh,nvl(jieslx, 0) jieslx,\n" + 
						"  nvl(shangccwzt, 0) shangccwzt,nvl(fahksrq, 0) fahksrq,nvl(fahjzrq, 0) fahjzrq,nvl(yansksrq, 0) yansksrq,\n" + 
						"  nvl(yansjzrq, 0) yansjzrq,nvl(shoukdw, 0) shoukdw,nvl(kaihyh, 0) kaihyh,nvl(zhangh, 0) zhangh,nvl(fapbh, 0) fapbh,\n" + 
						"  nvl(yzfxm, 0) yzfxm,nvl(pinz, 0) pinz,nvl(gongysmc, 0) gongysmc,nvl(meikxxb_id, 0) meikxxb_id,\n" + 
						"  nvl(meikmc, 0) meikmc,nvl(diancmc, 0) diancmc,nvl(fazmc, 0) fazmc,nvl(pinzmc, 0) pinzmc,nvl(yansbh, 0) yansbh,\n" + 
						"  nvl(buhsdj, 0) buhsdj,nvl(bukk, 0) bukk,nvl(jiakhj, 0) jiakhj,nvl(shuil, 0) shuil,nvl(meikhj, 0) meikhj,\n" + 
						"  nvl(yunf, 0) yunf,nvl(yunzf, 0) yunzf,nvl(kuangqyf, 0) kuangqyf,nvl(kuangqzf, 0) kuangqzf,nvl(bukqyf, 0) bukqyf,\n" + 
						"  nvl(yunfbhs, 0) yunfbhs,nvl(shuil1, 0) shuil1,nvl(shuik1, 0) shuik1,nvl(yunzfhj, 0) yunzfhj,nvl(jufyf, 0) jufyf,\n" + 
						"  nvl(jufzf, 0) jufzf,nvl(guohsl, 0) guohsl,nvl(ZONGHJ, 0) ZONGHJ,nvl(jiesslcy, 0) jiesslcy,\n" + 
						"  nvl((select sum(zj.zhejbz) as zhibzdj\n" + 
						"        from rl_js_jieszbsjb zj\n" + 
						"        where zj.jiesdbh = rl_js_yuejsdb.jiesbh),\n" + 
						"      0) zhibzdj\n" + 
						"  FROM rl_js_yuejsdb\n" + 
						"  WHERE jiesbh = '" + map.get("jiesbh") + "' "  );
		Map<String, Object> map3=null;
		String stryansrq=null;
		if(maps.get("JIESLX").equals("杂费结算" ) ){
			stryansrq = jdbcTemplate.queryForObject(
					" select shijd as qingcsj from rl_js_yuejsdb where jiesbh = '"+ map.get("jiesbh") + "'",String.class);
		}else {
			stryansrq = maps.get("FAHKSRQ").toString() +"  至  " +maps.get("FAHJZRQ").toString() ;
            //查询质量
			map3 =jdbcTemplate.queryForMap(
					"select sum(decode(j.leib, 'JINGZ', j.zhejbz, 0)) as SL_ZDJ,\n" +
					"       sum(decode(j.leib, 'QNET_AR_DK', j.jies, 0)) as QNET_AR,\n" + 
					"       round_new(sum(decode(j.leib, 'QNET_AR_DK', j.jies*0.0041816, 0)),2) as rez_j,\n" + 
					"       sum(decode(j.leib, 'QNET_AR_DK', j.zhejbz, 0)) as Qnetar_ZDJ,\n" + 
					"       sum(decode(j.leib, 'MT', j.jies, 0)) as MT,\n" + 
					"       sum(decode(j.leib, 'MT', j.zhejbz, 0)) as Mt_ZDJ,\n" + 
					"       sum(decode(j.leib, 'AD', j.jies, 0)) as Ad,\n" + 
					"       sum(decode(j.leib, 'AD', j.zhejbz, 0)) as Ad_ZDJ,\n" + 
					"       sum(decode(j.leib, 'STD', j.jies, 'STAR', j.jies , 0)) as STAR,\n" + 
					"       sum(decode(j.leib, 'STD', j.zhejbz,'STAR', j.jies ,  0)) as St_ZDJ\n" + 
					"  from rl_js_jieszbsjb j where jiesdbh ='"+ map.get("jiesbh").toString()+"' group by j.jiesdbh");
		}					
		

		String tianbdw = maps.get("DIANCMC").toString();
		String strHetbh = maps.get("HETBH").toString();;
		String strbianh = maps.get("JIESBH").toString();
		ArrHeader[5] = new String[] { "单位：" + tianbdw, "单位：" + tianbdw, "单位：" + tianbdw, "单位：" + tianbdw,
				"日期：" + stryansrq, "日期：" + stryansrq, "日期：" + stryansrq, "日期：" + stryansrq, "日期：" + stryansrq,
				"合同号：" + strHetbh, "合同号：" + strHetbh, "合同号：" + strHetbh, "单位：吨,元/吨,MJ/kg,%,元", "单位：吨,元/吨,MJ/kg,%,元",
				"单位：吨,元/吨,MJ/kg,%,元", "单位：吨,元/吨,MJ/kg,%,元", "编号：" + strbianh, "编号：" + strbianh, "编号：" + strbianh };

		String ArrBody[][] = new String[15][19];
		ArrBody[0] = new String[] { "结算部门：" + PropertiesUtil.getValue("jiesbm"), "", "", "供货单位：" + maps.get("GONGYSMC"),
				"", "", "", "", "", "煤矿单位："+ maps.get("MEIKMC"), "", "", "", "计划渠道：" + maps.get("JIHKJMC"), "", "", "",
				"品种：" + maps.get("PINZMC"), "" };
		ArrBody[1] = new String[] { "数量", "", "", "", "", "单价", "", "", "", "", "", "", "", "", "", "", "煤款", "",
				"" };
		ArrBody[2] = new String[] { "车数", "票重", "盈吨", "亏吨", "实收", "煤价", "扣款", "", "", "", "", "", "", "单价合计", "不含税价",
				"", "", "", "" };
		ArrBody[3] = new String[] { "", "", "", "", "", "", "热值", "灰分", "挥发分", "水分", "硫量", "其他", "小计", "", "", "", "",
				"", "" };

		DecimalFormat df_2 = new DecimalFormat("0.00");
		double hetjg = 0;
		double rezzk = 0;
		double huifzk = 0;
		double huiffzk = 0;
		double shuifzk = 0;
		double liufzk = 0;
		double koukxj = 0;
		double danjhj = 0;
		double buhsmj = 0;
		long   shul = 0;
		double meikhj = 0;
		double bukk = 0;
		double mk_yf_hj = 0;
		double mk_yf_bhsje = 0;
		double mk_yf_sk = 0;
		double mk_yf_hsdj = 0;
		
		if(maps.get("JIESLX").equals("杂费结算" ) ){
			koukxj = 0 ;//扣价小计
			danjhj = Double.parseDouble(df_2.format(hetjg- (koukxj )))  ; //Double.parseDouble(maps.get("JIESJG").toString());
			buhsmj = danjhj / 1.17;
			shul = Math.round(Double.parseDouble(maps.get("JINGZ").toString()));
			meikhj = Double.parseDouble(maps.get("MEIKHJ").toString()); //不含补扣
			bukk = Double.parseDouble(maps.get("BUKK").toString()); //补扣
			mk_yf_hj = Double.parseDouble(maps.get("JIAKHJ").toString()); // 煤款应付合计
		    mk_yf_bhsje =Double.parseDouble(maps.get("MEIKJE").toString()); //煤款应付不含税
		    mk_yf_sk = Double.parseDouble(maps.get("SHUIK").toString()); //煤款应付税款
		    mk_yf_hsdj = Double.parseDouble(maps.get("JIESJG").toString()); //煤款含税单价
		}else{
			hetjg = Double.parseDouble(maps.get("HETJG").toString()) + Double.parseDouble(map3.get("SL_ZDJ").toString())  ;
			//热值 	灰分 	挥发分	水分	 硫量	其他
			rezzk =  (map3.get("Qnetar_ZDJ") == null ? 0 : Double.parseDouble((df_2.format(Double.parseDouble(maps.get("HETJG").toString()) -Double.parseDouble(map3.get("Qnetar_ZDJ").toString())) )));
			huifzk = map3.get("Ad_ZDJ") == null ? 0 : Double.parseDouble(map3.get("Ad_ZDJ").toString()); ;
			huiffzk = 0;
			shuifzk = map3.get("Mt_ZDJ") == null ? 0 : Double.parseDouble(map3.get("Mt_ZDJ").toString());;		
			liufzk = map3.get("St_ZDJ") == null ? 0 : Double.parseDouble(map3.get("St_ZDJ").toString());
			koukxj = Double.parseDouble(df_2.format(rezzk + huifzk+ huiffzk +shuifzk +liufzk)) ;//扣价小计
			danjhj = Double.parseDouble(df_2.format(hetjg- (koukxj )))  ; //Double.parseDouble(maps.get("JIESJG").toString());
			buhsmj = danjhj / 1.17;
			shul = Math.round(Double.parseDouble(maps.get("JINGZ").toString()));
			meikhj = Double.parseDouble(maps.get("MEIKHJ").toString()); //不含补扣
			bukk = Double.parseDouble(maps.get("BUKK").toString()); //补扣
			mk_yf_hj = Double.parseDouble(maps.get("JIAKHJ").toString()); // 煤款应付合计
		    mk_yf_bhsje =Double.parseDouble(maps.get("MEIKJE").toString()); //煤款应付不含税
		    mk_yf_sk = Double.parseDouble(maps.get("SHUIK").toString()); //煤款应付税款
		    mk_yf_hsdj = Double.parseDouble(maps.get("JIESJG").toString()); //煤款含税单价
		}
		
		long rez_j=0;
		if(map3!=null){
			rez_j = 0;	
		}
	
		ArrBody[4] = new String[] { maps.get("CHES").toString(), String.valueOf(shul),
				maps.get("YINGD") == null ? "" : maps.get("YINGD").toString(),
				maps.get("KUID") == null ? "" : maps.get("KUID").toString(), String.valueOf(shul),
				String.valueOf(hetjg), String.valueOf(rezzk), String.valueOf(huifzk), String.valueOf(huiffzk), String.valueOf(shuifzk), String.valueOf(liufzk), "",
				String.valueOf(koukxj), String.valueOf(danjhj), df_2.format(buhsmj), "", df_2.format(meikhj), "",
				"" };
		ArrBody[5] = new String[] { "热值", "热值", "灰分", "挥发分", "水分", "硫量",  "其他扣款", "", "应付价款", "", "应付税金", "","实付金额", "", 
				"应付含税单价", "", "", "", "" };
		if(map3!=null){
			String rez = map3.get("REZ_J") == null ? map_yssj.get("QNET_AR")==null?"0":map_yssj.get("QNET_AR").toString():map3.get("REZ_J").toString();
			String rez_dk = map3.get("QNET_AR") == null ? map_yssj.get("QNET_AR_DK")==null?"0":map_yssj.get("QNET_AR_DK").toString():map3.get("QNET_AR").toString();
			String huif  = map3.get("Ad") == null ? map_yssj.get("AAR")==null?"0":map_yssj.get("AAR").toString():map3.get("Ad").toString();
			String huiff =map3.get("VDAF") == null ? map_yssj.get("VDAF")==null?"0":map_yssj.get("VDAF").toString():map3.get("VDAF").toString();
			String mt =map3.get("MT") == null ? map_yssj.get("MT")==null?"0":map_yssj.get("MT").toString():map3.get("MT").toString();
			String liuf = map3.get("STAR") == null ? map_yssj.get("STAR")==null?"0":map_yssj.get("STAR").toString():map3.get("STAR").toString();
			ArrBody[6] = new String[] { rez, rez_dk, huif , huiff, mt , liuf, 
					df_2.format(bukk),"", df_2.format(mk_yf_hj), "", df_2.format(mk_yf_bhsje), 
					"", df_2.format(mk_yf_sk), "", df_2.format(mk_yf_hsdj), "", "", "", "" };
		}else{ 
			ArrBody[6] = new String[] { String.valueOf(rez_j), "", "","", "", "", df_2.format(mk_yf_hj),
					"", df_2.format(mk_yf_bhsje), "", "", "", df_2.format(mk_yf_sk), "", "", "", "", "", "" };
		}
		
		ArrBody[7] = new String[] { "运距", "", "运费单价明细", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "印花税" };
		ArrBody[8] = new String[] { "国铁", "地铁", "国铁", "地铁", "矿运", "专线", "短运", "汽运", "其他运费", "", "", "", "", "",
				"运杂费(元/车)", "", "", "", "" };
		ArrBody[9] = new String[] { "", "", "", "", "", "", "", "", "电附加", "风沙", "储装", "道口", "其它", "小计", "取送车", "变更费",
				"合同折单价", "不含税价", "" };
		ArrBody[10] = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
		ArrBody[11] = new String[] { "国铁运费", "", "地铁运费", "", "矿区运费", "", "专线运费", "", "短途运费", "", "汽车运费", "", "其他运费", "",
				"运杂费", "", "扣款", "", "实付运费金额" };
		ArrBody[12] = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "亏吨费", "其他", "" };
		ArrBody[13] = new String[] { "" + "" + "", "", "", "", "" + "" + "", "", "", "", "", "", "" + "" + "", "", "",
				"", "", "", "" + "" + "", "", "" + "" + "" };
		ArrBody[14] = new String[] { "注:"+maps.get("BEIZ").toString() , "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
		
		String jiesr = maps.get("CHANGRLJBR").toString();
		
		// 调用webService获取审批信息
		 
		String ArrFooter[][] = new String[4][19];
		ArrFooter[0] = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
		ArrFooter[1] = new String[] { "", "", "", "市场营销部负责人：", "", "", "", "", "", "", "", "","", "", "结算人："+jiesr, "", "", "", "" };
		ArrFooter[2] = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
		ArrFooter[3] = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

		int ArrWidth[] = new int[] { 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54 };
		// 定义页Title
		rt.setTitle(new Table(ArrHeader, 0, 0, 0));
		rt.setBody(new Table(ArrBody, 0, 0, 0));
		rt.setFooter(new Table(ArrFooter, 0, 0, 0));
		rt.body.setWidth(ArrWidth);
		rt.title.setWidth(ArrWidth);
		rt.footer.setWidth(ArrWidth);
		rt.body.setBorder(2, 2, 2, 2);
		// 合并单元格
		// 表头_Begin
		// rt.title.merge(1, 1, 1, 19);
		rt.title.merge(2, 1, 2, 19);
		rt.title.merge(3, 1, 3, 19);
		rt.title.merge(4, 1, 4, 19);
		rt.title.merge(5, 1, 5, 19);

		rt.title.merge(6, 1, 6, 4);
		rt.title.merge(6, 5, 6, 9);
		rt.title.merge(6, 10, 6, 12);
		rt.title.merge(6, 13, 6, 16);
		rt.title.merge(6, 17, 6, 19);

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
		rt.title.setRowCells(6, Table.PER_ALIGN, Table.ALIGN_LEFT);

		// 字体
		rt.title.setCells(2, 1, 2, 19, Table.PER_FONTNAME, "黑体");
		rt.title.setCells(2, 1, 2, 19, Table.PER_FONTSIZE, 11);
		rt.title.setCells(3, 1, 3, 19, Table.PER_FONTNAME, "Arial Unicode MS");
		rt.title.setCells(3, 1, 3, 19, Table.PER_FONTSIZE, 12);
		rt.title.setCells(4, 1, 4, 19, Table.PER_FONTNAME, "隶书");
		rt.title.setCells(4, 1, 4, 19, Table.PER_FONTSIZE, 20);
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

		rt.footer.mergeCell(1, 1, 1, 19);
		rt.footer.setRowHeight(1, 8);
		rt.footer.setRowCells(1, Table.PER_BORDER_BOTTOM, 0);
		rt.footer.setRowCells(1, Table.PER_BORDER_RIGHT, 0);

		rt.footer.setCells(1, 1, 1, 1, Table.PER_BORDER_LEFT, 0);
		rt.footer.setCells(1, 4, 1, 4, Table.PER_BORDER_RIGHT, 0);

		rt.footer.mergeCell(2, 1, 2, 2);
		rt.footer.mergeCell(2, 3, 2, 4);
		rt.footer.mergeCell(2, 5, 2, 6);
		rt.footer.setCellAlign(2, 5, Table.ALIGN_RIGHT);
		rt.footer.mergeCell(2, 7, 2, 8);
		rt.footer.mergeCell(2, 9, 2, 10);
		rt.footer.setCellAlign(2, 9, Table.ALIGN_RIGHT);

		rt.footer.mergeCell(2, 11, 2, 12);
		rt.footer.mergeCell(2, 13, 2, 14);
		rt.footer.mergeCell(2, 15, 2, 16);
		rt.footer.setCellAlign(2, 13, Table.ALIGN_RIGHT);
		rt.footer.mergeCell(2, 18, 2, 19);
		rt.footer.setRowCells(2, Table.PER_BORDER_BOTTOM, 0);
		rt.footer.setRowCells(2, Table.PER_BORDER_RIGHT, 0);

		rt.footer.mergeCell(3, 1, 3, 4);
		rt.footer.setRowHeight(33, 0);
		rt.footer.setRowCells(3, Table.PER_BORDER_BOTTOM, 0);
		rt.footer.setRowCells(3, Table.PER_BORDER_RIGHT, 0);

		rt.footer.mergeCell(4, 1, 4, 2);
		rt.footer.mergeCell(4, 3, 4, 4);
		rt.footer.mergeCell(4, 5, 4, 6);
		rt.footer.setCellAlign(4, 5, Table.ALIGN_RIGHT);
		rt.footer.mergeCell(4, 7, 4, 8);
		rt.footer.mergeCell(4, 9, 4, 10);
		rt.footer.setCellAlign(4, 9, Table.ALIGN_RIGHT);

		rt.footer.mergeCell(4, 11, 4, 12);
		rt.footer.mergeCell(4, 13, 4, 14);
		rt.footer.mergeCell(4, 15, 4, 16);
		rt.footer.setCellAlign(4, 13, Table.ALIGN_RIGHT);
		rt.footer.mergeCell(4, 18, 4, 19);
		rt.footer.setRowCells(4, Table.PER_BORDER_BOTTOM, 0);
		rt.footer.setRowCells(4, Table.PER_BORDER_RIGHT, 0);
		rt.footer.setBorder(0, 0, 0, 0);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml());
		resultMap.put("pageCount", 1);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}
	@Override
	public JSONObject gethycJiesd(Map<String, Object> map) {
		Map<String, Object> maps_jisd = null;
		Map<String, Object> maps_hyz = null;
		JSONObject jo = new JSONObject();
        //查询数量、单价及金额
		maps_jisd = jdbcTemplate.queryForMap(
				"select JIESBH,  DIANCMC, CAOZRQ, GONGYSMC,  MEIKXXB_ID, MEIKMC,\n" +
						"FAZMC, PINZ, PINZMC, JIHKJB_ID, SHOUKDW,  HETBH, CHES, PIAOZ, JINGZ,\n" + 
						"YINGD, KUID, QINGCSJ, YANSKSRQ, YANSJZRQ, JIESRZ, REZZK,\n" + 
						"JIESMT, MTZK, JIESMAD,MADZK,JIESHF,HIUFZK,JIESHFF,HIUFFZK,JIESLF,\n" + 
						"LIUFZK,HETJG,JIESJG,BUHSDJ,JIESJE,MEIKJE,SHUIK,BUKK,JIAKHJ,\n" + 
						"SHUIL,MEIKHJ,CHANGRLJBR,CHANGRLJSRQ,YZFXM,JIESLX,BEIZ,ZHUANGT,LIUC_ID\n" + 
						"from rl_js_yuejsdb where jiesbh= '" + map.get("jiesbh") + "'");
		maps_hyz =jdbcTemplate.queryForMap(
						"select sum(decode(j.leib, 'JINGZ', j.zhejbz, 0)) as SL_ZDJ,\n" +
						"       sum(decode(j.leib, 'QNET_AR_DK', j.jies, 0)) as Qnetar_ZBB,\n" + 
						"       round_new(sum(decode(j.leib, 'QNET_AR_DK', j.jies*0.0041816, 0)),2) as Qnetar_ZBBS,\n" + 
						"       sum(decode(j.leib, 'QNET_AR_DK', j.zhejbz, 0)) as Qnetar_ZDJ,\n" + 
						"       sum(decode(j.leib, 'MT', j.jies, 0)) as Mt_ZBB,\n" + 
						"       sum(decode(j.leib, 'MT', j.zhejbz, 0)) as Mt_ZDJ,\n" + 
						"       sum(decode(j.leib, 'AD', j.jies, 0)) as Ad_ZBB,\n" + 
						"       sum(decode(j.leib, 'AD', j.zhejbz, 0)) as Ad_ZDJ,\n" + 
						"       sum(decode(j.leib, 'STD', j.jies, 'STAR', j.jies , 0)) as St_ZBB,\n" + 
						"       sum(decode(j.leib, 'STD', j.zhejbz,'STAR', j.jies ,  0)) as St_ZDJ\n" + 
						"  from rl_js_jieszbsjb j where jiesdbh ='"+ map.get("jiesbh").toString()+"' group by j.jiesdbh");
		jo.putAll(maps_jisd);
		jo.putAll(maps_hyz);
		return jo;
	}

	@Override
	public JSONArray getYansmx(Map<String, Object> map) {
		
		Map<String, Object> maps = jdbcTemplate.queryForMap(
				"SELECT G.QUANC GONGYS,D.QUANC DIANC,JS.CAOZRQ,JS.JIESBH FROM RL_JS_YUEJSDB JS,GONGYSB G,DIANCXXB D "
						+ "WHERE JS.GONGYSB_ID = G.ID AND JS.DIANCXXB_ID = D.ID AND JS.JIESBH = '" + map.get("jiesbh")
						+ "'");

		String sql = "SELECT SUBSTR(QC.QINGCSJ,0,10) QINGCSJ,P.MINGC PINZMC,Y.MINGC YUNSFS,SUM(PIAOZ) PIAOZ,SUM(MAOZ-PIZ-zongkd) JINGZ,C.SAMCODE,"
				+ "M.MUBBM,M2.MUBBM,MAX(H.QNET_AR) QNET_AR,ROUND(MAX(H.QNET_AR/4.1816*1000),0) QNET_AR_K,MAX(H.AAR) AAR,MAX(H.VAD) VAD,MAX(H.MT) MT,"
				+ "MAX(ROUND(H.STAD*(100-H.MT)/(100-H.MAD),2)) STAR "
				+ "FROM (SELECT * FROM RL_YS_CHEPB WHERE RL_JS_YUEJSDB_ID =(SELECT ID FROM RL_JS_YUEJSDB WHERE JIESBH = '" + map.get("jiesbh") + "'))"
				+ " C,RL_YS_CHEPB_QC QC,PINZB P,YUNSFSB Y,GX_CHEP_CAIZHBMB M,GX_CHEP_CAIZHBMB M2,RL_HY_HUAYDB H "
				+ "WHERE C.ID = QC.CHEPB_ID AND C.PINZB_ID = P.ID AND C.YUNSFSB_ID=Y.ID AND M.YUANBM = C.SAMCODE AND M2.YUANBM = M.MUBBM "
				+ "AND H.HUAYBM = M2.MUBBM "
				+ "GROUP BY  SUBSTR(QC.QINGCSJ,0,10),P.MINGC,Y.MINGC,C.SAMCODE,M.MUBBM,M2.MUBBM";

		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		double piaoz = 0;
		double jingz = 0;
		double qnet_ar_mj = 0;
		long qnet_ar_kcal = 0;
		double aar = 0;
		double vad = 0;
		double mar = 0;
		double star = 0;
		String[][] arrData = new String[list.size() + 1][16];
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> rt = list.get(i);
			arrData[i][0] = String.valueOf(i + 1);
			arrData[i][1] = rt.get("QINGCSJ") == null ? "" : rt.get("QINGCSJ").toString();
			arrData[i][2] = rt.get("PINZMC") == null ? "" : rt.get("PINZMC").toString();
			arrData[i][3] = rt.get("YUNSFS") == null ? "" : rt.get("YUNSFS").toString();
			arrData[i][4] = rt.get("PIAOZ") == null ? "" : rt.get("PIAOZ").toString();
			piaoz += Double.parseDouble(rt.get("PIAOZ").toString());
			arrData[i][5] = rt.get("JINGZ") == null ? "" : rt.get("JINGZ").toString();
			jingz += Double.parseDouble(rt.get("JINGZ").toString());
			arrData[i][6] = "";
			arrData[i][7] = "";
			arrData[i][8] = "";
			arrData[i][9] = rt.get("QNET_AR") == null ? "" : rt.get("QNET_AR").toString();
			qnet_ar_mj += Double.parseDouble(rt.get("JINGZ").toString())
					* Double.parseDouble(rt.get("QNET_AR").toString());
			arrData[i][10] = rt.get("QNET_AR_K") == null ? "" : rt.get("QNET_AR_K").toString();
			qnet_ar_kcal += Math.round(Double.parseDouble(rt.get("JINGZ").toString())
					* Double.parseDouble(rt.get("QNET_AR_K").toString()));
			arrData[i][11] = rt.get("AAR") == null ? "" : rt.get("AAR").toString();
			aar += Double.parseDouble(rt.get("JINGZ").toString()) * Double.parseDouble(rt.get("AAR").toString());
			arrData[i][12] = rt.get("VAD") == null ? "" : rt.get("VAD").toString();
			vad += Double.parseDouble(rt.get("JINGZ").toString()) * Double.parseDouble(rt.get("VAD").toString());
			arrData[i][13] = rt.get("MT") == null ? "" : rt.get("MT").toString();
			mar += Double.parseDouble(rt.get("JINGZ").toString()) * Double.parseDouble(rt.get("MT").toString());
			arrData[i][14] = rt.get("STAR") == null ? "" : rt.get("STAR").toString();
			star += Double.parseDouble(rt.get("JINGZ").toString()) * Double.parseDouble(rt.get("STAR").toString());
			arrData[i][15] = "";
		}

		Report rt = new Report(); // 报表定义
		String ArrHeader[][] = new String[3][16];
		ArrHeader[0] = new String[] { "序号", "日期", "品种", "运输方式", "数量验收", "数量验收", "数量验收", "数量验收", "数量验收", "质量验收", "质量验收",
				"质量验收", "质量验收", "质量验收", "质量验收", "备注" };
		ArrHeader[1] = new String[] { "序号", "日期", "品种", "运输方式", "矿发量", "验收量", "盈亏量", "途损量", "扣减量", "Qnet,ar", "Qnet,ar",
				"A,ar", "V,ad", "Mar", "St,ar", "备注" };
		ArrHeader[2] = new String[] { "序号", "日期", "品种", "运输方式", "吨", "吨", "吨", "吨", "吨", "MJ/kg", "kcal/kg", "%", "%",
				"%", "%", "备注" };
		int ArrWidth[] = new int[] { 60, 90, 70, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75 };
		rt.setTitle("<br/>国电电力发展股份有限公司燃料验收单", ArrWidth);
		rt.setDefaultTitleLeft("供货单位:" + maps.get("GONGYS") + "<br>收货单位:" + maps.get("DIANC"), 12);
		rt.setDefaultTitleRight("结算单号:" + maps.get("JIESBH") + "<br>日期:" + maps.get("CAOZRQ"), 4);
		rt.setBody(new Table(arrData, 3, 0, 0, 16));
		rt.body.setWidth(ArrWidth);
		rt.body.setHeaderData(ArrHeader);// 表头数据
		rt.body.mergeCell(rt.body.getRows(), 1, rt.body.getRows(), 4);
		rt.body.setCells(1, 1, rt.body.getRows(), rt.body.getCols(), Table.PER_ALIGN, Table.ALIGN_CENTER);
		rt.body.mergeFixedRow();
		rt.body.ShowZero = false;
		rt.body.setRowCells(rt.body.getRows(), Table.PER_BORDER_BOTTOM, 0);
		rt.body.setCellValue(rt.body.getRows(), 1, "合计");
		DecimalFormat df_0 = new DecimalFormat("0");
		DecimalFormat df_1 = new DecimalFormat("0.0");
		DecimalFormat df_2 = new DecimalFormat("0.00");
		DecimalFormat df_3 = new DecimalFormat("0.000");
		rt.body.setCellValue(rt.body.getRows(), 5, df_0.format(piaoz));
		rt.body.setCellValue(rt.body.getRows(), 6, df_0.format(jingz));
		rt.body.setCellValue(rt.body.getRows(), 10, df_3.format(qnet_ar_mj / jingz));
		rt.body.setCellValue(rt.body.getRows(), 11, String.valueOf(Math.round(qnet_ar_kcal / jingz)));
		rt.body.setCellValue(rt.body.getRows(), 12, df_2.format(aar / jingz));
		rt.body.setCellValue(rt.body.getRows(), 13, df_2.format(vad / jingz));
		rt.body.setCellValue(rt.body.getRows(), 14, df_1.format(mar / jingz));
		rt.body.setCellValue(rt.body.getRows(), 15, df_2.format(star / jingz));

		Map<String, Object> maps2 = jdbcTemplate.queryForMap(
				"SELECT JIESRZ,ROUND(JIESRZ/4.1816*1000,0) REZ,ROUND(JINGZ,0) JINGZ,ROUND(HETJG,2) HETJG,ROUND(JIESJG,2) JIESJG,ROUND(JIESJE,2) JIESJE FROM RL_JS_YUEJSDB "
						+ "WHERE JIESBH = '" + map.get("jiesbh") + "'");
		String jingz2 = maps2.get("JINGZ").toString();
		String jiesjg = maps2.get("JIESJG").toString();
		String jiesje = df_2.format(Double.parseDouble(jingz2) * Double.parseDouble(jiesjg));
		String[][] ArrHeaderhj = new String[4][16];
		ArrHeaderhj[0] = new String[] { "结算合计", "结算系数(价格目录)", "结算系数(价格目录)", "结算系数(价格目录)", "结算系数(价格目录)", "结算系数(价格目录)",
				"结算系数(价格目录)", "结算质量", "结算质量", "结算煤量", "煤款", "煤款", "运费", "运费", "运费", "总金额" };
		ArrHeaderhj[1] = new String[] { "结算合计", "Qnet,ar", "数量", "Aar ", "Vdaf", "运距调价", "合计", "Qnet,ar", "Qnet,ar",
				"结算煤量", "煤价", "煤款", "运距", "单价", "金额", "总金额" };
		ArrHeaderhj[2] = new String[] { "结算合计", "元/千卡(元/吨,元/兆焦)", "数量", "Aar", "Vdaf", "元/吨", "合计", "MJ/kg", "kcal/kg",
				"吨", "元/吨", "元", "千米", "元/吨", "元", "元" };
		ArrHeaderhj[3] = new String[] { "采购", "", maps2.get("HETJG").toString(), "", "", "",
				maps2.get("HETJG").toString(), maps2.get("JIESRZ").toString(), maps2.get("REZ").toString(),
				maps2.get("JINGZ").toString(), maps2.get("JIESJG").toString(), jiesje, "", "", "", jiesje };
		int ArrWidthhj[] = new int[] { 60, 90, 70, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75 };
		Report rthj = new Report();
		rthj.setBody(new Table(ArrHeaderhj, 0, 0, 0));
		rthj.body.setWidth(ArrWidthhj);
		rthj.body.mergeCell(1, 1, 3, 1);
		rthj.body.mergeCell(1, 2, 1, 7);
		rthj.body.mergeCell(1, 8, 1, 9);
		rthj.body.mergeCell(1, 10, 2, 10);
		rthj.body.mergeCell(1, 11, 1, 12);
		rthj.body.mergeCell(1, 13, 1, 15);
		rthj.body.mergeCell(1, 16, 2, 16);
		rthj.body.mergeCell(2, 3, 3, 3);
		rthj.body.mergeCell(2, 4, 3, 4);
		rthj.body.mergeCell(2, 5, 3, 5);
		rthj.body.mergeCell(2, 7, 3, 7);
		rthj.body.mergeCell(2, 8, 2, 9);
		rthj.body.setBorder(2, 1, 0, 1);
		rthj.body.setCells(1, 1, 3, rthj.body.getCols(), Table.PER_ALIGN, Table.ALIGN_CENTER);
		rthj.body.setCells(4, 1, ArrHeaderhj.length, 1, Table.PER_ALIGN, Table.ALIGN_CENTER);
		rthj.body.setCells(4, 2, ArrHeaderhj.length, rthj.body.getCols(), Table.PER_ALIGN, Table.ALIGN_CENTER);
		rthj.body.setRowCells(1, Table.PER_BORDER_TOP, 2);
		rthj.body.mergeFixedRow();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml() + rthj.getAllPagesHtml());
		resultMap.put("pageCount", 1);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	@Override
	public JSONArray getGuohd(Map<String, Object> map) {
		
		/* 日结算单查询SQL */
		/*
		 * List<Map<String,Object>> list = jdbcTemplate.queryForList(
		 * "SELECT SUBSTR(QC.QINGCSJ,0,10) QINGCSJ,C.CHEPH,C.MAOZ,C.PIZ,(C.MAOZ-C.PIZ) JINGZ,C.PIAOZ FROM RL_YS_CHEPB C,RL_YS_CHEPB_QC QC WHERE C.ID=QC.CHEPB_ID AND C.ID IN "
		 * +
		 * "(SELECT CHEPB_ID FROM GX_JIESDB_CHEPB WHERE JIESDB_ID = (SELECT ID FROM RL_JS_RIJSDB WHERE JIESBH = '"
		 * + map.get("jiesbh") +"'))");
		 */
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"SELECT SUBSTR(QC.QINGCSJ,0,10) QINGCSJ,C.CHEPH,C.MAOZ,C.PIZ,(C.MAOZ-C.PIZ-c.zongkd) JINGZ,C.PIAOZ FROM RL_YS_CHEPB C,RL_YS_CHEPB_QC QC  "
						+ "WHERE C.ID=QC.CHEPB_ID AND C.RL_JS_YUEJSDB_ID = (SELECT ID FROM RL_JS_YUEJSDB WHERE JIESBH = '" + map.get("jiesbh") + "')");
		String[][] arrData = new String[list.size()][7];
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> rt = list.get(i);
			arrData[i][0] = rt.get("QINGCSJ") == null ? "" : rt.get("QINGCSJ").toString();
			arrData[i][1] = rt.get("CHEPH") == null ? "" : rt.get("CHEPH").toString();
			arrData[i][2] = rt.get("MAOZ") == null ? "" : rt.get("MAOZ").toString();
			arrData[i][3] = rt.get("PIZ") == null ? "" : rt.get("PIZ").toString();
			arrData[i][4] = rt.get("JINGZ") == null ? "" : rt.get("JINGZ").toString();
			arrData[i][5] = rt.get("PIAOZ") == null ? "" : rt.get("PIAOZ").toString();
			arrData[i][6] = "0";
		}
		Report rt = new Report(); // 报表定义
		String ArrHeader[][] = new String[1][7];
		ArrHeader[0] = new String[] { "来煤时间", "车号", "毛重", "皮重", "净重", "票重", "扣吨扣水(吨)" };
		int ArrWidth[] = new int[] { 100, 100, 50, 50, 50, 50, 80 };
		// 数据
		rt.setTitle("<br/>来煤过衡单", ArrWidth);
		Table tab = new Table(arrData, 1, 0, 0);
		rt.setBody(tab);

		rt.body.setWidth(ArrWidth);

		rt.body.setPageRows(15);
		rt.body.setHeaderData(ArrHeader);// 表头数据
		rt.body.ShowZero = true;
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_RIGHT);
		rt.body.setColAlign(4, Table.ALIGN_RIGHT);
		rt.body.setColAlign(5, Table.ALIGN_RIGHT);
		rt.body.setColAlign(6, Table.ALIGN_RIGHT);
		rt.body.setColAlign(7, Table.ALIGN_RIGHT);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml());
		resultMap.put("pageCount", rt.getPages());
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	@Override
	public JSONArray getDanpcmx(Map<String, Object> map) {
		
		// 合同信息
		/* 日结算单查询SQL */
		/*
		 * List<Map<String,Object>> list = jdbcTemplate.queryForList(
		 * "SELECT RUKDBH,HANGH FROM GX_CHURKDB_YUNSDJ WHERE YUANDJLX = 1 AND YUANDJ_ID IN("
		 * +"SELECT CHEPB_ID FROM GX_JIESDB_CHEPB WHERE JIESDB_ID = (" +
		 * "SELECT ID FROM RL_JS_RIJSDB WHERE JIESBH = '"
		 * +map.get("jiesbh")+"'))");
		 */
		
		String sql = "select jiesjg,HETBH from rl_js_yuejsdb where JIESBH ='"+map.get("jiesbh")+"'";
		
		Map<String, Object> maps2 = jdbcTemplate.queryForMap(sql);
		double jij = Double.parseDouble(maps2.get("JIESJG").toString());

		Map<String, Object> maps1 = jdbcTemplate.queryForMap(
				"SELECT ROUND(JINGZ,0) JINGZ,ROUND(HETJG,2) HETJG,ROUND(JIESJG,2) JIESJG,JIAKHJ JIESJE , bukk "
						+ "FROM RL_JS_YUEJSDB WHERE JIESBH = '" + map.get("jiesbh") + "'");

		List<Map<String, Object>> lst = jdbcTemplate.queryForList(
				"SELECT cp.MEIKMC,\n" +
						"       max(substr(qc.zhongcsj, 0, 10)) laimrq,\n" + 
						"       cp.faz as CHEZMC,\n" + 
						"       COUNT(1) CHES,\n" + 
						"       SUM(Cp.PIAOZ) PIAOZ,\n" + 
						"       round(SUM(Cp.MAOZ - Cp.PIZ - cp.zongkd), 2) JINGZ,\n" + 
						"       Cp.SAMCODE,\n" + 
						"       MAX(Hy.QNET_AR) QNET_AR,\n" + 
						"       MAX(round(hy.qnet_ar * 1000 / 4.1816, 0)) QNET_AR,\n" + 
						"       MAX(Hy.AAR) AAR,\n" + 
						"       MAX(Hy.VAD) VAD,\n" + 
						"       MAX(Hy.MT) MT,\n" + 
						"       MAX(ROUND(Hy.STAD * (100 - Hy.MT) / (100 - Hy.MAD), 2)) STAR,\n" + 
						"       MAX(ROUND(Hy.MAD, 2)) MAD,\n" + 
						"       MAX(ROUND(Hy.VDAF, 2)) VDAF,\n" + 
						"       MAX(ROUND(Hy.AAD, 2)) AAD,\n" + 
						"       round(max(js.jiesjg), 2) jiesjg,\n" + 
						"        round(max(js.jiesjg)*SUM(Cp.MAOZ - Cp.PIZ - cp.zongkd), 2) jiesje\n" + 
						"  from rl_ys_chepb    cp,\n" + 
						"       rl_ys_chepb_qc qc,\n" + 
						"       vm_caizhbm     bm,\n" + 
						"       rl_hy_huaydb   hy,\n" + 
						"       rl_js_yuejsdb  js\n" + 
						" where cp.id = qc.chepb_id\n" + 
						" and js.id = cp.rl_js_yuejsdb_id\n" + 
						"   and cp.samcode = bm.caiybm\n" + 
						"   and bm.huaybm = hy.huaybm\n" + 
						"   and js.jiesbh ='"+map.get("jiesbh")+"'\n" + 
						"   group by cp.rl_js_yuejsdb_id,cp.MEIKMC,cp.faz,Cp.SAMCODE\n" );

		int chesSum = 0;
		double piaoz = 0;
		double jingz = 0;
		double qnet_ar_mj = 0;
		double aad = 0;
		double mt = 0;
		double mad = 0;
		double stad = 0;
		double vdaf = 0;

		double jiag = 0;
		String[][] arrData = new String[lst.size() + 1][17];
		for (int i = 0; i < lst.size(); i++) {
			Map<String, Object> rt = lst.get(i);
			arrData[i][0] = rt.get("MEIKMC").toString();
			arrData[i][1] = rt.get("MEIKMC").toString();
			arrData[i][2] = rt.get("LAIMRQ").toString();
			arrData[i][3] = rt.get("CHEZMC").toString();
			arrData[i][4] = String.valueOf(jij);
			arrData[i][5] = "";
			arrData[i][6] = rt.get("STAR").toString();
			stad += Double.parseDouble(rt.get("JINGZ").toString()) * Double.parseDouble(rt.get("STAR").toString());
			arrData[i][7] = rt.get("MT").toString();
			mt += Double.parseDouble(rt.get("JINGZ").toString()) * Double.parseDouble(rt.get("MT").toString());
			arrData[i][8] = rt.get("MAD").toString();
			mad += Double.parseDouble(rt.get("JINGZ").toString()) * Double.parseDouble(rt.get("MAD").toString());
			arrData[i][9] = rt.get("VDAF").toString();
			vdaf += Double.parseDouble(rt.get("JINGZ").toString()) * Double.parseDouble(rt.get("VDAF").toString());
			arrData[i][10] = rt.get("AAD").toString();
			aad += Double.parseDouble(rt.get("JINGZ").toString()) * Double.parseDouble(rt.get("AAD").toString());
			arrData[i][11] = rt.get("QNET_AR").toString();
			qnet_ar_mj += Double.parseDouble(rt.get("JINGZ").toString())
					* Double.parseDouble(rt.get("QNET_AR").toString());
			arrData[i][12] = rt.get("CHES").toString();
			chesSum += Integer.parseInt(rt.get("CHES").toString());
			arrData[i][13] = rt.get("PIAOZ").toString();
			piaoz += Double.parseDouble(rt.get("PIAOZ").toString());
			arrData[i][14] = rt.get("JINGZ").toString();
			jingz += Double.parseDouble(rt.get("JINGZ").toString());
			arrData[i][15] = rt.get("JIESJG").toString();
			jiag += Double.parseDouble(rt.get("JIESJG").toString()) * Double.parseDouble(rt.get("JINGZ").toString());
			arrData[i][16] = rt.get("JIESJE").toString();
		}
		if(jingz!=0){
			jiag = jiag / jingz;
		}
		DecimalFormat df_0 = new DecimalFormat("0");
		DecimalFormat df_1 = new DecimalFormat("0.0");
		DecimalFormat df_2 = new DecimalFormat("0.00");
		DecimalFormat df_3 = new DecimalFormat("0.000");
		arrData[lst.size()][0] = "合计";
		arrData[lst.size()][1] = "合计";
		arrData[lst.size()][2] = "合计";
		arrData[lst.size()][3] = "合计";
		arrData[lst.size()][4] = String.valueOf(jij);
		arrData[lst.size()][5] = "";
		arrData[lst.size()][6] = df_2.format(stad / jingz);
		arrData[lst.size()][7] = df_1.format(mt / jingz);
		arrData[lst.size()][8] = df_2.format(mad / jingz);
		arrData[lst.size()][9] = df_2.format(vdaf / jingz);
		arrData[lst.size()][10] = df_2.format(aad / jingz);
		arrData[lst.size()][11] = df_0.format(qnet_ar_mj / jingz);
		arrData[lst.size()][12] = String.valueOf(chesSum);
		arrData[lst.size()][13] = df_0.format(piaoz);
		arrData[lst.size()][14] = df_0.format(jingz);
		arrData[lst.size()][15] = df_2.format(jiag);
		String hej = maps1.get("JIESJE").toString();
//				df_2.format(Double.parseDouble(df_2.format(jiag)) * Integer.parseInt(df_0.format(jingz))- Double.parseDouble(maps1.get("BUKK")==null?"0":maps1.get("BUKK").toString()) );
		arrData[lst.size()][16] = hej;

		Report rt = new Report();
		String ArrHeader[][] = new String[4][17];

        // 结算单信息
        Map<String, Object> maps = jdbcTemplate.queryForMap(
                "SELECT js.diancmc DIANCMC,\n" +
                        "       JS.CAOZRQ,\n" +
                        "       JS.JIESBH,\n" +
                        "       JS.Gongysmc   GONGYSMC,\n" +
                        "       JS.Jihkjb_Id   JIHKJMC,\n" +
                        "       JS.PINZMC   PINZMC,\n" +
                        "       JS.CHES,\n" +
                        "       JS.PIAOZ,\n" +
                        "       JS.YINGD,\n" +
                        "       JS.KUID,\n" +
                        "       JS.JINGZ,\n" +
                        "       JS.REZZK,\n" +
                        "       JS.LIUFZK\n" +
                        "  FROM RL_JS_YUEJSDB JS\n" +
                        " WHERE JS.JIESBH ='"+ map.get("jiesbh") + "'");
		ArrHeader[0] = new String[] { "车数", "结算煤量", "单价", "煤款", "什费", "什费", "运费", "运费", "运费", "其它运杂费", "其它运杂费",
				"扣超\\亏吨运费", "扣超\\亏吨运费", "拒付金额", "拒付金额", "应付总金额", "应付总金额" };
		ArrHeader[1] = new String[] { maps.get("CHES").toString(), maps1.get("JINGZ").toString(),
				maps1.get("JIESJG").toString(), maps1.get("JIESJE").toString(), "", "", "", "", "", "", "", "", "", maps1.get("BUKK").toString(),
				maps1.get("BUKK").toString(), hej, hej };
		ArrHeader[2] = new String[] { "煤矿单位", "煤矿单位", "来煤日期", "发站", "基价", "扣水", "煤质", "煤质", "煤质", "煤质", "煤质", "煤质",
				"车数", "货票数", "验收数", "单价", "金额" };
		ArrHeader[3] = new String[] { "煤矿单位", "煤矿单位", "来煤日期", "发站", "基价", "扣水", "St,ar", "Mt", "Mad", "Vdaf", "Aad",
				"Qnet,ar", "车数", "货票数", "验收数", "单价", "金额" };
		int ArrWidth[] = new int[] { 60, 60, 90, 35, 40, 40, 40, 40, 40, 40, 40, 50, 30, 60, 60, 40, 80 };
		Table tb = new Table(arrData, 4, 0, 0);
		rt.setBody(tb);
		rt.setTitle("<br/><br/>国电电力发展股份有限公司燃料结算单批次明细单", ArrWidth);

		rt.setDefaultTitle(1, 4, "合同单位：" + maps.get("GONGYSMC"), Table.ALIGN_LEFT);
		rt.setDefaultTitle(5, 4, "结算日期：" + maps.get("CAOZRQ"), Table.ALIGN_LEFT);
		rt.setDefaultTitle(9, 5, "合同：" + maps2.get("HETBH"), Table.ALIGN_LEFT);
		rt.setDefaultTitle(14, 4, "编号：" + maps.get("JIESBH"), Table.ALIGN_LEFT);
		rt.body.setWidth(ArrWidth);
		// rt.body.setPageRows(18);
		rt.body.setHeaderData(ArrHeader);// 表头数据
		rt.body.setRowHeight(10);
		rt.body.AddTableRow(1);// 表格最下面添加一行
		// 合并单元格
		rt.body.mergeCell(1, 5, 1, 6);
		rt.body.mergeCell(2, 5, 2, 6);
		rt.body.mergeCell(1, 7, 1, 9);
		rt.body.mergeCell(2, 7, 2, 9);
		rt.body.mergeCell(1, 10, 1, 11);
		rt.body.mergeCell(2, 10, 2, 11);
		rt.body.mergeCell(1, 12, 1, 13);
		rt.body.mergeCell(2, 12, 2, 13);
		rt.body.mergeCell(1, 14, 1, 15);
		rt.body.mergeCell(2, 14, 2, 15);
		rt.body.mergeCell(1, 16, 1, 17);
		rt.body.mergeCell(2, 16, 2, 17);
		rt.body.mergeCell(3, 1, 4, 2);
		// rt.body.mergeCell(3, 3, 4, 4);
		rt.body.mergeCell(3, 3, 4, 3);
		rt.body.mergeCell(3, 4, 4, 4);
		rt.body.mergeCell(3, 5, 4, 5);
		rt.body.mergeCell(3, 6, 4, 6);
		rt.body.mergeCell(3, 7, 3, 12);
		rt.body.mergeCell(3, 13, 4, 13);
		rt.body.mergeCell(3, 14, 4, 14);
		rt.body.mergeCell(3, 15, 4, 15);
		rt.body.mergeCell(3, 16, 4, 16);
		rt.body.mergeCell(3, 17, 4, 17);
		int row = rt.body.getRows();
		for (int i = 5; i < row - 1; i++) {
			rt.body.mergeCell(i, 1, i, 2);
			// rt.body.mergeCell(i, 3, i, 4);
		}
		rt.body.mergeCell(row - 1, 1, row - 1, 4);
		rt.body.mergeCell(row, 2, row, 17);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		rt.body.setColAlign(4, Table.ALIGN_CENTER);
		rt.body.setColAlign(5, Table.ALIGN_CENTER);
		rt.body.setColAlign(6, Table.ALIGN_CENTER);
		rt.body.setColAlign(7, Table.ALIGN_CENTER);
		rt.body.setColAlign(8, Table.ALIGN_CENTER);
		rt.body.setColAlign(9, Table.ALIGN_CENTER);
		rt.body.setColAlign(10, Table.ALIGN_CENTER);
		rt.body.setColAlign(11, Table.ALIGN_CENTER);
		rt.body.setColAlign(12, Table.ALIGN_CENTER);
		rt.body.setColAlign(13, Table.ALIGN_CENTER);
		rt.body.setColAlign(14, Table.ALIGN_CENTER);
		rt.body.setColAlign(15, Table.ALIGN_CENTER);
		rt.body.setColAlign(16, Table.ALIGN_CENTER);
		rt.body.setColAlign(17, Table.ALIGN_CENTER);
		rt.body.setCellValue(row, 1, "备注");
		/* 日结算单查询SQL */
		/*
		 * List<Map<String,Object>> fahrq = jdbcTemplate.queryForList(
		 * "SELECT DISTINCT SUBSTR(QINGCSJ,0,10) QINGCSJ FROM RL_YS_CHEPB_QC WHERE CHEPB_ID IN("
		 * + "SELECT CHEPB_ID FROM GX_JIESDB_CHEPB WHERE JIESDB_ID = " +
		 * "(SELECT ID FROM RL_JS_RIJSDB WHERE JIESBH = '"
		 * +map.get("jiesbh")+"')" +") ORDER BY QINGCSJ ASC");
		 */

		List<Map<String, Object>> fahrq = jdbcTemplate
				.queryForList(
							"SELECT DISTINCT SUBSTR(ZHONGCSJ, 0, 10) ZHONGCSJ\n" +
							"  FROM RL_YS_CHEPB_QC\n" + 
							" WHERE CHEPB_ID IN\n" + 
							"       (SELECT ID\n" + 
							"          FROM RL_YS_CHEPB\n" + 
							"         WHERE  rl_js_yuejsdb_id IN\n" + 
							"\n" + 
							"                       (SELECT ID\n" + 
							"                           FROM RL_JS_YUEJSDB\n" + 
							"                          WHERE JIESBH = '"+map.get("jiesbh")+"'))\n" + 
							" ORDER BY ZHONGCSJ ASC");
		String fahrqStr = "";
		if (fahrq.size() > 1) {
			String[] ss = fahrq.get(0).get("ZHONGCSJ").toString().split("-");
			String[] ss2 = fahrq.get(fahrq.size() - 1).get("ZHONGCSJ").toString().split("-");
			fahrqStr = ss[0] + "年" + ss[1] + "月" + ss[2] + "日-" + ss2[0] + "年" + ss2[1] + "月" + ss2[2] + "日发货";
		} else {
			String[] ss = fahrq.get(0).get("QINGCSJ").toString().split("-");
			fahrqStr = ss[0] + "年" + ss[1] + "月" + ss[2] + "日发货";
		}
		rt.body.setCellValue(row, 2, fahrqStr);
		rt.createFooter(1, ArrWidth);
		rt.setDefautlFooter(1, 4, "供应商复核：", Table.ALIGN_LEFT);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml());
		resultMap.put("pageCount", 1);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	private String getPic(String name) {
		return "<image src='/gddlds/images/dsqm/" + name + ".gif" + "' width=\"75\" height=\"25\" align=\"left\"/>";
	}
}
