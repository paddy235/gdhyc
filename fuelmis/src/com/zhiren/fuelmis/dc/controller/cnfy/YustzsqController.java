package com.zhiren.fuelmis.dc.controller.cnfy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.dao.cnfy.YustzsqDao;
import com.zhiren.fuelmis.dc.service.cnfy.IYustzsqService;
import com.zhiren.fuelmis.dc.utils.JsonUtil;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/yutzsq")
public class YustzsqController {
	
	@Autowired
	private IYustzsqService yustzsqService ;
	@Autowired
	private YustzsqDao yustzsqDao ;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/yustzcx")
	public void getYustzData(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String zafid = request.getParameter("zafid");
		if(null == zafid){
			zafid = "-1";
		}
		String diancid = request.getParameter("diancid");
		if(null == diancid){
			diancid = "-1";
		}
		Map map = new HashMap();
		map.put("diancid",diancid);
		map.put("zafid",zafid );
		JSONObject jsonArray = yustzsqService.getYustzsqData(map);
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
	@RequestMapping(value="/ystzadd")
	public void addYustz(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		int ret = yustzsqService.addYustzData(map);
		String reback;
		if(ret>0){
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
	
	@RequestMapping(value="/getYustzById")
	public void getYustzById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		JSONObject jsonArray = yustzsqService.getYustzById(id);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/delyustz")
	public void delYustz(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		String zhuangt = yustzsqDao.getstate(id);
		String reback;
		if(zhuangt.equals("1")){
			reback = "已提交，请勿重复提交！！！";
		}else if(zhuangt.equals("2")){
			reback = "已审核通过，请勿重复提交！！！";
		}else{
			int ret = yustzsqService.delYustzById(id);
			if(ret>0){
				reback="删除成功！！！";
			}else{
				reback="删除失败！！！";
			}
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
	/**
     * json大写转小写
     * 
     * @param jSONArray1 jSONArray1
     * @return JSONObject
     */
    @SuppressWarnings({ "unused", "rawtypes" })
	public static JSONObject transToLowerObject(JSONObject jSONArray1) {
        JSONObject jSONArray2 = new JSONObject();
        Iterator it = jSONArray1.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            Object object = jSONArray1.get(key);
            Object obj = object.getClass().toString();
            System.out.println(object.getClass().toString());
            if (object.getClass().toString().endsWith("String")||object.getClass().toString().endsWith("BigDecimal")) {
                jSONArray2.accumulate(key.toLowerCase(), object);
            } else if (object.getClass().toString().endsWith("JSONObject")) {
                jSONArray2.accumulate(key.toLowerCase(), transToLowerObject((JSONObject) object));
            } else if (object.getClass().toString().endsWith("JSONArray")) {
                jSONArray2.accumulate(key.toLowerCase(), transToArray(jSONArray1.getJSONArray(key)));
            }
        }
        return jSONArray2;
    }
	/**
     * jsonArray转jsonArray
     * 
     * @param jSONArray1 jSONArray1
     * @return JSONArray
     */
    public static JSONArray transToArray(JSONArray jSONArray1) {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray1.size(); i++) {
            Object jArray = jSONArray1.getJSONObject(i);
            if (jArray.getClass().toString().endsWith("JSONObject")) {
                jSONArray2.add(transToLowerObject((JSONObject) jArray));
            } else if (jArray.getClass().toString().endsWith("JSONArray")) {
                jSONArray2.add(transToArray((JSONArray) jArray));
            }
        }
        return jSONArray2;
    }
	/*
	 * 提交至国电
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/submitToGuod")
	public void submitToGuod(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		String zhuangt = yustzsqDao.getstate(id);
		String render ="提交成功！！！";
		if(zhuangt.equals("1")){
			render = "已提交，请勿重复提交！！！";
		}else if(zhuangt.equals("2")){
			render = "已审核通过，请勿重复提交！！！";
		}else{
			List list = yustzsqDao.getsubmitdatas(id);
			JSONArray array1 = JSONArray.fromObject(list);
			JSONArray array = transToArray(array1);
			JSONObject obj = new JSONObject();
			obj.put("yustz", array);
			String ret = null;
			String value = obj.toString();
			try {
				//  使用RPC方式调用WebService          
				    RPCServiceClient serviceClient = new RPCServiceClient();  
				    Options options = serviceClient.getOptions();  
				    //  指定调用WebService的URL  
				    EndpointReference targetEPR = new EndpointReference(  
				            "http://localhost:8088/gddlcnfy/services/GdcnfyService");  
				    options.setTo(targetEPR);  
				    //  指定sayHelloToPerson方法的参数值  
				    Object[] opAddEntryArgs = new Object[] {value};
				    //  指定sayHelloToPerson方法返回值的数据类型的Class对象  
				    Class[] classes = new Class[] {String.class};  
				    //  指定要调用的sayHelloToPerson方法及WSDL文件的命名空间  
				    QName opAddEntry = new QName("http://ws.apache.org/axis2", "yustz");  
				    //  调用sayHelloToPerson方法并输出该方法的返回值  
				   ret = (String) serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];  
				} catch (Exception e) {
					e.printStackTrace();
				}  
			if(ret !=null &&ret.equals("1")){
				yustzsqDao.updateState(id);
			}else{
				render = "提交失败！！！";
			}
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(render);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/Yustzupdate")
	public void updateYustz(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		int ret = yustzsqService.updateYustzById(map);
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
	
}
