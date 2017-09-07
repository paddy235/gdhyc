package com.zhiren.fuelmis.dc.service.impl.webInterface;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.zhiren.fuelmis.dc.entity.common.CommonBean;
import com.zhiren.fuelmis.dc.entity.common.MainGlobal;
import com.zhiren.fuelmis.dc.entity.common.ResultSetList;
import com.zhiren.fuelmis.dc.entity.common.TransBean;
import com.zhiren.fuelmis.dc.service.webInterface.IWSCommonService;
import com.zhiren.fuelmis.dc.service.webInterface.InterFac_dt;
import com.zhiren.fuelmis.dc.utils.En_Decrypt_ZR;
import com.zhiren.fuelmis.dc.utils.PropertiesUtil;

import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.utils.ByteArrayOutputStream;
import org.codehaus.xfire.client.Client;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 厂级服务 厂级： InterFac 名称 类型 方法名1： 响应请求 request 参数名1： 用户名 usr String 参数名2：
 *         密码 password String 参数名3： 任务名 task String 返回值： 成功/失败1,失败2 String
 * 
 *         方法名2： 响应请求 requestall 参数名1： 用户名 usr String 参数名2： 密码 password String
 *         返回值： 成功/失败1,失败2 String
 *
 */
// 与中电投有两个区别：不使用电厂信息表，因为id是唯一的CreateSql、requestall、request的sql的diancxxb_id去掉

@org.springframework.stereotype.Service
public class InterFac_dtImpl implements InterFac_dt {
	private static final String error006 = "-1,006,远程webservice服务出现未知问题，可能是webservice部署失败。！";
	private static final String error101 = "-1,101,网络连接失败，或url不能定位到服务！";
	private static final String error102 = "-1,102,资源定位符url不符合其规则！";
	private static final String error103 = "-1,103,任务发送配置sql语句执行时出错！";//
	private static final String error104 = "-1,104,本地web服务部署失败！";
	private static final String error105 = "-1,105,本地位置异常！";

	private static final String rizPath = "c:/fasrz";// 发送日志路径
	private String user;
	private String password;
	private String endpointAddress;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IWSCommonService iWSCommonService;
@Override
	public String inceptWenj(String user, String password, byte[] XMLData) {
		// 对已发布文件操作
		return iWSCommonService.TransWenj(user, password, XMLData);
	}

	public InterFac_dtImpl() {
		super();
		// TODO 自动生成构造函数存根
		// String mingc="";
		// String sql="select zhi from xitxxb where leib='接口用' and
		// mingc='"+mingc+"'";
		// mingc="接口上传用户名";
		// user=jdbcTemplate.queryForObject(sql, String.class);
		// mingc="接口上传密码";
		// password=jdbcTemplate.queryForObject(sql, String.class);
		// mingc="入口地址";
		// endpointAddress=jdbcTemplate.queryForObject(sql, String.class);

		// user=MainGlobal.getXitxx_item(jdbcTemplate,"接口用","接口上传用户名", "0", "
		// ");
		// password=MainGlobal.getXitxx_item(jdbcTemplate,"接口用","接口上传密码", "0", "
		// ");
		// endpointAddress=MainGlobal.getXitxx_item(jdbcTemplate,"接口用","入口地址",
		// "0", " ");
	}

