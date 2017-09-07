package com.zhiren.fuelmis.dc.controller.cnfy;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.dao.cnfy.NiandYussqDao;
import com.zhiren.fuelmis.dc.service.cnfy.INiandyussqService;
import com.zhiren.fuelmis.dc.utils.JsonUtil;




import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/niandyussq")
public class NiandYussqController {
	
	@Autowired
	private INiandyussqService niandyussqService ;
	@Autowired
	private NiandYussqDao niandYussqDao;
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	@RequestMapping(value = "/yuscx")
	public void getYuedcaigjh(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String nianf = request.getParameter("nianf");
		if(null == nianf){
			nianf = new Date().getYear()+"";
		}
		String diancid = request.getParameter("diancid");
		if(null == diancid){
			diancid = "-1";
		}
		Map map = new HashMap();
		map.put("diancid",diancid);
		map.put("nianf",nianf );
		JSONObject jsonArray = niandyussqService.getYussqData(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/niandysadd")
	public void addNiandys(HttpServletRequest request , HttpServletResponse response , HttpSession session) throws UnsupportedEncodingException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		int ret = niandyussqService.addNiandysData(map);
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
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/delniandys")
	public void delNiandyus(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		int ret = niandyussqService.delNiandyusById(id);
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
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/getniandyusById")
	public void getNiandyusById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		JSONObject jsonArray = niandyussqService.getNinadyusById(id);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/submitToGuod")
	public void submitToGuod(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String nianf = request.getParameter("nianf").toString().substring(0, 4);
		String diancid = request.getParameter("diancid");
		Map map = new HashMap();
		map.put("nianf",nianf );
		map.put("diancid",diancid );
		List list = niandYussqDao.getsubmitdatas(map);
		JSONArray array1 = JSONArray.fromObject(list);
		JSONArray array = transToArray(array1);
		JSONObject obj = new JSONObject();
		obj.put("niandysdata", array);
		String render ="success";
		String ret = null;
		String value = obj.toString();
		System.out.println(value);
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
			    QName opAddEntry = new QName("http://ws.apache.org/axis2", "niandys");  
			    //  调用sayHelloToPerson方法并输出该方法的返回值  
			   ret = (String) serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];  
			} catch (Exception e) {
				e.printStackTrace();
			}
		if(ret !=null &&ret.equals("1")){
			map.put("zhuangt", 1);
			niandYussqDao.updateState(map);
		}else{
			render = "fail";
		}
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(render);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/niandysupdate")
	public void updateNiandys(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		int ret = niandyussqService.updateNiandysyId(map);
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
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getzhuangt")
	public void getzhuangt(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String nianf = request.getParameter("nianf");
		String diancid = request.getParameter("diancid");
		Map map = new HashMap();
		map.put("diancid", diancid);
		map.put("nianf", nianf);
		String Zhuangt = niandYussqDao.getZhuangt(map);
		if(null == Zhuangt){
			Zhuangt = "0";
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(Zhuangt);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
