package com.zhiren.fuelmis.dc.service.impl.jih;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.jih.NiandjhtjDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.jih.INiandjhtjService;
import com.zhiren.fuelmis.dc.utils.DrawTableUtil;
@Service
public class NiandjhtjService implements INiandjhtjService {
	@Autowired
	private NiandjhtjDao niandjhtjDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public String getZhibData(String diancid,String intyear) {
		// TODO Auto-generated method stub
		String curdate = intyear+"-01-01";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("curdate", curdate);
		map.put("diancid", diancid);
		List<Map<String, Object>> list = niandjhtjDao.getZhibData(map);
		String[] column = {"QIB","FADL","GONGDMH","FADCYDL","FADBML","GONGRL","GONGRMH","GONGRBML","BIAOMLHJ","MEIZBML","MEIZBMDJ","RANYL","YOUZBML","RANYDJ","YOUZBMDJ","QITFY","RLZHBMDJ"};
		String[][] arrData = DrawTableUtil.Creatarr(list,column);
		// 报表表头定义
		Report rt = new Report();
		int ArrWidth[] = null;
		String ArrHeader[][] = null;
		Table tb = new Table(arrData, 3, 0, 1, 17);
		rt.setBody(tb);

		ArrHeader = new String[3][17];
		ArrHeader[0] = (new String[] { "期别", "发电量", "供电煤耗", "发电厂<br>用电率", "发电标煤量","供热量","供热煤耗","供热标煤量","标煤量合计","煤折标煤量","煤折<br>标煤单价","燃油量","油折<br>标煤量","燃油单价<br>(不含税)","油折<br>标煤单价","其他费用","入炉综合标煤单价"});
		ArrHeader[1] = (new String[] { "期别", "万千瓦时","克/千瓦时", "%", "吨","万吉焦","千克/吉焦","吨","吨","吨","元/吨","吨","吨","元/吨","元/吨","元","元/吨"});
		ArrHeader[2] = (new String[] { "甲", "1","2", "3", "4","5","6","7","8","9","10","11","12","13","14","15","16"});
		
		ArrWidth = (new int[] { 120,80,70,70,80,80,70,80,80,80,70,80,70,70,80,80,80});
		String rptitle="表三:"+intyear+"年相关指标预测";
		rt.setTitle("", ArrWidth);
		rt.title.setCellValue(3, 1, rptitle);
		rt.title.setCellFont(3, 1, "", 12, true);
		rt.title.setCellAlign(3, 1, Table.ALIGN_LEFT);
		rt.title.mergeRowCells(3);
		
		rt.body.setWidth(ArrWidth);
		rt.body.ShowZero = true;
		rt.body.setWidth(ArrWidth);
		rt.body.setHeaderData(ArrHeader);
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
		//合并表头
		rt.body.mergeRow(1);
		rt.body.merge(1, 1, 2, 1);
		//设置对齐方式
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		
		rt.createDefautlFooter(ArrWidth);
		return rt.getAllPagesHtml();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getCgjhData(String diancid,String intyear) {
		// TODO Auto-generated method stub
		String curdate = intyear+"-01-01";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("curdate", curdate);
		map.put("diancid", diancid);
		//获取电厂名称
		String  dianc_mingc = niandjhtjDao.getDiancMingc(diancid);
		if(null == dianc_mingc){
			dianc_mingc = "";
		}
		List<Map<String, Object>> list = niandjhtjDao.getCgjhData(map);
		String[] column = {"GHDW","JIHKJ","HET_SL","HET_REZ","HET_MEIJ","HET_YUNJ","JIH_SL","JIH_REZ","JIH_REZC","JIH_MEIJ","JIH_MEIJBHS","JIH_YUNJ",
				"JIH_YUNJBHS","JIH_ZAF","JIH_ZAFBHS","JIH_QIT","JIH_QITBHS","JIH_DAOCJ","JIH_DAOCBMDJ","ZRLBMDL","RLMZBMDJ"};
		String[][] arrData = DrawTableUtil.Creatarr(list,column);
		// 报表表头定义
		Report rt = new Report();
		int ArrWidth[] = null;
		String ArrHeader[][] = null;
		Table tb = new Table(arrData, 4, 0, 1, 21);
		rt.setBody(tb);
		ArrHeader = new String[4][21];
		ArrHeader[0] = (new String[] { "供货单位","计划口径", intyear+"年合同量", intyear+"年合同量", intyear+"年合同量", intyear+"年合同量",intyear+"年采购情况预测",intyear+"年采购情况预测",intyear+"年采购情况预测",intyear+"年采购情况预测",intyear+"年采购情况预测",intyear+"年采购情况预测",intyear+"年采购情况预测",intyear+"年采购情况预测",intyear+"年采购情况预测",intyear+"年采购情况预测",intyear+"年采购情况预测",intyear+"年采购情况预测",intyear+"年采购情况预测",intyear+"年采购情况预测",intyear+"年采购情况预测"});
		ArrHeader[1] = (new String[] { "供货单位","计划口径", "数量", "热值", "车板价<br>(含税)", "运费<br>(含税)","到货量","热值","折入炉热值","车板价","车板价<br>(不含税)","运费","运费<br>(不含税)","杂费","杂费<br>(不含税)","其他","其他<br>(不含税)","到厂价","到厂标<br>煤单价","折入炉<br>标煤量","入炉煤折<br>标煤单价<br>(不含税)" });
		ArrHeader[2] = (new String[] { "供货单位","计划口径", "吨","兆焦/千克", "元/吨", "元/吨","吨","兆焦/千克","兆焦/千克","元/吨","元/吨","元/吨","元/吨","元/吨","元/吨","元/吨","元/吨","元/吨","元/吨","吨","元/吨"});
		ArrHeader[3] = (new String[] { "甲","乙", "1","2", "3", "4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19"});
		
		ArrWidth = (new int[] { 130,60,70,60,60,60,70,60,60,60,60,60,60,60,60,60,60,60,60,60,60 });
		String rptitle="表一："+intyear+"年煤炭采购计划(表中“杂费”是指煤炭买卖合同中约定的与煤款或运费一同结算的费用：包括填报装车、站台、港杂等费用，一票结算的除外)";
		rt.setTitle("", ArrWidth);
		
		rt.title.setCellValue(2, 1, "单位名称："+dianc_mingc);
		rt.title.setCellFont(2, 1, "", 12, true);
		rt.title.setCellAlign(2, 1, Table.ALIGN_LEFT);
		rt.title.mergeRowCells(2);
		
		rt.title.setCellValue(3, 1, rptitle);
		rt.title.setCellFont(3, 1, "", 12, true);
		rt.title.setCellAlign(3, 1, Table.ALIGN_LEFT);
		rt.title.mergeRowCells(3);
		
		rt.body.setWidth(ArrWidth);
		rt.body.ShowZero = true;
		rt.body.setWidth(ArrWidth);
		rt.body.setHeaderData(ArrHeader);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
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
		rt.body.setColAlign(18, Table.ALIGN_RIGHT);
		rt.body.setColAlign(19, Table.ALIGN_RIGHT);
		rt.body.setColAlign(20, Table.ALIGN_RIGHT);
		rt.body.setColAlign(21, Table.ALIGN_RIGHT);
//		合并表头
		rt.body.mergeRow(1);
		rt.body.merge(1, 1, 3, 1);
		rt.body.merge(1, 2, 3, 2);
//		设置对齐方式
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		return rt.getAllPagesHtml();
	}
	@SuppressWarnings("unchecked")
	@Override
	public String getZfjhbData(String diancid,String intyear) {
		// TODO Auto-generated method stub
		String curdate = intyear+"-01-01";
		int year = Integer.parseInt(intyear);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("curdate", curdate);
		map.put("diancid", diancid);
		List<Map<String, Object>> list = niandjhtjDao.getZfjhData(map);
		String[] column = {"ZAFMC","YUCJE","YUCSM","HEJ","SHIJWCJE","YUJWCJE","YUJWCSM"};
		String[][] arrData = DrawTableUtil.Creatarr(list,column);
		// 报表表头定义
		Report rt = new Report();
		int ArrWidth[] = null;
		String ArrHeader[][] = null;
		Table tb = new Table(arrData, 2, 0, 1, 7);
		rt.setBody(tb);

		ArrHeader = new String[2][7];
		ArrHeader[0] = (new String[] { "费用名称", intyear+"年预测", intyear+"年预测", (year-1)+"年预计完成", (year-1)+"年预计完成", (year-1)+"年预计完成",(year-1)+"年预计完成"});
		ArrHeader[1] = (new String[] { "费用名称", "预测(元)", "说明","合计(元)", "实际完成(元)", "预计(元)","说明"});
		
		ArrWidth = (new int[] { 170,120,380,100,100,100,380});
		String rptitle="表二:列入燃料成本的其它费用（燃料杂费）";
		rt.setTitle("", ArrWidth);
		rt.title.setCellValue(3, 1, rptitle);
		rt.title.setCellFont(3, 1, "", 12, true);
		rt.title.setCellAlign(3, 1, Table.ALIGN_LEFT);
		rt.title.mergeRowCells(3);
		
		rt.body.setWidth(ArrWidth);
		rt.body.ShowZero = true;
		rt.body.setWidth(ArrWidth);
		rt.body.setHeaderData(ArrHeader);
//				合并表头
		rt.body.mergeFixedRowCol();
//				设置对齐方式
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_LEFT);
		rt.body.setColAlign(4, Table.ALIGN_RIGHT);
		rt.body.setColAlign(5, Table.ALIGN_RIGHT);
		rt.body.setColAlign(6, Table.ALIGN_RIGHT);
		rt.body.setColAlign(7, Table.ALIGN_LEFT);
		return rt.getAllPagesHtml();
	}
	@Override
	public String getTableHtml(String diancid,String intyear){
		return getCgjhData(diancid,intyear)+getZfjhbData(diancid,intyear)+getZhibData(diancid,intyear);
	}

}
