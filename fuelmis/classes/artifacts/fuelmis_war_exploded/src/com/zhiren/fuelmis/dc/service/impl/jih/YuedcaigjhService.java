package com.zhiren.fuelmis.dc.service.impl.jih;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.jih.YuedcaigjhDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.jih.IYuedcaigjhService;
import com.zhiren.fuelmis.dc.utils.DrawTableUtil;
@Service
public class YuedcaigjhService implements IYuedcaigjhService {

	@Autowired
	private YuedcaigjhDao yuedcaigjhDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public String getTabelData(String diancid,String riq) {
		String curdate ="";
		if(null == riq || ""==riq){
			riq = new SimpleDateFormat("yyyy-MM").format(new Date());
		}
		curdate = riq+"-01";
		String format_curdate = riq.replace("-", "年")+"月";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("riq", curdate);
		map.put("id",diancid);
		List<Map<String, Object>> list  = yuedcaigjhDao.getTabelData(map);
		String[] column ={"MK","GONGYS","JIHKJ","DAOHL","REZ","CHEBJ","YUNF","ZAF","DAOCJ","DAOCBMJ","DAOCBMJBHS","YUCJE","RLZHBMDJ"};
		String[][] arrData  = DrawTableUtil.Creatarr(list,column);
		// 报表表头定义
		Report rt = new Report();
		int ArrWidth[] = null;
		String ArrHeader[][] = null;
		Table tb = new Table(arrData, 3, 0, 1, 13);
		rt.setBody(tb);

		ArrHeader = new String[3][13];
		ArrHeader[0] = (new String[] { "单位", "供货单位","计划口径", "到货量", "到货热值", "到货煤价<br>(含税)", "到货运价<br>(含税)", "其他费用", "到厂价", "到厂标煤单价", "到厂标煤单价", "厂内费用","入炉综合标煤单价"});
		ArrHeader[1] = (new String[] { "单位", "供货单位","计划口径", "到货量", "到货热值", "到货煤价<br>(含税)", "到货运价<br>(含税)", "其他费用", "到厂价", "含税", "不含税", "厂内费用","入炉综合标煤单价"});
		ArrHeader[2] = (new String[] { "单位", "供货单位","计划口径", "吨", "兆焦/千克", "元/吨 ", "元/吨","元/吨 ", "元/吨", "元/吨 ", "元/吨", "元","元/吨 " });
		ArrWidth = (new int[] { 100, 200,70, 70, 70, 70, 70, 70, 70, 70, 70,100,70});
		rt.setTitle( format_curdate+"煤炭采购计划及主要指标预测情况", ArrWidth);
		rt.body.setWidth(ArrWidth);

		rt.body.ShowZero = true;
		rt.body.setWidth(ArrWidth);
		rt.body.setHeaderData(ArrHeader);
		
//				标题行合并单元格
		rt.body.mergeFixedRowCol();
		rt.body.mergeCol(12);
		rt.body.mergeCol(13);
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
		rt.body.setColAlign(13, Table.ALIGN_CENTER);
		
		rt.createDefautlFooter(ArrWidth);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String dayriq = format.format(date);
		rt.setDefautlFooter(1, 2, "打印日期:" +dayriq,
				Table.ALIGN_LEFT);
		rt.setDefautlFooter(3, 4, "审核:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(7, 4, "制表:", Table.ALIGN_LEFT);
		rt.setDefautlFooter(rt.footer.getCols()-1, 1, "(第Page/Pages页)",
				Table.ALIGN_RIGHT);
		return rt.getAllPagesHtml();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public JSONObject getCaigData(Map<String, Object> map) {
		List list = yuedcaigjhDao.getCaigData(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Object[][] objs = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			HashMap<String, Object> hashMap = (HashMap<String, Object>) list.get(i);
			objs[i] = new Object[]{hashMap.get("ID"),hashMap.get("ROW_NUMBER"),hashMap.get("GONGYSB_ID"),hashMap.get("MEIKXXB_ID"),hashMap.get("JIHKJB_ID"),hashMap.get("PINZB_ID"),hashMap.get("FAZ_ID"),
					hashMap.get("JIH_SL"),hashMap.get("JIH_REZ"),hashMap.get("JIH_LIUF"),hashMap.get("JIH_HFF"),hashMap.get("JIH_MEIJ"),
					hashMap.get("JIH_MEIJBHS"),hashMap.get("JIH_YUNJ"),hashMap.get("JIH_YUNJBHS"),hashMap.get("JIH_ZAF"),hashMap.get("JIH_ZAFBHS"),
					hashMap.get("JIH_DAOCJ"),hashMap.get("JIH_DAOCJBHS"),hashMap.get("JIH_DAOCBMDJ"),hashMap.get("JIH_DAOCBMDJBHS")};  
		}
		jsonMap.put("data", objs);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}
	@Override
	public int addCaigData(Map<String, Object> map) {
		int  result;
		try{
			result = yuedcaigjhDao.addCaigData(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int delCaigById(String id) {
		int  result;
		try{
			result = yuedcaigjhDao.delCaigById(id);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int updateCaigById(Map<String, Object> map) {
		int  result;
		try{
			result = yuedcaigjhDao.updateCaigById(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public JSONObject getYuedcaigById(String id) {
		List list = yuedcaigjhDao.getYuedcaigById(id);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = (HashMap<String, Object>)list.get(0);
		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getyueducaigByDiancidAndRiq(Map<String, Object> map) {
		return yuedcaigjhDao.getyueducaigByDiancidAndRiq(map);
	}
	@Override
	public int DelYuedcaigByDiancidAndRiq(Map<String, Object> map) {
		int  result;
		try{
			result = yuedcaigjhDao.DelYuedcaigByDiancidAndRiq(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	@Override
	public int CopyYuedcaigData(Map<String, Object> map) {
		int  result;
		try{
			result = yuedcaigjhDao.CopyYuedcaigData(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getPinz() {
		return yuedcaigjhDao.getPinz();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getFaz(String riq) {
		return yuedcaigjhDao.getFaz(riq);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getGongys() {
		return yuedcaigjhDao.getGongys();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getJihkj() {
		return yuedcaigjhDao.getJihkj();
	}
}
