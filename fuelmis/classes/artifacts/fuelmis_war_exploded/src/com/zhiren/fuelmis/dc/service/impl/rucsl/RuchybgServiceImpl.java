package com.zhiren.fuelmis.dc.service.impl.rucsl;


import net.sf.json.JSONArray;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.rucsl.RuchybgDao;
import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Report2;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.report.Table2;
import com.zhiren.fuelmis.dc.service.rucsl.IRuchybgService;


/**
 * @author Wei
 *
 */
@Service("ruchybgService")
public class RuchybgServiceImpl implements IRuchybgService{

	@Autowired
	private RuchybgDao ruchybgDao;

	/**
	 * 构建报表数据
	 * @param map
	 * @return
	 */

	@Override
	public JSONArray getTabelData(Map<String,Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = ruchybgDao.getTabelData(map);
		String[][] arrData = new String[1][27];
		if(list!=null&&list.size()>0){
				arrData[0][0] = list.get(0).get("MEIKMC").toString();
				arrData[0][1] = list.get(0).get("HUAYSJ").toString();
				arrData[0][2] = list.get(0).get("HUAYBM").toString();
				arrData[0][3] = list.get(0).get("FAZ")!=null?list.get(0).get("FAZ").toString():"";
				arrData[0][4] = list.get(0).get("MEIL").toString();
				arrData[0][5] = list.get(0).get("CHES").toString();
				arrData[0][6] = list.get(0).get("ZHUANMSJ")!=null?list.get(0).get("ZHUANMSJ").toString():"";
				arrData[0][7] = list.get(0).get("HUAYY")!=null?list.get(0).get("HUAYY").toString():"";
				arrData[0][8] = list.get(0).get("CHEP")!=null?list.get(0).get("CHEP").toString():"";
				arrData[0][9] = list.get(0).get("MT").toString();
				arrData[0][10] = list.get(0).get("MAD").toString();
				arrData[0][11] = list.get(0).get("AAD").toString();
				arrData[0][12] = list.get(0).get("AAR").toString();
				arrData[0][13] = list.get(0).get("AD").toString();
				arrData[0][14] = list.get(0).get("VAD").toString();
				arrData[0][15] = list.get(0).get("VD").toString();
				arrData[0][16] = list.get(0).get("VDAF").toString();
				arrData[0][17] = list.get(0).get("STAD").toString();
				arrData[0][18] = list.get(0).get("STD").toString();
				arrData[0][19] = list.get(0).get("HAD").toString();
				arrData[0][20] = list.get(0).get("QBAD").toString();
				arrData[0][21] = list.get(0).get("QGRAD").toString();
				arrData[0][22] = list.get(0).get("QGRD").toString();
				arrData[0][23] = list.get(0).get("QNET_AR").toString();
				arrData[0][24] = list.get(0).get("QNET_ARK").toString();
				arrData[0][25] = list.get(0).get("VAR").toString();
				arrData[0][26] = list.get(0).get("STAR").toString();
		}
		
		Report2 rt = new Report2();
		//String[][] ArrHeader = new String[21][6];
		String[][] ArrHeader = new String[27][6];
		ArrHeader[0] = new String[] { "<br/><br/>国电新疆红雁池发电有限公司","<br/><br/>国电新疆红雁池发电有限公司","<br/><br/>国电新疆红雁池发电有限公司","<br/><br/>国电新疆红雁池发电有限公司","<br/><br/>国电新疆红雁池发电有限公司","<br/><br/>国电新疆红雁池发电有限公司"};
		ArrHeader[1] = new String[] { "煤质检验报告","煤质检验报告","煤质检验报告","煤质检验报告","煤质检验报告","煤质检验报告"};
		ArrHeader[2] = new String[] { "矿别",arrData[0][0], "化验日期", arrData[0][1], "编号",arrData[0][2]};
		ArrHeader[3] = new String[] { "发站",arrData[0][3] , "煤量(t)",arrData[0][4] , "车数",arrData[0][5]};
		//ArrHeader[2] = new String[] { "采样日期",arrData[0][6],"采制样人员",arrData[0][7],"化验室温度","20"};//以下数组标号未改
		ArrHeader[4] = new String[] { "采样日期",arrData[0][6],"采制样人员","-   -","化验室温度","20"};//以下数组标号未改
		ArrHeader[5] = new String[] { "车号", arrData[0][8],"","","化验室湿度","20"};
		ArrHeader[6] = new String[] { "全水分Mt(%)", "全水分Mt(%)","全水分Mt(%)", arrData[0][9], "检验所依据标准：", "" };
		ArrHeader[7] = new String[] { "空气干燥基水分Mad(%)", "空气干燥基水分Mad(%)","空气干燥基水分Mad(%)", arrData[0][10], "全水分：GB/T211-2007<br><br>工业分析：GB/T 30732-2014<br><br>全硫：GB/T 214-2007<br><br>发热量：GB/T 213-2008", "" };
		ArrHeader[8] = new String[] { "空气干燥基灰分Aad(%)", "空气干燥基灰分Aad(%)","空气干燥基灰分Aad(%)", arrData[0][11], "", "" };
		ArrHeader[9] = new String[] { "收到基灰分Aar(%)", "收到基灰分Aar(%)","收到基灰分Aar(%)", arrData[0][12], "", "" };
		ArrHeader[10] = new String[] { "干燥基灰分Ad(%)", "干燥基灰分Ad(%)","干燥基灰分Ad(%)", arrData[0][13], "", "" };
		ArrHeader[11] = new String[] { "空气干燥基挥发分Vad(%)","空气干燥基挥发分Vad(%)", "空气干燥基挥发分Vad(%)",arrData[0][14], "备注：", "" };
		
		ArrHeader[12] = new String[] { "收到基挥发分Var(%)","", "",arrData[0][25], "", "" };
		
		ArrHeader[13] = new String[] { "干燥基挥发分Vd(%)", "干燥基挥发分Vd(%)","干燥基挥发分Vd(%)", arrData[0][15], "天平：编号01<br><br>测硫仪：5E-8S/AII#073<br><br>水分仪：5E-MW6500048<br><br>工分仪：5E-MAG6700<br><br>量热仪：5E-KCIII224", "" };
		ArrHeader[14] = new String[] { "干燥无灰基挥发分Vdaf(%)","干燥无灰基挥发分Vdaf(%)", "干燥无灰基挥发分Vdaf(%)",arrData[0][16], "", "" };
		ArrHeader[15] = new String[] { "空气干燥基全硫St,ad(%)","空气干燥基全硫St,ad(%)", "空气干燥基全硫St,ad(%)",arrData[0][17], "", "" };
		ArrHeader[16] = new String[] { "干燥基全硫St,d(%)", "干燥基全硫St,d(%)","干燥基全硫St,d(%)", arrData[0][18], "", "" };
		
		ArrHeader[17] = new String[] { "收到基全硫St,ar(%)", "","", arrData[0][26], "", "" };
		
		ArrHeader[18] = new String[] { "空气干燥基氢Had(%)", "空气干燥基氢Had(%)","空气干燥基氢Had(%)", arrData[0][19], "", "" };
		ArrHeader[19] = new String[] { "弹筒热值Qb,ad(MJ/Kg)","弹筒热值Qb,ad(MJ/Kg)", "弹筒热值Qb,ad(MJ/Kg)",arrData[0][20], "", "" };
		ArrHeader[20] = new String[] { "空气干燥基高位热值Qgr,ad(MJ/Kg)","空气干燥基高位热值Qgr,ad(MJ/Kg)", "空气干燥基高位热值Qgr,ad(MJ/Kg)",arrData[0][21], "", "" };
		ArrHeader[21] = new String[] { "干燥基高位热值Qgr,d(MJ/Kg)","干燥基高位热值Qgr,d(MJ/Kg)", "干燥基高位热值Qgr,d(MJ/Kg)",arrData[0][22], "", "" };
		ArrHeader[22] = new String[] { "收到基低位热值Qnet,ar(MJ/Kg)","收到基低位热值Qnet,ar(MJ/Kg)", "收到基低位热值Qnet,ar(MJ/Kg)",arrData[0][23],"",""};
		ArrHeader[23] = new String[] { "收到基低位热值Qnet,ar(Kcal/Kg)","收到基低位热值Qnet,ar(Kcal/Kg)", "收到基低位热值Qnet,ar(Kcal/Kg)",arrData[0][24],"",""};
		ArrHeader[24] = new String[] { "注：无实验报告专用章无效,涂改、复制无效。","注：无实验报告专用章无效,涂改、复制无效。", "注：无实验报告专用章无效,涂改、复制无效。","", "", "" };
		ArrHeader[25] = new String[] { "负责人：","负责人：", "负责人：","审核：", "审核：","审核："};
		ArrHeader[26] = new String[] { "化验员："+arrData[0][7],"化验员："+arrData[0][7], "化验员："+arrData[0][7],"化验员："+arrData[0][7], "化验员："+arrData[0][7],"化验员："+arrData[0][7]};
		int[] ArrWidth = new int[] { 10, 10, 10, 10, 10, 10};
		//rt.setTitle("国电新疆红雁池发电有限公司<br>煤质检验报告", ArrWidth);
		//rt.createTitle(2, ArrWidth);
		//rt.setTitle("国电新疆红雁池发电有限公司", 1);
		//rt.setTitle("煤质检验报告", 2);
		//rt.title.setRowCells(1, Table.PER_FONTSIZE, 21);
		//rt.title.setRowCells(1, Table.PER_FONTNAME, "黑体");
		//rt.title.setRowCells(2, Table.PER_FONTSIZE, 18);
		//rt.title.setRowHeight(1, 40);
		//rt.title.setRowHeight(2, 40);
		//rt.title.setRowCells(2, Table.PER_FONTSIZE, 18);
		//rt.title.setRowHeight(2, 40);
		//rt.title.setRowCells(2, Table.PER_VALIGN, Table.VALIGN_TOP);
		rt.setBody(new Table2(27, 6));
		rt.body.setWidth(ArrWidth);
		String[][] ArrHeader1 = new String[1][6];
		ArrHeader1[0] = ArrHeader[0];
		rt.body.setHeaderData(ArrHeader1);// 表头数据
		rt.body.setBorderNone();
		for (int i = 1; i < 27; i++) {
			for (int j = 0; j < 6; j++) {
				if (ArrHeader[i][j] == null || ArrHeader[i][j].length() == 0) {
					ArrHeader[i][j] = "";
				}
				rt.body.setCellValue(i + 1, j + 1, ArrHeader[i][j]);
			}
		}
		for (int i = 1; i <= 27; i++) {
			rt.body.setRowCells(i, Table.PER_FONTSIZE, 9);
		}
//		rt.body.setCellValue(5, 4, rt.body.format(rt.body.getCellValue(5, 4),
//				"0.0"));
//		rt.body.setCellValue(5, 4, rt.body.format(rt.body.getCellValue(5, 4),
//				"0.00"));
		for (int i = 8; i < 23; i++) {
			rt.body.setCellValue(i, 4, rt.body.format(rt.body.getCellValue(i, 4), "0.00"));
		}
//		for (int i = 18; i < 23; i++) {
//			rt.body.setCellValue(i, 4, rt.body.format(rt.body
//					.getCellValue(i, 4), "0.00"));
//		}
		rt.body.setCellValue(24, 4, rt.body.format(rt.body.getCellValue(24, 4), "0"));
		rt.body.setCellValue(7, 4, rt.body.format(rt.body.getCellValue(7, 4), "0.0"));
		rt.body.setCellFontSize(4, 0, 9);
		rt.body.setCells(1, 1, 2, 6, Table.PER_ALIGN, Table.ALIGN_CENTER);
//		rt.body.setCells(3, 1, 22, 6, Table.PER_ALIGN, Table.ALIGN_LEFT);
		rt.body.setCells(3, 4, 24, 4, Table.PER_ALIGN, Table.ALIGN_CENTER);
		rt.body.setCells(4, 6, 6, 6, Table.PER_ALIGN, Table.ALIGN_CENTER);
		rt.body.setCellClassName(1, 1, "hello");
		rt.body.setCells(1, 1, 1, 6,Table.PER_BORDER_BOTTOM,0);
		rt.body.setCells(1, 1, 1, 6,Table.PER_FONTSIZE,21);
		rt.body.setCells(1, 1, 1, 6,Table.PER_FONTNAME,"黑体");
		rt.body.setCells(1, 1, 2, 6,Table.PER_BORDER_LEFT,0);
		rt.body.setCells(1, 1, 2, 6,Table.PER_BORDER_RIGHT,0);
		rt.body.setCells(1, 1, 2, 6,Table.PER_BORDER_TOP,0);
		rt.body.setCells(2, 1, 2, 6,Table.PER_FONTSIZE,18);
		//合并表头
		rt.body.merge(1, 1, 1, 6);
		rt.body.merge(2, 1, 2, 6);
		//合并表体
		rt.body.mergeCell(6, 2, 6, 4);
		for(int i=7;i<=24;i++){
			rt.body.mergeCell(i, 1, i, 3);
			rt.body.mergeCell(i, 5, i, 6);
		}	
		
//		rt.body.mergeCell(7, 5, 7, 6);
		rt.body.setCells(7, 5, 7, 6, Table.PER_ALIGN, Table.ALIGN_LEFT);
		rt.body.setCells(8, 5, 8, 6, Table.PER_ALIGN, Table.ALIGN_LEFT);
		
//		rt.body.mergeCell(8, 5, 8, 6);
//		rt.body.mergeCell(9, 5, 9, 6);
//		rt.body.mergeCell(10, 5, 10, 6);
//		rt.body.mergeCell(11, 5, 11, 6);
//		rt.body.mergeCell(12, 5, 12, 6);
//		rt.body.mergeCell(13, 5, 22, 6);
		
//		rt.body.setCells(12, 5, 12, 6,Table.PER_BORDER_BOTTOM,0);
		rt.body.setCells(12, 5, 22, 6, Table.PER_ALIGN, Table.ALIGN_LEFT);
		
		rt.body.mergeCell(7, 5, 7, 6);
//		rt.body.setCells(12, 5, 12, 6,Table.PER_BORDER_BOTTOM,0);
		rt.body.mergeCell(8, 5, 11, 6);
		
		rt.body.mergeCell(12, 5, 12, 6);
		rt.body.setCells(12, 5, 12, 6,Table.PER_BORDER_BOTTOM,0);
		rt.body.mergeCell(13, 5, 13, 6);
		rt.body.setCells(13, 5, 13, 6,Table.PER_BORDER_BOTTOM,0);
		rt.body.mergeCell(14, 5, 18, 6);
		rt.body.setCells(14, 5, 18, 6,Table.PER_BORDER_BOTTOM,0);
		rt.body.mergeCell(19, 5, 24, 6);
		
		//合并注
		rt.body.mergeCell(25, 1, 25, 6);
		//合并表尾
		rt.body.mergeCell(26, 1, 26, 3);
		rt.body.mergeCell(26, 4, 26, 6);
		rt.body.mergeCell(27, 1, 27, 6);
		//设置表尾边框
		rt.body.setCells(26, 1, 27, 6,Table.PER_BORDER_BOTTOM,0);
		rt.body.setCells(26, 1, 27, 6,Table.PER_BORDER_LEFT,0);
		rt.body.setCells(26, 1, 27, 6,Table.PER_BORDER_RIGHT,0);
		rt.body.setCells(26, 1, 27, 6,Table.PER_BORDER_TOP,0);
		//设置表体左边框
		rt.body.setCells(3, 1, 25, 1,Table.PER_BORDER_LEFT,1);
		rt.body.ShowZero = false;
		rt.body.setRowHeight(30);

 
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	@Override
	public JSONArray getHuaybh(Map<String, Object> map) {
		List<Map<String, Object>> list = ruchybgDao.getHuaybh(map);
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox("请选择", -1);
			jsonArray.add(combobox);
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("HUAYBM"), list
						.get(i).get("HUAYBM"));
				jsonArray.add(combobox);
			}
		}
		return jsonArray;
	}
	

}
