package com.zhiren.fuelmis.dc.controller.jih;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.dao.jih.NiandcaigjhDao;
import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.service.jih.INiandcaigjhService;
import com.zhiren.fuelmis.dc.utils.JsonUtil;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/niandcaigjh")
public class NiandcaigjhController {
	
	@Autowired
	private INiandcaigjhService niandcaigjhService;
	@Autowired
	private NiandcaigjhDao niandcaigjhDao;
	
	@RequestMapping(value = "/niandcaigjhcx")
	public void getNiandcaigjh(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String year = request.getParameter("year");
		String diancid = request.getParameter("diancid");
		if(null == diancid || ""==diancid){
			diancid = "-1";
		}
		String tablehtml = niandcaigjhService.getTabelData(diancid, year);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(tablehtml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getCaigjhList")
	public void getNiandCaigjhList(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String caoz = request.getParameter("caoz");//用于判断复制上年计划操作
		String nianf = request.getParameter("nianf");//本年日期
		String diancid = request.getParameter("diancid");
		if(null == nianf){
			nianf = new SimpleDateFormat("YYYY").format(new Date());
		}
		if(null == diancid){
			diancid = "-1";
		}
		Map map = new HashMap();
		map.put("diancid",diancid);
		map.put("nianf",nianf );
		JSONObject jsonArray = niandcaigjhService.getNiandcaigData(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			if(null != caoz){
				if(!"{\"data\":[]}".equals(jsonArray.toString())){
					writer.write("1");//提示是否继续复制并替换
				}else{
					writer.write("0"); //复制上年计划操作
				}
				
			}else{
				writer.write(jsonArray.toString());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/niandcaigjhcopy")
	public void NiandCaigjhcopy(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String caoz = request.getParameter("caoz");//如果操作为“replace”,那就删除本年记录
		String diancid = request.getParameter("diancid");
		String nianf = request.getParameter("nianf");//今年
		long year =  Integer.parseInt(nianf)-1;
		String lastyear = year + "";//上一年
		Map map = new HashMap();
		map.put("diancid",diancid);
		map.put("nianf",nianf +"-01-01");
		map.put("lastyear",lastyear+"-01-01" );
		String reback;
		List copylist = niandcaigjhService.getNiandcaigByDiancidAndRiq(map);
		if("[]" == copylist.toString()){
			reback = "上年计划无数据，无法复制！！！";
		}else{
			if(null != caoz){
				niandcaigjhService.DelNiandcaigByDiancidAndRiq(map);//先删除已有的值
			}
			map.put("copylist", copylist);
			int  ret = niandcaigjhService.CopyNiandcaigData(map);
			if(ret>0){
					reback="复制上年计划成功！！！";
			}else{
					reback="复制上年计划失败！！！";
			}
		}
		
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(reback);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/getNiandCaigById")
	public void getNiandCaigById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		JSONObject jsonArray = niandcaigjhService.getNiandCaigById(id);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/caigjhadd")
	public void addNinadcaig(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		map.put("NIANF", map.get("NIANF")+"-01-01");
		int ret = niandcaigjhService.addCaigData(map);
		String reback;
		if(ret>0){
			reback="添加成功！！！";
		}else{
			reback="保存失败！！！";
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(reback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/Niandcaigjhdel")
	public void delNiandcaigById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		int ret = niandcaigjhService.delNiandcaigById(id);
		String reback;
		if(ret>0){
			reback="删除成功！！！";
		}else{
			reback="删除失败！！！";
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(reback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/caigjhupdate")
	public void caigjhupdate(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		map.put("NIANF",  map.get("NIANF")+"-01-01");
		int ret = niandcaigjhService.updateNiandcaigById(map);
		String reback;
		if(ret>0){
			reback="更新成功！！！";
		}else{
			reback="更新失败！！！";
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(reback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({"unchecked" })
	@RequestMapping(value="/getgongys")
	public void getGongys(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<HashMap<String, Object>> list = niandcaigjhService.getGongys();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox();
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("MINGC"), list.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@SuppressWarnings({"unchecked", "rawtypes" })
	@RequestMapping(value="/getshenpstate")
	public void getshenpstate(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String nianf = request.getParameter("nianf")+"-01-01";
		String diancid = request.getParameter("diancid");
		Map map = new HashMap();
		map.put("nianf",nianf);
		map.put("diancid", diancid);
		PrintWriter writer = null;
		String state = niandcaigjhDao.getshenpstate(map);
		if(state == null){
			state = "0";
		}
		try {
			writer = response.getWriter();
			writer.write(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
