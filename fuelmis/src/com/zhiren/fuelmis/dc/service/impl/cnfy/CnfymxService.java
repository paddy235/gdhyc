package com.zhiren.fuelmis.dc.service.impl.cnfy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.cnfy.CnfymxDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.cnfy.ICnfymxService;
import com.zhiren.fuelmis.dc.utils.DrawTableUtil;
@Service
public class CnfymxService implements ICnfymxService {
	
	@Autowired
	private CnfymxDao cnfymxDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public String getTabelData(Map<String, Object> map) {
		String riq = "";
		if(null == map.get("riq") || ""==map.get("riq")){
			riq = new SimpleDateFormat("yyyy-MM").format(new Date());
//			map.put("riq", riq+"-01");
		}
		String format_curdate = riq.replace("-", "年")+"月";
		List<Map<String, Object>> list  = cnfymxDao.getTabelData(map);
		String[] column = {"BIANH","ZONGJE","MINGC","JINE"};
		String[][] arrData =  DrawTableUtil.Creatarr(list,column);
		
		//报表表头定义
				Report rt = new Report();
				int ArrWidth[] = null;
				String ArrHeader[][] = null;
				String arrFormat[]=null;
				ArrHeader=new String[1][4];
				 ArrHeader[0]=new String[] {"单据编号","合计(元)","费用名称","金额"};
				 
				 ArrWidth=new int[] {200,120,120,120};
//				 iFixedRows=1;
				 arrFormat=new String[]{"","0.00","0.00","0.00"};
			
				// 数据
				 Table tb = new Table(arrData, 1, 0, 0, 4);
				rt.setBody(tb);
				
				rt.setTitle(format_curdate+"厂内费用明细", ArrWidth);
				
				rt.body.setWidth(ArrWidth);
				rt.body.setHeaderData(ArrHeader);// 表头数据
				rt.body.ShowZero = true;
				rt.body.setColFormat(arrFormat);
			 
				rt.body.setColAlign(1, Table.ALIGN_CENTER);
				
				//页脚 
				 rt.createDefautlFooter(ArrWidth);
				 
				return rt.getAllPagesHtml();
	}
}
