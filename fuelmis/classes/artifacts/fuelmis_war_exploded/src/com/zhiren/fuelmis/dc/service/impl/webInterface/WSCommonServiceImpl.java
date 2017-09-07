package com.zhiren.fuelmis.dc.service.impl.webInterface;

import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.InputSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;



import com.zhiren.fuelmis.dc.service.webInterface.IWSCommonService;

/**
 * @author 陈宝露
 */
@Service
public class WSCommonServiceImpl implements IWSCommonService {

	private static final String error000 = "1,000,接收成功";
	private static final String error001 = "-1,001,XMLData数据转换为gb312编码时出错";
	private static final String error002 = "-1,002,文档不符合dom规范,可能是发送配置表中sql的字段为函数时没有写别名";
	private static final String error003 = "-1,003,双方没有安照接口协议传输数据，请检查发送接收的配置，例如发送数据的日期时间类型必须用格式字符串，接收端编码类型字段的配置等或远程数据库编码重复或违反唯一约束条件";//
	private static final String error004 = "-1,004,执行远程的sql时远程服务器数据库连接失败";
	private static final String error005 = "-1,005,执行远程的sql时出错，可能是客户端生成的sql语句不符合规范，请检查发送配置表";
	private static final String error007 = "-1,007,删除远程数据时没有找到主键字段名称，可能是远程接收配置没有设置主键名称!";
	private static final String error008 = "1,008,删除0条数据，也就是远程数据与本地数据没有同步";
	private static final String error009 = "-1,009,用户名称不在，你的电厂还没有在集团注册用户";
	private static final String error010 = "-1,010,用户账户的密码错误请检查系统密码设置";
	private static final String error011 = "-1,011,接收数据库的接收配置有不识别的类型";
	private static final String error013 = "-1,013,发送端与接收端配置的字段个数不一致。";
	private static final String error014 = "-1,014,未知异常。";
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public String[] sqlExe(String[] sqls, boolean isTransaction) {
		String[] arr = new String[sqls.length];
		for (int i = 0; i < sqls.length; i++) {
			try {
				jdbcTemplate.execute(sqls[i]);
				arr[i] = "true";
			} catch (Exception e) {
				arr[i] = e.getLocalizedMessage();
			}
		}
		return arr;
	}

	/**
	 * 
	 * @param user
	 *            用户名
	 * @param password
	 *            密码
	 * @return 检查接口账户表 验证登录信息
	 */	
	private int ValidateUser(String user, String password) {// 验证账户
		int v = 1;
		String sql = "select mim,id\n" 
				+ "from jiekzhb\n"
				+ "where jiekzhb.yonghmc='" + user + "'";
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		list = jdbcTemplate.queryForList(sql );
		if (list.size() > 0) {
			Map<String, Object> maps = list.get(0);
			if (password.equals(maps.get("MIM").toString())) {
				v = 1;// 如果用户存在，并且密码正确返回1
			} else {
				v = 0;// 密码错误
			}
		} else {
			v = -1;// 不存在该用户
		}
		
		 
		return v;
	}
	
	@Override
	public String TransWenj(String user, String password, byte[] XMLData) {// 对分公司发布文件的操作
		// xieb
		String message = "";
		int v = ValidateUser(user, password);
		if (v == -1) {// 用户不存在 
			message = error009;
			return message;
		} else if (v == 0) {// 密码错误
			message = error010;
			return message;
		} else {// 通过验证
			message = TransWenjData(XMLData);
		}
		return message;
	}


