package com.zhiren.fuelmis.dc.service.peim;

import java.util.List;

import net.sf.json.JSONObject;

//import org.json.JSONException;
//import org.json.JSONObject;


public interface IPeimeiService {
	//配煤单位
	@SuppressWarnings("rawtypes")
	List loadPeimdwData();
	@SuppressWarnings("rawtypes")
	//煤山下拉框
	List loadMeisData(String Peimdw_id);
	//运输单位下拉框
	@SuppressWarnings("rawtypes")
	List loadYunscdCombo();
	//煤源下拉框
	@SuppressWarnings("rawtypes")
	List loadMeiyCombo();
	@SuppressWarnings("rawtypes")
	//煤山表
	List loadMeisData();
	//煤源表
	@SuppressWarnings("rawtypes")
	List loadMeiyData();
	//运输单位表
	@SuppressWarnings("rawtypes")
	List loadYunsdwData();
	//煤山表
	JSONObject loadMeisData4ID(String ID);
	//煤源表
	JSONObject loadMeiyData4ID(String ID);
	//煤源表
	JSONObject loadYunscdData4ID(String ID);
	//一级配煤计算表
	JSONObject loadPageData(String strDate);
	//一级配煤
	@SuppressWarnings("rawtypes")
	List loadData4MeiyChes(String strDate);
	//一级配煤查询
	@SuppressWarnings("rawtypes")
	List loadData4Diaoymx(String Peimdw_id, String Meis_id, String strDate);
	//日车队调运计划
	JSONObject loadRicddyjhData(String strDate);
	//车队调运通知
	JSONObject loadData4Cheddytz(String yunscdb_id, String strDate);
	//车队调运通知
	JSONObject loadData4Meicpmdy(String Peimdw_id, String strDate);
	
	boolean changeMeiyStatus(String id);
	
	boolean changeYunscdStatus(String id);
	
	boolean saveMeisData(JSONObject jsonData);
	
	boolean saveMeiyData(JSONObject jsonData);
	
	boolean saveYunscdData(JSONObject jsonData);
	
	boolean saveData(String strDate, JSONObject jsonData);
	
	boolean submitData(String strDate, JSONObject jsonData);
	
	boolean saveRicddyjhData(String strDate, JSONObject jsonData);
	
	boolean saveRicddytzData(JSONObject jsonData);
	
	String calculate(String strDate, JSONObject jsonData);
	
	boolean delMeicpmdyData(String id);
	
	boolean saveMeicpmdyData(String strDate, JSONObject jsonData);
	
}
