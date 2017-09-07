package com.zhiren.fuelmis.dc.service.impl.ruchy;

import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.zhiren.fuelmis.dc.dao.ruchy.RuchyDao;
import com.zhiren.fuelmis.dc.dao.ruchy.ShenhDao;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.ruchy.IRuchyService;
import com.zhiren.fuelmis.dc.utils.IPUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;
import com.zhiren.fuelmis.dc.utils.TreeUtil;
import com.zhiren.fuelmis.dc.utils.formular.Huayz;
import com.zhiren.fuelmis.dc.utils.formular.Result;

/**
 * 
 * @author 刘志宇
 * 
 */
@Transactional
@Service
public class RuchyServiceImpl implements IRuchyService {

	private static final String error000 = "1,000,接收成功";
	// private static final String error001 = "-1,001,XMLData数据转换为gb312编码时出错";
	// private static final String error002 =
	// "-1,002,文档不符合dom规范,可能是发送配置表中sql的字段为函数时没有写别名";
	private static final String error003 = "-1,003,双方没有安照接口协议传输数据，请检查发送接收的配置，例如发送数据的日期时间类型必须用格式字符串，接收端编码类型字段的配置等或远程数据库编码重复或违反唯一约束条件";//
	// private static final String error004 = "-1,004,执行远程的sql时远程服务器数据库连接失败";
	private static final String error005 = "-1,005,执行远程的sql时出错，可能是客户端生成的sql语句不符合规范，请检查发送配置表";
	// private static final String error007 ="-1,007,删除远程数据时没有找到主键字段名称，可能是远程接收配置没有设置主键名称!";
	// private static final String error008 = "1,008,删除0条数据，也就是远程数据与本地数据没有同步";
	// private static final String error009 = "-1,009,用户名称不在，你的电厂还没有在集团注册用户";
	// private static final String error010 = "-1,010,用户账户的密码错误请检查系统密码设置";
	// private static final String error011 = "-1,011,接收数据库的接收配置有不识别的类型";
	// private static final String error013 = "-1,013,发送端与接收端配置的字段个数不一致。";
	// private static final String error014 = "-1,014,未知异常。";
	// private static final String error015 = "-1,015,提交的化验编号在燃料系统中不存在,请核对。";
	// private static final String rizPath = "d:/zhiren/";// 发送日志路径

	@Autowired
	private RuchyDao ruchyDao;
	@Autowired
	private ShenhDao shenhDao;
	@Override
	public JSONArray getMeiyjcjg(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<LinkedHashMap<String, Object>> list = ruchyDao.getMeiyjcjg(map);
		String[][] arrData = new String[list.size()][9];
		for (int i = 0; i < list.size(); i++) {
			LinkedHashMap<String, Object> rt = list.get(i);
			Huayz.round_new(rt);
			arrData[i][0] = (i + 1) + "";
			arrData[i][1] = rt.get("HUAYRQ") == null ? "" : rt.get("HUAYRQ").toString();
			arrData[i][2] = rt.get("MEIZ") == null ? "" : rt.get("MEIZ").toString();
			arrData[i][3] = rt.get("YANGPBM") == null ? "" : rt.get("YANGPBM").toString();
			arrData[i][4] = rt.get("MAD") == null ? "" : rt.get("MAD").toString();
			arrData[i][5] = rt.get("AD") == null ? "" : rt.get("AD").toString();
			arrData[i][6] = rt.get("VD") == null ? "" : rt.get("VD").toString();
			arrData[i][7] = rt.get("ST_D") == null ? "" : rt.get("ST_D").toString();
			arrData[i][8] = rt.get("QGRD") == null ? "" : rt.get("QGRD").toString();
		}
		Report rt = new Report();
		String ArrHeader[][] = new String[1][9];
		ArrHeader[0] = new String[] { "序号", "化验日期", "煤种", "样品编码",
				"空干基水分 <br>Mad(%)", "干基灰分<br>Ad(%)", "干基挥发分<br>Vd(%)",
				"干基全硫<br>St.d(%)", "干基高位发热量<br>Qgr,d(MJ/kg)" };
		int ArrWidth[] = new int[] { 45, 100, 90, 90, 90, 65, 65, 65, 67};
		rt.setBody(new Table(arrData, 1, 0, 0, 9));
		rt.body.setHeaderData(ArrHeader);
		rt.body.setPageRows(25);
		int pageCount = rt.getPages();
		rt.body.ShowZero = false;
		for (int i = 1; i <= 9; i++) {
			rt.body.setColAlign(i, Table.ALIGN_CENTER);
		}
		if (!list.isEmpty()) {
			System.out.println();
			String riq[] = (list.get(0).get("HUAYRQ")).toString().split("-");
			rt.setTitle(
					list.get(0).get("DIANCQC") + riq[1] + "月份" + "入厂煤样检测结果",ArrWidth);
		} else {

		}

		rt.body.setWidth(ArrWidth);
		rt.body.mergeFixedRowCol();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
		resultMap.put("pageCount", pageCount);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(resultMap);
		return jsonArray;
	}

