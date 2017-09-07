package com.zhiren.fuelmis.dc.service.impl.jih;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.jih.NiandRanlzfjhDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.jih.INiandRanlzfjhService;
import com.zhiren.fuelmis.dc.utils.DrawTableUtil;
@Service
public class NiandRanlzfjhService implements INiandRanlzfjhService {
	
	@Autowired
	private NiandRanlzfjhDao niandRanlzfjhDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public String getTabelData(String diancid, String year) {
		// TODO Auto-generated method stub
		String riq = "";
		if(null == year || ""==year){
			year = "";
			riq = "0001-01-01";
		}else{
			riq = year+"-01-01";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("riq", riq);
		map.put("diancid",diancid);
		List<Map<String, Object>> list  = niandRanlzfjhDao.getTabelData(map);
		String[] column ={"MK","FDL","FADCYDL","GONGDMH","FDBML","GRL","GONGRMH","GRBML","BMLHJ","MZBML","MZBMDJ","RYL","RYDJ","YZBML","YZBMDJ","QTFY",
				"RLZHBMDJ","RLZHBMDJ11","RLZHBMDJ12"};
		String[][] arrData  = DrawTableUtil.Creatarr(list,column);
		// 报表表头定义
		Report rt = new Report();
		int ArrWidth[] = null;
		String ArrHeader[][] = null;
		Table tb = new Table(arrData, 4, 0, 0, 19);
		rt.setBody(tb);

		ArrHeader = new String[4][19];
		ArrHeader[0] = (new String[] { "期别", "发电量", "发电厂<br>用电率", "供电标<br>准煤耗", "发电<br>标煤量", "供热量", "供热标<br>准煤耗", "供热<br>标煤量", "标煤量合计","煤折标煤量", "煤折<br>标煤单价", "燃油量", "燃油单价<br>不含税", "油折<br>标煤量", "油折<br>标煤单价","厂内费用","入炉综合标煤单价","备注(入炉综合标煤单价)","备注(入炉综合标煤单价)" });
		ArrHeader[1] = (new String[]  { "期别", "发电量", "发电厂<br>用电率", "供电标<br>准煤耗", "发电<br>标煤量", "供热量", "供热标<br>准煤耗","供热<br>标煤量", "标煤量合计","煤折标煤量", "煤折<br>标煤单价", "燃油量", "燃油单价<br>不含税", "油折<br>标煤量", "油折<br>标煤单价","厂内费用","入炉综合标煤单价","去年11月<br>累计完成","预测比去年<br>11月累计" });
		ArrHeader[2] = (new String[]  { "期别", "万千瓦时", "%", "克/千瓦时", "吨", "万吉焦", "千克/吉焦", "吨", "吨 ","吨", "元/吨", "吨", "元/吨", "吨", "元/吨","元","元/吨"," 元/吨","元/吨" });
		ArrHeader[3]=new String[] {"甲","1","2","3","4","5","6","7","8","9","10","11", "12","13","14","15","16","17","18"};
		ArrWidth = (new int[] { 120, 70, 60, 60, 60, 75, 60, 60, 75, 75, 60, 60, 60, 60,60, 75, 60, 75,75 });
		rt.setTitle(year+"年国电电力入炉综合标煤单价测算汇总表", ArrWidth);
		rt.body.setWidth(ArrWidth);

		rt.body.ShowZero = true;
		rt.body.setWidth(ArrWidth);
		rt.body.setHeaderData(ArrHeader);

		//	标题行合并单元格
		rt.body.mergeFixedRowCol();
		rt.getPages();
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_RIGHT);
		rt.body.setColAlign(3, Table.ALIGN_RIGHT);
		rt.body.setColAlign(4, Table.ALIGN_RIGHT);
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
		rt.body.setColAlign(17, Table.ALIGN_RIGHT);
		rt.body.setColAlign(18, Table.ALIGN_RIGHT);
		rt.body.setColAlign(19, Table.ALIGN_RIGHT);
		
		rt.createDefautlFooter(ArrWidth);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String dayriq = format.format(date);
		rt.setDefautlFooter(2, 5, "打印日期:" + dayriq,
				Table.ALIGN_LEFT);
		rt.setDefautlFooter(7, 5, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(12, 5, "制表:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(rt.footer.getCols()-1, 1, "(第Page/Pages页)",
				Table.ALIGN_RIGHT);
		return rt.getAllPagesHtml();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public JSONObject getRanlzfData(Map<String, Object> map) {
		List list = niandRanlzfjhDao.getRanlzfData(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{hashMap.get("ID"),hashMap.get("ROWNUM"),hashMap.get("ZAFMC"),
					hashMap.get("YUCJE"),hashMap.get("YUCSM"),hashMap.get("SHNYJWCJE"),hashMap.get("SHIJWCJE"),
					hashMap.get("YUJWCJE"),hashMap.get("YUJWCSM")};  
		}

		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public JSONObject getRanlzfById(String id) {
		List list = niandRanlzfjhDao.getRanlzfById(id);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = (HashMap<String, Object>)list.get(0);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}
	@Override
	public int delRanlzfById(String id) {
		int  result;
		try{
			result = niandRanlzfjhDao.delRanlzfById(id);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int addRanlzfData(Map<String, Object> map) {
		int  result;
		try{
			result = niandRanlzfjhDao.addRanlzfData(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int updateRanlzfById(Map<String, Object> map) {
		int  result;
		try{
			result = niandRanlzfjhDao.updateRanlzfById(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int CopyRanlzfData(Map<String, Object> map) {
		int  result;
		try{
			result = niandRanlzfjhDao.CopyRanlzfData(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int DelRanlzfByDiancidAndRiq(Map<String, Object> map) {
		int  result;
		try{
			result = niandRanlzfjhDao.DelRanlzfByDiancidAndRiq(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getRanlzfByDiancidAndRiq(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return niandRanlzfjhDao.getRanlzfByDiancidAndRiq(map);
	}
}
