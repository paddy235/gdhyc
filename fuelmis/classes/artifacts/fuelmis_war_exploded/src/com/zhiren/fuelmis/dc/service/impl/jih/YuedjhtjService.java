package com.zhiren.fuelmis.dc.service.impl.jih;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.jih.NiandjhtjDao;
import com.zhiren.fuelmis.dc.dao.jih.YuedjhtjDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.jih.IYuedjhtjService;
import com.zhiren.fuelmis.dc.utils.DrawTableUtil;
@Service
public class YuedjhtjService implements IYuedjhtjService {
	@Autowired
	private YuedjhtjDao yuedjhtjDao;
	@Autowired
	private NiandjhtjDao niandjhtjDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public String getYuedjhcaig(String diancid,String riq) {
		// TODO Auto-generated method stub
		String curdate = riq+"-01";
		String format_curdate = riq.replace("-", "年")+"月";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("curdate", curdate);
		map.put("diancid", diancid);
		//获取电厂名称
		String  dianc_mingc = niandjhtjDao.getDiancMingc(diancid);
		if(null == dianc_mingc){
			dianc_mingc = "";
		}
		List<Map<String, Object>> list = yuedjhtjDao.getYuedjhcaig(map);
		String[] column = {"GONGYSB_ID","MEIKXXB_ID","JIHKJB_ID","PINZB_ID","FAZ_ID","JIH_SL","JIH_REZ","JIH_REZC","JIH_LIUF","JIH_HFF","JIH_MEIJ",
				"JIH_MEIJBHS","JIH_YUNJ","JIH_YUNJBHS","JIH_ZAF","JIH_ZAFBHS","JIH_DAOCJ","JIH_DAOCJBHS","JIH_DAOCBMDJ","JIH_DAOCBMDJBHS","ZRLBMDL","RLMZBMDJ"};
		String[][] arrData = DrawTableUtil.Creatarr(list,column);
		// 报表表头定义
		Report rt = new Report();
		int ArrWidth[] = null;
		String ArrHeader[][] = null;
		Table tb = new Table(arrData, 3, 0, 5,22);
		rt.setBody(tb);
	
		ArrHeader = new String[3][22];
		ArrHeader[0] = (new String[] { "供货单位","煤矿单位","计划口径", "品种","发站","采购量","热值","折入炉热值","硫份","挥发份","车板价","车板价<br>(不含税)","运费","运费<br>(不含税)","杂费","杂费<br>(不含税)","到厂价","到厂价<br>(不含税)","到厂<br>标煤单价","到厂<br>标煤单价<br>(不含税)","折入炉<br>标煤量","入炉煤折<br>标煤单价<br>(不含税)"});
		ArrHeader[1] = (new String[] { "供货单位", "煤矿单位","计划口径", "品种", "发站", "吨","兆焦/千克","兆焦/千克","%","%","元/吨","元/吨","元/吨","元/吨","元/吨","元/吨","元/吨","元/吨","元/吨","元/吨","吨","元/吨"});
		ArrHeader[2] = (new String[] { "甲", "1","2", "3", "4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21"});
		
		ArrWidth = (new int[] { 150,150,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60 });
		
		String rptitle="表一："+format_curdate+"煤炭采购计划(表中“杂费”是指煤炭买卖合同中约定的与煤款或运费一同结算的费用：包括填报装车、站台、港杂等费用，一票结算的除外)";
		rt.setTitle("", ArrWidth);
		
		rt.title.setCellValue(2, 1, "单位名称："+dianc_mingc);
		rt.title.setCellFont(2, 1, "", 12, true);
		rt.title.setCellAlign(2, 1, Table.ALIGN_LEFT);
		
		rt.title.setCellValue(3, 1, rptitle);
		rt.title.setCellFont(3, 1, "", 12, true);
		rt.title.setCellAlign(3, 1, Table.ALIGN_LEFT);
		rt.title.mergeRowCells(3);
		
		rt.body.setWidth(ArrWidth);
		rt.body.ShowZero = true;
		rt.body.setWidth(ArrWidth);
		rt.body.setHeaderData(ArrHeader);
		//合并表头
		//rt.body.mergeRow(1);
		rt.body.merge(1, 1, 2, 1);
		rt.body.merge(1, 2, 2, 2);
		rt.body.merge(1, 3, 2, 3);
		rt.body.merge(1, 4, 2, 4);
		rt.body.merge(1, 5, 2, 5);
		
	//				设置对齐方式
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		rt.body.setColAlign(4, Table.ALIGN_CENTER);
		rt.body.setColAlign(5, Table.ALIGN_CENTER);
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
		return rt.getAllPagesHtml();
	}
	@SuppressWarnings("unchecked")
	@Override
	public String getZfjh(String diancid,String riq) {
		// TODO Auto-generated method stub
		String curdate = riq+"-01";
		String format_curdate = riq.replace("-", "年")+"月";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("curdate", curdate);
		map.put("diancid", diancid);
		List<Map<String, Object>> list = yuedjhtjDao.getZfjh(map);
		String[] column = {"ZAFMC","YUCJE","YUCSM"};
		String[][] arrData = DrawTableUtil.Creatarr(list,column);
		// 报表表头定义
		Report rt = new Report();
		int ArrWidth[] = null;
		String ArrHeader[][] = null;
		Table tb = new Table(arrData, 2, 0, 1,3);
		rt.setBody(tb);

		ArrHeader = new String[2][3];
		ArrHeader[0] = (new String[] { "费用名称", format_curdate+"预测", format_curdate+"预测"});
		ArrHeader[1] = (new String[] { "费用名称", "预测(元)", "说明"});
		
		ArrWidth = (new int[] { 360,290,850});
		String rptitle="表二:"+format_curdate+"列入燃料成本的其它费用（燃料杂费）";
		rt.setTitle("", ArrWidth);
		rt.title.setCellValue(3, 1, rptitle);
		rt.title.setCellFont(3, 1, "", 12, true);
		rt.title.setCellAlign(3, 1, Table.ALIGN_LEFT);
		rt.title.mergeRowCells(3);
		
		rt.body.setWidth(ArrWidth);
		rt.body.ShowZero = true;
		rt.body.setWidth(ArrWidth);
		rt.body.setHeaderData(ArrHeader);
		//合并表头
		rt.body.mergeFixedRowCol();
		//设置对齐方式
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_LEFT);
		return rt.getAllPagesHtml();
	}
	@SuppressWarnings("unchecked")
	@Override
	public String getZhib(String diancid,String riq) {
		// TODO Auto-generated method stub
		String curdate = riq+"-01";
		String format_curdate = riq.replace("-", "年")+"月";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("curdate", curdate);
		map.put("diancid", diancid);
		List<Map<String, Object>> list = yuedjhtjDao.getZhib(map);
		String[] column = {"QIB","FADL","GONGDMH","FADCYDL","FADBML","GONGRL","GONGRMH","GONGRBML","BIAOMLHJ","HAOYYML","RLZHBMDJ","SHANGYMKC","SHANGYMKCDJ",
				"SHANGYMKCRZ","YUEMKCJHZ","YUEMKCRZ"};
		String[][] arrData = DrawTableUtil.Creatarr(list,column);
		//报表表头定义
		Report rt = new Report();
		int ArrWidth[] = null;
		String ArrHeader[][] = null;
		Table tb = new Table(arrData, 3, 0, 1,16);
		rt.setBody(tb);

		ArrHeader = new String[3][16];
		ArrHeader[0] = (new String[] { "期别", "发电量", "供电煤耗", "发电厂用电率", "发电标煤量","供热量","供热煤耗","供热标煤量","标煤量合计","耗用原煤量","入炉综合标煤单价","上月末库存","上月末库存单价","上月末库存热值","月末库存计划值","月末库存热值"});
		ArrHeader[1] = (new String[] { "期别", "万千瓦时","克/千瓦时", "%", "吨","万吉焦","千克/吉焦","吨","吨","吨","元/吨","吨","元/吨","兆焦/千克","吨","兆焦/千克"});
		ArrHeader[2] = (new String[] { "甲", "1","2", "3", "4","5","6","7","8","9","10","11","12","13","14","15"});
		
		ArrWidth = (new int[] { 90,90,90,90,90,90,90,90,90,90,110,90,100,100,100,100});
		String rptitle="表三:"+format_curdate+"相关指标预测";
		rt.setTitle("", ArrWidth);
		rt.title.setCellValue(3, 1, rptitle);
		rt.title.setCellFont(3, 1, "", 12, true);
		rt.title.setCellAlign(3, 1, Table.ALIGN_LEFT);
		rt.title.mergeRowCells(3);
		
		rt.body.setWidth(ArrWidth);
		rt.body.ShowZero = true;
		rt.body.setWidth(ArrWidth);
		rt.body.setHeaderData(ArrHeader);
		//合并表头
		rt.body.mergeRow(1);
		rt.body.merge(1, 1, 2, 1);
		//设置对齐方式
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.createDefautlFooter(ArrWidth);
				
		return rt.getAllPagesHtml();
	}

	@Override
	public String getTableHtml(String diancid, String riq) {
		// TODO Auto-generated method stub
		return getYuedjhcaig(diancid,riq)+getZfjh(diancid,riq)+getZhib(diancid,riq);
	}
}
