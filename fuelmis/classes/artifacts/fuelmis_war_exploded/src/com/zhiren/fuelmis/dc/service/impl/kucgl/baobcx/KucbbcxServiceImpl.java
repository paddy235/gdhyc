package com.zhiren.fuelmis.dc.service.impl.kucgl.baobcx;

import com.zhiren.fuelmis.dc.dao.kucgl.KucbbcxDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.kucgl.baobcx.IKucbbcxService;
import com.zhiren.fuelmis.dc.utils.formular.Result;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/24.
 */
@Service
public class KucbbcxServiceImpl implements IKucbbcxService {
	@Autowired
	private KucbbcxDao kucbbcxDao;

	@Override
	public JSONArray getFadgrckhybb(Map<String, Object> map) {
		List<Map<String, Object>> list = kucbbcxDao.getFadgrckhyList(map);
		String[][] arrData = Result.list2array(list, new String[] { "CHUKDH", "RULRQ", "HUOZ", "KUCZZ", "KUCWZ","KUCWL", "CHUKSL", "JINE", "YEWLX", "ZHUANGT" });
		Report rt = new Report();
		String[][] ArrHeader = new String[1][10];
		ArrHeader[0] = new String[] { "出库单号", "入炉日期", "货主", "库存组织", "库存位置", "库存物料", "出库数量", "金额", "业务类型", "状态" };
		int[] ArrWidth = new int[] { 130, 70, 70, 70, 70, 70, 70, 70, 90, 70 };
		Table titleTable = new Table(4, 10);
		titleTable.setBorderNone();
		for (int i = 1; i <= titleTable.getRows(); i++) {
			for (int j = 1; j <= titleTable.getCols(); j++) {
				titleTable.setCellBorderNone(i, j);
			}
		}

		titleTable.setCellValue(4, 1, "填报单位:国电新疆红雁池发电有限公司"  , titleTable.getCols() - 2);
		titleTable.setCellAlign(4, 1, Table.ALIGN_LEFT);

		titleTable.setCellValue(3, titleTable.getCols() - 1, "发电供热耗用出库报表", 2);
		titleTable.setCellAlign(3, titleTable.getCols() - 1, Table.ALIGN_RIGHT);

		titleTable.setCellValue(3, 1, map.get("Date").toString(), titleTable.getCols() - 2);
		titleTable.setCellAlign(3, 1, Table.ALIGN_CENTER);

		titleTable.setCellValue(4, titleTable.getCols() - 1, "单位:吨、元、% ", 2);
		titleTable.setCellAlign(4, titleTable.getCols() - 1, Table.ALIGN_RIGHT);
		rt.setTitle(titleTable);
		rt.setTitle("国电电力发展股份有限公司发电供热耗用出库报表", 2);
		rt.setBody(new Table(arrData, 1, 0, 0, 10));
		rt.body.setHeaderData(ArrHeader);
		// convertItem(rt.body);
		rt.body.setPageRows(15);
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

		rt.body.ShowZero = false;
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "分管领导:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(6, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter((rt.footer.getCols() - 1), 1, "制表:", Table.ALIGN_RIGHT);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		//
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}
	@Override
	public JSONArray getShiscbhsbb(Map<String, Object> map) {
		List<Map<String, Object>> list = kucbbcxDao.getShiscbhsbb(map);
		String[][] arrData = Result.list2array(list,
				new String[] { "HANGC", "KUCZZ", "KUCMEIC", "KUCWL",
						"YUECJIECSL", "YUECJIECDJ", "YUECJIECJE",
						"JIESSL","JIESDJ", "JIESJE", 
						"LEIJRCSL", "LEIJRCDJ", "LEIJRCJE", "LEIJRCMK", "LEIJRCYF", "LEIJRCQT","CHANGNFY",
						"RULSL", "RULDJ", "RULJE", 
						"QITSL","QITDJ","QITJE",
						"YUEMJIECSL", "YUEMJIECDJ", "YUEMJIECJE" });
		Report rt = new Report();
		String[][] ArrHeader = new String[3][26];
		ArrHeader[0] = new String[] { "行次", "库存组织", "库存煤场", "库存物料",
				"月初结存", "月初结存", "月初结存",
				"本月结算入库", "本月结算入库","本月结算入库",
				"本月累计入厂", "", "", "", "", "", "", 
				"本月累计入炉", "本月累计入炉", "本月累计入炉", 
				"本月累计其他耗用", "", "", 
				"月末结存","月末结存", "月末结存"

		};
		ArrHeader[1] = new String[] { "行次", "库存组织", "库存煤场", "库存物料", 
				"数量", "单价", "金额", 
				"数量", "单价", "金额", 
				"实际来量", "单价","金额", "其中：煤款", "运费", "其他", "厂内费用",
				"数量", "单价", "金额", 
				"数量", "单价", "金额", 
				"数量", "单价", "金额" };
		ArrHeader[2] = new String[] { "行次", "库存组织", "库存煤场", "库存物料", "1", "2", "3", "11", "12", "13", "4", "5", "6", "7",
				"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" };
		int[] ArrWidth = new int[] { 40, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80,
				80, 80, 80, 80, 80};
		// rt.title.merge(5, 1, 5, 19);
		Table titleTable = new Table(4, 26);
		titleTable.setBorderNone();
		for (int i = 1; i <= titleTable.getRows(); i++) {
			for (int j = 1; j <= titleTable.getCols(); j++) {
				titleTable.setCellBorderNone(i, j);
			}
		}
//		titleTable.setWidth(ArrWidth);
		titleTable.setCellValue(4, 1, "编制单位:国电新疆红雁池发电有限公司", titleTable.getCols() - 2);
		titleTable.setCellAlign(4, 1, Table.ALIGN_LEFT);

//		titleTable.setCellValue(3, titleTable.getCols() - 1, "企财21表", 2);
		titleTable.setCellAlign(3, titleTable.getCols() - 1, Table.ALIGN_RIGHT);

		titleTable.setCellValue(3, 1, "填报日期:" + map.get("riq").toString(), titleTable.getCols() - 2);
		titleTable.setCellAlign(3, 1, Table.ALIGN_CENTER);

		titleTable.setCellValue(4, titleTable.getCols() - 1, "单位：元、吨、元/吨", 2);
		titleTable.setCellAlign(4, titleTable.getCols() - 1, Table.ALIGN_RIGHT);

		rt.setTitle(titleTable);
		rt.setTitle("火电企业燃料收支结存情况表-月末汇总表", 2);
//		rt.setBody(new Table(arrData, 0, 0, 0));
		rt.setBody(new Table(arrData, 3, 0, 0, 26));
		rt.body.setWidth(ArrWidth);
//		rt.title.setWidth(ArrWidth);
		rt.body.setHeaderData(ArrHeader);
		//---------------------------------------- 设置字体-------------------------------------------------
		rt.body.setColCells(2, Table.PER_BACKCOLOR, "#ebf1de");
		rt.body.setColCells(3, Table.PER_BACKCOLOR, "#ebf1de");
		rt.body.setColCells(4, Table.PER_BACKCOLOR, "#ebf1de");
		rt.body.setRowCells(1, Table.PER_FONTBOLD, true);
		rt.body.setRowCells(1, Table.PER_BACKCOLOR, "#948a54");
		rt.body.setRowCells(2, Table.PER_BACKCOLOR, "#948a54");
		rt.body.setRowCells(3, Table.PER_BACKCOLOR, "#948a54");
		rt.body.setRowCells(1, Table.PER_FORECOLOR, "#FFFFFF");
		rt.body.setRowCells(2, Table.PER_FORECOLOR, "#FFFFFF");
		rt.body.setRowCells(3, Table.PER_FORECOLOR, "#FFFFFF");
		// -----------------------------------合并单元格-----------------------------------------------------------
		// rt.body.mergeFixedRowCol();
		rt.body.mergeCell(1, 1, 3, 1);
		rt.body.mergeCell(1, 2, 3, 2);
		rt.body.mergeCell(1, 3, 3, 3);
		rt.body.mergeCell(1, 4, 3, 4);

		rt.body.mergeCell(1, 5, 1, 7);
		rt.body.mergeCell(1, 8, 1, 10);
		rt.body.mergeCell(1, 11, 1, 17);
		rt.body.mergeCell(1, 18, 1, 20);
		rt.body.mergeCell(1, 21, 1, 23);//
		rt.body.mergeCell(1, 24, 1, 26);
//		rt.body.setPageRows(15);
		int n=list.size()+3;
		rt.body.setCellValue(n, 1, "合计");
		rt.body.mergeCell(n,1,n,4);
		
		
//		int pageCount = rt.getPages();

		// -----------------------------设置内容位置-------------------------------------
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		rt.body.setColAlign(4, Table.ALIGN_CENTER);
		for (int i = 5; i <= 26; i++) {
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}
		//--------------------------------------------------------------------------
		for (int i = 5; i <= 26; i++) {
			rt.body.setColFormat(i, "0.00");
		}
		// rt.body.ShowZero=false;
		// convertItem(rt.body);
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "分管领导:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(11, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter((rt.footer.getCols() - 1), 1, "制表:", Table.ALIGN_RIGHT);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml());
//		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	@Override
	public JSONArray getChukdbb(Map<String, Object> map) {
		List<Map<String, Object>> list = kucbbcxDao.getChukdbb(map);
		String[][] arrData = Result.list2array(list,
				new String[] { "CHUKDBH", "YEWRQ", "KUCZZ", "YEWLX", "CHUKSL", "CHUKDJ", "CHUKJE", /*"QNETAR", "MINGX" */});
		Report rt = new Report();
		String[][] ArrHeader = new String[1][9];
		ArrHeader[0] = new String[] { "出库单编号", "业务日期", "库存组织", "业务类型", "出库数量", "出库单价", "出库金额", /*"收到基低位发热量", "明细" */};

		int[] ArrWidth = new int[] { 120, 80, 80, 80, 80, 80, 80/*, 80, 80*/ };
		Table titleTable = new Table(4, 7);
		titleTable.setBorderNone();
		for (int i = 1; i <= titleTable.getRows(); i++) {
			for (int j = 1; j <= titleTable.getCols(); j++) {
				titleTable.setCellBorderNone(i, j);
			}
		}
		titleTable.setWidth(ArrWidth);
		titleTable.setCellValue(4, 1, "填报单位:国电新疆红雁池发电有限公司", titleTable.getCols() - 2);
		titleTable.setCellAlign(4, 1, Table.ALIGN_LEFT);

		titleTable.setCellValue(3, titleTable.getCols() - 1, "国电燃表", 2);
		titleTable.setCellAlign(3, titleTable.getCols() - 1, Table.ALIGN_RIGHT);

		titleTable.setCellValue(3, 1, "填报日期:" + map.get("Date").toString(), titleTable.getCols() - 2);
		titleTable.setCellAlign(3, 1, Table.ALIGN_CENTER);

		titleTable.setCellValue(4, titleTable.getCols() - 1, "单位:吨、元、% ", 2);
		titleTable.setCellAlign(4, titleTable.getCols() - 1, Table.ALIGN_RIGHT);

		rt.setTitle(titleTable);
		rt.setTitle("国电电力发展股份有限公司出库单报表", 2);
		rt.setBody(new Table(arrData, 1, 0, 0, 7));
		rt.body.setHeaderData(ArrHeader);
		rt.body.setPageRows(15);
		int pageCount = rt.getPages();

		rt.body.mergeFixedRowCol();
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		rt.body.setColAlign(4, Table.ALIGN_CENTER);
		for (int i = 5; i <= 7; i++) {
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}
//		rt.body.setColAlign(9, Table.ALIGN_CENTER);
		// for (int i = 6; i <= 24; i++) {
		// rt.body.setColFormat(i, "0.00");
		// }
		// rt.body.ShowZero=false;
		// convertItem(rt.body);
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "分管领导:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(9, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter((rt.footer.getCols() - 1), 1, "制表:", Table.ALIGN_RIGHT);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	@Override
	public JSONArray getRiclhcbb(Map<String, Object> map) {
		String kuaijqId = kucbbcxDao.getKuaijqId(map);
		List<Map<String, Object>> list = null;
		String[][] arrData = {};
		map.put("kuaijqId", kuaijqId);
		if (kuaijqId != null) {
			list = kucbbcxDao.getRiclhcbb(map);
			arrData = Result.list2array(list,
			new String[] { "HANGC", "JIZRQ", "QICSL", "QICDJ", "QICJE", "RUCSL", "RUCDJ", "RUCJE",
					"CHUKSL", "CHUKDJ", "CHUKJE", /*"QITHYSL", "QITHYDJ", "QITHYJE",*/ "DANGRSL", "DANGRDJ",
					"DANGRJE" });

		}
		Report rt = new Report();
		String[][] ArrHeader = new String[3][14];
		ArrHeader[0] = new String[] { "行次", "记账日期", "月初结存", "月初结存", "月初结存", "入厂", "入厂", "入厂", "出库", "出库", "出库", /*"其他耗用",
				"其他耗用", "其他耗用",*/ "当日结存", "当日结存", "当日结存" };
		ArrHeader[1] = new String[] { "行次", "记账日期", "数量", "单价", "金额", "实际来量", "单价", "金额", "数量", "单价", "金额", "数量", /*"单价",
				"金额", "数量",*/ "单价", "金额", };
		ArrHeader[2] = new String[] { "行次", "记账日期", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"/*, "13",
				"14", "15", */};
		int[] ArrWidth = new int[] { 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80/*, 80, 80, 80, */};
		// rt.title.merge(5, 1, 5, 19);
		Table titleTable = new Table(4, 14);
		titleTable.setBorderNone();
		for (int i = 1; i <= titleTable.getRows(); i++) {
			for (int j = 1; j <= titleTable.getCols(); j++) {
				titleTable.setCellBorderNone(i, j);
			}
		}
		titleTable.setWidth(ArrWidth);
		titleTable.setCellValue(4, 1, "编制单位:国电新疆红雁池发电有限公司", titleTable.getCols() - 2);
		titleTable.setCellAlign(4, 1, Table.ALIGN_LEFT);

//		titleTable.setCellValue(3, titleTable.getCols() - 1, "企财21表", 2);
		titleTable.setCellAlign(3, titleTable.getCols() - 1, Table.ALIGN_RIGHT);

		titleTable.setCellValue(3, 1, "填报日期:" + map.get("riq").toString(), titleTable.getCols() - 2);
		titleTable.setCellAlign(3, 1, Table.ALIGN_CENTER);

		titleTable.setCellValue(4, titleTable.getCols() - 1, "单位：元、吨、元/吨", 2);
		titleTable.setCellAlign(4, titleTable.getCols() - 1, Table.ALIGN_RIGHT);

		rt.setTitle(titleTable);
		rt.setTitle("火电企业燃料收支结存情况表-日常来耗存", 2);
		rt.setBody(new Table(arrData, 3, 0, 0, 14));
		rt.body.setHeaderData(ArrHeader);
		// 合并单元格
		// rt.body.mergeFixedRowCol();
		rt.body.mergeCell(1, 1, 3, 1);
		rt.body.mergeCell(1, 2, 3, 2);

		rt.body.mergeCell(1, 3, 1, 5);
		rt.body.mergeCell(1, 6, 1, 8);
		rt.body.mergeCell(1, 9, 1, 11);
		rt.body.mergeCell(1, 12, 1, 14);
//		rt.body.mergeCell(1, 15, 1, 17);//
		rt.body.setRowCells(1, Table.PER_ALIGN, Table.ALIGN_CENTER);
//		rt.body.setPageRows(15);
		// 设置字体
		rt.body.setRowCells(1, Table.PER_FONTBOLD, true);
		rt.body.setRowCells(1, Table.PER_BACKCOLOR, "#948a54");
		rt.body.setRowCells(2, Table.PER_BACKCOLOR, "#948a54");
		rt.body.setRowCells(3, Table.PER_BACKCOLOR, "#948a54");
		rt.body.setRowCells(1, Table.PER_FORECOLOR, "#FFFFFF");
		rt.body.setRowCells(2, Table.PER_FORECOLOR, "#FFFFFF");
		rt.body.setRowCells(3, Table.PER_FORECOLOR, "#FFFFFF");
//		int pageCount = rt.getPages();

		// rt.body.mergeFixedRowCol();
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		for (int i = 3; i <= 16; i++) {
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}
		for (int i = 6; i <= 14; i++) {
			rt.body.setColFormat(i, "0.00");
		}
		// rt.body.ShowZero=false;
		// convertItem(rt.body);
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "分管领导:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(11, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter((rt.footer.getCols() - 1), 1, "制表:", Table.ALIGN_RIGHT);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
//		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}
	@Override
	public JSONArray getRiclhcbbmx(Map<String, Object> map) {
		String kuaijqId = kucbbcxDao.getKuaijqId(map);
		List<Map<String, Object>> list = null;
		String[][] arrData = {};
		map.put("kuaijqId", kuaijqId);
		if (kuaijqId != null) {
			list = kucbbcxDao.getRiclhcbbmx(map);
			arrData = Result.list2array(list,
			new String[] { "HANGC", "JIZRQ","KUCWZ","KUCWL","QICSL", "QICDJ", "QICJE", "RUCSL", "RUCDJ", "RUCJE",
					"CHUKSL", "CHUKDJ", "CHUKJE",/* "QITHYSL", "QITHYDJ", "QITHYJE",*/ "DANGRSL", "DANGRDJ",
					"DANGRJE" });

		}
		Report rt = new Report();
		String[][] ArrHeader = new String[3][17];
		ArrHeader[0] = new String[] { "行次", "记账日期","库存煤场","库存物料","月初结存", "月初结存", "月初结存", "入厂", "入厂", "入厂", "出库", "出库", "出库", /*"其他耗用",
				"其他耗用", "其他耗用",*/ "当日结存", "当日结存", "当日结存" };
		ArrHeader[1] = new String[] { "行次", "记账日期","库存煤场","库存物料", "数量", "单价", "金额", "实际来量", "单价", "金额", "数量", "单价", "金额", /*"数量", "单价",
				"金额",*/ "数量", "单价", "金额", };
		ArrHeader[2] = new String[] { "行次", "记账日期","库存煤场","库存物料", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"/*, "13",
				"14", "15",*/ };
		int[] ArrWidth = new int[] { 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, /*80,80,80*/};
		// rt.title.merge(5, 1, 5, 19);
		Table titleTable = new Table(4, 16);
		titleTable.setBorderNone();
		for (int i = 1; i <= titleTable.getRows(); i++) {
			for (int j = 1; j <= titleTable.getCols(); j++) {
				titleTable.setCellBorderNone(i, j);
			}
		}
		titleTable.setWidth(ArrWidth);
		titleTable.setCellValue(4, 1, "编制单位:国电新疆红雁池发电有限公司", titleTable.getCols() - 2);
		titleTable.setCellAlign(4, 1, Table.ALIGN_LEFT);

//		titleTable.setCellValue(3, titleTable.getCols() - 1, "企财21表", 2);
		titleTable.setCellAlign(3, titleTable.getCols() - 1, Table.ALIGN_RIGHT);

		titleTable.setCellValue(3, 1, "填报日期:" + map.get("riq").toString(), titleTable.getCols() - 2);
		titleTable.setCellAlign(3, 1, Table.ALIGN_CENTER);

		titleTable.setCellValue(4, titleTable.getCols() - 1, "单位：元、吨、元/吨", 2);
		titleTable.setCellAlign(4, titleTable.getCols() - 1, Table.ALIGN_RIGHT);

		rt.setTitle(titleTable);
		rt.setTitle("火电企业燃料收支结存情况表-日常来耗存", 2);
		rt.setBody(new Table(arrData, 3, 0, 0, 16));
		rt.body.setHeaderData(ArrHeader);
		// 合并单元格
		// rt.body.mergeFixedRowCol();
		rt.body.mergeCell(1, 1, 3, 1);
		rt.body.mergeCell(1, 2, 3, 2);
		rt.body.mergeCell(1, 3, 3, 3);
		rt.body.mergeCell(1, 4, 3, 4);

		rt.body.mergeCell(1, 5, 1, 7);
		rt.body.mergeCell(1, 8, 1, 10);
		rt.body.mergeCell(1, 11, 1, 13);
		rt.body.mergeCell(1, 14, 1, 16);
//		rt.body.mergeCell(1, 17, 1, 19);//
		rt.body.setRowCells(1, Table.PER_ALIGN, Table.ALIGN_CENTER);
//		rt.body.setPageRows(15);
		// 设置字体
		rt.body.setRowCells(1, Table.PER_FONTBOLD, true);
		rt.body.setRowCells(1, Table.PER_BACKCOLOR, "#948a54");
		rt.body.setRowCells(2, Table.PER_BACKCOLOR, "#948a54");
		rt.body.setRowCells(3, Table.PER_BACKCOLOR, "#948a54");
		rt.body.setRowCells(1, Table.PER_FORECOLOR, "#FFFFFF");
		rt.body.setRowCells(2, Table.PER_FORECOLOR, "#FFFFFF");
		rt.body.setRowCells(3, Table.PER_FORECOLOR, "#FFFFFF");
//		int pageCount = rt.getPages();

		// rt.body.mergeFixedRowCol();
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		rt.body.setColAlign(4, Table.ALIGN_CENTER);
		for (int i = 5; i <= 16; i++) {
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}
		for (int i = 6; i <= 16; i++) {
			rt.body.setColFormat(i, "0.00");
		}
		// rt.body.ShowZero=false;
		// convertItem(rt.body);
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "分管领导:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(11, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter((rt.footer.getCols() - 1), 1, "制表:", Table.ALIGN_RIGHT);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
//		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}
	@Override
	public JSONArray getChukcxbb(Map<String, Object> map) {

		List<Map<String, Object>> list = kucbbcxDao.getChukcxbb(map);
		String[][] arrData=Result.list2array(list, new String[] { "MEIZ", "CHUKSL", "CHUKJE" });
		Report rt = new Report();
		String[][] ArrHeader = new String[1][3];
		ArrHeader[0] = new String[] { "煤种", "出库数量", "出库金额"};
		int[] ArrWidth = new int[] { 130, 70, 70};
		Table titleTable = new Table(4, 10);
		titleTable.setBorderNone();
		for (int i = 1; i <= titleTable.getRows(); i++) {
			for (int j = 1; j <= titleTable.getCols(); j++) {
				titleTable.setCellBorderNone(i, j);
			}
		}

//		titleTable.setCellValue(4, 1, "填报单位:国电新疆红雁池发电有限公司"  , titleTable.getCols() - 2);
		titleTable.setCellAlign(4, 1, Table.ALIGN_LEFT);

//		titleTable.setCellValue(3, titleTable.getCols() - 1, "发电出库报表", 2);
		titleTable.setCellAlign(3, titleTable.getCols() - 1, Table.ALIGN_RIGHT);

		titleTable.setCellValue(3, 2, map.get("sDate").toString()+"至"+map.get("eDate").toString(), titleTable.getCols() - 2);
		titleTable.setCellAlign(3, 2, Table.ALIGN_CENTER);

//		titleTable.setCellValue(4, titleTable.getCols() - 1, "单位:吨、元、% ", 2);
		titleTable.setCellAlign(4, titleTable.getCols() - 1, Table.ALIGN_RIGHT);
		rt.setTitle(titleTable);
		rt.setTitle("国电新疆红雁池发电有限公司出库报表", 2);
		rt.setBody(new Table(arrData, 1, 0, 0, 3));
		rt.body.setHeaderData(ArrHeader);
		// convertItem(rt.body);
		rt.body.setPageRows(15);
		int pageCount = rt.getPages();

		rt.body.mergeFixedCols();
		rt.body.mergeFixedRow();
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		rt.body.ShowZero = false;
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "制表人:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(6, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter((rt.footer.getCols() - 1), 1, "制表:", Table.ALIGN_RIGHT);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		//
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}
	
}
