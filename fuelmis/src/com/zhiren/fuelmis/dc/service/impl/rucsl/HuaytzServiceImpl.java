package com.zhiren.fuelmis.dc.service.impl.rucsl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.rucsl.HuaytzDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.rucsl.HuaytzService;
import com.zhiren.fuelmis.dc.utils.formular.Huayz;
import com.zhiren.fuelmis.dc.utils.formular.Result;

@Service("huaytzService")
public class HuaytzServiceImpl implements HuaytzService{

	@Autowired
	private HuaytzDao huaytzDao;
	
	/**
	 * 构建报表数据
	 * @param map
	 * @return
	 */
	private String[][] getTabelDataInfo(Map<String, Object> map) {
		List<Map<String, Object>> list = huaytzDao.getTabelData(map);
		int rowsize = list.size();
		String[] column = {"FHDW","MKDW","DAOHRQ","PZ","FZ","JINGZ","CHES","MT","MAD","AAD","AD","AAR","VAD","VDAF","QBAD","FARL","QBAR","SDAF","STAD","STD","STAR","HDAF","HAD","FCAD","QGRD"};
		int colsize = column.length;
		String[][] tablevalue = new String[rowsize][colsize];
		Object middlevalue;
		for(int m = 0 ; m<list.size(); m++){
			HashMap<String, Object> HashMap = (HashMap<String, Object>) list.get(m);
			Huayz.round_new(HashMap);
			for(int n = 0; n<colsize; n++){
				middlevalue = HashMap.get(column[n]);
				if(null == middlevalue){
					tablevalue[m][n] = "";
				}else{
					if(n>6&&n!=7&&n!=14&&n!=15&&n!=16&&n!=24){
						tablevalue[m][n] = String.valueOf(HashMap.get(column[n]));
					}else{
						tablevalue[m][n] = String.valueOf(HashMap.get(column[n]));
					}
				}
			}
		}
		return tablevalue;
	}
	
	
	@Override
	public JSONArray getTabelData(String strdate,String enddate,String diancid,String yunsfs,String pinzid) {
				
		Map<String,Object> shouhcmap = new HashMap<String,Object>();
		shouhcmap.put("strdate", strdate);
		shouhcmap.put("diancid", "-1".equals(diancid)?null:diancid);
		shouhcmap.put("enddate",enddate);
		shouhcmap.put("pinzid", "-1".equals(pinzid)?null:pinzid);		
		shouhcmap.put("yunsfs","-1".equals(yunsfs)?null:yunsfs);
		
		String[][] arrData = this.getTabelDataInfo(shouhcmap);
		Report rt = new Report();
		String ArrHeader[][]=new String[1][25];
		ArrHeader[0]=new String[] {"发货单位","煤矿单位","到货日期","品种","发站","质检数量(吨)","车数","全水分(%)Mt","空气干燥基水分(%)Mad","空气干燥基灰分(%)Aad","干燥基灰分(%)Ad","收到基灰分(%)Aar","空气干燥基挥发分(%)Vad",
				"干燥无灰基挥发分(%)Vdaf","弹筒发热量(J/g)Qb,ad","收到基低位发热量(J/g)Qnet,ar","收到基低位热值(Kcal/Kg)","干燥无灰基硫Sdaf","空气干燥基硫(%)St,ad","干燥基全硫(%)St,d","收到基全硫(%)St,ar",
				"干燥无灰基氢(%)Hdaf","空气干燥基氢(%)Had","固定碳(%)FC,ad","干基高位热(Kcal/Kg)Qgr,d"};
		int ArrWidth[]=new int[] {100,90,90,90,90,90,90,90,90,80,80};
		rt.setBody(new Table(arrData, 1, 0, 5, 25));
		
		rt.body.setHeaderData(ArrHeader);
		rt.body.setWidth(ArrWidth);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
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
		rt.body.setColAlign(23, Table.ALIGN_RIGHT);
		rt.body.setColAlign(24, Table.ALIGN_RIGHT);
		rt.setTitle("煤质检验台账",ArrWidth);
		rt.body.setPageRows(15);
		int pageCount = rt.getPages();
		rt.setDefaultTitle(1, 3, "卸煤日期:"+strdate+"至"+enddate, Table.ALIGN_LEFT);
		
		Date currtime = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String dayriq = format.format(currtime);
		rt.createDefautlFooter(ArrWidth);
		
		rt.setDefautlFooter(1, 3, "打印日期:" + dayriq,
				Table.ALIGN_LEFT);
		rt.setDefautlFooter(4, 3, "主管：", Table.ALIGN_LEFT);
		rt.setDefautlFooter(7, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(9, 3, "制表:", Table.ALIGN_LEFT);
		
		rt.body.mergeFixedRow();
		rt.body.mergeFixedCols();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml());
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		
		
		return jsonArray;
	}

	@Override
	public JSONArray getTabelDataByRiq(Map<String, Object> map) {
		List<Map<String, Object>> list = huaytzDao.getTabelDataByRiq(map);
		String[][] arrData = Result.list2array(list,new String[]{"DAOHRQ","JINGZ","QNET_AR","MT","AAR","VAR","STAR","QNETAR","VDAF"});
		Report rt = new Report();
		String ArrHeader[][]=new String[1][9];
		ArrHeader[0]=new String[] {"到货日期","净重","收到基低位热值(Kcal/Kg)Qnet,ar","全水分(%)Mt","收到基灰分(%)Aar","收到基挥发份(Var)","收到基全硫(%)St,ar","收到基低位发热量(Mj/Kg)Qnet,ar","干燥无灰基挥发分(%)Vdaf"};

		int ArrWidth[]=new int[] {100,90,90,90,90,90,90,90,90};
		rt.setBody(new Table(arrData, 1, 0, 5, 9));

		rt.body.setHeaderData(ArrHeader);
		rt.body.setWidth(ArrWidth);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(6, Table.ALIGN_RIGHT);
		rt.body.setColAlign(7, Table.ALIGN_RIGHT);
		rt.body.setColAlign(8, Table.ALIGN_RIGHT);
		rt.body.setColAlign(9, Table.ALIGN_RIGHT);

		rt.setTitle("煤质检验台账",ArrWidth);
		rt.body.setPageRows(38);
		int pageCount = rt.getPages();

		rt.setDefaultTitle(1, 3, "卸煤日期:"+map.get("strdate").toString()+"至"+map.get("enddate").toString(), Table.ALIGN_LEFT);

		Date currtime = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String dayriq = format.format(currtime);
		rt.createDefautlFooter(ArrWidth);

		rt.setDefautlFooter(1, 3, "打印日期:" + dayriq,
				Table.ALIGN_LEFT);
		rt.setDefautlFooter(4, 3, "主管：", Table.ALIGN_LEFT);
		rt.setDefautlFooter(7, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(9, 3, "制表:", Table.ALIGN_LEFT);

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
