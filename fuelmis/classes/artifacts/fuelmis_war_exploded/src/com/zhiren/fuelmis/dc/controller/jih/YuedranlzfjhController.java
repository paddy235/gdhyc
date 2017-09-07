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

import com.zhiren.fuelmis.dc.dao.jih.YuedRanlzfjhDao;
import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.service.jih.IYuedRanlzfjhService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.JsonUtil;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/yuedranlzfjh")
public class YuedranlzfjhController {
	
	@Autowired
	private IYuedRanlzfjhService yuedRanlzfjhService;
	@Autowired
	private YuedRanlzfjhDao  yuedRanlzfjhDao;
	
	@RequestMapping(value = "/yuedranlzfjhcx")
	public void getYuedranlzfTable(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		String diancid = request.getParameter("diancid");
		if(null == diancid || ""==diancid){
			diancid = "-1";
		}
		String tablehtml = yuedRanlzfjhService.getTabelData(diancid, riq);
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
	@RequestMapping(value="/getRanlzfList")
	public void getYuedranlzfjh(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String caoz = request.getParameter("caoz");//用于判断复制上月计划操作
		String riq = request.getParameter("riq");//本月日期
		String diancid = request.getParameter("diancid");
		if(null == diancid){
			diancid = "-1";
		}
		if(null == riq){
			riq = new SimpleDateFormat("yyyy-MM").format(new Date())+"-01";
		}
		Map map = new HashMap();
		map.put("diancid",diancid);
		map.put("riq",riq );
		JSONObject jsonArray = yuedRanlzfjhService.getRanlzfData(map);
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
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/yuedralzfjhcopy")
	public void yuedralzfjhcopy(HttpServletRequest request , HttpServletResponse response , HttpSession session){
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
		List copylist = yuedRanlzfjhService.getRanlzfByDiancidAndRiq(map);
		if( "[]" == copylist.toString()){
			reback = "上月计划无数据，无法复制！！！";
		}else{
			if(null != caoz){
				yuedRanlzfjhService.DelRanlzfByDiancidAndRiq(map);//先删除已有的值
			}
			map.put("copylist", copylist);
			int  ret = yuedRanlzfjhService.CopyRanlzfData(map);
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
	
	@RequestMapping(value="/getRanlzfById")
	public void getRanlzfById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		JSONObject jsonArray = yuedRanlzfjhService.getRanlzfById(id);
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
	@RequestMapping(value="/ranlzfadd")
	public void addYuedranlzf(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		int ret = yuedRanlzfjhService.addRanlzfData(map);
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
	
	@RequestMapping(value="/Ranlzfdel")
	public void delYuedcaiglu(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		int ret = yuedRanlzfjhService.delRanlzfById(id);
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
	@RequestMapping(value="/Ranlzfupdate")
	public void updateYuedcaiglu(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		int ret = yuedRanlzfjhService.updateRanlzfById(map);
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
	
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value="/getZfmingcById")
	public void getZfmingcById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<HashMap<String, Object>> list = yuedRanlzfjhService.getZafmingc();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = null;
//			jsonArray.add(combobox);
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("MINGC"), list.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
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
		String state = yuedRanlzfjhDao.getshenpstate(map);
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
