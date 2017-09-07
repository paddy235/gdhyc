package com.zhiren.fuelmis.dc.service.impl.jih;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.jih.YudjhcxDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.jih.IYudjhcxService;
import com.zhiren.fuelmis.dc.utils.DrawTableUtil;

/** 
 * @author 摧枯拉朽cococa
 */
@Service
public class YudjhcxService implements IYudjhcxService {

	@Autowired
	private YudjhcxDao yudjhcxDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public String getTabelData(String diancid, String riq) {
		String curdate ="";
		if(null == riq || ""==riq){
			riq = new SimpleDateFormat("yyyy-MM").format(new Date());
		}
		curdate = riq+"-01";
		String format_curdate = riq.replace("-", "年")+"月";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("riq", curdate);
		map.put("id", diancid);
		List<Map<String, Object>> list  = yudjhcxDao.getTabelData(map);
		String[] column ={"QUANC","SUM_JHFDL","SUM_JHGRL","SUM_YJHML","SUM_YMKC","SUM_MUBKC","SUM_CGL"};
		String[][] arrData  = DrawTableUtil.Creatarr(list,column);
		Report rt = new Report();
		Table tb = new Table(arrData, 2, 0, 1, 7);
		rt.setBody(tb);
		int ArrWidth[] = null;
		String ArrHeader[][] = null;
		ArrHeader = new String[2][7];
		ArrHeader[0] = (new String[] { "单位", "计划发电量", "计划供热量", "预计耗煤量", "库存量",
				"库存量", "采购量" });
		ArrHeader[1] = (new String[] { "单位", "计划发电量", "计划供热量", "预计耗煤量", "上月末库存",
				"目标库存", "采购量" });
		ArrWidth = (new int[] { 250, 80, 80, 80, 80, 80, 80 });
//		rt.setTitle(intyear+"年"+intMonth+"月月度煤炭需求计划汇总", ArrWidth);
		rt.setTitle(format_curdate+"月度煤炭需求计划汇总", ArrWidth);
		rt.body.setWidth(ArrWidth);
		rt.setDefaultTitle(5, 3, "单位:万千瓦时、万吉焦、吨", Table.ALIGN_RIGHT);
		// rt.body.setPageRows(0);

		rt.body.ShowZero = true;
		rt.body.setWidth(ArrWidth);
		rt.body.setHeaderData(ArrHeader);
		rt.body.mergeFixedRow();// 合并行
		rt.body.mergeFixedCols();// 和并列
		rt.body.setColAlign(2, Table.ALIGN_RIGHT);
		rt.body.setColAlign(3, Table.ALIGN_RIGHT);
		rt.body.setColAlign(4, Table.ALIGN_RIGHT);
		rt.body.setColAlign(5, Table.ALIGN_RIGHT);
		rt.body.setColAlign(6, Table.ALIGN_RIGHT);
		rt.body.setColAlign(7, Table.ALIGN_RIGHT);
		rt.getPages();
		rt.body.setColAlign(1, Table.ALIGN_LEFT);

		rt.createDefautlFooter(ArrWidth);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String dayriq = format.format(date);
		
		rt.setDefautlFooter(1, 1, "打印日期:" + dayriq,
				Table.ALIGN_LEFT);
		rt.setDefautlFooter(2, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(4, 1, "制表:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(rt.footer.getCols(), 1, "(第Page/Pages页)",
				Table.ALIGN_RIGHT);
		return rt.getAllPagesHtml();
	}
	
}
