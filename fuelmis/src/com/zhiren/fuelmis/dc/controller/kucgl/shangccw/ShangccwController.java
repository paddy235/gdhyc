package com.zhiren.fuelmis.dc.controller.kucgl.shangccw;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.codehaus.xfire.client.Client;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.InputSource;

import com.zhiren.fuelmis.dc.service.kucgl.shangccw.IShangccwService;
import com.zhiren.fuelmis.dc.utils.FileUtil;
import com.zhiren.fuelmis.dc.utils.PropertiesUtil;
import com.zhiren.fuelmis.dc.utils.formular.Result;

/**
 * @author 陈宝露
 */
@Controller
@RequestMapping("/kucgl/shangccw")
public class ShangccwController {
	@Autowired
	private IShangccwService shangccwService;

	@RequestMapping(value = "/getList")
	public @ResponseBody JSONArray getList(@RequestParam(defaultValue = "") String search, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = JSONObject.fromObject(search);
		String[][] list = shangccwService.getList(map);
		if (list != null) {
			return JSONArray.fromObject(list);
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/shangbcw")
	public @ResponseBody JSONObject shangbcw(@RequestParam(defaultValue = "") String ids, String search,
			HttpServletRequest request, HttpServletResponse response, Object renyxx) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String> xmlMap = null;
		List<String> idList = JSONArray.fromObject(ids);
		// 1.根据id查询数据
		List<Map<String, Object>> list = null;
		Map<String, Object> map = JSONObject.fromObject(search);
		String leix = map.get("leix").toString();
		list = shangccwService.getList4Xml(idList, map);
		// 2.将数据生成xml
		if (list != null && list.size() != 0) {
			String xmlData = Result.list2XmlByte(list);
			try {
				FileUtil.writeFile(xmlData.getBytes(), new File("d:/data.xml"));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// 3.调用接口
			// ----------------------------------------开始提及流程---------------------------------------------------------------------------------
			String caiwurl = PropertiesUtil.getValue("caiw_url");
			// -------------------------------------------------new-------------------------------------------------------------------------------
			try {
				// 使用RPC方式调用WebService
				RPCServiceClient serviceClient = new RPCServiceClient();
				Options options = serviceClient.getOptions();
				String url = "";
				QName opAddEntry = null;
				if (leix.equals("0")) {
					url = caiwurl + "JsywdBillService/main.wsdl";
					// 指定要调用的计算机器中的方法及WSDL文件的命名空间：edu.sjtu.webservice。
					opAddEntry = new QName("http://soaware.ygsoft.com", "receiveJsywd");// 加法

				} else if (leix.equals("1")) {
					url = caiwurl + "RmgjdBillService/main.wsdl";
					// 指定要调用的计算机器中的方法及WSDL文件的命名空间：edu.sjtu.webservice。
					opAddEntry = new QName("http://soaware.ygsoft.com", "receiveRmgjd");// 加法
				} else {
					url = caiwurl + "RmhyftdBillService/main.wsdl";
					// 指定要调用的计算机器中的方法及WSDL文件的命名空间：edu.sjtu.webservice。
					opAddEntry = new QName("http://soaware.ygsoft.com", "receiveRmhyftd");// 加法
				}
				// 指定调用WebService的URL
				EndpointReference targetEPR = new EndpointReference(url);
				options.setTo(targetEPR);
				// 指定plus方法的参数值为两个，分别是加数和被加数
				Object[] opAddEntryArgs = new Object[] { xmlData };
				// 指定plus方法返回值的数据类型的Class对象
				Class[] classes = new Class[] { String.class };
				// 调用plus方法并输出该方法的返回值
				String resulXML = serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0].toString();
				System.out.println(resulXML);
				xmlMap = this.getXMLMap(resulXML);
				/*if (xmlMap.get("RSP_CODE").equals("1")) {
					throw new Exception(xmlMap.get("RSP_MSG"));
				}*/
			} catch (Exception e) {
				e.printStackTrace();
			}
			// ------------------------------------------------------------------------------------------------------------------------
			// 4.更新状态shangccwzt=1
			if (leix.equals("0")&&xmlMap.get("RSP_CODE").equals("0")) {
				shangccwService.updateJiesdShangccwzt(idList);
			} else {
				// shangccwService.updateMonthTotalShangccwzt(idList);
			}
		}
		return JSONObject.fromObject(xmlMap);
	}

	public Map<String, String> getXMLMap(String xmlstr) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			SAXBuilder builder = new SAXBuilder();
			StringReader sr = new StringReader(xmlstr);
			InputSource is = new InputSource(sr);
			Document doc = builder.build(is);
			Element root = doc.getRootElement();
			List lstDataRecords = root.getChildren();
			int intChildrenLen = lstDataRecords.size();
			for (int i = 0; i < intChildrenLen; ++i) {
				Element e1 = (Element) lstDataRecords.get(i);
				map.put("RSP_CODE", e1.getChildText("rsp_code"));
				map.put("RSP_MSG", e1.getChildText("rsp_msg"));
			}
			return map;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}