package com.zhiren.fuelmis.dc.service.impl.jih;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.jih.NiandcaigjhDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.jih.INiandcaigjhService;
import com.zhiren.fuelmis.dc.utils.DrawTableUtil;
@Service
public class NiandcaigjhService implements INiandcaigjhService {

	@Autowired
	private NiandcaigjhDao niandcaigjhDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public String getTabelData(String diancid,String year){
		String riq = "";
		if(null == year || ""==year){
			year ="";
			riq = "0001-01-01";
		}else{
			riq = year+"-01-01";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("riq", riq);
		map.put("id",diancid);
		List<Map<String, Object>> list = niandcaigjhDao.getTabelData(map);
		//按照表格顺序，并且列名大写
		String[] column ={"MK","GONGYS","JIHKJ","DAOHL","REZ","CHEBJ","YUNF","CNFY","DAOCJ","DAOCBMJ","DAOCBMJBHS","RLZHBMDJ"};
		String[][] arrData  = DrawTableUtil.Creatarr(list,column);
		// 报表表头定义
		Report rt = new Report();
		int ArrWidth[] = null;
		String ArrHeader[][] = null;
		Table tb = new Table(arrData, 3, 0, 1, 12);
		rt.setBody(tb);
		ArrHeader = new String[3][12];
		ArrHeader[0] = (new String[] { "单位", "供货单位","计划口径", "<br>到货量", "<br>到货热值", "<br>煤价<br>", "<br>运价<br>", "<br>杂费", "到厂价<br>(含税)", "到厂标煤单价", "到厂标煤单价", "入炉综合标煤单价"});
		ArrHeader[1] = (new String[] { "单位", "供货单位","计划口径", "<br>到货量", "<br>到货热值", "<br>煤价<br>", "<br>运价<br>", "<br>杂费", "到厂价<br>(含税)", "含税", "不含税", "入炉综合标煤单价"});
		ArrHeader[2] = (new String[] { "单位", "供货单位","计划口径", "吨", "兆焦/千克", "元/吨 ", "元/吨","元/吨 ", "元/吨", "元/吨 ", "元/吨", "元/吨 " });
		ArrWidth = (new int[] { 100, 200,70, 70, 70, 70, 70, 70, 70, 70, 70,70});
		rt.setTitle( year+"年煤炭采购计划及主要指标预测情况", ArrWidth);
		rt.body.setWidth(ArrWidth);

		rt.body.ShowZero = true;
		rt.body.setWidth(ArrWidth);
		rt.body.setHeaderData(ArrHeader);

		rt.body.mergeFixedRowCol();
		rt.body.mergeCol(12);
 		rt.getPages();
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		rt.body.setColAlign(4, Table.ALIGN_RIGHT);
		rt.body.setColAlign(5, Table.ALIGN_RIGHT);
		rt.body.setColAlign(6, Table.ALIGN_RIGHT);
		rt.body.setColAlign(7, Table.ALIGN_RIGHT);
		rt.body.setColAlign(8, Table.ALIGN_RIGHT);
		rt.body.setColAlign(9, Table.ALIGN_RIGHT);
		rt.body.setColAlign(10, Table.ALIGN_RIGHT);
		rt.body.setColAlign(11, Table.ALIGN_RIGHT);
		rt.body.setColAlign(12, Table.ALIGN_CENTER);
		
		rt.createDefautlFooter(ArrWidth);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String dayriq = format.format(date);
		rt.setDefautlFooter(1, 2, "打印日期:" + dayriq,
				Table.ALIGN_LEFT);
		rt.setDefautlFooter(3, 2, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(5, 1, "制表:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(rt.footer.getCols(), 1, "(第Page/Pages页)",
				Table.ALIGN_RIGHT);
		return rt.getAllPagesHtml();
	}
	@Override
	public int delNiandcaigById(String id) {
		int  result;
		try{
			result = niandcaigjhDao.delNiandcaigById(id);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int addCaigData(Map<String, Object> map) {
		int  result;
		try{
			result = niandcaigjhDao.addCaigData(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public JSONObject getNiandcaigData(Map<String, Object> map) {
		List list = niandcaigjhDao.getNiandcaigData(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{hashMap.get("ID"),hashMap.get("ROWNUM"),
					hashMap.get("GONGYSB_ID"),hashMap.get("JIHKJB_ID"),hashMap.get("HET_SL"),hashMap.get("HET_REZ"),hashMap.get("HET_MEIJ"),
					hashMap.get("HET_YUNJ"),hashMap.get("JIH_SL"),hashMap.get("JIH_REZ"),hashMap.get("JIH_MEIJ"),hashMap.get("JIH_YUNJ"),hashMap.get("JIH_QIT"),
					hashMap.get("JIH_QITBHS"),hashMap.get("JIH_DAOCJ"),hashMap.get("JIH_DAOCBMDJ"),hashMap.get("DAOCBHSBMDJ")};  
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public JSONObject getNiandCaigById(String id) {
		List list = niandcaigjhDao.getNiandCaigById(id);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = (HashMap<String, Object>)list.get(0);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}
	@Override
	public int updateNiandcaigById(Map<String, Object> map) {
		int  result;
		try{
			result = niandcaigjhDao.updateNiandcaigById(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getNiandcaigByDiancidAndRiq(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return niandcaigjhDao.getNiandcaigByDiancidAndRiq(map);
	}
	@Override
	public int DelNiandcaigByDiancidAndRiq(Map<String, Object> map) {
		int  result;
		try{
			result = niandcaigjhDao.DelNiandcaigByDiancidAndRiq(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int CopyNiandcaigData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int  result;
		try{
			result = niandcaigjhDao.CopyNiandcaigData(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getGongys() {
		return niandcaigjhDao.getGongys();
	}
	

}
