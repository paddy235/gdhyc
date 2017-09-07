package com.zhiren.fuelmis.dc.service.impl.caiygl.caiyzmd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.dao.caiygl.caiyzmd.CaiydzmDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.caiygl.caiyzmd.CaiydzmService;

@Repository("caiydzmService")
public class CaiydzmServiceImpl implements CaiydzmService{
	@Autowired
	private CaiydzmDao caiydzmDao;
	
	/**
	 * 构建报表数据
	 * @param map
	 * @return
	 */
	private String[][] getTabelDataInfo(Map<String, Object> map) {
		List<Map<String, Object>> list = caiydzmDao.getTabelData(map);
		int rowsize = list.size();
		String[] column = {"BIANM1","BIANM2","JINCBM","BIANM3"};
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
	public String getTabelData(String date) {
				
		Map<String,Object> shouhcmap = new HashMap<String,Object>();
		shouhcmap.put("date", date);
		
		String[][] arrData = this.getTabelDataInfo(shouhcmap);
		Report rt = new Report();
		String ArrHeader[][]=new String[1][4];
		ArrHeader[0]=new String[] {"煤矿名称(运输单位)","品种","进厂批次号","采样编码"};
		int ArrWidth[]=new int[] {120,90,90,90,90,90,90,90,90,80,80};
		rt.setBody(new Table(arrData, 1, 0, 0, 4));
		
		rt.body.setHeaderData(ArrHeader);
		rt.body.setWidth(ArrWidth);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.setTitle("采样编码单",ArrWidth);
		//rt.setDefaultTitle(1, 3, "卸煤日期:"+strdate+"至"+enddate, Table.ALIGN_LEFT);
				
		rt.body.mergeFixedRow();
		rt.body.mergeFixedCols();
		return rt.getAllPagesHtml();
	}

}