	@Transactional
	private  String TransWenjData(byte[] XMLData) {// 对分公司发布文件的操作 xieb
		String message = error000; // 返回信息
		String strOrgID = ""; // 组织ID
		String tN = ""; // 事务号
		String tT = ""; // 事务类型
		int flag;
		try {
			String xmlstr = new String(XMLData, "utf-8"); // 得到二进制XML数据信息转换为Str型
			System.out.println("1:"+xmlstr);
			StringReader sr = new StringReader(xmlstr); // 字符读取
			InputSource is = new InputSource(sr); //
			Document doc = (new SAXBuilder()).build(is);// 解读每一个数据帧文件
			Element root = doc.getRootElement(); // 得到XML的根
			tN = root.getChildText("TransNum"); // 事务号
			tT = root.getChildText("TransType"); // 事务类型
			Element etd = root.getChild("TransData"); // 事务对应数据
			List arrTasks = etd.getChildren(); // 数据数组
			StringBuffer bufferTable = new StringBuffer();
			StringBuffer bufferValus = new StringBuffer();
			for (int i = 0; i < arrTasks.size(); i++) { // 循环
				bufferTable.setLength(0);
				bufferValus.setLength(0);
				Element eTask = (Element) arrTasks.get(i); // 得到任务
				String strProtocol = eTask.getChildText("Protocol"); // 数据协议
				String strOperation = eTask.getChildText("Operation"); // 操作类型
				String strGUID = eTask.getChildText("GUID"); // 主键
				String strTime = eTask.getChildText("Time"); // 时间
				strOrgID = eTask.getChildText("OrgID"); // 组织ID
				Element eData = eTask.getChild("DataResource"); // 数据源
				List arrData = eData.getChildren(); // 数据源数组
				String sql = " select renwmc,jiekjspzb.zidmc,leix,jiekbmzhpzb.zidmc zhuanmsql,zhujmc,weik\n"
						+ " from jiekjspzb,jiekbmzhpzb\n"
						+ " where  jiekjspzb.bianm=jiekbmzhpzb.bianm(+) "
						+ " and renwmc='" + strProtocol + "' order by jiekjspzb.id";
				System.out.println("2:jiekjspz:"+sql);
				List<Map<String,Object>> rs=jdbcTemplate.queryForList(sql);
				System.out.println(rs.size());
				System.out.println(arrData.size());
				// 判定数据结构是否与接收端配置一致如果不一致关闭数据库连接返回提示信息
				if (arrData.size() != rs.size()) {
					message = error013;
					return message;
				}
				if (arrData.size() != rs.size()) {
					message = error013;
					return message;
				}
				// 删除操作
				if (!strProtocol.equals("fabwjb")) {
					sql = "delete from " + strProtocol + " where "
							+ rs.get(0).get("ZHUJMC")
							+ " = (select wenjb_id from fabwjb where id="
							+ strGUID + " )\n";
				} else {
					sql = "delete from " + strProtocol + " where "
							+ rs.get(0).get("ZHUJMC") + "=" + strGUID;
				}
				flag=jdbcTemplate.update(sql);
				if (flag == -1) {
					// 删除失败提示
					message = error007;
					return message;
				}
				if (strOperation.equals("0")) {// 插入或者修改
					for (Map<String,Object> m:rs) {
						String nodeName = m.get("ZIDMC").toString(); // 字段名
						String dataType = m.get("LEIX").toString(); // 字段类型
						boolean isNullable=false;
						if(m.get("WEIK")!=null){
							isNullable = m.get("WEIK").toString().equalsIgnoreCase("Y"); // 是否可为空
						}else{
							isNullable=true;
						}
						String transcodeSQL=null;
						if(m.get("ZHUANMSQL")!=null){
							transcodeSQL= m.get("ZHUANMSQL").toString(); // 转码SQL
						}

						String nodeText = eData.getChildText(nodeName.toUpperCase()); // 值
						// 形成insert的字段描述部分
						bufferTable.append(nodeName).append(",");
						// 验证数据正确性
						String valmsg = ValidateData(nodeName, isNullable,transcodeSQL, dataType, nodeText);
						if (valmsg != null) {
							return "-1,012," + valmsg;
						}
						// 形成insertSQL值段
						if ("varchar".equals(dataType)
								|| "number".equals(dataType)
								|| "id".equals(dataType)) {
							bufferValus.append("'" + nodeText + "'");
						} else if ("bianm".equals(dataType)) {
							bufferValus.append("(" + transcodeSQL + "'"
									+ nodeText + "')");
						} else if ("date".equals(dataType)) {
							bufferValus.append("to_date('" + nodeText
									+ "','YYYY-MM-DD')");
						} else if ("time".equals(dataType)) {
							bufferValus.append("to_date('" + nodeText
									+ "','YYYY-MM-DD HH24:MI:SS')");
						} else if ("decode".equals(dataType)) {// varchar,nubmer
							bufferValus.append("("
									+ transcodeSQL.replaceAll("%%", "'"
									+ nodeText + "'") + ")");// 替换参数
						} else {// 不识别的类型
							message = error011 + ":" + dataType;
							return message;
						}
						bufferValus.append(",");
					}
					if (bufferTable.length() > 0 || bufferValus.length() > 0) {
						bufferTable.deleteCharAt(bufferTable.length() - 1);
						bufferValus.deleteCharAt(bufferValus.length() - 1);
						sql = "insert into " + strProtocol + "("
								+ bufferTable.toString() + ") values\n("
								+ bufferValus.toString() + ")";
						System.out.println("执行的sql:" + sql);
						flag = jdbcTemplate.update(sql);
						if (flag == -1) {
							throw new SQLException();
						}
					} else {
						message = "-1,015,接收端未配置协议" + strProtocol + "。";
						return message;
					}
				}
			}

		} catch (IOException e) {
			message = error001;// zhixzt cuowlb zhixbz
            e.printStackTrace();
			return message;
		} catch (JDOMException e1) {
			message = error002;// zhixzt cuowlb zhixbz
            e1.printStackTrace();
			return message;
		} catch (SQLException e2) {
			message = error003;// zhixzt cuowlb zhixbz
            e2.printStackTrace();
			return message;
		} catch (Exception e4) {
			e4.printStackTrace();
			message = error014;// zhixzt cuowlb zhixbz
			return message;
		} finally {
			Xiejsrzb(strOrgID, tT, tN, message, "2");
		}
		return message;

	}
	private  String ValidateData( String nodeName, boolean isnullalbe, String transCodeSQL, String dataType,String data) {
		String errorMessage = null;
		if (!isnullalbe && (data == null || data.equals(""))) {
			// 判定数据不能为空
			errorMessage = nodeName + "不允许为空";
			return errorMessage;
		}
		if (dataType.equalsIgnoreCase("bianm")) {
			if (transCodeSQL == null || transCodeSQL.equals("")) {
				errorMessage = nodeName + "字段的转码配置错误";
				return errorMessage;
			}
//			if (!con.getHasIt(transCodeSQL + "'" + data + "'")) {
//				errorMessage = nodeName + "字段的" + data + "编码不能识别";
//				return errorMessage;
//			}
		}
		if (dataType.equalsIgnoreCase("decode")) {
			if (transCodeSQL == null || transCodeSQL.equals("")) {
				errorMessage = nodeName + "字段的转码配置错误";
				return errorMessage;
			}
			if (!isnullalbe) {
				List<Map<String,Object>> rs = jdbcTemplate.queryForList(transCodeSQL.replaceAll("%%", "'" + data + "'"));
				if (rs.size()!=0) {
					if (rs.get(0) == null || rs.get(0).equals("")) {
						errorMessage = nodeName + "字段的" + data + "在数据库中找不到对应的值";
					}
					return errorMessage;
				}
			}
		}
		return errorMessage;
	}
	private  void Xiejsrzb(String diancxxb_id, String renw,String renwbs, String message, String caoz) {// 写接收日志表
		String[] dat = message.split(",");
		System.out.println("renw的值是:" + renw);
		// if(dat[0].equals("-1")){//如果为错误
//		JDBCcon con = new JDBCcon();
		String sql = "insert into jiekjsrzb(id,diancxxb_id,renw,renwbs,shij,cuowdm,cuowxx,caoz,zhixzt)values(\n"
				+ "xl_xul_id.nextval,"
				+ diancxxb_id
				+ ",'"
				+ renw
				+ "',"
				+ renwbs
				+ ",sysdate,'"
				+ dat[1]
				+ "','"
				+ dat[2]
				+ "','"
				+ caoz + "'," + dat[0] + ")";
		jdbcTemplate.update(sql);
//		con.getInsert(sql);
//		con.Close();
		// }
	}
}
