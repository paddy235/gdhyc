package com.zhiren.fuelmis.dc.wsClient;

import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

/**
 * @author 陈宝露
 */
public class FahbClient {
	public boolean uploadFahb(Map<String, Object> map) {
		String[] arr = new String[1];
		if (!map.isEmpty()) {
			arr[0] = map.get("ID").toString();
			arr[1] = map.get("YUANID").toString();
			arr[2] = map.get("DIANCXXB_ID").toString();
			arr[3] = map.get("GONGYSB_ID").toString();
			arr[4] = map.get("MEIKXXB_ID").toString();
			arr[5] = map.get("PINZB_ID").toString();
			arr[6] = map.get("FAZ_ID").toString();
			arr[7] = map.get("DAOZ_ID").toString();
			arr[8] = map.get("JIHKJB_ID").toString();
			arr[9] = map.get("FAHRQ").toString();
			arr[10] = map.get("DAOHRQ").toString();
			arr[11] = map.get("HETB_ID").toString();
			arr[12] = map.get("ZHILB_ID").toString();
			arr[13] = map.get("JIESB_ID").toString();
			arr[14] = map.get("YUNSFSB_ID").toString();
			arr[15] = map.get("CHEC").toString();
			arr[16] = map.get("MAOZ").toString();
			arr[17] = map.get("PIZ").toString();
			arr[18] = map.get("JINGZ").toString();
			arr[19] = map.get("YINGD").toString();
			arr[20] = map.get("YINGK").toString();
			arr[21] = map.get("YUNS").toString();
			arr[22] = map.get("YUNSL").toString();
			arr[23] = map.get("KOUD").toString();
			arr[24] = map.get("KOUS").toString();
			arr[25] = map.get("KOUZ").toString();
			arr[26] = map.get("KOUM").toString();
			arr[27] = map.get("ZONGKD").toString();
			arr[28] = map.get("SANFSL").toString();
			arr[29] = map.get("CHES").toString();
			arr[30] = map.get("LIE_ID").toString();
			arr[31] = map.get("KUANGFZLB_ID").toString();
			arr[32] = map.get("LIUCB_ID").toString();
			arr[33] = map.get("LIUCZTB_ID").toString();
			arr[34] = map.get("HEDBZ").toString();
			arr[35] = map.get("DITJSBZ").toString();
			arr[36] = map.get("DITJSB_ID").toString();
			arr[37] = map.get("LIEID").toString();
			arr[38] = map.get("LAIMSL").toString();
			arr[39] = map.get("LAIMZL").toString();
			arr[40] = map.get("LAIMKC").toString();
		}
		Object[] objs = new Object[] { arr };
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference(
					"http://10.65.17.197:9000/axis2/services/FahbService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2", "uploadFahb");
			serviceClient.invokeBlocking(qname, objs);
			return true;
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static FahbClient getInstance() {
		return new FahbClient();
	}
}