	@Override
	public String get_huay_zhillsb(String dcId, String bianm, byte[] XMLData) {
//		dcId = "515";
/*		for (byte b : XMLData) {
			System.out.write(b);
		}

		System.out.println();
		 File newFile = new File("D:/", "me.xml");
		 try {
		 FileUtil.writeFile(XMLData, newFile);
		 } catch (IOException e1) {
		 e1.printStackTrace();
		 }
		 Map<String, String> map = this.getHuayjg(newFile);*/
		Map<String, String> map = this.getHuayjg(XMLData);
//		Set<Entry<String, String>> entrySet = map.entrySet();
		// for (Entry<String, String> entry : entrySet) {
		// System.out.println(entry.getValue() + "---" + entry.getKey());
		// }

		map.put("HUAYBM", bianm);
		map.put("dcId", dcId);
		//化验单中是否有相同的化验编码如果有责根据化验编码更新如果没有则插入
		String huaydID= ruchyDao.getHuaydID(bianm);
		if(huaydID!=null){
			map.put("HUAYD_ID", huaydID);
			//--------------------------------------------------------------------------
			//记录历史
/*			String ip=IPUtil.ipConfig();
			Map<String, Object> m =new HashMap<String,Object>();
			m.put("HUAYD_ID", huaydID);
			m.put("ID",Sequence.nextId());
			m.put("CAOZRY", "化验室");
			m.put("IP", ip);
			m.put("CAOZLX", "化验数据更新");
			m.put("YEWHJ", "化验室向燃料传化验数据");
			shenhDao.huaydLog(m);*/
			//------------------------------------------------------------------------
			ruchyDao.updateHuayd(map);
		}else{
			huaydID=Sequence.nextId();
			map.put("ID",huaydID);
			ruchyDao.insertHuayd(map);
		}
		return error000;
	}

	@SuppressWarnings("unused")
	@Override
	public byte[] getFahInfo_jt(String dcId, String bianm) {

		List<Map<String, Object>> rs = ruchyDao.getFahInfo(dcId, bianm);
		// 业务处理
		byte[] xml_by = null;
		String message = "";
		try {
			String xml = "";
			// 调用CreateXml方法，对记录集rs生成XML文件
			xml = TreeUtil.parseNodeToXML(rs);
			System.out.println(xml);
			try {
				xml_by = xml.getBytes("GB2312");// tencryptByDES(xml.getBytes());//对xml进行DES加密
			} catch (Exception e) {
				e.printStackTrace();
				message = error003;
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = error005;
		}
		return xml_by;
	}

	private Map<String, String> getHuayjg(byte[] XMLData) {
		Map<String, String> map = new HashMap<String, String>();
		String xmlstr=null;
		try {
			xmlstr = new String(XMLData, "gbk");
			StringReader sr = new StringReader(xmlstr);
			InputSource is = new InputSource(sr);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = (Document) builder.parse(is);
			Element element = document.getDocumentElement();
			NodeList nodeList = element.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				String nodeName = nodeList.item(i).getNodeName();
				String content = nodeList.item(i).getTextContent();
				if (content == "") {
					content = "-";
				}
				map.put(nodeName.toUpperCase(), content);
			}
			return map;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	
}
