package com.zhiren.fuelmis.dc.service.impl.webInterface;
//package com.zhiren.fuelmis.dc.service.impl.webInterface;
//
//
//
//import java.io.IOException;
//import java.io.StringReader;
//import java.io.StringWriter;
//import java.sql.SQLException;
//import java.util.Date;
//import java.util.List;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//import org.jdom.Document;
//import org.jdom.Element;
//import org.jdom.JDOMException;
//import org.jdom.input.SAXBuilder;
//import org.xml.sax.InputSource;
//
//import com.zhiren.common.DateUtil;
//import com.zhiren.common.JDBCcon;
//import com.zhiren.common.ResultSetList;
//
//public class DataServiceUtil {
//	private static final String error000 = "1,000,接收成功";
//	private static final String error001 = "-1,001,XMLData数据转换为gb312编码时出错";
//	private static final String error002 = "-1,002,文档不符合dom规范,可能是发送配置表中sql的字段为函数时没有写别名";
//	private static final String error003 = "-1,003,双方没有安照接口协议传输数据，请检查发送接收的配置，例如发送数据的日期时间类型必须用格式字符串，接收端编码类型字段的配置等或远程数据库编码重复或违反唯一约束条件";//
//	private static final String error004 = "-1,004,执行远程的sql时远程服务器数据库连接失败";
//	private static final String error005 = "-1,005,执行远程的sql时出错，可能是客户端生成的sql语句不符合规范，请检查发送配置表";
//	private static final String error007 = "-1,007,删除远程数据时没有找到主键字段名称，可能是远程接收配置没有设置主键名称!";
//	private static final String error008 = "1,008,删除0条数据，也就是远程数据与本地数据没有同步";
//	private static final String error009 = "-1,009,用户名称不在，你的电厂还没有在集团注册用户";
//	private static final String error010 = "-1,010,用户账户的密码错误请检查系统密码设置";
//	private static final String error011 = "-1,011,接收数据库的接收配置有不识别的类型";
//	private static final String error013 = "-1,013,发送端与接收端配置的字段个数不一致。";
//	private static final String error014 = "-1,014,未知异常。";
//
//	// private static final String error015="-1,015,接收端无配置";
//
//	public static String incept(String user, String password, byte[] XMLData) {
//		String message = "";
//		int v = ValidateUser(user, password);
//		if (v == -1) {// 用户不存在
//			message = error009;
//			return message;
//		} else if (v == 0) {// 密码错误
//			message = error010;
//			return message;
//		} else {// 通过验证
//			message = TransData(XMLData);
//		}
//		return message;
//	}
//
//	/**
//	 *
//	 * @param user
//	 *            接口用户
//	 * @param password
//	 *            接口密码
//	 * @param OrgID
//	 *            组织ID
//	 * @param AccountDate
//	 *            业务日期
//	 * @param DataID
//	 *            数据ID
//	 * @param ModuleName
//	 *            业务模式
//	 * @param Operator
//	 *            操作人
//	 * @param Remarks
//	 *            备注
//	 * @return
//	 */
//	public static String DataApproval(String user, String password,
//			String OrgID, String AccountDate, String DataID, String ModuleName,
//			String Operator, String Remarks) {
//		String message = "";
//		int v = ValidateUser(user, password);
//		if (v == -1) {// 用户不存在
//			message = error009;
//			return message;
//		} else if (v == 0) {// 密码错误
//			message = error010;
//			return message;
//		} else {// 通过验证
//			message = DataStatus.DataApproval(OrgID, AccountDate, DataID,
//					ModuleName, Operator, Remarks);
//		}
//		return message;
//	}
//
//	/**
//	 *
//	 * @param user
//	 *            接口用户
//	 * @param password
//	 *            接口密码
//	 * @param OrgID
//	 *            组织ID
//	 * @param AccountDate
//	 *            业务日期
//	 * @param DataID
//	 *            数据ID
//	 * @param ModuleName
//	 *            业务模式
//	 * @param Operator
//	 *            操作人
//	 * @param Remarks
//	 *            备注
//	 * @return
//	 */
//	public static String DataRollback(String user, String password,
//			String OrgID, String AccountDate, String DataID, String ModuleName,
//			String Operator, String Remarks) {
//		String message = "";
//		int v = ValidateUser(user, password);
//		if (v == -1) {// 用户不存在
//			message = error009;
//			return message;
//		} else if (v == 0) {// 密码错误
//			message = error010;
//			return message;
//		} else {// 通过验证
//			message = DataStatus.DataRollback(OrgID, AccountDate, DataID,
//					ModuleName, Operator, Remarks);
//		}
//		return message;
//	}
//
//	/**
//	 *
//	 * @param user
//	 *            用户名
//	 * @param password
//	 *            密码
//	 * @return 检查接口账户表 验证登录信息
//	 */
//	private static int ValidateUser(String user, String password) {// 验证账户
//		JDBCcon con = new JDBCcon();
//		int v = 1;
//		String sql = "select mim,id\n" + "from jiekzhb\n"
//				+ "where jiekzhb.yonghmc='" + user + "'";
//		ResultSetList rs = con.getResultSetList(sql);
//		try {
//			if (rs.next()) {
//				if (password.equals(rs.getString(0))) {
//					v = 1;// 如果用户存在，并且密码正确返回1
//				} else {
//					v = 0;// 密码错误
//				}
//			} else {
//				v = -1;// 不存在该用户
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			con.Close();
//		}
//		return v;
//	}
//
//	private static String ValidateData(JDBCcon con, String nodeName,
//			boolean isnullalbe, String transCodeSQL, String dataType,
//			String data) {
//		String errorMessage = null;
//		if (!isnullalbe && (data == null || data.equals(""))) {
//			// 判定数据不能为空
//			errorMessage = nodeName + "不允许为空";
//			return errorMessage;
//		}
//		if (dataType.equalsIgnoreCase("bianm")) {
//			if (transCodeSQL == null || transCodeSQL.equals("")) {
//				errorMessage = nodeName + "字段的转码配置错误";
//				return errorMessage;
//			}
//			if (!con.getHasIt(transCodeSQL + "'" + data + "'")) {
//				errorMessage = nodeName + "字段的" + data + "编码不能识别";
//				return errorMessage;
//			}
//		}
//		if (dataType.equalsIgnoreCase("decode")) {
//			if (transCodeSQL == null || transCodeSQL.equals("")) {
//				errorMessage = nodeName + "字段的转码配置错误";
//				return errorMessage;
//			}
//			if (!isnullalbe) {
//				ResultSetList rs = con.getResultSetList(transCodeSQL
//						.replaceAll("%%", "'" + data + "'"));
//				if (rs.next()) {
//					if (rs.getString(0) == null || rs.getString(0).equals("")) {
//						errorMessage = nodeName + "字段的" + data + "在数据库中找不到对应的值";
//					}
//					return errorMessage;
//				}
//			}
//		}
//		return errorMessage;
//	}
//
//	/**
//	 *
//	 * @param XMLData
//	 *            数据流
//	 * @return
//	 *
//	 *         数据格式 <xml> <TransNum></TransNum> <TransType></TransType>
//	 *         <TransData> <Task> <Protocol></Protocol> <Operation></Operation>
//	 *         <GUID></GUID> <Time></Time> <OrgID></OrgID> <DataResource>
//	 *         <xxxx></xxxx> ...... <xxx></xxx> </DataResource> </Task> ......
//	 *         <Task> <Protocol></Protocol> <Operation></Operation>
//	 *         <GUID></GUID> <Time></Time> <OrgID></OrgID> <DataResource>
//	 *         <xxxx></xxxx> ...... <xxx></xxx> </DataResource> </Task>
//	 *         </TransData> </xml>
//	 */
//	private static String TransData(byte[] XMLData) {
//		String message = error000; // 返回信息
//		JDBCcon con = new JDBCcon(); // 数据连接
//		String strOrgID = ""; // 组织ID
//		String tN = ""; // 事务号
//		String tT = ""; // 事务类型
//		int flag;
//		con.setAutoCommit(false); // 设置为不自动提交
//		try {
//			String xmlstr = new String(XMLData, "GB2312"); // 得到二进制XML数据信息转换为Str型
//			StringReader sr = new StringReader(xmlstr); // 字符读取
//			InputSource is = new InputSource(sr); //
//			Document doc = (new SAXBuilder()).build(is);// 解读每一个数据帧文件
//			Element root = doc.getRootElement(); // 得到XML的根
//			tN = root.getChildText("TransNum"); // 事务号
//			tT = root.getChildText("TransType");
//			// 事务类型
//			Element etd = root.getChild("TransData"); // 事务对应数据
//			List arrTasks = etd.getChildren(); // 数据数组
//			StringBuffer bufferTable = new StringBuffer();
//			StringBuffer bufferValus = new StringBuffer();
//			for (int i = 0; i < arrTasks.size(); i++) { // 循环
//				bufferTable.setLength(0);
//				bufferValus.setLength(0);
//				Element eTask = (Element) arrTasks.get(i); // 得到任务
//				String strProtocol = eTask.getChildText("Protocol"); // 数据协议
//				String strOperation = eTask.getChildText("Operation"); // 操作类型
//				String strGUID = eTask.getChildText("GUID"); // 主键
//				String strTime = eTask.getChildText("Time"); // 时间
//				strOrgID = eTask.getChildText("OrgID"); // 组织ID
//				Element eData = eTask.getChild("DataResource"); // 数据源
//				List arrData = eData.getChildren(); // 数据源数组
//				String sql = "select renwmc,jiekjspzb.zidmc,leix,jiekbmzhpzb.zidmc zhuanmsql,zhujmc,weik\n"
//						+ " from jiekjspzb,jiekbmzhpzb\n"
//						+ " where  jiekjspzb.bianm=jiekbmzhpzb.bianm(+) "
//						+ " and renwmc='"
//						+ strProtocol
//						+ "' order by jiekjspzb.id";
//				System.out.println(sql);
//
//				ResultSetList rs = con.getResultSetList(sql);
//				System.out.println(rs.getRows());
//				System.out.println(arrData.size());
//				// 判定数据结构是否与接收端配置一致如果不一致关闭数据库连接返回提示信息
//				if (arrData.size() != rs.getRows()) {
//					message = error013;
//					con.rollBack();
//					con.Close();
//					return message;
//				}
//
//				if (strOperation.equals("1")) {
//					// 由于插入操作是先删除后增加，所以与删除操作共用删除方法
//					sql = "delete from " + strProtocol + " where "
//							+ rs.getString(0, "zhujmc") + "=" + strGUID;
//					flag = con.getDelete(sql);
//					if (flag == -1) {
//						// 删除失败提示
//						message = error007;
//						con.rollBack();
//						con.Close();
//						return message;
//					} else if (flag == 0) {
//						// 如果是删除操作 且删除0条数据
//						message = error008;
//						con.rollBack();
//						con.Close();
//						return message;
//					} else {
//						continue;
//					}
//				}
//				// 循环配置
//				while (rs.next()) {
//					String nodeName = rs.getString("zidmc"); // 字段名
//					String dataType = rs.getString("leix"); // 字段类型
//					boolean isNullable = rs.getString("weik").equalsIgnoreCase(
//							"Y"); // 是否可为空
//					String transcodeSQL = rs.getString("zhuanmsql"); // 转码SQL
//					String nodeText = eData
//							.getChildText(nodeName.toUpperCase()); // 值
//
//					// 形成insert的字段描述部分
//					bufferTable.append(nodeName).append(",");
//					// 验证数据正确性
//					String valmsg = ValidateData(con, nodeName, isNullable,
//							transcodeSQL, dataType, nodeText);
//					if (valmsg != null) {
//						con.rollBack();
//						con.Close();
//						return "-1,012," + valmsg;
//					}
//					// 形成insertSQL值段
//					if ("varchar".equals(dataType) || "number".equals(dataType)
//							|| "id".equals(dataType)) {
//						bufferValus.append("'" + nodeText + "'");
//					} else if ("bianm".equals(dataType)) {
//						bufferValus.append("(" + transcodeSQL + "'" + nodeText
//								+ "')");
//					} else if ("date".equals(dataType)) {
//						bufferValus.append("to_date('" + nodeText
//								+ "','YYYY-MM-DD')");
//					} else if ("time".equals(dataType)) {
//						bufferValus.append("to_date('" + nodeText
//								+ "','YYYY-MM-DD HH24:MI:SS')");
//					} else if ("decode".equals(dataType)) {// varchar,nubmer
//						bufferValus.append("("
//								+ transcodeSQL.replaceAll("%%", "'" + nodeText
//										+ "'") + ")");// 替换参数
//					} else {// 不识别的类型
//						message = error011 + ":" + dataType;
//						con.rollBack();
//						con.Close();
//						return message;
//					}
//					bufferValus.append(",");
//				}
//				rs.close();
//				if (bufferTable.length() > 0 || bufferValus.length() > 0) {
//					bufferTable.deleteCharAt(bufferTable.length() - 1);
//					bufferValus.deleteCharAt(bufferValus.length() - 1);
//					sql = "insert into " + strProtocol + "("
//							+ bufferTable.toString() + ") values\n("
//							+ bufferValus.toString() + ")";
//					flag = con.getInsert(sql);
//					if (flag == -1) {
//						throw new SQLException();
//					}
//				} else {
//					message = "-1,015,接收端未配置协议" + strProtocol + "。";
//					con.rollBack();
//					con.Close();
//					return message;
//				}
//			}
//			con.commit();
//			con.Close();
//		} catch (IOException e) {
//			con.rollBack();
//			con.Close();
//			message = error001;// zhixzt cuowlb zhixbz
//			return message;
//		} catch (JDOMException e1) {
//			con.rollBack();
//			con.Close();
//			message = error002;// zhixzt cuowlb zhixbz
//			return message;
//		} catch (SQLException e2) {
//			con.rollBack();
//			con.Close();
//			message = error003;// zhixzt cuowlb zhixbz
//			return message;
//		} catch (Exception e4) {
//			e4.printStackTrace();
//			con.rollBack();
//			con.Close();
//			message = error014;// zhixzt cuowlb zhixbz
//			return message;
//		} finally {
//			con.Close();
//			Xiejsrzb(strOrgID, tT, tN, message, "2");
//		}
//		return message;
//	}
//
//	private static void Xiejsrzb(String diancxxb_id, String renw,
//			String renwbs, String message, String caoz) {// 写接收日志表
//		String[] dat = message.split(",");
//		System.out.println("renw的值是:" + renw);
//		// if(dat[0].equals("-1")){//如果为错误
//		JDBCcon con = new JDBCcon();
//		String sql = "insert into jiekjsrzb(id,diancxxb_id,renw,renwbs,shij,cuowdm,cuowxx,caoz,zhixzt)values(\n"
//				+ "xl_xul_id.nextval,"
//				+ diancxxb_id
//				+ ",'"
//				+ renw
//				+ "',"
//				+ renwbs
//				+ ",sysdate,'"
//				+ dat[1]
//				+ "','"
//				+ dat[2]
//				+ "','"
//				+ caoz + "'," + dat[0] + ")";
//		con.getInsert(sql);
//		con.Close();
//		// }
//	}
//
//	public String CreateDataXML(String TransNum, String TransType,
//			String OrgID, String[] TransTask) {
//		JDBCcon con = new JDBCcon();
//		DocumentBuilderFactory dbf = null; // document工厂不明白自己看书
//		DocumentBuilder db = null; // builder
//		org.w3c.dom.Document doc = null; // 文档
//		org.w3c.dom.Element root = null; // xml根
//		try {
//			dbf = DocumentBuilderFactory.newInstance();
//			db = dbf.newDocumentBuilder();
//			doc = db.newDocument();
//			root = doc.createElement("xml");
//			doc.appendChild(root);
//			// 添加事务号
//			org.w3c.dom.Element tn = doc.createElement("TransNum");
//			tn.appendChild(doc.createTextNode(TransNum));
//			root.appendChild(tn);
//			// 添加事务类型
//			org.w3c.dom.Element tt = doc.createElement("TransType");
//			tt.appendChild(doc.createTextNode(TransType));
//			root.appendChild(tt);
//			// 添加事务任务
//			org.w3c.dom.Element td = doc.createElement("TransData");
//			// 添加内容头
//			for (int i = 0; i < TransTask.length; i++) {
//				if (addTask(con, doc, td, TransTask[i], "0", TransNum, OrgID) == 0) {
//					return null;
//				}
//			}
//			root.appendChild(td);
//
//			DOMSource ds = new DOMSource(doc);
//
//			String strXml = "";
//			StringWriter sw = new StringWriter();
//			Transformer serializer;
//			serializer = TransformerFactory.newInstance().newTransformer();
//			serializer.transform(ds, new StreamResult(sw));
//			strXml = sw.toString();
//			return strXml;
//		} catch (ParserConfigurationException e) {
//			e.printStackTrace();
//			return null;
//		} catch (TransformerConfigurationException e) {
//			e.printStackTrace();
//			return null;
//		} catch (TransformerException e) {
//			e.printStackTrace();
//			return null;
//		} finally {
//			// 关闭数据库相关连接
//			con.Close();
//		}
//	}
//
//	private int addTask(JDBCcon con, org.w3c.dom.Document doc,
//			org.w3c.dom.Element pNode, String TaskName, String oper,
//			String GUID, String OrgID) {
//		int recnum = 0;
//		String sql = "SELECT ID, RENWMC, RENWSQL, RENWBS, RENWTJ, BEIZ, MINGLLX, GENGXY\n"
//				+ "\tFROM JIEKFSPZB\n" + " WHERE RENWMC = '" + TaskName + "'";
//		ResultSetList rsl = con.getResultSetList(sql);
//		if (rsl.next()) {
//			String filterSQL = rsl.getString("RENWBS") + "'" + GUID + "'";
//			sql = rsl.getString("RENWSQL").replaceAll("%%", filterSQL);
//			ResultSetList rs = con.getResultSetList(sql);
//			if (rs == null) {
//				return 0;
//			} else {
//				recnum = rs.getRows();
//			}
//			String[] cNames = rs.getColumnNames();
//			while (rs.next()) {
//				org.w3c.dom.Element task = doc.createElement("Task");
//				org.w3c.dom.Element eProtocol = doc.createElement("Protocol");
//				eProtocol.appendChild(doc.createTextNode(TaskName));
//				task.appendChild(eProtocol);
//
//				org.w3c.dom.Element eOperation = doc.createElement("Operation");
//				eOperation.appendChild(doc.createTextNode(oper));
//				task.appendChild(eOperation);
//
//				org.w3c.dom.Element eGUID = doc.createElement("GUID");
//				eGUID.appendChild(doc.createTextNode(GUID));
//				task.appendChild(eGUID);
//
//				org.w3c.dom.Element eTime = doc.createElement("Time");
//				eTime.appendChild(doc.createTextNode(DateUtil
//						.FormatDateTime(new Date())));
//				task.appendChild(eTime);
//
//				org.w3c.dom.Element eOrgID = doc.createElement("OrgID");
//				eOrgID.appendChild(doc.createTextNode(OrgID));
//				task.appendChild(eOrgID);
//
//				org.w3c.dom.Element eDS = doc.createElement("DataResource");
//				for (int i = 0; i < cNames.length; i++) {
//					org.w3c.dom.Element col = doc.createElement(cNames[i]);
//					col.appendChild(doc.createTextNode(rs.getString(cNames[i])));
//					eDS.appendChild(col);
//				}
//				task.appendChild(eDS);
//				pNode.appendChild(task);
//			}
//			rs.close();
//		}
//		rsl.close();
//		return recnum;
//	}
//
//	public static String TransWenj(String user, String password, byte[] XMLData) {// 对分公司发布文件的操作
//																					// xieb
//		String message = "";
//		int v = ValidateUser(user, password);
//		if (v == -1) {// 用户不存在
//			message = error009;
//			return message;
//		} else if (v == 0) {// 密码错误
//			message = error010;
//			return message;
//		} else {// 通过验证
//			message = TransWenjData(XMLData);
//		}
//		return message;
//	}
//
//	private static String TransWenjData(byte[] XMLData) {// 对分公司发布文件的操作 xieb
//
//		String message = error000; // 返回信息
//		JDBCcon con = new JDBCcon(); // 数据连接
//		String strOrgID = ""; // 组织ID
//		String tN = ""; // 事务号
//		String tT = ""; // 事务类型
//		int flag;
//		con.setAutoCommit(false); // 设置为不自动提交
//		try {
//			String xmlstr = new String(XMLData, "GB2312"); // 得到二进制XML数据信息转换为Str型
//System.out.println("1:"+xmlstr);
//			StringReader sr = new StringReader(xmlstr); // 字符读取
//			InputSource is = new InputSource(sr); //
//			Document doc = (new SAXBuilder()).build(is);// 解读每一个数据帧文件
//			Element root = doc.getRootElement(); // 得到XML的根
//			tN = root.getChildText("TransNum"); // 事务号
//			tT = root.getChildText("TransType"); // 事务类型
//			Element etd = root.getChild("TransData"); // 事务对应数据
//			List arrTasks = etd.getChildren(); // 数据数组
//			StringBuffer bufferTable = new StringBuffer();
//			StringBuffer bufferValus = new StringBuffer();
//			for (int i = 0; i < arrTasks.size(); i++) { // 循环
//				bufferTable.setLength(0);
//				bufferValus.setLength(0);
//				Element eTask = (Element) arrTasks.get(i); // 得到任务
//				String strProtocol = eTask.getChildText("Protocol"); // 数据协议
//				String strOperation = eTask.getChildText("Operation"); // 操作类型
//				String strGUID = eTask.getChildText("GUID"); // 主键
//				String strTime = eTask.getChildText("Time"); // 时间
//				strOrgID = eTask.getChildText("OrgID"); // 组织ID
//				Element eData = eTask.getChild("DataResource"); // 数据源
//				List arrData = eData.getChildren(); // 数据源数组
//				String sql = "select renwmc,jiekjspzb.zidmc,leix,jiekbmzhpzb.zidmc zhuanmsql,zhujmc,weik\n"
//						+ " from jiekjspzb,jiekbmzhpzb\n"
//						+ " where  jiekjspzb.bianm=jiekbmzhpzb.bianm(+) "
//						+ " and renwmc='" + strProtocol + "' order by jiekjspzb.id";
//System.out.println("2:jiekjspz:"+sql);
//				ResultSetList rs = con.getResultSetList(sql);
//				System.out.println(rs.getRows());
//				System.out.println(arrData.size());
//				// 判定数据结构是否与接收端配置一致如果不一致关闭数据库连接返回提示信息
//				if (arrData.size() != rs.getRows()) {
//					message = error013;
//					con.rollBack();
//					con.Close();
//					return message;
//				}
//
//				// 删除操作
//				if (!strProtocol.equals("fabwjb")) {
//					sql = "delete from " + strProtocol + " where "
//							+ rs.getString(0, "zhujmc")
//							+ " = (select wenjb_id from fabwjb where id="
//							+ strGUID + " )\n";
//				} else {
//					sql = "delete from " + strProtocol + " where "
//							+ rs.getString(0, "zhujmc") + "=" + strGUID;
//				}
//				flag = con.getDelete(sql);
//				if (flag == -1) {
//					// 删除失败提示
//					message = error007;
//					con.rollBack();
//					con.Close();
//					return message;
//				}
//				if (strOperation.equals("0")) {// 插入或者修改
//					while (rs.next()) {
//						String nodeName = rs.getString("zidmc"); // 字段名
//						String dataType = rs.getString("leix"); // 字段类型
//						boolean isNullable = rs.getString("weik")
//								.equalsIgnoreCase("Y"); // 是否可为空
//						String transcodeSQL = rs.getString("zhuanmsql"); // 转码SQL
//						String nodeText = eData.getChildText(nodeName
//								.toUpperCase()); // 值
//						// 形成insert的字段描述部分
//						bufferTable.append(nodeName).append(",");
//						// 验证数据正确性
//						String valmsg = ValidateData(con, nodeName, isNullable,
//								transcodeSQL, dataType, nodeText);
//						if (valmsg != null) {
//							con.rollBack();
//							con.Close();
//							return "-1,012," + valmsg;
//						}
//						// 形成insertSQL值段
//						if ("varchar".equals(dataType)
//								|| "number".equals(dataType)
//								|| "id".equals(dataType)) {
//							bufferValus.append("'" + nodeText + "'");
//						} else if ("bianm".equals(dataType)) {
//							bufferValus.append("(" + transcodeSQL + "'"
//									+ nodeText + "')");
//						} else if ("date".equals(dataType)) {
//							bufferValus.append("to_date('" + nodeText
//									+ "','YYYY-MM-DD')");
//						} else if ("time".equals(dataType)) {
//							bufferValus.append("to_date('" + nodeText
//									+ "','YYYY-MM-DD HH24:MI:SS')");
//						} else if ("decode".equals(dataType)) {// varchar,nubmer
//							bufferValus.append("("
//									+ transcodeSQL.replaceAll("%%", "'"
//											+ nodeText + "'") + ")");// 替换参数
//						} else {// 不识别的类型
//							message = error011 + ":" + dataType;
//							con.rollBack();
//							con.Close();
//							return message;
//						}
//						bufferValus.append(",");
//					}
//					rs.close();
//					if (bufferTable.length() > 0 || bufferValus.length() > 0) {
//						bufferTable.deleteCharAt(bufferTable.length() - 1);
//						bufferValus.deleteCharAt(bufferValus.length() - 1);
//						sql = "insert into " + strProtocol + "("
//								+ bufferTable.toString() + ") values\n("
//								+ bufferValus.toString() + ")";
//						System.out.println("执行的sql:" + sql);
//						flag = con.getInsert(sql);
//						if (flag == -1) {
//							throw new SQLException();
//						}
//					} else {
//						message = "-1,015,接收端未配置协议" + strProtocol + "。";
//						con.rollBack();
//						con.Close();
//						return message;
//					}
//				}
//			}
//			con.commit();
//			con.Close();
//		} catch (IOException e) {
//			con.rollBack();
//			con.Close();
//			message = error001;// zhixzt cuowlb zhixbz
//			return message;
//		} catch (JDOMException e1) {
//			con.rollBack();
//			con.Close();
//			message = error002;// zhixzt cuowlb zhixbz
//			return message;
//		} catch (SQLException e2) {
//			con.rollBack();
//			con.Close();
//			message = error003;// zhixzt cuowlb zhixbz
//			return message;
//		} catch (Exception e4) {
//			e4.printStackTrace();
//			con.rollBack();
//			con.Close();
//			message = error014;// zhixzt cuowlb zhixbz
//			return message;
//		} finally {
//			con.Close();
//			Xiejsrzb(strOrgID, tT, tN, message, "2");
//		}
//		return message;
//
//	}
//
//}
