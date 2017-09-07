package com.zhiren.fuelmis.dc.service.impl.rib;

import com.zhiren.fuelmis.dc.dao.rib.RucbddbbbDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.rib.IRucbddbbbService;
import com.zhiren.fuelmis.dc.utils.DrawTableUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @author 贾少伟
 */
@Service
public class RucbddbbbServiceImpI implements IRucbddbbbService {

	@Autowired
	private RucbddbbbDao RanmzhrbbDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

	public String getXitxxItem(Map<String, Object> map) {
		String zhi1 = RanmzhrbbDao.getXitxxItem(map);
		return zhi1;
	}


	public String findzhi(String diancid) {
		String zhi2 = RanmzhrbbDao.findzhi(diancid);
		return zhi2;
	}



	public String getShouhc_zhoub(String diancid,String kaisrq,String jiesrq) {
		String riq_month= jiesrq.substring(0,7);
		Map<String,Object> shouhcmap = new HashMap<String,Object>();
		shouhcmap.put("beginriq", kaisrq);
		shouhcmap.put("endriq", jiesrq);
		shouhcmap.put("riq_month", riq_month);
		shouhcmap.put("diancid", diancid);
		List<Map<String, Object>> list = RanmzhrbbDao.getShouhc_zhoub(shouhcmap);
		String[] column = {"QUANC","DANGRGM_BZ","DANGRGM_SZ","HAOYQKDR_BZ","HAOYQKDR_SZ","KUC_BZ","KUC_SZ",
				"MEIJ_BZ","MEIJ_SZ","YUNJ_BZ","YUNJ_SZ","REZ_J_BZ","REZ_BZ","REZ_SZ",
				"BIAOD_BZ","BIAOD_SZ","BIAOD_YUE"};
		String[][] arrData = DrawTableUtil.Creatarr(list,column);
		 String ArrHeader[][]=new String[3][17];
		 ArrHeader[0]=new String[] {"单位","周入厂原煤量（吨）","周入厂原煤量（吨）","周耗用原煤量（吨）","周耗用原煤量（吨）","库存","库存","天然煤单价   （含税，元/吨）","天然煤单价   （含税，元/吨）","运费        （元/吨）","运费        （元/吨）","到厂煤热值          (千卡/千克)    ","到厂煤热值          (千卡/千克)    ","到厂煤热值          (千卡/千克)    ","入厂标煤单价              （不含税，元/吨）","入厂标煤单价              （不含税，元/吨）","入厂标煤单价              （不含税，元/吨）"};
		 ArrHeader[1]=new String[] {"单位","周入厂原煤量（吨）","周入厂原煤量（吨）","周耗用原煤量（吨）","周耗用原煤量（吨）","数量(吨)","数量(吨)","天然煤单价   （含税，元/吨）","天然煤单价   （含税，元/吨）","运费        （元/吨）","运费        （元/吨）","到厂煤热值          (千卡/千克)    ","到厂煤热值          (千卡/千克)    ","到厂煤热值          (千卡/千克)    ","入厂标煤单价              （不含税，元/吨）","入厂标煤单价              （不含税，元/吨）","入厂标煤单价              （不含税，元/吨）"};
		 ArrHeader[2]=new String[] {"单位","本周","环比","本周","环比","本周","环比","本周","环比","本周","环比","兆焦/千克","本周","环比","本周","环比","月累计"};
		 int ArrWidth[]=new int[] {75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75};
		 Report rt = new Report();
			rt.setBody(new Table(arrData, 3, 0, 0, 17));
			rt.body.setHeaderData(ArrHeader);
			rt.body.setWidth(ArrWidth);
			rt.body.mergeFixedRow();
			return rt.getAllPagesHtml();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getShouhc(Map<String,Object> map) {
        Map<String, Object> m = jdbcTemplate.queryForMap("select max(mingc) mingc from diancxxb WHERE id="+map.get("diancid"));
        String dianc_mingc =m.get("mingc").toString();
		//获取电厂名称
//		String  dianc_mingc = niandjhtjDao.getDiancMingc(map.get("diancid").toString());

		List<Map<String, Object>> list = RanmzhrbbDao.getShouhc(map);
		//column里的字符与Ribcx.xml里的查询字段对应，注意一定要大写，否则取不出数据。
		String[] column = {"DIANCMC","DRDH","LJDH","DRHY","LJHY","DRKC","DRKDKC","DRCHANGWML","LJCHANGWML","FADL","GONGRL"};
		String[][] arrData = DrawTableUtil.Creatarr(list,column);
		
		Report rt = new Report();
		String ArrHeader[][]=new String[2][17];
		ArrHeader[0]=new String[] {"项目","车数","车数","矿发数量","矿发数量","矿发数量","矿发数量","过衡数量","过衡数量","合理运损","合理运损","亏吨","亏吨","实际损耗","实际损耗","亏损率","亏损率"};
		ArrHeader[1]=new String[] {"矿名","合计","累计","合计 ","累计","计划","完成率","合计","累计","合计","累计","合计","累计","合计","累计","合计","累计"};
		int ArrWidth[]=new int[] {100,90,90,90,90,90,90,90,90,80,80,90,90,90,90,80,80};
		//rs.beforefirst();
		rt.setBody(new Table(arrData, 1, 0, 0, 17));
		rt.body.setHeaderData(ArrHeader);
		rt.body.setWidth(ArrWidth);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
//		rt.setTitle(getDiancmc(String.valueOf(visit.getDiancxxb_id()))+"燃料来耗存统计日报",ArrWidth);
		rt.setTitle(dianc_mingc+"发电有限公司燃煤综合日报表",ArrWidth);
//		rt.setDefaultTitle(1, 3, "填报单位:"+getDiancmc(), Table.ALIGN_LEFT);
		rt.setDefaultTitle(1, 3, "填报单位:国电新疆红雁池发电有限公司", Table.ALIGN_LEFT);
//		rt.setDefaultTitle(4, 4, riq+"至"+riq1, Table.ALIGN_CENTER);
		//rt.setDefaultTitle(4, 4, kaisrq+"至"+jiesrq, Table.ALIGN_CENTER);
		rt.setDefaultTitle(13, 4, "单位:吨,千瓦时,%", Table.ALIGN_RIGHT);
		rt.body.mergeFixedRow();
		rt.body.mergeFixedCols();
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_RIGHT);
		rt.body.setColAlign(3, Table.ALIGN_RIGHT);
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
		return rt.getAllPagesHtml();
	}
	@SuppressWarnings("unchecked")

