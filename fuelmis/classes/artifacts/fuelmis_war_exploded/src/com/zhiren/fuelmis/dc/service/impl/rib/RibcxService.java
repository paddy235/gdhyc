package com.zhiren.fuelmis.dc.service.impl.rib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.jih.NiandjhtjDao;
import com.zhiren.fuelmis.dc.dao.rib.RibcxDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.rib.IRibcxService;
import com.zhiren.fuelmis.dc.utils.DrawTableUtil;

/** 
 * @author 摧枯拉朽cococa
 */
@Service
public class RibcxService implements IRibcxService {

	@Autowired
	private RibcxDao ribcxDao;
	@Autowired
	private NiandjhtjDao niandjhtjDao;

	@Override
	public String getXitxxItem(Map<String, Object> map) {
		String zhi1 = ribcxDao.getXitxxItem(map);
		return zhi1;
	}

	@Override
	public String findzhi(String diancid) {
		String zhi2 = ribcxDao.findzhi(diancid);
		return zhi2;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String getRipjkc(String diancid,String kaisrq,String jiesrq) {
		String xitxxItem = this.getItem();
		String zhi2 = this.getZHI(diancid);
		Map<String,Object> shouhcmap = new HashMap<String,Object>();
		shouhcmap.put("beginriq", kaisrq);
		shouhcmap.put("endriq", jiesrq);
		shouhcmap.put("zhi1", xitxxItem);
		shouhcmap.put("zhi2", zhi2);
		shouhcmap.put("diancid", diancid);
		//获取电厂名称
		String  dianc_mingc = niandjhtjDao.getDiancMingc(diancid);
		if(null == dianc_mingc){
			dianc_mingc = "";
		}
		List<Map<String, Object>> list = ribcxDao.getRipjkc(shouhcmap);
		//column里的字符与Ribcx.xml里的查询字段对应，注意一定要大写，否则取不出数据。
		String[] column = {"MINGC","RIQ","DANGRGM","HAOYQKDR","KUC","KEDKC","CHANGWML","FADL","GONGRL"};
		String[][] arrData = DrawTableUtil.Creatarr(list,column);
		
		Report rt = new Report();
		String ArrHeader[][]=new String[2][8];
		ArrHeader[0]=new String[] {"单位","日期","实际到货","耗用情况","当日库存","当日库存","其中：厂外煤量","发电量","供热量"};
		ArrHeader[1]=new String[] {"单位","日期","实际到货","耗用情况","账面库存","可用库存","其中：厂外煤量","发电量","供热量"};
		int ArrWidth[]=new int[] {100,90,90,90,160,160,90,90,80};
		rt.setBody(new Table(arrData, 2, 0, 0, 9));
		rt.body.setHeaderData(ArrHeader);
		rt.body.setWidth(ArrWidth);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.setTitle(dianc_mingc+"燃料来耗存统计日报",ArrWidth);
		rt.setDefaultTitle(1, 3, "填报单位:"+dianc_mingc, Table.ALIGN_LEFT);
		rt.setDefaultTitle(4, 2, kaisrq+"至"+jiesrq, Table.ALIGN_CENTER);
		rt.setDefaultTitle(6,3, "单位:吨、元/吨、MJ/Kg、万千瓦时、吉焦", Table.ALIGN_RIGHT);
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
		return rt.getAllPagesHtml();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getShouhc_zhoub(String diancid,String kaisrq,String jiesrq) {
		String riq_month= jiesrq.substring(0,7);
		Map<String,Object> shouhcmap = new HashMap<String,Object>();
		shouhcmap.put("beginriq", kaisrq);
		shouhcmap.put("endriq", jiesrq);
		shouhcmap.put("riq_month", riq_month);
		shouhcmap.put("diancid", diancid);
		List<Map<String, Object>> list = ribcxDao.getShouhc_zhoub(shouhcmap);
		String[] column = {"QUANC","DANGRGM_BZ","DANGRGM_SZ","HAOYQKDR_BZ","HAOYQKDR_SZ","KUC_BZ","KUC_SZ",
				"MEIJ_BZ","MEIJ_SZ","YUNJ_BZ","YUNJ_SZ","REZ_J_BZ","REZ_BZ","REZ_SZ",
				"BIAOD_BZ","BIAOD_SZ","BIAOD_YUE"};
		String[][] arrData = DrawTableUtil.Creatarr(list,column);
		 String ArrHeader[][]=new String[3][17];
		 ArrHeader[0]=new String[] {"单位","周入厂原煤量（吨）","周入厂原煤量（吨）","周耗用原煤量（吨）","周耗用原煤量（吨）","库存","库存","天然煤单价   （含税，元/吨）","天然煤单价   （含税，元/吨）","运费        （元/吨）","运费        （元/吨）","到厂煤热值          (千卡/千克)    ","到厂煤热值          (千卡/千克)    ","到厂煤热值          (千卡/千克)    ","入厂标煤单价              （不含税，元/吨）","入厂标煤单价              （不含税，元/吨）","入厂标煤单价              （不含税，元/吨）"};
		 ArrHeader[1]=new String[] {"单位","周入厂原煤量（吨）","周入厂原煤量（吨）","周耗用原煤量（吨）","周耗用原煤量（吨）","数量(吨)","数量(吨)","天然煤单价   （含税，元/吨）","天然煤单价   （含税，元/吨）","运费        （元/吨）","运费        （元/吨）","到厂煤热值          (千卡/千克)    ","到厂煤热值          (千卡/千克)    ","到厂煤热值          (千卡/千克)    ","入厂标煤单价              （不含税，元/吨）","入厂标煤单价              （不含税，元/吨）","入厂标煤单价              （不含税，元/吨）"};
		 ArrHeader[2]=new String[] {"单位","本周","环比","本周","环比","本周","环比","本周","环比","本周","环比","兆焦/千克","本周","环比","本周","环比","月累计"};
		 int ArrWidth[]=new int[] {175,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75};
		 Report rt = new Report();
			rt.setBody(new Table(arrData, 3, 0, 0, 17));
			rt.body.setHeaderData(ArrHeader);
			rt.body.setWidth(ArrWidth);
			rt.body.mergeFixedRow();
			return rt.getAllPagesHtml();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getShouhc(String diancid,String kaisrq,String jiesrq) {
		String xitxxItem = this.getItem();
		String zhi2 = this.getZHI(diancid);
		Map<String,Object> shouhcmap = new HashMap<String,Object>();
		shouhcmap.put("beginriq", kaisrq);
		shouhcmap.put("endriq", jiesrq);
		shouhcmap.put("zhi1", xitxxItem);
		shouhcmap.put("zhi2", zhi2);
		shouhcmap.put("diancid", diancid);
		//获取电厂名称
		String  dianc_mingc = niandjhtjDao.getDiancMingc(diancid);
		if(null == dianc_mingc){
			dianc_mingc = "";
		}
		List<Map<String, Object>> list = ribcxDao.getShouhc(shouhcmap);
		//column里的字符与Ribcx.xml里的查询字段对应，注意一定要大写，否则取不出数据。
		String[] column = {"DIANCMC","DRDH","LJDH","DRHY","LJHY","DRKC","DRKDKC","DRCHANGWML","LJCHANGWML","FADL","GONGRL"};
		String[][] arrData = DrawTableUtil.Creatarr(list,column);
		
		Report rt = new Report();
		String ArrHeader[][]=new String[2][10];
		ArrHeader[0]=new String[] {"单位","实际到货","实际到货","耗用情况","耗用情况","当日库存","当日库存","其中：厂外煤量","其中：厂外煤量","发电量","供热量"};
		ArrHeader[1]=new String[] {"单位","当日","累计","当日","累计","账面库存","可用库存","当日进煤","累计进煤","发电量","供热量"};
		int ArrWidth[]=new int[] {100,90,90,90,90,90,90,90,90,80,80};
		//rs.beforefirst();
		rt.setBody(new Table(arrData, 2, 0, 0, 11));
		rt.body.setHeaderData(ArrHeader);
		rt.body.setWidth(ArrWidth);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
//		rt.setTitle(getDiancmc(String.valueOf(visit.getDiancxxb_id()))+"燃料来耗存统计日报",ArrWidth);
		rt.setTitle(dianc_mingc+"燃料来耗存统计日报",ArrWidth);
//		rt.setDefaultTitle(1, 3, "填报单位:"+getDiancmc(), Table.ALIGN_LEFT);
		rt.setDefaultTitle(1, 3, "填报单位:"+dianc_mingc, Table.ALIGN_LEFT);
//		rt.setDefaultTitle(4, 4, riq+"至"+riq1, Table.ALIGN_CENTER);
		rt.setDefaultTitle(4, 4, kaisrq+"至"+jiesrq, Table.ALIGN_CENTER);
		rt.setDefaultTitle(8, 4, "单位:吨、元/吨、MJ/Kg、万千瓦时、吉焦", Table.ALIGN_RIGHT);
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
		return rt.getAllPagesHtml();
	}
	@SuppressWarnings("unchecked")
	@Override
	public String getShouhc_zhoubDetail(String diancid,String kaisrq,String jiesrq,String baobleix){
		String riq_month= jiesrq.substring(0,10);
		Map<String,Object> shouhcmap = new HashMap<String,Object>();
		shouhcmap.put("beginriq", kaisrq);
		shouhcmap.put("endriq", jiesrq);
		shouhcmap.put("riq_month", riq_month);
		shouhcmap.put("diancid", diancid);
		List<Map<String, Object>> list = ribcxDao.getShouhc_zhoubdetail(shouhcmap);
		//column里的字符与Ribcx.xml里的查询字段对应，注意一定要大写，否则取不出数据。
		String[] column = {"RIQ","DANGRGM","MEIJ","YUNJ","BIAOD","REZ","HAOYQKDR"};
		String[][] arrData = DrawTableUtil.Creatarr(list,column);
		String ArrHeader[][]=new String[1][7];
		ArrHeader[0]=new String[] {"日期","入厂煤量    （吨）","煤价      （元/吨）","运费     （元/吨）","原煤价格（元/吨）","入厂热值（MJ/kg）","耗量   （吨）"};
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
	public String getShouhcDetail(String diancid,String kaisrq,String jiesrq,String baobleix){
		String sr="LJ";
		if(jiesrq.equals(jiesrq)){
//			beginRiq=DateUtil.Formatdate("yyyy-MM-dd", DateUtil.getFirstDayOfMonth(DateUtil.getDate(endRiq)));
			sr="DR";
		}
		
		String zhi = this.getZHI(diancid);
		String zhi2 = this.getItem();
		Map<String,Object> shouhcmap = new HashMap<String,Object>();
		shouhcmap.put("beginriq", kaisrq);
		shouhcmap.put("endriq", jiesrq);
		shouhcmap.put("zhi", zhi);
		shouhcmap.put("zhi2", zhi2);
		shouhcmap.put("sr", sr);
		shouhcmap.put("diancid", diancid);
		shouhcmap.put("xialid", baobleix);//报表类型下拉框id
		
		
		List<Map<String, Object>> list = ribcxDao.getShouhcDetail(shouhcmap);
		
		String[] column = {"DANW","DQ","GONGYS","MEIK","JIHKJ","PINZ","YUNSMC","MEIJ","YUNJ","REZ","HSBMDJ","BHSBMDJ","DR","LJ","DRCHES","LJCHES"};
		String[][] arrData = DrawTableUtil.Creatarr(list,column);
		Report rt = new Report();
		String ArrHeader[][]=new String[3][column.length];
//		if(getBBLXValue().getId()==1){
		
		if(baobleix.equals("1")){
			ArrHeader[0]=new String[] {"分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况"};
			ArrHeader[1]=new String[] {"单位","地区","供应商","矿别","计划口径","品种","运输<br>方式","煤价<br>(含税)","运价<br>(含税)","到厂热值","含税<br>标煤单价","不含税<br>标煤单价","来煤量","来煤量","来煤量","来煤量"};
			ArrHeader[2]=new String[] {"单位","地区","供应商","矿别","计划口径","品种","运输<br>方式","煤价<br>(含税)","运价<br>(含税)","到厂热值","含税<br>标煤单价","不含税<br>标煤单价","当日","累计","车数","累计车数"};
		}else if(baobleix.equals("2")){
			ArrHeader[0]=new String[] {"分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况","分矿来煤情况"};
			ArrHeader[1]=new String[] {"地区","单位","供应商","矿别","计划口径","品种","运输<br>方式","煤价<br>(含税)","运价<br>(含税)","到厂热值","含税<br>标煤单价","不含税<br>标煤单价","来煤量","来煤量"};
			ArrHeader[2]=new String[] {"地区","单位","供应商","矿别","计划口径","品种","运输<br>方式","煤价<br>(含税)","运价<br>(含税)","到厂热值","含税<br>标煤单价","不含税<br>标煤单价","当日","累计"};
		}
		int ArrWidth[]=new int[] {180,80,120,110,60,50,60,60,60,60,60,60,60,60,60,60};
		//rs.beforefirst();
		rt.setBody(new Table(arrData, 3, 0, 0 , 16));
		rt.body.setHeaderData(ArrHeader);
		//rt.body.merge(1, 1, 1, 14);
		rt.body.setWidth(ArrWidth);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
//		rt.body.mergeFixedRow();
		/*rt.body.mergeFixedCol(1);
		rt.body.mergeFixedCol(2);*/
		
		rt.body.mergeFixedRow();
		rt.body.mergeFixedCols();
		
		rt.body.ShowZero=true;
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_LEFT);
		rt.body.setColAlign(4, Table.ALIGN_LEFT);
		rt.body.setColAlign(5, Table.ALIGN_CENTER);
		rt.body.setColAlign(6, Table.ALIGN_CENTER);
		rt.body.setColAlign(7, Table.ALIGN_CENTER);
		rt.body.setColAlign(8, Table.ALIGN_RIGHT);
		rt.body.setColAlign(9, Table.ALIGN_RIGHT);
		rt.body.setColAlign(10, Table.ALIGN_RIGHT);
		rt.body.setColAlign(11, Table.ALIGN_RIGHT);
		rt.body.setColAlign(12, Table.ALIGN_RIGHT);
		rt.body.setColAlign(13, Table.ALIGN_RIGHT);
		rt.body.setColAlign(14, Table.ALIGN_RIGHT);
		rt.body.setColAlign(15, Table.ALIGN_RIGHT);
		rt.body.setColAlign(16, Table.ALIGN_RIGHT);

		rt.createFooter(1, ArrWidth);
		rt.setDefautlFooter(1, 3, "主管：", Table.ALIGN_CENTER);
		rt.setDefautlFooter(4, 3, "审核：", Table.ALIGN_CENTER);
		rt.setDefautlFooter(7, 3, "制表：", Table.ALIGN_CENTER);
		return rt.getAllPagesHtml();
	}
	
	private String getItem(){
		Map<String,Object> xitxxmap = new HashMap<String,Object>();
		xitxxmap.put("leib","收耗存日报");
		xitxxmap.put("mingc","是否对日报报表汇总信息取整");
		xitxxmap.put("diancid","0");
		String xitxxItem = ribcxDao.getXitxxItem(xitxxmap);
		if(null == xitxxItem || ""==xitxxItem){
			xitxxItem = "";
		}
		return xitxxItem;
	}
	
	private String getZHI(String diancid){
		String zhi = "";
		zhi = ribcxDao.findzhi(diancid);
		if(null==zhi || "".equals(zhi)){
			zhi = " -1 ";
		}
		return zhi;
	}
}
