package com.zhiren.fuelmis.dc.controller.cnfy;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;

//import com.sun.jna.platform.win32.ObjBase;

import com.zhiren.fuelmis.dc.dao.cnfy.ZafhtDao;
import com.zhiren.fuelmis.dc.entity.jih.Hetbean;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.cnfy.IZafhtService;
import com.zhiren.fuelmis.dc.utils.JsonUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/zafht")
public class ZafhtController {
	
	@Autowired
	private IZafhtService zafhtService ;
	
	@Autowired
	private ZafhtDao zafhtDao;
	
	@RequestMapping(value = "/getZafhtByNianf")
	public void getZafhtByNianf(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String nianf = request.getParameter("nianf");
		JSONObject jsonArray = zafhtService.getZafhtByNianf(nianf);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/getcnfyxm")
	public void getcnfyxm(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		List cnfyList = zafhtDao.getCnfyxm();
		JSONArray jsonarr = JSONArray.fromObject(cnfyList);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonarr.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/zafhetadd")
	public void addZafhet(HttpServletRequest request , HttpServletResponse response , HttpSession session) throws UnsupportedEncodingException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//mybatis将参数以实体类作为参数，这样插入的时候将id返回给实体类的id属性，通过实体类获取到插入记录的id值.
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		Hetbean hetBean = new Hetbean();
		hetBean.setMingc(map.get("MINGC").toString());
		hetBean.setBianh(map.get("BIANH").toString());
		hetBean.setJiaf(map.get("JIAF").toString());
		hetBean.setYif(map.get("YIF").toString());
		hetBean.setQiandrq(map.get("QIANDRQ").toString());
		hetBean.setJiaysfzr(map.get("JIAYSFZR").toString());
		hetBean.setWeiyzr(map.get("WEIYZR").toString());
		hetBean.setFukfs(map.get("FUKFS").toString());
		hetBean.setAnqzayd(map.get("ANQZAFMYD").toString());
		hetBean.setYouxksrq(map.get("QISRQ").toString());
		hetBean.setYouxjsrq(map.get("JIESRQ").toString());
		hetBean.setQit(map.get("QIT").toString());
		hetBean.setQianddd(map.get("QIANDDD").toString());
		hetBean.setQianzy(map.get("QIANZY").toString());
		int het_flag = zafhtService.addhetData(hetBean);
		int hetzaf_flag = 0;
		if(het_flag==1){
			long hetid = hetBean.getId();
			//一个合同有多项费用，获取费用的id和费用的值，遍历出来后插入到hetzafb
			Map map1 = JsonUtil.parseJSON2Map(request.getParameter("cnfy"));
			Iterator it = map1.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry entry = (Entry) it.next();
				Map hetzafMap = new HashMap();
				hetzafMap.put("zafid",entry.getKey());
				hetzafMap.put("zafzhi",entry.getValue());
				hetzafMap.put("hetid",hetid);
				hetzaf_flag = zafhtService.saveHetZaf(hetzafMap);
				if(hetzaf_flag!=1){
					break;
				}
			}
		}
		String reback;
		if(het_flag>0&&hetzaf_flag>0){
			reback="保存成功！！！";
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/zafhetupdate")
	public void zafhetupdate(HttpServletRequest request , HttpServletResponse response , HttpSession session) throws UnsupportedEncodingException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//mybatis将参数以实体类作为参数，这样插入的时候将id返回给实体类的id属性，通过实体类获取到插入记录的id值.
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		
		int het_flag = zafhtService.updatehetData(map);
		int hetzaf_flag = 0;
		if(het_flag==1){
			//一个合同有多项费用，获取费用的id和费用的值，遍历出来后插入到hetzafb
			Map map1 = JsonUtil.parseJSON2Map(request.getParameter("cnfy"));
			Iterator it = map1.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry entry = (Entry) it.next();
				Map hetzafMap = new HashMap();
				hetzafMap.put("zafid",entry.getKey());
				hetzafMap.put("zafzhi",entry.getValue());
				hetzafMap.put("hetid",map.get("ID").toString());
				hetzaf_flag = zafhtService.updatehetzafData(hetzafMap);
				if(hetzaf_flag!=1){
					break;
				}
			}
		}
		String reback;
		if(het_flag>0&&hetzaf_flag>0){
			reback="保存成功！！！";
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
	
	
	
	@RequestMapping(value="/delhetbyid")
	public void delHetById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		int ret = zafhtService.delHetById(id);
		int ret1 = zafhtService.delHetzafById(id);
		String reback;
		if(ret1>0&&ret>0){
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
	
	@RequestMapping(value="/getzafhtById")
	public void getzafhtById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		JSONObject jsonObj = zafhtService.getZafhtById(id);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/getupdatecnfyxm")
	public void getupdatecnfyxm(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		List cnfyxmList = zafhtDao.getUpdateCnfyxm(id);
		JSONArray jsonarr = JSONArray.fromObject(cnfyxmList);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonarr.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 提交至国电
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/submit")
	public void submit(HttpServletRequest request , HttpServletResponse response , HttpSession session,String search){
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map=JSONObject.fromObject(search);
		zafhtService.submit(map);
//		List<Map<String,Object>> list = zafhtDao.getsubmitdatas(map);

//		JSONArray array1 = JSONArray.fromObject(list);
//		JSONArray array = transToArray(array1);
//		JSONObject obj = new JSONObject();
//		obj.put("yuedysdata", array);
//		String render ="success";
//		String ret = null;
//		String value = obj.toString();
//		try {
//			//  使用RPC方式调用WebService
//			RPCServiceClient serviceClient = new RPCServiceClient();
//			Options options = serviceClient.getOptions();
//			//  指定调用WebService的URL
//			EndpointReference targetEPR = new EndpointReference(
//					"http://localhost:8088/gddlcnfy/services/GdcnfyService");
//			options.setTo(targetEPR);
//			//  指定sayHelloToPerson方法的参数值
//			Object[] opAddEntryArgs = new Object[] {value};
//			//  指定sayHelloToPerson方法返回值的数据类型的Class对象
//			Class[] classes = new Class[] {String.class};
//			//  指定要调用的sayHelloToPerson方法及WSDL文件的命名空间
//			QName opAddEntry = new QName("http://ws.apache.org/axis2", "yuedys");
//			//  调用sayHelloToPerson方法并输出该方法的返回值
//			ret = (String) serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if(ret !=null &&ret.equals("1")){
//			map.put("zhuangt", 1);
//			yuedYussqDao.updateState(map);
//		}else{
//			render = "fail";
//		}
//		PrintWriter writer  = null;
//		try {
//			writer = response.getWriter();
//			writer.write(render);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getzhuangt")
	public void getzhuangt(HttpServletRequest request , HttpServletResponse response , HttpSession session,String search){
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map =JSONObject.fromObject(search);
		String Zhuangt = zafhtDao.getZhuangt(map);
		if(null == Zhuangt){
			Zhuangt = "0";
		}
		//插入记录
		Renyxx renyxx=(Renyxx)session.getAttribute("user");
		String shenpr=renyxx.getMingc();
		map.put("SHENPR",shenpr);
		map.put("YEWLX","杂费合同审批");
		map.put("BEIZ","-");
		map.put("ID", Sequence.nextId());
		zafhtDao.insertShenpjl(map);
		//返回
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(Zhuangt);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
