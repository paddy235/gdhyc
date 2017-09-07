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
public class MonthReportClient {
	//数量
	@SuppressWarnings("rawtypes")
	public String[] uploadYuesl(List<Map<String, Object>> list) {
		String[][] arrs = null;
		if (list != null && list.size() > 0) {
			arrs = new String[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (!map.isEmpty()) {
					String[] arr = new String[map.size()];
					arr[0] = map.get("ID").toString();
					arr[1] = map.get("YUETJKJB_ID").toString();
					arr[2] = map.get("FENX").toString();
					arr[3] = map.get("JINGZ").toString();
					arr[4] = map.get("BIAOZ").toString();
					arr[5] = map.get("YINGD").toString();
					arr[6] = map.get("KUID").toString();
					arr[7] = map.get("YUNS").toString();
					arr[8] = map.get("KOUD").toString();
					arr[9] = map.get("KOUS").toString();
					arr[10] = map.get("KOUZ").toString();
					arr[11] = map.get("KOUM").toString();
					arr[12] = map.get("ZONGKD").toString();
					arr[13] = map.get("SANFSL").toString();
					arr[14] = map.get("JIANJL").toString();
					arr[15] = map.get("RUCTZL").toString();
					arr[16] = map.get("ZHUANGT").toString();
					arr[17] = map.get("LAIMSL").toString();
					arr[18] = map.get("YINGDZJE").toString();
					arr[19] = map.get("KUIDZJE").toString();
					arr[20] = map.get("SUOPSL").toString();
					arr[21] = map.get("SUOPJE").toString();
					arrs[i] = arr;
				}
			}
		}
		Object[] objs = new Object[] { arrs };
		Class[] classes = new Class[] { String[].class };

		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference("http://10.65.17.197:9000/axis2/services/MonthReportService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2","uploadYuesl");
			return (String[]) serviceClient.invokeBlocking(qname, objs, classes)[0];
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//质量
	@SuppressWarnings("rawtypes")
	public String[] uploadYuezl(List<Map<String, Object>> list) {
		String[][] arrs = null;
		if (list != null && list.size() > 0) {
			arrs = new String[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (!map.isEmpty()) {
					String[] arr = new String[map.size()];
					arr[0] = map.get("MAD").toString();
					arr[1] = map.get("QBAD").toString();
					arr[2] = map.get("HAD").toString();
					arr[3] = map.get("VAD").toString();
					arr[4] = map.get("FCAD").toString();
					arr[5] = map.get("STD").toString();
					arr[6] = map.get("QBRAD").toString();
					arr[7] = map.get("HDAF").toString();
					arr[8] = map.get("QGRAD_DAF").toString();
					arr[9] = map.get("SDAF").toString();
					arr[10] = map.get("VAR").toString();
					arr[11] = map.get("QNET_AR_KF").toString();
					arr[12] = map.get("AAR_KF").toString();
					arr[13] = map.get("AD_KF").toString();
					arr[14] = map.get("VDAF_KF").toString();
					arr[15] = map.get("MT_KF").toString();
					arr[16] = map.get("STAD_KF").toString();
					arr[17] = map.get("AAD_KF").toString();
					arr[18] = map.get("MAD_KF").toString();
					arr[19] = map.get("QBAD_KF").toString();
					arr[20] = map.get("HAD_KF").toString();
					arr[21] = map.get("VAD_KF").toString();
					arr[22] = map.get("FCAD_KF").toString();
					arr[23] = map.get("STD_KF").toString();
					arr[24] = map.get("QBRAD_KF").toString();
					arr[25] = map.get("HDAF_KF").toString();
					arr[26] = map.get("QGRAD_DAF_KF").toString();
					arr[27] = map.get("SDAF_KF").toString();
					arr[28] = map.get("VAR_KF").toString();
					arr[29] = map.get("ZHUANGT").toString();
					arr[30] = map.get("ID").toString();
					arr[31] = map.get("FENX").toString();
					arr[32] = map.get("YUETJKJB_ID").toString();
					arr[33] = map.get("QNET_AR").toString();
					arr[34] = map.get("AAR").toString();
					arr[35] = map.get("AD").toString();
					arr[36] = map.get("VDAF").toString();
					arr[37] = map.get("MT").toString();
					arr[38] = map.get("STAD").toString();
					arr[39] = map.get("AAD").toString();
					arr[40] = map.get("ZHIJBFML").toString();
					arr[41] = map.get("ZHIJBFJE").toString();
					arr[42] = map.get("SUOPJE").toString();
					arr[43] = map.get("LSUOPSL").toString();
					arr[44] = map.get("LSUOPJE").toString();
					arr[45] = map.get("ZHIJBFJE_M").toString();
					arr[46] = map.get("ZHIJBFJE_A").toString();
					arr[47] = map.get("ZHIJBFJE_V").toString();
					arr[48] = map.get("ZHIJBFJE_Q").toString();
					arr[49] = map.get("ZHIJBFJE_S").toString();
					arr[50] = map.get("ZHIJBFJE_T").toString();
					arrs[i] = arr;
				}
			}
		}
		Object[] objs = new Object[] { arrs };
		Class[] classes = new Class[] { String[].class };
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference("http://10.65.17.197:9000/axis2/services/MonthReportService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2","uploadYuezl");
			return (String[]) serviceClient.invokeBlocking(qname, objs, classes)[0];
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//耗存合计
	@SuppressWarnings("rawtypes")
	public String[] uploadYueshchj(List<Map<String, Object>> list) {
		String[][] arrs = null;
		if (list != null && list.size() > 0) {
			arrs = new String[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (!map.isEmpty()) {
					String[] arr = new String[map.size()];
					arr[0] = map.get("ID").toString();
					arr[1] = map.get("RIQ").toString();
					arr[2] = map.get("FENX").toString();
					arr[3] = map.get("QICKC").toString();
					arr[4] = map.get("SHOUML").toString();
					arr[5] = map.get("FADY").toString();
					arr[6] = map.get("GONGRY").toString();
					arr[7] = map.get("QITH").toString();
					arr[8] = map.get("SUNH").toString();
					arr[9] = map.get("DIAOCL").toString();
					arr[10] = map.get("PANYK").toString();
					arr[11] = map.get("KUC").toString();
					arr[12] = map.get("DIANCXXB_ID").toString();
					arr[13] = map.get("SHUIFCTZ").toString();
					arr[14] = map.get("ZHUANGT").toString();
					arr[15] = map.get("JITCS").toString();
					arr[16] = map.get("RUNXCS").toString();
					arrs[i] = arr;
				}
			}
		}
		Object[] objs = new Object[] { arrs };
		Class[] classes = new Class[] { String[].class };
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference("http://10.65.17.197:9000/axis2/services/MonthReportService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2","uploadYueshchj");
			return (String[]) serviceClient.invokeBlocking(qname, objs, classes)[0];
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//耗存情况
	@SuppressWarnings("rawtypes")
	public String[] uploadYuehc(List<Map<String, Object>> list) {
		String[][] arrs = null;
		if (list != null && list.size() > 0) {
			arrs = new String[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (!map.isEmpty()) {
					String[] arr = new String[map.size()];
					arr[0] = map.get("ID").toString();
					arr[1] = map.get("FENX").toString();
					arr[2] = map.get("YUETJKJB_ID").toString();
					arr[3] = map.get("QICKC").toString();
					arr[4] = map.get("SHOUML").toString();
					arr[5] = map.get("FADY").toString();
					arr[6] = map.get("GONGRY").toString();
					arr[7] = map.get("QITH").toString();
					arr[8] = map.get("SUNH").toString();
					arr[9] = map.get("DIAOCL").toString();
					arr[10] = map.get("PANYK").toString();
					arr[11] = map.get("KUC").toString();
					arr[12] = map.get("SHUIFCTZ").toString();
					arr[13] = map.get("JITCS").toString();
					arr[14] = map.get("ZHUANGT").toString();
					arrs[i] = arr;
				}
			}
		}
		Object[] objs = new Object[] { arrs };
		Class[] classes = new Class[] { String[].class };
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference("http://10.65.17.197:9000/axis2/services/MonthReportService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2","uploadYuehc");
			return (String[]) serviceClient.invokeBlocking(qname, objs, classes)[0];
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//入厂标煤单价
	@SuppressWarnings("rawtypes")
	public String[] uploadYuejsbmdj(List<Map<String, Object>> list) {
		String[][] arrs = null;
		if (list != null && list.size() > 0) {
			arrs = new String[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (!map.isEmpty()) {
					String[] arr = new String[map.size()];
					arr[0] = map.get("BIAOMDJ").toString();
					arr[1] = map.get("BUHSBMDJ").toString();
					arr[2] = map.get("KUANGQYF").toString();
					arr[3] = map.get("ZHUANGT").toString();
					arr[4] = map.get("ZAFS").toString();
					arr[5] = map.get("ID").toString();
					arr[6] = map.get("FENX").toString();
					arr[7] = map.get("YUETJKJB_ID").toString();
					arr[8] = map.get("JIESL").toString();
					arr[9] = map.get("MEIJ").toString();
					arr[10] = map.get("MEIJS").toString();
					arr[11] = map.get("YUNJ").toString();
					arr[12] = map.get("YUNJS").toString();
					arr[13] = map.get("DAOZZF").toString();
					arr[14] = map.get("ZAF").toString();
					arr[15] = map.get("QIT").toString();
					arr[16] = map.get("QNET_AR").toString();
					arrs[i] = arr;
				}
			}
		}
		Object[] objs = new Object[] { arrs };
		Class[] classes = new Class[] { String[].class };
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference("http://10.65.17.197:9000/axis2/services/MonthReportService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2","uploadYuejsbmdj");
			return (String[]) serviceClient.invokeBlocking(qname, objs, classes)[0];
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//指标情况
	@SuppressWarnings("rawtypes")
	public String[] uploadYuezb(List<Map<String, Object>> list) {
		String[][] arrs = null;
		if (list != null && list.size() > 0) {
			arrs = new String[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (!map.isEmpty()) {
					String[] arr = new String[map.size()];
					arr[0] = map.get("ID").toString();
					arr[1] = map.get("DIANCXXB_ID").toString();
					arr[2] = map.get("RIQ").toString();
					arr[3] = map.get("FENX").toString();
					arr[4] = map.get("FADYTRML").toString();
					arr[5] = map.get("GONGRYTRML").toString();
					arr[6] = map.get("RULTRMPJFRL").toString();
					arr[7] = map.get("FADGRYTRML").toString();
					arr[8] = map.get("FADMZBML").toString();
					arr[9] = map.get("GONGRMZBML").toString();
					arr[10] = map.get("RULMZBZML").toString();
					arr[11] = map.get("FADYTRYL").toString();
					arr[12] = map.get("GONGRYTRYL").toString();
					arr[13] = map.get("RULTRYPJFRL").toString();
					arr[14] = map.get("FADGRYTRYL").toString();
					arr[15] = map.get("FADYZBZML").toString();
					arr[16] = map.get("GONGRYZBZML").toString();
					arr[17] = map.get("RULYZBZML").toString();
					arr[18] = map.get("FADYTRQL").toString();
					arr[19] = map.get("GONGRYTRQL").toString();
					arr[20] = map.get("RULTRQPJFRL").toString();
					arr[21] = map.get("FADGRYTRQL").toString();
					arr[22] = map.get("FADQZBZML").toString();
					arr[23] = map.get("GONGRQZBZML").toString();
					arr[24] = map.get("RULQZBZML").toString();
					arr[25] = map.get("FADL").toString();
					arr[26] = map.get("FADBZMH").toString();
					arr[27] = map.get("FADCGDL").toString();
					arr[28] = map.get("FADZHCGDL").toString();
					arr[29] = map.get("GONGRCGDL").toString();
					arr[30] = map.get("GONGDL").toString();
					arr[31] = map.get("GONGDBZMH").toString();
					arr[32] = map.get("SHOUDL").toString();
					arr[33] = map.get("GOURDL").toString();
					arr[34] = map.get("GONGRL").toString();
					arr[35] = map.get("SHOURL").toString();
					arr[36] = map.get("GONGRBZMH").toString();
					arr[37] = map.get("FADMCB").toString();
					arr[38] = map.get("FADYCB").toString();
					arr[39] = map.get("FADRQCB").toString();
					arr[40] = map.get("GONGRMCB").toString();
					arr[41] = map.get("GONGRYCB").toString();
					arr[42] = map.get("GONGRRQCB").toString();
					arr[43] = map.get("RANLCB_BHS").toString();
					arr[44] = map.get("QIZ_RANM").toString();
					arr[45] = map.get("QIZ_RANY").toString();
					arr[46] = map.get("QIZ_RANQ").toString();
					arr[47] = map.get("GONGRCYDFTRLF").toString();
					arr[48] = map.get("RULTRMPJDJ").toString();
					arr[49] = map.get("QIZ_FADTRMDJ").toString();
					arr[50] = map.get("QIZ_GONGRTRMDJ").toString();
					arr[51] = map.get("RULTRYPJDJ").toString();
					arr[52] = map.get("QIZ_FADTRYDJ").toString();
					arr[53] = map.get("QIZ_GONGRTRYDJ").toString();
					arr[54] = map.get("RULTRQPJDJ").toString();
					arr[55] = map.get("QIZ_FADTRQDJ").toString();
					arr[56] = map.get("QIZ_GONGRTRQDJ").toString();
					arr[57] = map.get("FADBZMDJ").toString();
					arr[58] = map.get("QIZ_MEIZBMDJ").toString();
					arr[59] = map.get("QIZ_YOUZBMDJ").toString();
					arr[60] = map.get("QIZ_QIZBMDJ").toString();
					arr[61] = map.get("GONGRBZMDJ").toString();
					arr[62] = map.get("QIZ_MEIZBMDJ_GR").toString();
					arr[63] = map.get("QIZ_YOUZBMDJ_GR").toString();
					arr[64] = map.get("QIZ_QIZBMDJ_GR").toString();
					arr[65] = map.get("FADDWRLCB").toString();
					arr[66] = map.get("SHOUDDWBDCB").toString();
					arr[67] = map.get("SHOUDDWGDCB").toString();
					arr[68] = map.get("SHOUDDJ").toString();
					arr[69] = map.get("SHOURDJ").toString();
					arr[70] = map.get("SHOURDWCB").toString();
					arr[71] = map.get("LIRZE").toString();
					arr[72] = map.get("SHOUDDWRLCB_MEID").toString();
					arr[73] = map.get("SHOUDDWRLCB_QID").toString();
					arr[74] = map.get("BENQRCMGJSL").toString();
					arr[75] = map.get("BENQRCMGJZJE_BHS").toString();
					arr[76] = map.get("GONGRHY").toString();
					arr[77] = map.get("FADHY").toString();
					arr[78] = map.get("RULMRZ").toString();
					arr[79] = map.get("GONGRHML").toString();
					arr[80] = map.get("FADHML").toString();
					arr[81] = map.get("RULYRZ").toString();
					arr[82] = map.get("GONGRTRMDJ").toString();
					arr[83] = map.get("FADTRMDJ").toString();
					arr[84] = map.get("RANLCNFY").toString();
					arr[85] = map.get("QITFY").toString();
					arr[86] = map.get("RULZHBMDJ").toString();
					arr[87] = map.get("RULZHBMDJ_JH").toString();
					arr[88] = map.get("ZHUANGT").toString();
					arr[89] = map.get("KUCTRMRZ").toString();
					arr[90] = map.get("KUCTRMJ").toString();
					arrs[i] = arr;
				}
			}
		}
		Object[] objs = new Object[] { arrs };
		Class[] classes = new Class[] { String[].class };
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference("http://10.65.17.197:9000/axis2/services/MonthReportService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2","uploadYuezb");
			return (String[]) serviceClient.invokeBlocking(qname, objs, classes)[0];
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//收耗存（油）
	@SuppressWarnings("rawtypes")
	public String[] uploadYueshcy(List<Map<String, Object>> list) {
		String[][] arrs = null;
		if (list != null && list.size() > 0) {
			arrs = new String[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (!map.isEmpty()) {
					String[] arr = new String[map.size()];
					arr[0] = map.get("ID").toString();
					arr[1] = map.get("RIQ").toString();
					arr[2] = map.get("DIANCXXB_ID").toString();
					arr[3] = map.get("PINZB_ID").toString();
					arr[4] = map.get("FENX").toString();
					arr[5] = map.get("QICKC").toString();
					arr[6] = map.get("SHOUYL").toString();
					arr[7] = map.get("FADYY").toString();
					arr[8] = map.get("GONGRY").toString();
					arr[9] = map.get("QITHY").toString();
					arr[10] = map.get("SUNH").toString();
					arr[11] = map.get("DIAOCL").toString();
					arr[12] = map.get("PANYK").toString();
					arr[13] = map.get("KUC").toString();
					arr[14] = map.get("ZHUANGT").toString();
					arr[15] = map.get("YOUJ").toString();
					arr[16] = map.get("YUNJ").toString();
					arrs[i] = arr;
				}
			}
		}
		Object[] objs = new Object[] { arrs };
		Class[] classes = new Class[] { String[].class };
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference("http://10.65.17.197:9000/axis2/services/MonthReportService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2","uploadYueshcy");
			return (String[]) serviceClient.invokeBlocking(qname, objs, classes)[0];
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//厂内费用
	@SuppressWarnings("rawtypes")
	public String[] uploadZaf(List<Map<String, Object>> list) {
		String[][] arrs = null;
		if (list != null && list.size() > 0) {
			arrs = new String[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (!map.isEmpty()) {
					String[] arr = new String[map.size()];
					arr[0] = map.get("ID").toString();
					arr[1] = map.get("DIANCXXB_ID").toString();
					arr[2] = map.get("MINGC").toString();
					arr[3] = map.get("JINE").toString();
					arr[4] = map.get("BEIZ").toString();
					arr[5] = map.get("ZHUANGT").toString();
					arr[6] = map.get("RIQ").toString();
					arrs[i] = arr;
				}
			}
		}
		Object[] objs = new Object[] { arrs };
		Class[] classes = new Class[] { String[].class };
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference("http://10.65.17.197:9000/axis2/services/MonthReportService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2","uploadZaf");
			return (String[]) serviceClient.invokeBlocking(qname, objs, classes)[0];
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//
	@SuppressWarnings("rawtypes")
	public String[] uploadYuetjkj(List<Map<String, Object>> list) {
		String[][] arrs = null;
		if (list != null && list.size() > 0) {
			arrs = new String[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (!map.isEmpty()) {
					String[] arr = new String[map.size()];
					arr[0] = map.get("ID").toString();
					arr[1] = map.get("RIQ").toString();
					arr[2] = map.get("DIANCXXB_ID").toString();
					arr[3] = map.get("XUH").toString();
					arr[4] = map.get("GONGYSB_ID").toString();
					arr[5] = map.get("JIHKJB_ID").toString();
					arr[6] = map.get("PINZB_ID").toString();
					arr[7] = map.get("YUNSFSB_ID").toString();
					arrs[i] = arr;
				}
			}
		}
		Object[] objs = new Object[] { arrs };
		Class[] classes = new Class[] { String[].class };
		
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference er = new EndpointReference("http://10.65.17.197:9000/axis2/services/MonthReportService");
			options.setTo(er);
			QName qname = new QName("http://ws.apache.org/axis2","uploadYuetjkj");
			return (String[]) serviceClient.invokeBlocking(qname, objs, classes)[0];
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static MonthReportClient getInstance(){
		return new MonthReportClient();
	}
}