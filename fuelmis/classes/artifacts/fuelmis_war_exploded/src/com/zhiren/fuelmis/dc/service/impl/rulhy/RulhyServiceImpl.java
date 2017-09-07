package com.zhiren.fuelmis.dc.service.impl.rulhy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.zhiren.fuelmis.dc.dao.ruchy.RulhyDao;
import com.zhiren.fuelmis.dc.service.rulhy.IRulhyService;
import com.zhiren.fuelmis.dc.utils.Sequence;

/**
 * 
 * @author 刘志宇
 * 
 */
@Transactional
@Service
public class RulhyServiceImpl implements IRulhyService {

	private static final String error000 = "1,000,接收成功";
//	private static final String error001 = "-1,001,XMLData数据转换为gb312编码时出错";
	// private static final String error002 =
	// "-1,002,文档不符合dom规范,可能是发送配置表中sql的字段为函数时没有写别名";
//	private static final String error003 = "-1,003,双方没有安照接口协议传输数据，请检查发送接收的配置，例如发送数据的日期时间类型必须用格式字符串，接收端编码类型字段的配置等或远程数据库编码重复或违反唯一约束条件";//
	// private static final String error004 = "-1,004,执行远程的sql时远程服务器数据库连接失败";
//	private static final String error005 = "-1,005,执行远程的sql时出错，可能是客户端生成的sql语句不符合规范，请检查发送配置表";
	// private static final String error007 =
	// "-1,007,删除远程数据时没有找到主键字段名称，可能是远程接收配置没有设置主键名称!";
	// private static final String error008 = "1,008,删除0条数据，也就是远程数据与本地数据没有同步";
	// private static final String error009 = "-1,009,用户名称不在，你的电厂还没有在集团注册用户";
	// private static final String error010 = "-1,010,用户账户的密码错误请检查系统密码设置";
	// private static final String error011 = "-1,011,接收数据库的接收配置有不识别的类型";
	// private static final String error013 = "-1,013,发送端与接收端配置的字段个数不一致。";
	private static final String error014 = "-1,014,未知异常。";
	// private static final String error015 = "-1,015,提交的化验编号在燃料系统中不存在,请核对。";
	// private static final String rizPath = "d:/zhiren/";// 发送日志路径

	@Autowired
	private RulhyDao rulhyDao;

	private Map<String, String> getHuayjg(byte[] XMLData) {
		Map<String, String> map = new HashMap<String, String>();
		String xmlstr = null;
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

	@Override
	public String setHuayxx_jt(String user, String pasd, byte[] XMLData) {	
		OutputStream os;
		try {
			os = new FileOutputStream(new File("C:\\zhiren\\rulxml.xml"));
	        os.write(XMLData);
	        os.flush();
	        os.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Map<String, String> map = this.getHuayjg(XMLData);
		System.out.println(map);
		// 业务处理
		String message = error000;
		try {
			String huaybh = map.get("HUAYBH");
			System.out.println(huaybh);
			String ispip = rulhyDao.getIspip(huaybh);
			if (ispip!=null&&ispip.equals("1")) {
				message = "数据已经匹配,不允许再上传";
				System.out.println(message);
				return message;
			} else {
				// 插入之前先进行删除操作.
				rulhyDao.deleteRulhy(huaybh);
			}
			String dcId = "515";
			map.put("dcId", dcId);
			String huaydID=Sequence.nextId();
			map.put("HUAYD_ID", huaydID);
			//1.插入化验单数据
			rulhyDao.insertRulhy(map);
			//2.需找对应的入炉信息将化验单id就让到对应的班组中并添加匹配状态为已经匹配
			String rulrq = map.get("RULRQ");
			String banzxx = map.get("BANZXX");
			map.put("HUAYD_ID", huaydID);
			rulhyDao.addHuaydID(huaydID,rulrq,banzxx);
		} catch (Exception e) {
			message = error014;// zhixzt cuowlb zhixbz
			e.printStackTrace();
		}
		return message;
	}
	@Override
	public boolean isPip(String bianm) {
		// 入炉化验编号是否匹配
		String isPip = rulhyDao.getIspip(bianm);
		boolean biaoz = false;
		@SuppressWarnings("unused")
		String message = error000;
		if (isPip!=null&&isPip.equals("1")) {
			biaoz = false;
			message = "数据已匹配，不可以再上传";
		} else {
			biaoz = true;
			message = "数据未匹配，可以进行重新上传操作";
		}
		return biaoz;
	}
}
