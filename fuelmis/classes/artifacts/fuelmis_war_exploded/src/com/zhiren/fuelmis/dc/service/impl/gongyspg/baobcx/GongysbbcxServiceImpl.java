package com.zhiren.fuelmis.dc.service.impl.gongyspg.baobcx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;



import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.gongyspg.baobcx.IGongysbbcxService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.formular.Result;




@Service
public class GongysbbcxServiceImpl implements IGongysbbcxService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public JSONArray getGongysypfbb(Map<String, Object> map) {
		String riqBegin = map.get("riqBegin").toString();
		String riqEnd = map.get("riqEnd").toString();
		
		
		StringBuffer sqlView_nc=new StringBuffer();
		StringBuffer sqlView_wc=new StringBuffer();		
		List<Map<String,Object>> rs = jdbcTemplate.queryForList("select id,zhibmc,zhibdm,zhibdw,beiz,leib,BAOLXSW from VWZHIBDYB  order by id");// 得到指标定义表中启用状态的指标。
		
		
		for (Map<String, Object> m : rs) {
			String zhibmc = m.get("ZHIBDM").toString();
			int xiaosw = Integer.parseInt(m.get("BAOLXSW").toString());
			if(m.get("ZHIBDM").equals("SL")){
				
			}else{
				sqlView_nc.append(" 			  sum(round_new(decode((mx.zhibdm), '"+zhibmc+"', (CF), '0'),"+xiaosw+")) as "+zhibmc+"_CF,\n");
				sqlView_nc.append("               sum(round_new(decode((mx.zhibdm), '"+zhibmc+"', (KF), '0'),"+xiaosw+")) as "+zhibmc+"_KF,\n");
				sqlView_nc.append("               sum(decode((mx.zhibdm), '"+zhibmc+"', (jif), '0')) as "+zhibmc+"_JIF,\n");				
				sqlView_wc.append("  "+zhibmc+"_CF,"+zhibmc+"_KF,("+zhibmc+"_CF-"+zhibmc+"_KF) as chay_"+zhibmc+","+zhibmc+"_JIF,  ");
			}
		}
		
		// 处理 单位条件判断
		StringBuffer dianc = new StringBuffer("");
		dianc.append(" and r.diancxxb_id in (" + map.get("diancxxb_id") + ") \n");

		// 单位条件判断
		StringBuffer danwCondition = new StringBuffer("");
		if (Long.parseLong(map.get("gongys_id").toString())!=-1) {
			danwCondition.append(" and g.id=" + map.get("gongys_id"));
		}

		String sql = "select g.mingc,h.kaisrq||'至'||h.jiesrq hetyxq ,y.kf_sl,\n" +
					"       round(y.cf_sl,2) as cf_sl,decode(kf_sl,0,0,round(y.cf_sl / y.kf_sl * 100, 2)) as duixl,y.cf_sl-y.kf_sl as chay_sl,y.jif,\n" + 
					"       " +   sqlView_wc.toString() +
					"       y.zongf\n" + 
					"  from rl_gys_yuegmjfb y,gongysb g,\n" + 
					"       (select  \n" + sqlView_nc.toString() +
					"				mx.yuegmjfb_id \n"+
					"          from rl_gys_yuegmjfmxb mx\n" + 
					"         group by mx.yuegmjfb_id) mx,\n"
					+ "rl_gys_hetb h \n" + 
					" where y.id = mx.yuegmjfb_id(+)\n"
					+ " and h.id=y.hetb_id \n" + 
					"   and y.riq between '"+riqBegin+"' and '"+riqEnd+"' \n" +
					"   and y.gongysb_id = g.id\n" +danwCondition+ 
					
					"--   and y.\n" + 
					"   order by y.zongf desc";
		
		String[][] rs1=null;
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		if(list.size()!=0){
			rs1=Result.list2array(list);
		}else{
			rs1=new String[][]{{}};
		}
		
		Report rt = new Report();
		String[] temArray = getZhibs("zhibmc").split(",");
		String ArrHeader[][] = new String[2][temArray.length * 4 + 8];
		int ArrWidth[] = new int[temArray.length * 4 + 8];

		ArrHeader[0][0] = "供应商";
		ArrHeader[0][1] = "合同有效期";
		ArrHeader[0][2] = "预报数量";
		ArrHeader[0][3] = "预报数量";
		ArrHeader[0][4] = "预报数量";
		ArrHeader[0][5] = "预报数量";
		ArrHeader[0][6] = "预报数量";
		
