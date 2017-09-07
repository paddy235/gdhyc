package com.zhiren.fuelmis.dc.service.impl.hetgl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.gddl.dao.hetgl.ChaxdyDao2;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.hetgl.ChaxdyDao;

import com.zhiren.fuelmis.dc.report.Cell;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.hetgl.IChaxdyService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.PropertiesUtil;

/** 
 * @author 陈宝露
 */
@Service
public class ChaxdyServiceImpl implements IChaxdyService {

	@Autowired
	private ChaxdyDao chaxdyDao;
	
	@Autowired
	private ChaxdyDao2 chaxdyDao2;
	
	@Override
	public JSONArray getHetcx(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<LinkedHashMap<String, Object>> list = chaxdyDao.getHetcx(map);
		String[][] arrData = new String[list.size()][9];
		for (int i = 0; i < list.size(); i++) {
			LinkedHashMap<String, Object> rt = list.get(i);
			arrData[i][0] = rt.get("XUFDWMC")==null?"":rt.get("XUFDWMC").toString();
			arrData[i][1] = rt.get("GONGFDWMC")==null?"":rt.get("GONGFDWMC").toString();
			arrData[i][2] = rt.get("HETBH")==null?"":rt.get("HETBH").toString();
			arrData[i][3] = rt.get("HETL")==null?"":rt.get("HETL").toString();
			arrData[i][4] = rt.get("MEIJ")==null?"":rt.get("MEIJ").toString();
			arrData[i][5] = rt.get("QIANDRQ")==null?"":rt.get("QIANDRQ").toString();
			arrData[i][6] = rt.get("QISRQ")==null?"":rt.get("QISRQ").toString();
			arrData[i][7] = rt.get("GUOQRQ")==null?"":rt.get("GUOQRQ").toString();
			arrData[i][8] = rt.get("JIHKJ")==null?"":rt.get("JIHKJ").toString();
		}
		Report rt = new Report();
		String[][] ArrHeader = new String[1][13];
		ArrHeader[0] = new String[] { "需方单位", "供方单位","合同号",  "数量<br>(吨)",
				 "煤价<br>(元/吨)", "签订日期", "起始日期","截止日期","合同类型"};
		int[] ArrWidth = new int[] { 70, 300, 180, 70, 70, 70, 70, 70, 70};
		rt.setBody(new Table(arrData, 1, 0, 0, 9));
		rt.body.setHeaderData(ArrHeader);
		rt.body.setPageRows(15);
		int pageCount = rt.getPages();
		rt.body.ShowZero = false;
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setColAlign(2, Table.ALIGN_CENTER);
		rt.body.setColAlign(3, Table.ALIGN_CENTER);
		rt.body.setColAlign(4, Table.ALIGN_RIGHT);
		rt.body.setColAlign(5, Table.ALIGN_RIGHT);
		rt.body.setColAlign(6, Table.ALIGN_CENTER);
		rt.body.setColAlign(7, Table.ALIGN_CENTER);
		rt.body.setColAlign(8, Table.ALIGN_CENTER);
		rt.body.setColAlign(9, Table.ALIGN_CENTER);
		rt.setTitle("合   同   查   询", ArrWidth);
		rt.title.setRowCells(2, Table.PER_FONTSIZE, 19);
		rt.title.setRowCells(2, Table.PER_VALIGN, Table.VALIGN_TOP);
		rt.body.setWidth(ArrWidth);
		rt.createDefautlFooter(ArrWidth);
		rt.setDefautlFooter(1, 8, "打印日期:" + DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_CN_TYPE),
				Table.ALIGN_RIGHT);

		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	@Override
	public List<Map<String, Object>> getHetbh(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list_old = chaxdyDao2.getHetbh(map);
		List<Map<String, Object>> list_new = chaxdyDao.getHetbh(map);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(int i=0;i<list_old.size();i++){
			list.add(list_old.get(i));
		}
		for(int i=0;i<list_new.size();i++){
			list.add(list_new.get(i));
		}
		
		return list;
	}

