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

import com.zhiren.fuelmis.dc.dao.jih.YuedcaigjhDao;
import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.service.jih.IYuedcaigjhService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.JsonUtil;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/yuedcaigjh")
public class YuedcaigjhController {
	
	@Autowired
	private IYuedcaigjhService yuedcaigjhService;
	@Autowired
	private YuedcaigjhDao YuedcaigjhDao;
	
	
	@RequestMapping(value = "/yuedcaigjhcx")
	public void getYuedcaigjh(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		String diancid = request.getParameter("diancid");
		if(null == diancid || ""==diancid){
			diancid = "-1";
		}
		String tablehtml = yuedcaigjhService.getTabelData(diancid, riq);
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
	@RequestMapping(value="/getCaigList")
	public void getYuedCaigjhList(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String caoz = request.getParameter("caoz");//用于判断复制上月计划操作
		String riq = request.getParameter("riq");
		if(null == riq){
			riq = new SimpleDateFormat("yyyy-MM").format(new Date())+"-01";
		}
		String diancid = request.getParameter("diancid");
		if(null == diancid){
			diancid = "-1";
		}
		Map map = new HashMap();
		map.put("diancid",diancid);
		map.put("riq",riq );
		JSONObject jsonArray = yuedcaigjhService.getCaigData(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			if(null != caoz){
				if(!"{\"data\":[]}".equals(jsonArray.toString())){
				writer.write("1");//提示是否继续复制并替换
			}else{
				writer.write("0"); //复制上月计划操作
			}
			
		}else{
			writer.write(jsonArray.toString());
		}} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/yuedcaigjhcopy")
	public void yuedcaigjhcopy(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String caoz = request.getParameter("caoz");//如果操作为“replace”,那就删除本月记录
		String diancid = request.getParameter("diancid");
		
		String riq = request.getParameter("riq");//本月日期
		Date date = DateUtil.StringToDate(riq, "yyyy-MM-dd");
		Date lastMonth = DateUtil.getLastMonth(date);
		String lastriq = DateUtil.dateToString(lastMonth,"yyyy-MM-dd");//上月日期
		
		Map map = new HashMap();
		map.put("diancid",diancid);
		map.put("riq",riq );
		map.put("lastriq",lastriq );
		String reback;
		List copylist = yuedcaigjhService.getyueducaigByDiancidAndRiq(map);
		if("[]" == copylist.toString()){
			reback = "上月计划无数据，无法复制！！！";
		}else{
			if(null != caoz){
				yuedcaigjhService.DelYuedcaigByDiancidAndRiq(map);//先删除已有的值
			}
			map.put("copylist", copylist);
			int  ret = yuedcaigjhService.CopyYuedcaigData(map);
			if(ret>0){
					reback="复制上月计划成功！！！";
			}else{
					reback="复制上月计划失败！！！";
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/Caigadd")
	public void getYuedcaiglu(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		int ret = yuedcaigjhService.addCaigData(map);
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
	
	
	@RequestMapping(value="/getyuedcaigById")
	public void getYuedcaigById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		JSONObject jsonArray = yuedcaigjhService.getYuedcaigById(id);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/Caigdel")
	public void delYuedcaiglu(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		int ret = yuedcaigjhService.delCaigById(id);
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
	@RequestMapping(value="/Caigupdate")
	public void updateYuedcaiglu(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		int ret = yuedcaigjhService.updateCaigById(map);
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
	@RequestMapping(value="/getPinz")
	public void getPinz(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<HashMap<String, Object>> list = yuedcaigjhService.getPinz();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox();
			jsonArray.add(combobox);
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
	
	@SuppressWarnings({"unchecked" })
	@RequestMapping(value="/getgongys")
	public void getGongys(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<HashMap<String, Object>> list = yuedcaigjhService.getGongys();
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
	
	@SuppressWarnings({"unchecked" })
	@RequestMapping(value="/getFaz")
	public void getFaz(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		riq = riq.substring(0, riq.indexOf("-"))+"-01-01";
		PrintWriter writer = null;
		List<HashMap<String, Object>> list = yuedcaigjhService.getFaz(riq);
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
	@SuppressWarnings({"unchecked" })
	@RequestMapping(value="/getJihkj")
	public void getJihkj(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<HashMap<String, Object>> list = yuedcaigjhService.getJihkj();
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
		String riq = request.getParameter("riq")+"-01";
		String diancid = request.getParameter("diancid");
		Map map = new HashMap();
		map.put("riq",riq);
		map.put("diancid", diancid);
		PrintWriter writer = null;
		String state = YuedcaigjhDao.getshenpstate(map);
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
