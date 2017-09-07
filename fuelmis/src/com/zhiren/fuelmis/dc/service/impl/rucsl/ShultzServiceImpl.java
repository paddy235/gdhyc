package com.zhiren.fuelmis.dc.service.impl.rucsl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.rucsl.ShultzDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.rucsl.ShultzService;

@Service("shultzService")
public class ShultzServiceImpl implements ShultzService{

	@Autowired
	private ShultzDao shultzDao;
	
	/**
	 * 构建报表数据
	 * @param map
	 * @return
	 */
/*	private String[][] getTableDataInfo(Map<String, Object> map){
		List<Map<String, Object>> list = shultzDao.getBaseData(map);
		int rowsize = list.size();
		String[] column = {"GYS","MK","DAOHRQ","PZ","FZ","CHES","LAIMSL","JINGZ","BIAOZ","YUNS","YINGK","ZONGKD"};
		int colsize = column.length;
		String[][] tablevalue = new String[rowsize][colsize];
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> m=list.get(i);
			//计算运损、赢吨、亏吨
			Double maoz=Double.parseDouble(m.get("MAOZ").toString());
			Double piz=Double.parseDouble(m.get("PIZ").toString());
			Double piaoz=Double.parseDouble(m.get("PIAOZ").toString());
			Double zongkd=Double.parseDouble(m.get("ZONGKD").toString());
			Double yingd=0.0;//赢吨
			Double kuid=0.0;//亏吨
			Double yuns=0.0;//运损
			Double zuidys=0.0;//最大运损
			Double yunsl=0.11;
			Double yingk=0.0;
			Double jingz=maoz-piz-zongkd;
			if(maoz-piz>=piaoz){
				yingd=maoz-piz-piaoz;
				kuid=0.0;
				yuns=0.0;
			}else{
				zuidys=(Double) (piaoz*yunsl);//最大运损=票重*运损率
				yingd=0.0;
				if(Math.abs(maoz-piz-piaoz)<zuidys){
					kuid=0.0;
					yuns=Math.abs(maoz-piz-piaoz);
				}else{
					yuns=zuidys;
					kuid=Math.abs(maoz-piz-piaoz)-yuns;
				}
			}
			if(jingz-piaoz>=0){
				yingk=yingd;
			}else{
				yingk=kuid+yuns;
			}
			tablevalue[i][0]=m.get("GYS").toString();
			tablevalue[i][1]=m.get("MK").toString();
			tablevalue[i][2]=m.get("DAOHRQ").toString();
			tablevalue[i][3]=m.get("PZ").toString();
			tablevalue[i][4]=m.get("FZ").toString();
			tablevalue[i][5]=m.get("CHES").toString();
			tablevalue[i][6]=m.get("LAIMSL").toString();
			tablevalue[i][7]=String .format("%.2f",jingz);
			tablevalue[i][8]=m.get("PIAOZ").toString();
			tablevalue[i][9]=String .format("%.2f",yuns);
			tablevalue[i][10]=String .format("%.2f",yingk);
			tablevalue[i][11]=String .format("%.2f",zongkd);
			
		}
		return tablevalue;
		
	}*/
	
	private String[][] getTableDataInfo(Map<String, Object> map) {
		List<Map<String, Object>> list = shultzDao.getTabelData(map);
		int rowsize = list.size();
		String[] column = {"GYS","MK","DAOHRQ","PZ","FZ","CHES","LAIMSL","JINGZ","PIAOZ","YUNS","YINGK","ZONGKD"};
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
	public JSONArray getTabelData(String strdate, String enddate, String id,String gongysid,String pinzid,String jihkj,String shijtj) {
				
		Map<String,Object> shouhcmap = new HashMap<String,Object>();
		shouhcmap.put("strdate", strdate);
		shouhcmap.put("diancid", id);
		shouhcmap.put("enddate", enddate);
		shouhcmap.put("shijtj", shijtj);
		
		shouhcmap.put("gongysid","-1".equals(gongysid)?null:gongysid);
		shouhcmap.put("pinzid","-1".equals(pinzid)?null:pinzid);
		shouhcmap.put("jihkj","-1".equals(jihkj)?null:jihkj);
		
		String[][] arrData = this.getTableDataInfo(shouhcmap);
		Report rt = new Report();
		String ArrHeader[][]=new String[1][12];
		ArrHeader[0]=new String[] {"供货单位","煤矿单位","到货日期","品种","发站","车数","实收量","净重","票重","运损","盈亏","总扣吨"};
		int ArrWidth[]=new int[] {100,90,120,90,90,90,90,90,90,80,80};
		rt.setBody(new Table(arrData, 1, 0, 5, 12));
		//rt.setBody(new Table(arrData, 0, 1, 5));
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
		
		rt.setTitle("数量台账",ArrWidth);;
		rt.body.setPageRows(30);
		int pageCount = rt.getPages();
		rt.setDefaultTitle(1, 3, "填报单位:国电新疆红雁池发电有限公司", Table.ALIGN_LEFT);
		rt.setDefaultTitle(4, 4,  strdate+"至"+enddate, Table.ALIGN_CENTER);
		
		Date currtime = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String dayriq = format.format(currtime);
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 2, "打印日期:" + dayriq,
				Table.ALIGN_LEFT);
		rt.setDefautlFooter(3, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(5, 1, "制表:", Table.ALIGN_LEFT);
		//rt.body.mergeFixedCols();
		//rt.body.mergeFixedRow();
		//rt.body.mergeFixedRowCol();
		rt.body.mergeFixedCols();
		rt.body.mergeFixedRow();
		for(int i = 2; i< rt.body.getRows() ; i++){
			rt.body.merge(i, 2, i, 6);
		}
		rt.body.merge(rt.body.getRows(), 1, rt.body.getRows(), 6);
     
      //rt.body.merge(1,2,arrData.length,5,true);
       //rt.body.merge(1,3,10,5,true);
       //rt.body.merge(1,2,arrData.length,5,true);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml());
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

}
