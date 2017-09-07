package com.zhiren.fuelmis.dc.webService;

import static org.junit.Assert.*;

import java.net.URL;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.codehaus.xfire.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.activation.DataHandler;
import javax.xml.rpc.ParameterMode;

import com.zhiren.fuelmis.dc.service.impl.webInterface.InterFac_dtImpl;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml",
		"file:WebContent/WEB-INF/spring-servlet.xml" })
public class InterFac_dtTest {
@Autowired
private InterFac_dtImpl inf;
	@Test
	public void testInceptWenj() {
		// ----------------------------------------开始提及流程---------------------------------------------------------------------------------
		
//		Client client;
//		try {
//			client = new Client(new URL("http://localhost:8080/FileMIS/FileService.jws?wsdl"));
//			Object[] parms = { "g","g","g","g", new Byte[]{}};
//			Object[] xmlReturn = client.invoke("upLoadFile", new Object[]{"g","g","g","g",new byte[]{12,13}});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// --------------------------------------------------------------------------------------------------------------------------------
		try{
//		Service service = new Service();
//		Call call = (Call) service.createCall();// 远程调用者
		Call call =new Call("http://localhost:8080/FileMIS/FileService.jws");
//		java.net.URL url = new java.net.URL("http://localhost:8080/FileMIS/FileService.jws");
//		call.setTargetEndpointAddress(url);
		call.setOperationName("test");
//		call.addParameter("AppID", XMLType.SOAP_STRING, ParameterMode.IN);
//		call.addParameter("OperationID", XMLType.SOAP_STRING, ParameterMode.IN);
//		call.addParameter("FileName", XMLType.SOAP_BASE64BINARY, ParameterMode.IN);
//		call.addParameter("Description", XMLType.SOAP_STRING, ParameterMode.IN);
//		call.addParameter("FileData", XMLType.SOAP_BASE64BINARY, ParameterMode.IN);
//		call.setReturnType(XMLType.SOAP_STRING);
		String s=(String)call.invoke(new Object[] { "g", "2".getBytes()});
		System.out.println(s);
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}

	@Test
	public void testInterFac_dt() {
	
	}

	@Test
	public void testRequest() {
		inf.request("0");
	}

	@Test
	public void testRequestall() {
		inf.requestall();
	}

	@Test
	public void testRequestallTrans() {
		fail("Not yet implemented");
	}

	@Test
	public void testSqlExeStringArrayBoolean() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetJiecxx_Sj() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSqlString() {
		fail("Not yet implemented");
	}

	@Test
	public void testSqlExeStringStringArrayBoolean() {
		fail("Not yet implemented");
	}

}
