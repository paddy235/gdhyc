package com.zhiren.fuelmis.dc.controller.jih;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
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

import com.zhiren.fuelmis.dc.dao.jih.NiandjhtjDao;
import com.zhiren.fuelmis.dc.dao.jih.YuedjhtjDao;
import com.zhiren.fuelmis.dc.dao.jih.reback.RebackNiandJhDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.jih.INiandjhtjService;
import com.zhiren.fuelmis.dc.utils.PropertiesUtil;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/niandjh")
public class NiandjihtjController {
	
	@Autowired
	private INiandjhtjService niandjhtjService;
	@Autowired
	private YuedjhtjDao yuedjhtjDao;
	@Autowired
	private NiandjhtjDao niandjhtjDao;
	@Autowired
	private RebackNiandJhDao rebackNiandJhDao;
	
	@RequestMapping(value = "/niandjhrefresh")
	public void niandjhrefresh(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String year = request.getParameter("year");
		String diancid = request.getParameter("diancid");
		if(null == year || ""==year){
			year = "0";
		}
		if(null == diancid || ""==diancid){
			diancid = "-1";
		}
		String tablehtml  = niandjhtjService.getTableHtml(diancid, year);
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
		String year = request.getParameter("year");
		String diancid = request.getParameter("diancid");
		if(null == year || ""==year){
			year = "0";
		}
		if(null == diancid || ""==diancid){
			diancid = "-1";
		}
		Map map = new HashMap();
		map.put("riq", year+"-01-01");
		map.put("diancid", diancid);
		String state  = niandjhtjDao.getSanjshenpState(map)+"";
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
	@RequestMapping(value = "niandjhtj")
	public void niandjhtj(HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		String year = request.getParameter("year");
		String diancid = request.getParameter("diancid");
		if(null == year || ""==year){
			year = "0";
		}
		if(null == diancid || ""==diancid){
			diancid = "-1";
		}
//		Renyxx renybean = (Renyxx) session.getAttribute("user");
//		String userid = renybean.getId()+"";
//		String ret = "";
//		//取得电厂名称
//		List dianclist = yuedjhtjDao.getdiancxx(diancid);
//		Map diancmap = (Map) dianclist.get(0); 
//		//取得电厂名称
//		String mingc = diancmap.get("MINGC").toString();
//		String quanc = diancmap.get("QUANC").toString();
//		//任务描述
//		String  renwms = "";
//		renwms = "单位："+mingc +"、计划名称："+year+"年年度计划";
		
//		//计划表单
//		String htmlcontent = "";
//		htmlcontent = niandjhtjService.getTableHtml(diancid, year);
//		String nian_cgjh = "";
//		String nian_zfjh = "";
//		String nain_zhib = "";
		
//		try {
///*		//  使用RPC方式调用WebService          
//		    RPCServiceClient serviceClient = new RPCServiceClient();  
//		    Options options = serviceClient.getOptions();  
//		    //  指定调用WebService的URL  
//		    String sanjspUrl = PropertiesUtil.getValue("sanjsp_url");
//		    EndpointReference targetEPR = new EndpointReference(  
//		    		sanjspUrl+"/services/SpstartService");  
//		    options.setTo(targetEPR);  
//		    //  指定sayHelloToPerson方法的参数值  
//		    Object[] opAddEntryArgs = new Object[] {year,"",diancid,mingc,quanc,userid,nian_cgjh,nian_zfjh,nain_zhib,renwms,htmlcontent};
//		    //  指定sayHelloToPerson方法返回值的数据类型的Class对象  
//		    Class[] classes = new Class[] {String.class};  
//		    //  指定要调用的sayHelloToPerson方法及WSDL文件的命名空间  
//		    QName opAddEntry = new QName("http://ws.apache.org/axis2", "saveJihsp");  
//		    //  调用sayHelloToPerson方法并输出该方法的返回值  
//		   ret = (String) serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];  */
//			String sanjurl = PropertiesUtil.getValue("sanjsp_url");
//			sanjurl +="/service/StartupService?wsdl";
//			Client client = new Client(new URL(sanjurl));
//			String[] parms = {year,"",diancid,mingc,quanc,userid,nian_cgjh,nian_zfjh,nain_zhib,renwms,htmlcontent};
//			Object[] r = client.invoke("saveJihsp",parms);
//			ret=r[0].toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if(ret.equals("1")){
			Map statemap = new HashMap();
			statemap.put("riq", year+"-01-01");
			statemap.put("diancxxb_id",diancid);
			statemap.put("zhuangt","1");
			rebackNiandJhDao.updateNiandjhZhib(statemap);
			rebackNiandJhDao.updateNiandjhZaf(statemap);
			rebackNiandJhDao.updateNiandjhCaig(statemap);
//			ret = "流程启动成功!";
//		}else{
//			ret ="流程启动失败!";
//		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write("流程启动成功!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