	public String getShouhc_zhoubDetail(String diancid,String kaisrq,String jiesrq,String baobleix){
		String riq_month= jiesrq.substring(0,10);
		Map<String,Object> shouhcmap = new HashMap<String,Object>();
		shouhcmap.put("beginriq", kaisrq);
		shouhcmap.put("endriq", jiesrq);
		shouhcmap.put("riq_month", riq_month);
		shouhcmap.put("diancid", diancid);
		List<Map<String, Object>> list = RanmzhrbbDao.getShouhc_zhoubdetail(shouhcmap);
		//column里的字符与Ribcx.xml里的查询字段对应，注意一定要大写，否则取不出数据。
		String[] column = {"RIQ","DANGRGM","MEIJ","YUNJ","BIAOD","REZ","HAOYQKDR"};
		String[][] arrData = DrawTableUtil.Creatarr(list,column);
		String ArrHeader[][]=new String[1][7];
		ArrHeader[0]=new String[] {"日期","入厂煤量    （吨）","煤价（元/吨）","运费（元/吨）","原煤价格（元/吨）","入厂热值（MJ/kg）","耗量   （吨）"};
		int ArrWidth[]=new int[] {105,105,105,105,105,105,105};
		Report rt = new Report();
		rt.setBody(new Table(arrData, 1, 0, 0, 7));
		rt.body.setHeaderData(ArrHeader);
		rt.body.setWidth(ArrWidth);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
//		rt.setTitle(getDiancmc(String.valueOf(visit.getDiancxxb_id()))+"燃料来耗存统计日报",ArrWidth);
//		rt.setTitle(dianc_mingc+"燃料来耗存统计日报",ArrWidth);
//		rt.setDefaultTitle(1, 3, "填报单位:"+getDiancmc(), Table.ALIGN_LEFT);
//		rt.setDefaultTitle(1, 3, "填报单位:"+dianc_mingc, Table.ALIGN_LEFT);
//		rt.setDefaultTitle(4, 4, riq+"至"+riq1, Table.ALIGN_CENTER);
//		rt.setDefaultTitle(4, 4, kaisrq+"至"+jiesrq, Table.ALIGN_CENTER);
//		rt.setDefaultTitle(8, 4, "单位:吨、元/吨、MJ/Kg、万千瓦时、吉焦", Table.ALIGN_RIGHT);
//		rt.body.mergeFixedRow();
//		rt.body.mergeFixedCols();
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		rt.body.setColAlign(4, Table.ALIGN_CENTER);
		rt.body.setColAlign(5, Table.ALIGN_CENTER);
		rt.body.setColAlign(6, Table.ALIGN_CENTER);
		rt.body.setColAlign(7, Table.ALIGN_CENTER);
		rt.createFooter(1, ArrWidth);
		rt.setDefautlFooter(1, 6, "备注：1.本周到货**万吨，其中***到货***万吨。    2.分析本周入厂标煤单价环比下降的原因：入厂煤环比下降原因 ：", Table.ALIGN_CENTER);
		return rt.getAllPagesHtml();
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getShouhcDetail(Map<String,Object> map){
		//String sr="LJ";

        //Map<String, Object> m = jdbcTemplate.queryForMap("select max(mingc) mingc from diancxxb WHERE id="+map.get("diancid"));
		String sql="select a.riq,\n" +
				"round(a.benywcz,2) benywcz,\n" +
				"round(a.duibdwbywcz,2) duibdwbywcz,\n" +
				"round(a.jitxfdbmbz,0) jitxfdbmbz,\n" +
				"round(a.benywcz-a.duibdwbywcz,2) duibwcz,\n" +
				"round(a.duibwcz-a.jitxfdbmbz,2) wanczymbzzc\n" +
				"from yuebddbwcsjb a  where riq between '"+map.get("sDate")+"'and '"+map.get("eDate")+"'";

		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		
		String[] column = {"RIQ","BENYWCZ","DUIBDWBYWCZ","JITXFDBMBZ","DUIBWCZ","WANCZYMBZZC"};
		String[][] arrData = DrawTableUtil.Creatarr(list,column);
		Report rt = new Report();
		String ArrHeader[][]=new String[1][column.length];

        ArrHeader[0]=new String[] {"年月","本月完成值","对标单位本月完成值","集团下发对标目标值","对标完成值","完成值与目标值之差"};

		int ArrWidth[]=new int[] {100,100,100,100,100,100};

		Table titleTable = new Table(4, 10);
		titleTable.setBorderNone();
		for (int i = 1; i <= titleTable.getRows(); i++) {
			for (int j = 1; j <= titleTable.getCols(); j++) {
				titleTable.setCellBorderNone(i, j);
			}
		}
		rt.setBody(new Table(arrData, 1, 0, 0 , 6));
		rt.body.setHeaderData(ArrHeader);

		rt.body.setWidth(ArrWidth);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);

		//rt.setTitle("国电电力发展股份有限公司生产用燃煤供应、耗用与结存月报", 2);
		rt.setTitle(titleTable);
		rt.setTitle("国电电力发展股份有限公司入厂标单对标完成趋势图", 2);
		

		rt.body.mergeFixedCols();
		
		rt.body.ShowZero=true;
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		rt.body.setColAlign(4, Table.ALIGN_CENTER);
		rt.body.setColAlign(5, Table.ALIGN_CENTER);
		rt.body.setColAlign(6, Table.ALIGN_CENTER);

		rt.body.setPageRows(15);
		int pageCount = rt.getPages();
		rt.createFooter(1, ArrWidth);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		//
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}
	private String getZHI(String diancid){
		String zhi = "";
		zhi = RanmzhrbbDao.findzhi(diancid);
		if(null==zhi || "".equals(zhi)){
			zhi = " -1 ";
		}
		return zhi;
	}
}