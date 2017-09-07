package com.zhiren.fuelmis.dc.service.impl.peim;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;













import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;












import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.service.peim.IPeimeiService;
import com.zhiren.fuelmis.dc.utils.Sequence;
import com.zhiren.fuelmis.dc.utils.Simplex;
@Service
public class Peimei implements IPeimeiService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String changeStatusSql(String tableName, String tableID){
		String sql = "UPDATE "+tableName+" SET ZHUANGT = ZHUANGT * -1 WHERE ID = " + tableID;
				return sql;
	}
	
	private String createSql4InsDy(String ID, String strDate, String MSID, 
			String Q_MB, String S_MB, String V_MB, String SHUL_MB, String QNET_AR_JG,
			String S_JG, String V_JG, String SHUL_JG, String BIAOMDJ, String ZHUANGT){
		String sql = "INSERT INTO RL_PM_RIDYB\n" +
				"  (ID,\n" + 
				"   RIQ,\n" + 
				"   MEISB_ID,\n" + 
				"   QNET_AR_MB,\n" + 
				"   S_MB,\n" + 
				"   V_MB,\n" + 
				"   SHUL_MB,\n" + 
				"   QNET_AR_JG,\n" + 
				"   S_JG,\n" + 
				"   V_JG,\n" + 
				"   SHUL_JG,\n" + 
				"   BIAOMDJ,\n" + 
				"   ZHUANGT)\n" + 
				"VALUES\n" + 
				"  ("+ID+",\n" + 
				"   '"+strDate+"',\n" + 
				"   "+MSID+",\n" + 
				"   "+Q_MB+",\n" + 
				"   "+S_MB+",\n" + 
				"   "+V_MB+",\n" + 
				"   "+SHUL_MB+",\n" + 
				"   "+QNET_AR_JG+",\n" + 
				"   "+S_JG+",\n" + 
				"   "+V_JG+",\n" + 
				"   "+SHUL_JG+",\n" + 
				"   "+BIAOMDJ+",\n" + 
				"   "+ZHUANGT+")";
		return sql;
	}
	
	private String createSql4UpDy(String ID, String strDate, String MSID, 
			String Q_MB, String S_MB, String V_MB, String SHUL_MB, String QNET_AR_JG,
			String S_JG, String V_JG, String SHUL_JG, String BIAOMDJ, String ZHUANGT){
		String sql = "UPDATE RL_PM_RIDYB\n" +
				"   SET MEISB_ID   = "+MSID+",\n" + 
				"       QNET_AR_MB = "+Q_MB+",\n" + 
				"       S_MB       = "+S_MB+",\n" + 
				"       V_MB       = "+V_MB+",\n" + 
				"       SHUL_MB    = "+SHUL_MB+",\n" + 
				"       QNET_AR_JG = "+QNET_AR_JG+",\n" + 
				"       S_JG       = "+S_JG+",\n" + 
				"       V_JG       = "+V_JG+",\n" + 
				"       SHUL_JG    = "+SHUL_JG+",\n" + 
				"       BIAOMDJ    = "+BIAOMDJ+",\n" + 
				"       ZHUANGT    = "+ZHUANGT+"\n" + 
				" WHERE ID = "+ ID;
		return sql;
	}
	
	private String createSql4InsMx(String DIAOYB_ID, String MXMEIYB_ID, String MXBIAOMDJ, 
			String MXQNET_AR, String MXS, String MXV, String MXSHUL_MAX, String MXSHUL_MIN, 
			String MXSHUL_DY, String MXCHES, String MXID){
		String sql =	"INSERT INTO RL_PM_DIAOYMX\n" +
				"  (ID,\n" + 
				"   DIAOYB_ID,\n" + 
				"   MEIYB_ID,\n" + 
				"   BIAOMDJ,\n" + 
				"   QNET_AR,\n" + 
				"   S,\n" + 
				"   V,\n" + 
				"   SHUL_MAX,\n" + 
				"   SHUL_MIN,\n" + 
				"   SHUL_DY,\n" + 
				"   CHES)\n" + 
				"VALUES\n" + 
				"  ("+MXID+",\n" + 
				"   "+DIAOYB_ID+",\n" + 
				"   "+MXMEIYB_ID+",\n" + 
				"   "+MXBIAOMDJ+",\n" + 
				"   "+MXQNET_AR+",\n" + 
				"   "+MXS+",\n" + 
				"   "+MXV+",\n" + 
				"   "+MXSHUL_MAX+",\n" + 
				"   "+MXSHUL_MIN+",\n" + 
				"   "+MXSHUL_DY+",\n" + 
				"   "+MXCHES+")";
		return sql;
	}
	
	private String createSql4UpMx(String DIAOYB_ID, String MXMEIYB_ID, String MXBIAOMDJ, String MXQNET_AR,
			String MXS, String MXV, String MXSHUL_MAX, String MXSHUL_MIN, String MXSHUL_DY, 
			String MXCHES, String MXID){
		String sql = "UPDATE RL_PM_DIAOYMX\n" +
				"   SET DIAOYB_ID = "+DIAOYB_ID+",\n" + 
				"       MEIYB_ID  = "+MXMEIYB_ID+",\n" + 
				"       BIAOMDJ   = "+MXBIAOMDJ+",\n" + 
				"       QNET_AR   = "+MXQNET_AR+",\n" + 
				"       S         = "+MXS+",\n" + 
				"       V         = "+MXV+",\n" + 
				"       SHUL_MAX  = "+MXSHUL_MAX+",\n" + 
				"       SHUL_MIN  = "+MXSHUL_MIN+",\n" + 
				"       SHUL_DY   = "+MXSHUL_DY+",\n" + 
				"       CHES      = "+MXCHES+"\n" + 
				" WHERE ID = " + MXID;
		return sql;
	}
	
	private String createSql4InsCDDy(String ID, String strDate, String MEIYB_ID,
			String DIAOYL, String QNET_AR, String S){
		String sql = "INSERT INTO RL_PM_RICDDYJH\n" +
			"    (ID, RIQ, MEIYB_ID, DIAOYL, QNET_AR, S)\n" + 
			"  VALUES\n" + 
			"    ("+ID+", '"+strDate+"', "+MEIYB_ID+", "+
			("".equals(DIAOYL)?"NULL":DIAOYL)+", "+
			("".equals(QNET_AR)?"NULL":QNET_AR)+", "+
			("".equals(S)?"NULL":S)+")";
		return sql;
	}
	
	@SuppressWarnings("unused")
	private String createSql4UpCDDy(String ID, String strDate, String MEIYB_ID,
			String DIAOYL, String QNET_AR, String S){
		String sql = "UPDATE RL_PM_RICDDYJH\n" +
			"       SET RIQ      = '"+strDate+"',\n" + 
			"           MEIYB_ID = "+MEIYB_ID+",\n" + 
			"           DIAOYL   = "+("".equals(DIAOYL)?"NULL":DIAOYL)+",\n" + 
			"           QNET_AR  = "+("".equals(QNET_AR)?"NULL":QNET_AR)+",\n" + 
			"           S        = "+("".equals(S)?"NULL":S)+"\n" + 
			"     WHERE ID = ID";
		return sql;
	}
	
	private String createSql4InsCDDyMx(String ID, String RICDDYJH_ID, String YUNSCDB_ID, 
			String DIANCXXB_ID, String DIAOYL, String CHES){
		String sql = "INSERT INTO RL_PM_RICDDYJHMX\n" +
			"  (ID, RICDDYJH_ID, YUNSCDB_ID, DIANCXXB_ID, DIAOYL, CHES)\n" + 
			"VALUES\n" + 
			"  ("+ID+", "+RICDDYJH_ID+", "+YUNSCDB_ID+", "+DIANCXXB_ID+", "+DIAOYL+", "+CHES+")";
		return sql;
	}
	
	private String createSql4UpCDDyMx(String ID, String RICDDYJH_ID, String YUNSCDB_ID, 
			String DIANCXXB_ID, String DIAOYL, String CHES){
		String sql = "UPDATE RL_PM_RICDDYJHMX\n" +
				"   SET RICDDYJH_ID = "+RICDDYJH_ID+",\n" +
				"		YUNSCDB_ID  = "+YUNSCDB_ID+",\n" + 
				"       DIANCXXB_ID = "+DIANCXXB_ID+",\n" + 
				"       DIAOYL      = "+DIAOYL+",\n" + 
				"       CHES        = "+CHES+"\n" + 
				" WHERE ID = " + ID;
		return sql;
	}
	
	private void createSqlList(List<String> lSql, String DYID, String strDate, String MSID, 
			double SHUL_MB, double Q_MB, double S_MB, double V_MB, String ZHUANGT, 
			JSONArray jsArrMeiy, double[] v) {
		String ID = DYID;
		boolean isNewMeis = false;
		if(ID == null || "0".equals(ID) || "".equals(ID)){
			ID = Sequence.nextId();
			isNewMeis = true;
		}
		String sql;
		double Qnet_ar_jg = 0.0;
		double S_JG = 0.0;
		double V_JG = 0.0;
		double SHUL_JG = 0.0;
		double BIAOMDJ = 0.0;
		//循环构建插入日调运明细表的SQL
		for (int intCounter=0 ; intCounter < jsArrMeiy.size(); intCounter++){
			JSONObject jsonMeiy = (JSONObject)jsArrMeiy.get(intCounter);
			String MXID = jsonMeiy.getString("ID");
			boolean isNewMeiy = false;
			if(MXID == null || "0".equals(MXID) || "".equals(MXID)){
				MXID = Sequence.nextId();
				isNewMeiy = true;
			}
			String strQnet_ar_MX = jsonMeiy.getString("QNET_AR");
			String strS_MX = jsonMeiy.getString("S");
			String strV_MX = jsonMeiy.getString("V");
			String strBIAOMDJ_MX = jsonMeiy.getString("BIAOMDJ");
			double Qnet_ar_MX = strQnet_ar_MX==null||"".equals(strQnet_ar_MX)||"null".equals(strQnet_ar_MX)?0.0:Double.parseDouble(strQnet_ar_MX);
			double S_MX = strS_MX==null||"".equals(strS_MX)||"null".equals(strS_MX)?0.0:Double.parseDouble(strS_MX);
			double V_MX = strV_MX==null||"".equals(strV_MX)||"null".equals(strV_MX)?0.0:Double.parseDouble(strV_MX);
			double BIAOMDJ_MX = strBIAOMDJ_MX==null||"".equals(strBIAOMDJ_MX)||"null".equals(strBIAOMDJ_MX)?0.0:Double.parseDouble(strBIAOMDJ_MX);
			double bl = v[intCounter];
			double shul_dy = BigDecimal.valueOf(SHUL_MB*bl).setScale(0,BigDecimal.ROUND_HALF_UP).doubleValue();	//调运量保留整数
			double ches = BigDecimal.valueOf(shul_dy).divide(BigDecimal.valueOf(39.0), 0, BigDecimal.ROUND_HALF_UP).doubleValue();
			Qnet_ar_jg += BigDecimal.valueOf(Qnet_ar_MX*bl).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();	
			S_JG += BigDecimal.valueOf(S_MX*bl).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			V_JG += BigDecimal.valueOf(V_MX*bl).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			SHUL_JG += shul_dy;
			BIAOMDJ += BigDecimal.valueOf(BIAOMDJ_MX*bl).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			if(isNewMeiy){
				sql = createSql4InsMx(ID, jsonMeiy.getString("MEIYB_ID"), strBIAOMDJ_MX, strQnet_ar_MX,
						strS_MX, strV_MX, jsonMeiy.getString("SHUL_MAX"), jsonMeiy.getString("SHUL_MIN"),
						String.valueOf(shul_dy), String.valueOf(ches), MXID);
			}else{
				sql = createSql4UpMx(ID, jsonMeiy.getString("MEIYB_ID"), strBIAOMDJ_MX, strQnet_ar_MX,
						strS_MX, strV_MX, jsonMeiy.getString("SHUL_MAX"), jsonMeiy.getString("SHUL_MIN"),
						String.valueOf(shul_dy), String.valueOf(ches), MXID);
			}
			lSql.add(sql);
		}
		Qnet_ar_jg = BigDecimal.valueOf(Qnet_ar_jg).setScale(0,BigDecimal.ROUND_HALF_UP).doubleValue();	//热值保留整数
		if(isNewMeis){
			sql = createSql4InsDy(ID, strDate, MSID, String.valueOf(Q_MB), String.valueOf(S_MB),
				String.valueOf(V_MB), String.valueOf(SHUL_MB), String.valueOf(Qnet_ar_jg), 
				String.valueOf(S_JG), String.valueOf(V_JG), String.valueOf(SHUL_JG), 
				String.valueOf(BIAOMDJ), "0");
		}else{
			sql = createSql4UpDy(ID, strDate, MSID, String.valueOf(Q_MB), String.valueOf(S_MB),
					String.valueOf(V_MB), String.valueOf(SHUL_MB), String.valueOf(Qnet_ar_jg), 
					String.valueOf(S_JG), String.valueOf(V_JG), String.valueOf(SHUL_JG), 
					String.valueOf(BIAOMDJ), ZHUANGT);
		}
		lSql.add(sql);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public JSONObject loadPageData(String strDate) {
		// TODO Auto-generated method stub
		String sql = "SELECT \n" +
			"       CASE WHEN D.ID IS NULL THEN 0 ELSE 1 END CHECKED,\n" + 
			"       CASE WHEN D.ID IS NULL THEN 0 ELSE D.ID END ID,\n" + 
			"       H.ID AS MSID,\n" + 
			"       H.MEISMC,\n" + 
			"       CASE WHEN D.QNET_AR_MB IS NULL THEN H.QNET_AR ELSE D.QNET_AR_MB END Q_MB,\n" + 
			"       CASE WHEN D.S_MB IS NULL THEN H.S ELSE D.S_MB END S_MB,\n" + 
			"       CASE WHEN D.V_MB IS NULL THEN H.V ELSE D.V_MB END V_MB,\n" + 
			"       ROUND(D.SHUL_MB) SHUL_MB,\n" + 
			"       ROUND(D.QNET_AR_JG,0) QNET_AR_JG,\n" + 
			"       ROUND(D.S_JG,1) S_JG,\n" + 
			"       ROUND(D.V_JG,2) V_JG,\n" + 
			"       ROUND(D.SHUL_JG,2) SHUL_JG,\n" + 
			"       ROUND(D.BIAOMDJ,2) BIAOMDJ,\n" + 
			"       D.ZHUANGT\n" + 
			"  FROM RL_PM_MEISB H\n" + 
			"  LEFT JOIN RL_PM_RIDYB D ON H.ID = D.MEISB_ID\n" + 
			"                         AND D.RIQ = \n" + 
			" '"+strDate+"'";

		List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
		Iterator i4RowData = l4RowData.iterator();
		JSONArray jsonArrData = new JSONArray();			//构建记录各行信息的JsonArray
		while (i4RowData.hasNext()) {
			Map mRow = (Map)i4RowData.next();				
			JSONObject jsORowData = new JSONObject();		//构建记录某一行数据的JsonOb
			jsORowData.put("rowData", mRow);				//加入某行的头数据，也就日调运行
			JSONArray jsArrDetails = new JSONArray();		//构建记录计算之后的日调运明细信息的JsonArray
			if(!"0".equals(mRow.get("ID"))){				//如果该调运行ID为0，则说明还没有经过计算，则没有日调运明细信息
				sql = "SELECT \n" +
			"       D.ID,\n" + 
			"       D.MEIYB_ID,\n" + 
			"       M.MEIYMC,\n" + 
			"       D.BIAOMDJ,\n" + 
			"       D.QNET_AR,\n" + 
			"       D.S,\n" + 
			"       D.V,\n" + 
			"       D.SHUL_MAX,\n" + 
			"       D.SHUL_MIN,\n" + 
			"       D.SHUL_DY,\n" + 
			"       D.CHES\n" + 
			"  FROM RL_PM_DIAOYMX D\n" + 
			"  JOIN RL_PM_MEIYB M ON D.MEIYB_ID = M.ID\n" + 
			"                    AND D.DIAOYB_ID = " + mRow.get("ID");
				List l4Details = jdbcTemplate.queryForList(sql);	//查出某一煤山行对应的明细信息list
				Iterator i4Details = l4Details.iterator();
				while (i4Details.hasNext()) {
					Map mDetails = (Map)i4Details.next();
					jsArrDetails.add(mDetails);//.put(mDetails);						//将明细循环计入明细JsonArray
				}
			}
			jsORowData.put("rowDetails", jsArrDetails);				//将JsonArray计入行JsonOb，构建出完整行信息
			jsonArrData.add(jsORowData);//.put(jsORowData);							//将完整的行信息计入整体JsonArray
		}
		JSONObject jsonOData = new JSONObject();
		jsonOData.put("data", jsonArrData);
		return jsonOData;
	}
	
	/**
	 * 设定关键因素为各煤源所占某一煤山目标数量的比例 用字母x代替
	 * 求min 标煤价  = x1*标煤价1 + x2*标煤价2 + ...
	 * 约束条件
	 * 1.		x1*硫分1 + x2*硫分2 + ... <= 煤山目标硫分
	 * 2.   	x1*煤山目标数量 + x2*0 + ...<= x1最大值 
	 * 3.		x1*0 + x2*煤山目标数量 + ...<= x2最大值 
	 * n+1. 	x1*0 + x2*0 + ... + xn*煤山目标数量 <= xn最大值
	 * n+2.		x1*1 + x2*0 + ... <= 1
	 * n+3.		x1*0 + x2*1 + ... <= 1
	 * 2n+1.	x1*0 + x2*0 + ... + xn*1 <=1
	 * 2n+2.	x1*煤山目标数量 + x2*煤山目标数量 + ... = 煤山目标数量
	 * 2n+3.	x1*热值1 + x2*热值2 + ... >= 煤山目标热值
	 * 2n+4. 	x1*煤山目标数量 + x2*0 + ...>= x1最小值 
	 * 2n+5. 	x1*0 + x2*煤山目标数量 + ...>= x2最小值 
	 * 3n+3.	x1*0 + x2*0 + ... + xn*煤山目标数量 >= xn最小值
	 * 3n+4.	x1*1 + x2*0 + ... >=0
	 * 3n+5.	x1*0 + x2*1 + ... >=0
	 * 4n+3.	x1*0 + x2*0 + ... + xn*1 >=0
	 * 综上约束条件个数为 4n+3 ，n为关键因素个数
	 * <= 的条件个数为 2n+1 个
	 * =  的条件 个数为 1个
	 * >= 的条件个数为 2n+1个
	 * @throws JSONException 
	 */
	public Simplex createSimplex(JSONArray jsArrMeiy, double S_MB, double SHUL_MB, double Q_MB) {
		int minOrmax = -1;
		int variablesNumber = 0;		//关键因素个数,即变量个数
		int constraintsNumber = 0;		//约束条件个数
		int lessConstraintsNumber = 0;	//小于等于的条件个数
		int equalConstraintsNumber = 0;	//等于的条件个数
		int greatConstraintsNumber = 0;	//大于等于的条件个数
		double[][] ConstraintsArr = null;	//约束条件的系数矩阵
		double[] variablesArr = null;		//关键因素参数
		variablesNumber = jsArrMeiy.size();//.length();
		constraintsNumber = 4 * variablesNumber + 3;
		lessConstraintsNumber = 2 * variablesNumber + 1;
		equalConstraintsNumber = 1;
		greatConstraintsNumber = 2 * variablesNumber + 1;
		variablesArr = new double[constraintsNumber];
		ConstraintsArr = new double[constraintsNumber][constraintsNumber+1];
		
		ConstraintsArr[0][variablesNumber] = S_MB;								//小于等于硫
		ConstraintsArr[2 * variablesNumber + 1][variablesNumber] = SHUL_MB;		//等于数量
		ConstraintsArr[2 * variablesNumber + 2][variablesNumber] = Q_MB;		//大于等于热值
		
		for (int intCounter=0 ; intCounter < variablesNumber; intCounter++){
			JSONObject jsonMeiy = (JSONObject)jsArrMeiy.get(intCounter);
			String strBiaomdj = jsonMeiy.getString("BIAOMDJ");//(String)mMeiy.get("BIAOMDJ");
			String strS = jsonMeiy.getString("S");//(String)mMeiy.get("S");
			String strShul_Max = jsonMeiy.getString("SHUL_MAX");//(String)mMeiy.get("SHUL_MAX");
			String strQnet_ar = jsonMeiy.getString("QNET_AR");//(String)mMeiy.get("QNET_AR");
			String strShul_Min = jsonMeiy.getString("SHUL_MIN");//(String)mMeiy.get("SHUL_MIN");
			variablesArr[intCounter] = Double.parseDouble(strBiaomdj==null?"0":strBiaomdj);		//关键因素参数数组 参数是标煤单价
			
			ConstraintsArr[0][intCounter] = Double.parseDouble(strS==null?"0":strS);				//条件数组中第0行是硫条件, 第intCounter列是当前的"关键因素"变量列
			ConstraintsArr[intCounter+1][intCounter] = SHUL_MB;									//进行小于等于最大值判断的赋值
			ConstraintsArr[intCounter+1][variablesNumber] = Double.parseDouble(strShul_Max==null?"0":strShul_Max);	//最大值
			ConstraintsArr[variablesNumber + intCounter + 1][intCounter] = 1;					//小于等于1
			ConstraintsArr[variablesNumber + intCounter + 1][variablesNumber] = 1;					//小于等于1
			ConstraintsArr[2 * variablesNumber + 1][intCounter] = SHUL_MB;					//等于煤山目标数量
			ConstraintsArr[2 * variablesNumber + 2][intCounter] = Double.parseDouble(strQnet_ar==null?"0":strQnet_ar);					//等于煤山目标数量
			ConstraintsArr[2 * variablesNumber + 2 + intCounter+1][intCounter] = SHUL_MB;									//进行大于等于最小值判断的赋值
			ConstraintsArr[2 * variablesNumber + 2 + intCounter+1][variablesNumber] = Double.parseDouble(strShul_Min==null?"0":strShul_Min);	//最小值
			ConstraintsArr[3 * variablesNumber + 2 + intCounter+1][intCounter] = 1;				//大于等于0
			ConstraintsArr[3 * variablesNumber + 2 + intCounter+1][variablesNumber] = 0;				//大于等于0
		}
		
		Simplex sp = new Simplex(minOrmax, constraintsNumber, variablesNumber, lessConstraintsNumber, equalConstraintsNumber,
				greatConstraintsNumber, ConstraintsArr, variablesArr);
		return sp;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public String calculate(String strDate, JSONObject jsonData) {
		// TODO Auto-generated method stub
		//判断超过一定的关键参数后，拒绝计算
		int intThreshold = 10;			//煤源计算阀值
		int[] flag = null;						//判定是否成功
		JSONArray jsArrMeiy = new JSONArray();
		String sql = "SELECT 0 AS ID, ID AS MEIYB_ID, QNET_AR, S, V, BIAOMDJ, SHUL_MAX, SHUL_MIN\n" +
			"  FROM RL_PM_MEIYB\n" + 
			" WHERE ZHUANGT = 1\n" + 
			"   AND QNET_AR IS NOT NULL\n" + 
			"   AND S IS NOT NULL\n" + 
			"   AND BIAOMDJ IS NOT NULL\n" + 
			"   AND SHUL_MAX IS NOT NULL\n" + 
			"   AND SHUL_MIN IS NOT NULL";

		List lMeiy = jdbcTemplate.queryForList(sql);
		if(lMeiy.size() > intThreshold){
			return "您的版本不支持煤源超过10个的线性规划，请进行付费升级！";
		}
		Iterator i4Meiy = lMeiy.iterator();
		while (i4Meiy.hasNext()) {
			Map mMeiy = (Map)i4Meiy.next();
			jsArrMeiy.add(mMeiy);						//将明细循环计入明细JsonArray
		}	
		JSONArray jsonArr = jsonData.getJSONArray("data");
		for(int i=0; i<jsonArr.size(); i++){
			List<String> lSql = new ArrayList<String>();
			JSONObject jsonRowData = ((JSONObject) jsonArr.get(i)).getJSONObject("rowData");
			JSONArray jsonArrDetails = ((JSONObject) jsonArr.get(i)).getJSONArray("rowDetails");
			String ID = jsonRowData.getString("ID");
			String MSID = jsonRowData.getString("MSID");
			String strSHUL_MB = jsonRowData.getString("SHUL_MB");
			if(strSHUL_MB==null||"".equals(strSHUL_MB)||"null".equals(strSHUL_MB)){
				return "未填入目标煤量，请填入后重新计算！";
			}
			double Q_MB = Double.parseDouble(jsonRowData.getString("Q_MB"));		//煤山目标热值
			double S_MB = Double.parseDouble(jsonRowData.getString("S_MB"));		//煤山目标硫分
			double V_MB = Double.parseDouble(jsonRowData.getString("V_MB"));		//煤山目标挥发分
			double SHUL_MB = Double.parseDouble(strSHUL_MB);	//煤山目标数量
			if("0".equals(ID)){		//如果该煤山未进行过计算,则从数据库中取值进行赋值
				Simplex sp = createSimplex(jsArrMeiy, S_MB, SHUL_MB, Q_MB);
				sp.solve();
				if(sp.getError() != null){
					return sp.getError();
				}
				double[] v = sp.getVariablesValue();
				
				createSqlList(lSql, ID, strDate, MSID, SHUL_MB, Q_MB, S_MB, V_MB,
						jsonRowData.getString("ZHUANGT"), jsArrMeiy, v);
				
			}else{					//如果该煤山已经计算过,则从json中取值进行赋值
				Simplex sp = createSimplex(jsonArrDetails, S_MB, SHUL_MB, Q_MB);
				sp.solve();
				if(sp.getError() != null){
					return sp.getError();
				}
				double[] v = sp.getVariablesValue();
				
				createSqlList(lSql, ID, strDate, MSID, SHUL_MB, Q_MB, S_MB, V_MB,
						jsonRowData.getString("ZHUANGT"), jsonArrDetails, v);
			}
			String[] sqlArr = lSql.toArray(new String[lSql.size()]);
			flag = jdbcTemplate.batchUpdate(sqlArr);
		}
		if(flag == null){
			return "计算产生未知失败";
		}else{
			if(flag.length>0){
				return "计算成功";
			}else{
				return "计算成功，未更新数据";
			}
		}
		
	}

	@Override
	public boolean saveData(String strDate, JSONObject jsonData) {
		// TODO Auto-generated method stub
		return saveOrsubmitData(strDate, jsonData, false);
	}

	@Override
	public boolean submitData(String strDate, JSONObject jsonData) {
		// TODO Auto-generated method stub
		return saveOrsubmitData(strDate, jsonData, true);
	}
	
	private boolean saveOrsubmitData(String strDate, JSONObject jsonData, boolean isSubmit) {
		JSONArray jsonArr = jsonData.getJSONArray("data");
		String sql;
		List<String> listSql = new ArrayList<String>();
		for(int i=0; i<jsonArr.size(); i++){
			JSONObject jsonRowData = ((JSONObject) jsonArr.get(i)).getJSONObject("rowData");
			JSONArray jsonRowDetails = ((JSONObject) jsonArr.get(i)).getJSONArray("rowDetails");
			
			String ID = jsonRowData.getString("ID");
			
			String MSID = jsonRowData.getString("MSID");
			String Q_MB = jsonRowData.getString("Q_MB");
			String S_MB = jsonRowData.getString("S_MB");
			String V_MB = jsonRowData.getString("V_MB");
			String SHUL_MB = jsonRowData.getString("SHUL_MB");
			String QNET_AR_JG = jsonRowData.getString("QNET_AR_JG");
			String S_JG = jsonRowData.getString("S_JG");
			String V_JG = jsonRowData.getString("V_JG");
			String SHUL_JG = jsonRowData.getString("SHUL_JG");
			String BIAOMDJ = jsonRowData.getString("BIAOMDJ");
			String ZHUANGT = jsonRowData.getString("ZHUANGT");
			if("0".equals(ID)){
				ID = Sequence.nextId();
				sql = createSql4InsDy(ID, strDate, MSID, Q_MB, S_MB, V_MB,
						SHUL_MB, QNET_AR_JG, S_JG, V_JG, SHUL_JG, BIAOMDJ, 
						(isSubmit?"1":ZHUANGT));
			}else{
				sql = createSql4UpDy(ID, strDate, MSID, Q_MB, S_MB, V_MB,
						SHUL_MB, QNET_AR_JG, S_JG, V_JG, SHUL_JG, BIAOMDJ, 
						(isSubmit?"1":ZHUANGT));
			}
			listSql.add(sql);
			
			for(int j=0; j<jsonRowDetails.size(); j++){
				JSONObject jsonDetailsData = (JSONObject) jsonRowDetails.get(j);
				//调运明细只有更新没有插入
				String MXID = jsonDetailsData.getString("ID");
				String MXMEIYB_ID = jsonDetailsData.getString("MEIYB_ID");
				String MXBIAOMDJ = jsonDetailsData.getString("BIAOMDJ");
				String MXQNET_AR = jsonDetailsData.getString("QNET_AR");
				String MXS = jsonDetailsData.getString("S");
				String MXV = jsonDetailsData.getString("V");
				String MXSHUL_MAX = jsonDetailsData.getString("SHUL_MAX");
				String MXSHUL_MIN = jsonDetailsData.getString("SHUL_MIN");
				String MXSHUL_DY = jsonDetailsData.getString("SHUL_DY");
				String MXCHES = jsonDetailsData.getString("CHES");
				sql = createSql4UpMx(ID, MXMEIYB_ID, MXBIAOMDJ, MXQNET_AR, MXS, MXV,
						MXSHUL_MAX, MXSHUL_MIN, MXSHUL_DY, MXCHES, MXID);
				listSql.add(sql);
			}
		}
		String[] sqlArr = listSql.toArray(new String[listSql.size()]);
		int[] flag = jdbcTemplate.batchUpdate(sqlArr);
		if(flag.length>=0)
			return true;
		else{
			return false;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List loadData4MeiyChes(String strDate) {
		// TODO Auto-generated method stub
		String sql = 
				"SELECT 0 AS PAIXH,\n" +
						"       Y.MEIYMC,\n" + 
						"       SUM(M.CHES) AS CHES,\n" + 
						"       0 AS DAOCLYG,\n" + 
						"       0 AS SHIJDCL,\n" + 
						"       H.RIQ\n" + 
						"  FROM RL_PM_DIAOYMX M\n" + 
						" INNER JOIN RL_PM_RIDYB H ON H.ID = M.DIAOYB_ID\n" + 
						"                         AND H.RIQ = \n" + 
						" '"+strDate+"'\n" + 
						" INNER JOIN RL_PM_MEIYB Y ON M.MEIYB_ID = Y.ID\n" + 
						" GROUP BY Y.MEIYMC, H.RIQ\n" + 
						"UNION\n" + 
						"SELECT 1, '合计', SUM(M.CHES), 0, 0, ''\n" + 
						"  FROM RL_PM_DIAOYMX M\n" + 
						" INNER JOIN RL_PM_RIDYB H ON H.ID = M.DIAOYB_ID\n" + 
						"                         AND H.RIQ = \n" + 
						" '"+strDate+"'\n" + 
						" INNER JOIN RL_PM_MEIYB Y ON M.MEIYB_ID = Y.ID";
			List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
			return l4RowData;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List loadData4Diaoymx(String Peimdw_id, String Meis_id, String strDate) {
		// TODO Auto-generated method stub
		String conditionSql = "";
		if(Long.parseLong(Meis_id) != -1){
			conditionSql = "                AND S.ID = "+Meis_id+"\n";
		}
		String sql = "SELECT 0 as paixh,G.MINGC,\n" +
						"       G.MEISMC,\n" + 
						"       T.MEIYMC,\n" + 
						"       T.SHUL_DY,\n" + 
						"       T.CHES,\n" + 
						"       T.QNET_AR,\n" + 
						"       T.S,\n" + 
						"       T.V,\n" + 
						"       T.BIAOMDJ,\n" + 
						"       R.RIQ\n" + 
						"  FROM RL_PM_RIDYB R\n" + 
						" INNER JOIN (SELECT MX.DIAOYB_ID,\n" + 
						"                    MY.MEIYMC,\n" + 
						"                    MX.SHUL_DY,\n" + 
						"                    MX.CHES,\n" + 
						"                    MX.QNET_AR,\n" + 
						"                    MX.S,\n" + 
						"                    MX.V,\n" + 
						"                    MX.BIAOMDJ\n" + 
						"               FROM RL_PM_DIAOYMX MX\n" + 
						"              INNER JOIN RL_PM_MEIYB MY ON MX.MEIYB_ID = MY.ID) T ON R.ID =\n" + 
						"                                                                     T.DIAOYB_ID\n" + 
						" INNER JOIN (SELECT D.MINGC, S.ID, S.MEISMC\n" + 
						"               FROM RL_PM_MEISB S\n" + 
						"              INNER JOIN DIANCXXB D ON S.DIANCXXB_ID = D.ID\n" + 
						"              WHERE 1 = 1\n" + 
						conditionSql + 
						"                AND D.ID = "+Peimdw_id+") G ON R.MEISB_ID = G.ID\n" + 
						" WHERE R.RIQ = '"+strDate+"'\n" + 
						"UNION\n" + 
						"SELECT 1,'合计',\n" + 
						"       '',\n" + 
						"       '' C,\n" + 
						"       SUM(T.SHUL_DY),\n" + 
						"       SUM(T.CHES),\n" + 
						"       ROUND(CASE\n" + 
						"               WHEN SUM(T.SHUL_DY) = 0 THEN\n" + 
						"                0\n" + 
						"               ELSE\n" + 
						"                SUM(T.SHUL_DY * T.QNET_AR) / SUM(T.SHUL_DY)\n" + 
						"             END,\n" + 
						"             0) AS QNET_AR,\n" + 
						"       ROUND(CASE\n" + 
						"               WHEN SUM(T.SHUL_DY) = 0 THEN\n" + 
						"                0\n" + 
						"               ELSE\n" + 
						"                SUM(T.SHUL_DY * T.S) / SUM(T.SHUL_DY)\n" + 
						"             END,\n" + 
						"             0) AS S,\n" + 
						"       ROUND(CASE\n" + 
						"               WHEN SUM(T.SHUL_DY) = 0 THEN\n" + 
						"                0\n" + 
						"               ELSE\n" + 
						"                SUM(T.SHUL_DY * T.V) / SUM(T.SHUL_DY)\n" + 
						"             END,\n" + 
						"             0) AS V,\n" + 
						"       ROUND(CASE\n" + 
						"               WHEN SUM(T.SHUL_DY) = 0 THEN\n" + 
						"                0\n" + 
						"               ELSE\n" + 
						"                SUM(T.SHUL_DY * T.BIAOMDJ) / SUM(T.SHUL_DY)\n" + 
						"             END,\n" + 
						"             0) AS BIAOMDJ,\n" + 
						"       ''\n" + 
						"  FROM RL_PM_RIDYB R\n" + 
						" INNER JOIN (SELECT MX.DIAOYB_ID,\n" + 
						"                    MY.MEIYMC,\n" + 
						"                    MX.SHUL_DY,\n" + 
						"                    MX.CHES,\n" + 
						"                    MX.QNET_AR,\n" + 
						"                    MX.S,\n" + 
						"                    MX.V,\n" + 
						"                    MX.BIAOMDJ\n" + 
						"               FROM RL_PM_DIAOYMX MX\n" + 
						"              INNER JOIN RL_PM_MEIYB MY ON MX.MEIYB_ID = MY.ID) T ON R.ID =\n" + 
						"                                                                     T.DIAOYB_ID\n" + 
						" INNER JOIN (SELECT D.MINGC, S.ID, S.MEISMC\n" + 
						"               FROM RL_PM_MEISB S\n" + 
						"              INNER JOIN DIANCXXB D ON S.DIANCXXB_ID = D.ID\n" + 
						"              WHERE 1 = 1\n" + 
						conditionSql + 
						"                AND D.ID = "+Peimdw_id+") G ON R.MEISB_ID = G.ID\n" + 
						" WHERE R.RIQ = '"+strDate+"'";

			List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
			return l4RowData;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List loadPeimdwData() {
		// TODO Auto-generated method stub
		String sql = "SELECT ID, MINGC FROM DIANCXXB";
		List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
		return l4RowData;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List loadMeisData(String Peimdw_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT ID, MEISMC FROM RL_PM_MEISB WHERE DIANCXXB_ID = " + Peimdw_id;
		List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
		return l4RowData;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List loadMeisData() {
		// TODO Auto-generated method stub
		String sql = "SELECT S.ID,\n" +
				"       S.MEISMC,\n" + 
				"       S.DIANCXXB_ID,\n" + 
				"       D.MINGC AS DIANCXXB_ID_CN,\n" + 
				"       MEICMC,\n" + 
				"       QNET_AR,\n" + 
				"       S,\n" + 
				"       V\n" + 
				"  FROM RL_PM_MEISB S\n" + 
				" INNER JOIN DIANCXXB D ON S.DIANCXXB_ID = D.ID";
		List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
		return l4RowData;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List loadMeiyData() {
		// TODO Auto-generated method stub
		String sql = "SELECT ID,\n" +
			"       MEIYMC,\n" + 
			"       QNET_AR,\n" + 
			"       S,\n" + 
			"       V,\n" + 
			"       MEIJ,\n" + 
			"       YUNJ,\n" + 
			"       BIAOMDJ,\n" + 
			"       SHUL_MAX,\n" + 
			"       SHUL_MIN,\n" + 
			"       CASE\n" + 
			"         WHEN ZHUANGT = 1 THEN\n" + 
			"          '启用'\n" + 
			"         ELSE\n" + 
			"          '停用'\n" + 
			"       END AS ZHUANGT_CN,\n" + 
			"       ZHUANGT\n" + 
			"  FROM RL_PM_MEIYB";
		List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
		return l4RowData;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List loadYunsdwData() {
		// TODO Auto-generated method stub
		String sql = "SELECT ID,\n" +
			"       CHEDMC,\n" + 
			"       CASE\n" + 
			"         WHEN ZHUANGT = 1 THEN\n" + 
			"          '启用'\n" + 
			"         ELSE\n" + 
			"          '停用'\n" + 
			"       END AS ZHUANGT_CN,\n" + 
			"       ZHUANGT\n" + 
			"  FROM RL_PM_YUNSCDB";
		List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
		return l4RowData;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List loadYunscdCombo() {
		// TODO Auto-generated method stub
		String sql = "SELECT ID,\n" +
				"       CHEDMC\n" + 
				"  FROM RL_PM_YUNSCDB WHERE ZHUANGT = 1";
			List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
			return l4RowData;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List loadMeiyCombo() {
		// TODO Auto-generated method stub
		String sql = 
				"SELECT ID, MEIYMC FROM RL_PM_MEIYB WHERE ZHUANGT = 1";
			List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
			return l4RowData;
	}
	
	@Override
	public boolean saveMeisData(JSONObject jsonData) {
		// TODO Auto-generated method stub
		String ID = jsonData.getString("ID");
		String meismc = jsonData.getString("MEISMC");
		String diancxxb_id = jsonData.getString("DIANCXXB_ID");
		String meicmc = jsonData.getString("MEICMC");
		String qnet_ar = jsonData.getString("QNET_AR");
		String s = jsonData.getString("S");
		String v = jsonData.getString("V");
		String sql;
		if("0".equals(ID)){
			ID = Sequence.nextId();
			sql = "INSERT INTO RL_PM_MEISB\n" +
				"  (ID, MEISMC, DIANCXXB_ID, MEICMC, QNET_AR, S, V)\n" + 
				"VALUES\n" + 
				"  ("+ID+", '"+meismc+"', "+diancxxb_id+", '"+meicmc+"', "+
				("".equals(qnet_ar)?"null":qnet_ar)+", "
				+("".equals(s)?"null":s)+", "+("".equals(v)?"null":v)+")";
		}else{
			sql = "UPDATE RL_PM_MEISB\n" +
				"   SET MEISMC      = '"+meismc+"',\n" + 
				"       DIANCXXB_ID = "+diancxxb_id+",\n" + 
				"       MEICMC      = '"+meicmc+"',\n" + 
				"       QNET_AR     = "+("".equals(qnet_ar)?"null":qnet_ar)+",\n" + 
				"       S           = "+("".equals(s)?"null":s)+",\n" + 
				"       V           = "+("".equals(v)?"null":v)+"\n" + 
				" WHERE ID = " + ID;
		}
		int flag = jdbcTemplate.update(sql);
		if(flag > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean saveMeiyData(JSONObject jsonData) {
		// TODO Auto-generated method stub
		String ID = jsonData.getString("ID");
		String meiymc = jsonData.getString("MEIYMC");
		String qnet_ar = jsonData.getString("QNET_AR");
		String s = jsonData.getString("S");
		String v = jsonData.getString("V");
		String meij = jsonData.getString("MEIJ");
		String yunj = jsonData.getString("YUNJ");
		String biaomdj = jsonData.getString("BIAOMDJ");
		String shul_max = jsonData.getString("SHUL_MAX");
		String shul_min = jsonData.getString("SHUL_MIN");
//		String zhuangt = jsonData.getString("ZHUANGT");
		String sql;
		if("0".equals(ID)){
			ID = Sequence.nextId();
			sql = "INSERT INTO RL_PM_MEIYB\n" +
			"    (ID,\n" + 
			"     MEIYMC,\n" + 
			"     QNET_AR,\n" + 
			"     S,\n" + 
			"     V,\n" + 
			"     MEIJ,\n" + 
			"     YUNJ,\n" + 
			"     BIAOMDJ,\n" + 
			"     SHUL_MAX,\n" + 
			"     SHUL_MIN,\n" + 
			"     ZHUANGT)\n" + 
			"  VALUES\n" + 
			"    ("+ID+",\n" + 
			"     '"+meiymc+"',\n" + 
			"     "+("".equals(qnet_ar)?"null":qnet_ar)+",\n" + 
			"     "+("".equals(s)?"null":s)+",\n" + 
			"     "+("".equals(v)?"null":v)+",\n" + 
			"     "+("".equals(meij)?"null":meij)+",\n" + 
			"     "+("".equals(yunj)?"null":yunj)+",\n" + 
			"     "+("".equals(biaomdj)?"null":biaomdj)+",\n" + 
			"     "+("".equals(shul_max)?"null":shul_max)+",\n" + 
			"     "+("".equals(shul_min)?"null":shul_min)+",\n" + 
			"     1)";
		}else{
			sql = "UPDATE RL_PM_MEIYB\n" +
				"   SET MEIYMC   = '"+meiymc+"',\n" + 
				"       QNET_AR  = "+("".equals(qnet_ar)?"null":qnet_ar)+",\n" + 
				"       S        = "+("".equals(s)?"null":s)+",\n" + 
				"       V        = "+("".equals(v)?"null":v)+",\n" + 
				"       MEIJ     = "+("".equals(meij)?"null":meij)+",\n" + 
				"       YUNJ     = "+("".equals(yunj)?"null":yunj)+",\n" + 
				"       BIAOMDJ  = "+("".equals(biaomdj)?"null":biaomdj)+",\n" + 
				"       SHUL_MAX = "+("".equals(shul_max)?"null":shul_max)+",\n" + 
				"       SHUL_MIN = "+("".equals(shul_min)?"null":shul_min)+"\n" + 
//				"       ZHUANGT  = "+zhuangt+"\n" + 
				" WHERE ID = " + ID;
		}
		int flag = jdbcTemplate.update(sql);
		if(flag > 0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean saveYunscdData(JSONObject jsonData) {
		// TODO Auto-generated method stub
		String ID = jsonData.getString("ID");
		String chedmc = jsonData.getString("CHEDMC");
//		String zhuangt = jsonData.getString("ZHUANGT");
		String sql;
		if("0".equals(ID)){
			ID = Sequence.nextId();
			sql = "INSERT INTO RL_PM_YUNSCDB (ID, CHEDMC, ZHUANGT) VALUES ("+ID+", '"+chedmc+"', 1)";
		} else {
			sql = "UPDATE RL_PM_YUNSCDB SET CHEDMC = '"+chedmc+"' WHERE ID = " + ID;
		}
		int flag = jdbcTemplate.update(sql);
		if(flag > 0){
			return true;
		}else{
			return false;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public JSONObject loadRicddyjhData(String strDate) {
		// TODO Auto-generated method stub
		String sql;
		sql = 
				"SELECT CASE\n" +
						"         WHEN CD.ID IS NULL THEN\n" + 
						"          0\n" + 
						"         ELSE\n" + 
						"          CD.ID\n" + 
						"       END ID,\n" + 
						"       H.MEIYB_ID,\n" + 
						"       MY.MEIYMC,\n" + 
						"       CASE\n" + 
						"         WHEN CD.DIAOYL IS NULL THEN\n" + 
						"          H.SHUL_JG\n" + 
						"         ELSE\n" + 
						"          CD.DIAOYL\n" + 
						"       END DIAOYL,\n" + 
						"       CASE\n" + 
						"         WHEN CD.QNET_AR IS NULL THEN\n" + 
						"          MY.QNET_AR\n" + 
						"         ELSE\n" + 
						"          CD.QNET_AR\n" + 
						"       END QNET_AR,\n" + 
						"       CASE\n" + 
						"         WHEN CD.S IS NULL THEN\n" + 
						"          MY.S\n" + 
						"         ELSE\n" + 
						"          CD.S\n" + 
						"       END S,\n" + 
						"       CASE\n" + 
						"         WHEN CD.CDS IS NULL THEN\n" + 
						"          0\n" + 
						"         ELSE\n" + 
						"          CD.CDS\n" + 
						"       END CDS\n" + 
						"  FROM (SELECT M.MEIYB_ID, R.RIQ, SUM(R.SHUL_JG) SHUL_JG\n" + 
						"          FROM RL_PM_RIDYB R\n" + 
						"         INNER JOIN RL_PM_DIAOYMX M ON R.ID = M.DIAOYB_ID\n" + 
						"         WHERE R.RIQ = '"+strDate+"' GROUP BY M.MEIYB_ID, R.RIQ) H\n" + 
						"  LEFT JOIN (SELECT J.ID,\n" + 
						"                    J.MEIYB_ID,\n" + 
						"                    J.RIQ,\n" + 
						"                    J.DIAOYL,\n" + 
						"                    J.QNET_AR,\n" + 
						"                    J.S,\n" + 
						"                    CDMX.CDS\n" + 
						"               FROM RL_PM_RICDDYJH J\n" + 
						"              INNER JOIN (SELECT RICDDYJH_ID, COUNT(*) CDS\n" + 
						"                           FROM RL_PM_RICDDYJHMX\n" + 
						"                          GROUP BY RICDDYJH_ID) CDMX ON J.ID =\n" + 
						"                                                        CDMX.RICDDYJH_ID) CD ON H.MEIYB_ID =\n" + 
						"                                                                                CD.MEIYB_ID\n" + 
						"                                                                            AND H.RIQ =\n" + 
						"                                                                                CD.RIQ\n" + 
						" INNER JOIN RL_PM_MEIYB MY ON H.MEIYB_ID = MY.ID\n" + 
						" ORDER BY MY.ID";
		List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
		Iterator i4RowData = l4RowData.iterator();
		JSONArray jsonArrData = new JSONArray();			//构建记录各行信息的JsonArray
		while (i4RowData.hasNext()) {
			Map mRow = (Map)i4RowData.next();				
			JSONObject jsORowData = new JSONObject();		//构建记录某一行数据的JsonOb
			jsORowData.put("rowData", mRow);				//加入某行的头数据，也就日调运行
			JSONArray jsArrDetails = new JSONArray();		//构建记录计算之后的日调运明细信息的JsonArray
			if(!"0".equals(mRow.get("ID"))){				//如果该调运行ID为0，则说明还没有经过计算，则没有日调运明细信息
				sql = 
						"SELECT MX.ID,\n" +
								"       MX.RICDDYJH_ID,\n" + 
								"       MX.YUNSCDB_ID,\n" + 
								"       CD.CHEDMC AS YUNSCDB_ID_CN,\n" + 
								"       MX.DIAOYL,\n" + 
								"       MX.CHES,\n" + 
								"       MX.DIANCXXB_ID,\n" + 
								"       D.MINGC DIANCXXB_ID_CN\n" + 
								"  FROM RL_PM_RICDDYJHMX MX\n" + 
								" INNER JOIN DIANCXXB D ON MX.DIANCXXB_ID = D.ID\n" + 
								" INNER JOIN RL_PM_YUNSCDB CD ON MX.YUNSCDB_ID = CD.ID\n" + 
								" WHERE MX.RICDDYJH_ID = " + mRow.get("ID");
				List l4Details = jdbcTemplate.queryForList(sql);	//查出某一煤山行对应的明细信息list
				Iterator i4Details = l4Details.iterator();
				while (i4Details.hasNext()) {
					Map mDetails = (Map)i4Details.next();
					jsArrDetails.add(mDetails);//.put(mDetails);						//将明细循环计入明细JsonArray
				}
			}
			jsORowData.put("rowDetails", jsArrDetails);				//将JsonArray计入行JsonOb，构建出完整行信息
			jsonArrData.add(jsORowData);//.put(jsORowData);							//将完整的行信息计入整体JsonArray
		}
		JSONObject jsonOData = new JSONObject();
		jsonOData.put("data", jsonArrData);
		return jsonOData;
	}

	@Override
	public boolean changeMeiyStatus(String id) {
		// TODO Auto-generated method stub
		String sql = this.changeStatusSql("RL_PM_MEIYB", id);
		int flag = jdbcTemplate.update(sql);
		if(flag > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean changeYunscdStatus(String id) {
		// TODO Auto-generated method stub
		String sql = this.changeStatusSql("RL_PM_YUNSCDB", id);
		int flag = jdbcTemplate.update(sql);
		if(flag > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean saveRicddyjhData(String strDate, JSONObject jsonData) {
		// TODO Auto-generated method stub
		JSONArray jsonArr = jsonData.getJSONArray("data");
		String sql;
		List<String> listSql = new ArrayList<String>();
		for(int i=0; i<jsonArr.size(); i++){
			JSONObject jsonRowData = ((JSONObject) jsonArr.get(i)).getJSONObject("rowData");
			JSONArray jsonRowDetails = ((JSONObject) jsonArr.get(i)).getJSONArray("rowDetails");
			String ID = jsonRowData.getString("ID");
			String MEIYB_ID = jsonRowData.getString("MEIYB_ID");
			String DIAOYL = jsonRowData.getString("DIAOYL");
			String QNET_AR = jsonRowData.getString("QNET_AR");
			String S = jsonRowData.getString("S");
			if("0".equals(ID)){
				ID = Sequence.nextId();
				sql = createSql4InsCDDy(ID, strDate, MEIYB_ID, DIAOYL, QNET_AR, S);
				listSql.add(sql);
			}
//			else{
//				sql = createSql4UpCDDy(ID, strDate, MEIYB_ID, DIAOYL, QNET_AR, S);
//			}
			for(int j=0; j<jsonRowDetails.size(); j++){
				JSONObject jsonDetailsData = (JSONObject) jsonRowDetails.get(j);
				//调运明细只有更新没有插入
				String MXID = jsonDetailsData.getString("ID");
				String MXYUNSCDB_ID = jsonDetailsData.getString("YUNSCDB_ID");
				String MXDIANCXXB_ID = jsonDetailsData.getString("DIANCXXB_ID");
				String MXDIAOYL = jsonDetailsData.getString("DIAOYL");
				String MXCHES = jsonDetailsData.getString("CHES");
				if("0".equals(MXID)){
					MXID = Sequence.nextId();
					sql = createSql4InsCDDyMx(MXID, ID, MXYUNSCDB_ID, MXDIANCXXB_ID, MXDIAOYL, MXCHES);
				}else{
					sql = createSql4UpCDDyMx(MXID, ID, MXYUNSCDB_ID, MXDIANCXXB_ID, MXDIAOYL, MXCHES);
				}
				listSql.add(sql);
			}
		}
		String[] sqlArr = listSql.toArray(new String[listSql.size()]);
		int[] flag = jdbcTemplate.batchUpdate(sqlArr);
		if(flag.length>=0)
			return true;
		else{
			return false;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public JSONObject loadData4Cheddytz(String yunscdb_id, String strDate) {
		// TODO Auto-generated method stub
		String sql;
		sql = "SELECT DISTINCT MX.YUNSCDB_ID,CD.CHEDMC\n" +
		"        FROM RL_PM_RICDDYJHMX MX\n" + 
		"       INNER JOIN (SELECT Z.ID, MEIYMC\n" + 
		"                     FROM RL_PM_RICDDYJH Z\n" + 
		"                    INNER JOIN RL_PM_MEIYB Y ON Z.MEIYB_ID = Y.ID\n" + 
		"                    WHERE Z.RIQ = '"+strDate+"') H ON MX.RICDDYJH_ID = H.ID\n" + 
		"       INNER JOIN RL_PM_YUNSCDB CD ON MX.YUNSCDB_ID = CD.ID\n" + 
		"       INNER JOIN DIANCXXB D ON MX.DIANCXXB_ID = D.ID\n" + 
		"       WHERE 1 = 1" +
		("-1".equals(yunscdb_id)?"":"   AND MX.YUNSCDB_ID = " +yunscdb_id) ;
		List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
		Iterator i4RowData = l4RowData.iterator();
		JSONArray jsonArrData = new JSONArray();			//构建记录各行信息的JsonArray
		while (i4RowData.hasNext()) {
			Map mRow = (Map)i4RowData.next();				
			JSONObject jsORowData = new JSONObject();		//构建记录某一行数据的JsonOb
			jsORowData.put("rowData", mRow);				//加入某行的头数据，也就日调运行
			JSONArray jsArrDetails = new JSONArray();		//构建记录计算之后的日调运明细信息的JsonArray
			if(!"0".equals(mRow.get("YUNSCDB_ID"))){				//如果该调运行ID为0，则说明还没有经过计算，则没有日调运明细信息
				sql = "SELECT MX.ID, CD.CHEDMC, H.MEIYMC, MX.CHES, D.MINGC, MX.BEIZ\n" +
						"  FROM RL_PM_RICDDYJHMX MX\n" + 
						" INNER JOIN (SELECT Z.ID, MEIYMC\n" + 
						"               FROM RL_PM_RICDDYJH Z\n" + 
						"              INNER JOIN RL_PM_MEIYB Y ON Z.MEIYB_ID = Y.ID\n" + 
						"              WHERE Z.RIQ = '"+strDate+"') H ON MX.RICDDYJH_ID = H.ID\n" + 
						" INNER JOIN RL_PM_YUNSCDB CD ON MX.YUNSCDB_ID = CD.ID\n" + 
						" INNER JOIN DIANCXXB D ON MX.DIANCXXB_ID = D.ID\n" + 
						" WHERE 1 = 1\n" + 
						"   AND MX.YUNSCDB_ID = " +mRow.get("YUNSCDB_ID") ;
				List l4Details = jdbcTemplate.queryForList(sql);	//查出某一煤山行对应的明细信息list
				Iterator i4Details = l4Details.iterator();
				while (i4Details.hasNext()) {
					Map mDetails = (Map)i4Details.next();
					jsArrDetails.add(mDetails);//.put(mDetails);						//将明细循环计入明细JsonArray
				}
			}
			jsORowData.put("rowDetails", jsArrDetails);				//将JsonArray计入行JsonOb，构建出完整行信息
			jsonArrData.add(jsORowData);//.put(jsORowData);							//将完整的行信息计入整体JsonArray
		}
		JSONObject jsonOData = new JSONObject();
		jsonOData.put("data", jsonArrData);
		return jsonOData;
	}

	@Override
	public boolean saveRicddytzData(JSONObject jsonData) {
		// TODO Auto-generated method stub
		String sql;
		JSONArray jsonArr = jsonData.getJSONArray("data");
		List<String> listSql = new ArrayList<String>();
		for(int i=0; i<jsonArr.size(); i++){
			JSONArray jsonRowDetails = ((JSONObject) jsonArr.get(i)).getJSONArray("rowDetails");
			for(int j=0; j<jsonRowDetails.size(); j++){
				JSONObject jsonDetailsData = (JSONObject) jsonRowDetails.get(j);
				sql = "UPDATE RL_PM_RICDDYJHMX SET BEIZ = '"+
						jsonDetailsData.getString("BEIZ")+"' WHERE ID = " + 
						jsonDetailsData.getString("ID");
				listSql.add(sql);
			}
		}
		String[] sqlArr = listSql.toArray(new String[listSql.size()]);
		int[] flag = jdbcTemplate.batchUpdate(sqlArr);
		if(flag.length>=0)
			return true;
		else{
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public JSONObject loadData4Meicpmdy(String Peimdw_id, String strDate) {
		// TODO Auto-generated method stub
		String sql;
		sql = "SELECT ID, MINGC FROM DIANCXXB WHERE 1=1\n" +
			("-1".equals(Peimdw_id)?"":"   AND ID = " +Peimdw_id) ;
		List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
		Iterator i4RowData = l4RowData.iterator();
		JSONArray jsonArrData = new JSONArray();			//构建记录各行信息的JsonArray
		while (i4RowData.hasNext()) {
			Map mRow = (Map)i4RowData.next();				
			JSONObject jsORowData = new JSONObject();		//构建记录某一行数据的JsonOb
			jsORowData.put("rowData", mRow);				//加入某行的头数据，也就日调运行
			JSONArray jsArrDetails = new JSONArray();		//构建记录计算之后的日调运明细信息的JsonArray
			if(!"0".equals(mRow.get("ID"))){				//如果该调运行ID为0，则说明还没有经过计算，则没有日调运明细信息
				sql = "SELECT T.ID,\n" +
						"       D.MINGC AS DIANCXXB_ID_CN,\n" + 
						"       T.DIANCXXB_ID,\n" + 
						"       Y.MEIYMC AS MEIYB_ID_CN,\n" + 
						"       T.MEIYB_ID,\n" + 
						"       T.CHES,\n" + 
						"       T.DAOCLYG,\n" + 
						"       T.SHIJDCL,\n" + 
						"       S.MEISMC MEISB_ID_CN,\n" + 
						"       T.MEISB_ID,\n" + 
						"       T.RIQ\n" + 
						"  FROM RL_PM_MEICPMDY T\n" + 
						" INNER JOIN DIANCXXB D ON T.DIANCXXB_ID = D.ID\n" + 
						" INNER JOIN RL_PM_MEIYB Y ON T.MEIYB_ID = Y.ID\n" + 
						" INNER JOIN RL_PM_MEISB S ON T.MEISB_ID = S.ID\n" + 
						" WHERE RIQ = '"+strDate+"'\n" +
						"   AND T.DIANCXXB_ID = " +mRow.get("ID") ;
				List l4Details = jdbcTemplate.queryForList(sql);	//查出某一煤山行对应的明细信息list
				Iterator i4Details = l4Details.iterator();
				while (i4Details.hasNext()) {
					Map mDetails = (Map)i4Details.next();
					jsArrDetails.add(mDetails);//.put(mDetails);						//将明细循环计入明细JsonArray
				}
			}
			jsORowData.put("rowDetails", jsArrDetails);				//将JsonArray计入行JsonOb，构建出完整行信息
			jsonArrData.add(jsORowData);//.put(jsORowData);							//将完整的行信息计入整体JsonArray
		}
		JSONObject jsonOData = new JSONObject();
		jsonOData.put("data", jsonArrData);
		return jsonOData;
	}

	@Override
	public boolean delMeicpmdyData(String id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM RL_PM_MEICPMDY WHERE ID = " + id;
		int flag = jdbcTemplate.update(sql);
		if(flag > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean saveMeicpmdyData(String strDate, JSONObject jsonData) {
		// TODO Auto-generated method stub
		String sql;
		JSONArray jsonArr = jsonData.getJSONArray("data");
		List<String> listSql = new ArrayList<String>();
		for(int i=0; i<jsonArr.size(); i++){
			JSONArray jsonRowDetails = ((JSONObject) jsonArr.get(i)).getJSONArray("rowDetails");
			for(int j=0; j<jsonRowDetails.size(); j++){
				JSONObject jsonDetailsData = (JSONObject) jsonRowDetails.get(j);
				String ID = jsonDetailsData.getString("ID");
				String DIANCXXB_ID = jsonDetailsData.getString("DIANCXXB_ID");
				String MEIYB_ID = jsonDetailsData.getString("MEIYB_ID");
				String MEISB_ID = jsonDetailsData.getString("MEISB_ID");
				String CHES = jsonDetailsData.getString("CHES");
				String DAOCLYG = jsonDetailsData.getString("DAOCLYG");
				String SHIJDCL = jsonDetailsData.getString("SHIJDCL");
				if("0".equals(ID)){
					ID = Sequence.nextId();
					sql =
					"INSERT INTO RL_PM_MEICPMDY\n" +
					"  (ID, RIQ, DIANCXXB_ID, MEIYB_ID, MEISB_ID, CHES, DAOCLYG, SHIJDCL)\n" + 
					"VALUES\n" + 
					"  ("+ID+", '"+strDate+"', "+DIANCXXB_ID+", "+MEIYB_ID+", "+MEISB_ID+", "+
					("".equals(CHES)?"NULL":CHES)+", "+("".equals(DAOCLYG)?"NULL":DAOCLYG)+
					", "+("".equals(SHIJDCL)?"NULL":SHIJDCL)+")";
				}else{
					sql = "UPDATE RL_PM_MEICPMDY\n" +
					"   SET RIQ         = '"+strDate+"',\n" + 
					"       DIANCXXB_ID = "+DIANCXXB_ID+",\n" + 
					"       MEIYB_ID    = "+MEIYB_ID+",\n" + 
					"       MEISB_ID    = "+MEISB_ID+",\n" + 
					"       CHES        = "+("".equals(CHES)?"NULL":CHES)+",\n" + 
					"       DAOCLYG     = "+("".equals(DAOCLYG)?"NULL":DAOCLYG)+",\n" + 
					"       SHIJDCL     = "+("".equals(SHIJDCL)?"NULL":SHIJDCL)+"\n" + 
					" WHERE ID = " + ID;
				}
				listSql.add(sql);
			}
		}
		String[] sqlArr = listSql.toArray(new String[listSql.size()]);
		int[] flag = jdbcTemplate.batchUpdate(sqlArr);
		if(flag.length>=0)
			return true;
		else{
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public JSONObject loadMeisData4ID(String ID) {
		// TODO Auto-generated method stub
		String sql = "SELECT S.ID,\n" +
				"       S.MEISMC,\n" + 
				"       S.DIANCXXB_ID,\n" + 
				"       D.MINGC AS DIANCXXB_ID_CN,\n" + 
				"       MEICMC,\n" + 
				"       QNET_AR,\n" + 
				"       S,\n" + 
				"       V\n" + 
				"  FROM RL_PM_MEISB S\n" + 
				" INNER JOIN DIANCXXB D ON S.DIANCXXB_ID = D.ID\n" +
				"	WHERE S.ID = " + ID;
		List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
		JSONObject jsonOb = JSONObject.fromObject(l4RowData.get(0));
		return jsonOb;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public JSONObject loadMeiyData4ID(String ID) {
		// TODO Auto-generated method stub
		String sql = "SELECT ID,\n" +
				"       MEIYMC,\n" + 
				"       QNET_AR,\n" + 
				"       S,\n" + 
				"       V,\n" + 
				"       MEIJ,\n" + 
				"       YUNJ,\n" + 
				"       BIAOMDJ,\n" + 
				"       SHUL_MAX,\n" + 
				"       SHUL_MIN,\n" + 
				"       CASE\n" + 
				"         WHEN ZHUANGT = 1 THEN\n" + 
				"          '启用'\n" + 
				"         ELSE\n" + 
				"          '停用'\n" + 
				"       END AS ZHUANGT_CN,\n" + 
				"       ZHUANGT\n" + 
				"  FROM RL_PM_MEIYB\n" +
				"	WHERE ID = " + ID;
		List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
		JSONObject jsonOb = JSONObject.fromObject(l4RowData.get(0));
		return jsonOb;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public JSONObject loadYunscdData4ID(String ID) {
		// TODO Auto-generated method stub
		String sql = "SELECT ID,\n" +
				"       CHEDMC,\n" + 
				"       CASE\n" + 
				"         WHEN ZHUANGT = 1 THEN\n" + 
				"          '启用'\n" + 
				"         ELSE\n" + 
				"          '停用'\n" + 
				"       END AS ZHUANGT_CN,\n" + 
				"       ZHUANGT\n" + 
				"  FROM RL_PM_YUNSCDB WHERE ID ="  + ID;
		List l4RowData = jdbcTemplate.queryForList(sql);	//查出页面展示煤山的行信息list
		JSONObject jsonOb = JSONObject.fromObject(l4RowData.get(0));
		return jsonOb;
	}
	
	
}
