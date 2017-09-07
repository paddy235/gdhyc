package com.zhiren.fuelmis.dc.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author 陈宝露
 */
@Component("haoyckJob")
public class HaoyckJob {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	public void execute() {
		//String [] haoymc=new String[]{"",""};
		//生成出库单编号ckd20160101333
		String chukdbh="ckd"+sdf.format(new Date());
		int xuh =0;
		List<Map<String,Object>> xuhList = jdbcTemplate.queryForList(
				"select nvl(max(substr(chukdbh,12,14)),0)+1 xuh from rl_kc_chukdb where substr(chukdbh,0,11)='"+chukdbh+"'");
		if(xuhList.size()!=0){
			xuh=Integer.parseInt(xuhList.get(0).get("XUH").toString());
		}
		if (xuh < 10) {
			chukdbh += "00" + xuh;
		} else if(xuh<100){
			chukdbh +="0"+ xuh;
		}else{
			chukdbh +=xuh;
		}
		String sql="insert into rl_kc_chukdb (\n" +
				"id,\n" +
				"chukdbh,\n" +
				"zuz,\n" +
				"huoz,\n" +
				"kuczz,\n" +
				"kucwz,\n" +
				"kucwl,\n" +
				"jine,\n" +
				"yewlx,\n" +
				"churklx,\n" +
				"yewrq,\n" +
				"kuaijrq,\n" +
				"jinzrq,\n" +
				"chuksl,\n" +
				"zhuangt,\n" +
				"aar,\n" +
				"ad,\n" +
				"vdaf,\n" +
				"mt,\n" +
				"stad,\n" +
				"aad,\n" +
				"mad,\n" +
				"qbad,\n" +
				"had,\n" +
				"vad,\n" +
				"fcad,\n" +
				"std,\n" +
				"qgrad,\n" +
				"hdaf,\n" +
				"qgrad_daf,\n" +
				"sdaf,\n" +
				"var,\n" +
				"t1,\n" +
				"t2,\n" +
				"t3,\n" +
				"t4,\n" +
				"har,\n" +
				"qgrd,\n" +
				"star,\n" +
				"qnet_ar\n" +
				")\n" +
				"select\n" +
				"seq_chukdb.nextvar id,\n" +
				"'"+chukdbh+"' chukdbh,\n" +
				"515 zuz,\n" +
				"515 huoz,\n" +
				"'' kuczz,\n" +
				"'' kucwz,\n" +
				"f.KUCWL kucwl,\n" +
				"'' jine,\n" +
				"7 yewlx,\n" +
				"7 churklx,\n" +
				"h.riq yewrq,\n" +
				"(select max(k.KUAIJRQ)  from RL_KC_KUAIJRQB k where k.ZHUANGT='启用')  kuaijrq,\n" +
				"'' jiezrq,\n" +
				"h.gongry*f.BAIFB  chuksl,\n" +
				"0 zhuangt,\n" +
				"0 aar,\n" +
				"0 ad,\n" +
				"0 vdaf,\n" +
				"0 mt,\n" +
				"0 stad,\n" +
				"0 aad,\n" +
				"0 mad,\n" +
				"0 qbad,\n" +
				"0 had,\n" +
				"0 vad,\n" +
				"0 fcad,\n" +
				"0 std,\n" +
				"0 qgrad,\n" +
				"0 hdaf,\n" +
				"0 qgrad_daf,\n" +
				"0 sdaf,\n" +
				"0 var,\n" +
				"0 t1,\n" +
				"0 t2,\n" +
				"0 t3,\n" +
				"0 t4,\n" +
				"0 har,\n" +
				"0 qgrd,\n" +
				"0 star,\n" +
				"0 qnet_ar\n" +
				"from rl_kc_rihysjb h,GX_KC_KUCBLFTSZB f";
		jdbcTemplate.update(sql);

		//生成出库单编号ckd20160101333
		chukdbh="ckd"+sdf.format(new Date());
		xuh =0;
		xuhList = jdbcTemplate.queryForList(
				"select nvl(max(substr(chukdbh,12,14)),0)+1 xuh from rl_kc_chukdb where substr(chukdbh,0,11)='"+chukdbh+"'");
		if(xuhList.size()!=0){
			xuh=Integer.parseInt(xuhList.get(0).get("XUH").toString());
		}
		if (xuh < 10) {
			chukdbh += "00" + xuh;
		} else if(xuh<100){
			chukdbh +="0"+ xuh;
		}else{
			chukdbh +=xuh;
		}
		sql="insert into rl_kc_chukdb (\n" +
				" id,\n" +
				"chukdbh,\n" +
				"zuz,\n" +
				"huoz,\n" +
				"kuczz,\n" +
				"kucwz,\n" +
				"kucwl,\n" +
				"jine,\n" +
				"yewlx,\n" +
				"churklx,\n" +
				"yewrq,\n" +
				"kuaijrq,\n" +
				"jinzrq,\n" +
				"chuksl,\n" +
				"zhuangt,\n" +
				"aar,\n" +
				"ad,\n" +
				"vdaf,\n" +
				"mt,\n" +
				"stad,\n" +
				"aad,\n" +
				"mad,\n" +
				"qbad,\n" +
				"had,\n" +
				"vad,\n" +
				"fcad,\n" +
				"std,\n" +
				"qgrad,\n" +
				"hdaf,\n" +
				"qgrad_daf,\n" +
				"sdaf,\n" +
				"var,\n" +
				"t1,\n" +
				"t2,\n" +
				"t3,\n" +
				"t4,\n" +
				"har,\n" +
				"qgrd,\n" +
				"star,\n" +
				"qnet_ar\n" +
				")\n" +
				"select\n" +
				"seq_chukdb.nextvar id,\n" +
				"'"+chukdbh+"' chukdbh,\n" +
				"515 zuz,\n" +
				"515 huoz,\n" +
				"'' kuczz,\n" +
				"'' kucwz,\n" +
				"f.KUCWL kucwl,\n" +
				"'' jine,\n" +
				"6 yewlx,\n" +
				"6 churklx,\n" +
				"h.riq yewrq,\n" +
				"(select max(k.KUAIJRQ)  from RL_KC_KUAIJRQB k where k.ZHUANGT='启用')  kuaijrq,\n" +
				"'' jiezrq,\n" +
				"h.fady*f.BAIFB  chuksl,\n" +
				"0 zhuangt,\n" +
				"0 aar,\n" +
				"0 ad,\n" +
				"0 vdaf,\n" +
				"0 mt,\n" +
				"0 stad,\n" +
				"0 aad,\n" +
				"0 mad,\n" +
				"0 qbad,\n" +
				"0 had,\n" +
				"0 vad,\n" +
				"0 fcad,\n" +
				"0 std,\n" +
				"0 qgrad,\n" +
				"0 hdaf,\n" +
				"0 qgrad_daf,\n" +
				"0 sdaf,\n" +
				"0 var,\n" +
				"0 t1,\n" +
				"0 t2,\n" +
				"0 t3,\n" +
				"0 t4,\n" +
				"0 har,\n" +
				"0 qgrd,\n" +
				"0 star,\n" +
				"0 qnet_ar\n" +
				"from rl_kc_rihysjb h,GX_KC_KUCBLFTSZB f";
		jdbcTemplate.update(sql);
	}
}
