package com.zhiren.fuelmis.dc.service.webInterface;


/** 
 * @author 陈宝露
 */
public interface IWSCommonService { 
	public String[] sqlExe(String[] sqls,boolean isTransaction);
	
	public String TransWenj(String user, String password, byte[] XMLData);

}
