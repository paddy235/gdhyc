package com.zhiren.fuelmis.dc.service.impl.diaoygl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.diaoygl.DiaoybbDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.diaoygl.IDiaoybbService;
import com.zhiren.fuelmis.dc.utils.formular.Result;

@Service
public class DiaoybbcxServiceImpl implements IDiaoybbService {

	@Autowired
	private DiaoybbDao diaoybbDao;

	@Override
	public JSONArray getRanlrcgjhd(String riq) {
		List<Map<String, Object>> l = diaoybbDao.getRanlrcgjhd(riq);

		String[][] arrData = Result.list2array(l, new String[] { "JIHDH","RIQ", "GONGF", "MEIZ", "SHUL", "QNET_AR", "ST_AR", "V_AR","ZONGCS", "BANZCS" });
		Report rt = new Report();
		String ArrHeader[][] = new String[1][25];
		ArrHeader[0] = new String[] { "计划单号", "日期", "供方", "煤种", "数量(吨)",
				"收到基热值(kcal/kg)", "收到基硫分(%)", "收到基挥发分(%)", "收到基灰分(%)","总车数", "班组" };
		int ArrWidth[] = new int[] { 100, 90, 90, 90, 90, 90, 90, 90, 90, 80 ,80};
		rt.setBody(new Table(arrData, 1, 0, 5, 11));

		rt.body.setHeaderData(ArrHeader);
		rt.body.setWidth(ArrWidth);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(6, Table.ALIGN_RIGHT);
		rt.body.setColAlign(7, Table.ALIGN_RIGHT);
		rt.body.setColAlign(8, Table.ALIGN_RIGHT);
		rt.body.setColAlign(9, Table.ALIGN_RIGHT);
		rt.body.setColAlign(10, Table.ALIGN_RIGHT);
		rt.body.setColAlign(11, Table.ALIGN_RIGHT);
		rt.setTitle("燃料日采购计划单", ArrWidth);
		rt.body.setPageRows(15);
		int pageCount = rt.getPages();
		// rt.setDefaultTitle(1, 3, "卸煤日期:"+strdate+"至"+enddate,
		// Table.ALIGN_LEFT);

		rt.setDefautlFooter(4, 3, "批准：", Table.ALIGN_LEFT);
		rt.setDefautlFooter(9, 3, "编制:", Table.ALIGN_LEFT);

		//rt.body.mergeFixedRow();
		//rt.body.mergeFixedCols();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml());
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);

		return jsonArray;
	}

	@Override
	public JSONArray getDuizd(String riq) {
		List<Map<String, Object>> l = diaoybbDao.getDuizd(riq);
		String[] jihNames= new String[] { "JIHDH_J","RIQ_J", "GONGF_J", "MEIZ_J", "SHUL_J", "QNET_AR_J", "ST_AR_J", "V_AR_J","ZONGCS_J","BANZCS_J"};//,"ZONGCS", "BANZCS" }
		String[] shijNames= new String[] { "JIHDH_S","RIQ_S", "GONGF_S", "MEIZ_S", "SHUL_S", "QNET_AR_S", "ST_AR_S", "V_AR_S","ZONGCS_S","BANZCS_S"};
		String[][] arrData = new String[l.size()*2][jihNames.length];
		for (int i = 0; i < l.size(); i++) {
			Map<String, Object> map = l.get(i);
			for (int j = 0; j < jihNames.length; j++) {
				String key=jihNames[j];
				Object value = map.get(key);
				if(value!=null){
					arrData[2*i][j]=value.toString();
				}else{
					arrData[2*i][j]="";
				}
			}
			for (int j = 0; j < shijNames.length; j++) {
				String key=shijNames[j];
				Object value = map.get(key);
				if(value!=null){
					arrData[2*i+1][j]=value.toString();
				}else{
					arrData[2*i+1][j]="";
				}
			}
		}
		//String[][] arrData = Result.list2array(l, new String[] { "JIHDH","RIQ", "GONF", "MEIZ", "SHUL", "QNET_AR", "ST_AR", "V_AR","ZONGCS", "BANZCS" });
		Report rt = new Report();
		String ArrHeader[][] = new String[1][25];
		ArrHeader[0] = new String[] { "计划单号", "日期", "供方", "煤种", "数量(吨)",
				"收到基热值(kcal/kg)", "收到基硫分(%)", "收到基挥发分(%)", "收到基灰分(%)","总车数", "班组" };
		int ArrWidth[] = new int[] { 100, 90, 90, 90, 90, 90, 90, 90, 90, 80 ,80};
		rt.setBody(new Table(arrData, 1, 0, 5, 11));

		rt.body.setHeaderData(ArrHeader);
		rt.body.setWidth(ArrWidth);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(6, Table.ALIGN_RIGHT);
		rt.body.setColAlign(7, Table.ALIGN_RIGHT);
		rt.body.setColAlign(8, Table.ALIGN_RIGHT);
		rt.body.setColAlign(9, Table.ALIGN_RIGHT);
		rt.body.setColAlign(10, Table.ALIGN_RIGHT);
		rt.body.setColAlign(11, Table.ALIGN_RIGHT);
		rt.setTitle("燃料日采购计划单", ArrWidth);
		rt.body.setPageRows(15);
		int pageCount = rt.getPages();
		// rt.setDefaultTitle(1, 3, "卸煤日期:"+strdate+"至"+enddate,
		// Table.ALIGN_LEFT);

		rt.setDefautlFooter(4, 3, "批准：", Table.ALIGN_LEFT);
		rt.setDefautlFooter(9, 3, "编制:", Table.ALIGN_LEFT);

		rt.body.mergeFixedRow();
		rt.body.mergeFixedCols();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml());
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);

		return jsonArray;
	}
}