	private void init() {
		user = MainGlobal.getXitxx_item(jdbcTemplate, "接口用", "接口上传用户名", "0", " ");
		password = MainGlobal.getXitxx_item(jdbcTemplate, "接口用", "接口上传密码", "0", " ");
		endpointAddress = MainGlobal.getXitxx_item(jdbcTemplate, "接口用", "入口地址", "0", " ");
	}
	@Override
	public void request(String task) {//// 远程调用、本地定时调
		String renwmc = "", renllx = "", id = "", shujjl = "", tiaoj = "", xml = "", id0 = "", tem1 = "", tem2 = "";
		String message = "";
		try {
			// 删除查看文件夹的所有文件
			File file = new File(rizPath);
			if (!file.exists()) {
				file.mkdir();
			}
			File[] files = file.listFiles();
			for (int j = 0; j < files.length; j++) {
				files[j].delete();
			}
			String sql = "select j.id id0,j.renwmc,j.renllx, j.renwbs id,p.renwsql  shujjl,p.renwbs||''''||j.renwbs||''''  tiaoj\n"
					+ // ||' '||p.renwbs||''''||changbb.id_jit||j.renwbs||''''
					"from jiekrwb j,jiekfspzb p \n"
					+ "where j.renllx!=3 and (zhixzt=0 or zhixzt=-1) and j.renwmc=p.renwmc  and j.mingllx=p.mingllx and j.renwmc='"
					+ task + "' and shiwh is  null order by id0";

			SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
			Service service = new Service();

			while (rs.next()) {// a
				renwmc = rs.getString("renwmc");
				renllx = rs.getString("renllx");
				id = rs.getString("id");// 集团唯一任务标识id
				id0 = rs.getString("id0");// 任务id
				shujjl = rs.getString("shujjl");
				tiaoj = rs.getString("tiaoj");
				shujjl = shujjl.replaceAll("%%", tiaoj);
				// if(renllx.equals("2")){// 修改
				// xml=CreateXml(renwmc,"1",id,
				// con.getResultSet(shujjl));//如果是修改要变为删除和插入两个命令帧
				// Call call = (Call) service.createCall();//远程调用者
				// call.setTargetEndpointAddress(new
				// java.net.URL(endpointAddress));
				// call.setOperationName("incept");
				// call.addParameter("user",
				// XMLType.SOAP_STRING,ParameterMode.IN);
				// call.addParameter("password",
				// XMLType.SOAP_STRING,ParameterMode.IN);
				// call.addParameter("XMLData",
				// XMLType.SOAP_BASE64BINARY,ParameterMode.IN);
				// call.setReturnType(XMLType.SOAP_STRING);
				// tem1=String.valueOf(call.invoke(new Object[]
				// {user,password,xml.getBytes()}));//写日志
				//
				// xml=CreateXml(renwmc,"0",id,
				// con.getResultSet(shujjl));//如果是修改要变为删除和插入两个命令帧
				// Call call1 = (Call) service.createCall();//远程调用者
				// call1.setTargetEndpointAddress(new
				// java.net.URL(endpointAddress));
				// call1.setOperationName("incept");
				// call1.addParameter("user",
				// XMLType.SOAP_STRING,ParameterMode.IN);
				// call1.addParameter("password",
				// XMLType.SOAP_STRING,ParameterMode.IN);
				// call1.addParameter("XMLData",
				// XMLType.SOAP_BASE64BINARY,ParameterMode.IN);
				// call1.setReturnType(XMLType.SOAP_STRING);
				// tem2=String.valueOf(call.invoke(new Object[]
				// {user,password,xml.getBytes()}));//写日志
				// //写日志
				// Xierz(tem2+tem1,id0);
				// }else{//新增删除
				if (renllx.equals("2")) {// 因为插入前都要删除、所以修改与增加是一样的都是先删除后新增
					renllx = "0";
				}
				xml = CreateXml(renwmc, renllx, id, jdbcTemplate.queryForRowSet(shujjl));
				Call call = (Call) service.createCall();// 远程调用者
				java.net.URL url = new java.net.URL(endpointAddress);
				call.setTargetEndpointAddress(url);
				call.setOperationName("incept");
				call.addParameter("user", XMLType.SOAP_STRING, ParameterMode.IN);
				call.addParameter("password", XMLType.SOAP_STRING, ParameterMode.IN);
				call.addParameter("XMLData", XMLType.SOAP_BASE64BINARY, ParameterMode.IN);
				call.setReturnType(XMLType.SOAP_STRING);
				tem1 = String.valueOf(call.invoke(new Object[] { user, password, xml.getBytes() }));// 写日志777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777
				Xierz(tem1, id0);
				// }

			}
			StringBuffer id0s = new StringBuffer();
			List<String> sqls = CreateSql(id0s);
			for (int ii = 0; ii < sqls.size(); ii++) {
				Call call = (Call) service.createCall();// 远程调用者
				call.setTargetEndpointAddress(new java.net.URL(endpointAddress));
				call.setOperationName("execute");
				call.addParameter("user", XMLType.SOAP_STRING, ParameterMode.IN);
				call.addParameter("password", XMLType.SOAP_STRING, ParameterMode.IN);
				call.addParameter("sql", XMLType.SOAP_STRING, ParameterMode.IN);
				call.setReturnType(XMLType.SOAP_STRING);

				String data = String.valueOf(call.invoke(new Object[] { user, password, sqls.get(ii).toString() }));// 写日志
				// String
				// in=sqls.get(ii).toString().substring(sqls.get(ii).toString().lastIndexOf("in"));
				Xierzexe(data, id0s.toString());
			}
		} catch (MalformedURLException e) {// 构造url时出错
			message = error102;
			e.printStackTrace();
		} catch (RemoteException e) {// 远程未知错误或网络错误No route to host: connect
			e.printStackTrace();
			if (e.getCause() != null && e.getCause().getMessage().indexOf(":") != -1 && e.getCause().getMessage()
					.substring(0, e.getCause().getMessage().indexOf(":")).equals("No route to host")) {// 网络错误
				message = error101;
			} else {
				message = error006;
			}
		} catch (ServiceException e) {
			message = error104;
			e.printStackTrace();
		} catch (Exception e) {
			message = error103;
			e.printStackTrace();
		} finally {
			if (!message.equals("")) {
				Xierz(message, id0);
			}
		}
		return;
	}
	@Override
	public void requestall() {//// 远程调用、本地定时调用
		init();
		String renwmc = "", renllx = "", id = "", shujjl = "", tiaoj = "", xml = "", id0 = "", tem1 = "", tem2 = "";
		String message = "";
		// 删除查看文件夹的所有文件
		File file = new File(rizPath);
		if (!file.exists()) {
			file.mkdir();
		}
		File[] files = file.listFiles();
		for (int j = 0; j < files.length; j++) {
			files[j].delete();
		}

		String sql = "select j.id id0,j.renwmc,j.renllx,j.renwbs id,p.renwsql  shujjl,p.renwbs||''''||j.renwbs||''''  tiaoj\n"
				+ // ||' '||p.renwbs||''''||changbb.id_jit||j.renwbs||''''
				"from jiekrwb j,jiekfspzb p \n"
				+ "where j.renllx!=3 and (zhixzt=0 or zhixzt=-1) and j.renwmc=p.renwmc and j.mingllx=p.mingllx and shiwh is  null order by decode(j.renwmc,'shouhcrbb',0,1),id0";
		try {
			SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
			Service service = new Service();

			while (rs.next()) {// a
				renwmc = rs.getString("renwmc");
				renllx = rs.getString("renllx");
				id = rs.getString("id");// 集团唯一任务标识id
				id0 = rs.getString("id0");// 任务id
				shujjl = rs.getString("shujjl");
				tiaoj = rs.getString("tiaoj");

				shujjl = shujjl.replaceAll("%%", tiaoj);
				// if(renllx.equals("2")){// 修改
				// xml=CreateXml(renwmc,"1",id,
				// con1.getResultSet(shujjl));//如果是修改要变为删除和插入两个命令帧
				// Call call = (Call) service.createCall();//远程调用者
				// call.setTargetEndpointAddress(new
				// java.net.URL(endpointAddress));
				// call.setOperationName("incept");
				// call.addParameter("user",
				// XMLType.SOAP_STRING,ParameterMode.IN);
				// call.addParameter("password",
				// XMLType.SOAP_STRING,ParameterMode.IN);
				// call.addParameter("XMLData",
				// XMLType.SOAP_BASE64BINARY,ParameterMode.IN);
				// call.setReturnType(XMLType.SOAP_STRING);
				// tem1=String.valueOf(call.invoke(new Object[]
				// {user,password,xml.getBytes()}));//写日志
				//
				// xml=CreateXml(renwmc,"0",id,
				// con1.getResultSet(shujjl));//如果是修改要变为删除和插入两个命令帧
				// Call call1 = (Call) service.createCall();//远程调用者
				// call1.setTargetEndpointAddress(new
				// java.net.URL(endpointAddress));
				// call1.setOperationName("incept");
				// call1.addParameter("user",
				// XMLType.SOAP_STRING,ParameterMode.IN);
				// call1.addParameter("password",
				// XMLType.SOAP_STRING,ParameterMode.IN);
				// call1.addParameter("XMLData",
				// XMLType.SOAP_BASE64BINARY,ParameterMode.IN);
				// call1.setReturnType(XMLType.SOAP_STRING);
				// tem2=String.valueOf(call.invoke(new Object[]
				// {user,password,xml.getBytes()}));//写日志
				// //写日志
				// Xierz(tem2+tem1,id0);
				// }else{//新增删除
				if (renllx.equals("2")) {// 因为插入前都要删除、所以修改与增加是一样的都是先删除后新增
					renllx = "0";
				}
				xml = CreateXml(renwmc, renllx, id, jdbcTemplate.queryForRowSet(shujjl));
				Call call = (Call) service.createCall();// 远程调用者
				java.net.URL url = new java.net.URL(endpointAddress);
				call.setTargetEndpointAddress(url);
				call.setOperationName("incept");
				call.addParameter("user", XMLType.SOAP_STRING, ParameterMode.IN);
				call.addParameter("password", XMLType.SOAP_STRING, ParameterMode.IN);
				call.addParameter("XMLData", XMLType.SOAP_BASE64BINARY, ParameterMode.IN);
				call.setReturnType(XMLType.SOAP_STRING);
				tem1 = String.valueOf(call.invoke(new Object[] { user, password, xml.getBytes("gb2312") }));// 写日志
				Xierz(tem1, id0);
				// }

			}
			StringBuffer id0s = new StringBuffer();
			List<String> sqls = CreateSql(id0s);
			for (int ii = 0; ii < sqls.size(); ii++) {
				Call call = (Call) service.createCall();// 远程调用者
				call.setTargetEndpointAddress(new java.net.URL(endpointAddress));
				call.setOperationName("execute");
				call.addParameter("user", XMLType.SOAP_STRING, ParameterMode.IN);
				call.addParameter("password", XMLType.SOAP_STRING, ParameterMode.IN);
				call.addParameter("sql", XMLType.SOAP_STRING, ParameterMode.IN);
				call.setReturnType(XMLType.SOAP_STRING);

				String data = String.valueOf(call.invoke(new Object[] { user, password, sqls.get(ii).toString() }));// 写日志
				// String
				// in=sqls.get(ii).toString().substring(sqls.get(ii).toString().lastIndexOf("in"));
				Xierzexe(data, id0s.toString());
			}
		} catch (MalformedURLException e) {// 构造url时出错
			message = error102;
			e.printStackTrace();
		} catch (RemoteException e) {// 远程未知错误或网络错误No route to host: connect
			System.out.println(e.getMessage());
			if (e.getCause() != null && e.getCause().getMessage().indexOf(":") != -1 && e.getCause().getMessage()
					.substring(0, e.getCause().getMessage().indexOf(":")).equals("No route to host")) {// 网络错误
				message = error101;
			} else {
				message = error006;
			}
		} catch (ServiceException e) {
			message = error104;
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!message.equals("")) {
				Xierz(message, id0);
			}
		}
		return;
	}

	private String CreateXml(String shujxy, String caoz, String guanlId, SqlRowSet rs) {
		String xmlAray = "";// 没有记录客户端异常。。。。。
		// TODO 自动生成方法存根
		Element root = new Element("命令帧");
		Document document = new Document(root);

		// root.setAttribute(new Attribute("vin", "123fhg5869705iop90"));
		root.addContent(new Element("数据协议").addContent(shujxy));
		root.addContent(new Element("操作").addContent(caoz));
		root.addContent(new Element("主键").addContent(guanlId));
		try {
			while (rs.next()) {
				Element elShujjl = new Element("数据记录");
				root.addContent(elShujjl);
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {//
					if(rs.getString(i)!=null){
//						elShujjl.addContent(new Element(rs.getMetaData().getColumnName(i)).addContent(new String(rs.getString(i).getBytes("utf-8"),"utf-8")));
						elShujjl.addContent(new Element(rs.getMetaData().getColumnName(i)).addContent(rs.getString(i)));
					}else{
						elShujjl.addContent(new Element(rs.getMetaData().getColumnName(i)).addContent(""));
					}

				}
			}
			File file = new File(rizPath);
			FileWriter writer = new FileWriter(file.getAbsolutePath() + "/" + shujxy + guanlId + caoz + ".xml");
			XMLOutputter outputter = new XMLOutputter();
			Format format = Format.getPrettyFormat();
			format.setEncoding("gb2312");
			// format.setOmitDeclaration(true);
			outputter.setFormat(format);
			outputter.output(document, writer);
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			outputter.output(document, bo);
			xmlAray = bo.toString("gb2312");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return xmlAray;
	}
	@Override
	public void requestallTrans() {// 执行所有的事务
		TransBean tranbean = null;
		//
		String xml = "", id0 = "", tem1 = "", shiwh0 = "", shiwh1 = "", tiaoj = "", shujjl = "";
		byte[] xml_by = null;
		String message = "";
		// 删除查看文件夹的所有文件
		File file = new File(rizPath);
		if (!file.exists()) {
			file.mkdir();
		}
		File[] files = file.listFiles();
		for (int j = 0; j < files.length; j++) {
			files[j].delete();
		}
		// 表转换成事务集合
		List<Object> transSet = new ArrayList<Object>();
		String sql = "select j.id id0,j.renwmc,j.renllx,changbb_id,to_char(renwsj,'yyyy-mm-dd hh24:mi:ss')renwsj,j.renwbs id,p.renwsql  shujjl,p.renwbs||''''||j.renwbs||''''  tiaoj,shiwh\n"
				+ // ||' '||p.renwbs||''''||changbb.id_jit||j.renwbs||''''
				"from jiekrwb j,jiekfspzb p \n"
				+ "where j.renllx!=3 and (zhixzt=0 or zhixzt=-1) and j.renwmc=p.renwmc and j.mingllx=p.mingllx  and shiwh is not null order by shiwh,id0";
		ResultSetList rs = jdbcTemplate.queryForObject(sql, ResultSetList.class);
		Service service = new Service();
		try {
			List<CommonBean> comList = null;
			while (rs.next()) {// a
				shiwh1 = rs.getString("shiwh");// 当前任务的事务号

				// 命令
				CommonBean comBean = new CommonBean();
				comBean.setCaoz(rs.getString("renllx"));// 每条记录都是一个命令
				comBean.setDiancxxb_id(rs.getString("changbb_id"));
				comBean.setRenwsj(rs.getString("renwsj"));
				comBean.setShujxy(rs.getString("renwmc"));
				comBean.setZhuj(rs.getString("id"));
				List<List<String[]>> shujList = new ArrayList<List<String[]>>();
				shujjl = rs.getString("shujjl");
				tiaoj = rs.getString("tiaoj");
				shujjl = shujjl.replaceAll("%%", tiaoj);
				loadDateRec(shujjl, shujList);// 加载数据shujjl到shujList中
				comBean.setShujjl(shujList);
				if (shiwh1.equals(shiwh0)) {// 如果相等就说明是同一个事务
					comList.add(comBean);// 加入事务的命令序列
				} else {// 新事务：每个任务号的变化都生产一个任务号
					tranbean = new TransBean();
					tranbean.setShiwbh(shiwh1);
					comList = new ArrayList<CommonBean>();// 命令序列
					comList.add(comBean);// 命令
					tranbean.setListCommon(comList);
					transSet.add(tranbean);
				}
				shiwh0 = shiwh1;// 上次事务
			}
			// 执行事务集合transSet
			for (int jj = 0; jj < transSet.size(); jj++) {
				Call call = (Call) service.createCall();// 远程调用者
				java.net.URL url = new java.net.URL(endpointAddress);
				call.setTargetEndpointAddress(url);
				call.setOperationName("incept");
				call.addParameter("user", XMLType.SOAP_STRING, ParameterMode.IN);
				call.addParameter("password", XMLType.SOAP_STRING, ParameterMode.IN);
				call.addParameter("XMLData", XMLType.SOAP_BASE64BINARY, ParameterMode.IN);
				call.setReturnType(XMLType.SOAP_STRING);
				tranbean = (TransBean) transSet.get(jj);
				xml = tranbean.CreateXml(rizPath);
				xml_by = En_Decrypt_ZR.encryptByDES(xml.getBytes());// 加密
				tem1 = String.valueOf(call.invoke(new Object[] { user, password, xml_by }));// 写日志
				Xierztrans(tem1, tranbean.getShiwbh());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			message = error103;
		} catch (MalformedURLException e) {// 构造url时出错
			message = error102;
			e.printStackTrace();
		} catch (RemoteException e) {// 远程未知错误或网络错误No route to host: connect
			System.out.println(e.getMessage());
			if (e.getCause() != null && e.getCause().getMessage().indexOf(":") != -1 && e.getCause().getMessage()
					.substring(0, e.getCause().getMessage().indexOf(":")).equals("No route to host")) {// 网络错误
				message = error101;
			} else {
				message = error006;
			}
		} catch (ServiceException e) {
			message = error104;
			e.printStackTrace();
		} catch (Exception e) {
			message = error105;
			e.printStackTrace();
		} finally {

			if (!message.equals("")) {
				Xierztrans(message, tranbean.getShiwbh());
			}
		}
		return;
	}

	private void loadDateRec(String sql, List<List<String[]>> list) throws SQLException {

		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);

		while (rs.next()) {// 数据记录集合
			List<String[]> zidList = new ArrayList<String[]>();
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {//
				zidList.add(new String[] { rs.getMetaData().getColumnName(i), rs.getString(i) });
			}
			list.add(zidList);
		}
	}

	private List<String> CreateSql(StringBuffer id0s) {
		List<String> resultSql = new ArrayList<String>();

		String ids = "";
		String sql = "select j.id,p.renwsql biaom,p.gengxy,j.minglcs gengxyz,p.renwbs,j.renwbs renwbsz\n"
				+ "from jiekrwb j,jiekfspzb p\n"
				+ "where j.renwmc=p.renwmc and j.mingllx=p.mingllx and j.renllx=3 and zhixzt=0 "
				+ " order by biaom,gengxy,gengxyz,renwbs";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		String biaom = "", gengxy = "", gengxyz = "", renwbs = "", renwbsz = "";
		String biaom_p = "", gengxy_p = "", gengxyz_p = "", renwbs_p = "";
		String in = "(";
		int kk = 0;
		try {
			while (rs.next()) {
				ids = ids + rs.getString("id") + ",";
				biaom = rs.getString("biaom");
				gengxy = rs.getString("gengxy");
				gengxyz = rs.getString("gengxyz");
				renwbs = rs.getString("renwbs");
				renwbsz = rs.getString("renwbsz");
				if (kk == 0 || (biaom.equals(biaom_p) && gengxy.equals(gengxy_p) && gengxyz.equals(gengxyz_p)
						&& renwbs.equals(renwbs_p))) {// 如果当前值上一个相等或第一个值则说明为同一个分组
					// 追加in后的（。。。）
					biaom_p = biaom;
					gengxy_p = gengxy;
					gengxyz_p = gengxyz;
					renwbs_p = renwbs;

					if (kk == 0) {
						in += renwbsz;
					} else {
						in += "," + renwbsz;
					}
					kk++;
				} else { // 不相等则产生一个新分组。
					in += ")";
					resultSql.add(
							"update " + biaom_p + " set " + gengxy_p + "'" + gengxyz_p + "' where " + renwbs_p + in);
					// 初始化各个变量
					kk = 1;
					in = "(";
					biaom_p = biaom;
					gengxy_p = gengxy;
					gengxyz_p = gengxyz;
					renwbs_p = renwbs;
					in += renwbsz;
				}
			}
			// 最后一个分组
			if (kk != 0) {
				in += ")";
				resultSql.add("update " + biaom + " set " + gengxy + "'" + gengxyz + "' where " + renwbs + in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		if (!ids.equals("")) {
			id0s.append(ids.substring(0, ids.lastIndexOf(",")));
		}
		return resultSql;
	}

	private void Xierz(String data, String id0) {// 写日志
		// "1,'000','接收成功'";
		String[] dat = data.split(",");

		String sql = "update jiekrwb\n" + "set jiekrwb.zhixzt=" + dat[0] + ",\n" + "cuowlb='" + dat[1] + "',\n"
				+ "zhixbz='" + dat[2] + "',\n" + "zhixsj=sysdate\n" +

		"where id='" + id0 + "'";
		jdbcTemplate.update(sql);
	}

	private void Xierztrans(String data, String id0) {// 写日志
		// "1,'000','接收成功'";
		String[] dat = data.split(",");

		String sql = "update jiekrwb\n" + "set jiekrwb.zhixzt=" + dat[0] + ",\n" + "cuowlb='" + dat[1] + "',\n"
				+ "zhixbz='" + dat[2] + "',\n" + "zhixsj=sysdate\n" +

		"where shiwh='" + id0 + "'";
		jdbcTemplate.update(sql);
	}

	private void Xierzexe(String data, String in) {// 执行日志
		// "1,'000','接收成功'";
		String[] dat = data.split(",");

		String sql = "update jiekrwb\n" + "set jiekrwb.zhixzt=" + dat[0] + ",\n" + "cuowlb='" + dat[1] + "',\n"
				+ "zhixbz='" + dat[2] + "',\n" + "zhixsj=sysdate\n" + "where id in(" + in + ")";
		jdbcTemplate.update(sql);
	}

//	public String[] sqlExe(String[] sqls, boolean isTransaction) throws Exception {
//		// 1连接数据库
//		String ConnStr = "", UserName = "", UserPassword = "";
//		String[] arryRes = new String[sqls.length];
//		if (ConnStr.equals("")) {
//			ConnStr = MainGlobal.getDb_jdbcDriverURL();
//		}
//		if (UserName.equals("")) {
//			UserName = MainGlobal.getDb_username();
//		}
//		if (UserPassword.equals("")) {
//			UserPassword = MainGlobal.getDb_password();
//		}
//
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection dBConnection = DriverManager.getConnection(ConnStr, UserName, UserPassword);
//		Statement st = dBConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//		// 2循环执行sqls，并记录执行结果。
//		try {
//			if (isTransaction) {// 如果是事务
//				dBConnection.setAutoCommit(false);
//				for (int i = 0; i < sqls.length; i++) {
//					try {
//						st.executeUpdate(sqls[i]);
//						arryRes[0] = "true";
//					} catch (SQLException e) {
//						// TODO 自动生成 catch 块
//						arryRes[0] = e.getLocalizedMessage();
//						e.printStackTrace();
//						dBConnection.rollback();
//						return arryRes;
//					}
//				}
//				dBConnection.commit();
//			} else {// 不是事务
//				for (int i = 0; i < sqls.length; i++) {
//					try {
//						st.executeUpdate(sqls[i]);
//						arryRes[i] = "true";
//					} catch (SQLException e) {
//						// TODO 自动生成 catch 块
//						arryRes[i] = e.getLocalizedMessage();
//						e.printStackTrace();
//					}
//				}
//			}
//		} finally {
//			st.close();
//			dBConnection.close();
//		}
//		return arryRes;
//	}
	@Override
	public String[] getJiecxx_Sj(String Type)  {
		// 说明：编写日期2009-02-23
		// 编写人员：zsj
		// 主要目的：基础信息上下同步
		// 主要功能：下级单位通过调用该函数访问上级单位的jws接口，返回数据
		String[] resultData = null;
		String ErrorMessage = "";
		String result = "";
		//
		if (!endpointAddress.equals("")) {
			// 得到了上级单位入口地址

			Service ser = new Service();
			Call call = null;
			try {

				// 调用接口程序
				call = (Call) ser.createCall();
				call.setTargetEndpointAddress(new java.net.URL(endpointAddress));
				call.setOperationName("getShangj_Jicxx");
				call.addParameter("Type", XMLType.SOAP_STRING, ParameterMode.IN);
				call.setReturnType(XMLType.SOAP_STRING);
				result = String.valueOf(call.invoke(new Object[] { Type }));

				// 将返回值转化为数组类型
				if (!result.equals("")) {

					resultData = result.split("~%&");
				}

			} catch (ServiceException e) {

				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
				ErrorMessage = error102;
				resultData = new String[] { "错误：" + ErrorMessage };

			} catch (RemoteException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
				ErrorMessage = error006;
				resultData = new String[] { "错误：" + ErrorMessage };

			} // 调用接口

		} else {

			ErrorMessage = "没有配置上级单位的入口地址";
			resultData = new String[] { "错误：" + ErrorMessage };
		}

		return resultData;
	}

	// 输入一个sql语句能够返回他的结果字符串，字符串格式 1123 ，20
	// 按天验证数据
	@Override
	public String getSqlString(String sql) {
		String reslutStr = "";

		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		try {
			int Colucount = rs.getMetaData().getColumnCount();
			if (rs.next()) {// 规定只能有一个返回值
				for (int i = 1; i <= Colucount; i++) {
					String tem = rs.getString(i);
					if (i == 1) {
						reslutStr += (tem == null ? "" : tem);
					} else {
						reslutStr += "," + (tem == null ? "" : tem);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return reslutStr;
	}
	@Override
	public String[] sqlExe(String diancxxb_id, String[] sqls, boolean isTransaction) {
		String ENDPOINTADDRESS = "";

		String[] resultArry;
		String sql = "select d.diancxxb_id,z.endpointaddress\n" + "from dianczhb d,jiekzhb z\n"
				+ "where d.jiekzhb_id=z.id and d.diancxxb_id=" + diancxxb_id;
		ResultSetList rs = jdbcTemplate.queryForObject(sql, ResultSetList.class);
		try {
			if (rs.next()) {
				ENDPOINTADDRESS = rs.getString("endpointaddress");
			}
			if (ENDPOINTADDRESS.equals("") || ENDPOINTADDRESS == null) {
				return new String[] { "该电厂没有入口地址！" };
			}
			Service service = new Service();
			Call call = (Call) service.createCall();// 远程调用者
			call.setTargetEndpointAddress(new java.net.URL(ENDPOINTADDRESS));
			call.setOperationName("sqlExe");
			call.addParameter("sqls", XMLType.SOAP_ARRAY, ParameterMode.IN);
			call.addParameter("isTransaction", XMLType.SOAP_BOOLEAN, ParameterMode.IN);
			call.setReturnType(XMLType.SOAP_ARRAY);
			resultArry = (String[]) call.invoke(new Object[] { sqls, Boolean.valueOf(isTransaction) });// 如果执行成功返回true
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return new String[] { "出错啦！" };
		} finally {

		}
		return resultArry;
	}
	// public String DataRollback(String user, String password, String OrgID,
	// String AccountDate, String DataID, String ModuleName,
	// String Operator, String Remarks) {
	// return DataServiceUtil.DataRollback(user, password, OrgID, AccountDate,
	// DataID, ModuleName, Operator, Remarks);
	// }
	//
	// public String DataRollbackJTToCom(String user,String password,String
	// reportcode,
	// String OpOrgId,String RptOrgId, String ReportDate,
	// String ReportName, String Operator, String OpDescription) {
	// return DataManServerUtil.DataRollbackJTToCom(user, password,
	// reportcode,OpOrgId,RptOrgId,
	// ReportDate,ReportName,Operator,OpDescription);
	// }

	@Override
    public String[]  sqlExe(String[] sqls,boolean isTransaction) {
        String[] arryRes=new String[sqls.length];
        //2循环执行sqls，并记录执行结果。
        try{
            if(isTransaction){//如果是事务
                for (int i=0;i<sqls.length;i++){
                    try {
                        jdbcTemplate.update(sqls[i]);
                        arryRes[0]="true";
                    } catch (Exception e) {
                        // TODO 自动生成 catch 块
                        arryRes[0]=e.getLocalizedMessage();
                        e.printStackTrace();
                        return arryRes;
                    }
                }
            }else{// 不是事务
                for (int i=0;i<sqls.length;i++){
                    try {
                        jdbcTemplate.update(sqls[i]);
                        arryRes[i]="true";
                    } catch (Exception e) {
                        // TODO 自动生成 catch 块
                        arryRes[i]=e.getLocalizedMessage();
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return arryRes;
    }
}
