package com.zhiren.fuelmis.dc.wsClient;

import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

/**
 * @author 陈宝露
 */
public class FoundationDataClient {
	// 上传供应商
	@SuppressWarnings("rawtypes")
	public String[] uploadGongysb(List<Map<String, Object>> list) {
		String[][] arrs = new String[list.size()][];
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (!map.isEmpty()) {
					String[] arr = new String[map.size()];
					arr[0] = map.get("ID")!=null?map.get("ID").toString():"";
					arr[1] = map.get("FUID")!=null?map.get("FUID").toString():"";
					arr[2] = map.get("XUH")!=null?map.get("XUH").toString():"";
					arr[3] = map.get("BIANM")!=null?map.get("BIANM").toString():"";
					arr[4] = map.get("MINGC")!=null?map.get("MINGC").toString():"";
					arr[5] = map.get("QUANC")!=null?map.get("QUANC").toString():"";
					arr[6] = map.get("PINY")!=null?map.get("PINY").toString():"";
					arr[7] = map.get("DANWDZ")!=null?map.get("DANWDZ").toString():"";
					arr[8] = map.get("FADDBR")!=null?map.get("FADDBR").toString():"";
					arr[9] = map.get("WEITDLR")!=null?map.get("WEITDLR").toString():"";
					arr[10] = map.get("KAIHYH")!=null?map.get("KAIHYH").toString():"";
					arr[11] = map.get("ZHANGH")!=null?map.get("ZHANGH").toString():"";
					arr[12] = map.get("LEIX")!=null?map.get("LEIX").toString():"";
					arr[13] = map.get("SHENGFB_ID")!=null?map.get("SHENGFB_ID").toString():"";
					arr[14] = map.get("SHANGJGSBM")!=null?map.get("SHANGJGSBM").toString():"";
					arr[15] = map.get("ZHUANGT")!=null?map.get("ZHUANGT").toString():"";
					arrs[i] = arr;
				}
			}
		}
		Object[] objs = new Object[] { arrs };
		Class[] classes = new Class[] { String[].class };
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference("http://10.65.17.197:9000/axis2/services/FoundationDataService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2","uploadGongysb");
			return (String[])serviceClient.invokeBlocking(qname, objs, classes)[0];
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// 上传煤矿信息
	@SuppressWarnings("rawtypes")
	public String[] uploadMeikxx(List<Map<String, Object>> list) {
		String[][] arrs = new String[list.size()][];
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (!map.isEmpty()) {
					String[] arr = new String[map.size()];
					arr[0] = map.get("ID")!=null?map.get("ID").toString():"";
					arr[1] = map.get("XUH")!=null?map.get("XUH").toString():"";
					arr[2] = map.get("BIANM")!=null?map.get("BIANM").toString():"";
					arr[3] = map.get("MINGC")!=null?map.get("MINGC").toString():"";
					arr[4] = map.get("QUANC")!=null?map.get("QUANC").toString():"";
					arr[5] = map.get("PINY")!=null?map.get("PINY").toString():"";
					arr[6] = map.get("SHENGFB_ID")!=null?map.get("SHENGFB_ID").toString():"";
					arr[7] = map.get("LEIB")!=null?map.get("LEIB").toString():"";
					arr[8] = map.get("JIHKJB_ID")!=null?map.get("JIHKJB_ID").toString():"";
					arr[9] = map.get("LEIX")!=null?map.get("LEIX").toString():"";
					arr[10] = map.get("SHANGJGSBM")!=null?map.get("SHANGJGSBM").toString():"";
					arr[11] = map.get("MEIKDQ_ID")!=null?map.get("MEIKDQ_ID").toString():"";
					arr[12] = map.get("ZHUANGT")!=null?map.get("ZHUANGT").toString():"";
					arr[13] = map.get("SHIYZT")!=null?map.get("SHIYZT").toString():"";
					arrs[i] = arr;
				}
			}
		}
		Object[] objs = new Object[] { arrs };
		Class[] classes = new Class[] { String[].class };
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference("http://10.65.17.197:9000/axis2/services/FoundationDataService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2","uploadMeikxx");
			return (String[])serviceClient.invokeBlocking(qname, objs, classes)[0];
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// 上传车站信息
	@SuppressWarnings("rawtypes")
	public String[] uploadChezxx(List<Map<String, Object>> list) {
		String[][] arrs = new String[list.size()][];
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (!map.isEmpty()) {
					String[] arr = new String[map.size()];
					arr[0] = map.get("ID")!=null?map.get("ID").toString():"";
					arr[1] = map.get("XUH")!=null?map.get("XUH").toString():"";
					arr[2] = map.get("BIANM")!=null?map.get("BIANM").toString():"";
					arr[3] = map.get("MINGC")!=null?map.get("MINGC").toString():"";
					arr[4] = map.get("QUANC")!=null?map.get("QUANC").toString():"";
					arr[5] = map.get("PINY")!=null?map.get("PINY").toString():"";
					arr[6] = map.get("LUJXXB_ID")!=null?map.get("LUJXXB_ID").toString():"";
					arr[7] = map.get("LEIB")!=null?map.get("LEIB").toString():"";
					arrs[i] = arr;
				}
			}
		}
		Object[] objs = new Object[] { arrs };
		Class[] classes = new Class[] { String[].class };
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference("http://10.65.17.197:9000/axis2/services/FoundationDataService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2","uploadChezxx");
			return (String[])serviceClient.invokeBlocking(qname, objs, classes)[0];
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// 上传煤矿地区
	@SuppressWarnings("rawtypes")
	public String[] uploadMeikdq(List<Map<String, Object>> list) {
		String[][] arrs = new String[list.size()][];
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (!map.isEmpty()) {
					String[] arr = new String[map.size()];
					arr[0] = map.get("ID")!=null?map.get("ID").toString():"";
					arr[1] = map.get("XUH")!=null?map.get("XUH").toString():"";
					arr[2] = map.get("BIANM")!=null?map.get("BIANM").toString():"";
					arr[3] = map.get("MINGC")!=null?map.get("MINGC").toString():"";
					arr[4] = map.get("QUANC")!=null?map.get("QUANC").toString():"";
					arr[5] = map.get("SHENGFB_ID")!=null?map.get("SHENGFB_ID").toString():"";
					arr[6] = map.get("ZHUANGT")!=null?map.get("ZHUANGT").toString():"";
					arrs[i] = arr;
				}
			}
		}
		Object[] objs = new Object[] { arrs };
		Class[] classes = new Class[] { String[].class };
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference("http://10.65.17.197:9000/axis2/services/FoundationDataService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2","uploadMeikdq");
			return (String[])serviceClient.invokeBlocking(qname, objs, classes)[0];
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// 上传煤种信息
	@SuppressWarnings("rawtypes")
	public String[] uploadMeizxx(List<Map<String, Object>> list) {
		String[][] arrs = new String[list.size()][];
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (!map.isEmpty()) {
					String[] arr = new String[map.size()];
					arr[0] = map.get("ID")!=null?map.get("ID").toString():"";
					arr[1] = map.get("XUH")!=null?map.get("XUH").toString():"";
					arr[2] = map.get("BIANM")!=null?map.get("BIANM").toString():"";
					arr[3] = map.get("MINGC")!=null?map.get("MINGC").toString():"";
					arrs[i] = arr;
				}
			}
		}
		Object[] objs = new Object[] { arrs };
		Class[] classes = new Class[] { String[].class };
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference("http://10.65.17.197:9000/axis2/services/FoundationDataService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2","uploadMeizxx");
			return (String[])serviceClient.invokeBlocking(qname, objs, classes)[0];
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// 上传品种信息
	@SuppressWarnings("rawtypes")
	public String[] uploadPinzxx(List<Map<String, Object>> list) {
		String[][] arrs = new String[list.size()][];
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (!map.isEmpty()) {
					String[] arr = new String[map.size()];
					arr[0] = map.get("ID")!=null?map.get("ID").toString():"";
					arr[1] = map.get("XUH")!=null?map.get("XUH").toString():"";
					arr[2] = map.get("BIANM")!=null?map.get("BIANM").toString():"";
					arr[3] = map.get("MINGC")!=null?map.get("MINGC").toString():"";
					arr[4] = map.get("PINY")!=null?map.get("PINY").toString():"";
					arr[5] = map.get("ZHUANGT")!=null?map.get("ZHUANGT").toString():"";
					arr[6] = map.get("PINZMS")!=null?map.get("PINZMS").toString():"";
					arr[7] = map.get("LEIB")!=null?map.get("LEIB").toString():"";
					arrs[i] = arr;
				}
			}
		}
		Object[] objs = new Object[] { arrs };
		Class[] classes = new Class[] { String[].class };
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference("http://10.65.17.197:9000/axis2/services/FoundationDataService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2","uploadPinzxx");
			return (String[])serviceClient.invokeBlocking(qname, objs, classes)[0];
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static FoundationDataClient getInstance(){
		return new FoundationDataClient();
	}
}
