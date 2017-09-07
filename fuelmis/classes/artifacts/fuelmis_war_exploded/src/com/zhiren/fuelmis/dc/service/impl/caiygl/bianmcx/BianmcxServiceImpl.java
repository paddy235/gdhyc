package com.zhiren.fuelmis.dc.service.impl.caiygl.bianmcx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.caiygl.bianmcx.BianmcxDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.caiygl.bianmcx.BianmcxService;

@Service("bianmcxService")
public class BianmcxServiceImpl implements BianmcxService {

	@Autowired
	private BianmcxDao bianmcxDao;

	/**
	 * 构建报表数据
	 * 
	 * @param map
	 * @return
	 */
	private String[][] getTabelDataInfo(Map<String, Object> map) {
		try {
			List<Map<String, Object>> list = bianmcxDao.getTabelData(map);
			int rowsize = list.size();
			String[] column = { "CAIYBM", "ZHIYBM" };
			int colsize = column.length;
			String[][] tablevalue = new String[rowsize][colsize];
			Object middlevalue;
			for (int m = 0; m < list.size(); m++) {
				HashMap<String, Object> HashMap = (HashMap<String, Object>) list
						.get(m);
				for (int n = 0; n < colsize; n++) {
					middlevalue = HashMap.get(column[n]);
					if (null == middlevalue) {
						tablevalue[m][n] = "";
					} else {
						tablevalue[m][n] = String.valueOf(HashMap.get(column[n]));
					}
				}
			}
			return tablevalue;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}

	@Override
	public String getTabelData(String date) {

		Map<String, Object> shouhcmap = new HashMap<String, Object>();
		shouhcmap.put("date", date);

		String[][] arrData = this.getTabelDataInfo(shouhcmap);
		Report rt = new Report();
		String ArrHeader[][] = new String[1][2];
		ArrHeader[0] = new String[] { "采样编码", "制样编码" };
		int ArrWidth[] = new int[] { 100, 90, 90, 90, 90, 90, 90, 90, 90, 80,
				80 };
		rt.setBody(new Table(arrData, 1, 0, 0, 2));

		rt.body.setHeaderData(ArrHeader);
		rt.body.setWidth(ArrWidth);
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.setTitle("采样编码单", ArrWidth);

		rt.body.mergeFixedRow();
		rt.body.mergeFixedCols();
		return rt.getAllPagesHtml();
	}

}
