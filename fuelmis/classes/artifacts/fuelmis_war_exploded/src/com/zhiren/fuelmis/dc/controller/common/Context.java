package com.zhiren.fuelmis.dc.controller.common;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Context {

	private static Logger log = Logger.getLogger(Context.class);

	// 配置文件
	private Properties prop = null;

	// 端口
	private int port = 9089;
	
	//图片路径
	private String imgDir = "upload";
	

	public Context() {
		prop = Context.getProByCustomPath("/", "application.properties");
		if (prop == null) {
			log.error("找不到application.properties属性文件!");
		}
		this.port = Integer.parseInt(prop.getProperty("port"));
		this.imgDir = prop.getProperty("imgDir");
	}

	/**
	 * 获取自定义包路径的配置文件
	 * 
	 * @param customPath
	 *            自定义路径
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static Properties getProByCustomPath(String customPath,
			String fileName) {
		InputStream in = null;
		Properties prop = new Properties();
		try {
			in = Context.class.getResourceAsStream(customPath + fileName);
			prop.load(in);
		} catch (Exception e) {
			throw new RuntimeException("获取文件[" + fileName + "]失败", e);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					throw new RuntimeException("关闭流失败", e);
				}
		}
		return prop;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getImgDir() {
		return imgDir;
	}

	public void setImgDir(String imgDir) {
		this.imgDir = imgDir;
	}
	
	

}
