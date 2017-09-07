package com.zhiren.fuelmis.dc.service.impl.yansgl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.dao.yansgl.GuohmxDao;
import com.zhiren.fuelmis.dc.dao.yansgl.ShulhjblDao;
import com.zhiren.fuelmis.dc.dao.yansgl.WailyrlDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.yansgl.GuohmxService;
import com.zhiren.fuelmis.dc.service.yansgl.ShulhjblService;
import com.zhiren.fuelmis.dc.service.yansgl.WailyrlService;

public class GuohmxServiceImpl implements GuohmxService{

	@Resource
	private GuohmxDao guohmxDao;
	
	@Override
	public JSONArray getGuohmxAll(String sDate, String meikxxb_id, String eDate, String gongys_id) {
		Map<String,Object> shouhcmap = new HashMap<String,Object>();
		shouhcmap.put("sDate", sDate);
		shouhcmap.put("meikxxb_id", meikxxb_id);
		shouhcmap.put("eDate", eDate);
		shouhcmap.put("gongys_id", gongys_id);
		
		String[][] arrData = this.getTableDataInfo(shouhcmap);
		Report rt = new Report();
		String ArrHeader[][]=new String[1][11];
		ArrHeader[0]=new String[] {"供应商","煤矿","品种","车号","轻车时间","重车时间","票重","毛重","皮重","扣吨","净重"};
		int ArrWidth[]=new int[] {100,90,120,90,90,90,90,90,90,80,80};
		rt.setBody(new Table(arrData, 1, 0, 5, 11));
		
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
		
		rt.setTitle("过衡明细",ArrWidth);
		rt.body.setPageRows(30);
		int pageCount = rt.getPages();
		rt.setDefaultTitle(1, 3, "填报单位:国电新疆红雁池发电有限公司", Table.ALIGN_LEFT);
		rt.setDefaultTitle(4, 4,  sDate+"至"+eDate, Table.ALIGN_CENTER);
		
		Date currtime = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String dayriq = format.format(currtime);
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "打印日期:" + dayriq,
				Table.ALIGN_LEFT);
		rt.setDefautlFooter(3, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(5, 1, "制表:", Table.ALIGN_LEFT);
		
		rt.body.mergeFixedCols();
		rt.body.mergeFixedRow();
		for(int i = 2; i< rt.body.getRows() ; i++){
			rt.body.merge(i, 2, i, 6);
		}
		rt.body.merge(rt.body.getRows(), 1, rt.body.getRows(), 6);
     
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml());
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
		//List<Map<String,Object>> list = guohmxDao.getGuohmxAll(map);
		//JSONArray result = JSONArray.fromObject(list);
		//return result;
	}
	private String[][] getTableDataInfo(Map<String, Object> map) {
		List<Map<String, Object>> list = guohmxDao.getGuohmxAll(map);
		int rowsize = list.size();
		String[] column = {"GYSMC","MKMC","PZMC","CHEPH","QINGCSJ","ZHONGCSJ","PIAOZ","MAOZ","PIZ","KOUD","JINGZ"};
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
}
