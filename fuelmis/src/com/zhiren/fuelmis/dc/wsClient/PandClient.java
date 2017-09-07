package com.zhiren.fuelmis.dc.wsClient;

import java.util.Map;

import javax.activation.DataHandler;
import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

/** 
 * @author 陈宝露
 */
public class PandClient {
	public boolean uploadPand(Map<String, Object> map,DataHandler handler,String fileName,String diancxxb_id) {
		String[] arr = new String[1];
		if (!map.isEmpty()) {
			arr[0] = map.get("ID").toString();
			arr[1] = map.get("RIQ").toString();
			arr[2] = map.get("DIANCXXB_ID").toString();
			arr[3] = map.get("ZHANGMKC").toString();
			arr[4] = map.get("SHIPKC").toString();
			arr[5] = map.get("CHANGSL").toString();
			arr[6] = map.get("SHUIFCTZL").toString();
			arr[7] = map.get("YINGKD").toString();
			arr[8] = map.get("FUJZT").toString();
			arr[9] = map.get("FUJMC").toString();
			arr[10] = map.get("ZHUANGT").toString();
		}
		Object[] objs = new Object[] { arr,handler,fileName };
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference(
					"http://10.65.17.197:9000/axis2/services/PandService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2", "uploadPand");
			serviceClient.invokeBlocking(qname, objs);
			return true;
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static PandClient getinstance(){
		return new PandClient();
	}
}