	@Override
	public JSONArray getPingsyjb(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String hetbh = "";
		String qiandrq="";
		String gongfdw = "";
		String jiag = "";
		String nianf  = "";
		String liuc_id = "";
		
		String JBRY="";
		String SHRY="";
		String BMZGSH="";
		String BMZGSHYJ="";
		String BMZGSHRQ="";
		String RLBSHYJ="";
		String RLBSHRY="";
		String RLBSHRQ="";
		String CWSHYJ="";
		String CWSHRY="";
		String CWSHRQ="";
		String JCSHYJ="";
		String JCSHRY="";
		String JCZG="";
		String JCSHRQ="";
		String LDYJ="";
		String LDQM="";
		String SHRQ="";
		String ZJLYJ="";
		String ZJLQM="";
		String ZJLRQ="";
		
		List<Map<String, Object>> hetxx = chaxdyDao.getHetxx(map);
		if(hetxx.size()==0){
			hetxx = chaxdyDao2.getHetxx(map);
			hetbh = hetxx.get(0).get("HETBH").toString();
			gongfdw = hetxx.get(0).get("GONGFDWMC").toString();
			jiag = "一票到厂含税价："+hetxx.get(0).get("JIJ").toString();
			nianf = hetxx.get(0).get("QIANDRQ").toString().substring(0,4);
			
			
			List<LinkedHashMap<String,Object>> pingsxx = chaxdyDao2.getPingsxx(map);
			String[][] qianm=new String[pingsxx.size()][2];
			for(int i=0;i<pingsxx.size();i++){
				qianm[i][0] = pingsxx.get(i).get("SHIJ").toString();
				qianm[i][1] = pingsxx.get(i).get("CAORY").toString();
			}
			
			if(qianm.length>1){
				JBRY=getPic(qianm[0][1]);
				qiandrq=qianm[0][0];
			}
			if(qianm.length>2){
				SHRY=getPic(qianm[1][1]);
			}
			if(qianm.length>3){
				BMZGSHYJ=getPic(qianm[2][1]+"_qm");
				BMZGSH=getPic(qianm[2][1]);
				BMZGSHRQ=qianm[2][0];
			}
			
			if(qianm.length>4){
				RLBSHYJ=getPic(qianm[3][1]+"_qm");
				RLBSHRY=getPic(qianm[3][1]);
				RLBSHRQ=qianm[3][0];
			}
			if(qianm.length>5){
				CWSHYJ=getPic(qianm[4][1]+"_qm");
				CWSHRY=getPic(qianm[4][1]);
				CWSHRQ=qianm[4][0];
			}
			if(qianm.length>6){
				JCSHRY=getPic(qianm[5][1]);
				JCSHRQ=qianm[5][0];
			}

			if(qianm.length>7){
				JCSHYJ=getPic(qianm[6][1]+"_qm");
				JCZG=getPic(qianm[6][1]);
				LDYJ=getPic(qianm[7][1]+"_qm");
				LDQM=getPic(qianm[7][1]);
				SHRQ=qianm[7][0];
			}
			
			if(qianm.length>8){
				ZJLYJ=getPic(qianm[8][1]+"_qm");
				ZJLQM=getPic(qianm[8][1]);
				ZJLRQ=qianm[8][0];
			}
		}else{
			hetbh = hetxx.get(0).get("HETBH").toString();
			gongfdw = hetxx.get(0).get("QUANC").toString();
			jiag = "一票到厂含税价："+hetxx.get(0).get("JIAG").toString();
			nianf = hetxx.get(0).get("QIANDRQ").toString().substring(0,4);
			liuc_id = hetxx.get(0).get("LIUC_ID")==null?"": hetxx.get(0).get("LIUC_ID").toString();
			
			//调用webService获取审批信息
			try {
				String sanjurl = PropertiesUtil.getValue("sanjsp_url");
				sanjurl +="/services/HuiqService?wsdl";
				Client client = new Client(new URL(sanjurl));
				//传入流程id
				String[] parms={liuc_id};
				Object[] r = client.invoke("Huiq",parms);
				JSONArray jsonArray = JSONArray.fromObject(r[0].toString());
				if(jsonArray!=null&&jsonArray.size()>0){
					String[][] qianm=new String[jsonArray.size()][2];
					for(int i=0;i<jsonArray.size();i++){
						JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i));
						qianm[i][0] = jsonObject.get("time").toString();
						qianm[i][1] = jsonObject.get("name").toString();
					}
					
					if(qianm.length>1){
						JBRY=getPic(qianm[0][1]);
						qiandrq=qianm[0][0];
					}
					if(qianm.length>2){
						SHRY=getPic(qianm[1][1]);
					}
					if(qianm.length>3){
						BMZGSHYJ=getPic(qianm[2][1]+"_qm");
						BMZGSH=getPic(qianm[2][1]);
						BMZGSHRQ=qianm[2][0];
					}
					
					if(qianm.length>4){
						RLBSHYJ=getPic(qianm[3][1]+"_qm");
						RLBSHRY=getPic(qianm[3][1]);
						RLBSHRQ=qianm[3][0];
					}
					if(qianm.length>5){
						CWSHYJ=getPic(qianm[4][1]+"_qm");
						CWSHRY=getPic(qianm[4][1]);
						CWSHRQ=qianm[4][0];
					}
					if(qianm.length>6){
						JCSHRY=getPic(qianm[5][1]);
						JCSHRQ=qianm[5][0];
					}

					if(qianm.length>7){
						JCSHYJ=getPic(qianm[6][1]+"_qm");
						JCZG=getPic(qianm[6][1]);
						LDYJ=getPic(qianm[7][1]+"_qm");
						LDQM=getPic(qianm[7][1]);
						SHRQ=qianm[7][0];
					}
					
					if(qianm.length>8){
						ZJLYJ=getPic(qianm[8][1]+"_qm");
						ZJLQM=getPic(qianm[8][1]);
						ZJLRQ=qianm[8][0];
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String EL1 = nianf+"年煤炭供需合同";
		String EL2 = hetbh;
		String EL3 = gongfdw;
		String EL4 = jiag;
		
		String ArrHeader[][]=new String[23][11];
		ArrHeader[0]=new String[] {"合同（协议）名称","",EL1,"","","","","合同编号",EL2,"",""};
		ArrHeader[1]=new String[] {"承包商/供应商","",EL3,"","","","","","","",""};
		ArrHeader[2]=new String[] {"概算范围（元）","","","","","","合同金额（元）","",EL4,"",""};
		ArrHeader[3]=new String[] {"承&nbsp办","计划营销部","","&nbsp审核人：",SHRY,"","","&nbsp经办人：",JBRY,"",""};
		ArrHeader[4]=new String[] {"部&nbsp门","","","","","","","","","",""};
		ArrHeader[5]=new String[] {"项&nbsp目","列支燃料费用","","","","","","","","",""};
		ArrHeader[6]=new String[] {"说&nbsp明","","","","","","","","","",""};
		ArrHeader[7]=new String[] {"承&nbsp办","","","","","","","","","",""};
		ArrHeader[8]=new String[] {"说&nbsp明","","","","","","","","","",""};
		ArrHeader[9]=new String[] {"需参加评审部门","","","","","","","","","",""};
		ArrHeader[10]=new String[] {"&nbsp&nbsp&nbsp&nbsp&nbsp■计划营销部&nbsp&nbsp&nbsp&nbsp&nbsp■财务产权部&nbsp&nbsp&nbsp&nbsp&nbsp■监察审计部&nbsp&nbsp&nbsp&nbsp&nbsp■燃料管理部","","","","","","","","","",""};
		ArrHeader[11]=new String[] {"","审核意见：","",BMZGSHYJ,"","","","","","",""};
		ArrHeader[12]=new String[] {"","部门：计划营销部：","","审核人：","","","部门主管：",BMZGSH,"","日期:"+BMZGSHRQ,""};
		ArrHeader[13]=new String[] {"评","审核意见： ","",RLBSHYJ,"","","","","","",""};
		ArrHeader[14]=new String[] {"审","部门：燃料管理部：","","审核人：","","","部门主管：",RLBSHRY,"","日期:"+RLBSHRQ,""};
		ArrHeader[15]=new String[] {"意","审核意见：","",CWSHYJ,"","","","","","",""};
		ArrHeader[16]=new String[] {"见","部门：财务产权部：","","审核人：","","","部门主管：",CWSHRY,"","日期:"+CWSHRQ,""};
		ArrHeader[17]=new String[] {"","审核意见：","",JCSHYJ,"","","","","","",""};
		ArrHeader[18]=new String[] {"","部门：监察审计部：","","审核人：",JCSHRY,"","部门主管：",JCZG,"","日期:"+JCSHRQ,""};
		ArrHeader[19]=new String[] {"承办部门分管领导意见","审核意见： ",LDYJ,"","签名：",LDQM,"","","","日期："+SHRQ,""};
		ArrHeader[20]=new String[] {"","","","","","","","","","",""};
		ArrHeader[21]=new String[] {"总经理审批","审核意见： ",ZJLYJ,"","签名：",ZJLQM,"","","","日期："+ZJLRQ,""};
		ArrHeader[22]=new String[] {"","","","","","","","","","",""};
		int ArrWidth[]=new int[] {45,70,54,70,54,54,80,60,54,70,70};
		
		Report rt=new Report();
		Cell c = new Cell();
		c.setBorderNone();
		Table title = new Table(4, ArrWidth.length, c);
		title.setWidth(ArrWidth);
		title.setBorderNone();
		title.setCellValue(2, 1, "国电新疆红雁池发电有限公司");
		title.setCellAlign(2, 1, Table.ALIGN_CENTER);
		title.setCellFont(2, 1, "", 16, true);
		title.mergeRowCells(2);
		
		title.setCellValue(3, 1, "<br><u>合同评审意见表</u><br>");
		title.setCellAlign(3, 1, Table.ALIGN_CENTER);
		title.setCellFont(3, 1,"宋体", 11, true);
		title.mergeRowCells(3);
		
		title.setCellValue(4, 1, "日期:"+qiandrq);
		title.setCellAlign(4, 1, Table.ALIGN_RIGHT);
		title.setCellFont(4, 1, "", 10, false);
		title.mergeRowCells(4);

		rt.setTitle(title);			
		rt.setBody(new Table(ArrHeader,0,0,0));
		//rt.body.setWidth(ArrWidth);

		rt.body.mergeCell(1,1,1,2);
		rt.body.mergeCell(1,3,1,7);
		rt.body.mergeCell(1,9,1,11);
		rt.body.mergeCell(2,1,2,2);
		rt.body.mergeCell(2,3,2,11);
		rt.body.mergeCell(3,1,3,2);
		rt.body.mergeCell(3,3,3,6);
		rt.body.mergeCell(3,7,3,8);
		rt.body.mergeCell(3,9,3,11);
		rt.body.mergeCell(4,2,5,3);
		rt.body.mergeCell(4,4,5,4);
		rt.body.mergeCell(4,5,5,7);
		rt.body.mergeCell(4,8,5,8);
		rt.body.mergeCell(4,9,5,11);
		rt.body.mergeCell(6,2,7,11);
		rt.body.mergeCell(8,2,9,11);
		rt.body.mergeCell(10,1,10,11);
		rt.body.mergeCell(11,1,11,11);
		
		rt.body.mergeCell(12,2,12,3);
		rt.body.mergeCell(12,4,12,11);
		
		rt.body.mergeCell(13,2,13,3);
		rt.body.mergeCell(13,5,13,6);
		rt.body.mergeCell(13,8,13,9);
		rt.body.mergeCell(13,10,13,11);
		
		rt.body.mergeCell(14,2,14,3);
		rt.body.mergeCell(14,4,14,11);
		
		rt.body.mergeCell(15,2,15,3);
		rt.body.mergeCell(15,5,15,6);
		rt.body.mergeCell(15,8,15,9);
		rt.body.mergeCell(15,10,15,11);
		
		rt.body.mergeCell(16,2,16,3);
		rt.body.mergeCell(16,4,16,11);
		
		rt.body.mergeCell(17,2,17,3);
		rt.body.mergeCell(17,5,17,6);
		rt.body.mergeCell(17,8,17,9);
		rt.body.mergeCell(17,10,17,11);
	
		rt.body.mergeCell(18,2,18,3);
		rt.body.mergeCell(18,4,18,11);

		rt.body.mergeCell(19,2,19,3);
		rt.body.mergeCell(19,5,19,6);
		rt.body.mergeCell(19,8,19,9);
		rt.body.mergeCell(19,10,19,11);
		
		rt.body.mergeCell(20,3,20,4);
		rt.body.mergeCell(20,6,20,9);
		rt.body.mergeCell(20,10,20,11);
		
		rt.body.mergeCell(20,1,21,1);
		rt.body.mergeCell(20,2,21,2);
		rt.body.mergeCell(20,3,21,4);
		rt.body.mergeCell(20,5,21,5);
		rt.body.mergeCell(20,6,21,9);
		rt.body.mergeCell(20,10,21,11);
		
		rt.body.mergeCell(22,3,22,4);
		rt.body.mergeCell(22,6,22,9);
		rt.body.mergeCell(22,10,22,11);
		
		rt.body.setRowHeight(45);

		rt.body.setRowHeight(4,20);
		rt.body.setRowHeight(5,20);
		rt.body.setRowHeight(6,20);
		rt.body.setRowHeight(7,20);
		rt.body.setRowHeight(8,20);
		rt.body.setRowHeight(9,20);
		rt.body.setRowHeight(10,20);
		rt.body.setRowHeight(20,80);
		rt.body.setRowHeight(22,70);
		rt.body.setRowHeight(23,1);

		rt.body.setCellBorderbottom(4, 1, 0);
		rt.body.setCellBorderbottom(6, 1, 0);
		rt.body.setCellBorderbottom(8, 1, 0);
		rt.body.setCellBorderbottom(12, 1, 0);
		rt.body.setCellBorderbottom(13, 1, 0);
		rt.body.setCellBorderbottom(14, 1, 0);
		rt.body.setCellBorderbottom(15, 1, 0);
		rt.body.setCellBorderbottom(16, 1, 0);
		rt.body.setCellBorderbottom(17, 1, 0);
		rt.body.setCellBorderbottom(18, 1, 0);
		
		rt.body.setCellBorderRight(12, 2, 0);
		rt.body.setCellBorderRight(13, 4, 0);
		rt.body.setCellBorderRight(13, 7, 0);
		rt.body.setCellBorderRight(14, 2, 0);
		rt.body.setCellBorderRight(15, 4, 0);
		rt.body.setCellBorderRight(15, 7, 0);
		rt.body.setCellBorderRight(16, 2, 0);
		rt.body.setCellBorderRight(17, 4, 0);
		rt.body.setCellBorderRight(17, 7, 0);
		rt.body.setCellBorderRight(18, 2, 0);
		rt.body.setCellBorderRight(19, 4, 0);
		rt.body.setCellBorderRight(19, 7, 0);
		
		rt.body.setCellBorderRight(20, 2, 0);
		rt.body.setCellBorderRight(20, 3, 0);
		rt.body.setCellBorderRight(20, 4, 0);
		rt.body.setCellBorderRight(20, 5, 0);
		rt.body.setCellBorderRight(20, 6, 0);
		rt.body.setCellBorderRight(20, 7, 0);
		rt.body.setCellBorderRight(20, 8, 0);
		rt.body.setCellBorderRight(20, 9, 0);
	
		rt.body.setCellBorderbottom(22, 1, 0);
		rt.body.setCellBorderRight(22, 2, 0);
		rt.body.setCellBorderbottom(22, 2, 0);
		rt.body.setCellBorderRight(22, 3, 0);
		rt.body.setCellBorderbottom(22, 3, 0);
		rt.body.setCellBorderRight(22, 4, 0);
		rt.body.setCellBorderbottom(22, 4, 0);
		rt.body.setCellBorderRight(22, 5, 0);
		rt.body.setCellBorderbottom(22, 5, 0);
		rt.body.setCellBorderRight(22, 6, 0);
		rt.body.setCellBorderbottom(22, 6, 0);
		rt.body.setCellBorderRight(22, 7, 0);
		rt.body.setCellBorderbottom(22, 7, 0);
		rt.body.setCellBorderRight(22, 8, 0);
		rt.body.setCellBorderbottom(22, 8, 0);
		rt.body.setCellBorderRight(22, 9, 0);
		rt.body.setCellBorderbottom(22, 9, 0);
		rt.body.setCellBorderbottom(22, 10, 0);
		
		rt.body.setCellBorderRight(23, 2, 0);
		rt.body.setCellBorderRight(23, 2, 0);
		rt.body.setCellBorderRight(23,3, 0);
		rt.body.setCellBorderRight(23, 4, 0);
		rt.body.setCellBorderRight(23, 5, 0);
		rt.body.setCellBorderRight(23, 6, 0);
		rt.body.setCellBorderRight(23, 7, 0);
		rt.body.setCellBorderRight(23, 8, 0);
		rt.body.setCellBorderRight(23, 9, 0);
		rt.body.setCellBorderRight(23, 10, 0);
		
		rt.body.setColAlign(1, Table.ALIGN_CENTER);
		rt.body.setCellAlign(1, 3, Table.ALIGN_CENTER);
		rt.body.setCellAlign(3, 7, Table.ALIGN_CENTER);
		rt.body.setCellAlign(4, 2, Table.ALIGN_CENTER);
		rt.body.setCellAlign(11, 1, Table.ALIGN_LEFT);
		
		rt.body.setCellAlign(13, 4, Table.ALIGN_RIGHT);
		rt.body.setCellAlign(15, 4, Table.ALIGN_RIGHT);
		rt.body.setCellAlign(17, 4, Table.ALIGN_RIGHT);
		rt.body.setCellAlign(19, 4, Table.ALIGN_RIGHT);
		
		rt.body.setCellAlign(13, 7, Table.ALIGN_RIGHT);
		rt.body.setCellAlign(15, 7, Table.ALIGN_RIGHT);
		rt.body.setCellAlign(17, 7, Table.ALIGN_RIGHT);
		rt.body.setCellAlign(19, 7, Table.ALIGN_RIGHT);

		rt.body.setCellFont(1, 3, "", 12, false);
		rt.body.setCellFont(2, 3, "", 12, false);
		rt.body.setCellFont(6, 2, "", 12, false);
			
		rt.createFooter(4,ArrWidth);
		 
		rt.footer.setCellValue(1, 1, "说明: 1、承办部门必须填写全部表头，并将应评审计部门打■");
		rt.footer.setCellAlign(1, 1, Table.ALIGN_LEFT);
		rt.footer.mergeCell(1, 1, 1, 10);
		
		rt.footer.setCellValue(2, 1, "&nbsp&nbsp&nbsp&nbsp&nbsp 2、承办部门主管和承办人栏，必须签字，不得打印，评审栏不需承办部门再次填写");
		rt.footer.setCellAlign(2, 1, Table.ALIGN_LEFT);
		rt.footer.mergeCell(2, 1, 2, 10);
		
		rt.footer.setCellValue(3, 1, "&nbsp&nbsp&nbsp&nbsp&nbsp 3、评审部门名称由各部门填写，除监察审计部为最终评审外，其他部门顺序不限");
		rt.footer.setCellAlign(3, 1, Table.ALIGN_LEFT);
		rt.footer.mergeCell(3, 1, 3, 10);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("html", rt.getAllPagesHtml());
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}
	
	private String getPic(String name){
		return "<image src='/fuelmis/images/dsqm/"+name+".gif"+"' width=\"100\" height=\"40\" align=\"left\"/>";
	}
}
