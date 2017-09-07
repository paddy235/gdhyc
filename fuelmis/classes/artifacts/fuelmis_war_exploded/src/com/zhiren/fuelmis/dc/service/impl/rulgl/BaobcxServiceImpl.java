package com.zhiren.fuelmis.dc.service.impl.rulgl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.rulgl.BaobcxDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.rulgl.IBaobcxService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.DrawTableUtil;
import com.zhiren.fuelmis.dc.utils.DateUtil.DateFormatType;

/** 
 * @author 陈宝露
 */
@Service
public class BaobcxServiceImpl implements IBaobcxService { 

	@Autowired
	private BaobcxDao baobcxDao;
	
	@Override
	public JSONArray getRulhyd(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = baobcxDao.getRulhyd(map);
		String[][] arrData = new String[1][25];
		if(list!=null&&list.size()>0){
			int j = 0;
			Iterator<String> it = list.get(0).keySet().iterator();
			while (it.hasNext()) {
				arrData[0][j++] = list.get(0).get(it.next()).toString();
			}
		}
		
		Report rt = new Report();
		String[][] ArrHeader = new String[20][6];
		ArrHeader[0] = new String[] { "入炉日期",arrData[0][0], "录入时间", arrData[0][1], "分析日期",arrData[0][2]};
		ArrHeader[1] = new String[] { "煤耗用",arrData[0][3] , "班组信息",arrData[0][4] , "机组信息",arrData[0][5]};
		ArrHeader[2] = new String[] { "录入人员",arrData[0][6],arrData[0][6],arrData[0][6],arrData[0][6],arrData[0][6]};
		ArrHeader[3] = new String[] { "全水分Mt(%)", "全水分Mt(%)","全水分Mt(%)", arrData[0][7], "备注", "备注" };
		ArrHeader[4] = new String[] { "空气干燥基水分Mad(%)", "空气干燥基水分Mad(%)","空气干燥基水分Mad(%)", arrData[0][8], "", "" };
		ArrHeader[5] = new String[] { "空气干燥基灰分Aad(%)", "空气干燥基灰分Aad(%)","空气干燥基灰分Aad(%)", arrData[0][9], "", "" };
		ArrHeader[6] = new String[] { "收到基灰分Aar(%)", "收到基灰分Aar(%)","收到基灰分Aar(%)", arrData[0][9], "", "" };
		ArrHeader[6] = new String[] { "收到基灰分Aar(%)", "收到基灰分Aar(%)","收到基灰分Aar(%)", arrData[0][10], "", "" };
		ArrHeader[7] = new String[] { "干燥基灰分Ad(%)", "干燥基灰分Ad(%)","干燥基灰分Ad(%)", arrData[0][11], "0", "0" };
		ArrHeader[8] = new String[] { "空气干燥基挥发分Vad(%)","空气干燥基挥发分Vad(%)", "空气干燥基挥发分Vad(%)",arrData[0][12], "", "" };
		ArrHeader[9] = new String[] { "干燥无灰基挥发分Vdaf(%)","干燥无灰基挥发分Vdaf(%)", "干燥无灰基挥发分Vdaf(%)",arrData[0][13], "", "" };
		ArrHeader[10] = new String[] { "空气干燥基全硫St,ad(%)","空气干燥基全硫St,ad(%)", "空气干燥基全硫St,ad(%)",arrData[0][14], "", "" };
		ArrHeader[11] = new String[] { "干燥基全硫St,d(%)", "干燥基全硫St,d(%)","干燥基全硫St,d(%)", arrData[0][15], "", "" };
		ArrHeader[12] = new String[] { "收到基全硫St,ar(%)","收到基全硫St,ar(%)", "收到基全硫St,ar(%)",arrData[0][16], "", "" };
		ArrHeader[13] = new String[] { "空气干燥基氢Had(%)", "空气干燥基氢Had(%)","空气干燥基氢Had(%)", arrData[0][17], "", "" };
		ArrHeader[14] = new String[] { "收到基氢Har(%)", "收到基氢Har(%)","收到基氢Har(%)", arrData[0][18], "", "" };
		ArrHeader[15] = new String[] { "空气干燥基弹筒热值Qb,ad(J/g)","空气干燥基弹筒热值Qb,ad(J/g)", "空气干燥基弹筒热值Qb,ad(J/g)",arrData[0][19], "", "" };
		ArrHeader[16] = new String[] { "干燥基高位热值Qgr,d(J/g)","干燥基高位热值Qgr,d(J/g)", "干燥基高位热值Qgr,d(J/g)",arrData[0][20], "", "" };
		ArrHeader[17] = new String[] { "空气干燥基高位热值Qgr,ad(J/g)","空气干燥基高位热值Qgr,ad(J/g)", "空气干燥基高位热值Qgr,ad(J/g)",arrData[0][21], "", "" };
		ArrHeader[18] = new String[] { "干燥无灰基高位热值Qgr,daf(J/g)","干燥无灰基高位热值Qgr,daf(J/g)", "干燥无灰基高位热值Qgr,daf(J/g)",arrData[0][22], "", "" };
		ArrHeader[19] = new String[] { "收到基低位热值Qnet,ar(J/g)","收到基低位热值Qnet,ar(J/g)", "收到基低位热值Qnet,ar(J/g)",arrData[0][23],arrData[0][24] + "(千卡/千克)", arrData[0][24] + "(千卡/千克)" };
		int[] ArrWidth = new int[] { 100, 95, 95, 155, 95, 95 };
		rt.setTitle("入  炉  煤  质  报  告", ArrWidth);
		rt.title.setRowCells(2, Table.PER_FONTSIZE, 20);
		rt.title.setRowHeight(2, 40);
		rt.title.setRowCells(2, Table.PER_VALIGN, Table.VALIGN_TOP);
		rt.setBody(new Table(20, 6));
		rt.body.setWidth(ArrWidth);
		String[][] ArrHeader1 = new String[1][6];
		ArrHeader1[0] = ArrHeader[0];
		rt.body.setHeaderData(ArrHeader1);// 表头数据
		for (int i = 1; i < 20; i++) {
			for (int j = 0; j < 6; j++) {
				if (ArrHeader[i][j] == null || ArrHeader[i][j].length() == 0) {
					ArrHeader[i][j] = "0";
				}
				rt.body.setCellValue(i + 1, j + 1, ArrHeader[i][j]);
			}
		}
		for (int i = 1; i <= 20; i++) {
			rt.body.setRowCells(i, Table.PER_FONTSIZE, 9);
		}
		rt.body.setCellValue(4, 4, rt.body.format(rt.body.getCellValue(4, 4),
				"0.0"));
		for (int i = 5; i < 16; i++) {
			rt.body.setCellValue(i, 4, rt.body.format(rt.body
					.getCellValue(i, 4), "0.00"));
		}
		for (int i = 16; i < 21; i++) {
			rt.body.setCellValue(i, 4, rt.body.format(rt.body
					.getCellValue(i, 4), "0"));
		}
		rt.body.setCellFontSize(4, 0, 9);
		rt.body.setCells(2, 1, 20, 6, Table.PER_ALIGN, Table.ALIGN_CENTER);
		rt.body.merge(2, 1, 20, 3);
		rt.body.merge(2, 5, 20, 6);
		rt.body.merge(3, 2, 3, 6);
		rt.body.ShowZero = false;
		rt.body.setRowHeight(43);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	@Override
	public JSONArray getRuljzbb(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = baobcxDao.getRuljzbb(map);
		String[][] arrData = DrawTableUtil.Creatarr(list, new String[]{"DIANC","RLRQ","MEIL","MT","MAD","AAD","AD","AAR",
				"VAD","VDAF","QBAD","QNET_AR","QNET_AR1","STAD","STD","STAR","HDAF","HAD","FCAD"});
		
		Report rt = new Report();
		String[][] ArrHeader = new String[][]{{"单位","入炉日期","煤量<br>(吨)", "全水分<br>(%)<br>MT",
			"空气干燥基水分<br>(%)<br>Mad","空气干燥基灰分<br>(%)<br>Aad","干燥基灰分<br>(%)<br>Ad","收到基灰分<br>(%)<br>Aar",
			"空气干燥基挥发分<br>(%)<br>Vad","干燥无灰基挥发分<br>(%)<br>Vdaf","弹筒发热量<br>(J/g)<br>Qb,ad",
			"收到基低位发热量<br>(J/g)<br>Qnet,ar","收到基低位发热量<br>(Kcal/kg)<br>Qnet,ar","全硫分<br>(%)<br>St,ad",
			"干燥基硫<br>(%)<br>St,d","收到基硫<br>(%)<br>St,ar","干燥无灰基氢<br>(%)<br>Hdaf","空干基氢<br>(%)<br>Had",
			"固定碳<br>(%)<br>Fcad"}};
		int[] ArrWidth = new int[] {100,70,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65};
		rt.setTitle(map.get("sDate").toString()+"至"+map.get("eDate")+"入炉煤质量报表", ArrWidth);
		rt.title.setRowCells(2, Table.PER_VALIGN, Table.VALIGN_TOP);
		rt.title.setRowCells(2, Table.PER_FONTSIZE, 19);
		rt.setDefaultTitle(1, 5, "制表单位：" + ((Diancxx)map.get("danwmc")).getQuanc(),
				Table.ALIGN_LEFT);
		rt.setDefaultTitle(6, 4, "报表日期：" + DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE),
				Table.ALIGN_LEFT);
		rt.setDefaultTitle(11, 3, "单位：吨、%", Table.ALIGN_RIGHT);

		rt.setBody(new Table(arrData, 1, 0 ,0, 19));
		rt.body.setHeaderData(ArrHeader);
		rt.body.mergeFixedRow();
		rt.body.mergeFixedCols();
		rt.body.ShowZero = true;
		rt.createFooter(1,ArrWidth);
		rt.setDefautlFooter(1, 5, "打印日期：" + DateUtil.format(new Date(),DateFormatType.SIMPLE_CN_TYPE),
				Table.ALIGN_LEFT);
		rt.setDefautlFooter(6, 4, "审核：", Table.ALIGN_LEFT);
		rt.setDefautlFooter(11, 3, "制表：", Table.ALIGN_LEFT);
		rt.footer.setRowCells(1, Table.PER_VALIGN, Table.VALIGN_TOP);
		for(int i=3;i<=19;i++){
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}
		rt.body.setPageRows(15);
		int pageCount = rt.getPages();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	@Override
	public JSONArray getRuljztz(Map<String, Object> map) {
		List<Map<String, Object>> list = baobcxDao.getRuljztz(map);
		String[][] arrData = DrawTableUtil.Creatarr(list, new String[] {
				"RLRQ", "BANZ", "JIZ", "FENXRQ", "MEIL", "MT", "MAD", "AAD",
				"AD", "AAR", "VAD", "VDAF", "VAR", "QBAD", "QNET_AR",
				"QNET_AR1", "STAD", "STD", "STAR", "HDAF", "HAD", "FCAD",
				"QGRAD","QGRAD_DAF","SDAF","HAR","QGRD","HUAYBH" });

		Report rt = new Report();
		String[][] ArrHeader = new String[][] { { "入炉日期", "班组", "机组", "分析日期",
				"煤量<br>(吨)", "全水分<br>(%)<br>Mt", "空气干燥基水分<br>(%)<br>Mad",
				"空气干燥基灰分<br>(%)<br>Aad", "干燥基灰分<br>(%)<br>Ad",
				"收到基灰分<br>(%)<br>Aar", "空气干燥基挥发分<br>(%)<br>Vad",
				"干燥无灰基挥发分<br>(%)<br>Vdaf", "收到基挥发分<br>(%)<br>Var",
				"弹筒发热量<br>(J/g)<br>Qb,ad", "收到基低位发热量<br>(J/g)<br>Qnet,ar",
				"收到基低位发热量<br>(Kcal/kg)<br>Qnet,ar", "全硫分<br>(%)<br>St,ad",
				"干燥基硫<br>(%)<br>St,d", "收到基硫<br>(%)<br>St,ar",
				"干燥无灰基氢<br>(%)<br>Hdaf", "空干基氢<br>(%)<br>Had",
				"固定碳<br>(%)<br>Fcad", "空气干燥基高位热值<br/>(Mj/kg)<br/>QGRAD", 
				"干燥无灰基高位热值<br/>(Mj/kg)<br/>QGRAD_DAF", "干燥无灰基全硫<br/>(%)<br/>SDAF", 
				"HAR", "干燥基高位热值(Mj/kg)<br/>QGRD", "化验编号" } };
		int[] ArrWidth = new int[] { 70, 70, 70, 70, 65, 65, 65, 65, 65, 65,
				65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65,
				65, 80 };
		rt.setBody(new Table(arrData, 1, 0, 0, 28));
		rt.setTitle(map.get("sDate").toString() + "至" + map.get("eDate")
				+ "入炉煤质量台账", ArrWidth);
		rt.title.setRowHeight(2, 50);
		rt.title.setRowCells(2, Table.PER_VALIGN, Table.VALIGN_TOP);
		rt.title.setRowCells(2, Table.PER_FONTSIZE, 19);
		rt.setDefaultTitle(1, 6,
				"制表单位：" + ((Diancxx) map.get("danwmc")).getQuanc(),
				Table.ALIGN_LEFT);
		rt.setDefaultTitle( 9, 5,
				"报表日期：" + DateUtil.format(new Date(), DateUtil.DateFormatType.SIMPLE_TYPE),
				Table.ALIGN_CENTER);
		rt.setDefaultTitle(19, 2, "单位：吨、%", Table.ALIGN_RIGHT);
		rt.body.setPageRows(15);
		rt.body.setHeaderData(ArrHeader);// 表头数据
		rt.body.mergeFixedRow();
		rt.body.mergeFixedCols();
		rt.body.ShowZero = true;
		rt.createFooter(1, ArrWidth);
		rt.setDefautlFooter( 1, 6, "打印日期："
						+ DateUtil.format(new Date(),
								DateFormatType.SIMPLE_CN_TYPE),
				Table.ALIGN_LEFT);
		rt.setDefautlFooter(9, 5, "审核：", Table.ALIGN_CENTER);
		rt.setDefautlFooter(19, 2, "制表：", Table.ALIGN_RIGHT);
		rt.footer.setRowCells(1, Table.PER_VALIGN, Table.VALIGN_TOP);
		for (int i = 5; i <= 21; i++) {
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}
		int pageCount = rt.getPages();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	@Override
	public JSONArray getRucrlrzc(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<LinkedHashMap<String, Object>> list = baobcxDao.getRucrlrzc(map);
		String[][] arrData = new String[list.size()][19];
		for (int i = 0; i < list.size(); i++) {
			int j = 0;
			Iterator<String> it = list.get(i).keySet().iterator();
			while (it.hasNext()) {
				arrData[i][j++] = list.get(i).get(it.next()).toString();
			}
		}
		
		Report rt = new Report();
		String[][] ArrHeader = new String[3][19];
		int[] ArrWidth = new int[] { 80,60,70,70,60,60,60,60,60,70,70,70,60,60,60,60,60,60,60};
		ArrHeader[0] = new String[] { "日期", "入厂", "入厂","入厂","入厂","入厂","入厂","入厂","入厂","入炉","入炉","入炉","入炉","入炉","入炉","入炉","入炉","热值差","热值差"};
		ArrHeader[1] = new String[] { "日期", "数量", "热值","热值","水分","硫份","硫份","灰份","挥发份","数量","热值","热值","水分","硫份","硫份","灰份","挥发份","热值差","热值差"};
		ArrHeader[2] = new String[] { "日期", "(吨)", "(MJ/Kg)","(Kcal/Kg)","Mt(%)","St,d(%)","St,ad(%)","Ad(%)","Vdaf(%)","(吨)","(MJ/Kg)","(Kcal/Kg)","Mt(%)","St,d(%)","St,ad(%)","Ad(%)","Vdaf(%)","(MJ/Kg)","(Kcal/Kg)"};
		rt.setBody(new Table(arrData, 3, 0, 0, 19));
		rt.body.setHeaderData(ArrHeader);
		rt.setTitle("入 厂 入 炉 热 值 差", ArrWidth);
		rt.title.setRowHeight(2, 50);
		rt.title.setRowCells(2, Table.PER_FONTSIZE, 19);
		rt.title.setRowCells(2, Table.PER_VALIGN, Table.VALIGN_TOP);
		rt.setDefaultTitle(1, 5, "制表单位:" + ((Diancxx)map.get("danwmc")).getQuanc(),Table.ALIGN_LEFT);
		rt.setDefaultTitle(11, 3, "单位:(吨)" ,Table.ALIGN_RIGHT);
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 13, "打印日期:"+DateUtil.format(new Date(),DateFormatType.SIMPLE_CN_TYPE),Table.ALIGN_RIGHT);
		rt.body.setPageRows(15);
		rt.body.mergeFixedCols();
		rt.body.mergeFixedRow();
		rt.body.ShowZero=true;
		for(int i=2;i<=19;i++){
			rt.body.setColAlign(i, Table.ALIGN_RIGHT);
		}
		int pageCount = rt.getPages();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	@Override
	public JSONArray getSIS_shujcx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<LinkedHashMap<String, Object>> list = baobcxDao.getSIS_shujcx(map);
		String[][] arrData = new String[list.size()][3];
		for (int i = 0; i < list.size(); i++) {
			int j = 0;
			Iterator<String> it = list.get(i).keySet().iterator();
			while (it.hasNext()) {
				arrData[i][j++] = list.get(i).get(it.next()).toString();
			}
		}
		
		Report rt = new Report();
		String[][] ArrHeader =new String[1][3];
		ArrHeader[0]=new String[] {"时间","位置","耗煤总量累计"};
		int[] ArrWidth=new int[] {160,140,140};
		rt.setBody(new Table(arrData, 1, 0, 0, 3));
		rt.setTitle("sis日传输数据", ArrWidth);
		rt.body.setWidth(ArrWidth);
		rt.body.setPageRows(15);
		rt.body.setHeaderData(ArrHeader);
		rt.body.mergeFixedRow();
		rt.body.mergeFixedCols();
		rt.body.ShowZero = true;
		int pageCount = rt.getPages();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

}
