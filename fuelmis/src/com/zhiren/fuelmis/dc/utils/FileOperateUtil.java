package com.zhiren.fuelmis.dc.utils;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zhiren.fuelmis.dc.controller.common.Context;



/**
* 
* 
* @date 2012-5-5 下午12:05:57
*/
public class FileOperateUtil {
	private static Logger log = Logger.getLogger(FileOperateUtil.class);
//	private static final String REALNAME = "realName";
//	private static final String STORENAME = "storeName";
//	private static final String SIZE = "size";
//	private static final String SUFFIX = "suffix";
//	private static final String CONTENTTYPE = "contentType";
//	private static final String CREATETIME = "createTime";
	private static final String UPLOADDIR = "uploadDir";

	/**
	 * 将上传的文件进行重命名
	 * @date 2012-3-29 下午3:39:53
	 * @param name
	 * @return
	 */
	private static String rename(String name) {

		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date()));
		Long random = (long) (Math.random() * now);
		String fileName = now + "" + random;

		if (name.indexOf(".") != -1) {
			fileName += name.substring(name.lastIndexOf("."));
		}

		return fileName;
	}

	/**
	 * 上传文件
	 * @date 2012-5-5 下午12:25:47
	 * @param request
	 * @param params
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> upload(HttpServletRequest request,String path) throws Exception {

		Map<String, String> result = new HashMap<String, String>();

		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		Properties prop = Context.getProByCustomPath("/", "application.properties");
		if (prop == null) {
			log.error("找不到application.properties属性文件!");
		}
		//暂时用服务器地址
		//String uploadDir = prop.getProperty("fujpath")+ FileOperateUtil.UPLOADDIR+File.separator;
//		
		
		String uploadDir = path + File.separator;
		
		
		File file = new File(uploadDir);

		if (!file.exists()) {
			file.mkdir();
		}

		String fileName = null;
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {

			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();

			fileName = mFile.getOriginalFilename();

			String storeName = rename(fileName);

			String newName = uploadDir + storeName;
			result.put("fileName", fileName);
			result.put("newName", newName);

			// 上传成为压缩文件
			FileOutputStream outputStream = new FileOutputStream(newName);
			
			FileCopyUtils.copy(mFile.getInputStream(), outputStream);
//			String result = "{msg:'附件上传成功！',id:'"+fuj.id+"',fujName:'"+fileName+"'}"
			outputStream.close();
		}
		return result;
	}

	public static void uploadWithRealName(HttpServletRequest request,String path) throws Exception {
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		Properties prop = Context.getProByCustomPath("/", "application.properties");
		if (prop == null) {
			log.error("找不到application.properties属性文件!");
		}
		String uploadDir = path + File.separator;


		File file = new File(uploadDir);

		if (!file.exists()) {
			file.mkdir();
		}
		for (Map.Entry<String, MultipartFile> entry:fileMap.entrySet()) {
			MultipartFile mFile = entry.getValue();
			String fileName = mFile.getOriginalFilename();
			if (!file.exists()) {
				file.mkdir();
			}
			FileOutputStream outputStream = new FileOutputStream(uploadDir+fileName);
			FileCopyUtils.copy(mFile.getInputStream(), outputStream);
			outputStream.close();
		}
	}
	public static Map<String, String> upload1(HttpServletRequest request,String path) throws Exception {

		Map<String, String> result = new HashMap<String, String>();

		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();
			String fileName = mFile.getOriginalFilename();

			//服务器上传文件
			String strAppID = "红雁池燃料系统.合同模板";
			String strOPID = "433";
//			String filename = mFile.getName();
			String description = "红雁池合同模板"+mFile.getName();
			// TODO Auto-generated method stub
//			File f = new File("D:\\", "服务器返回结果.doc");
			
			
			byte[] FileData = mFile.getBytes();
			org.apache.axis.client.Service service1 = new org.apache.axis.client.Service();
			Call call1 = (Call) service1.createCall();//远程调用者
			String url = PropertiesUtil.getValue("filemis_url");
			url +="/FileService.jws";
			//call1.setTargetEndpointAddress(new java.net.URL("http://10.115.25.199:9099/FileMIS/FileService.jws"));
			call1.setTargetEndpointAddress(new java.net.URL(url));
			call1.setOperationName("upLoadFile");
			call1.addParameter("AppID", XMLType.SOAP_STRING,ParameterMode.IN);
			call1.addParameter("OperationID", XMLType.SOAP_STRING,ParameterMode.IN);
			call1.addParameter("FileName", XMLType.SOAP_STRING,ParameterMode.IN);
			call1.addParameter("Description", XMLType.SOAP_STRING,ParameterMode.IN);
			call1.addParameter("FileData", XMLType.SOAP_BASE64BINARY,ParameterMode.IN);
			call1.setReturnType(XMLType.SOAP_STRING);
			String reValue=String.valueOf(call1.invoke(new Object[] { 
					strAppID, strOPID, fileName, description, FileData}));
			result.put("fid", reValue);
			result.put("fileName", fileName);
		}
		return result;
	}
	
	
	/**
	 * 下载
	 * @date 2012-5-5 下午12:25:39
	 * @param request
	 * @param response
	 * @param storeName
	 * @param contentType
	 * @param realName
	 * @throws Exception
	 */
	public static void download(HttpServletRequest request,
			HttpServletResponse response, String storeName, String contentType,
			String realName) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		Properties prop = Context.getProByCustomPath("/", "application.properties");
		if (prop == null) {
			log.error("找不到application.properties属性文件!");
		}
		String ctxPath = prop.getProperty("fujpath")+ FileOperateUtil.UPLOADDIR+File.separator;
//		String ctxPath = request.getSession().getServletContext()
//				.getRealPath("/")
//				+ FileOperateUtil.UPLOADDIR;
		String downLoadPath = ctxPath + storeName;

		long fileLength = new File(downLoadPath).length();

		response.setContentType(contentType);
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(realName.getBytes("utf-8"), "ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));

		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}
}
