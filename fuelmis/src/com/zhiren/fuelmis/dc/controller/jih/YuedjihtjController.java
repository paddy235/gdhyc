package com.zhiren.fuelmis.dc.controller.jih;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.dao.jih.YuedjhtjDao;
import com.zhiren.fuelmis.dc.dao.jih.reback.RebackYuedJhDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.jih.IYuedjhtjService;
import com.zhiren.fuelmis.dc.utils.PropertiesUtil;
/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/yuedjh")
public class YuedjihtjController {
	
	@Autowired
	private IYuedjhtjService yuedjhtjService;
	@Autowired
	private YuedjhtjDao yuedjhtjDao;
	@Autowired
	private RebackYuedJhDao rebackyuedJhDao;
	@RequestMapping(value = "yuedjhrefresh")
	public void getYuedjhrefresh(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		String diancid = request.getParameter("diancid");
		if(riq==null || "".equals(riq.trim())){
			riq = new SimpleDateFormat("yyyy-MM").format(new Date());
		}
		if(null == diancid || ""==diancid){
			diancid = "-1";
		}
		String tablehtml  = yuedjhtjService.getTableHtml(diancid,riq);
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
	@RequestMapping(value = "/getShenpState")
	public void getShenpState(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		String diancid = request.getParameter("diancid");
		if(null == riq || ""==riq){
			riq = new SimpleDateFormat("yyyy-MM").format(new Date());
		}
		if(null == diancid || ""==diancid){
			diancid = "-1";
		}
		Map map = new HashMap();
		map.put("riq", riq+"-01");
		map.put("diancid", diancid);
		
		String state  = yuedjhtjDao.getSanjshenpState(map)+"";
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "yuedjhtj")
	public void yuedjhtj(HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		String diancid = request.getParameter("diancid");
		if(null == riq || ""==riq){
			riq = new SimpleDateFormat("yyyy-MM").format(new Date());
		}
		if(null == diancid || ""==diancid){
			diancid = "-1";
		}
		String[] date = riq.split("-");
		String year = date[0];
		String month = date[1];
 
			Map statemap = new HashMap();
			statemap.put("riq", riq+"-01");
			statemap.put("diancxxb_id",diancid);
			statemap.put("zhuangt","1");
			rebackyuedJhDao.updateYuedjhZhib(statemap);
			rebackyuedJhDao.updateYuedjhZaf(statemap);
			rebackyuedJhDao.updateYuedjhCaig(statemap);
 
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write("流程启动成功!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
