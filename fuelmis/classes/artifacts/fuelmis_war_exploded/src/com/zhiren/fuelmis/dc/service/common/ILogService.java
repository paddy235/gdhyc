package com.zhiren.fuelmis.dc.service.common;

/**
 * @author 陈宝露
 */
public interface ILogService {
	void insertLog(String tableName, String className, String methodName,
			String logs, String str1, String str2, String str3, String str4,
			String str5);
}
