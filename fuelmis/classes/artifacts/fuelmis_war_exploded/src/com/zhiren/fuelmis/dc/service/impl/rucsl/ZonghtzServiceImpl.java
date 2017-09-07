package com.zhiren.fuelmis.dc.service.impl.rucsl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.rucsl.ZonghtzDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.rucsl.ZonghtService;

import net.sf.json.JSONArray;

@Service("zonghtzService")
public class ZonghtzServiceImpl implements ZonghtService{

	@Autowired
	private ZonghtzDao zonghtzDao;
	
	
	/**
	 * 构建报表数据
	 * @param map
	 * @return
	 */
	private String[][] getTabelDataInfo(Map<String, Object> map) {
		List<Map<String, Object>> list = zonghtzDao.getTabelData(map);
		int rowsize = list.size();
		
		//MEIKDW,DAOHRQ,MINGC,BIANM,JINGZ,QNET_AR,MT, , AAR, VAR,STAR, ,QNETAR,VDAF,JIESJG,JIESJE, HANSBD
		
		String[] column = {"MEIKDW","DAOHRQ","MINGC","BIANM","JINGZ","QNET_AR","MT","MAD","AAR","AD","VAR","STAR","STD","QNETAR","VDAF","FCAD","FCD","JIESJG","JIESJE","HANSBD"};
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
	public JSONArray getTabelData(String strdate,String enddate,String diancid,String meik,String pinzid,String type,String xiax,String shangx) {
		
		Map<String,Object> shouhcmap = new HashMap<String,Object>();
		shouhcmap.put("strdate", strdate);
		shouhcmap.put("diancid", diancid);
		shouhcmap.put("enddate", enddate);
		
		shouhcmap.put("meik","-1".equals(meik)?null:meik);
		shouhcmap.put("pinzid","-1".equals(pinzid)?null:pinzid);
		shouhcmap.put("diancid","-1".equals(diancid)?null:diancid);
		shouhcmap.put("xiax", xiax);
		shouhcmap.put("shangx", shangx);
		shouhcmap.put("type", type);
		
		String[][] arrData = this.getTabelDataInfo(shouhcmap);
		Report rt = new Report();
		String ArrHeader[][]=new String[1][17];
		ArrHeader[0]=new String[] {"矿别","入厂煤日期","品种","来样编号","入厂煤量(吨)","收到基低位热量(Kcal/Kg,Qnet,ar)","收到基水分(%)Mt","干基水(%)MAD","收到基灰(%)Aar","干基灰(%)Ad","收到基挥发分(%)Var","收到基硫(%)St,ar","干基硫(%)Std",
					"收到基低位热值<br/>Qnet,ar(MJ/kg)","干燥无灰基挥发分Vdaf(%)","固定碳Fcad","固定碳Fcd","结算单价","总金额","含税标单"};
		int ArrWidth[]=new int[] {100,90,90,90,90,90,90,90,90,80,80,70,80,80,80,80,80,80,80,80};
		rt.setBody(new Table(arrData, 1, 0, 1, 20));
		
		rt.body.setHeaderData(ArrHeader);
		rt.body.setWidth(ArrWidth);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
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
		rt.setTitle("数量、化验统计台账",ArrWidth);
		rt.body.setPageRows(40);
		int pageCount = rt.getPages();		
		rt.setDefaultTitle(1, 3, "卸煤日期"+strdate+"至"+enddate, Table.ALIGN_LEFT);
		
		
		Date currtime = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String dayriq = format.format(currtime);
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 3, "打印日期:" + dayriq,
				Table.ALIGN_LEFT);
		rt.setDefautlFooter(4, 3, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(7, 2, "制表:", Table.ALIGN_LEFT);

		
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
