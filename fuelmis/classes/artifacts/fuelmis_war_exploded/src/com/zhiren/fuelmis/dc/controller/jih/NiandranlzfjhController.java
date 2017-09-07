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

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.dao.jih.NiandRanlzfjhDao;
import com.zhiren.fuelmis.dc.service.jih.INiandRanlzfjhService;
import com.zhiren.fuelmis.dc.utils.JsonUtil;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/niandranlzfjh")
public class NiandranlzfjhController {
	
	@Autowired
	private INiandRanlzfjhService niandRanlzfjhService;
	@Autowired
	private NiandRanlzfjhDao  niandRanlzfjhDao;
	
	@RequestMapping(value = "/niandranlzfjhcx")
	public void getNiandranlzfTable(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String year = request.getParameter("year");
		String diancid = request.getParameter("diancid");
		if(null == diancid || ""==diancid){
			diancid = "-1";
		}
		String tablehtml = niandRanlzfjhService.getTabelData(diancid, year);
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
	public void getNiandranlzfjh(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String caoz = request.getParameter("caoz");//用于判断复制上年计划操作
		String riq = request.getParameter("riq");//本年日期
		String diancid = request.getParameter("diancid");
		if(null == diancid){
			diancid = "-1";
		}
		if(null == riq){
			riq = new SimpleDateFormat("yyyy").format(new Date())+"-01-01";
		}
		Map map = new HashMap();
		map.put("diancid",diancid);
		map.put("riq",riq );
		JSONObject jsonArray = niandRanlzfjhService.getRanlzfData(map);
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
	@RequestMapping(value="/niandralzfjhcopy")
	public void Niandralzfjhcopy(HttpServletRequest request , HttpServletResponse response , HttpSession session){
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
		List copylist = niandRanlzfjhService.getRanlzfByDiancidAndRiq(map);
		if("[]" == copylist.toString()){
			reback = "上年计划无数据，无法复制！！！";
		}else{
			if(null != caoz){
				niandRanlzfjhService.DelRanlzfByDiancidAndRiq(map);//先删除已有的值
			}
			map.put("copylist", copylist);
			int  ret = niandRanlzfjhService.CopyRanlzfData(map);
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
	
	@RequestMapping(value="/getRanlzfById")
	public void getRanlzfById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		JSONObject jsonArray = niandRanlzfjhService.getRanlzfById(id);
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
	public void addNiandranlzf(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		int ret = niandRanlzfjhService.addRanlzfData(map);
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
	public void delNiandranlzf(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		int ret = niandRanlzfjhService.delRanlzfById(id);
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
	public void updateNiandranlzf(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		int ret = niandRanlzfjhService.updateRanlzfById(map);
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
		String state = niandRanlzfjhDao.getshenpstate(map);
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
