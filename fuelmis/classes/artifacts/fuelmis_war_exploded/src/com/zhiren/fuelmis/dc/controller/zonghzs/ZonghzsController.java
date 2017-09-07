package com.zhiren.fuelmis.dc.controller.zonghzs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhiren.fuelmis.dc.service.zonghzs.ZonghzsDao;
import com.zhiren.fuelmis.dc.utils.formular.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("zonghzs/ghcqk")
public class ZonghzsController {
	
	@Autowired
	private ZonghzsDao zonghzsDao;
	
	@RequestMapping(value = "/getRanlxxgdts")
	public void getRanlxxgdts(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.getRanlxxgdts();
		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(resultJson.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getFenklmxxData")
	public void getFenklmxxData(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.getFenklmxxData();
		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(resultJson.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getFenklmcolumnData")
	public void getFenklmcolumnData(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.getFenklmcolumnData();
		String[][] a=new String[0][0];
		if(list.size()>0){
		String[][] array = Result.list2array(list,new String[]{"FENKLM","FENKMC"});
		a=new String[array[0].length][array.length];
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				a[j][i]=array[i][j];
			}		
		}
		}else{
			
		}
//		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(a);
//		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getKucjg")
	public void getKucjg(@RequestParam(defaultValue="") String yearmonth,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.getKucjg(yearmonth);
		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(resultJson.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getKucqkqxData")
	public void getKucqkqxData(@RequestParam(defaultValue="") String qsriq,@RequestParam(defaultValue="") String jzriq,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.getKucqkqxData(qsriq,jzriq);
		String[][] a=new String[0][0];
		if(list.size()>0){
		String[][] array = Result.list2array(list,new String[]{"RIQ","JINGJKC","KUCQK","ZHENGCCB"});
		a=new String[array[0].length][array.length];
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				a[j][i]=array[i][j];
			}		
		}
		}else{
			
		}
//		JSONObject resultJson = new JSONObject();
//		JSONArray jsonArray = JSONArray.fromObject(list);
//		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(a).toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getLaihmqkqxData")
	
	public void getLaihmqkqxData(@RequestParam(defaultValue="") String qsriq,@RequestParam(defaultValue="") String jzriq,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.getLaihmqkqxData(qsriq,jzriq);
		String[][] a=new String[0][0];
		if(list.size()>0){
		String[][] array = Result.list2array(list,new String[]{"RIQ","LAIM","HAOM",});
		a=new String[array[0].length][array.length];
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				a[j][i]=array[i][j];
			}		
		}
		}else{
			
		}
//		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(a);
//		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getKucmqx")
	public void getKucmqx(@RequestParam(defaultValue="") String riq,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.getKucmqx(riq);
		String[][] a=new String[0][0];
		if(list.size()>0){
		String[][] array = Result.list2array(list,new String[]{"MEIC","JINM","HAOM","CUNM","KUCRZ","KUCBMDJ","JMXS","HMXS","CMXS"});
		a=new String[array[0].length][array.length];
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				a[j][i]=array[i][j];
			}		
		}
		}else{
			
		}
//		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(a);
//		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//收耗存日报数据
	@RequestMapping(value = "/getLaihcrbGridData")
	public void getLaihcrbGridData(@RequestParam(defaultValue="") String riq,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.getLaihcrbGridData(riq);
		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(resultJson.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getLaimxx")
	public void getLaimxx(@RequestParam(defaultValue="") String riq,HttpServletRequest request,HttpServletResponse response,HttpSession session){		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.getLaimxx(riq);
		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(resultJson.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getRucbmdj")
	public void getRucbmdj(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="") String jzriq,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.getRucbmdj(riq,jzriq);
		String[][] a=new String[0][0];
		if(list.size()>0){
		String[][] array = Result.list2array(list,new String[]{"MEIK","PRICE","PRICEB","LAIMSL"});
		a=new String[array[0].length][array.length];
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				a[j][i]=array[i][j];
			}		
		}
		}else{
			
		}
//		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(a);
//		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getRuctrmj")
	public void getRuctrmj(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="") String jzriq,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.getRuctrmj(riq,jzriq);
		String[][] a=new String[0][0];
		if(list.size()>0){
		String[][] array = Result.list2array(list,new String[]{"RUCTRMJRIQ","RUCTRMJPRICE","RUCTRMJPRICEB"});
		a=new String[array[0].length][array.length];
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				a[j][i]=array[i][j];
			}		
		}
		}else{
			
		}
//		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(a);
//		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getDaysofRucbmdj")
	public void getDaysofRucbmdj(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="") String jzriq,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.getDaysofRucbmdj(riq,jzriq);
		String[][] a=new String[0][0];
		if(list.size()>0){
		String[][] array = Result.list2array(list,new String[]{"DAYSOFRUCBMDJRIQ","DAYSOFRUCBMDJPRICE","DAYSOFRUCBMDJPRICEB"});
		a=new String[array[0].length][array.length];
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				a[j][i]=array[i][j];
			}		
		}
		}else{
			
		}
//		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(a);
//		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/gridLoad")
	public void gridLoad(@RequestParam(defaultValue="") String qsriq,@RequestParam(defaultValue="") String jzriq,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.gridLoad(qsriq,jzriq);
		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(resultJson.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/chartLoad")
	public void chartLoad(@RequestParam(defaultValue="") String qsriq,@RequestParam(defaultValue="") String jzriq,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.chartLoad(qsriq,jzriq);
		String[][] a=new String[0][0];
		if(list.size()>0){
		String[][] array = Result.list2array(list,new String[]{"RIQ","RULML","RULRZ","RUCML","RUCRZ"});
		a=new String[array[0].length][array.length];
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				a[j][i]=array[i][j];
			}		
		}
		}else{
			
		}
//		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(a);
//		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/bingtkj")
	public void bingtkj(@RequestParam(defaultValue="") String yearmonth,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.bingtkj(yearmonth);
		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(resultJson.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/bingtgys")
	public void bingtgys(@RequestParam(defaultValue="") String yearmonth,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.bingtgys(yearmonth);
		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(resultJson.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/bingtmk")
	public void bingtmk(@RequestParam(defaultValue="") String yearmonth,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.bingtmk(yearmonth);
		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(resultJson.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/bingtmz")
	public void bingtmz(@RequestParam(defaultValue="") String yearmonth,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		List<Map<String, Object>> list = zonghzsDao.bingtmz(yearmonth);
		JSONObject resultJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		resultJson.put("datalist", jsonArray);
		try {
			writer = response.getWriter();
			writer.write(resultJson.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	@RequestMapping(value = "/tongzdb")
//	public void tongzdb(@RequestParam(defaultValue="") String yearmonth,HttpServletRequest request,HttpServletResponse response,HttpSession session){
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter writer  = null;
//		List<Map<String, Object>> list = zonghzsDao.tongzdb(yearmonth);
//		JSONObject resultJson = new JSONObject();
//		JSONArray jsonArray = JSONArray.fromObject(list);
//		resultJson.put("datalist", jsonArray);
//		try {
//			writer = response.getWriter();
//			writer.write(resultJson.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
