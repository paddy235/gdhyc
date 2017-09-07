package com.zhiren.fuelmis.dc.service.impl.kucgl.baobcx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.kucgl.RanmzgDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.kucgl.baobcx.IRanmzgService;
import com.zhiren.fuelmis.dc.utils.formular.Result;

import net.sf.json.JSONArray;
@Service
public class RanmzgService implements IRanmzgService{
	/**
	 * 
	 * 燃煤暂估明细表
	 */
	@Autowired
	private RanmzgDao ranmzgDao;
	@Override
	public JSONArray getRanmzg(Map<String, Object> map) {
		
		String ranmzgId = ranmzgDao.getRanmzgId(map);
		List<Map<String, Object>> list = null;
		String[][] arrData = {};
		map.put("kuaijqId", ranmzgId);
		if (ranmzgId != null) {
			list = ranmzgDao.getRanmzg(map);
			arrData = Result.list2array(list,
			new String[] { "HANGC", "JIZRQ", "QICSL", "QICDJ", "QICJE", "RUCSL", "RUCDJ", "RUCJE",
					"CHUKSL", "CHUKDJ", "CHUKJE", "QITHYSL", "QITHYDJ", "QITHYJE", "DANGRSL", "DANGRDJ",
					"DANGRJE" });

		}
	
		Report rt = new Report();
		String[][] ArrHeader = new String[3][14];
		ArrHeader[0] = new String[] { "煤种", "矿点", "上月暂估","上月暂估","上月暂估", "本月来煤","本月来煤","本月来煤", "本月结算","本月结算","本月结算", "本月暂估","本月暂估","本月暂估" };
		ArrHeader[1] = new String[] { "煤种", "矿点", "煤量", "单价", "金额", "煤量", "单价", "金额", "煤量", "单价","金额", "暂估煤量", "暂估单价", "暂估金额", };
		//ArrHeader[2] = new String[] { "行次", "记账日期", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"/*, "13",
		//		"14", "15", */};
		int[] ArrWidth = new int[] { 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80};
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

		titleTable.setCellAlign(3, titleTable.getCols() - 1, Table.ALIGN_RIGHT);

		titleTable.setCellAlign(3, 1, Table.ALIGN_CENTER);

		titleTable.setCellValue(4, titleTable.getCols() - 1, "单位：元、吨、元/吨", 2);
		titleTable.setCellAlign(4, titleTable.getCols() - 1, Table.ALIGN_RIGHT);

		rt.setTitle(titleTable);
		rt.setTitle(map.get("riq").toString().substring(0,4)+"年"+map.get("riq").toString().substring(6)+"月"+"燃煤暂估明细表", 2);
		rt.setBody(new Table(arrData, 3, 0, 0, 14));
		rt.body.setHeaderData(ArrHeader);
		// 合并单元格
		rt.body.mergeCell(1, 1, 3, 1);
		rt.body.mergeCell(1, 2, 3, 2);

		rt.body.mergeCell(1, 3, 1, 5);
		rt.body.mergeCell(1, 6, 1, 8);
		rt.body.mergeCell(1, 9, 1, 11);
		rt.body.mergeCell(1, 12, 1, 14);
		rt.body.setRowCells(1, Table.PER_ALIGN, Table.ALIGN_CENTER);
		// 设置字体
		rt.body.setRowCells(1, Table.PER_FONTBOLD, true);
		rt.body.setRowCells(1, Table.PER_BACKCOLOR, "#948a54");
		rt.body.setRowCells(2, Table.PER_BACKCOLOR, "#948a54");
		rt.body.setRowCells(3, Table.PER_BACKCOLOR, "#948a54");
		rt.body.setRowCells(1, Table.PER_FORECOLOR, "#FFFFFF");
		rt.body.setRowCells(2, Table.PER_FORECOLOR, "#FFFFFF");
		rt.body.setRowCells(3, Table.PER_FORECOLOR, "#FFFFFF");

		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		for (int i = 3; i <= 16; i++) {
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}
		for (int i = 6; i <= 14; i++) {
			rt.body.setColFormat(i, "0.00");
		}
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "分管领导:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(11, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter((rt.footer.getCols() - 1), 1, "制表:", Table.ALIGN_RIGHT);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

}
