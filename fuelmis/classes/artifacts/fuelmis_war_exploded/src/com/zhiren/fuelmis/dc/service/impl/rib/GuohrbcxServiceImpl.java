package com.zhiren.fuelmis.dc.service.impl.rib;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.rib.GuohrbDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.rib.IGuohrbcxService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.DateUtil.DateFormatType;

@Service("guohrbcxService")
public class GuohrbcxServiceImpl implements IGuohrbcxService {

	@Autowired
	private GuohrbDao guohrbDao;
	
	/**
	 * 构建报表数据
	 * @param map
	 * @return
	 */
	private String[][] getTabelDataInfo(Map<String, Object> map) {	
		List<Map<String, Object>> list = guohrbDao.getTabelData(map);
		int rowsize = list.size();
		String[] column = {"DIANC","FAHDW","MEIKDW","FAZ","PINZ","LAIMSL","JINGZ","BIAOZ","YINGK","YUNS","ZONGKD","CHES"};
		int colsize = column.length;
		
		String[][] tablevalue = new String[rowsize][colsize];
		Object middlevalue;
		for(int m = 0 ; m<list.size(); m++){
			HashMap<String, Object> HashMap = (HashMap<String, Object>) list.get(m);
			for(int n = 0; n<colsize; n++){
				middlevalue = HashMap.get(column[n]);
				if(null == middlevalue){
					tablevalue[m][n] = "";
				}else{
					tablevalue[m][n] = String.valueOf(HashMap.get(column[n]));
				}
			}
		}
		return tablevalue;
	}
	
	@Override
	public JSONArray getTabelData(String date,String id) {
		
		Map<String,Object> shouhcmap = new HashMap<String,Object>();
		shouhcmap.put("date", date);
		Date std = DateUtil.parse(date, DateFormatType.SIMPLE_TYPE);
		String enddate = DateUtil.getAFDay(std);
		shouhcmap.put("enddate", enddate);
		shouhcmap.put("id", id);
		String[][] arrData = this.getTabelDataInfo(shouhcmap);
		Report rt = new Report();
		String ArrHeader[][]=new String[1][12];
		ArrHeader[0]=new String[] {"电厂名称","供货单位","煤矿单位","发站","品种","实收量","净重","票重","盈亏","运损","总扣顿","车数"};
		int ArrWidth[]=new int[] {100,90,90,90,90,90,90,90,90,80,80};
		//rt.setBody(new Table(arrData, 1, 0, 0, 12));
		rt.setBody(new Table(arrData, 1, 0, 5, 12));
		
		rt.body.setHeaderData(ArrHeader);
		rt.body.setWidth(ArrWidth);
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
		
		rt.setTitle("过衡日报",ArrWidth);
		rt.body.setPageRows(15);
		int pageCount = rt.getPages();
		rt.setDefaultTitle(1, 3, "填报单位:国电新疆红雁池发电有限公司", Table.ALIGN_LEFT);
		rt.setDefaultTitle(4, 4, "报表日期:"+date, Table.ALIGN_CENTER);
		rt.setDefaultTitle(8, 4, "单位:吨、车", Table.ALIGN_RIGHT);
//		rt.body.merge(1, 1, arrData.length+1, 1);
		
//		rt.body.merge(2, 2, 4, 2);
//		rt.body.merge(4, 2, 7, 2);
//		String mindval = null;
//		int stratRow=1;
//	    int endRow;
//		for(int i =0;i<arrData.length;i++){
//			if(mindval == null){
//				mindval = arrData[i][1];
//			}else{
//				if(mindval.equals(arrData[i][1])){
//					stratRow = stratRow + i;
//				}
//			}
//		}
		Date currtime = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String dayriq = format.format(currtime);
		
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "打印日期:" + dayriq, Table.ALIGN_LEFT);
		rt.setDefautlFooter(6, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter((rt.footer.getCols() - 1), 1, "制表:",
				Table.ALIGN_RIGHT);
		
		
        rt.body.mergeFixedRow();
		rt.body.mergeFixedCols();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//
		resultMap.put("html", rt.getAllPagesHtml());
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}	 	
}