		ArrHeader[1][0] = "供应商";
		ArrHeader[1][1] = "合同有效期";
		ArrHeader[1][2] = "合同量";
		ArrHeader[1][3] = "到货数量";
		ArrHeader[1][4] = "合同兑现率";
		ArrHeader[1][5] = "差值";
		ArrHeader[1][6] = "得分";

		
		
		for (int i = 0; i < temArray.length; i++) {
			for (int j = 0; j < 4; j++) {
				ArrHeader[0][7 + i * 4 + j] = temArray[i];
			}
			ArrHeader[1][7 + i * 4 + 0] = "合同约定";
			ArrHeader[1][7 + i * 4 + 1] = "验收值";
			ArrHeader[1][7 + i * 4 + 2] = "差值";
			ArrHeader[1][7 + i * 4 + 3] = "得分";
		}

		ArrWidth[0] = 180;
		ArrWidth[1] = 180;
		ArrWidth[2] = 70;
		ArrWidth[3] = 70;
		ArrWidth[4] = 70;
		ArrWidth[5] = 70;
		ArrWidth[6] = 70;
		ArrWidth[7] = 70;
		String[] strFormat = new String[ArrHeader[0].length];
		strFormat[0] = "";
		strFormat[1] = "0";
		strFormat[2] = "0";
		strFormat[3] = "0";
		strFormat[4] = "0";
		strFormat[5] = "0";
		strFormat[6] = "0";
		
		for (int i = 7; i < ArrHeader[0].length ; i++) {
			ArrWidth[i] = 50;
			strFormat[i] = "";
		}

		int a = ArrHeader[0].length-1 ; 
 
		ArrHeader[0][a] = "当月总分"; 
		ArrHeader[1][a] = "当月总分"; 
		ArrWidth[a] = 70;
		strFormat[a] = ""; 

		rt.setTitle("" + "国电红雁池电厂" + "月评分统计台账", ArrWidth);
		rt.setDefaultTitle(1, 5, "制表单位：国电新疆红雁池发电有限公司", Table.ALIGN_LEFT);
		// 设置日期靠最右边显示
		rt.setDefaultTitle((rt.title.getCols() - 8) / 2 + 6, 7, riqBegin
				+ "至"
				+ riqEnd,
				Table.ALIGN_RIGHT);

		rt.setBody(new Table(rs1,2,0, 2));
		rt.body.setWidth(ArrWidth);
		rt.body.setHeaderData(ArrHeader);
		rt.body.setPageRows(15);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.mergeFixedRow();
		if (rt.body.getRows() > 2) {
			rt.body.merge(2, 1, rt.body.getRows(), 2);
		}
		
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 4, "打印日期："
				+ DateUtil.getCurrentTime(),
				Table.ALIGN_LEFT);
		rt.setDefautlFooter(4, 2, "审核:", Table.ALIGN_LEFT);

		rt.footer.setRowCells(2, Table.PER_FONTSIZE, 10);

		rt.setDefautlFooter(rt.footer.getCols() - 1, 2, Table.PAGENUMBER_CHINA,
				Table.ALIGN_RIGHT);
		// 设置页数
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int pageCount = rt.getPages();
		resultMap.put("pageCount", pageCount);

		resultMap.put("html", rt.getAllPagesHtml());
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}
	private String getZhibs(String leix){

		StringBuffer sqlView_zhibm = new StringBuffer();
		List<Map<String,Object>> list= jdbcTemplate.queryForList("select max(id) as id,max(zhibmc)  zhibmc, zhibdm,max(zhibdw)  zhibdw,max(beiz) as beiz,max(leib) as leib from VWZHIBDYB  group by zhibdm order by id");// 得到指标定义表中启用状态的指标。
		int i = 0;	
		for (Map<String, Object> map : list) {
			if(map.get("ZHIBDM").toString().equals("SL")){
				
			}else{
				sqlView_zhibm.append(map.get("ZHIBMC").toString() + "("+ map.get("ZHIBDW").toString() + "),"); 
			}
			i++;
		}
		if (i>=1) {
			sqlView_zhibm.deleteCharAt(sqlView_zhibm.lastIndexOf(","));
			return sqlView_zhibm.toString();
		}else{
			return "";
		}	
	}
}
